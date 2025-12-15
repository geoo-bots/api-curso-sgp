package br.com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.enums.ProjetoStatus;
import br.com.sgp.api.model.Projeto;
import br.com.sgp.api.repository.ProjetoRepository;

@Service
public class ProjetoService {
    

    @Autowired
    private  ProjetoRepository projetoRepository;


    public Projeto salvarProjeto(Projeto projeto){
        return projetoRepository.save(projeto);
    }

    public List<Projeto> consultarProjetos(){
        return projetoRepository.findAll();
    }
    public Optional<Projeto> consultarProjetoPeloId(Long id){
        return projetoRepository.findById(id);
    }

    public void deletarProjeto(Long id){
        projetoRepository.deleteById(id);
    }

    public List<Projeto> filtrarProjetosPeloStatus(ProjetoStatus status){
        return projetoRepository.findByStatus(status);
    }
}
