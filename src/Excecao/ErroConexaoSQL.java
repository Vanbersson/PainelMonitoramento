/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excecao;

/**
 *
 * @author Administrador
 */
public class ErroConexaoSQL extends Exception{
   public ErroConexaoSQL(){
       
   }
   public ErroConexaoSQL(String txt){
       super(txt);
   }
   public ErroConexaoSQL(Exception ex){
       super(ex);
   }
   
   
   
}
