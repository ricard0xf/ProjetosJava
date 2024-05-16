/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.io.Serializable;
/**
 *
 * @author jose.ricardo
 */
public class Usuario implements Serializable{
    public String Nome;
    public String Email;
    public String Senha;

    public Usuario(String nome, String email, String senha) {
        this.Nome = nome;
        this.Email = email;
        this.Senha = senha;
    }

    public Usuario( String email, String senha) {
        this.Email = email;
        this.Senha = senha;
    }

    public Usuario() {

    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    

}
