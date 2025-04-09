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
            return;  // Caso a conexão falhe, o programa termina
        }

        // Inicializando o scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        int opc = 0;  // Variável que armazenará a opção escolhida pelo usuário

        // Loop para exibir o menu até o usuário escolher a opção 6 (Fechar programa)
        while (opc != 6) {
            System.out.println("#MENU DE OPERAÇÕES#");
            System.out.println("(1) Salvar novo Jogador.");
            System.out.println("(2) Listar.");
            System.out.println("(3) Buscar por ID.");
            System.out.println("(4) Atualizar Jogador por ID.");
            System.out.println("(5) Excluir por ID.");
            System.out.println("(6) Fechar programa.");
            System.out.println("Digite uma opção:");
            opc = scanner.nextInt();  // Captura a opção do usuário
            scanner.nextLine();  // Limpa o buffer do scanner após usar nextInt()

            // Verifica se a opção digitada é válida, ou seja, entre 1 e 6
            if (opc < 1 || opc > 6) {
                System.out.println("Digite uma opção válida!");
                continue;  // Volta para o início do loop se a opção for inválida
            }

            // Estrutura switch para tratar cada opção do menu
            switch (opc) {
                case 1:
                    // Caso 1: Salvar um novo jogador
                    System.out.println("Digite o nome do jogador: ");
                    jogador.setNome(scanner.nextLine());
                    while (jogador.getNome().isEmpty()) {
                        System.out.println("Digite um nome válido!");
                        jogador.setNome(scanner.nextLine());
                    }

                    System.out.println("Digite a idade: ");
                    jogador.setIdade(scanner.nextInt());
                    scanner.nextLine();  // Limpa o buffer após nextInt()
                    while (jogador.getIdade() <= 5 || jogador.getIdade() >= 50) {
                        System.out.println("Digite uma idade válida! (5 até 50 anos)");
                        jogador.setIdade(scanner.nextInt());
                        scanner.nextLine();
                    }

                    System.out.println("Digite a naturalidade: ");
                    jogador.setNaturalidade(scanner.nextLine());
                    while (jogador.getNaturalidade().isEmpty()) {
                        System.out.println("Digite uma naturalidade válida: ");
                        jogador.setNaturalidade(scanner.nextLine());
                    }

                    System.out.println("Número de camisa: ");
                    jogador.setCamisa(scanner.nextInt());
                    scanner.nextLine();  // Limpa o buffer após nextInt()
                    while (jogador.getCamisa() <= 0 || jogador.getCamisa() > 99) {
                        System.out.println("Valor inválido. Digite um número de camisa válido!");
                        jogador.setCamisa(scanner.nextInt());
                        scanner.nextLine();
                    }

                    System.out.println("Posição do jogador: ");
                    jogador.setPosicao(scanner.nextLine());
                    while (jogador.getPosicao().isEmpty()) {
                        System.out.println("Digite a posição do jogador: ");
                        jogador.setPosicao(scanner.nextLine());
                    }

                    System.out.println("Pé dominante: ");
                    jogador.setPe(scanner.nextLine());
                    while (jogador.getPe().isEmpty()) {
                        System.out.println("Informe o pé dominante: ");
                        jogador.setPe(scanner.nextLine());
                    }

                    System.out.println("Clube atual: ");
                    jogador.setClube(scanner.nextLine());
                    while (jogador.getClube().isEmpty()) {
                        System.out.println("Digite o clube atual: ");
                        jogador.setClube(scanner.nextLine());
                    }

                    jogadorDAO.salvar(jogador);
                    System.out.println("Jogador salvo com sucesso!");
                    break;

                case 2:
                    // Caso 2: Listar jogadores cadastrados
                    List<Jogador> listaJogadores = jogadorDAO.listagem();
                    if (!listaJogadores.isEmpty()) {
                        for (Jogador j : listaJogadores) {
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
                        System.out.println("Nada cadastrado!");
                    }
                    break;

                case 3:
                    // Caso 3: Buscar por ID
                    System.out.println("Digite um ID: ");
                    int idBuscar = scanner.nextInt();
                    jogador = jogadorDAO.buscarPorID(idBuscar);
                    if (jogador != null) {
                        System.out.println();
                        System.out.println("Jogador encontrado: ");
                        System.out.println("------------------------");
                        System.out.println("ID: " + jogador.getId());
                        System.out.println("Nome: " + jogador.getNome());
                        System.out.println("Idade: " + jogador.getIdade());
                        System.out.println("Pé: " + jogador.getPe());
                        System.out.println("Posição: " + jogador.getPosicao());
                        System.out.println("Camisa: " + jogador.getCamisa());
                        System.out.println("Naturalidade: " + jogador.getNaturalidade());
                        System.out.println("Clube: " + jogador.getClube());
                        System.out.println("------------------------");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("Nada encontrado!");
                        System.out.println();
                    }
                    break;

                case 4:
                    // Caso 4: Atualizar jogador por ID
                    System.out.println("Digite um id para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();  // Limpa o buffer

                    jogador = jogadorDAO.buscarPorID(idAtualizar);
                    if (jogador != null) {
                        // Mesmo processo de entrada de dados como no caso 1
                        System.out.println("Digite o nome do jogador: ");
                        jogador.setNome(scanner.nextLine());
                        while (jogador.getNome().isEmpty()) {
                            System.out.println("Digite um nome válido!");
                            jogador.setNome(scanner.nextLine());
                        }

                        // Continuação do processo de atualização (semelhante ao caso 1)
                        // Código para atualizar o jogador...

                        System.out.println("Jogador atualizado com sucesso!");
                        jogadorDAO.atualizar(jogador);
                    } else {
                        System.out.println();
                        System.out.println("Nada encontrado!");
                        System.out.println();
                    }
                    break;

                case 5:
                    // Caso 5: Excluir jogador por ID
                    System.out.println("Digite um ID para excluir: ");
                    int idExcluir = scanner.nextInt();
                    jogador = jogadorDAO.buscarPorID(idExcluir);
                    if (jogador != null) {
                        System.out.println();
                        System.out.println("ID: " + jogador.getId());
                        System.out.println("Jogador " + jogador.getNome() + ", do clube " + jogador.getClube() + " excluído com sucesso!");
                        System.out.println();
                        jogadorDAO.excluir(idExcluir);
                    } else {
                        System.out.println();
                        System.out.println("Nada encontrado!");
                        System.out.println();
                    }
                    break;

                case 6:
                    // Caso 6: Fechar programa
                    System.out.println("Fechando programa...");
                    // Fechar a conexão com o banco de dados antes de sair
                    try {
                        conn.close();
                        System.out.println();
                        System.out.println("Conexão fechada.");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Erro ao fechar a conexão.");
                        System.out.println();
                    }
                    return;

                default:
                    break;
            }
        }

        scanner.close();  // Fecha o scanner ao final
    }
}
