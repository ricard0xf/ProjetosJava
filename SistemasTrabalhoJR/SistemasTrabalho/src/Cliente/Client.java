/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import Envio.*;
import Results.*;
import appView.LoginTela;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entidades.Usuario;
import entidades.Routes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jose.ricardo
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    String usuarioLogado = "";
    
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static Scanner scanner;
       
    public static void main(String[] args) {
        Client client = new Client();
        client.app();
    }

    public void app() {
        
        try {
            scanner = new Scanner(System.in);

            // Solicita ao usuário o endereço do servidor e a porta
            System.out.print("Digite o endereço do servidor (host): ");
            String serverHost = scanner.nextLine();

            System.out.print("Digite a porta do servidor: ");
            int serverPort = Integer.parseInt(scanner.nextLine());

            // Cria o socket uma vez e mantém aberto
            socket = new Socket(serverHost, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Loop para continuar recebendo comandos do usuário
            while (true) {
                System.out.println("Escolha a função que deseja chamar:");
                System.out.println("1. Realizar Login");
                System.out.println("2. Realizar Cadastro");
                System.out.println("3. Visualizar informações de usuário");
                System.out.println("4. Atualizar informações");
                System.out.println("5. Remover usuário");
                System.out.println("6. Realizar Logout");
                System.out.println("7. Sair");

                System.out.print("Opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        realizarLogin(scanner, out, in);
                        break;
                    case 2:
                        realizarCadastro(scanner, out, in);
                        break;
                    case 3:
                        visualizarCandidato(scanner, out, in);
                        break;
                    case 4:
                        atualizarCandidato(scanner, out, in);
                        break;
                    case 5:
                        removerCandidato(scanner, out, in);
                        break;
                    case 6:
                        realizarLogout(scanner, out, in);
                        break;
                    case 7:
                        encerrarConexao();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }

        } catch (IOException ex) {
            System.out.println("Erro de I/O: " + ex.getMessage());
        }
        /*finally {
            // Fecha o socket, o BufferedReader e o PrintWriter
            try {
                if (socket != null) {
                    socket.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }*/
    }

    private  void realizarLogin(Scanner scanner, PrintWriter out, BufferedReader in) throws IOException {
        Gson gson = new Gson();
        LogarEnvio rota = new LogarEnvio();
        rota.operacao = "loginCandidato";

        System.out.print("Digite o e-mail: ");
        rota.email = scanner.nextLine();

        System.out.print("Digite a senha: ");
        rota.senha = scanner.nextLine();

        String jsonLogin = gson.toJson(rota);

        out.println(jsonLogin);
        System.out.println("JSON enviado para o servidor: " + jsonLogin);

        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);

        LoginResultado result = gson.fromJson(resposta, LoginResultado.class);
        usuarioLogado = result.token;
        //System.out.println("aa" + usuarioLogado);
        handleLoginResult(result);
    }
    
    private static void realizarCadastro(Scanner scanner, PrintWriter out, BufferedReader in) throws IOException {
        Gson gson = new Gson();
        CadastrarEnvio rota = new CadastrarEnvio();
        rota.operacao = "cadastrarCandidato";
        
        System.out.print("Digite o nome: ");
        rota.nome = scanner.nextLine();
        
        System.out.print("Digite o e-mail: ");
        rota.email = scanner.nextLine();
        
        System.out.print("Digite a senha: ");
        rota.senha = scanner.nextLine();
        
        String jsonCadastro = gson.toJson(rota);
        
        out.println(jsonCadastro);
        System.out.println("JSON enviado para o servidor: " + jsonCadastro);

        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);
        
        CadastroResultado result = gson.fromJson(resposta, CadastroResultado.class);
        handleCadastroResult(result);
    }
    
    private static void visualizarCandidato(Scanner scanner, PrintWriter out, BufferedReader in) throws IOException {
        Gson gson = new Gson();
        VisualizarEnvio rota = new VisualizarEnvio();
        rota.operacao = "visualizarCandidato";
        
        System.out.print("Digite o e-mail: ");
        rota.email = scanner.nextLine();
        
        String jsonVisualizar = gson.toJson(rota);
        
        out.println(jsonVisualizar);
        System.out.println("JSON enviado para o servidor: " + jsonVisualizar);

        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);
        
        VisualizarResultado result = gson.fromJson(resposta, VisualizarResultado.class);
        handleVisualizarResult(result);
        
    }
    
    private static void atualizarCandidato(Scanner scanner, PrintWriter out, BufferedReader in) throws IOException {
        Gson gson = new Gson();
        AtualizarEnvio rota = new AtualizarEnvio();
        rota.operacao = "atualizarCandidato";
        
        System.out.print("Digite o e-mail do usuario a ser atualizado: ");
        rota.email = scanner.nextLine();
       
        System.out.print("Digite o novo nome: ");
        rota.nome = scanner.nextLine();
        
        System.out.print("Digite a nova senha: ");
        rota.senha = scanner.nextLine();
        
        String jsonAtualizar = gson.toJson(rota);
        
        System.out.println(jsonAtualizar);
        
        out.println(jsonAtualizar);
        System.out.println("JSON enviado para o servidor: " + jsonAtualizar);

        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);
        
        AtualizarCandidato result = gson.fromJson(resposta, AtualizarCandidato.class);
        handleAtualizarResult(result);
        
    }
    
    private static void removerCandidato(Scanner scanner, PrintWriter out, BufferedReader in) throws IOException {
        Gson gson = new Gson();
        AtualizarEnvio rota = new AtualizarEnvio();
        rota.operacao = "apagarCandidato";
        
        System.out.print("Digite o e-mail do usuario a ser removido: ");
        rota.email = scanner.nextLine();
        
        String jsonRemover = gson.toJson(rota);
        
        System.out.println(jsonRemover);
        
        out.println(jsonRemover);
        System.out.println("JSON enviado para o servidor: " + jsonRemover);

        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);
        
        DeleteResultado result = gson.fromJson(resposta, DeleteResultado.class);
        handleRemoverResult(result);
        
    }
    
    private void realizarLogout(Scanner scanner, PrintWriter out, BufferedReader in) throws IOException {
        Gson gson = new Gson();
        LogoutEnvio rota = new LogoutEnvio();
        rota.operacao = "logout";
        System.out.println(usuarioLogado);
        rota.token = usuarioLogado;
        
        String jsonLogout = gson.toJson(rota);
        
        out.println(jsonLogout);
        System.out.println("JSON enviado para o servidor: " + jsonLogout);

        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);
        
        String usuarioLogado = "";
        
        LogoutResultado result = gson.fromJson(resposta, LogoutResultado.class);
        handleLogoutResult(result);
        
    }
    
    

    private static void handleLoginResult(LoginResultado result) {
        switch (result.status) {
            case 401:
                System.out.println(result.mensagem);
                break;
            case 200:
                System.out.println("Login efetuado com sucesso!");
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
    
    private static void handleCadastroResult(CadastroResultado result) {
        switch (result.status) {
            case 404:
                System.out.println(result.mensagem);
                break;
            case 201:
                System.out.println("Cadastro efetuado com sucesso!");
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
    
    private static void handleVisualizarResult(VisualizarResultado result) {
        switch (result.status) {
            case 404:
                System.out.println(result.mensagem);
                break;
            case 201:
                System.out.println("Informacoes visualizadas com sucesso!");
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
    
    private static void handleAtualizarResult(AtualizarCandidato result) {
        switch (result.status) {
            case 404:
                System.out.println(result.mensagem);
                break;
            case 201:
                System.out.println("Informacoes atualizadas com sucesso!");
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
    
    private static void handleRemoverResult(DeleteResultado result) {
        switch (result.status) {
            case 404:
                System.out.println(result.mensagem);
                break;
            case 201:
                System.out.println("Candidato removido com sucesso!");
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
    
    private static void handleLogoutResult(LogoutResultado result) {
        switch (result.status) {
            case 204:
                System.out.println("Logout realizado com sucesso!");
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }

    private static void encerrarConexao() {
        // Fecha o socket, o BufferedReader e o PrintWriter
        try {
            if (socket != null) {
                socket.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
}
