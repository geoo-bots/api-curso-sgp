package br.com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.model.Usuario;
import br.com.sgp.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    //INSERT INTO tb_usuarios VALUES (dados)
    //UPDATE tb_usuarios WHERE id = ?
    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);

    }
    //SELECT * FROM tb_usuarios
    public List<Usuario> consultarUsuarios(){
        return usuarioRepository.findAll();
    }
    //SELECT * from tb_usuarios where id = ?
    public Optional<Usuario> consultarUsuarioPeloId(Long id){
        return usuarioRepository.findById(id);
    }

    // DELETE FROM tb_usuarios where id = ?
    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);

    } 

    //SELECT * FROM tb_usuarios WHERE cpf = ?
    public Optional<Usuario> buscarUsuarioPeloCpf(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

    //SELECT * FROM tb_usuarios WHERE cpf = ?
    public Optional<Usuario> buscarUsuarioPeloEmail(String email){
        return usuarioRepository.findByEmail(email);
        
    }
}