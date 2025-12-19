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

    @NotBlank (message = "O campo 'título' deve ser obrigatório.")
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String titulo;
    

    @Column(columnDefinition = "TEXT")
    private String descricao;
    

    @NotNull (message = "O campo 'Data de criação' deve ser obrigatório.")
    @Column(nullable = false)
    private LocalDate dataCriacao;
    
    @Column
    private LocalDate dataConclusao;

    @NotNull(message = "O campo 'Prioridade' deve ser obrigatório.")
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Prioridade prioridade;

    @NotNull(message=  "O campo 'status' deve ser obrigatório.")
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TarefaStatus status; 

    @NotNull(message = "O campo 'projeto' deve ser obrigatório.")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Projeto projeto;

    @ManyToOne
    private Usuario usuario;

}
