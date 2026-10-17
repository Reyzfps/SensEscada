package br.com.fiap.sensescada.modelo;

/**
 * Sensor de temperatura do motor da escada (em graus Celsius).
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class SensorTemperatura extends Sensor {

    /**
     * Cria um sensor de temperatura com os limites padrão (70 / 80 / 90 °C).
     *
     * @param codigo         código do sensor
     * @param posicao        lugar onde o sensor está
     * @param dataCalibracao data da última calibração
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public SensorTemperatura(String codigo, String posicao, String dataCalibracao) {
        super(codigo, posicao, dataCalibracao, 70, 80, 90);
    }

    /**
     * Pega a temperatura da leitura.
     *
     * @param leitura leitura com todos os valores
     * @return temperatura em graus Celsius
     */
    @Override
    public double lerValor(Leitura leitura) {
        return leitura.getTemperatura();
    }

    /**
     * Retorna o que o sensor mede.
     *
     * @return "Temperatura"
     */
    @Override
    public String getTipo() {
        return "Temperatura";
    }

    /**
     * Retorna a unidade de medida.
     *
     * @return "°C"
     */
    @Override
    public String getUnidade() {
        return "°C";
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
            return "Temperatura normal.";
        } else if (nivel == ATENCAO) {
            return "Motor um pouco quente. Verificar ventilação.";
        } else if (nivel == ALERTA) {
            return "Aquecimento anormal. Possível atrito ou falta de lubrificação.";
        } else {
            return "Superaquecimento. Risco de queimar o motor.";
        }
    }
}
