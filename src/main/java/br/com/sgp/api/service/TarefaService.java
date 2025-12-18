package br.com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.dto.TarefaDTO;
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

    public TarefaDTO consultarTarefaPeloId(Long id){
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        if(tarefaExistente.isPresent()){
            Tarefa tarefa = tarefaExistente.get();


            //formatacao status

            TarefaStatus status = tarefa.getStatus();
            String statusString = status.toString();
            String primeiroCaracterStatus = statusString.substring(0,1);
            String demaisCaracteresStatus = statusString.substring(1).toLowerCase();
            String statusFormatado = primeiroCaracterStatus + demaisCaracteresStatus;


            //formatacao prioridade

            Prioridade prioridade = tarefa.getPrioridade();
            String prioridadeString = prioridade.toString();
            String primeiroCaracterePriori = prioridadeString.substring(0,1);
            String demaisCaracteresPriori = prioridadeString.substring(1).toLowerCase();
            String prioridadeFormatado = primeiroCaracterePriori + demaisCaracteresPriori;


        



            TarefaDTO tarefaDTO = new TarefaDTO();

            tarefaDTO.setId(tarefa.getId());
            tarefaDTO.setTitulo(tarefa.getTitulo());
            tarefaDTO.setDescricao(tarefa.getDescricao());
            tarefaDTO.setDataCriacao(tarefa.getDataCriacao());
            tarefaDTO.setDataConclusao(tarefa.getDataConclusao());
            tarefaDTO.setPrioridade(prioridadeFormatado);
            tarefaDTO.setStatus(statusFormatado);
            tarefaDTO.setProjeto(tarefa.getProjeto());
            tarefaDTO.setUsuario(tarefa.getUsuario());
        
            return tarefaDTO;
        }
        return null;

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