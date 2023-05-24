package com.otavio.biblioteca.individuos;

/**
 * Alunos
 * A classe Alunos representa um objeto Aluno, que herda as propriedades da classe pai Usuario.
 * Possui informações especializadas sobre o curso e o período do aluno.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class Alunos extends Usuario {
    private String curso;
    private String periodo;

    /**
     * Construtor da classe Alunos que inicializa as propriedades do aluno.
     *
     * @param nome O nome do aluno String.
     * @param senha A senha do aluno String.
     * @param matricula A matrícula do aluno String.
     * @param curso O curso do aluno String.
     * @param periodo O período do aluno String.
     */
    public Alunos(String nome,String senha,String matricula,String curso,String periodo) {
        super(nome,senha,matricula);
        this.curso = curso;
        this.periodo = periodo;
    }


    /**
     * Método que calcula a multa com base no número de dias de atraso.
     *
     * @param dias O número de dias de atraso int.
     * @return O valor da multa calculado com base nos dias de atraso double.
     */
    @Override
    public double calcularMulta(int dias) {
        return 5 * dias;
    }
}
