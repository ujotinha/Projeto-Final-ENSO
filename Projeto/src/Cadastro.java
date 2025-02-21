
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author João
 */
public class Cadastro {
    private String nome,senha,emailinstitucional;
    boolean adm = false;
    boolean realizado;
    Scanner leia = new Scanner(System.in);

    public boolean Cadastro(String nome, String senha, String emailinstitucional) {
        this.nome = nome;
        this.senha = senha;
        this.emailinstitucional = emailinstitucional;
        
        
        UsuarioDAO teste = new UsuarioDAO();
        realizado = teste.inserirUsuario(nome, senha, emailinstitucional, adm);
        return realizado;
    }
    
    
}
