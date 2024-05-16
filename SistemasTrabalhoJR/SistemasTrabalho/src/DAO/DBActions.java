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

	public static boolean login(Usuario usuario) throws SQLException {
		Connection conexao = new DBConnect().getConnection();
		String sql = "SELECT Email, Senha FROM Candidato where Email = '" + usuario.Email + "' AND senha = '"
				+ usuario.Senha + "' ";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			System.out.println("possui");
                        return true;
		} else {
			System.out.println("nao possui");
                        return false;
		}
                
                

		

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
                //System.out.println(usuario);
		return usuario;

	}

	public static boolean atualizarUsuario(Usuario usuario) throws SQLException {
            Connection conexao = new DBConnect().getConnection();
            //System.out.println(usuario.Email);
            String sqlVerUser = "SELECT Email FROM Candidato where Email = '" + usuario.Email + "'";
            PreparedStatement statement1 = conexao.prepareStatement(sqlVerUser);
            ResultSet result = statement1.executeQuery();
            
            if (result.next()) {
                String sql = "UPDATE candidato SET Nome = '" + usuario.Nome + "', Senha = '" + usuario.Senha
				+ "' WHERE Email = '" + usuario.Email + "'  ";
                PreparedStatement statement2 = conexao.prepareStatement(sql);
                statement2.execute();
                conexao.close();
                return true;

            } else {
		System.out.println("erro");
                return false;
                
            }
            
        }

	public static boolean deletarUsuario(String email) throws SQLException {
            var isUsuario = visualizarUsuario(email);

            if (isUsuario.Nome == null) {
                return false;
            }
            Connection conexao = new DBConnect().getConnection();
            String sql = "DELETE FROM Candidato WHERE Email = '" + email + "'";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.execute();
            conexao.close();
            return true;

	}

}
