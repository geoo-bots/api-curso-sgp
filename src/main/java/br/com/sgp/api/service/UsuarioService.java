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

  
   
    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);

    }
  
    public List<Usuario> consultarUsuarios(){
        return usuarioRepository.findAll();
    }
    
    public UsuarioDTO consultarUsuarioPeloId(Long id){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if(usuarioExistente.isPresent()){
            Usuario usuario = usuarioExistente.get();

           
            LocalDate dataNascimento = usuario.getDataNascimento();
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataNascimento, dataAtual);
            Integer idade = periodo.getYears();

            String cpfCadastrado = usuario.getCpf();
            String tresPrimeirosIndices = cpfCadastrado.substring(0, 3);
            String cpfFormatado = tresPrimeirosIndices + ".***.***-**";


            UsuarioStatus status = usuario.getStatus();
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

    
    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);

    } 

   
    public Optional<Usuario> buscarUsuarioPeloCpf(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

   
    public Optional<Usuario> buscarUsuarioPeloEmail(String email){
        return usuarioRepository.findByEmail(email);
        
    }
}