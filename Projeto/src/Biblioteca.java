import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Biblioteca {
    private ArrayList<Livro> livros_biblioteca = new ArrayList<Livro>();
    private ArrayList<Exemplar> exemplares_catalogo = new ArrayList<Exemplar>();
    private ArrayList<Usuario> usuarios_biblioteca = new ArrayList<Usuario>();
    UsuarioDAO Usuario = new UsuarioDAO();
    private Scanner leia = new Scanner(System.in);
    private Calendar c = Calendar.getInstance();
    private int ano = c.get(Calendar.YEAR);
    private int mes = (c.get(Calendar.MONTH) + 1);
    private int dia_do_mes = c.get(Calendar.DAY_OF_MONTH);

    // Inserindo exemplares no catálogo
    public void inserindoExemplaresCatalogo() {
        Exemplar novo_exemplar = new Exemplar();
        System.out.println("Digite o id do livro condizente ao exemplar: ");
        int id = leia.nextInt();
        for (int i = 0; i < livros_biblioteca.size(); i++) {
            if (id == livros_biblioteca.get(i).getId_livro()) {
                novo_exemplar.setLivro(livros_biblioteca.get(i));
                exemplares_catalogo.add(novo_exemplar);
                System.out.println("Exemplar adicionado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado!");
    }

    public void adicionandoLivro() {
        Livro novo_livro = new Livro();
        livros_biblioteca.add(novo_livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    // Remover exemplar no catálogo
    public void removerExemplarCatalogo() {
        String remover_exemplar;
        System.out.println("Digite o código do exemplar que deseja remover: ");
        remover_exemplar = leia.next();
        for (int i = 0; i < exemplares_catalogo.size(); i++) {
            if (remover_exemplar.equals(exemplares_catalogo.get(i).getCodigo())) {
                exemplares_catalogo.remove(i);
                System.out.println("Remoção feita com sucesso!!");
                return;
            }
        }
        System.out.println("Exemplar não encontrado!!");
    }

    // Registrando usuários na biblioteca
    /*public void cadastrarUsuarios() {
        Usuario novo_usuario = new Usuario();
        UsuarioDAO teste = new UsuarioDAO();
        teste.inserirUsuario(novo_usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }*/

    public void cadastrarUsuariosAdm() {
        Usuario novo_usuario_adm = new Usuario(true);
        usuarios_biblioteca.add(novo_usuario_adm);
        System.out.println("Usuário administrador cadastrado com sucesso!");
    }

    public void exibirCatalogo() {
        if (!exemplares_catalogo.isEmpty()) {
            for (int i = 0; i < exemplares_catalogo.size(); i++) {
                System.out.println((i + 1) + "°:\n\n" + exemplares_catalogo.get(i).exibirExemplar() + "\n");
            }
        } else {
            System.out.println("Não há nenhum exemplar no catálogo");
        }
    }

    public void calcularMulta() {
        for (int i = 0; i < usuarios_biblioteca.size(); i++) {
            for (int j = 0; j < usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().size(); j++) {
                int dia_multa = Integer.parseInt(usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).getDia_do_mes_aux());
                int mes_multa = Integer.parseInt(usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).getMes_aux());
                int ano_multa = Integer.parseInt(usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).getAno_aux());
                if (ano_multa == ano && mes_multa < mes && !usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).isMultado()) {
                    usuarios_biblioteca.get(i).getHistorico().aplicarMulta(10);
                    usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).setMultado(true);
                } else if (ano_multa == ano && mes_multa == mes && dia_multa < dia_do_mes && !usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).isMultado()) {
                    usuarios_biblioteca.get(i).getHistorico().aplicarMulta(10);
                    usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).setMultado(true);
                } else if (ano_multa < ano && !usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).isMultado()) {
                    usuarios_biblioteca.get(i).getHistorico().aplicarMulta(10);
                    usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).setMultado(true);
                }
            }
        }
    }

    public void menuBiblioteca(int usuario) {
        boolean verificar = Usuario.obterIsAdm(usuario);
        if (verificar == true) {
            int acao_menu, acao_gerencia, acao_catalogo, acao_gerencia_cat;
            String codigo_emprestimo, codigo_devolucao;
            while (true) {
                System.out.println("Seja bem-vindo à biblioteca Space!");
                System.out.println("(1) - Gerenciar Conta");
                System.out.println("(2) - Abrir Catálogo");
                System.out.println("(3) - Gerenciar Catálogo");
                System.out.println("(4) - Sair");
                System.out.println("Indique sua ação: ");
                acao_menu = leia.nextInt();
                if (acao_menu == 1) {
                    calcularMulta();
                    System.out.println("| GERENCIAR CONTA |");
                    System.out.println("Nome: " + usuarios_biblioteca.get(usuario).getNome());
                    System.out.println("Email: " + usuarios_biblioteca.get(usuario).getEmailinstitucional());
                    System.out.println("(1) - Atualizar credenciais da conta");
                    System.out.println("(2) - Histórico de Empréstimos");
                    System.out.println("(3) - Voltar");
                    System.out.println("Indique sua ação: ");
                    acao_gerencia = leia.nextInt();
                    if (acao_gerencia == 1) {
                        usuarios_biblioteca.get(usuario).atualizarUsuario();
                    } else if (acao_gerencia == 2) {
                        if (usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().isEmpty()) {
                            System.out.println("Não há nenhum empréstimo");
                        } else {
                            for (Emprestimo emprestimo : usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados()) {
                                emprestimo.exibirEmprestimo();
                            }
                        }
                    }
                } else if (acao_menu == 2) {
                    exibirCatalogo();
                    System.out.println("(1) - Realizar empréstimo");
                    System.out.println("(2) - Devolver livro");
                    System.out.println("(3) - Voltar");
                    System.out.println("Informe sua ação: ");
                    acao_catalogo = leia.nextInt();
                    if (acao_catalogo == 1) {
                        realizarEmprestimo(usuario);
                    } else if (acao_catalogo == 2) {
                        devolverLivro(usuario);
                    } 
                    else if (acao_catalogo == 3) {
                        break;
                    }
                } else if (acao_menu == 3) {
                        calcularMulta();
                        System.out.println("| GERENCIAR CATÁLOGO |");
                        System.out.println("(1) - Adicionar livro");
                        System.out.println("(2) - Adicionar exemplar no catálogo");
                        System.out.println("(3) - Remover exemplar do catálogo");
                        System.out.println("(4) - Voltar");
                        System.out.println("Informe sua ação: ");
                        acao_gerencia_cat = leia.nextInt();
                        if(acao_gerencia_cat == 1){
                            calcularMulta();
                            adicionandoLivro();
                        }
                        else if (acao_gerencia_cat == 2) {
                            calcularMulta();
                            inserindoExemplaresCatalogo();
                        } else if (acao_gerencia_cat == 3) {
                            calcularMulta();
                            exibirCatalogo();
                            removerExemplarCatalogo();
                        } else if (acao_gerencia_cat == 4) {
                            calcularMulta();
                            break;
                        }
                    }else if (acao_menu == 4) {
                    return;
                } else {
                    System.out.println("Opção inválida!");
                }
            }
        } else {
            int acao_menu, acao_gerencia, acao_catalogo;
            String codigo_emprestimo, codigo_devolucao;
            while (true) {
                System.out.println("Seja bem-vindo à Biblioteca Space!");
                System.out.println("(1) - Gerenciar Conta");
                System.out.println("(2) - Abrir Catálogo");
                System.out.println("(3) - Sair");
                System.out.println("Indique sua ação: ");
                acao_menu = leia.nextInt();
                if (acao_menu == 1) {
                    calcularMulta();
                    System.out.println("| GERENCIAR CONTA |");
                    System.out.println("Nome: " + usuarios_biblioteca.get(usuario).getNome());
                    System.out.println("Email: " + usuarios_biblioteca.get(usuario).getEmailinstitucional());
                    System.out.println("(1) - Atualizar credenciais da conta");
                    System.out.println("(2) - Histórico de Empréstimos");
                    System.out.println("(3) - Voltar");
                    System.out.println("Indique sua ação: ");
                    acao_gerencia = leia.nextInt();
                    if (acao_gerencia == 1) {
                        usuarios_biblioteca.get(usuario).atualizarUsuario();
                    } else if (acao_gerencia == 2) {
                        if (usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().isEmpty()) {
                            System.out.println("Não há nenhum empréstimo");
                        } else {
                            for (Emprestimo emprestimo : usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados()) {
                                emprestimo.exibirEmprestimo();
                            }
                        }
                    }
                } else if (acao_menu == 2) {
                    exibirCatalogo();
                    System.out.println("(1) - Realizar empréstimo");
                    System.out.println("(2) - Devolver livro");
                    System.out.println("(3) - Voltar");
                    System.out.println("Informe sua ação: ");
                    acao_catalogo = leia.nextInt();
                    if (acao_catalogo == 1) {
                        realizarEmprestimo(usuario);
                    } else if (acao_catalogo == 2) {
                        devolverLivro(usuario);
                    } else if (acao_catalogo == 3) {
                        break;
                    }
                } else if (acao_menu == 3) {
                    return;
                } else {
                    System.out.println("Opção inválida!");
                }
            }
        }
    }

    public void realizarEmprestimo(int usuario) {
        System.out.println("Informe o código do livro que deseja realizar o empréstimo: ");
        String codigo_emprestimo = leia.next();
        for (int i = 0; i < exemplares_catalogo.size(); i++) {
            if (codigo_emprestimo.equals(exemplares_catalogo.get(i).getCodigo()) && exemplares_catalogo.get(i).getStatus().equals("Disponível")) {
                Emprestimo emprestimo_realizado = new Emprestimo(exemplares_catalogo.get(i));
                usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().add(emprestimo_realizado);
                exemplares_catalogo.get(i).setStatus("Indisponível");
                System.out.println("Empréstimo realizado com sucesso! ID do Empréstimo: " + emprestimo_realizado.getIdEmprestimo());
                return;
            }
        }
        System.out.println("Exemplar não encontrado ou o exemplar está indisponível!!");
    }

    public void devolverLivro(int usuario) {
        System.out.println("Informe o ID do empréstimo que deseja devolver: ");
        int idDevolucao = leia.nextInt();
        boolean encontrado = false;

        for (Emprestimo emprestimo : usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados()) {
            if (emprestimo.getIdEmprestimo() == idDevolucao) {
                Exemplar exemplar = emprestimo.getExemplar();
                exemplar.setStatus("Disponível");
                emprestimo.setStatus("Devolvido");
                System.out.println("Devolução feita com sucesso!!");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("ID de empréstimo não encontrado!!");
        }
    }

    // Getters e Setters
    public ArrayList<Exemplar> getExemplares_catalogo() {
        return exemplares_catalogo;
    }

    public void setExemplares_catalogo(ArrayList<Exemplar> exemplares_catalogo) {
        this.exemplares_catalogo = exemplares_catalogo;
    }

    public ArrayList<Usuario> getUsuarios_biblioteca() {
        return usuarios_biblioteca;
    }

    public void setUsuarios_biblioteca(ArrayList<Usuario> usuarios_biblioteca) {
        this.usuarios_biblioteca = usuarios_biblioteca;
    }

    public Scanner getLeia() {
        return leia;
    }

    public void setLeia(Scanner leia) {
        this.leia = leia;
    }
}