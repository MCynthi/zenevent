package com.amh.zenevent.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.jwtFiles.JwtTokenUtil;
import com.amh.zenevent.repository.EntrepriseRepository;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.service.EntrepriseService;

@RestController
@RequestMapping("/api")
public class EntrepriseController {
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping(value = "/entreprises")
	public List<Entreprise> listEntreprise(){
		return entrepriseService.listEntreprise();
	}
	
	@GetMapping(value = "/entreprises/{id}")
	public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable UUID id){
		Entreprise entreprise = entrepriseService.getEntrepriseById(id);
		if (entreprise != null) {
			return new ResponseEntity<Entreprise>(entreprise,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PostMapping("/entreprises")
	public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise){
		try {
			entreprise.setIsDeleted(false);
			entrepriseService.addEntreprise(entreprise);
			return new ResponseEntity<Entreprise>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Entreprise>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/entreprises/{id}")
	public ResponseEntity<?> updateEntreprise(@PathVariable long id, @RequestBody Entreprise entreprise){
		
		if (entrepriseService.isEntrepriseExist(id)) {
			entrepriseService.updateEntreprise(id,entreprise);
			return new ResponseEntity<>(HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/entreprises/{id}")
	public void deleteEntreprise(@PathVariable UUID id) {
		Entreprise entreprise = entrepriseRepository.getEntrepriseById(id);
		entreprise.setIsDeleted(true);
		entrepriseRepository.save(entreprise);
	}
	
	@GetMapping("/entreprises/count")
	public long countEntreprise() {
		return entrepriseService.countEntreprise();
	}
	
	@GetMapping("/entreprises/login/{username}/{password}")
	public ResponseEntity<Entreprise> loginEntreprise(@PathVariable String username, @PathVariable String password) {
		Entreprise entreprise = entrepriseService.loginEntreprise(username, password);
		if (entreprise != null) {
			return new ResponseEntity<Entreprise>(entreprise, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/entreprise/authenticate", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> loginAgent(@RequestBody Entreprise entreprise) throws Exception {

		Map<String, Object> entrepriseInfo = new HashMap<String, Object>();

		authenticateEntreprise(entreprise);
		
		Entreprise currentEntreprise = entrepriseService.getEntrepriseByUsername(entreprise.getUsername());

			if (currentEntreprise != null) {

				final UserDetails userDetails = userDetailsService.loadUserByUsername(currentEntreprise.getUsername());
				final String token = jwtTokenUtil.generateToken(userDetails);

				entrepriseInfo.put("token", token);
				entrepriseInfo.put("entreprises", countEntreprise());

				System.out.println((new Date()).toString() + "  ************************  connection effectuée");
				return ResponseEntity.ok(entrepriseInfo);

			} else {
				System.out.print(
						(new Date()).toString() + "  ************************  connection  échouée"
								+ entreprise.getUsername() + " " + entreprise.getPassword());
				return new ResponseEntity<>("contacter votre administrateur", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}

	private void authenticateEntreprise(Entreprise entreprise) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(entreprise.getUsername(), entreprise.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	

}
