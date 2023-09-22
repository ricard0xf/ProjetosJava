/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package streams_aula4;

import java.io.Serializable;

/**
 *
 * @author cassio
 */
public class Vendedor implements Serializable{

    private int codigo;
    private String nome;
    private double venda;
    private double comissao;

    public Vendedor(int codigo, String nome, double venda, double comissao) {
        this.codigo = codigo;
        this.nome = nome;
        this.venda = venda;
        this.comissao = comissao;
    }

    public Vendedor(String linha) {
        this.codigo = Integer.parseInt(linha.substring(0, linha.indexOf(",")));
        linha = linha.substring(linha.indexOf(",") + 1);
        this.nome = linha.substring(0, linha.indexOf(","));
        linha = linha.substring(linha.indexOf(",") + 1);
        this.venda = Double.parseDouble(linha.substring(0, linha.indexOf(",")));
        linha = linha.substring(linha.indexOf(",") + 1);
        this.comissao = Double.parseDouble(linha.substring(0));
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - " + venda + " - " + comissao;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
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
     * @return the venda
     */
    public double getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(double venda) {
        this.venda = venda;
    }

    /**
     * @return the comissao
     */
    public double getComissao() {
        return comissao;
    }

    /**
     * @param comissao the comissao to set
     */
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
}
