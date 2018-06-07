package stockman.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stockman.model.Request;
import stockman.repository.RequestRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RequestController {
	
	@Autowired
	RequestRepository repository;
	
	@GetMapping("/request")
	public List<Request> getAllRequests() {
		return repository.findAll();
	}
	
	@GetMapping("/request/{id}")
	public Optional<Request> getRequestById(@PathVariable(value = "id") Long requestId) {
			return repository.findById(requestId);
	}
	
	@PostMapping("/request")
	public Request createRequest(@Valid @RequestBody Request request) {
		return repository.save(request);
	}
	
	@PutMapping("/request/{id}")
	public Request updateRequest(
			@PathVariable(value = "id") Long requestId,
			@Valid
			@RequestBody Request requestResponse) {
		Optional<Request> request = repository.findById(requestId);
		Request newRequest = request.get();
		if(request.isPresent()) {
			
			newRequest.setQuantity(requestResponse.getQuantity() );
			newRequest.setDeliveryDate(requestResponse.getDeliveryDate());
			newRequest.setStatusList(requestResponse.getStatusList());
			newRequest.setSupply(requestResponse.getSupply());
			newRequest.setUser(requestResponse.getUser());
			return repository.save(newRequest);	
        }
		
        return null;
        
	}
	
	@DeleteMapping("/request/{id}")
	public void deleteRequest(@PathVariable(value = "id") Long requestId) {
		Optional<Request>  request = repository.findById(requestId);
		if(request.isPresent()) {
			repository.deleteById(requestId);
		}
	}


}
