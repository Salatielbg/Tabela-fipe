package br.com.alura.tabelfipe.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
