/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basica;

import com.towel.el.annotation.Resolvable;

/**
 *
 * @author Administrador
 */
public class Mecanico {
    @Resolvable(colName = "Mecânico")
    private String mecanico;
    @Resolvable(colName = "Nome")
    private String nomeMecanico;
    @Resolvable(colName = "Nro_OS")
    private String nro_os;
    @Resolvable(colName = "Código Serviço")
    private String cod_servico;
    @Resolvable(colName = "Tipo serviço")
    private String tipo_os;
    @Resolvable(colName = "Situação")
    private String situacao;
    @Resolvable(colName = "Tempo Serviço")
    private String qtdHora;
    @Resolvable(colName = "Descrição")
    private String des_servico;

    /**
     * @return the mecanico
     */
    public String getMecanico() {
        return mecanico;
    }

    /**
     * @param mecanico the mecanico to set
     */
    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    /**
     * @return the nomeMecanico
     */
    public String getNomeMecanico() {
        return nomeMecanico;
    }

    /**
     * @param nomeMecanico the nomeMecanico to set
     */
    public void setNomeMecanico(String nomeMecanico) {
        this.nomeMecanico = nomeMecanico;
    }

    /**
     * @return the nro_os
     */
    public String getNro_os() {
        return nro_os;
    }

    /**
     * @param nro_os the nro_os to set
     */
    public void setNro_os(String nro_os) {
        this.nro_os = nro_os;
    }

    /**
     * @return the cod_servico
     */
    public String getCod_servico() {
        return cod_servico;
    }

    /**
     * @param cod_servico the cod_servico to set
     */
    public void setCod_servico(String cod_servico) {
        this.cod_servico = cod_servico;
    }

    /**
     * @return the tipo_os
     */
    public String getTipo_os() {
        return tipo_os;
    }

    /**
     * @param tipo_os the tipo_os to set
     */
    public void setTipo_os(String tipo_os) {
        this.tipo_os = tipo_os;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the qtdHora
     */
    public String getQtdHora() {
        return qtdHora;
    }

    /**
     * @param qtdHora the qtdHora to set
     */
    public void setQtdHora(String qtdHora) {
        this.qtdHora = qtdHora;
    }

    /**
     * @return the des_servico
     */
    public String getDes_servico() {
        return des_servico;
    }

    /**
     * @param des_servico the des_servico to set
     */
    public void setDes_servico(String des_servico) {
        this.des_servico = des_servico;
    }
}
