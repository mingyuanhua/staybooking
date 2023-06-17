package com.hmy.staybooking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

// 这里不需要Builder因为数据不用从前端发送过来，因为后端Controller就知道
@Entity
@Table(name = "authority")
public class Authority implements Serializable {
    // serialVersionUID是个随机数，每个类是一样的
    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String authority;

    public Authority() {}

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public Authority setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public Authority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}

