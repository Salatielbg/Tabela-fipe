package br.com.alura.tabelfipe.principal;

import br.com.alura.tabelfipe.model.DadosAno;
import br.com.alura.tabelfipe.model.DadosVeiculo;
import br.com.alura.tabelfipe.model.DadosModelos;
import br.com.alura.tabelfipe.model.InformacoesVeiculo;
import br.com.alura.tabelfipe.service.ConsumoApi;
import br.com.alura.tabelfipe.service.ConverteDados;
import br.com.alura.tabelfipe.service.IConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    Scanner sc = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();
    private IConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas/";
    private String url = "";

    public void exibeMenu(){
        System.out.println("Escolha a opção com tipo de veiculo que deseja buscar: ");
        System.out.println("1 - Carros" +
                "\n2 - Motos" +
                "\n3 - Caminhoes");
        int tipoVeiculo = sc.nextInt();
        var json = "";
        switch (tipoVeiculo){
            case 1:
                url = ENDERECO + "carros" + MARCAS;
                json = consumo.obterDados(url);
                break;
            case 2:
                url = ENDERECO + "motos" + MARCAS;
                json = consumo.obterDados(url);
                break;
            case 3:
                url = ENDERECO + "caminhoes" + MARCAS;
                json = consumo.obterDados(url);
                break;
            default:
                System.out.println("Opção digitada não foi encontrada.");
                break;
        }

        List<DadosVeiculo> marcas = conversor.obterDados(json, List.class);
        marcas.sort(Comparator.comparingInt(marca -> Integer.parseInt(marca.codigo())));
        marcas.forEach(marca ->
                System.out.println("Cód: " + marca.codigo()
                        + " - Descrição: " + marca.nome())
        );

        System.out.println("Informe o código da marca para consulta: ");
        var marca = sc.nextInt();
        sc.nextLine();
        url = url + marca + "/modelos/";
        json = consumo.obterDados(url);

        DadosModelos modelos = conversor.obterDados(json, DadosModelos.class);
        modelos.getModelos().sort(Comparator.comparing(DadosVeiculo::codigo));
        System.out.println("Modelos: ");
        modelos.getModelos().forEach(modelo ->
                System.out.println("Codigo: " + modelo.codigo() + " Descrição: " + modelo.nome())
        );


        System.out.println("Digite um trecho do nome do veiculo para consultar: ");
        var modeloVeiculo = sc.nextLine();
        List<DadosVeiculo> veiculosEncontrados = modelos.getModelos().stream()
                .filter(e -> e.nome().toUpperCase().contains(modeloVeiculo.toUpperCase()))
                .collect(Collectors.toList());
        if (veiculosEncontrados.isEmpty()) {
            System.out.println("Nenhum veículo encontrado com esse nome.");
        } else {
            veiculosEncontrados.forEach(veiculo ->
                    System.out.println("Cód.: " + veiculo.codigo() + " - Descrição: " + veiculo.nome()));
        }


        System.out.println("Digite o codigo do modelo para consultar os valores: ");
        var codModelo = sc.nextLine();
        url = url + codModelo + "/anos/";
        json = consumo.obterDados(url);

        List<DadosVeiculo> anos = conversor.obterDados(json, List.class);

        for (DadosVeiculo ano : anos){
            String codigoAno = ano.codigo();
            String urlFinal = url + codigoAno;

            json = consumo.obterDados(urlFinal);
            InformacoesVeiculo infoVeic = conversor.obterDados(json, InformacoesVeiculo.class);

            System.out.println("\nAno: " + infoVeic.modelo());
            System.out.println("Marca: " + infoVeic.marca());
            System.out.println("Combustível: " + infoVeic.combustivel());
            System.out.println("Ano: " + infoVeic.anoModelo());
            System.out.println("Valor: " + infoVeic.valor());
            System.out.println("--------------------------");
        }
    }
}
