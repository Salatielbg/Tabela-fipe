package br.com.alura.tabelfipe.service;

import br.com.alura.tabelfipe.model.DadosAno;
import br.com.alura.tabelfipe.model.DadosVeiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            if (classe.equals(List.class)){
                return (T) mapper.readValue(json,
                        mapper.getTypeFactory().constructCollectionType(List.class, DadosVeiculo.class));
            }
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            try {
                return (T) mapper.readValue(json,
                        mapper.getTypeFactory().constructCollectionType(List.class, DadosAno.class));
            }catch (JsonProcessingException ex){
                throw new RuntimeException(e);
            }
        }
    }
}
