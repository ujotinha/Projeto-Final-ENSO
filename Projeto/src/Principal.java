import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        TelaCadastro cad = new TelaCadastro();
        cad.setVisible(true);

        // Adicionando usuários administradores
        

        /*while (true) {
            System.out.println("| TELA DE INÍCIO |");
            System.out.println("Deseja realizar um login ou se cadastrar? (Login - 1; Cadastrar - 2; Encerrar aplicação - 3)");
            escolha_telainicio = leia.nextInt();
            if (escolha_telainicio == 2) {
                Space_Team.cadastrarUsuarios();
                verificarUsuario = usuario.obterMaiorIdUsuario();
                Space_Team.menuBiblioteca(verificarUsuario);
            } else if (escolha_telainicio == 1) {
                System.out.println("Informe o email institucional: ");
                email_login = leia.next();
                System.out.println("Informe a senha: ");
                senha_login = leia.next();
                verificarUsuario = usuario.obterIdPorEmailESenha(email_login, senha_login);
                if (verificarUsuario != 0){
                    
                    Space_Team.menuBiblioteca(verificarUsuario);
                }
            } else if (escolha_telainicio == 3) {
                System.exit(0);
            } else {
                System.out.println("Insira uma opção válida.");
            }
        }*/
    }
}