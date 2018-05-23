package stockman.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stockman.modele.Provider;
import stockman.modele.Supply;
import stockman.repository.ProviderRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProviderController {
	
	@Autowired
	private ProviderRepository repository;
	
	@GetMapping("/provider")
	public List<Provider> getAllProviders() {
		return repository.findAll();
	}
	
	@GetMapping("/provider/{id}")
	public Optional<Provider> getProviderById(@PathVariable(value = "id") Long providerId) {
			return repository.findById(providerId);
	}
}
