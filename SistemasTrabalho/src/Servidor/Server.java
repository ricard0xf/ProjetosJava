/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;
import java.io.*;
import Service.UserActions;

import java.net.*;
import java.sql.SQLException;
import com.google.gson.Gson;
import entidades.Usuario;
import entidades.Routes;
/**
 *
 * @author jose.ricardo
 */
public class Server extends Thread{
    private ServerSocket serverSocket;

	public Server(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	public void run() {
		try {
			boolean continuar = true;
			while (continuar) {
				System.out.println("Aguardando conexão do cliente...");
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado.");

				// Recebendo a string JSON
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String json = in.readLine();
				System.out.println("JSON recebido: " + json);
				
				if (json.equals("exit")) {
                    continuar = false;
				} else {
					Gson gson = new Gson();
					Routes<Usuario> operacao = gson.fromJson(json, Routes.class);
                                        System.out.println(operacao.dado);

					switch (operacao.operacao) {
					//case "cadastrarCandidato" : UserActions.cadastro(operacao.dado.Nome(), operacao.dado.Email(), operacao.dado.Senha());
					//	break;

					case "loginCandidato" : UserActions.login(operacao.dado.toString());
					break;

					case "visualizarCandidato" :
						Usuario usuario = UserActions.visualizar(operacao.dado.toString());
						System.out.println(usuario.Email + " " + usuario.Nome + " " + usuario.Senha);
						String jsonResponse = gson.toJson(usuario);

					    // Enviando a resposta para o cliente
					    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					    out.println(jsonResponse);
					    break;
					 
					case "atualizarCandidato" : UserActions.atualizarUsuario(operacao.dado.toString());
						break;
						
					case "deletarUsuario" : UserActions.deletarUsuario(operacao.dado.toString());
						break;
					}
				}

				
				
				socket.close();
			}

			// Fechando o socket

			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int porta = 12345; // Porta do servidor
		try {
			Thread servidor = new Server(porta);
			servidor.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
