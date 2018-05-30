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

import stockman.model.OrderRequest;
import stockman.repository.OrderRequestRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderRequestController {
	
	@Autowired
	OrderRequestRepository repository;
	
	@GetMapping("/request")
	public List<OrderRequest> getAllRequests() {
		return repository.findAll();
	}
	
	@GetMapping("/request/{id}")
	public Optional<OrderRequest> getRequestById(@PathVariable(value = "id") Long requestId) {
			return repository.findById(requestId);
	}
	
	@PostMapping("/request")
	public OrderRequest createRequest(@Valid @RequestBody OrderRequest orderRequest) {
		return repository.save(orderRequest);
	}
	
	@PutMapping("/request/{id}")
	public OrderRequest updateRequest(
			@PathVariable(value = "id") Long requestId,
			@Valid
			@RequestBody OrderRequest requestResponse) {
		Optional<OrderRequest> request = repository.findById(requestId);
		OrderRequest newRequest = request.get();
		if(request.isPresent()) {
			
			newRequest.setQuantity(requestResponse.getQuantity() );
			newRequest.setDeliveryDate(requestResponse.getDeliveryDate());
			newRequest.setSupply(requestResponse.getSupply());
			newRequest.setUser(requestResponse.getUser());
			return repository.save(newRequest);	
        }
		
        return null;
        
	}
	
	@DeleteMapping("/request/{id}")
	public void deleteRequest(@PathVariable(value = "id") Long requestId) {
		Optional<OrderRequest>  request = repository.findById(requestId);
		if(request.isPresent()) {
			repository.deleteById(requestId);
		}
	}


}
