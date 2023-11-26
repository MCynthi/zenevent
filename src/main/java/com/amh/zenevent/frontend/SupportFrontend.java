package com.amh.zenevent.frontend;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.Support;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.SupportService;

@Controller
public class SupportFrontend {
	@Autowired
	SupportService supportService;
	@Autowired
	EntrepriseService entrepriseService;

	@RequestMapping(value = "/support", method = RequestMethod.GET)
	public String supportClient(Model model, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);	
		
		List<Support> listSupportFerme = supportService.listSupportFermeByEntreprise(entreprise.getIdEntreprise());
		List<Support> listSupportOuvert = supportService.listSupportOuvertByEntreprise(entreprise.getIdEntreprise());
		
		long countSupportOuvert = supportService.totalSupportOuvertByEntreprise(entreprise.getIdEntreprise());
		long countSupportFerme = supportService.totalSupportFermeByEntreprise(entreprise.getIdEntreprise());

		model.addAttribute("support", new Support());

		model.addAttribute("listSupportFerme", listSupportFerme);
		model.addAttribute("listSupportOuvert", listSupportOuvert);
		
		model.addAttribute("supportOuvert", countSupportOuvert);
		model.addAttribute("supportFerme", countSupportFerme);
		
		return "support/support";
	}

	@RequestMapping(value = "/support/saveSupport", method = RequestMethod.POST)
	public String saveSupport(Support support, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		support.setEntreprise(entreprise);
		supportService.saveSupport(support);
		return "redirect:/support";
	}

	@RequestMapping(value = "/support/edit/{id}")
	public ModelAndView editUser(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("support/editSupport");
		Support support = supportService.getSupportById(id);
		mav.addObject("support", support);
		return mav;
	}

	@RequestMapping(value = "/support/update", method = RequestMethod.POST)
	public String updateUser(Support support) {
		UUID id = support.getId();
		supportService.updateSupport(id, support);
		return "redirect:/support";
	}

	@RequestMapping("/support/delete/{id}")
	public String deleteSupport(@PathVariable("id") UUID id) {
		Support support = supportService.getSupportById(id);
		support.setDeleted(true);
		supportService.saveSupport(support);
		return "redirect:/support";
	}
	
	@RequestMapping(value = "/support/view/{id}")
	public ModelAndView viewSupport(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("support/viewSupport");	
		Support support = supportService.getSupportById(id);		
		mav.addObject("support", support);
		return mav;
	}
	
	@RequestMapping(value = "gestionnaire/support", method = RequestMethod.GET)
	public String listSupport(Model model) {		
		List<Support> listSupportOuvert = supportService.listSupportOuvert();
		model.addAttribute("support", listSupportOuvert);
		return "support/listeSupport";
	}
	
	@RequestMapping(value = "gestionnaire/support/reponse/{id}")
	public ModelAndView answerSupport(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("support/answerSupport");
		Support support = supportService.getSupportById(id);
		mav.addObject("support", support);
		return mav;
	}

	@RequestMapping(value = "gestionnaire/support/answer/{id}")
	public ModelAndView sentAnswer(@PathVariable(name = "id") UUID id){
		ModelAndView mav = new ModelAndView("support/answerSupport");
		Support support = supportService.getSupportById(id);
		mav.addObject("support", support);
		return mav;
	}
	
	
	@RequestMapping(value = "gestionnaire/support/send", method = RequestMethod.POST)
	public String sendAnswer(Support support) {
		UUID id = support.getId();
		supportService.sendAnswerSupport(id, support);
		return "redirect:/gestionnaire/support";
	}
}
