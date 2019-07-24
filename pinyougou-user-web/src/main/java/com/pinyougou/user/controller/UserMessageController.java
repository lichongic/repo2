package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbAreas;
import com.pinyougou.pojo.TbCities;
import com.pinyougou.pojo.TbProvinces;
import com.pinyougou.pojo.group.UserMessage;
import com.pinyougou.user.service.UserMessageService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class UserMessageController {

    @Reference
    private UserMessageService userMessageService;

    @RequestMapping("/add")
    public Result add(@RequestBody UserMessage userMessage){
        try {
            System.out.println(userMessage);
            userMessageService.add(userMessage);
            return new Result(true,"添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败！");
        }

    }


    @RequestMapping("/findAddressList")
    public List<TbProvinces> findAddressList(){
        List<TbProvinces> addressList = userMessageService.findAddressList();
        return addressList;
    }

    @RequestMapping("/findCities")
    public List<TbCities> findCities(String provinceId){
        try {
            List<TbCities> cities = userMessageService.findCities(provinceId);

            return cities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @RequestMapping("/findAreas")
    public List<TbAreas> findAreas(String cityId){
        try {
            List<TbAreas> areas = userMessageService.findAreas(cityId);
            return areas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findOneUser")
    public UserMessage findOneUser(String loginName){
        try {
            UserMessage oneUser = userMessageService.findOneUser(loginName);
            return oneUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
