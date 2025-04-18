package br.com.alura.tabelfipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAno(@JsonAlias("codigo") String codigo,
                       @JsonAlias("nome") String nome)  {
}
