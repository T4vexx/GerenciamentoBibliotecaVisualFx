package com.otavio.biblioteca.individuos;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDB {
    private HashMap<String,Alunos> alunos = new HashMap<String,Alunos>();
    private HashMap<String,Professor> professor = new HashMap<String,Professor>();
    private HashMap<String,AssessorTecnico> assessoresTecnicos = new HashMap<String,AssessorTecnico>();

    public UsuariosDB() {
        Scanner usuarios = null;
        String linha;
        String[] campos;
        try {
            usuarios = new Scanner(new File("C:"+File.separator+"Users"+File.separator+"tavin"+File.separator+"OneDrive"+File.separator+"Desktop"+File.separator+"3semestre"+File.separator+"GerenciamentoParaBiblioteca"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"org"+File.separator+"tavex"+File.separator+"assets"+File.separator+"usuarios.txt"));
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

    public boolean addNewProfessor(Professor p,String m) {
        if(professor.containsKey(m) || alunos.containsKey(m) || assessoresTecnicos.containsKey(m)) {
            System.out.println("Conta já existente");
            return false;
        } else {
            professor.put(m,p);
            return true;
        }

    }
    public boolean addNewAlunos(Alunos a,String m) {
        if(professor.containsKey(m) || alunos.containsKey(m) || assessoresTecnicos.containsKey(m)) {
            System.out.println("Conta já existente");
            return false;
        } else {
            alunos.put(m,a);
            return true;
        }

    }
    public boolean addNewAssesorTecnico(AssessorTecnico at,String m) {
        if(professor.containsKey(m) || alunos.containsKey(m) || assessoresTecnicos.containsKey(m)) {
            System.out.println("Conta já existente");
            return false;
        } else {
            assessoresTecnicos.put(m,at);
            return true;
        }
    }
}
