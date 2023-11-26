package com.amh.zenevent.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.User;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.pojo.HistoriquePojo;
import com.amh.zenevent.repository.UserRepository;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.UserService;
import com.amh.zenevent.service.VisitorService;

@Controller
public class UserFrontend {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventService eventService;
	@Autowired
	VisitorService 	visitorService;
	@Autowired
	EntrepriseService entrepriSeService;
	
	@RequestMapping("/user")
	public String listUser(Model model, Authentication authentication, 
			@RequestParam(name = "status", defaultValue = "") String mc){
		String nom = authentication.getName();
		Entreprise entreprise = entrepriSeService.getEntrepriseByUsername(nom);	
		List<User> listUser = new ArrayList<User>();
		
		if (mc.equalsIgnoreCase("ACTIF")) {
			listUser = userService.listUserActifByEntreprise(entreprise.getIdEntreprise());
		}else if (mc.equalsIgnoreCase("INACTIF")){
			listUser = userService.listUserInactifByEntreprise(entreprise.getIdEntreprise());
		}else {
			listUser = userService.listUserByEntreprise(entreprise.getIdEntreprise());
		}
		
		model.addAttribute("pStatus", mc);
		model.addAttribute("user", listUser);
		return "user/listUser.html";
	}
	
	@RequestMapping(value="/user/add", method = RequestMethod.GET)
	public String addUser(Model model, Authentication authentication){
		model.addAttribute("user",new User());
		return "user/addUser.html";
	}
	
	@RequestMapping(value="/user/saveUser", method = RequestMethod.POST)
	public String saveEvent(User user, Authentication authentication){
		String nom = authentication.getName();
		Entreprise entreprise = entrepriSeService.getEntrepriseByUsername(nom);
		user.setEntreprise(entreprise);
		userService.addUser(user);
		return "redirect:/user";
	}
	
	@PostMapping("/visitors")
	public ResponseEntity<Visitor> addVisitor(@RequestBody HistoriquePojo historique) {
		Visitor visitor = visitorService.addVisitorFromHistoriquePojo(historique);
		return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/edit/{id}")
	public ModelAndView editUser(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("user/updateUser");
		User user = userService.getUserById(id);
		UUID idEntreprise = user.getEntreprise().getIdEntreprise();
		List<Event> listEvents = eventService.listEventByEntreprise(idEntreprise);
		mav.addObject("user", user);
		mav.addObject("event", listEvents);
		return mav;
	}
	
	@RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
	public String updateUser(User user) {
		UUID id = user.getIdUser();
		userService.updateUser(id, user);
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/user/view/{id}")
	public ModelAndView viewUser(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("user/viewUser");		
		User user = userService.getUserById(id);		
		List<Visitor> visitors = visitorService.listVisitorByUser(user.getIdUser());
		
		mav.addObject("user", user);
		mav.addObject("visitors", visitors);
		return mav;
	}

	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") UUID id) {
		User user = userRepository.getUserById(id);
		user.setDeleted(true);
		userRepository.save(user);
		return "redirect:/user";
	}
	
	@RequestMapping("/user/activer/{id}")
	public String activer(@PathVariable("id") UUID id, Model model) {
		User user = userRepository.getUserById(id);		
		user.setActif(true);		
		userRepository.save(user);
		return "redirect:/user";
	}
	
	@RequestMapping("/user/desactiver/{id}")
	public String desactiver(@PathVariable("id") UUID id, Model model) {
		User user = userRepository.getUserById(id);		
		user.setActif(false);		
		userRepository.save(user);
		return "redirect:/user";
	}
	

}
