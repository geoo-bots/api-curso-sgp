package br.com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.dto.ProjetoDTO;
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
    public ProjetoDTO consultarProjetoPeloId(Long id){
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);

        if(projetoExistente.isPresent()){
            Projeto projeto = projetoExistente.get();

            ProjetoStatus status = projeto.getStatus();
            String statusString = status.toString();
            String primeiroCaracter = statusString.substring(0,1).toUpperCase();
            String demaisCaracteres = statusString.substring(1).toLowerCase();
            String statusFormatado = primeiroCaracter + demaisCaracteres;

            ProjetoDTO projetoDTO = new ProjetoDTO();

            projetoDTO.setId(projeto.getId());
            projetoDTO.setNome(projeto.getNome());
            projetoDTO.setDescricao(projeto.getDescricao());
            projetoDTO.setDataInicio(projeto.getDataInicio());
            projetoDTO.setDataConclusao(projeto.getDataConclusao());
            projetoDTO.setStatus(statusFormatado);
            projetoDTO.setResponsavel(projeto.getResponsavel());
            
            return projetoDTO;
        }
        return null;

    }

    public void deletarProjeto(Long id){
        projetoRepository.deleteById(id);
    }

    public List<Projeto> filtrarProjetosPeloStatus(ProjetoStatus status){
        return projetoRepository.findByStatus(status);
    }
}
