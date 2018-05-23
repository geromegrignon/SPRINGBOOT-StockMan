package stockman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import stockman.modele.Supply;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long>, JpaSpecificationExecutor<Supply>{}
