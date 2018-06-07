package stockman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stockman.model.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

}


