package stockman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import stockman.modele.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>, JpaSpecificationExecutor<Provider>{}
