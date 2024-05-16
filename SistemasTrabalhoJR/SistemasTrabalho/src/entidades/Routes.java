/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author jose.ricardo
 */
public class Routes<T> {
    public String operacao;
	public T dado;
	
	public Routes(String operacao, T dado) {
		super();
		this.operacao = operacao;
		this.dado = dado;
	}	
}
