package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.user.service.SettingUser;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Reference
    private SettingUser settingUser;

    @RequestMapping("/sendMessage")
    public Result sendMessage(String phone){

        try {
            System.out.println(phone);
            settingUser.sendMessage(phone);
            return new Result(true,"发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"发送失败！");
        }

    }

    /**
     * 增加
	 * @param
	 * @return
             */
    @RequestMapping("/checkSmsCode")
    public Result checkSmsCode( String phone , String smscode){

        //校验验证码是否正确
        boolean checkSmsCode = settingUser.checkSmsCode(phone, smscode);
        if(!checkSmsCode){
            return new Result(false, "验证码不正确！");
        }
        return new Result(true,"验证成功");
    }


    @RequestMapping("/setPassWord")
    public Result setPassWord(String username,String password){
        try {
            ArrayList list = new ArrayList();
            
            System.out.println(username+"    "+password);
            settingUser.setPassWord(username,password);
            return new Result(true,"设置成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"密码设置失败");
        }
    }
}
