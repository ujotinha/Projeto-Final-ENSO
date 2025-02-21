import java.util.Scanner;

public class Livro {
    private String titulo, autor, sinopse, genero;
    private int id_livro;
    Scanner leia = new Scanner(System.in);

    public Livro() {
        System.out.println("Informe o título do livro: ");
        titulo = leia.nextLine();
        System.out.println("Informe o autor do livro: ");
        autor = leia.nextLine();
        System.out.println("Informe a sinopse do livro: ");
        sinopse = leia.nextLine();
        System.out.println("Informe o gênero do livro: ");
        genero = leia.nextLine();
        System.out.println("Informe o id do livro: ");
        id_livro = leia.nextInt();
    }

    public String exibirLivro() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nGênero: " + genero + "\nSinopse: " + sinopse;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}