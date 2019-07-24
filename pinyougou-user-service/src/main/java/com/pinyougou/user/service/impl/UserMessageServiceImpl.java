package com.pinyougou.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import com.pinyougou.pojo.group.UserMessage;
import com.pinyougou.user.service.UserMessageService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private TbUserMapper tbUserMapper;


    @Autowired
    private TbProvincesMapper tbProvincesMapper;

    @Autowired
    private TbAreasMapper tbAreasMapper;


    @Autowired
    private TbCitiesMapper tbCitiesMapper;

    @Autowired
    private TbAddressMapper tbAddressMapper;

    @Override
    public void add(UserMessage userMessage) {


            TbUser tbUser = userMessage.getTbUser();
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(tbUser.getUsername());
            List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
            if (tbUsers!=null&& tbUsers.size()>0){
                TbUser tbUser1 = tbUsers.get(0);
                Long id = tbUser1.getId();
                tbUser.setId(id);

                tbUser.setId(tbUser1.getId());
                tbUser.setEmail(tbUser1.getEmail());
                tbUser.setPhone(tbUser1.getPhone());
                tbUser.setPassword(tbUser1.getPassword());
                tbUser.setCreated(tbUser1.getCreated());
                tbUser.setUpdated(new Date());
                tbUser.setSourceType(tbUser1.getSourceType());
                tbUser.setAccountBalance(tbUser1.getAccountBalance());
                tbUser.setExperienceValue(tbUser1.getExperienceValue());
                tbUser.setHeadPic(tbUser1.getHeadPic());
                tbUser.setPoints(tbUser1.getPoints());
            }
            tbUserMapper.updateByPrimaryKey(tbUser);


        TbAddress tbAddress = userMessage.getTbAddress();
        TbAddressExample addressExample = new TbAddressExample();
        TbAddressExample.Criteria criteria1 = addressExample.createCriteria();
        criteria1.andUserIdEqualTo(tbUser.getUsername());//判断当前登录人
        criteria1.andIsDefaultEqualTo("3");//是否为所在地
        List<TbAddress> tbAddresses = tbAddressMapper.selectByExample(addressExample);
        if (tbAddresses!=null&&tbAddresses.size()>0){
            TbAddress tbAddress1 = tbAddresses.get(0);
            tbAddress.setId(tbAddress1.getId());
            tbAddress.setUserId(tbUser.getUsername());

            tbAddress.setIsDefault("3");
            System.out.println("1");
        }
        tbAddressMapper.updateByPrimaryKey(tbAddress);


    }

    /**
     * 查询省份地址列表
     * @return
     */
    @Override
    public List<TbProvinces> findAddressList() {
        List<TbProvinces> tbProvinces = tbProvincesMapper.selectByExample(null);
        return tbProvinces;
    }

    @Override
    public List<TbCities> findCities(String id) {
        TbCitiesExample example = new TbCitiesExample();
        TbCitiesExample.Criteria criteria = example.createCriteria();
        criteria.andProvinceidEqualTo(id);
        List<TbCities> tbCities = tbCitiesMapper.selectByExample(example);
        return tbCities;
    }

    @Override
    public List<TbAreas> findAreas(String cityId) {
        TbAreasExample example = new TbAreasExample();
        TbAreasExample.Criteria criteria = example.createCriteria();
        criteria.andCityidEqualTo(cityId);
        List<TbAreas> tbAreas = tbAreasMapper.selectByExample(example);
        return tbAreas;
    }

    @Override
    public UserMessage findOneUser(String username) {
        UserMessage userMessage = new UserMessage();
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (tbUsers!=null&&tbUsers.size()>0){
            userMessage.setTbUser(tbUsers.get(0));
        }
        TbAddressExample addressExample = new TbAddressExample();
        TbAddressExample.Criteria criteria1 = addressExample.createCriteria();
        criteria1.andUserIdEqualTo(username);
        List<TbAddress> tbAddresses = tbAddressMapper.selectByExample(addressExample);
        if (tbAddresses!=null&&tbAddresses.size()>0){
            userMessage.setTbAddress(tbAddresses.get(0));
        }

        return userMessage;
    }
}
