
import java.io.IOException;
import java.nio.file.*;

public class CopiarImagem {

    public void CopiarImagem(String caminho, String Titulo) {
        // Caminho da imagem de origem
        String caminhoOrigem = caminho;

        // Caminho de destino no diretório do projeto (por exemplo, dentro de uma pasta "imagens")
        String caminhoDestino = "C:\\Users\\João\\Documents\\NetBeansProjects\\Telas\\src\\CapaLivros\\" + Titulo + ".jpg";

        try {
            // Copia a imagem da origem para o destino
            Path origem = Paths.get(caminhoOrigem);
            Path destino = Paths.get(caminhoDestino);
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Imagem copiada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renomearArquivo(String caminhoCompleto, String novoNome) {
        try {
            Path origem = Paths.get(caminhoCompleto);
            Path diretorio = origem.getParent(); // Obtém o diretório do arquivo original
            Path novoArquivo = diretorio.resolve(novoNome); // Cria o novo caminho dentro do mesmo diretório

            Files.move(origem, novoArquivo, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo renomeado com sucesso para: " + novoNome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
