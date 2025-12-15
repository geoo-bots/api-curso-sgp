package br.com.sgp.api.model;

import java.time.LocalDate;

import br.com.sgp.api.enums.ProjetoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_projetos")
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column (nullable = false)
    private LocalDate dataInicio;

    @Column
    private LocalDate dataConclusao;
    

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProjetoStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario responsavel;

}