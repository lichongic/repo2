package com.pinyougou.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.TbUserMapper;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.pojo.TbUserExample;
import com.pinyougou.user.service.SettingUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettingUserImpl implements SettingUser {

    @Autowired
    private TbUserMapper tbUserMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination smsDestination;

    @Value("${template_code}")
    private String template_code;

    @Value("${sign_name}")
    private String sign_name;

    @Override
    public void sendMessage(final String phone) {
        //1.生成一个6位随机数（验证码）
        final String smscode=  (long)(Math.random()*1000000)+"";
        System.out.println("验证码："+smscode);

        //2.将验证码放入redis
        redisTemplate.boundHashOps("smscode").put(phone, smscode);
        //3.将短信内容发送给activeMQ

        /*jmsTemplate.send(smsDestination, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("mobile", phone);//手机号
                message.setString("template_code", template_code);//验证码
                message.setString("sign_name", sign_name);//签名
                Map map=new HashMap();
                map.put("number", smscode);
                message.setString("param", JSON.toJSONString(map));
                return message;
            }
        });*/


    }

    @Override
    public boolean checkSmsCode(String phone, String code) {

        String systemcode= (String) redisTemplate.boundHashOps("smscode").get(phone);
        if(systemcode==null){
            return false;
        }
        if(!systemcode.equals(code)){
            return false;
        }

        return true;
    }

    @Override
    public void setPassWord(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if (tbUsers!=null&&tbUsers.size()>0){
            TbUser tbUser = tbUsers.get(0);
            tbUser.setPassword(DigestUtils.md5Hex(password));
            tbUserMapper.updateByPrimaryKey(tbUser);
        }
    }
}
