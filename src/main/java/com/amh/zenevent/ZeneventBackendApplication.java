package com.amh.zenevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amh.zenevent.entities.Category;
import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.Historique;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.Support;
import com.amh.zenevent.entities.User;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.entities.Visitor.Impression;
import com.amh.zenevent.entities.Visitor.Sexe;
import com.amh.zenevent.repository.CategoryRepository;
import com.amh.zenevent.repository.EntrepriseEventRepository;
import com.amh.zenevent.repository.EntrepriseRepository;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.repository.HistoriqueRepository;
import com.amh.zenevent.repository.ServiceEntrepriseRepository;
import com.amh.zenevent.repository.SupportRepository;
import com.amh.zenevent.repository.UserRepository;
import com.amh.zenevent.repository.VisitorRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@SpringBootApplication
@EnableSwagger2
public class ZeneventBackendApplication extends SpringBootServletInitializer
		implements CommandLineRunner, WebMvcConfigurer {
	@Autowired
	EventRepository eventRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	ServiceEntrepriseRepository serviceRepository;
	@Autowired
	HistoriqueRepository historiqueRepository;
	@Autowired
	EntrepriseEventRepository entrepriseEventRepository;
	@Autowired
	SupportRepository supportRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZeneventBackendApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	@Override
	public void run(String... args) throws Exception {
		Event event1 = eventRepository.save(new Event("SAGO","Cameroun", "Yaounde", "Bastos", "golf", "20-06-2022", "18-07-2022", "foire organisé dans le but de promouvoir les entreprises"));
		Event event2 = eventRepository.save(new Event("FOMARIC","Cameroun", "Douala", "Bepanda", "stade camtel", "12-06-2022", "10-07-2022", "foire organisé dans le but de promouvoir les entreprises locales"));		
		Event event3 = eventRepository.save(new Event("FOIRE De l'eau","Senegal", "Dakar", "dakar", "salle de fete", "12-06-2022", "10-07-2022", "foire organisé dans le but de promouvoir les entreprises africaines"));
		
		Category categorie1 = categoryRepository.save(new Category("Technologie"));
		Category categorie2 = categoryRepository.save(new Category("Sante"));
		Category categorie3 = categoryRepository.save(new Category("Environnement"));
		Category categorie4 = categoryRepository.save(new Category("Industrie"));
		
		Entreprise entreprise1 = entrepriseRepository.save(new Entreprise("AMH Consulting", "contact@amhconsulting-group.com", "69999999", "67000000", "amh", "$2a$12$aDlrjRIUAVxGrdVVuekzhu6ti/H2mzkNWboSZYKvGKPa2i7NnKSTC", null));
		Entreprise entreprise2 = entrepriseRepository.save(new Entreprise("MTN Cameroun", "contact@mtn.com", "69000000", "671111111", "mtn", "$2a$12$aDlrjRIUAVxGrdVVuekzhu6ti/H2mzkNWboSZYKvGKPa2i7NnKSTC", null));
		Entreprise entreprise3 = entrepriseRepository.save(new Entreprise("ORANGE Cameroun", "contact@orange.com", "692222222", "67666666", "orange", "$2a$12$aDlrjRIUAVxGrdVVuekzhu6ti/H2mzkNWboSZYKvGKPa2i7NnKSTC", null));
		
		User amhUser1 = userRepository.save(new User(entreprise1, "Cynthia", "690581086", "pass", "cmingo@amhconsulting-group.com", "690581086"));
		User amhUser2 = userRepository.save(new User(entreprise1, "Herve", "651041395", "pass", "hfoudjo@amhconsulting-group.com", "651041395"));
		User amhUser3 = userRepository.save(new User(entreprise1, "Francky","655604155", "pass", "fshiti@amhconsulting-group.com", "655604155"));
		
		User mtnUser1 = userRepository.save(new User(entreprise2,"Derrick", "690710856", "pass", "derrick@mtn.com", "690710856"));
		User mtnUser2 = userRepository.save(new User(entreprise2,"Amber", "645222222", "pass", "amber@mtn.com", "645222222"));
		
		User orangeUser1 = userRepository.save(new User(entreprise3,"Jordan", "67158888", "passe", "jordan@orange.com", "67158888"));
		User orangeUser2 = userRepository.save(new User(entreprise3, "Anisse","679333333", "passe", "anisse@orange.com", "679333333"));
		
		ServiceEntreprise service1 = serviceRepository.save(new ServiceEntreprise(categorie1, "Reseau et systeme",entreprise2,false));
		ServiceEntreprise service2 = serviceRepository.save(new ServiceEntreprise(categorie1, "DEvOps", entreprise1, false));
		ServiceEntreprise service3 = serviceRepository.save(new ServiceEntreprise(categorie4, "Construction",entreprise3,false));
		ServiceEntreprise service4 = serviceRepository.save(new ServiceEntreprise(categorie2, "Cardiologie",entreprise3,false));
		ServiceEntreprise service5 = serviceRepository.save(new ServiceEntreprise(categorie3, "QHSE",entreprise1,false));
		
		Visitor visitor1 = visitorRepository.save(new Visitor("visiteur 1", "visiteur1@gmail.com", Sexe.MASCULIN, Impression.TRES_SATISFAIT, "691223344", null, "Architect", "Batiment", amhUser1, "Ceci est un commentaire","18-25", service4));
		Visitor visitor2 = visitorRepository.save(new Visitor("visiteur 2", "visiteur2@gmail.com", Sexe.FEMININ, Impression.SATISFAIT, "691223344", null, "Sage-femme", "Medecine", amhUser1,"Ceci est un commentaire", "26-34", service2));
		Visitor visitor6 = visitorRepository.save(new Visitor("visiteur 6", "visiteur2@gmail.com", Sexe.FEMININ, Impression.SATISFAIT, "691223344", null, "logisticiennne", "Medecine", amhUser2,"Ceci est un commentaire", "26-34", service2));
		
		Visitor visitor3 = visitorRepository.save(new Visitor("visiteur 3", "visiteur3@gmail.com", Sexe.FEMININ, Impression.SATISFAIT, "691144559", null, "Enseignante", "Enseignement", orangeUser1,"Ceci est un commentaire","26-34", service3));
		Visitor visitor4 = visitorRepository.save(new Visitor("visiteur 4", "visiteur4@gmail.com", Sexe.FEMININ, Impression.SATISFAIT, "678559966", null, "Infographe", "Informatique", mtnUser2,"Ceci est un commentaire", "35-45", service2));
		
		Visitor visitor5 =visitorRepository.save(new Visitor("visiteur 5", "visiteur5@gmail.com", Sexe.MASCULIN, Impression.SATISFAIT, "678521133", null, "agriculteur", "agriculture", mtnUser1,"Ceci est un commentaire", "46-plus",  service1));

		Historique historique1 = historiqueRepository.save(new Historique(mtnUser1, service2, event1, visitor1));
		Historique historique2 = historiqueRepository.save(new Historique(mtnUser1, service3,event1,visitor2));
		Historique historique3 = historiqueRepository.save(new Historique(orangeUser1, service1,event2,visitor3));
		Historique historique4 = historiqueRepository.save(new Historique(amhUser1, service4,event1,visitor4));
		Historique historique5 = historiqueRepository.save(new Historique(amhUser2, service5,event2,visitor5));
		Historique historique6 = historiqueRepository.save(new Historique(amhUser1, service4,event1,visitor6));
		
		Support support = supportRepository.save(new Support("first support", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain..", entreprise1));

				
		
	}

}
