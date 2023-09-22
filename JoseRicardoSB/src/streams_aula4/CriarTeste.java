/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package streams_aula4;

import java.io.IOException;

/**
 *
 * @author cassio
 */
public class CriarTeste {
    public static void main(String[] args) throws IOException {
        ArqTexto arquivo = new ArqTexto("/home/cassio/text.txt");
        arquivo.abreArqLeitura();
        System.out.println(arquivo.lerLinha());
        arquivo.fechaArqLeitura();
        
        arquivo.abreArqGravacao();
        arquivo.gravarLinha("123456789123456789123456789123456789");
        arquivo.fechaArqGravacao();
    }
}
