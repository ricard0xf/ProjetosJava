/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author jose.ricardo
 */
public class Empresa extends Usuario {
    public Empresa(String nome, String email, String senha) {
		super(nome, email, senha);
		// TODO Auto-generated constructor stub
	}
	public String ramo;
	public String descricao;

}
