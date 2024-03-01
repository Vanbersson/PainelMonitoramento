/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Basica.Mecanico;
import Excecao.ErroConexaoSQL;
import Excecao.ErroDao;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public interface IDMecanico {
    
    public ArrayList<Mecanico> ListaServico_OS(String Nro_OS) throws ErroConexaoSQL,ErroDao;
    
    public ArrayList<Mecanico> ListaNome_Mecanico() throws ErroConexaoSQL,ErroDao;
    
    public ArrayList<Mecanico> ListaTodos_Servico_Mecanico(String codMecanico) throws ErroConexaoSQL,ErroDao;
    
    public ArrayList<Mecanico> Lista_Mecanico_Situacao(String codMecanico,String situacao) throws ErroConexaoSQL,ErroDao;
    
    public ArrayList<Mecanico> Lista_Ser_Situacao(String situacao) throws ErroConexaoSQL,ErroDao;
    
}
