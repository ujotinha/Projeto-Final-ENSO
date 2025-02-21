import java.util.Scanner;

public class Usuario {
    private String nome,matricula,senha,emailinstitucional;
    private HistoricoDeEmprestimos historico;
    private boolean adm;
    Scanner leia = new Scanner(System.in);
    
    //Cadastro de usuários
    
    public Usuario(){
        System.out.println("| CADASTRO |");
        System.out.println("Informe seu nome: ");
        nome = leia.nextLine();
        System.out.println("Informe seu email constitucional: ");
        emailinstitucional = leia.next();
        System.out.println("Informe a senha: ");
        senha = leia.next();
        historico = new HistoricoDeEmprestimos();
        adm = false;
    }

    public Usuario(boolean adm) {
        System.out.println("| CADASTRO |");
        System.out.println("Informe seu nome: ");
        nome = leia.nextLine();
        System.out.println("Informe seu email constitucional: ");
        emailinstitucional = leia.next();
        System.out.println("Informe a senha: ");
        senha = leia.next();
        historico = new HistoricoDeEmprestimos();
    }

    public Usuario(String nome, String matricula, String senha, String emailinstitucional, boolean adm) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.emailinstitucional = emailinstitucional;
        this.adm = adm;
        historico = new HistoricoDeEmprestimos();
    }
    
    //Atualizar Credenciais
    
    public void atualizarUsuario(){
        System.out.println("| ATUALIZANDO CREDENCIAIS |");
        System.out.println("Nome: ");
        nome = leia.nextLine();
        nome = leia.nextLine();
        System.out.println("Email Institucional: ");
        emailinstitucional = leia.next();
        System.out.println("Senha: ");
        senha = leia.next();
    }
    
    // Getters e Setters
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmailinstitucional() {
        return emailinstitucional;
    }

    public void setEmailinstitucional(String emailinstitucional) {
        this.emailinstitucional = emailinstitucional;
    }

    public HistoricoDeEmprestimos getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoDeEmprestimos historico) {
        this.historico = historico;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public Scanner getLeia() {
        return leia;
    }

    public void setLeia(Scanner leia) {
        this.leia = leia;
    }
    
    
    
}
