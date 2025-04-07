package com.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

     private static final String URL = "jdbc:mysql://localhost:3306/Bancosistemajogadores";
     private static final String USUARIO = "root";
     private static final String SENHA = "12345";


    public static Connection obterConexao(){

        try {

            return DriverManager.getConnection(URL, USUARIO, SENHA);

            
        } catch (SQLException e) {
System.out.println();
System.out.println("Erro SQL ao conectar-se! " + e.getMessage());
System.out.println();
}
        return null;

    }
}
