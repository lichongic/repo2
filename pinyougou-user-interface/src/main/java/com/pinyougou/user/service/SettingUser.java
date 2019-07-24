package com.pinyougou.user.service;

public interface SettingUser {

    /**
     * 发送验证码
     * @param phone
     */
    public void sendMessage(String phone);


    /**
     * 校验验证码
     * @param phone
     * @param code
     * @return
     */
    public boolean checkSmsCode(String phone,String code);

    /**
     * 修改密码
     * @param username
     * @param password
     */
    public void setPassWord(String username,String password);
}
