package com.amh.zenevent.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Entreprise;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	UserService userService;
	@Autowired
	EntrepriseService entrepriseService;
	
	@Override
	public User loadUserByUsername(String telephone) throws UsernameNotFoundException{
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(telephone);
		com.amh.zenevent.entities.User utilisateur = userService.getUserByPhone(telephone);
		
			if (entreprise != null) {
				User user = createEntreprise(entreprise);
				return user;
				
			}else if (utilisateur != null) {
				User user = createUser(utilisateur);	
				return user;			
			}
			throw new UsernameNotFoundException("Utilisateur non trouvé:"+ telephone);		
	}

	
	private User createEntreprise(Entreprise entreprise) {
		return new User(entreprise.getUsername(), entreprise.getPassword(), createEntrepriseAuthorities(entreprise));
	}
	
	private Collection<GrantedAuthority> createEntrepriseAuthorities(Entreprise entreprise){
		Collection<GrantedAuthority> authorities = new ArrayList<>();			
				authorities.add(new SimpleGrantedAuthority("ADMIN"));		
		return authorities;
	}	
	
	private User createUser(com.amh.zenevent.entities.User utilisateur) {
		return new User(utilisateur.getTelephone(), utilisateur.getPassword(), createUserAuthorities(utilisateur));
	}			
	
	private Collection<GrantedAuthority> createUserAuthorities(com.amh.zenevent.entities.User utilisateur){
	Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("USER"));		
	return authorities;
	}

}
