package com.osese.Yarpp.RestoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RestoUserService implements UserDetailsService {

	private final RestoUserRepo repository;

	@Autowired
	public RestoUserService(RestoUserRepo repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		RestoUser restoUser = this.repository.findByUsername(name);
		
		return new User(restoUser.getUsername(), restoUser.getPassword(),
				AuthorityUtils.createAuthorityList(restoUser.getRoles()));
	}

}