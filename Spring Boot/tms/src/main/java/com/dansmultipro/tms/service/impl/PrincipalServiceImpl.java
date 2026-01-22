package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.pojo.AuthorizationPoJo;
import com.dansmultipro.tms.service.PrincipalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalServiceImpl implements PrincipalService {

    @Override
    public AuthorizationPoJo getPrincipal() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new UsernameNotFoundException("Invalid login");
        }

        return (AuthorizationPoJo) auth.getPrincipal();
    }

}
