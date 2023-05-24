package com.otavio.biblioteca.individuos;

import com.otavio.exceptions.InvalidInformationsError;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * UsuariosDB
 * Classe usada como "banco de dados" para armazenar os alunos professores e assessores tecnicos.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class UsuariosDB {
    private HashMap<String,Alunos> alunos = new HashMap<String,Alunos>();
    private HashMap<String,Professor> professor = new HashMap<String,Professor>();
    private HashMap<String,AssessorTecnico> assessoresTecnicos = new HashMap<String,AssessorTecnico>();


    /**
     * Construtor que inicia os HashMaps dos Usuario com dados de um aquivo.
     * Substituir pelo local onde esta o usuarios.txt.
     */
    public UsuariosDB() {
        Scanner usuarios = null;
        String linha;
        String[] campos;
        try {
            usuarios = new Scanner(new File("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoDeBibliotecaGUI"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"otavio"+File.separator+"assets"+File.separator+"usuarios.txt"));
            //usuarios = new Scanner(new File("C:\\Users\\tavexx\\Desktop\\BibliotecaVisual\\src\\main\\java\\com\\otavio\\assets\\usuarios.txt"));
            while(usuarios.hasNextLine()) {
                linha = usuarios.nextLine();
                campos = linha.split("#");
                if(campos[0].equalsIgnoreCase("aluno")) {
                    Alunos aluno = new Alunos(campos[1],campos[3],campos[2],campos[4],campos[5]);
                    addNewAlunos(aluno,campos[3]);
                } else if (campos[0].equalsIgnoreCase("professor")) {
                    Professor prof = new Professor(campos[1],campos[3],campos[2],campos[4],campos[5]);
                    addNewProfessor(prof,campos[3]);
                } else if (campos[0].equalsIgnoreCase("assessor")) {
                    AssessorTecnico at = new AssessorTecnico(campos[1],campos[3],campos[2],campos[4]);
                    addNewAssesorTecnico(at,campos[3]);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex.getMessage());
            Logger.getLogger(UsuariosDB.class.getName()).log(Level.SEVERE , null ,ex);
        } finally {
            usuarios.close();
        }
    }

    /**
     * Método que busca dentre os hashmaps se existe algum usuario e valida sua senha retornando o próprio Usuario.
     * 
     * @param classificacao classificação entre alunos/professor/assessorTecnico String.
     * @param matricula matricula de um usuario String.
     * @param senha senha de um usuario String.
     * @return retorna um Usuario.
     */
    public Usuario login(String classificacao, String matricula, String senha) {
        if(classificacao.equalsIgnoreCase("aluno")) {
            Alunos aluno;
            aluno = alunos.get(matricula);
            if(aluno.validarSenha(senha)) {
                return aluno;
            } else {
                return null;
            }
        } else if(classificacao.equalsIgnoreCase("professor")) {
            Professor prof;
            prof = professor.get(matricula);
            if(prof.validarSenha(senha)) {
                return prof;
            } else {
                return null;
            }
        } else if(classificacao.equalsIgnoreCase("assessor")) {
            AssessorTecnico assessor;
            assessor = assessoresTecnicos.get(matricula);
            if(assessor.validarSenha(senha)) {
                return assessor;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Método que adiciona um novo professor.
     * 
     * @param p um professor do tipo Professor.
     * @param m a matricula do professor String.
     * @throws InvalidInformationsError erro caso ja exista a conta.
     */
    public void addNewProfessor(Professor p,String m) throws InvalidInformationsError {
        if(professor.containsKey(m) || alunos.containsKey(m) || assessoresTecnicos.containsKey(m)) {
            throw new InvalidInformationsError("Conta já existente");
        } else {
            professor.put(m,p);
        }

    }

    /**
     * Método que adiciona um novo aluno.
     * 
     * @param a um aluno do tipo Aluno.
     * @param m a matricula do aluno String.
     * @throws InvalidInformationsError erro caso ja exista a conta.
     */
    public void addNewAlunos(Alunos a,String m) throws InvalidInformationsError {
        if(professor.containsKey(m) || alunos.containsKey(m) || assessoresTecnicos.containsKey(m)) {
            throw new InvalidInformationsError("Conta já existente");
        } else {
            alunos.put(m,a);
        }

    }

    /**
     * Método que adiciona um novo assessor tecnico.
     * 
     * @param at um assessor tecnico do tipo AssessorTecnico.
     * @param m a matricula do assessor tecnico String.
     * @throws InvalidInformationsError erro caso ja exista a conta.
     */
    public void addNewAssesorTecnico(AssessorTecnico at,String m) throws InvalidInformationsError {
        if(professor.containsKey(m) || alunos.containsKey(m) || assessoresTecnicos.containsKey(m)) {
            throw new InvalidInformationsError("Conta já existente");
        } else {
            assessoresTecnicos.put(m,at);
        }
    }
}
