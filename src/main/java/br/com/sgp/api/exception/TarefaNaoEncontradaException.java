package br.com.sgp.api.exception;

public class TarefaNaoEncontradaException extends RuntimeException{
    public TarefaNaoEncontradaException(Long id){
        super(String.format("Tarefa de ID = %d não encontrada.", id));
    }


    public TarefaNaoEncontradaException(String status){
        super(String.format("Tarefa de status = %s não encontrada.. Verifique e tente novamente", status));
    }

    
}
