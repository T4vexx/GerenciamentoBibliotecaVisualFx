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

            bibliotecaFiles = new Scanner(new File("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoParaBiblioteca"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"org"+File.separator+"tavex"+File.separator+"assets"+File.separator+"itens.txt"));
            //bibliotecaFiles = new Scanner(new File("C:\\Users\\tavexx\\Desktop\\BibliotecaVisual\\src\\main\\java\\com\\otavio\\assets\\itens.txt"));

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

    public Usuario getMeuCliente() {
        return meuCliente;
    }

    public Vitrine getVitrine() { return vitrine; }

    public boolean login(ActionEvent actE, String funcao, String matricula, String senha) {
        boolean isAccountLogged = false;
        Usuario myUser = null;
        myUser = usuarios.login(funcao,matricula,senha);
        if(myUser != null) {
            meuCliente = myUser;
            DBUtils.changeScene2(actE,"user-view.fxml","Gerenciamento de biblioteca | Home page",meuCliente.getNome(),meuCliente.getMatricula(),funcao);
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

    public boolean fazerEmprestimo(Item item)  {
        if(item.getQuantidadeDisponivel() > 0) {
            item.emprestar();
            Emprestimo emprestar = new Emprestimo(item, LocalDateTime.now(),LocalDateTime.now().plusDays(2));
            return meuCliente.addItemEmprestado(emprestar);
        } else {
            return false;
        }
    }

    public double devolverItem(String nomeItem){
        ArrayList<Emprestimo> emps;
        emps = meuCliente.getItensEmprestados();
        for(Emprestimo e: emps) {
            if(e.getItem().getTitulo().equalsIgnoreCase(nomeItem)) {
                e.setDataDeDevolucaoReal(LocalDateTime.now());
                e.getItem().devolver();
                if(e.getDataDeDevolucaoPrevista().isBefore(e.getDataDeDevolucaoReal())) {
                    if(meuCliente instanceof Alunos) {
                        return ((Alunos) meuCliente).calcularMulta((int)e.calculaMulta());
                    } else if(meuCliente instanceof Professor) {
                        return ((Professor) meuCliente).calcularMulta((int)e.calculaMulta());
                    } else if(meuCliente instanceof AssessorTecnico) {
                        return ((AssessorTecnico) meuCliente).calcularMulta((int)e.calculaMulta());
                    }
                }
                return 0.0;
            }
        }
        return 0.0;
    }

    public Item buscarItem(ActionEvent actE, String title) {
        Item meuItem;
        meuItem = vitrine.buscarItemPeloNome(title);
        return meuItem;
    }

    public boolean adicionarItem(ActionEvent actE, String titulo, String autor, String anoPublicacao, String quantidaDisponivel, String opc1, String opc2, String tipo) {
        if(tipo.equalsIgnoreCase("Livro")) {
            Livro livro = new Livro(titulo,autor,anoPublicacao,Integer.parseInt(quantidaDisponivel),opc1,opc2);
            vitrine.setNewLivro(livro);
            return true;
        } else if(tipo.equalsIgnoreCase("Revista")) {
            Revista revista = new Revista(titulo,autor,anoPublicacao,Integer.parseInt(quantidaDisponivel),Integer.parseInt(opc1),Integer.parseInt(opc2));
            vitrine.setNewRevista(revista);
            return true;
        } else if(tipo.equalsIgnoreCase("CD")) {
            CD cd = new CD(titulo,autor,anoPublicacao,Integer.parseInt(quantidaDisponivel),Integer.parseInt(opc1),opc2);
            vitrine.setNewCd(cd);
            return true;
        }
        return false;
    }

    public void sair(ActionEvent event) {
        meuCliente = null;
        DBUtils.changeScene(event,"login-view.fxml","Gerenciamento de biblioteca | Log-In page","vazio");
    }
}
