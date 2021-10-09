package com.example.food.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> autoAuthorities;
	
	public UserDetail(Long id, 
					  String username, 
					  String email, 
					  String password,
					  Collection<? extends GrantedAuthority> autoAuthorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.autoAuthorities = autoAuthorities;
	}
	
	public static UserDetail build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
												.map(role -> new SimpleGrantedAuthority(role.getName().name()))
												.collect(Collectors.toList());
		
		return new UserDetail(user.getId(), 
							  user.getUsername(),
							  user.getEmail(), 
							  user.getPassword(), 
							  authorities);
	}					
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autoAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}


	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
