/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jose.ricardo
 */
public class DBConnect {
    public Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/SistemaRH", "root", "");
		return conexao;
	}

}
