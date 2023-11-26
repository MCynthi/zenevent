package com.amh.zenevent.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.User;
import com.amh.zenevent.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public List<User> listUser() {
		return userRepository.listUser();
	}

	public User getUserById(UUID id) {
		return userRepository.getUserById(id);
	}

	public void addUser(User user) {
		if (user.getTelephone() != null) {
			user.setUsername(user.getTelephone());
		}
		// user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public boolean isUserExist(UUID id) {
		return userRepository.existsById(id);
	}

	public void updateUser(UUID id, User user) {
		User checkUser = userRepository.getUserById(id);
		if (checkUser != null) {
			checkUser.setEmail(user.getEmail());
			checkUser.setEntreprise(user.getEntreprise());
			checkUser.setNomUser(user.getNomUser());
			checkUser.setEntreprise(user.getEntreprise());
			checkUser.setPassword(user.getPassword());
			// checkUser.setPassword(passwordEncoder.encode(user.getPassword()));
			checkUser.setTelephone(user.getTelephone());
			checkUser.setUsername(user.getUsername());
			checkUser.setDeleted(user.isDeleted());
		}
		userRepository.save(checkUser);

	}

	public long countUser() {
		return userRepository.countUser();
	}

	public User loginUserByEntreprise(long idEntreprise, String username, String password) {
		return userRepository.loginUserByEntreprise(idEntreprise, username, password);
	}

	public User loginUser(String username, String password) {
		return userRepository.loginUser(username, password);
	}

	public User getUserByPhone(String telephone) {
		return userRepository.getUserByPhone(telephone);
	}

	public List<User> listUserByEvent(UUID idEvent) {
		return userRepository.listUserByEvent(idEvent);
	}

	public long countUserByEntreprise(UUID idEntreprise) {
		return userRepository.countUserByEntreprise(idEntreprise);
	}

	public List<User> listUserByEntreprise(UUID idEntreprise) {
		return userRepository.listUserByEntreprise(idEntreprise);
	}

	public List<User> listUserActifByEntreprise(UUID idEntreprise) {
		return userRepository.listUserActifByEntreprise(idEntreprise);
	}

	public List<User> listUserInactifByEntreprise(UUID idEntreprise) {
		return userRepository.listUserInactifByEntreprise(idEntreprise);
	}

	public long countUserActifByEntreprise(UUID idEntreprise) {
		return userRepository.countUserActifByEntreprise(idEntreprise);
	}

	public long countUserInactifByEntreprise(UUID idEntreprise) {
		return userRepository.countUserInactifByEntreprise(idEntreprise);
	}
}
