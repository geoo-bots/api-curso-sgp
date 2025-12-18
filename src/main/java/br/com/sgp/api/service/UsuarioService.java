package br.com.sgp.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.dto.UsuarioDTO;
import br.com.sgp.api.enums.UsuarioStatus;
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
    public UsuarioDTO consultarUsuarioPeloId(Long id){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if(usuarioExistente.isPresent()){
            Usuario usuario = usuarioExistente.get();

            //pegar idade
            LocalDate dataNascimento = usuario.getDataNascimento();
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataNascimento, dataAtual);
            Integer idade = periodo.getYears();

            //formatação cpf
            String cpfCadastrado = usuario.getCpf();
            String tresPrimeirosIndices = cpfCadastrado.substring(0, 3);
            String cpfFormatado = tresPrimeirosIndices + ".***.***-**";

            //formatação status

            UsuarioStatus status = usuario.getStatus(); // ATIVO, INATIVO
            String statusString = status.toString();
            String primeiroCaracter = statusString.substring(0,1).toUpperCase();
            String demaisCaracteres = statusString.substring(1).toLowerCase();
            String statusFormatado = primeiroCaracter + demaisCaracteres;

            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setNome(usuario.getNome());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setDataNascimento(dataNascimento);
            usuarioDTO.setIdade(idade);
            usuarioDTO.setCpf(cpfFormatado);
            usuarioDTO.setStatus(statusFormatado);

            return usuarioDTO;

        }
        return null;
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