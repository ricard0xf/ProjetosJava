/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;
import Envio.AtualizarEnvio;
import Envio.CadastrarEnvio;
import Envio.DeletarEnvio;
import Envio.LogarEnvio;
import Envio.LogoutEnvio;
import Envio.VisualizarEnvio;
import Results.AtualizarCandidato;
import Results.CadastroResultado;
import Results.DeleteResultado;
import Results.LoginResultado;
import Results.LogoutResultado;
import Results.VisualizarResultado;
import java.io.*;
import Service.UserActions;

import java.net.*;
import java.sql.SQLException;
import com.google.gson.Gson;
import entidades.Usuario;
import entidades.Routes;


public class Server extends Thread {
    
    private ServerSocket serverSocket;

    public Server(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    public void run() {
    try {
        while (true) { 
            System.out.println("Aguardando conexão do cliente...");
            Socket socket = serverSocket.accept(); 
            System.out.println("Cliente conectado.");

            try {
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        int porta = 22222; // Porta do servidor
        try {
            Thread servidor = new Server(porta);
            servidor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

   
    public void run() {
        try {
            // Recebe a string JSON
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String json = in.readLine();
            System.out.println("JSON recebido: " + json);

            Gson gson = new Gson();
            Routes rota = gson.fromJson(json, Routes.class);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            switch (rota.operacao) {
                case "cadastrarCandidato" :
                    CadastrarEnvio cadastroEnvio = gson.fromJson(json, CadastrarEnvio.class);
                    CadastroResultado cadastro = UserActions.cadastro(cadastroEnvio);
                    String jsonCadastro = gson.toJson(cadastro);
                    out.println(jsonCadastro);
                    break;

                case "loginCandidato" :
                    LogarEnvio loginEnvio = gson.fromJson(json, LogarEnvio.class);
                    LoginResultado login = UserActions.login(loginEnvio);
                    String jsonLogin = gson.toJson(login);
                    System.out.println(jsonLogin);
                    out.println(jsonLogin);
                    String usuarioLogado = login.token;
                    break;

                case "visualizarCandidato" :
                    VisualizarEnvio visualizarEnvio = gson.fromJson(json, VisualizarEnvio.class);
                    VisualizarResultado visualizar = UserActions.visualizar(visualizarEnvio);
                    String jsonVisualizar = gson.toJson(visualizar);
                    out.println(jsonVisualizar);
                    break;

                case "atualizarCandidato" :
                    AtualizarEnvio atualizarEnvio = gson.fromJson(json, AtualizarEnvio.class);
                    AtualizarCandidato atualizar = UserActions.atualizarUsuario(atualizarEnvio);
                    String jsonAtualizar = gson.toJson(atualizar);
                    out.println(jsonAtualizar);
                    break;

                case "apagarCandidato" :
                    DeletarEnvio deletarEnvio = gson.fromJson(json, DeletarEnvio.class);
                    DeleteResultado deletar = UserActions.deletarUsuario(deletarEnvio);
                    String jsonDeletar = gson.toJson(deletar);
                    out.println(jsonDeletar);
                    break;
                
                case "logout" :
                    LogoutEnvio logoutEnvio = gson.fromJson(json, LogoutEnvio.class);
                    LogoutResultado logout = UserActions.realizarLogout(logoutEnvio);
                    String jsonLogout = gson.toJson(logout);
                    out.println(jsonLogout);
                    usuarioLogado = "";
                    break;
                    
            }

            //socket.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}

