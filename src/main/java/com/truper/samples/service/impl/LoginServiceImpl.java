package com.truper.samples.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.truper.samples.dao.UserDAO;
import com.truper.samples.service.LoginService;
import com.truper.sinv.vo.UserVO;

/**
 * 
 * @author cgarcias
 * @since 07/06/2017
 */
@Service(value="administradorLoginService")
public class LoginServiceImpl implements UserDetailsService, LoginService {
	
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Carga el UserDetails con el user name y la contraseña es comparada internamente con Spring-Security
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userDAO.getByUser(username);
		return makeGrantedAuthorities(user, false);
	}
	
	/**
	 * Genera el usuario, con los permisos y password para ser comparado por spring security
	 * @param uUser
	 * @param boolToken
	 * @return UserVO
	 */
	private UserVO makeGrantedAuthorities(UserVO uUser, boolean boolToken) {
		Set<GrantedAuthority> sGrantedAuthorities = new HashSet<>();
        sGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + uUser.getRol()));
		uUser.setAuthorities(sGrantedAuthorities);
		uUser.setPassword(uUser.getPassword());
		
		return uUser;
	}

}