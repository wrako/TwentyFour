package com.portal.web.domain.service;

import com.portal.web.domain.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    public void addUser(Account account) throws CommentException;

    public Account getUser(String game, String name) throws CommentException;

    public void reset() throws CommentException;
}
