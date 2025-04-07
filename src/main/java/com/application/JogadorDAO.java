package com.application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {

    public void salvar(Jogador jogador) {
        // SQL para inserir um novo jogador
        var sql = "INSERT INTO jogador (nome, idade, pe, posicao, camisa, naturalidade, clube) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Tentando conectar ao banco e executar a query
        try (var conexao = Conexao.obterConexao();
             var st = conexao.prepareStatement(sql)) {

            // Definindo os parâmetros do PreparedStatement
            st.setString(1, jogador.getNome());
            st.setInt(2, jogador.getIdade());
            st.setString(3, jogador.getPe());
            st.setString(4, jogador.getPosicao());
            st.setInt(5, jogador.getCamisa());
            st.setString(6, jogador.getNaturalidade());
            st.setString(7, jogador.getClube());

            // Executando o comando SQL
            st.executeUpdate();

        } catch (SQLException e) {
            // Exibindo detalhes do erro no console
            System.out.println();
            System.out.println("Erro ao salvar jogador no banco de dados!");
            System.out.println("Mensagem: " + e.getMessage());
            e.printStackTrace();  // Detalha o erro para ajudar no diagnóstico
            System.out.println();
        }
    }

    public List<Jogador> listagem() {
        // SQL para selecionar todos os jogadores
        var sql = "SELECT * FROM jogador";
    
        // Lista para armazenar os jogadores recuperados do banco de dados
        List<Jogador> listaJogadores = new ArrayList<>();
    
        // Tentando conectar ao banco e executar a query
        try (var conexao = Conexao.obterConexao();
             var st = conexao.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
    
            // Percorrendo os resultados do banco de dados
            while (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setId(rs.getInt("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setIdade(rs.getInt("idade"));
                jogador.setPe(rs.getString("pe"));
                jogador.setPosicao(rs.getString("posicao"));
                jogador.setCamisa(rs.getInt("camisa"));
                jogador.setNaturalidade(rs.getString("naturalidade"));
                jogador.setClube(rs.getString("clube"));
    
                // Adicionando o jogador à lista
                listaJogadores.add(jogador);
            }
    
        } catch (SQLException e) {
            // Exibindo detalhes do erro
            System.out.println();
            System.out.println("Erro ao listar!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    
        // Retorna a lista de jogadores
        return listaJogadores;
    }

    public Jogador buscarPorID(int id) {
        // SQL para selecionar/buscar por ID
        var sql = "SELECT * FROM jogador WHERE id = ?";
    
        try (var conexao = Conexao.obterConexao();
             var st = conexao.prepareStatement(sql)) {
    
            // Definir o parâmetro do PreparedStatement
            st.setInt(1, id);
    
            // Executar a consulta
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    // Se o jogador for encontrado, cria e popula o objeto
                    Jogador jogador = new Jogador();
                    jogador.setId(rs.getInt("id"));
                    jogador.setNome(rs.getString("nome"));
                    jogador.setIdade(rs.getInt("idade"));
                    jogador.setPe(rs.getString("pe"));
                    jogador.setPosicao(rs.getString("posicao"));
                    jogador.setCamisa(rs.getInt("camisa"));
                    jogador.setNaturalidade(rs.getString("naturalidade"));
                    jogador.setClube(rs.getString("clube"));
    
                    return jogador;  // Retorna o jogador encontrado
                }
            }
    
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Erro ao buscar jogador por ID: " + e.getMessage());
            e.printStackTrace();  // Detalha o erro para diagnóstico
        }
    
        // Caso o jogador não seja encontrado, retorna null
        return null;
    }

    public void atualizar(Jogador jogador) {
        // SQL para atualizar os dados do jogador
        var sql = "UPDATE jogador SET nome = ?, idade = ?, pe = ?, posicao = ?, camisa = ?, naturalidade = ?, clube = ? WHERE id = ?";
    
        try (var conexao = Conexao.obterConexao();
             var st = conexao.prepareStatement(sql)) {
    
            // Definindo os parâmetros do PreparedStatement
            st.setString(1, jogador.getNome());
            st.setInt(2, jogador.getIdade());
            st.setString(3, jogador.getPe());
            st.setString(4, jogador.getPosicao());
            st.setInt(5, jogador.getCamisa());
            st.setString(6, jogador.getNaturalidade());
            st.setString(7, jogador.getClube());
            st.setInt(8, jogador.getId());
    
            // Executando a atualização
          st.executeUpdate();
    
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Erro de SQL ao atualizar jogador: " + e.getMessage());
            e.printStackTrace();  // Detalha o erro para diagnóstico
            System.out.println();
        }
    }
    
    public void excluir(int id) {
        // SQL para excluir um jogador
        var sql = "DELETE FROM jogador WHERE id = ?";
    
        try (var conexao = Conexao.obterConexao();
             var st = conexao.prepareStatement(sql)) {
    
            st.setInt(1, id);
    
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Erro de SQL ao excluir jogador: " + e.getMessage());
            e.printStackTrace();  // Detalha o erro para diagnóstico
           System.out.println();
        }
    }
}    