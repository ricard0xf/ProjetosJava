/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generics_aula2;

/**
 *
 * @author cassio
 */
public class Cliente {
    private int codigo;
    private String nome;

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
    
    public Cliente(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }
    
}
