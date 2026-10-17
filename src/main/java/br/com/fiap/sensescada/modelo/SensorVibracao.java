package br.com.fiap.sensescada.modelo;

/**
 * Sensor de vibração do motor ou do redutor da escada (em mm/s).
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class SensorVibracao extends Sensor {
    private String componente;

    /**
     * Cria um sensor de vibração com os limites padrão (1,8 / 2,8 / 4,5 mm/s).
     *
     * @param codigo         código do sensor
     * @param posicao        lugar onde o sensor está
     * @param dataCalibracao data da última calibração
     * @param componente     "Motor" ou "Redutor"
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public SensorVibracao(String codigo, String posicao, String dataCalibracao, String componente) {
        super(codigo, posicao, dataCalibracao, 1.8, 2.8, 4.5);
        this.componente = componente;
    }

    /**
     * Cria um sensor de vibração com limites escolhidos.
     *
     * @param codigo         código do sensor
     * @param posicao        lugar onde o sensor está
     * @param dataCalibracao data da última calibração
     * @param componente     "Motor" ou "Redutor"
     * @param limiteAtencao  limite para ATENÇÃO
     * @param limiteAlerta   limite para ALERTA
     * @param limiteCritico  limite para CRÍTICO
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public SensorVibracao(String codigo, String posicao, String dataCalibracao, String componente,
                          double limiteAtencao, double limiteAlerta, double limiteCritico) {
        super(codigo, posicao, dataCalibracao, limiteAtencao, limiteAlerta, limiteCritico);
        this.componente = componente;
    }

    /**
     * Retorna a parte da escada que o sensor mede.
     *
     * @return "Motor" ou "Redutor"
     */
    public String getComponente() {
        return componente;
    }

    /**
     * Pega a vibração da leitura.
     *
     * @param leitura leitura com todos os valores
     * @return vibração em mm/s
     */
    @Override
    public double lerValor(Leitura leitura) {
        return leitura.getVibracao();
    }

    /**
     * Retorna o que o sensor mede.
     *
     * @return "Vibração"
     */
    @Override
    public String getTipo() {
        return "Vibração";
    }

    /**
     * Retorna a unidade de medida.
     *
     * @return "mm/s"
     */
    @Override
    public String getUnidade() {
        return "mm/s";
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
            return "Vibração normal.";
        } else if (nivel == ATENCAO) {
            return "Vibração um pouco alta. Acompanhar.";
        } else if (nivel == ALERTA) {
            return "Possível desalinhamento ou desgaste de rolamento.";
        } else {
            return "Vibração muito alta. Risco de quebra de rolamento ou eixo.";
        }
    }

    /**
     * Retorna os dados do sensor em texto.
     *
     * @return texto com os dados do sensor
     */
    @Override
    public String toString() {
        return super.toString() + " | componente: " + componente;
    }
}
