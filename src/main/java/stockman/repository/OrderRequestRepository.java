package stockman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockman.model.OrderRequest;


@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long>{

//	@Query("SELECT o.id, o.quantity, o.deliveryDate, s.id, s.name, u.id, u.name FROM OrderRequest o INNER JOIN Supply s ON o.supply.id = s.id INNER JOIN User u ON o.user = u.id ")
//	  List<OrderRequest> findAllRequests();
}


