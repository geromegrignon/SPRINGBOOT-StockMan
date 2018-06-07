package stockman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockman.model.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

	@Query("SELECT r.id, r.quantity as quantity, r.deliveryDate, s.id, s.name, u.id, u.name FROM Request r INNER JOIN Supply s ON r.supply.id = s.id INNER JOIN User u ON r.user = u.id ")
	  List<Request> findAllRequests();
}


