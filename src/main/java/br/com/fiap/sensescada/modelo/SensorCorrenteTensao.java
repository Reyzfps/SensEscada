package br.com.fiap.sensescada.modelo;

/**
 * Sensor de corrente e tensão do painel elétrico da escada.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class SensorCorrenteTensao extends Sensor {
    private double correnteNominal;
    private double tensaoNominal;

    /**
     * Cria um sensor de corrente e tensão.
     *
     * @param codigo          código do sensor
     * @param posicao         lugar onde o sensor está
     * @param dataCalibracao  data da última calibração
     * @param correnteNominal corrente normal do motor, em ampères
     * @param tensaoNominal   tensão normal, em volts
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public SensorCorrenteTensao(String codigo, String posicao, String dataCalibracao,
                                double correnteNominal, double tensaoNominal) {
        super(codigo, posicao, dataCalibracao, 100, 110, 125);
        if (correnteNominal <= 0 || tensaoNominal <= 0) {
            throw new IllegalArgumentException("Corrente e tensão nominais devem ser maiores que zero.");
        }
        this.correnteNominal = correnteNominal;
        this.tensaoNominal = tensaoNominal;
    }

    /**
     * Retorna a corrente nominal.
     *
     * @return corrente nominal em ampères
     */
    public double getCorrenteNominal() {
        return correnteNominal;
    }

    /**
     * Retorna a tensão nominal.
     *
     * @return tensão nominal em volts
     */
    public double getTensaoNominal() {
        return tensaoNominal;
    }

    /**
     * Calcula quanto a tensão da leitura está diferente da tensão nominal.
     *
     * @param leitura leitura com todos os valores
     * @return diferença em %
     */
    public double calcularDesvioTensao(Leitura leitura) {
        double diferenca = Math.abs(leitura.getTensao() - tensaoNominal);
        return diferenca / tensaoNominal * 100;
    }

    /**
     * Calcula a carga do motor (corrente medida / corrente nominal x 100).
     *
     * @param leitura leitura com todos os valores
     * @return carga do motor em %
     */
    @Override
    public double lerValor(Leitura leitura) {
        return leitura.getCorrente() / correnteNominal * 100;
    }

    /**
     * Avalia a carga do motor e também a tensão.
     *
     * @param leitura leitura a ser avaliada
     * @return nível de alerta (0 a 3)
     */
    @Override
    public int avaliar(Leitura leitura) {
        int nivel = super.avaliar(leitura);

        if (calcularDesvioTensao(leitura) > 10 && nivel < ALERTA) {
            nivel = ALERTA;
        }
        return nivel;
    }

    /**
     * Retorna o que o sensor mede.
     *
     * @return "Carga elétrica"
     */
    @Override
    public String getTipo() {
        return "Carga elétrica";
    }

    /**
     * Retorna a unidade de medida.
     *
     * @return "%"
     */
    @Override
    public String getUnidade() {
        return "%";
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
            return "Parte elétrica normal.";
        } else if (nivel == ATENCAO) {
            return "Motor trabalhando no limite. Acompanhar.";
        } else if (nivel == ALERTA) {
            return "Sobrecarga ou tensão instável. Verificar inversor e contatores.";
        } else {
            return "Sobrecarga forte. Risco de queimar o motor.";
        }
    }

    /**
     * Retorna os dados do sensor em texto.
     *
     * @return texto com os dados do sensor
     */
    @Override
    public String toString() {
        return super.toString() + " | nominal: " + correnteNominal + " A / " + tensaoNominal + " V";
    }
}
