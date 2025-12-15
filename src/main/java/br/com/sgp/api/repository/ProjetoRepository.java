package br.com.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.api.enums.ProjetoStatus;
import br.com.sgp.api.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

    List<Projeto> findByStatus(ProjetoStatus status);

    
}