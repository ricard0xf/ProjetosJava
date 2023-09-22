/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enum_aula3;

/**
 *
 * @author cassio
 */
public enum Bebida {
    AGUA("Água",2.5),
    COCA("Coca-cola", 5.0);
    
    private String nome;
    private double preco;
    
    Bebida(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - R$" + preco;
    }
    
    
}
