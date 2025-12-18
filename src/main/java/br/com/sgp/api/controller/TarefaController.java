package br.com.sgp.api.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sgp.api.dto.TarefaDTO;
import br.com.sgp.api.enums.Prioridade;
import br.com.sgp.api.enums.TarefaStatus;
import br.com.sgp.api.model.Tarefa;
import br.com.sgp.api.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;


    //buscar tarefa pelo Id  = consultar tarefa pelo id
    @GetMapping(value="/{id}")
    public ResponseEntity <TarefaDTO> buscarTarefaPeloId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(tarefaService.consultarTarefaPeloId(id));
    }


    // buscar tarefas (lista de tarefas) = consultar tarefas 

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(){
        return ResponseEntity.ok().body(tarefaService.consultarTarefas());
    }

    //salva a tarefa (chamando o cadastro)
    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@Valid @RequestBody Tarefa tarefa){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
    }


    //atualiza a tarefa (primeiro consulta se a tarefa ja existe pelo consultar tarefa id, verifica no If se está
    //vazio e se estiver seta como atualizado

    @PutMapping(value = "/{id}")
        public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid
            @RequestBody Tarefa tarefa)
           {
            TarefaDTO tarefaExistente = tarefaService.consultarTarefaPeloId(id);

            if(Objects.isNull(tarefaExistente)){
                return ResponseEntity.notFound().build();
            }
                tarefa.setId(id);
            return ResponseEntity.ok().body(tarefaService.salvarTarefa(tarefa));
        }


        //verifica na lista de tarefas se existe uma tarefa consultada pelo id
        // se no if da lista ela estiver vazia, ele ignora, se estiver com algo ele deleta

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> excluirTarefa(@PathVariable Long id){
            TarefaDTO tarefaExistente = tarefaService.consultarTarefaPeloId(id);

            if(Objects.isNull(tarefaExistente)){
                return ResponseEntity.notFound().build();
            }
            tarefaService.deletarTarefa(id);
            return ResponseEntity.noContent().build();

        }    


        //get optional filtrar pelo titulo

        @GetMapping(value="/buscaPorTitulo")
        public ResponseEntity <Tarefa> consultarTarefaPeloTitulo(@RequestParam("titulo") String titulo){
        Optional<Tarefa> tarefaExistente = tarefaService.buscarTarefaPeloTitulo(titulo);

        if(tarefaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(tarefaExistente.get());

    }

    //get com lista 
    //fazer a consulta e depois mandar sem get
       @GetMapping(value="/buscaPorStatus")
        public ResponseEntity<List<Tarefa>> consultarTarefaPeloStatus(@RequestParam("status") TarefaStatus status){
        List<Tarefa> tarefaExistente = tarefaService.filtrarTarefaPeloStatus(status);

        if(tarefaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(tarefaExistente);
    }

    
    //get filtrado pela prioeidade

       @GetMapping(value="/buscaPorPrioridade")
       public ResponseEntity<List<Tarefa>> consultarTarefaPelaPrioridade(@RequestParam("prioridade") Prioridade prioridade){
        List<Tarefa> tarefaExistente = tarefaService.filtrarTarefaPelaPrioridade(prioridade);
        
        if(tarefaExistente.isEmpty()){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok().body(tarefaExistente);
       }
}