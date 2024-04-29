/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entidades.Usuario;
import entidades.Routes;
/**
 *
 * @author jose.ricardo
 */
public class Client {
    public static void main(String[] args) {
        String servidorHost = "localhost"; // Endereço IP do servidor
        int servidorPorta = 12345; // Porta do servidor

        try {
            Socket socket = new Socket(servidorHost, servidorPorta);

            // Objeto a ser enviado
            Usuario teste = new Usuario("Joao2", "cleber@hotmail.com", "123");
            String email = "cleber@hotmail.com";

            // Convertendo o objeto em JSON
            Gson gson = new Gson();
       
            
            Routes<Usuario> operacao = new Routes<>("cadastrarCandidato", teste);
            //Routes<Usuario> operacao = new Routes<>("loginCandidato", teste); //testando
            //Routes rota = new Routes<String>("visualizarCandidato", email);
            //Routes rota = new Routes<Usuario>("atualizarCandidato", teste);
            //Routes rota = new Routes<String>("deletarUsuario", email);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("operacao", operacao.operacao);

            // Adicionando campos do usuário diretamente no JSON
            Usuario usuario = operacao.dado;
            jsonObject.addProperty("Nome", usuario.getNome());
            jsonObject.addProperty("Email", usuario.getEmail());
            jsonObject.addProperty("Senha", usuario.getSenha());
            System.out.println(jsonObject);
            String json1 = gson.toJson(jsonObject);

            // Enviando o JSON
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json1);
            System.out.println("JSON enviado para o servidor.");

            // Fechando o socket
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String resposta = in.readLine();
            System.out.println("Resposta do servidor: " + resposta);
            
            socket.close();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
