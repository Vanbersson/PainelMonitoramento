/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basica;

import com.towel.el.annotation.Resolvable;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Cliente {
    @Resolvable(colName = "Código")
    private String codigo;
    @Resolvable(colName = "Cliente")
    private String nome;
    @Resolvable(colName = "Placa")
    private String placa;
    @Resolvable(colName = "Data Emissão")
    private Date dataEmissao;
    @Resolvable(colName = "Data Prevista")
    private Date dataPrevista;
    @Resolvable(colName = "Data Entrega")
    private Date dataentrega;
    @Resolvable(colName = "Serviço")
    private String servico;
    @Resolvable(colName = "Nro. O.S.")
    private String ordem_OS;
    @Resolvable(colName = "Situação")
    private String situacao_os;
    @Resolvable(colName = "Chassi")
    private String chassi;
    @Resolvable(colName = "Observação")
    private String descricao;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the dataPrevista
     */
    public Date getDataPrevista() {
        return dataPrevista;
    }

    /**
     * @param dataPrevista the dataPrevista to set
     */
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    /**
     * @return the dataentrega
     */
    public Date getDataentrega() {
        return dataentrega;
    }

    /**
     * @param dataentrega the dataentrega to set
     */
    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }

    /**
     * @return the servico
     */
    public String getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(String servico) {
        this.servico = servico;
    }

    /**
     * @return the ordem_OS
     */
    public String getOrdem_OS() {
        return ordem_OS;
    }

    /**
     * @param ordem_OS the ordem_OS to set
     */
    public void setOrdem_OS(String ordem_OS) {
        this.ordem_OS = ordem_OS;
    }

    /**
     * @return the situacao_os
     */
    public String getSituacao_os() {
        return situacao_os;
    }

    /**
     * @param situacao_os the situacao_os to set
     */
    public void setSituacao_os(String situacao_os) {
        this.situacao_os = situacao_os;
    }

    /**
     * @return the chassi
     */
    public String getChassi() {
        return chassi;
    }

    /**
     * @param chassi the chassi to set
     */
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
