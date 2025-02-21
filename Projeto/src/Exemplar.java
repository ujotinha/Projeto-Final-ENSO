import java.util.Scanner;

public class Exemplar {
    private Livro livro;
    private String status = "Disponível", codigo, possuidor = "Nenhum";
    Scanner leia = new Scanner(System.in);

    public Exemplar() {
        System.out.println("Informe o código de identificação do livro: ");
        codigo = leia.nextLine();
    }

    public String exibirExemplar() {
        return livro.exibirLivro() + "\nCódigo: " + codigo + "\nStatus: " + status;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPossuidor() {
        return possuidor;
    }

    public void setPossuidor(String possuidor) {
        this.possuidor = possuidor;
    }
}