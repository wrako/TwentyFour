package com.portal.web.domain.config;

import com.portal.web.domain.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AccountDetails implements UserDetails {
    Account account;
    public AccountDetails(Account account) {
        this.account = account;
    }

    public Account getAccount(){
        return this.account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // now user havent roles
    }

    @Override
    public String getPassword() {
        return account.getPass();
    }

    @Override
    public String getUsername() {
        return account.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;//  is account not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // if account not banned
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // if account pass not expired
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
