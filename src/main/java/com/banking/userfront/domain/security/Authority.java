package com.banking.userfront.domain.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by AmitAgarwal on 4/16/19.
 */
public class Authority implements GrantedAuthority{

    private final String authority;

    public Authority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
