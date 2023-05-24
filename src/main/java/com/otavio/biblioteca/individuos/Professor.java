package com.otavio.biblioteca.individuos;

/**
 * Professor
 * A classe Professor representa um objeto Professor, que herda as propriedades da classe pai Usuario.
 * Possui informações especializadas sobre o departamento e a titulação do professor.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class Professor extends Usuario {
    private String departamento;
    private String titulacao;

    /**
     * Construtor da classe Professor que inicializa as propriedades do professor.
     *
     * @param nome O nome do professor String.
     * @param senha A senha do professor String.
     * @param matricula A matrícula do professor String.
     * @param departamento O departamento em que o professor atua String.
     * @param titulacao A titulação do professor String.
     */
    public Professor(String nome,String senha,String matricula,String departamento,String titulacao) {
        super(nome,senha,matricula);
        this.departamento = departamento;
        this.titulacao = titulacao;
    }

    /**
     * Método que calcula a multa com base no número de dias de atraso.
     *
     * @param dias O número de dias de atraso int.
     * @return O valor da multa calculado com base nos dias de atraso double.
     */
    @Override
    public double calcularMulta(int dias) {
        return 6.25 * dias;
    }
}
