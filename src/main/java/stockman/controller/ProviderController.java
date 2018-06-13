package stockman.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stockman.model.Provider;
import stockman.model.Request;
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
	
	
	@GetMapping("/provider/notEmpty")
	public List<Provider> getNotEmptyProviders() {
		return repository.findNotEmptyProviders();
	}
	
	@GetMapping("/provider/{id}")
	public Optional<Provider> getProviderById(@PathVariable(value = "id") Long providerId) {
			return repository.findById(providerId);
	}
	
	@PostMapping("/provider")
	public Provider createProvider(@Valid @RequestBody Provider provider) {
		return repository.save(provider);
	}
	
	@PutMapping("/provider/{id}")
	public Provider updateProvider(
			@PathVariable(value = "id") Long providerId,
			@Valid
			@RequestBody Provider providerResponse) {
		Optional<Provider> provider = repository.findById(providerId);
		Provider newProvider = provider.get();
		if(provider.isPresent()) {
			
			newProvider.setName (providerResponse.getName ());
			newProvider.setSiret(providerResponse.getSiret());
			newProvider.setAddressInfo(providerResponse.getAddressInfo());
//			newProvider.setSupplyList(providerResponse.getSupplyList());
			return repository.save(newProvider);	
        }
		
        return null;
        
	}
}
