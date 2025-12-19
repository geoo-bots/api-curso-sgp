package br.com.sgp.api.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class MensagemErroApi{
    private HttpStatus status;
    private List<String> erros;

    public MensagemErroApi(HttpStatus status, List<String> erros){

        this.status = status;
        this.erros = erros;
    }

    public MensagemErroApi(HttpStatus status, String erro){

        this.status = status;
        this.erros = Arrays.asList(erro);
    }
}