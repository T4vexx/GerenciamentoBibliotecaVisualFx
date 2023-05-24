package com.otavio.biblioteca;

import com.otavio.biblioteca.individuos.*;
import com.otavio.biblioteca.itens.*;
import com.otavio.exceptions.AttempLibraryError;
import com.otavio.exceptions.UnavailableItemError;
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


/**
 * DisplayBiblioteca
 * Classe que é responsavel por todo o funcionamento da biblioteca, de sua funções e relações.
 * Gerencia os usuario os itens e suas conexões.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class DisplayBiblioteca {
    private final UsuariosDB usuarios = new UsuariosDB();
    private Usuario meuCliente;
    private final Vitrine vitrine;

    /**
     * Construtor que inicia os os itens da biblioteca.
     * Substituir pelo local onde esta o itens.txt.
     */
    public DisplayBiblioteca()  {
        vitrine = new Vitrine();
        Scanner bibliotecaFiles = null;

        try {
            String linha;
            String[] campos;

            bibliotecaFiles = new Scanner(new File("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoDeBibliotecaGUI"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"otavio"+File.separator+"assets"+File.separator+"itens.txt"));
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

    /**
     * Método que realiza o login do usuário com as informações fornecidas na interface.
     *
     * @param actE   O evento de ação ActionEvent.
     * @param funcao A função do usuário (aluno/professor/assessor) String.
     * @param matricula A matrícula do usuário String.
     * @param senha A senha do usuário String.
     * @return true se o login for correto, caso contrário false.
     */
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

    /**
     * Método que registra um novo usuário com as informações fornecidas na interface.
     *
     * @param actE O evento de ação ActionEvent.
     * @param nome O nome do usuário String.
     * @param senha A senha do usuário String.
     * @param matricula A matrícula do usuário String.
     * @param opcional1 Opcional 1 (depende da função do usuário) String.
     * @param opcional2 Opcional 2 (depende da função do usuário) String.
     * @param funcao A função do usuário (aluno/professor/assessor) String.
     * @throws InvalidInformationsError erro caso as informações forem invalidas.
     */
    public void register(ActionEvent actE, String nome, String senha, String matricula, String opcional1, String opcional2,String funcao) throws InvalidInformationsError {
        if(funcao.equalsIgnoreCase("aluno")) {
            Alunos aluno = new Alunos(nome,senha,matricula,opcional1,opcional2);
            usuarios.addNewAlunos(aluno,matricula);
        } else if(funcao.equalsIgnoreCase("professor")) {
            Professor professor = new Professor(nome,senha,matricula,opcional1,opcional2);
            usuarios.addNewProfessor(professor,matricula);
        } else if(funcao.equalsIgnoreCase("assessor")) {
            AssessorTecnico assessor = new AssessorTecnico(nome,senha,matricula,opcional1);
            usuarios.addNewAssesorTecnico(assessor,matricula);
        }
        throw new InvalidInformationsError("Digite a função correta | aluno/professor/assessor!");
    }

     /**
     * Método que realiza o empréstimo de um item para o cliente logado.
     *
     * @param item O item a ser emprestado Item.
     * @throws AttempLibraryError Se o item já for do cliente.
     * @throws UnavailableItemError Se o item estiver indisponível no momento.
     */
    public void fazerEmprestimo(Item item) throws AttempLibraryError, UnavailableItemError  {
        boolean retorno;
        if(item.getQuantidadeDisponivel() > 0) {
            item.emprestar();
            Emprestimo emprestar = new Emprestimo(item, LocalDateTime.now(),LocalDateTime.now().plusDays(2));
            retorno = meuCliente.addItemEmprestado(emprestar);
            if(!retorno) {
                throw new AttempLibraryError("Esse item já está com vc");
            }
        } else {
            throw new UnavailableItemError("Item indisponível no momento");
        }
    }

    /**
     * Método que devolve um item do cliente logado.
     *
     * @param nomeItem O nome do item a ser devolvido Item.
     * @return A multa a ser paga, se houver double.
     * @throws NotBorrowedItemError Se o item não estiver com o cliente.
     */
    public double devolverItem(String nomeItem) throws NotBorrowedItemError {
        ArrayList<Emprestimo> emps;
        emps = meuCliente.getItensEmprestados();
        for(Emprestimo e: emps) {
            if(e.getItem().getTitulo().equalsIgnoreCase(nomeItem)) {
                e.setDataDeDevolucaoReal(LocalDateTime.now().plusDays(5));
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
        throw new NotBorrowedItemError("Erro ao tentar devolver um item não emprestado");
    }

    /**
     * Método que busca um item na vitrine da biblioteca pelo título.
     *
     * @param actE  O evento de ação ActionEvent.
     * @param title O título do item a ser buscado String.
     * @return O item encontrado Item.
     * @throws UnavailableItemError Se o item não for encontrado.
     */
    public Item buscarItem(ActionEvent actE, String title) throws UnavailableItemError {
        Item meuItem;
        meuItem = vitrine.buscarItemPeloNome(title);
        if(meuItem == null) {
            throw new UnavailableItemError("Item não encontrado | Título inexistente");
        }
        return meuItem;
    }

    /**
     * Método que adiciona um item novo na vitrine da biblioteca.
     *
     * @param actE  O evento de ação ActionEvent.
     * @param titulo O título do item String.
     * @param autor O autor do item String.
     * @param anoPublicacao O ano de publicação do item String.
     * @param quantidaDisponivel A quantidade disponível do item String.
     * @param opc1 Opcional 1 (depende do tipo de item) String.
     * @param opc2 Opcional 2 (depende do tipo de item) String.
     * @param tipo O tipo de item (Livro/Revista/CD) String.
     * @throws InvalidInformationsError Se as informações fornecidas forem inválidas.
    */
    public void adicionarItem(ActionEvent actE, String titulo, String autor, String anoPublicacao, String quantidaDisponivel, String opc1, String opc2, String tipo) throws InvalidInformationsError {
        if(tipo.equalsIgnoreCase("Livro")) {
            Livro livro = new Livro(titulo,autor,anoPublicacao,Integer.parseInt(quantidaDisponivel),opc1,opc2);
            vitrine.setNewLivro(livro);
        } else if(tipo.equalsIgnoreCase("Revista")) {
            Revista revista = new Revista(titulo,autor,anoPublicacao,Integer.parseInt(quantidaDisponivel),Integer.parseInt(opc1),Integer.parseInt(opc2));
            vitrine.setNewRevista(revista);
        } else if(tipo.equalsIgnoreCase("CD")) {
            CD cd = new CD(titulo,autor,anoPublicacao,Integer.parseInt(quantidaDisponivel),Integer.parseInt(opc1),opc2);
            vitrine.setNewCd(cd);
        }
        throw new InvalidInformationsError("Informações inválidas | Tipo inexistente");
    }

    /**
     * Método que deloga um conta e volta para o login
     * 
     * @param event um envento de ação ActionEvent.
     */
    public void sair(ActionEvent event) {
        meuCliente = null;
        DBUtils.changeScene(event,"login-view.fxml","Gerenciamento de biblioteca | Log-In page","vazio");
    }
}
