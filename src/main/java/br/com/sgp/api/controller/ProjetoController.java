package br.com.sgp.api.controller;

import java.util.List;
import java.util.Objects;

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

import br.com.sgp.api.dto.ProjetoDTO;
import br.com.sgp.api.enums.ProjetoStatus;
import br.com.sgp.api.exception.ProjetoNaoEncontradoException;
import br.com.sgp.api.model.Projeto;
import br.com.sgp.api.service.ProjetoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value="/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<Projeto>>ListarProjetos(){
        return ResponseEntity.ok().body(projetoService.consultarProjetos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjetoDTO>buscarProjetoPeloId(@PathVariable ("id") Long id){
        ProjetoDTO projetoDTO = projetoService.consultarProjetoPeloId(id);

        if(Objects.isNull(projetoDTO)){
            throw new ProjetoNaoEncontradoException(id);
        }
        
        return ResponseEntity.ok().body(projetoDTO);    
    
    }

    @PostMapping 
    public ResponseEntity<Projeto> cadastrarProjeto(@Valid @RequestBody Projeto projeto){
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.salvarProjeto(projeto));
    }
    
    @PutMapping(value = "/{id}")
        public ResponseEntity<Projeto>atualizarProjeto(@PathVariable Long id, 
           @Valid @RequestBody Projeto projeto)
        {
            ProjetoDTO projetoDTO = projetoService.consultarProjetoPeloId(id);
            if(Objects.isNull(projetoDTO)){
                throw new ProjetoNaoEncontradoException(id);
            }
                
            
            projeto.setId(id);
        return ResponseEntity.ok().body(projetoService.salvarProjeto(projeto));
        }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> excluirProjeto(@PathVariable Long id){
            ProjetoDTO projetoDTO = projetoService.consultarProjetoPeloId(id);

            if(Objects.isNull(projetoDTO)){
                throw new ProjetoNaoEncontradoException(id);
            }
            projetoService.deletarProjeto(id);
            return ResponseEntity.noContent().build();
        }


    @GetMapping(value= "/buscaPorStatus")
        public ResponseEntity<List<Projeto>> consultarProjetoPeloStatus(@RequestParam("status") ProjetoStatus status){
            List<Projeto> projetoExistente = projetoService.filtrarProjetosPeloStatus(status);
            if(projetoExistente.isEmpty()){
                throw new ProjetoNaoEncontradoException(status.toString());
            }
            return ResponseEntity.ok().body(projetoExistente);
        }

}
