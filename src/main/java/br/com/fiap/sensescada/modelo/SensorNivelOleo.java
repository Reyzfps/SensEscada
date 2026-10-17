package br.com.fiap.sensescada.modelo;

/**
 * Sensor de nível de óleo do redutor da escada (em %).
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class SensorNivelOleo extends Sensor {
    private double capacidadeLitros;

    /**
     * Cria um sensor de nível de óleo.
     *
     * @param codigo           código do sensor
     * @param posicao          lugar onde o sensor está
     * @param dataCalibracao   data da última calibração
     * @param capacidadeLitros capacidade do reservatório, em litros
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public SensorNivelOleo(String codigo, String posicao, String dataCalibracao, double capacidadeLitros) {
        super(codigo, posicao, dataCalibracao, 50, 35, 20);
        if (capacidadeLitros <= 0) {
            throw new IllegalArgumentException("A capacidade do reservatório deve ser maior que zero.");
        }
        this.capacidadeLitros = capacidadeLitros;
    }

    /**
     * Retorna a capacidade do reservatório.
     *
     * @return capacidade em litros
     */
    public double getCapacidadeLitros() {
        return capacidadeLitros;
    }

    /**
     * Calcula quantos litros de óleo há no reservatório.
     *
     * @param leitura leitura com todos os valores
     * @return volume de óleo em litros
     */
    public double calcularVolume(Leitura leitura) {
        return capacidadeLitros * leitura.getNivelOleo() / 100;
    }

    /**
     * Pega o nível de óleo da leitura.
     *
     * @param leitura leitura com todos os valores
     * @return nível de óleo em %
     */
    @Override
    public double lerValor(Leitura leitura) {
        return leitura.getNivelOleo();
    }

    /**
     * Avalia o nível de óleo. Aqui a regra é ao contrário da classe Sensor:
     *
     * @param leitura leitura a ser avaliada
     * @return nível de alerta (0 a 3)
     */
    @Override
    public int avaliar(Leitura leitura) {
        double nivelOleo = lerValor(leitura);

        if (nivelOleo <= getLimiteCritico()) {
            return CRITICO;
        } else if (nivelOleo <= getLimiteAlerta()) {
            return ALERTA;
        } else if (nivelOleo <= getLimiteAtencao()) {
            return ATENCAO;
        } else {
            return NORMAL;
        }
    }

    /**
     * Retorna o que o sensor mede.
     *
     * @return "Nível de óleo"
     */
    @Override
    public String getTipo() {
        return "Nível de óleo";
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
            return "Óleo em nível bom.";
        } else if (nivel == ATENCAO) {
            return "Óleo baixando. Programar reposição.";
        } else if (nivel == ALERTA) {
            return "Óleo baixo. Repor e procurar vazamento.";
        } else {
            return "Óleo muito baixo. Risco de danificar o redutor.";
        }
    }

    /**
     * Retorna os dados do sensor em texto.
     *
     * @return texto com os dados do sensor
     */
    @Override
    public String toString() {
        return super.toString() + " | reservatório: " + capacidadeLitros + " L";
    }
}
