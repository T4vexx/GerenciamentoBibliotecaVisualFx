package com.otavio.biblioteca;

import com.otavio.biblioteca.individuos.*;
import com.otavio.biblioteca.itens.*;
import com.otavio.exceptions.AttempLibraryError;
import com.otavio.exceptions.InexistentItemError;
import com.otavio.exceptions.InvalidInformationsError;
import com.otavio.exceptions.NotBorrowedItemError;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayBiblioteca {
    private UsuariosDB usuarios = new UsuariosDB();
    private Usuario meuCliente;
    private Vitrine vitrine;


    public DisplayBiblioteca()  {
        vitrine = new Vitrine();
        Scanner bibliotecaFiles = null;

        try {
            String linha;
            String[] campos;

            //bibliotecaFiles = new Scanner(new File("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoParaBiblioteca"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"org"+File.separator+"tavex"+File.separator+"assets"+File.separator+"itens.txt"));
            bibliotecaFiles = new Scanner(new File("C:\\Users\\tavexx\\Desktop\\BibliotecaVisual\\src\\main\\java\\com\\otavio\\assets\\itens.txt"));

            while(bibliotecaFiles.hasNextLine()) {
                linha = bibliotecaFiles.nextLine();
                campos = linha.split("#");
                if("Livro".equalsIgnoreCase(campos[0])) {
                    Livro livro = new Livro(campos[1],campos[2],campos[3],Integer.parseInt(campos[4]),campos[5],campos[6]);
                    vitrine.setNewLivro(livro);
                } else if("Revista".equalsIgnoreCase(campos[0])) {
                    Revista revista = new Revista(campos[1],campos[2],campos[3],Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),Integer.parseInt(campos[6]));
                    vitrine.setNewRevista(revista);
                } else if("CD".equalsIgnoreCase(campos[0])) {
                    CD cd = new CD(campos[1],campos[2],campos[3],Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),campos[6]);
                    vitrine.setNewCd(cd);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex.getMessage());
            Logger.getLogger(DisplayBiblioteca.class.getName()).log(Level.SEVERE , null ,ex);
        } finally {
            bibliotecaFiles.close();
        }
    }

    public boolean login(ActionEvent actE, String funcao, String matricula, String senha) {
        boolean isAccountLogged = false;
        Usuario myUser = null;
        myUser = usuarios.login(funcao,matricula,senha);
        if(myUser != null) {
            meuCliente = myUser;
            return true;
        } else {
            return false;
        }
    }

    public boolean register(ActionEvent actE, String nome, String senha, String matricula, String opcional1, String opcional2,String funcao) {
        if(funcao.equalsIgnoreCase("aluno")) {
            Alunos aluno = new Alunos(nome,senha,matricula,opcional1,opcional2);
            return usuarios.addNewAlunos(aluno,matricula);
        } else if(funcao.equalsIgnoreCase("professor")) {
            Professor professor = new Professor(nome,senha,matricula,opcional1,opcional2);
            return usuarios.addNewProfessor(professor,matricula);
        } else if(funcao.equalsIgnoreCase("assessor")) {
            AssessorTecnico assessor = new AssessorTecnico(nome,senha,matricula,opcional1);
            return usuarios.addNewAssesorTecnico(assessor,matricula);
        }
        return false;
    }

    private void telaDeUsuario() {
        Scanner in = new Scanner(System.in);
        int opt = 0;
        do {
            System.out.println("********************************");
            System.out.println("****       Biblioteca       ****");
            System.out.println("********************************");
            System.out.println("> 0    - Emprestimo de item -   ");
            System.out.println("> 1    - Devolução de item -    ");
            System.out.println("> 2    - Buscar item -    ");
            System.out.println("> 3    - Emprestados -    ");
            System.out.println("> 4    - Adicionar item -    ");
            System.out.println("> 5    - Sair -    ");
            opt = in.nextInt();

            switch (opt) {
                case 0:
                    try {
                        fazerEmprestimo();
                    } catch (AttempLibraryError ex) {
                        System.out.println("Erro: "+ex.getMessage());
                    }
                    break;
                case 1:
                    try {
                        devolverItem();
                    } catch (AttempLibraryError ex) {
                        System.out.println("Erro: "+ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        buscarItem();
                    } catch (InexistentItemError ex) {
                        System.out.println("Erro: "+ex.getMessage());
                    }

                    break;
                case 3:
                    emprestados();
                    break;
                case 4:
                    if(meuCliente instanceof Professor || meuCliente instanceof AssessorTecnico) {
                        try {
                            adicionarItem();
                        } catch (InvalidInformationsError ex) {
                            System.out.println("Erro: "+ex.getMessage());
                        }
                    } else {
                        System.out.println("Somente Professores e Pós Graduandos podem adicionar itens na biblioteca");
                    }
                    break;
                default:
                    meuCliente = null;
                    break;
            }
        } while(opt != 5);
    }

    private void fazerEmprestimo() throws AttempLibraryError, InexistentItemError {
        Item item=null;
        Scanner in = new Scanner(System.in);
        int opt = 0;
        boolean response;
        do {
            System.out.println("********************************");
            System.out.println("****       Biblioteca       ****");
            System.out.println("********************************");
            System.out.println("> 0    - CDs -   ");
            System.out.println("> 1    - Livros -    ");
            System.out.println("> 2    - Revistas -    ");
            System.out.println("> 3    - Voltar -    ");

            opt = in.nextInt();

            switch (opt) {
                case 0:
                    item = listarCDs();
                    break;
                case 1:
                    item = listarLivros();
                    break;
                case 2:
                    item = listarRevistas();
                    break;
                default:
                    item = null;
                    break;
            }

            if(item == null) {
                System.out.println("Operação cancelada");
            } else {
                if(item.getQuantidadeDisponivel() > 0) {
                    item.emprestar();
                    Emprestimo emprestar = new Emprestimo(item, LocalDateTime.now(),LocalDateTime.now().plusDays(2));
                    response = meuCliente.addItemEmprestado(emprestar);
                    if(response) {
                        System.out.println("Item emprestado com sucesso");
                    } else {
                        throw new AttempLibraryError("Esse item já está com vc");
                    }
                } else {
                    throw new InexistentItemError("Item indisponivel no momento");
                }
            }
        } while(opt != 3);
    }

    private CD listarCDs() {
        ArrayList<CD> cds;
        Scanner in = new Scanner(System.in);
        int opt = 0;
        int count = 0;
        do {
            System.out.println("********************************");
            System.out.println("****       Biblioteca       ****");
            System.out.println("********************************");
            cds = vitrine.getCDs();
            for(CD c: cds) {
                System.out.println("> "+count+" -> nome: "+c.getTitulo());
                count++;
            }

            System.out.println("Digite o número do item que deseja (-1 para sair)!");
            opt = in.nextInt();
            if(opt == -1) {
                return null;
            } else {
                return cds.get(opt);
            }
        } while(opt != -1);
    }

    private Livro listarLivros() {
        ArrayList<Livro> livros;
        Scanner in = new Scanner(System.in);
        int opt = 0;
        int count = 0;
        do {
            System.out.println("********************************");
            System.out.println("****       Biblioteca       ****");
            System.out.println("********************************");
            livros = vitrine.getLivros();
            for(Livro v: livros) {
                System.out.println("> "+count+" -> nome: "+v.getTitulo());
                count++;
            }

            System.out.println("Digite o número do item que deseja (-1 para sair)!");
            opt = in.nextInt();
            if(opt == -1) {
                return null;
            } else {
                return livros.get(opt);
            }
        } while(opt != -1);
    }

    private Revista listarRevistas() {
        Scanner in = new Scanner(System.in);
        ArrayList<Revista> revistas;
        int opt = 0;
        int count = 0;
        do {
            System.out.println("********************************");
            System.out.println("****       Biblioteca       ****");
            System.out.println("********************************");
            revistas = vitrine.getRevistas();
            for(Revista r: revistas) {
                System.out.println("> "+count+" -> nome: "+r.getTitulo());
                count++;
            }

            System.out.println("Digite o número do item que deseja (-1 para sair)!");
            opt = in.nextInt();
            if(opt == -1) {
                return null;
            } else {
                return revistas.get(opt);
            }
        } while(opt != -1);
    }

    private int emprestados() {
        ArrayList<Emprestimo> emps;
        int count = 0;
        emps = meuCliente.getItensEmprestados();
        if(emps.size() > 0) {
            for(Emprestimo e: emps) {
                System.out.println("> "+count+" -> "+ (e.getDataDeDevolucaoReal() != null ? "Devolvido" : "Não Devolvido" )+" -> Item: "+e.getItem().getTitulo()+" | Data de emprestimo: "+e.getDataDeEmprestimo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))+" | Data prevista de devolução: "+e.getDataDeDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                count++;
            }
            return count;
        } else {
            System.out.println("Você não tem itens emprestados aqui");
            return 0;
        }

    }

    private void buscarItem() throws InexistentItemError {
        Scanner in = new Scanner(System.in);
        String nomeDoItem;
        Item meuItem;

        System.out.println("********************************");
        System.out.println("****       Biblioteca       ****");
        System.out.println("********************************");
        System.out.println("> Digite o nome do item que deseja buscar!");
        nomeDoItem = in.nextLine();
        meuItem = vitrine.buscarItemPeloNome(nomeDoItem);
        if(meuItem != null) {
            System.out.println("Nome: "+meuItem.getTitulo());
            System.out.println("Autor: "+meuItem.getAutor());
            System.out.println("Ano de publicação: "+meuItem.getAnoPublicacao());
            System.out.println("Quantidade disponivel: "+meuItem.getQuantidadeDisponivel());
            System.out.println("Quantidade emprestado: "+meuItem.getQuantidadeEmprestada());
            if(meuItem instanceof Revista) {
                System.out.println("Volume: "+((Revista) meuItem).getVolume());
                System.out.println("Número da revista: "+((Revista) meuItem).getNumero());
            } else if(meuItem instanceof Livro) {
                System.out.println("Editora: "+((Livro) meuItem).getEditora());
                System.out.println("ISBN: "+((Livro) meuItem).getISBN());
            } else if(meuItem instanceof CD) {
                System.out.println("Volume: "+((CD) meuItem).getVolume());
                System.out.println("Gravadora: "+((CD) meuItem).getGravadora());
            }
        } else {
            throw new InexistentItemError("Item inexistente");
        }
    }

    private void devolverItem() throws NotBorrowedItemError {
        Scanner in = new Scanner(System.in);
        ArrayList<Emprestimo> emps;
        Item devolver;
        int opt = 0;
        int emprestados;
        String nomeItem;
        double valorDaMulta=0;
        System.out.println("********************************");
        System.out.println("****       Biblioteca       ****");
        System.out.println("********************************");
        emprestados = emprestados();
        if(emprestados != 0) {
            System.out.println("> Digite o nome do item que deseja devolver!");
            nomeItem = in.nextLine();
            emps = meuCliente.getItensEmprestados();
            for(Emprestimo e: emps) {
                if(e.getItem().getTitulo().equalsIgnoreCase(nomeItem)) {
                    e.setDataDeDevolucaoReal(LocalDateTime.now());
                    e.getItem().devolver();
                    if(e.getDataDeDevolucaoPrevista().isBefore(e.getDataDeDevolucaoReal())) {
                        if(meuCliente instanceof Alunos) {
                            valorDaMulta = ((Alunos) meuCliente).calcularMulta((int)e.calculaMulta());
                        } else if(meuCliente instanceof Professor) {
                            valorDaMulta = ((Professor) meuCliente).calcularMulta((int)e.calculaMulta());
                        } else if(meuCliente instanceof AssessorTecnico) {
                            valorDaMulta = ((AssessorTecnico) meuCliente).calcularMulta((int)e.calculaMulta());
                        }
                        System.out.println("Valor da multa por atraso - R$ "+valorDaMulta);
                        return;
                    }
                    System.out.println("Item devolvido com sucesso | você não teve multa");
                    return;
                }
            }
            throw new NotBorrowedItemError("Erro ao tentar devolver um item não emprestado");
        }
    }

    private void adicionarItem() throws InvalidInformationsError {
        Scanner in = new Scanner(System.in);
        String tipo;
        String titulo;
        String autor;
        String anoPublicacao;
        int quantidadeDisponivel;

        System.out.println("********************************");
        System.out.println("****       Biblioteca       ****");
        System.out.println("********************************");
        System.out.println("> Digite o titulo do item");
        titulo = in.nextLine();
        System.out.println("> Digite o autor do item");
        autor = in.nextLine();
        System.out.println("> Digite o ano publicação do item");
        anoPublicacao = in.nextLine();
        System.out.println("> Digite a quantidade de itens disponíveis");
        quantidadeDisponivel = in.nextInt();
        System.out.println("> Digite o tipo do item (CD | Livro | Revista)");
        tipo = in.nextLine();

        if(tipo.equalsIgnoreCase("CD")) {
            int volume;
            String gravadora;
            System.out.println("> Digite o volume do cd");
            volume = in.nextInt();
            System.out.println("> Digite a gravadora do cd");
            gravadora = in.nextLine();
            CD cd = new CD(titulo,autor,anoPublicacao,quantidadeDisponivel,volume,gravadora);
            vitrine.setNewCd(cd);
        } else if(tipo.equalsIgnoreCase("Livro")) {
            String editora;
            String ISBN;
            System.out.println("> Digite a editora do livro");
            editora = in.nextLine();
            System.out.println("> Digite o ISBN do livro");
            ISBN = in.nextLine();
            Livro livro = new Livro(titulo,autor,anoPublicacao,quantidadeDisponivel,editora,ISBN);
            vitrine.setNewLivro(livro);
        }else if(tipo.equalsIgnoreCase("Revista")) {
            int volume;
            int numero;
            System.out.println("> Digite o volume do revista");
            volume = in.nextInt();
            System.out.println("> Digite o número da revista");
            numero = in.nextInt();
            Revista revista = new Revista(titulo,autor,anoPublicacao,quantidadeDisponivel,volume,numero);
            vitrine.setNewRevista(revista);
        }

        throw new InvalidInformationsError("Informações inválidas | Tipo inexistente");
    }
}
