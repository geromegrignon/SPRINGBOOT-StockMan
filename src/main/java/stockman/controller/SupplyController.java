package stockman.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import stockman.model.Supply;
import stockman.repository.SupplyRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SupplyController {
	
	@Autowired
	private SupplyRepository repository;
	
	@GetMapping("/supply")
	public List<Supply> getAllSupplies() {
		return repository.findAll();
	}
	
	@GetMapping("/supply/alert")
	public List<Supply> getOnAlertSupplies() {
		return repository.findOnAlertSupplies();
	}
	
	@GetMapping("/supply/{id}")
	public Optional<Supply> getSupplyById(@PathVariable(value = "id") Long supplyId) {
			return repository.findById(supplyId);
	}
	
	@PostMapping("/supply")
	public Supply createSupply(@Valid @RequestBody Supply supply) {
		return repository.save(supply);
	}
	
	@PutMapping("/supply/{id}")
	public Supply updateSupply(
			@PathVariable(value = "id") Long supplyId,
			@Valid
			@RequestBody Supply supplyResponse) {
		Optional<Supply> supply = repository.findById(supplyId);
		Supply newSupply = supply.get();
		if(supply.isPresent()) {
			
			newSupply.setName (supplyResponse.getName ());
			newSupply.setDescription (supplyResponse.getDescription ());
			newSupply.setUnitsInStock(supplyResponse.getUnitsInStock());
			newSupply.setAlertStock(supplyResponse.getAlertStock());
			newSupply.setRequestList(supplyResponse.getRequestList());
			return repository.save(newSupply);	
        }
		
        return null;
        
	}
	
	@DeleteMapping("/supply/{id}")
	public void deleteSupply(@PathVariable(value = "id") Long supplyId) {
		Optional<Supply>  supply = repository.findById(supplyId);
		if(supply.isPresent()) {
			repository.deleteById(supplyId);
		}
	}


}
