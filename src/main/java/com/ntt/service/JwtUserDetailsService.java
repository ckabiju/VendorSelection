package com.ntt.service;

/**
 * JWTUserDetailsService implements the Spring Security UserDetailsService interface. 
 * It overrides the loadUserByUsername for fetching user details from the database using the username.
 * The Spring Security Authentication Manager calls this method for getting the user 
 * details from the database when authenticating the user details provided by the user.
 * 
 * Password for a user is stored in encrypted format using BCrypt.
 */
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntt.hibernate.Entity.DAOUser;
import com.ntt.hibernate.dao.UserDao;
import com.ntt.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 * Loads the user details from DB based on username and returns the 
	 * details to Spring Security Authentication Manager.
	 * 
	 *  @param username
	 *  @return UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	/**
	 * Saves User details to database if the user does not exist.
	 * @param userdto
	 * @return DAOUser
	 */
	public DAOUser save(UserDTO userdto) {
		DAOUser user = userDao.findByUsername(userdto.getUsername());
		if(user != null) {
			return user;
		}
		DAOUser newUser = new DAOUser();
		newUser.setUsername(userdto.getUsername());
		newUser.setPassword(bcryptEncoder.encode(userdto.getPassword()));
		return userDao.save(newUser);
	}
}
