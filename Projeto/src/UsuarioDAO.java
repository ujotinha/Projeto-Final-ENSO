
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioDAO {

    Connection conexao;
    int id_usuario, nota;
    String nome, email;
    boolean is_adm, verificacao = false;
    ArrayList<Integer> ids_livro = new ArrayList<Integer>();
    ArrayList<String> ids_exemplar = new ArrayList<String>();
    ArrayList<String> titulos_livros = new ArrayList<String>();
    String titulo, autor, genero, sinopse, retorno;

    public UsuarioDAO() {
        conexao = new Conexao().getConexao();
    }
    
    public void obterEmprestimoPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        
    }
    
    public void inserirUsuarioAtual(String nome, String email, String senha, boolean isAdm, int Id) {
        try {
            String sql = "insert into usuario_atual (id_usuario, nome, email, senha, is_admin) values ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, Id);
            ps.setString(2, nome);
            ps.setString(3, email);
            ps.setString(4, senha);
            ps.setBoolean(5, isAdm);

            ps.executeUpdate();    //mandando para o banco

            System.out.println("\n Pessoa inserida com sucesso");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public boolean inserirUsuario(String nome, String senha, String email, boolean isAdm) {
        boolean realizado = false;
        try {
            String email_verificar = "";
            String sql = "Select * from usuarios where email_institucional = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email_verificar = rs.getString("email_institucional");
            }
            if (email_verificar.equalsIgnoreCase(email)) {
                return realizado;
            } else {
                sql = "insert into Usuarios ( nome, email_institucional, senha, is_admin) values (?, ?, ?, ?);";
                ps = conexao.prepareStatement(sql);

                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, senha);
                ps.setBoolean(4, isAdm);
                ps.executeUpdate();    //mandando para o banco

                realizado = true;
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
        return realizado;
    }

    public void inserirExemplar(int quant, int id_livro) {
        boolean realizado = false;
        try {
            String sql;
            ResultSet rs;
            PreparedStatement ps;
            int i = 0;
            while (i < quant) {
                sql = "insert into exemplares (id_livro_fk) values (?)";
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, id_livro);
                ps.executeUpdate();
                i++;
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void inserirUsuario(int id, String nome, String senha, String email, boolean isAdm) {
        try {
            String sql = "insert into Usuarios (id_usuario, nome, email_institucional, senha, is_admin) values ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.setString(3, email);
            ps.setString(4, senha);
            ps.setBoolean(5, isAdm);

            ps.executeUpdate();    //mandando para o banco

            System.out.println("\n Pessoa inserida com sucesso");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public String inserirLivro(String Titulo, String Autor, String Genero, String Sinopse, Integer nota) {
        try {
            String repeticao = "";
            retorno = "Livro não foi adicionado, verifique as informações inseridas e seus tamanhos!";
            String sql = "SELECT * FROM livros_biblioteca WHERE LOWER(titulo) = LOWER(?);";
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, Titulo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                repeticao = rs.getString("titulo");
            }
            if (!repeticao.equalsIgnoreCase("")) {
                retorno = "Já existe o livro que você está tentando inserir!";
            } else {
                sql = "insert into livros_biblioteca (autor, titulo, genero, sinopse, nota) values (?, ?, ?, ?, ?)";
                ps = conexao.prepareStatement(sql);

                ps.setString(1, Autor);
                ps.setString(2, Titulo);
                ps.setString(3, Genero);
                ps.setString(4, Sinopse);
                ps.setInt(5, nota);

                ps.executeUpdate();    //mandando para o banco

                System.out.println("\n Livro inserido com sucesso");
                retorno = "Livro inserido com sucesso!";
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
        return retorno;
    }

    public String inserirEmprestimo(int id_livro_fk, int idUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        String retorno = "Empréstimo não foi registrado, verifique as informações!";

        try {
            String sql = "SELECT id_exemplar FROM exemplares where id_livro_fk = ? and id_exemplar not in (select (id_exemplar_fk) from emprestimos where lower(situacao)='em andamento'); ";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_livro_fk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_exemplar = rs.getInt("id_exemplar");
                ids_exemplar.add("ID: " + id_exemplar);
            }
            if (ids_exemplar.size() == 0) {
                    retorno = "Não existe exemplar disponível para emprestimo!";
            }
            // Insere um novo empréstimo
            sql = "INSERT INTO Emprestimos (id_exemplar_fk, id_livro_fk, id_usuario_fk, situacao, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?, ?);";
            ps = conexao.prepareStatement(sql);
            int inicio = ids_exemplar.get(0).indexOf(":");
            String trecho = null;
            if (inicio >= 0 && inicio < ids_exemplar.get(0).length()) {
                trecho = ids_exemplar.get(0).substring(inicio + 1).trim();
                System.out.println("Trecho extraído: " + trecho);
            }
            Integer id = Integer.parseInt(trecho);
            ps.setInt(1, id);
            ps.setInt(2, id_livro_fk);
            ps.setInt(3, idUsuario);
            ps.setString(4, "Em andamento");
            ps.setDate(5, java.sql.Date.valueOf(dataEmprestimo));
            ps.setDate(6, java.sql.Date.valueOf(dataDevolucao));

            ps.executeUpdate();

            retorno = "Emprestimo realizado com sucesso!";

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return retorno;
    }

    public int obterMaiorIdUsuario() {
        int maiorId = 0;
        try {
            String sql = "SELECT MAX(id_usuario) AS maior_id FROM Usuarios";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maiorId = rs.getInt("maior_id");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return maiorId;
    }

    public int obterIdPorEmailESenha(String email, String senha) {
        int id = 0;
        try {
            String sql = "SELECT id_usuario FROM Usuarios WHERE email_institucional = ? AND senha = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            System.out.println(id_usuario);
            if (rs.next()) {
                id = rs.getInt("id_usuario");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return id;
    }

    public String obterNomePorId(int id) {
        try {
            String sql = "SELECT nome FROM Usuarios WHERE id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return nome;
    }
    
    public ArrayList obterIdLivros() {
        try {
            String sql = "SELECT id_livro FROM livros_biblioteca";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_livro = rs.getInt("id_livro");
                ids_livro.add(id_livro);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return ids_livro;
    }
    
    public ArrayList obterIdLivrosInicial() {
        try {
            String sql = "SELECT id_livro FROM livros_biblioteca WHERE id_livro IS NOT NULL ORDER BY id_livro DESC LIMIT 3;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_livro = rs.getInt("id_livro");
                ids_livro.add(id_livro);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return ids_livro;
    }
    
    public ArrayList obterIdLivrosPesquisa(String pesquisa) {
        try {
            String Pesquisa = "%"+pesquisa+"%" ;
            String sql = "SELECT id_livro FROM livros_biblioteca where titulo like ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, Pesquisa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_livro = rs.getInt("id_livro");
                ids_livro.add(id_livro);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return ids_livro;
    }

    public ArrayList obterIdExemplares(int id_livro_fk) {
        try {
            String sql = "SELECT id_exemplar FROM exemplares where id_livro_fk = ? and id_exemplar not in (select (id_exemplar_fk) from emprestimos where lower(situacao)='em andamento'); ";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_livro_fk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_exemplar = rs.getInt("id_exemplar");
                ids_exemplar.add("ID: " + id_exemplar);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return ids_exemplar;
    }

    public boolean obterIsAdm(int id) {
        boolean adm = false;
        String sql = "SELECT is_admin FROM Usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    adm = rs.getBoolean("is_admin");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return adm;
    }

    public ArrayList obterNomeLivros() {
        try {
            String sql = "SELECT id_livro, titulo FROM livros_biblioteca";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_livro = rs.getInt("id_livro");
                String titulo_livro = rs.getString("titulo");
                titulos_livros.add("ID: " + id_livro + " - " + titulo_livro);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return titulos_livros;
    }

    public String obterSinopseLivro(int id) {
        try {
            String sql = "SELECT sinopse FROM livros_biblioteca WHERE id_livro = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sinopse = rs.getString("sinopse");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return sinopse;
    }

    public String obterGeneroLivro(int id) {
        try {
            String sql = "SELECT genero FROM livros_biblioteca WHERE id_livro = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                genero = rs.getString("genero");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return genero;
    }

    public int obterNotaLivro(int id) {
        try {
            String sql = "SELECT nota FROM livros_biblioteca WHERE id_livro = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nota = rs.getInt("nota");
            }
            System.out.println(nota);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return nota;
    }

    public String obterAutorLivro(int id) {
        try {
            String sql = "SELECT autor FROM livros_biblioteca WHERE id_livro = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                autor = rs.getString("autor");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return autor;
    }

    public String obterNomeLivro(int id) {
        try {
            String sql = "SELECT titulo FROM livros_biblioteca WHERE id_livro = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                titulo = rs.getString("titulo");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return titulo;
    }

    public int obterTotalUsuarioAtual() {
        int quant = 0;
        try {
            String sql = "SELECT COUNT(*) AS total_usuarios FROM usuario_atual;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            quant = rs.getInt("total_usuarios");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return quant;
    }

    public int obterTotalUsuarios() {
        int quant = 0;
        try {
            String sql = "SELECT COUNT(*) AS total_usuarios FROM usuarios;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            quant = rs.getInt("total_usuarios");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return quant;
    }

    public int obterTotalLivros() {
        int quant = 0;
        try {
            String sql = "SELECT COUNT(*) AS total_livros FROM livros_biblioteca;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            quant = rs.getInt("total_livros");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return quant;
    }

    public int obterTotalExemplares() {
        int quant = 0;
        try {
            String sql = "SELECT COUNT(*) AS total_exemplares FROM exemplares;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            quant = rs.getInt("total_exemplares");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return quant;
    }

    public int obterTotalEmprestimos() {
        int quant = 0;
        try {
            String sql = "SELECT COUNT(*) AS total_emprestimos FROM emprestimos;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            quant = rs.getInt("total_emprestimos");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return quant;
    }

    public String obterNomeAtual() {
        try {
            String sql = "SELECT nome FROM usuario_atual";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar ID por email e senha: " + e);
        }
        return nome;
    }

    public String obterEmailAtual() {
        try {
            String sql = "SELECT email FROM usuario_atual";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar ID por email e senha: " + e);
        }
        return email;
    }

    public int obterIDAtual() {
        try {
            String sql = "SELECT id_usuario FROM usuario_atual";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_usuario = rs.getInt("id_usuario");
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter id: " + e);
        }
        return id_usuario;
    }

    public boolean obterIs_AdmAtual() {
        try {
            String sql = "SELECT is_admin FROM usuario_atual";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                is_adm = rs.getBoolean("is_admin");
            }
            System.out.println("Encontrado");
        } catch (Exception e) {
            System.out.println("Erro ao obter is_sdm: " + e);
        }
        return is_adm;
    }

    public void removerUsuarioAntigo() {
        try {
            String sql = "delete from usuario_atual";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.executeUpdate();    //mandando para o banco
            System.out.println("\n Pessoa removida");
        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void removerExemplar(int id_exemplar) {
        try {
            String sql = "delete from exemplares where id_exemplar = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_exemplar);
            ps.executeUpdate();    //mandando para o banco
            System.out.println("\n Exemplar removido");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void removerCredAntiga(int id) {
        try {
            String sql = "delete from Usuarios where id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();    //mandando para o banco
            System.out.println("\n Pessoa removida");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void removerLivro(int id) {
        try {
            String sql = "delete from livros_biblioteca where id_livro = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();    //mandando para o banco

            System.out.println("\n Livro removido");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void remover(int id) {
        try {
            String sql = "delete from Pessoa where id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();    //mandando para o banco

            System.out.println("\n Pessoa removida");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void AtualizarUsuarioAtual(int Id, String nome, String email, String senha, boolean isAdm) {
        try {
            String sql = "delete from usuario_atual;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.executeUpdate();
            sql = "insert into usuario_atual (id_usuario, nome, email, senha, is_admin) values ( ?, ?, ?, ?, ?);";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, Id);
            ps.setString(2, nome);
            ps.setString(3, email);
            ps.setString(4, senha);
            ps.setBoolean(5, isAdm);

            ps.executeUpdate();    //mandando para o banco

            System.out.println("\n Pessoa inserida com sucesso");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void atualizarInfo(int id, String Titulo, String Autor, String Genero, String Sinopse, Integer Nota) {
        try {
            String sql = "REPLACE INTO livros_biblioteca (id_livro, autor, titulo, sinopse, genero, nota) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, Autor);
            ps.setString(3, Titulo);
            ps.setString(4, Sinopse);
            ps.setString(5, Genero);
            ps.setInt(6, Nota);

            ps.executeUpdate();    //mandando para o banco

            System.out.println("\n Pessoa inserida com sucesso");

        } catch (Exception e) {
            System.out.println("Erro: " + e);

        }
    }

    public void consultarTudo() {

        try {
            String sql = "select * from pessoa;";
            PreparedStatement sttmt = conexao.prepareStatement(sql);
            ResultSet rst = sttmt.executeQuery();
            while (rst.next()) {
                String n = rst.getString("nome");
                int i = rst.getInt("id");
                System.out.println("Dados do banco: " + n + " " + i);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na consulta de pessoa: " + ex);
        }
    }

}
