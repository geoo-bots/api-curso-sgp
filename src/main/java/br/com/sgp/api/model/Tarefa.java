package br.com.sgp.api.model;

import java.time.LocalDate;

import br.com.sgp.api.enums.Prioridade;
import br.com.sgp.api.enums.TarefaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String titulo;
    

    @Column(columnDefinition = "TEXT")
    private String descricao;
    

    @NotNull
    @Column(nullable = false)
    private LocalDate dataCriacao;
    
    @Column
    private LocalDate dataConclusao;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Prioridade prioridade;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TarefaStatus status; 

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Projeto projeto;

    @ManyToOne
    private Usuario usuario;

    //checar com o professor se o usuário é nullable ou n

}
