/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Usuario;
/**
 *
 * @author jose.ricardo
 */
public class DBActions {
    /*
	 * public static void main(String[] args) throws SQLException { try { Connection
	 * conexao = new ConexaoDB().getConnection(); } catch (Exception e) {
	 * System.out.println("nao deu"); }
	 * 
	 * }
	 */

	public static void cadastro(Usuario usuario) throws SQLException {
		Connection conexao = new DBConnect().getConnection();
		String sql = "INSERT INTO Candidato (Nome, Email, Senha) VALUES ('" + usuario.Nome + "', '" + usuario.Email
				+ "', '" + usuario.Senha + "')";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		conexao.close();

	}

	public static void login(Usuario usuario) throws SQLException {
		Connection conexao = new DBConnect().getConnection();
		String sql = "SELECT Email, Senha FROM Candidato where Email = '" + usuario.Email + "' AND senha = '"
				+ usuario.Senha + "' ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			System.out.println("possui");
		} else {
			System.out.println("nao possui");
		}

		conexao.close();

	}

	public static Usuario visualizarUsuario(String email) throws SQLException {
		Connection conexao = new DBConnect().getConnection();
		String sql = "SELECT * FROM Candidato where Email = '" + email + "' ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement = conexao.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		System.out.println("to aqui");

		Usuario usuario = new Usuario();

		if (result.next()) {
			String emailUsuario = result.getString("Email");
			// System.out.println(emailUsuario);
			String nomeUsuario = result.getString("Nome");
			String senhaUsuario = result.getString("Senha");
			usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario);

		} else {
			System.out.println("erro");
		}

		conexao.close();
		return usuario;

	}

	public static void atualizarUsuario(Usuario usuario) throws SQLException {
		Connection conexao = new DBConnect().getConnection();
		String sql = "UPDATE candidato SET Nome = '" + usuario.Nome + "', Senha = '" + usuario.Senha
				+ "' WHERE Email = '" + usuario.Email + "'  ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		conexao.close();
	}

	public static void deletarUsuario(String email) throws SQLException {
		Connection conexao = new DBConnect().getConnection();
		String sql = "DELETE FROM Candidato WHERE Email = '" + email + "'";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.execute();
		conexao.close();

	}

}
