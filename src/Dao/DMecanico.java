/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Basica.Mecanico;
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
public class DMecanico implements IDMecanico{

    private final IGere_Conexao CONEXAO;
    private Connection c;
    
    public DMecanico(){
        CONEXAO = Gere_Conexao.getinstancia();
    }
    
    @Override
    public ArrayList<Mecanico> ListaServico_OS(String Nro_OS) throws ErroConexaoSQL, ErroDao {
        
        String sql="SELECT SO.MECANICO,ME.NOME,SO.NRO_OS,SO.SERVICO,SO.DESCRICAO,SO.TIPO_SERVICO,SO.SITUACAO,SO.QUANTIDADE FROM OFI_SERVICO_OS AS SO \n" +
"INNER JOIN OFI_SERVICO AS SER ON(SO.SERVICO = SER.SERVICO) AND (SO.EMPRESA = '2') AND (SER.EMPRESA = '2') AND (SO.NRO_OS = ?)\n" +
"LEFT JOIN OFI_MECANICO AS ME ON (ME.MECANICO = SO.MECANICO ) AND (ME.EMPRESA = '2')";
        c=CONEXAO.Conectar();
        
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, Nro_OS);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Mecanico> lista = new ArrayList<>();
            Mecanico ser;
            
            while(rs.next()){
                ser = new Mecanico();
                
                ser.setMecanico(rs.getString("MECANICO"));
                ser.setNomeMecanico(rs.getString("NOME"));
                ser.setNro_os(rs.getString("NRO_OS"));
                ser.setCod_servico(rs.getString("SERVICO"));
                ser.setTipo_os(rs.getString("TIPO_SERVICO"));
                ser.setSituacao(rs.getString("SITUACAO"));
                ser.setQtdHora(rs.getString("QUANTIDADE"));
                ser.setDes_servico(rs.getString("DESCRICAO"));
                
                lista.add(ser);
            }
            
            
            return lista;
            
            
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            CONEXAO.Desconectar(c);
        }
        
    }

    @Override
    public ArrayList<Mecanico> ListaNome_Mecanico() throws ErroConexaoSQL, ErroDao {
         c =CONEXAO.Conectar();
        String sql="SELECT MEC.MECANICO,MEC.NOME FROM OFI_MECANICO AS MEC WHERE EMPRESA = 2 ORDER BY MEC.NOME";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
           
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Mecanico> lista = new ArrayList();
            Mecanico mec;
            
            while(rs.next()){
                mec = new Mecanico();
                mec.setMecanico(rs.getString("MECANICO"));
                mec.setNomeMecanico(rs.getString("NOME"));
                
                lista.add(mec);
            }
                     
        return lista;
        
        }catch(SQLException ex) {
            throw new ErroDao(ex);
        }finally{
          CONEXAO.Desconectar(c);
        }
    
    }
    
    @Override
    public ArrayList<Mecanico> ListaTodos_Servico_Mecanico(String codMecanico) throws ErroConexaoSQL,ErroDao{
        c =CONEXAO.Conectar();
       String sql = "SELECT SO.MECANICO,ME.NOME,SO.NRO_OS,SO.SERVICO,SO.DESCRICAO,SO.TIPO_SERVICO,SO.SITUACAO,SO.QUANTIDADE FROM OFI_SERVICO_OS AS SO \n" +
"INNER JOIN OFI_SERVICO AS SER ON(SO.SERVICO = SER.SERVICO) AND (SO.EMPRESA LIKE '2') AND (SER.EMPRESA LIKE '2')\n" +
"INNER JOIN OFI_MECANICO AS ME ON (ME.MECANICO = SO.MECANICO ) AND (ME.EMPRESA LIKE '2') AND (ME.MECANICO = ?) ORDER BY SO.NRO_OS DESC";
       
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, codMecanico);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Mecanico> lista = new ArrayList<>();
            Mecanico ser;
            
            while(rs.next()){
                ser = new Mecanico();
                
                ser.setMecanico(rs.getString("MECANICO"));
                ser.setNomeMecanico(rs.getString("NOME"));
                ser.setNro_os(rs.getString("NRO_OS"));
                ser.setCod_servico(rs.getString("SERVICO"));
                ser.setTipo_os(rs.getString("TIPO_SERVICO"));
                ser.setSituacao(rs.getString("SITUACAO"));
                ser.setQtdHora(rs.getString("QUANTIDADE"));
                ser.setDes_servico(rs.getString("DESCRICAO"));
                
                lista.add(ser);  
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            CONEXAO.Desconectar(c);
        }
        
    }
        
    @Override
    public ArrayList<Mecanico> Lista_Mecanico_Situacao(String codMecanico,String situacao) throws ErroConexaoSQL,ErroDao{
        c =CONEXAO.Conectar();
       String sql = "SELECT SO.MECANICO,ME.NOME,SO.NRO_OS,SO.SERVICO,SO.DESCRICAO,SO.TIPO_SERVICO,SO.SITUACAO,SO.QUANTIDADE FROM OFI_SERVICO_OS AS SO\n" +
"INNER JOIN OFI_SERVICO AS SER ON(SO.SERVICO = SER.SERVICO) AND (SO.EMPRESA = '2') AND (SER.EMPRESA = '2')\n" +
"INNER JOIN OFI_MECANICO AS ME ON (ME.MECANICO = SO.MECANICO ) AND (ME.EMPRESA = '2') AND (ME.MECANICO = ?) AND (SO.SITUACAO = ?) \n" +
"ORDER BY SO.NRO_OS DESC";
       
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, codMecanico);
            pstm.setString(2, situacao);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Mecanico> lista = new ArrayList<>();
            Mecanico ser;
            
            while(rs.next()){
                ser = new Mecanico();
                
                ser.setMecanico(rs.getString("MECANICO"));
                ser.setNomeMecanico(rs.getString("NOME"));
                ser.setNro_os(rs.getString("NRO_OS"));
                ser.setCod_servico(rs.getString("SERVICO"));
                ser.setTipo_os(rs.getString("TIPO_SERVICO"));
                ser.setSituacao(rs.getString("SITUACAO"));
                ser.setQtdHora(rs.getString("QUANTIDADE"));
                ser.setDes_servico(rs.getString("DESCRICAO"));
                
                lista.add(ser);  
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            CONEXAO.Desconectar(c);
        }
    }
    
    @Override
    public ArrayList<Mecanico> Lista_Ser_Situacao(String situacao) throws ErroConexaoSQL,ErroDao{
         c =CONEXAO.Conectar();
       String sql = "SELECT SO.MECANICO,ME.NOME,SO.NRO_OS,SO.SERVICO,SO.DESCRICAO,SO.TIPO_SERVICO,SO.SITUACAO,SO.QUANTIDADE FROM OFI_SERVICO_OS AS SO\n" +
"INNER JOIN OFI_SERVICO AS SER ON(SO.SERVICO = SER.SERVICO) AND (SO.EMPRESA = '2') AND (SER.EMPRESA = '2')\n" +
"INNER JOIN OFI_MECANICO AS ME ON (ME.MECANICO = SO.MECANICO ) AND (ME.EMPRESA = '2') AND (SO.SITUACAO = ?) \n" +
"ORDER BY SO.NRO_OS DESC";
       
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, situacao);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<Mecanico> lista = new ArrayList<>();
            Mecanico ser;
            
            while(rs.next()){
                ser = new Mecanico();
                
                ser.setMecanico(rs.getString("MECANICO"));
                ser.setNomeMecanico(rs.getString("NOME"));
                ser.setNro_os(rs.getString("NRO_OS"));
                ser.setCod_servico(rs.getString("SERVICO"));
                ser.setTipo_os(rs.getString("TIPO_SERVICO"));
                ser.setSituacao(rs.getString("SITUACAO"));
                ser.setQtdHora(rs.getString("QUANTIDADE"));
                ser.setDes_servico(rs.getString("DESCRICAO"));
                
                lista.add(ser);  
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new ErroDao(ex);
        }finally{
            CONEXAO.Desconectar(c);
        }
    }
}
