/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generics_aula2;

/**
 *
 * @author cassio
 */
public class Produto {
    private int codigo;
    private String descricao;
    private double valorUnitario;

    @Override
    public String toString() {
        return codigo + " - " + descricao + " - R$" + valorUnitario;
    }
    
    public Produto(int codigo, String descricao, double valorUnitario){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }
    
   
}
