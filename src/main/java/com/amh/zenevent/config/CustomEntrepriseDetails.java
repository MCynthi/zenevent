package com.amh.zenevent.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.amh.zenevent.entities.Entreprise;

public class CustomEntrepriseDetails  extends User implements UserDetails{

	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	List<GrantedAuthority> authorities;
	
	public CustomEntrepriseDetails(Entreprise entreprise, List<GrantedAuthority> authorities) {
		super(entreprise.getUsername(),entreprise.getPassword(), authorities);
		this.authorities = authorities;
		this.entreprise = entreprise;
	}

//	@Override
//	public Collection<GrantedAuthority> getAuthorities() {
//		return super.getAuthorities();
//	}

	@Override
	public String getPassword() {
		return entreprise.getPassword();
	}

	@Override
	public String getUsername() {
		return entreprise.getUsername();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}

	@Override
	public boolean isAccountNonExpired() {
		return super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return super.isCredentialsNonExpired();
	}
}
