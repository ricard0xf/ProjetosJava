/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generics_aula2;

/**
 *
 * @author cassio
 */
public class Lista2Teste {
    public static void main(String[] args) {
        Fila<Produto> fila = new Fila<>();
        fila.insert(new Produto(0,"P0", 0.0));
        fila.insert(new Produto(1,"P1", 1.0));
        System.out.println(fila.first());
        System.out.println(fila.last());
        System.out.println(fila.size());
        
        Fila<Cliente> filac = new Fila<>();
        filac.insert(new Cliente(0, "José Ricardo"));
        filac.insert(new Cliente(1, "Bernardo"));
        filac.insert(new Cliente(2 , "Cassio"));
        System.out.println(filac.first());
        
    }
}
