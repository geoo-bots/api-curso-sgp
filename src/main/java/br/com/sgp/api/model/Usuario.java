package br.com.sgp.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sgp.api.enums.UsuarioStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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


    @NotBlank(message = "O campo 'nome' é obrigatório") // validação a nível de requisição
    @Column(nullable = false)
    private String nome;

    @NotBlank (message = "O campo 'cpf' é obrigatório e deve ser único!")
    @Column(nullable = false, unique = true)
    private String cpf;

    @Email
    @NotBlank(message = "O campo 'email' é obrigatório e deve ser único!")
    @Column(nullable = false, unique = true)
    private String email;
    
    @Pattern (regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{1,19}$" ) //1 letra maiuscula, 1 caracter especial e deve conter números
    @NotBlank(message= "O campo 'senha' é obrigatório e deve ter no máximo  19 caracteres.")
    @Size(max = 19)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column (nullable = false, length = 19)
    private String senha;
    
    @NotNull (message = "O campo 'Data de nascimento' é obrigatório.")
    @Column (nullable = false)
    private LocalDate dataNascimento;


    @NotNull (message = "O campo 'status' é obrigatório.")
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UsuarioStatus status;

}


//@Min(value = 18)
//@Max(value = 50)