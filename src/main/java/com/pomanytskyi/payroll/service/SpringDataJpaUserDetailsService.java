package com.pomanytskyi.payroll.service;

import com.pomanytskyi.payroll.domain.Manager;
import com.pomanytskyi.payroll.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

    private final ManagerRepository repository;

    @Autowired
    public SpringDataJpaUserDetailsService(ManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = this.repository.findByName(username);
        return new User(manager.getName(), manager.getPassword(), AuthorityUtils.createAuthorityList(manager.getRoles()));
    }
}
