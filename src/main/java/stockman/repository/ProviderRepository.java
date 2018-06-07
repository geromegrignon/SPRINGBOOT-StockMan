package stockman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockman.model.Provider;


@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
	
	@Query("SELECT p FROM Provider p WHERE p.supplyList IS NOT EMPTY")
	  List<Provider> findNotEmptyProviders();
}
