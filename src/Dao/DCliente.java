/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Basica.Cliente;
import Excecao.ErroConexaoSQL;
import Excecao.ErroDao;
import Util.Gere_Conexao;
import Util.IGere_Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class DCliente implements IDCliente{
    
    private Connection c;
    private final IGere_Conexao GER;
    public DCliente(){
        GER = Gere_Conexao.getinstancia();
    }
    
    @Override
    public ArrayList<Cliente> situacao_OS(String s) throws ErroConexaoSQL, ErroDao {
        c = GER.Conectar();
        
        String sql = "SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
                    "INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA LIKE '2')\n" +
                    "INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (ORS.SITUACAO_OS = ?) \n" +
                    "ORDER BY ORS.NRO_OS DESC";
        Cliente cli = null;
        ArrayList<Cliente> lista = new ArrayList<>();
       
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
           
            pstm.setString(1,s);
            
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                cli = new Cliente();
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));
                
                lista.add(cli);
            }
            
            return lista;
            
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            GER.Desconectar(c);
        }
        
        
    }
    
    
    @Override
    public ArrayList<Cliente> lista_OS_ClienteCodigo(String codigo) throws ErroConexaoSQL, ErroDao{
        c = GER.Conectar();
        String sql = "SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
                    "INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA LIKE '2')\n" +
                    "INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (CLI.CLIENTE LIKE ?)\n" +
                    "ORDER BY ORS.NRO_OS DESC";
       // AND (CLI.CLIENTE LIKE ?)
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Cliente> lista = new ArrayList();
            Cliente cli=null;
            while(rs.next()){
                 cli = new Cliente();
                
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));

                lista.add(cli);
                
            }
            return lista;
            
        } catch (SQLException ex) {
             throw new ErroDao(ex);
        } finally{
            GER.Desconectar(c);
        }
    }
    
    @Override
    public ArrayList<Cliente> Lista_os_ClienteNome(String Nome) throws ErroConexaoSQL, ErroDao{
         c = GER.Conectar();
        
        String sql = "SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
                    "INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA LIKE '2')\n" +
                    "INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (CLI.NOME LIKE ?) \n" +
                    "ORDER BY ORS.NRO_OS DESC";
        Cliente cli = null;
        ArrayList<Cliente> lista = new ArrayList<>();
       
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
           
            pstm.setString(1,Nome);
            
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                cli = new Cliente();
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));
                
                lista.add(cli);
            }
            
            return lista;
            
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            GER.Desconectar(c);
        }
    }
    
    
    @Override
    public ArrayList<Cliente> Lista_os_ClienteNro_OS(String Nro_OS) throws ErroConexaoSQL, ErroDao{
        c =GER.Conectar();
        String sql="SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
"INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA = '2')\n" +
"INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (ORS.NRO_OS = ?)";
       
        
        try {         
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, Nro_OS);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Cliente> lista = new ArrayList();
            Cliente cli= null;
            
            while(rs.next()){
                cli = new Cliente();
                
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));

                lista.add(cli);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            GER.Desconectar(c);
        }

    }
    
    
    @Override
    public ArrayList<Cliente> Lista_os_ClientePlca(String placa) throws ErroConexaoSQL, ErroDao{
        c =GER.Conectar();
        String sql="SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
"INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA LIKE '2')\n" +
"INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (AT.PLACA LIKE ?)";
       
        
        try {         
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, placa);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Cliente> lista = new ArrayList();
            Cliente cli= null;
            
            while(rs.next()){
                cli = new Cliente();
                
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));
                
                lista.add(cli);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            GER.Desconectar(c);
        }  

    }
    
    @Override
    public ArrayList<Cliente> Lista_Cli_Situa_OSCodigo(String cli_codigo,String situacao_OS) throws ErroConexaoSQL, ErroDao{
        c =GER.Conectar();
        String sql="SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
"INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA = '2')\n" +
"INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (CLI.CLIENTE = ?) AND (ORS.SITUACAO_OS = ?)  \n" +
"ORDER BY ORS.NRO_OS DESC";
       
        
        try {         
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, cli_codigo);
            pstm.setString(2, situacao_OS);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Cliente> lista = new ArrayList();
            Cliente cli= null;
            
            while(rs.next()){
                cli = new Cliente();
                
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));
                
                lista.add(cli);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            GER.Desconectar(c);
        }  
    }
      
     public ArrayList<Cliente> Lista_Cli_Situa_OSNome(String cli_Nome,String situacao_OS) throws ErroConexaoSQL, ErroDao{
       c =GER.Conectar();
        String sql="SELECT CLI.CLIENTE,CLI.NOME,AT.CHASSI,AT.PLACA,ORS.NRO_OS,ORS.SITUACAO_OS,ORS.DTA_EMISSAO,ORS.DTA_PROMETIDA,ORS.DTA_ENCERRAMENTO,ORS.OBSERVACAO FROM OFI_ORDEM_SERVICO AS ORS \n" +
"INNER JOIN OFI_ATENDIMENTO AS AT ON(AT.CONTATO = ORS.CONTATO) AND (ORS.EMPRESA = '2') AND (AT.EMPRESA = '2')\n" +
"INNER JOIN FAT_CLIENTE AS CLI ON(CLI.CLIENTE = AT.CLIENTE_EMISSAO_NF ) AND (CLI.NOME LIKE ?) AND (ORS.SITUACAO_OS = ?)  \n" +
"ORDER BY ORS.NRO_OS DESC";
       
        
        try {         
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, cli_Nome);
            pstm.setString(2, situacao_OS);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Cliente> lista = new ArrayList();
            Cliente cli= null;
            
            while(rs.next()){
                cli = new Cliente();
                
                cli.setCodigo(rs.getString("CLIENTE"));
                cli.setNome(rs.getString("NOME"));
                cli.setChassi(rs.getString("CHASSI"));
                cli.setPlaca(rs.getString("PLACA"));
                cli.setOrdem_OS(rs.getString("NRO_OS"));
                cli.setSituacao_os(rs.getString("SITUACAO_OS"));
                cli.setDataEmissao(rs.getDate("DTA_EMISSAO"));
                cli.setDataPrevista(rs.getDate("DTA_PROMETIDA"));
                cli.setDataentrega(rs.getDate("DTA_ENCERRAMENTO"));
                cli.setDescricao(rs.getString("OBSERVACAO"));
                
                lista.add(cli);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            GER.Desconectar(c);
        }  
     }
    
            
}
