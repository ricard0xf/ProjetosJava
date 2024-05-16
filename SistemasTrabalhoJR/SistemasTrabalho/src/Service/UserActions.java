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
import Results.*;
import Envio.*;
import java.util.UUID;
/**
 *
 * @author jose.ricardo
 */
public class UserActions {
    public static CadastroResultado cadastro(CadastrarEnvio cadastroResponse) throws SQLException {
        Usuario usuarioCadastro = new Usuario(cadastroResponse.nome, cadastroResponse.email, cadastroResponse.senha);


        boolean validarEmail = validarEmail(usuarioCadastro.Email);
        System.out.println(usuarioCadastro.Email);
        boolean validarSenha = validarSenha(usuarioCadastro.Senha);

        CadastroResultado retorno = new CadastroResultado();
        if(validarEmail && validarSenha){
            DBActions.cadastro(usuarioCadastro);
            String token = gerarUID();
            System.out.println(token);
            retorno.status = 200;
            retorno.token = token;
        }else {
            retorno.status = 404;
            retorno.mensagem = "Erro durante o cadastro";
        }

        return retorno;
    }

    public static LoginResultado login(LogarEnvio loginResponse) throws SQLException {
        Usuario usuarioLogin = new Usuario(loginResponse.email, loginResponse.senha);
        boolean sucesso;
        sucesso = DBActions.login(usuarioLogin);
        LoginResultado retorno = new LoginResultado();
        if(sucesso){
            String token = gerarUID();
            retorno.status = 200;
            retorno.token = token;
        }else {
            retorno.status = 401;
            retorno.mensagem = "Login ou senha incorretos";
        }

        return retorno;
    }

    public static VisualizarResultado visualizar(VisualizarEnvio visualizarResponse) throws SQLException {

        Usuario usuario = DBActions.visualizarUsuario(visualizarResponse.email);


        // Enviando a resposta para o cliente
        VisualizarResultado retorno = new VisualizarResultado();
        //System.out.println(usuario);
        if(usuario.Nome != null){
            retorno.status = 201;
            retorno.nome = usuario.Nome;
            retorno.senha = usuario.Senha;
        }else {
            retorno.status = 404;
            retorno.mensagem = "E-mail não encontrado";
        }

        return retorno;
    }

    public static AtualizarCandidato atualizarUsuario(AtualizarEnvio atualizarResponse) throws SQLException {
        Usuario usuarioUpdate = new Usuario(atualizarResponse.nome, atualizarResponse.email, atualizarResponse.senha);
        var sucesso = DBActions.atualizarUsuario(usuarioUpdate);

        AtualizarCandidato retorno = new AtualizarCandidato();
        if(sucesso){
            retorno.status = 201;
        }else {
            retorno.status = 404;
            retorno.mensagem = "E-mail não encontrado";
        }

        return retorno;
    }

    public static DeleteResultado deletarUsuario(DeletarEnvio deletarResponse) throws SQLException {

        var sucesso = DBActions.deletarUsuario(deletarResponse.email);

        DeleteResultado retorno = new DeleteResultado();
        if(sucesso){
            retorno.status = 201;
        }else {
            retorno.status = 404;
            retorno.mensagem = "E-mail não encontrado";
        }

        return retorno;
    }
    
    public static LogoutResultado realizarLogout(LogoutEnvio logoutResponse)  {

        LogoutResultado retorno = new LogoutResultado();
        retorno.status = 204;

        return retorno;
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
    
    public static String gerarUID(){
        
        UUID uuid = UUID.randomUUID();
        String tokenString = uuid.toString();
        
        return tokenString;
    }

}
