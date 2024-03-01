/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Excecao.ErroConexaoSQL;
import java.sql.Connection;

/**
 *
 * @author vambersson
 */
public interface IGere_Conexao {
    
    public Connection Conectar()throws ErroConexaoSQL;
    
    public void Desconectar(Connection c)throws ErroConexaoSQL;
    
}
