package com.application;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Estabelecendo a conexão com o banco de dados
        Connection conn = Conexao.obterConexao();
        JogadorDAO jogadorDAO = new JogadorDAO();  // Criando uma instância do DAO para interagir com o banco de dados
        Jogador jogador = new Jogador();  // Criando uma instância de Jogador
    
        // Verificando se a conexão foi bem-sucedida
        if (conn != null) {
            System.out.println();
            System.out.println("Conectado com sucesso!");  // Exibe uma mensagem de sucesso se a conexão for estabelecida
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Erro ao conectar-se!");  // Exibe uma mensagem de erro se a conexão falhar
            System.out.println();
        }
    
        // Inicializando o scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        int opc = 0;  // Variável que armazenará a opção escolhida pelo usuário
    
        // Loop do menu para garantir que o usuário insira uma opção válida (1 a 6)
        do {
            System.out.println("#MENU DE OPERAÇÕES#");
            System.out.println("(1) Salvar novo Jogador.");
            System.out.println("(2) Listar.");
            System.out.println("(3) Buscar por ID.");
            System.out.println("(4) Atualizar Jogador por ID.");
            System.out.println("(5) Excluir por ID.");
            System.out.println("(6) Fechar programa.");
            System.out.println("Digite uma opção.");
            opc = scanner.nextInt();  // Captura a opção do usuário
            scanner.nextLine();  // Limpa o buffer do scanner após usar nextInt()
    
            // Verifica se a opção digitada é válida, ou seja, entre 1 e 6
            if (opc < 1 || opc > 6) {
                System.out.println();
                System.out.println("Digite uma opção válida!");  // Exibe uma mensagem de erro se a opção for inválida
            }
    
        } while (opc < 1 || opc > 6);  // Repete o loop até o usuário escolher uma opção válida
    
        // Estrutura switch para tratar cada opção do menu
        switch (opc) {
            case 1:
                // Caso 1: Salvar um novo jogador
    
                // Solicita e valida o nome do jogador
                System.out.println("Digite o nome do jogador: ");
                jogador.setNome(scanner.nextLine());
                while (jogador.getNome().isEmpty()) {  // Verifica se o nome não está vazio
                    System.out.println("Digite um nome válido!");
                    jogador.setNome(scanner.nextLine());
                }
    
                // Solicita e valida a idade do jogador
                System.out.println("Digite a idade: ");
                jogador.setIdade(scanner.nextInt());
                scanner.nextLine();  // Limpa o buffer após a captura de um inteiro
                while (jogador.getIdade() <= 5 || jogador.getIdade() >= 50) {  // Verifica se a idade está dentro do intervalo válido
                    System.out.println("Digite uma idade válida! 5 anos até 50.");
                    jogador.setIdade(scanner.nextInt());
                    scanner.nextLine();  // Limpa o buffer após a captura de um inteiro
                }
    
                // Solicita e valida a naturalidade do jogador
                System.out.println("Digite a naturalidade: ");
                jogador.setNaturalidade(scanner.nextLine());
                while (jogador.getNaturalidade().isEmpty()) {  // Verifica se a naturalidade não está vazia
                    System.out.println("Digite uma naturalidade válida: ");
                    jogador.setNaturalidade(scanner.nextLine());
                }
    
                // Solicita e valida o número da camisa do jogador
                System.out.println("Número de camisa: ");
                jogador.setCamisa(scanner.nextInt());
                scanner.nextLine();  // Limpa o buffer após a captura de um inteiro
                while (jogador.getCamisa() <= 0 || jogador.getCamisa() > 99) {  // Verifica se o número da camisa está entre 1 e 99
                    System.out.println("Valor invalido. Digite um número de camisa válido! ");
                    jogador.setCamisa(scanner.nextInt());
                    scanner.nextLine();  // Limpa o buffer após a captura de um inteiro
                }
    
                // Solicita e valida a posição do jogador
                System.out.println("Posição do jogador: ");
                jogador.setPosicao(scanner.nextLine());
                while (jogador.getPosicao().isEmpty()) {  // Verifica se a posição não está vazia
                    System.out.println("Digite a posição do jogador: ");
                    jogador.setPosicao(scanner.nextLine());
                }
    
                // Solicita e valida o pé dominante do jogador
                System.out.println("Pé dominante: ");
                jogador.setPe(scanner.nextLine());
                while (jogador.getPe().isEmpty()) {  // Verifica se o pé dominante não está vazio
                    System.out.println("Informe o pé dominante: ");
                    jogador.setPe(scanner.nextLine());
                }
    
                // Solicita e valida o clube atual do jogador
                System.out.println("Clube atual: ");
                jogador.setClube(scanner.nextLine());
                while (jogador.getClube().isEmpty()) {  // Verifica se o clube atual não está vazio
                    System.out.println("Digite o clube atual: ");
                    jogador.setClube(scanner.nextLine());
                }
    
                System.out.println();
                System.out.println("Jogador salvo com sucesso!");  // Mensagem de sucesso ao salvar o jogador
    
                // Chama o método salvar do JogadorDAO para salvar o jogador no banco de dados
                jogadorDAO.salvar(jogador);
    
                break;
    
            case 2:
                // Caso 2: Listar jogadores cadastrados
                List<Jogador> listaJogadores = jogadorDAO.listagem();  // Chama o método para obter a lista de jogadores
                if (!listaJogadores.isEmpty()) {  // Verifica se há jogadores na lista
                    for (Jogador j : listaJogadores) {
                        // Exibe as informações de cada jogador
                        System.out.println("------------------------");
                        System.out.println("ID: " + j.getId());
                        System.out.println("Nome: " + j.getNome());
                        System.out.println("Idade: " + j.getIdade());
                        System.out.println("Pé: " + j.getPe());
                        System.out.println("Posição: " + j.getPosicao());
                        System.out.println("Camisa: " + j.getCamisa());
                        System.out.println("Naturalidade: " + j.getNaturalidade());
                        System.out.println("Clube: " + j.getClube());
                        System.out.println();
                    }
                } else {
                    System.out.println();
                    System.out.println("Nada cadastrado!");  // Mensagem caso não haja jogadores cadastrados
                    System.out.println();
                }
                break;
    
            case 3:
                // Caso 3: Buscar jogador por ID (ainda não implementado)
                break;
    
            case 4:
                // Caso 4: Atualizar jogador por ID (ainda não implementado)
                break;
    
            case 5:
                // Caso 5: Excluir jogador por ID (ainda não implementado)
                break;
    
            case 6:
                // Caso 6: Fechar o programa
                System.out.println("Programa fechado.");
                break;
    
            default:
                break;
        }
    }
}    