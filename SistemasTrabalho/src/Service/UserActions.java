/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import com.google.gson.Gson;

import DAO.DBConnect;
import DAO.DBActions;
import entidades.Routes;
import entidades.Usuario;

/**
 *
 * @author jose.ricardo
 */
public class UserActions {
    public static void cadastro(String nome, String email, String senha) throws SQLException {
        Gson gson = new Gson();
		Usuario usuarioCadastro = new Usuario(nome, email, senha);
		
		
		boolean validarEmail = validarEmail(usuarioCadastro.Email);
		boolean validarSenha = validarSenha(usuarioCadastro.Senha);
		
		
		if  (validarEmail && validarSenha) {
			
			DBActions.cadastro(usuarioCadastro);
		}else {
			System.out.println("Usuario ou senha incorretos");
		}

	}

	public static void login(String usuario) throws SQLException {
		Gson gson = new Gson();
		Usuario usuarioLogin = gson.fromJson(usuario, Usuario.class);
		DBActions.login(usuarioLogin);
	}

	public static Usuario visualizar(String email) throws SQLException {
		Gson gson = new Gson();
		Usuario usuario = DBActions.visualizarUsuario(email);
		System.out.println(usuario.Email + " " + usuario.Nome + " " + usuario.Senha);


	    // Enviando a resposta para o cliente
		return usuario;
	}

	public static void atualizarUsuario(String usuario) throws SQLException {
		Gson gson = new Gson();
		Usuario usuarioUpdate = gson.fromJson(usuario, Usuario.class);
		DBActions.atualizarUsuario(usuarioUpdate);
		
	}

	public static void deletarUsuario(String email) throws SQLException {
		Gson gson = new Gson();
		DBActions.deletarUsuario(email);

	}
	
	private static boolean validarEmail(String email) {
		
		 if (email.length() < 7 || email.length() > 50) {
	            return false;
	        }

	        // Divide o email em nome de usuário e domínio
	        String[] parts = email.split("@");
	        if (parts.length != 2) {
	            return false;
	        }

	        String username = parts[0];
	        String domain = parts[1];

	        // Verifica se o nome de usuário e o domínio atendem aos critérios
	        if (username.length() < 1 || domain.length() < 3) {
	            return false;
	        }

	        // Verifica se o domínio contém um ponto
	        if (!domain.contains(".")) {
	            return false;
	        }

	        // Verifica se o nome de usuário e o domínio contêm apenas caracteres alfanuméricos e alguns caracteres especiais permitidos
	        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.-_";
	        for (char c : username.toCharArray()) {
	            if (allowedCharacters.indexOf(c) == -1) {
	                return false;
	            }
	        }
	        for (char c : domain.toCharArray()) {
	            if (allowedCharacters.indexOf(c) == -1) {
	                return false;
	            }
	        }

	        return true;
	    }
	
		private static boolean validarSenha (String senha) {
			
			if (senha.length() < 3 || senha.length() > 8) {
	            return false;
	        }

	        // Verifica se todos os caracteres são dígitos
	        for (int i = 0; i < senha.length(); i++) {
	            if (!Character.isDigit(senha.charAt(i))) {
	                return false;
	            }
	        }

	        return true;
	    }

}
