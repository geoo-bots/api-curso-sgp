package br.com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.enums.Prioridade;
import br.com.sgp.api.enums.TarefaStatus;
import br.com.sgp.api.model.Tarefa;
import br.com.sgp.api.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa salvarTarefa(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> consultarTarefas(){
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> consultarTarefaPeloId(Long id){
        return tarefaRepository.findById(id);
    }

    public void deletarTarefa(Long id){
        tarefaRepository.deleteById(id);
    }

    public Optional<Tarefa> buscarTarefaPeloTitulo(String titulo){
        return tarefaRepository.findByTitulo(titulo);
    }

    
    public List<Tarefa> filtrarTarefaPeloStatus(TarefaStatus status){
        return tarefaRepository.findByStatus(status);
        
    }
    
    public List<Tarefa> filtrarTarefaPelaPrioridade(Prioridade prioridade){
        return tarefaRepository.findByPrioridade(prioridade);
    }

}