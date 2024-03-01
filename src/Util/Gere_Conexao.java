/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Excecao.ErroConexaoSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author vambersson
 */
public class Gere_Conexao implements IGere_Conexao{
    
    private static Gere_Conexao instancia;
    private final String LOCAL;
    private final String USUARIO;
    private final String SENHA;
    private final String DRIVER;
    
    private Gere_Conexao(){
        ResourceBundle rb = ResourceBundle.getBundle("Util.Dados");
        LOCAL = rb.getString("local");
        USUARIO = rb.getString("usuario");
        SENHA = rb.getString("senha");
        DRIVER = rb.getString("driver");
    }
    
    public static Gere_Conexao getinstancia(){
        if(instancia == null){
            instancia = new Gere_Conexao();
        }
        return instancia;
    }
    
    
    @Override
    public Connection Conectar() throws ErroConexaoSQL {
       Connection c = null;
       
       try{
            Class.forName(DRIVER);
            c = DriverManager.getConnection(LOCAL,USUARIO,SENHA);
        }catch(ClassNotFoundException | SQLException ex){
            throw new ErroConexaoSQL(ex);
        }
        return c;
    }

    @Override
    public void Desconectar(Connection c) throws ErroConexaoSQL {
        try {
            c.close();
        } catch (SQLException ex) {
            throw new ErroConexaoSQL(ex);
        }
    }
    
}
