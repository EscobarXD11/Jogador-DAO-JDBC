package com.example;

import java.sql.Connection;

import com.Conexao;

public class Main {
    public static void main(String[] args) {
       Connection conn = Conexao.obterConexao();

       if (conn != null) {
        System.out.println();
        System.out.println("Conectado com sucesso!");
        System.out.println();
        
       } else {
        System.out.println();
        System.out.println("Erro ao conectar-se!");
        System.out.println();
        
       }


    }
}