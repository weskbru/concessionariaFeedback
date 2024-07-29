package com.saga.concessionaria.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saga.concessionaria.model.Consultor;



@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Long> {

	Optional<Consultor>findByCpf(String cpf); 

	/* Optional<Consultor> findByCpfAndSenha(String cpf); */

}
