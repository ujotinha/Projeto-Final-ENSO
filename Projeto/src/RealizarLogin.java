/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class RealizarLogin {
    private String email, senha;
    private int id_logado;
    UsuarioDAO login = new UsuarioDAO();

    public RealizarLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
        
        id_logado = login.obterIdPorEmailESenha(email, senha);
        System.out.println(id_logado);
        
        
    }

    public int getId_logado() {
        return id_logado;
    }
    
    
}
