package com.amh.zenevent.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.repository.ServiceEntrepriseRepository;

@Service
public class ServiceEntrepriseService {
	@Autowired
	ServiceEntrepriseRepository serviceEntrepriseRepository;

	public List<ServiceEntreprise> listService() {
		return serviceEntrepriseRepository.listService();
	}

	public ServiceEntreprise getServiceById(UUID id) {
		return serviceEntrepriseRepository.getServiceById(id);
	}

	public void addservice(ServiceEntreprise service) {
		serviceEntrepriseRepository.save(service);
	}

	public boolean isServiceExist(UUID id) {
		return serviceEntrepriseRepository.existsById(id);
	}

	public void updateservice(UUID id, ServiceEntreprise service) {
		ServiceEntreprise checkService = serviceEntrepriseRepository.getServiceById(id);
		if (checkService != null) {
			checkService.setNomService(service.getNomService());
			checkService.setCategory(service.getCategory());
			checkService.setEntreprise(service.getEntreprise());
			checkService.setDeleted(service.isDeleted());
		}
		serviceEntrepriseRepository.save(checkService);
	}

	public long countService() {
		return serviceEntrepriseRepository.countService();
	}

	public List<ServiceEntreprise> listServiceByCategory(long idCategory) {
		return serviceEntrepriseRepository.listServiceByCategory(idCategory);
	}

	public List<ServiceEntreprise> listServiceOfEntrepriseByEvent(long idEvent, long idEntreprise) {
		return serviceEntrepriseRepository.listServiceOfEntrepriseByEvent(idEvent, idEntreprise);
	}

	public List<ServiceEntreprise> listServiceByCategoryAndEvent(long idCategory, long idEvent) {
		return serviceEntrepriseRepository.listServiceByCategoryAndEvent(idCategory, idEvent);
	}

	public List<ServiceEntreprise> listServiceByVisitor(UUID idVisitor) {
		return serviceEntrepriseRepository.listServiceByVisitor(idVisitor);
	}

	public List<ServiceEntreprise> listServiceOfVisitorByEvent(UUID idEvent, UUID idVisitor) {
		return serviceEntrepriseRepository.listServiceOfVisitorByEvent(idEvent, idVisitor);
	}

	public long countServiceByEntreprise(UUID idEntreprise) {
		return serviceEntrepriseRepository.countServiceByEntreprise(idEntreprise);
	}

	public List<ServiceEntreprise> listServiceByEntreprise(UUID idEntreprise) {
		return serviceEntrepriseRepository.listServiceByEntreprise(idEntreprise);
	}

	public List<ServiceEntreprise> listDistinctServiceById(UUID idEntreprise) {
		return serviceEntrepriseRepository.listDistinctServiceById(idEntreprise);
	}


}
