package com.shop.repositories_dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.models.User;
import com.shop.models.UserSecurity;


@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private IUserDAO iUserDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = iUserDAO.findByNick(username).get(0);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found", null);
		}
		
		return UserSecurity.build(user);
	}
}
