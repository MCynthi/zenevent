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

import com.amh.zenevent.entities.User;
import com.amh.zenevent.jwtFiles.JwtTokenUtil;
import com.amh.zenevent.repository.UserRepository;
import com.amh.zenevent.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping(value = "/users")
	public List<User> listUser(){
		return userService.listUser();
	}
	
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable UUID id){
		User user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user){
		try {
			userService.addUser(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User user){
		
		if (userService.isUserExist(id)) {
			userService.updateUser(id,user);
			return new ResponseEntity<>(HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable UUID id) {
		User user = userRepository.getUserById(id);
		user.setDeleted(true);
		userRepository.save(user);
	}
	
	@GetMapping("/users/count")
	public long countUser() {
		return userService.countUser();
	}
	
	@GetMapping("user/entreprises/{idEntreprise}/login/{username}/{password}")
	public ResponseEntity<User> loginUserByEntreprise(@PathVariable long idEntreprise, @PathVariable String username, @PathVariable String password) {
		User user  = userService.loginUserByEntreprise(idEntreprise, username, password);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("user/login/{username}/{password}")
	public ResponseEntity<User> loginUser(@PathVariable String username, @PathVariable String password) {
		User user  = userService.loginUser(username, password);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/user/authenticate", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> loginAgent(@RequestBody User user) throws Exception {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		authenticateUser(user);
		
		User currentUser = userService.getUserByPhone(user.getTelephone());

		if (currentUser != null) {
			
			final UserDetails userDetails = userDetailsService.loadUserByUsername(currentUser.getTelephone());
			final String token = jwtTokenUtil.generateToken(userDetails);

			userInfo.put("token", token);
			userInfo.put("user", currentUser);

			System.out.println((new Date()).toString() + "  ************************  connection effectuée");
			return ResponseEntity.ok(userInfo);

		} else {
			System.out
					.print((new Date()).toString() + "  ************************  connection utilisateur  échouée (désactivé)"
							+ user.getTelephone() + " " + user.getPassword());
			return new ResponseEntity<>("Contacter votre administrateur", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void authenticateUser(User user) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getTelephone(), user.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
