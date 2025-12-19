package br.com.sgp.api.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    
    public UsuarioNaoEncontradoException(Long id){
        super(String.format("Usuario de ID = %d não encontrado.", id));
    
    }

    public UsuarioNaoEncontradoException(String cpf){
        super(String.format("Usuário de CPF = %s não encontrado.", cpf));
    }
    
}
