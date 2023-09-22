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
public class LerCsv {
    public static void main(String[] args) throws IOException {
        ArqObj arquivoobj = new ArqObj("/home/cassio/obj.obj");
        arquivoobj.abreGravaArqObj();
        
        ArqTexto arquivo = new ArqTexto("/home/cassio/text.txt");
        arquivo.abreArqLeitura();
        String linha = arquivo.lerLinha();
        while(linha!=null){
            arquivoobj.gravaArqObj(new Vendedor(linha));
            linha = arquivo.lerLinha();
        }
        arquivoobj.fechaArqObj();
        arquivo.fechaArqLeitura();
    }
}
