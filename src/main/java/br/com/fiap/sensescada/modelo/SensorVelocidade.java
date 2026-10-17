package br.com.fiap.sensescada.modelo;

/**
 * Sensor óptico de velocidade dos degraus.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class SensorVelocidade extends Sensor {
    private double velocidadeNominal;

    /**
     * Cria um sensor de velocidade.
     *
     * @param codigo            código do sensor
     * @param posicao           lugar onde o sensor está
     * @param dataCalibracao    data da última calibração
     * @param velocidadeNominal velocidade normal da escada, em m/s
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public SensorVelocidade(String codigo, String posicao, String dataCalibracao, double velocidadeNominal) {
        super(codigo, posicao, dataCalibracao, 2, 5, 10);
        if (velocidadeNominal <= 0) {
            throw new IllegalArgumentException("A velocidade nominal deve ser maior que zero.");
        }
        this.velocidadeNominal = velocidadeNominal;
    }

    /**
     * Retorna a velocidade nominal.
     *
     * @return velocidade nominal em m/s
     */
    public double getVelocidadeNominal() {
        return velocidadeNominal;
    }

    /**
     * Calcula a diferença entre a velocidade real e a nominal, em %.
     *
     * @param leitura leitura com todos os valores
     * @return diferença de velocidade em %
     */
    @Override
    public double lerValor(Leitura leitura) {
        double diferenca = Math.abs(velocidadeNominal - leitura.getVelocidade());
        return diferenca / velocidadeNominal * 100;
    }

    /**
     * Retorna o que o sensor mede.
     *
     * @return "Velocidade"
     */
    @Override
    public String getTipo() {
        return "Velocidade";
    }

    /**
     * Retorna a unidade de medida.
     *
     * @return "% de diferença"
     */
    @Override
    public String getUnidade() {
        return "% de diferença";
    }

    /**
     * Retorna a causa provável de acordo com o nível.
     *
     * @param nivel nível de alerta (0 a 3)
     * @return texto com a causa provável
     */
    @Override
    public String diagnosticar(int nivel) {
        if (nivel == NORMAL) {
            return "Velocidade normal.";
        } else if (nivel == ATENCAO) {
            return "Pequena perda de velocidade. Acompanhar.";
        } else if (nivel == ALERTA) {
            return "Escada desacelerando. Possível atrito ou desgaste.";
        } else {
            return "Perda grande de velocidade. Possível travamento.";
        }
    }

    /**
     * Retorna os dados do sensor em texto.
     *
     * @return texto com os dados do sensor
     */
    @Override
    public String toString() {
        return super.toString() + " | velocidade nominal: " + velocidadeNominal + " m/s";
    }
}
