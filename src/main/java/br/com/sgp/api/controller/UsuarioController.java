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

import br.com.sgp.api.dto.UsuarioDTO;
import br.com.sgp.api.exception.UsuarioNaoEncontradoException;
import br.com.sgp.api.model.Usuario;
import br.com.sgp.api.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value="/{id}")
    public ResponseEntity<UsuarioDTO>buscarUsuarioPeloId(@PathVariable("id") Long id){
        UsuarioDTO usuarioDTO = usuarioService.consultarUsuarioPeloId(id);

        if(Objects.isNull(usuarioDTO)){
            throw new UsuarioNaoEncontradoException(id);
        }

        return ResponseEntity.ok().body(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok().body(usuarioService.consultarUsuarios());
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @PutMapping(value = "/{id}")
        public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, 
           @Valid @RequestBody Usuario usuario)
           {
            UsuarioDTO usuarioDTO = usuarioService.consultarUsuarioPeloId(id);

            if(Objects.isNull(usuarioDTO)){
                throw new UsuarioNaoEncontradoException(id);
            }
                usuario.setId(id);
            return ResponseEntity.ok().body(usuarioService.salvarUsuario(usuario));
        }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){
            UsuarioDTO usuarioDTO = usuarioService.consultarUsuarioPeloId(id);

            if(Objects.isNull(usuarioDTO)){
                throw new UsuarioNaoEncontradoException(id);
            }
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        }    


    
    @GetMapping(value="/buscaPorCpf")
    public ResponseEntity <Usuario> consultarUsuarioPeloCpf(@RequestParam("cpf") String cpf){
        Optional<Usuario> usuarioExistente = usuarioService.buscarUsuarioPeloCpf(cpf);

        if(usuarioExistente.isEmpty()){
            throw new UsuarioNaoEncontradoException(cpf);
        }
        return ResponseEntity.ok().body(usuarioExistente.get());

    }

    @GetMapping(value="/buscaPorEmail")
    public ResponseEntity <Usuario> consultarUsuarioPeloEmail(@RequestParam("email") String email){
        Optional<Usuario> usuarioExistente = usuarioService.buscarUsuarioPeloEmail(email);

        if(usuarioExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuarioExistente.get());

    }
    }


