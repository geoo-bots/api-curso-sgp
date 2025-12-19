package br.com.sgp.api.exception;

public class ProjetoNaoEncontradoException extends RuntimeException{
    public ProjetoNaoEncontradoException(Long id){
        super(String.format("Projeto de ID = %d não encontrado", id));
    }


    public ProjetoNaoEncontradoException(String status){
        super(String.format("Projeto de status = %s não encontrado. Verifique e tente novamente", status));
    }
}
