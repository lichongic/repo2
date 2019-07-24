package com.pinyougou.pojo.group;

import com.pinyougou.pojo.TbAddress;
import com.pinyougou.pojo.TbUser;

import java.io.Serializable;

public class UserMessage implements Serializable {
    private TbAddress tbAddress;
    private TbUser tbUser;


    @Override
    public String toString() {
        return "UserMessage{" +
                "tbAddress=" + tbAddress +
                ", tbUser=" + tbUser +
                '}';
    }

    public TbAddress getTbAddress() {
        return tbAddress;
    }

    public void setTbAddress(TbAddress tbAddress) {
        this.tbAddress = tbAddress;
    }

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }
}
