package com.zhong.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhong.pojo.dto.LoginDTO;
import com.zhong.pojo.dto.RegisterDTO;
import com.zhong.pojo.enums.RoleEnum;
import com.zhong.pojo.po.User;
import com.zhong.service.UserService;
import com.zhong.other.result.BaseResult;
import com.zhong.other.utils.TokenProcessor;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: zhong
 * @Description: 系统用户登陆注册模块，包含修改
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController {

    @Autowired
    UserService userService;

    /**
     * 获取验证码，redis中保存验证码文本2分钟
     *
     * @return 验证码的base64编码
     */
    @ResponseBody
    @RequestMapping("/captcha")
    public BaseResult captcha() {
        //设置长宽，并且设置验证码个数
        SpecCaptcha specCaptcha = new SpecCaptcha(150, 50, 4);
        String code = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为2分钟
        userService.redisSaveCode(key, code);

        System.out.println("code:" + code);           //debug
        System.out.println("key:" + key);           //debug

        // 将key和base64返回给前端
        Map<String, Object> data = new HashMap<>();
        data.put("key", key);
        data.put("img", specCaptcha.toBase64());
        return BaseResult.successMsgAndData("获取验证码成功", data);
    }

    /**
     * 检查验证码
     *
     * @param key  redis中验证码的key
     * @param code 用户填写的验证码
     * @return
     */
    private BaseResult checkCode(String key, String code) {
        String rightCode = userService.redisGetCode(key);    // 取正确的验证码，总是小写
        userService.redisRemoveCode(key);    // 不管用户填入的验证码是否正确都移除redis中的验证码
        if (rightCode == null) {    // key失效
            return BaseResult.errorMsg("验证码失效");
        }
        if (!rightCode.equals(code)) {  // 验证码不正确
            return BaseResult.errorMsg("验证码错误");
        }
        return BaseResult.success();
    }

    /**
     * 登录
     *
     * @param loginDTO 登陆传输对象
     * @return
     */
    @PostMapping(value = "/login")
    public BaseResult login(@RequestBody LoginDTO loginDTO) {
        // 取数据
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        String key = loginDTO.getKey();
        String code = loginDTO.getCode();
        code = code.toLowerCase(); // 转换成小写

        // 检查验证码
        BaseResult res = checkCode(key, code);
        if (res.getStatus() != 200) return res;

        // 检查用户名
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User userObj = userService.getOne(queryWrapper);
        if (userObj == null) {  // 用户名不正确
            return BaseResult.errorMsg("用户名错误");
        }

        // 检查密码
        if (!userObj.getPassword().equals(password)) {    // 密码不正确
            return BaseResult.errorMsg("密码错误");
        }

        // 创建token
        String token = TokenProcessor.getInstance().makeToken();
        // 保存到Redis，登陆状态保存24小时
        userService.redisSaveUser(token, userObj);
        // 返回结果对象
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        return BaseResult.successMsgAndData("登录成功", data);
    }

    /**
     * 查看用户信息
     *
     * @param token 令牌
     * @return
     */
    @GetMapping(value = "/info")
    public BaseResult info(String token) {
        // 从redis中取用户
        User user = userService.redisGetUser(token);
        if (user == null) {  // 获取失败
            return BaseResult.errorMsg("获取用户信息失败");
        } else {    // 获取成功
            return BaseResult.successMsgAndData("获取用户信息成功", user);
        }
    }

    /**
     * 通过id获取user
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getUserById")
    public BaseResult getUserById(Integer id) {
        User user = userService.getById(id);
        return BaseResult.successData(user);
    }

    /**
     * 退出登录
     *
     * @param token 令牌
     * @return
     */
    @RequestMapping(value = "/logout")
    public BaseResult logout(String token) {
        // 从redis中移除用户
        userService.redisRemoveUser(token);
        return BaseResult.successMsg("退出登录成功");
    }

    /**
     * 注册账号
     *
     * @param registerDTO 注册数据传输对象
     * @return
     */
    @PostMapping("/register")
    public BaseResult register(@RequestBody RegisterDTO registerDTO) {
        // 获得信息
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String key = registerDTO.getKey();
        String code = registerDTO.getCode().toLowerCase();

        // 检查验证码
        BaseResult res = checkCode(key, code);
        if (res.getStatus() != 200) return res;

        // 检查账号是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User one = userService.getOne(queryWrapper);
        if (one != null) {  //账号重复
            return BaseResult.errorMsg("该账号已存在");
        }

        // 保存用户信息
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(RoleEnum.STUDENT);
        userService.save(newUser);

        return BaseResult.successMsg("注册成功");
    }

    /**
     * 修改个人信息
     *
     * @param user 用户对象
     * @return
     */
    @PutMapping(value = "/updateInfo")
    public BaseResult updateInfo(@RequestBody User user) {
        User oldUser = userService.checkExistsUsername(user.getUsername());
        if (oldUser != null && !Objects.equals(oldUser.getId(), user.getId()))
            return BaseResult.errorMsg("用户名已存在，请更换用户名!");
        return BaseResult.boolResult(userService.updateById(user));
    }

}
