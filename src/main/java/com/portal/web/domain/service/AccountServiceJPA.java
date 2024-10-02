package com.portal.web.domain.service;

import com.portal.web.domain.config.AccountDetails;
import com.portal.web.domain.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
//import sk.tuke.gamestudio.config.SecurityConfig;


@Transactional
public class AccountServiceJPA implements AccountService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void addUser(Account account) throws CommentException {
        account.setPass(passwordEncoder.encode(account.getPass()));
        entityManager.persist(account);

    }

    @Override
    public Account getUser(String game, String name) throws CommentException {
        return null;
    }

    @Override
    public void reset() throws CommentException {

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = (Account) entityManager.createNamedQuery("Account.getByName")
                    .setParameter("name", username)
                    .getSingleResult();
            return new AccountDetails(account);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


}
