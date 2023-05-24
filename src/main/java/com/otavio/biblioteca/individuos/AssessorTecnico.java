package com.otavio.biblioteca.individuos;

/**
 * Assessor Tecnico
 * A classe AssessorTecnico representa um objeto Assessor Técnico, que herda as propriedades da classe pai Usuario.
 * Possui informações especializadas sobre a seção em que o assessor trabalha.
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 */
public class AssessorTecnico extends Usuario {
    private String secao;

    /**
     * Construtor da classe AssessorTecnico que inicializa as propriedades do assessor técnico.
     *
     * @param nome O nome do assessor técnico String.
     * @param senha A senha do assessor técnico String.
     * @param matricula A matrícula do assessor técnico String.
     * @param secao A seção em que o assessor técnico trabalha String.
     */
    public AssessorTecnico(String nome,String senha,String matricula,String secao) {
        super(nome,senha,matricula);
        this.secao = secao;
    }

    /**
     * Método que calcula a multa com base no número de dias de atraso.
     *
     * @param dias O número de dias de atraso int.
     * @return O valor da multa calculado com base nos dias de atraso double.
     */
    @Override
    public double calcularMulta(int dias) {
        return 5.75 * dias;
    }
}
