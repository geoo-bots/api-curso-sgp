package br.com.sgp.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sgp.api.model.Projeto;
import br.com.sgp.api.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
    
    
    private Long id;
    private String titulo;
    private String descricao;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataConclusao;

   
    private String prioridade;
    private String status; 

   
    private Projeto projeto; //editar
    private Usuario usuario; //editar

}
