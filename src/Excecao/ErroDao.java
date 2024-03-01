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
public class ErroDao extends Exception {
 
    public ErroDao(){
        
    }
    
    public ErroDao(String txt){
        super(txt);
    }
    
    public ErroDao(Exception ex){
        super(ex);
    }
}
