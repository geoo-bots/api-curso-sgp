package br.com.sgp.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.api.enums.Prioridade;
import br.com.sgp.api.enums.TarefaStatus;
import br.com.sgp.api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    List<Tarefa> findByPrioridade(Prioridade prioridade);

    List<Tarefa> findByStatus(TarefaStatus status);

	Optional<Tarefa> findByTitulo(String titulo);

    
}