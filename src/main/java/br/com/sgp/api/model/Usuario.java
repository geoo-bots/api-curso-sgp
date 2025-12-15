package br.com.sgp.api.model;

import java.time.LocalDate;

import br.com.sgp.api.enums.UsuarioStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_usuarios")

public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column (nullable = false, length = 19)
    private String senha;
    
    @Column (nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UsuarioStatus status;

}
