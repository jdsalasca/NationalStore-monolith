package com.shop.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurity  implements UserDetails{
	

	@Size(min = 3, max = 200, message 
    	      = "Default")
	private String name;
    @Size(min = 3, max = 200, message 
    	      = "Default")
	private String nick;
    @Email
	private String email;
    private City city;
    @Size(min = 3, max = 200, message 
  	      = "Default")
	private String identityDocument;
    @Size(min = 5, max = 200, message 
  	      = "Default")
	private String password;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    public static UserSecurity build(User user){
        List<GrantedAuthority> authorities =
        		
        		user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                )).collect(Collectors.toList());
        return new UserSecurity(user.getName(),user.getNick(),user.getEmail(),user.getCity(), user.getIdentityDocument(), user.getPassword(), authorities);
    }

	public UserSecurity(@Size(min = 3, max = 200, message = "Default") String name,
			@Size(min = 3, max = 200, message = "Default") String nick, @Email String email,
			City city,
			@Size(min = 3, max = 200, message = "Default") String identityDocument,
			@Size(min = 5, max = 200, message = "Default") String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.nick = nick;
		this.email = email;
		this.city= city;
		this.identityDocument = identityDocument;
		this.password = password;
		this.authorities = authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return nick;
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
