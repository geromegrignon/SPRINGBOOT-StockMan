package stockman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockman.model.Supply;


@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long>{
	
	@Query("SELECT s FROM Supply s WHERE s.unitsInStock < s.alertStock")
	  List<Supply> findOnAlertSupplies();
}
