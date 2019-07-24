package com.pinyougou.user.service;

import com.pinyougou.pojo.TbAreas;
import com.pinyougou.pojo.TbCities;
import com.pinyougou.pojo.TbProvinces;
import com.pinyougou.pojo.group.UserMessage;
import entity.Result;

import java.util.List;

public interface UserMessageService {
    /**
     *
     * 添加个人信息
     * @return
     */
    public void add(UserMessage userMessage);

    /**
     * 查询省份地址列表
     * @return
     */
    public List<TbProvinces> findAddressList();

    /**
     * 根据省份id查询市级列表
     * @return
     */
    public List<TbCities> findCities(String id);

    /**
     * 根据市级id查询地区列表
     * @param cityId
     * @return
     */
    public List<TbAreas> findAreas(String cityId);

    /**
     * 根据用户登录名查询用户
     * @param username
     * @return
     */
    public UserMessage findOneUser(String username);
}
