package hh.sof003.bikestore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof003.bikestore.domain.Account;
import hh.sof003.bikestore.domain.AccountRepository;



@Service
public class AccountDetailServiceImpl implements UserDetailsService{
    private final AccountRepository repository;

    @Autowired
    public AccountDetailServiceImpl(AccountRepository accountRepository) {
        this.repository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	Account currentAccount = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentAccount.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(currentAccount.getRole()));
        return user;
    }   
}
