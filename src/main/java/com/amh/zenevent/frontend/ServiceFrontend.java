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

import com.amh.zenevent.entities.Category;
import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.repository.HistoriqueRepository;
import com.amh.zenevent.repository.ServiceEntrepriseRepository;
import com.amh.zenevent.service.CategoryService;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.HistoriqueService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.VisitorService;

@Controller
public class ServiceFrontend {
	@Autowired
	ServiceEntrepriseService serviceEntrepriseService;
	@Autowired
	ServiceEntrepriseRepository serviceEntrepriseRepository;
	@Autowired
	CategoryService categoryService;
	@Autowired
	VisitorService visitorService;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	HistoriqueRepository historiqueRepository;
	@Autowired
	EntrepriseService entrepriseService;

	@RequestMapping("/service")
	public String listService(Model model, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		
		List<ServiceEntreprise> listService = serviceEntrepriseService.listServiceByEntreprise(entreprise.getIdEntreprise());
		model.addAttribute("service", listService);
		return "service/listService.html";
	}
	
	@RequestMapping(value="/service/add", method = RequestMethod.GET)
	public String addService(Model model){
		List<Category> categories = categoryService.listCategory();
		model.addAttribute("categories",categories);
		model.addAttribute("service",new ServiceEntreprise());
		return "service/addService.html";
	}
	
	@RequestMapping(value="/service/saveService", method = RequestMethod.POST)
	public String saveService(ServiceEntreprise service, Authentication authentication){
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		service.setEntreprise(entreprise);
		serviceEntrepriseService.addservice(service);
		return "redirect:/service";
	}
	
	
	@RequestMapping(value = "/service/edit/{id}")
	public ModelAndView editService(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("service/updateService");
		List<Category> categories = categoryService.listCategory();
		ServiceEntreprise service = serviceEntrepriseService.getServiceById(id);
		mav.addObject("categories", categories);
		mav.addObject("service", service);
		return mav;
	}
	
	@RequestMapping(value="/service/updateService", method = RequestMethod.POST)
	public String updateService(ServiceEntreprise service){
		UUID id = service.getIdService();
		serviceEntrepriseService.updateservice(id, service);
		return "redirect:/service";
	}
	
	@RequestMapping(value="/service/delete/{id}")
	public String deleteservice(@PathVariable("id") UUID id) {
		ServiceEntreprise service = serviceEntrepriseService.getServiceById(id);
		service.setDeleted(true);
		serviceEntrepriseRepository.save(service);
		return "redirect:/service";
	}

	@RequestMapping(value = "/service/view/{id}")
	public ModelAndView viewEvent(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("service/viewService");
		ServiceEntreprise service = serviceEntrepriseService.getServiceById(id);

		List<Visitor> listVisitor = visitorService.listVisitorsByService(id);
		mav.addObject("service", service);
		mav.addObject("visitor", listVisitor);
		
		return mav;
	}

}
