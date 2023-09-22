/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams_aula4;

/**
 *
 * @author Cassio
 */
import java.io.*;

public class ArqObj {

    ObjectInputStream arqLeitura;
    ObjectOutputStream arqGravacao;
    String nomeArq;

    public ArqObj(String nome) {
        this.nomeArq = nome;
    }

    public Object lerArqObj() throws IOException {
        Object registro = null;
        try {
            registro = arqLeitura.readObject();
        } catch (IOException e1) {
            throw new IOException("Erro na Leitura " + e1.toString());
        } catch (ClassNotFoundException cnfEx) {
            throw new IOException("Objeto nao pode ser criado ");
        } finally {
            return registro;
        }
    }

    public void gravaArqObj(Object registro) throws IOException {
        try {
            arqGravacao.writeObject(registro);
        } catch (IOException e1) {
            throw new IOException("Erro na gravacao " + e1.toString());
        }
    }

    public void abreLerArqObj() throws IOException {
        try {
            arqLeitura = new ObjectInputStream(new FileInputStream(nomeArq));
        } catch (FileNotFoundException e1) {
            throw new IOException(nomeArq + "não encontrado!");
        }
    }

    public void abreGravaArqObj() throws IOException {
        try {
            arqGravacao = new ObjectOutputStream(new FileOutputStream(nomeArq));
        } catch (FileNotFoundException e1) {
            throw new IOException(nomeArq + "não encontrado!");
        }
    }

    public void fechaArqObj() throws IOException {
        try {
            if (this.arqLeitura != null) 
                arqLeitura.close();
           
            if (this.arqGravacao != null)
                arqGravacao.close();
          
        } catch (IOException e1) {
            throw new IOException("Erro no fechamento dos arquivos, \n"
                    + e1.getLocalizedMessage());
        }
    }

}