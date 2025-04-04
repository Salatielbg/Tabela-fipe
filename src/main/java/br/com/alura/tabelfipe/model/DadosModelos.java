package br.com.alura.tabelfipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosModelos {

    @JsonAlias("modelos")
    private List<DadosVeiculo> modelos;

    public List<DadosVeiculo> getModelos(){
        return modelos;
    }

    public void setModelos(List<DadosVeiculo> modelos) {
        this.modelos = modelos;
    }
}
