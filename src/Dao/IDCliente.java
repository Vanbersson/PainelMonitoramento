/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Basica.Cliente;
import Excecao.ErroConexaoSQL;
import Excecao.ErroDao;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public interface IDCliente {
    
    
    public ArrayList<Cliente> situacao_OS(String s) throws ErroConexaoSQL, ErroDao; 
    
    public ArrayList<Cliente> lista_OS_ClienteCodigo(String codigo) throws ErroConexaoSQL, ErroDao; 
    
    public ArrayList<Cliente> Lista_os_ClienteNome(String Nome) throws ErroConexaoSQL, ErroDao; 
    
    public ArrayList<Cliente> Lista_os_ClienteNro_OS(String Nro_OS) throws ErroConexaoSQL, ErroDao;
    
    public ArrayList<Cliente> Lista_os_ClientePlca(String placa) throws ErroConexaoSQL, ErroDao;
    
    
    public ArrayList<Cliente> Lista_Cli_Situa_OSCodigo(String cli_codigo,String situacao_OS) throws ErroConexaoSQL, ErroDao;
    
    public ArrayList<Cliente> Lista_Cli_Situa_OSNome(String cli_Nome,String situacao_OS) throws ErroConexaoSQL, ErroDao;
    
}
