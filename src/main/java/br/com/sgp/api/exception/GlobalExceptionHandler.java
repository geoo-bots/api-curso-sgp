package br.com.sgp.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.sgp.api.dto.MensagemErroApi;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErroApi> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, WebRequest request
        ) {
                                        ///get binding
            List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                    .map(fe -> fe.getDefaultMessage())
                    .collect(Collectors.toList());

            MensagemErroApi msgErroApi = new MensagemErroApi(HttpStatus.BAD_REQUEST, erros);

        return ResponseEntity.badRequest().body(msgErroApi);
        
        }





        @ExceptionHandler(UsuarioNaoEncontradoException.class)
        public ResponseEntity<MensagemErroApi> handleUsuarioNaoEncontradoException(
            UsuarioNaoEncontradoException ex, WebRequest request)
            {
        MensagemErroApi mErroApi = new MensagemErroApi(HttpStatus.NOT_FOUND, ex.getMessage());

        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mErroApi);
        }




        @ExceptionHandler(ProjetoNaoEncontradoException.class)
        public ResponseEntity<MensagemErroApi>handleProjetoNaoEncontradoException(
            ProjetoNaoEncontradoException ex, WebRequest request)
        {
            MensagemErroApi mErroApi = new MensagemErroApi(HttpStatus.NOT_FOUND, ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mErroApi);
        }


        @ExceptionHandler(TarefaNaoEncontradaException.class)
        public ResponseEntity<MensagemErroApi>handleTarefaNaoEncontradaException(
            TarefaNaoEncontradaException ex, WebRequest request)
            {
            MensagemErroApi mErroApi = new MensagemErroApi(HttpStatus.NOT_FOUND, ex.getMessage());
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mErroApi);
            }


        
    }


       