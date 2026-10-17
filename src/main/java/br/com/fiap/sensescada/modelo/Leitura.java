package br.com.fiap.sensescada.modelo;

/**
 * Representa uma leitura dos sensores da escada em um determinado momento.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class Leitura {
    private String dataHora;
    private double vibracao;
    private double temperatura;
    private double corrente;
    private double tensao;
    private double nivelOleo;
    private double velocidade;

    /**
     * Cria uma leitura com os valores dos sensores.
     *
     * @param dataHora    data e hora da leitura
     * @param vibracao    vibração em mm/s
     * @param temperatura temperatura em graus Celsius
     * @param corrente    corrente em ampères
     * @param tensao      tensão em volts
     * @param nivelOleo   nível de óleo em %
     * @param velocidade  velocidade em m/s
     * @throws IllegalArgumentException se algum valor for impossível (negativo ou óleo acima de 100%)
     */
    public Leitura(String dataHora, double vibracao, double temperatura, double corrente,
                   double tensao, double nivelOleo, double velocidade) {
        if (dataHora == null || dataHora.isEmpty()) {
            throw new IllegalArgumentException("A data e hora da leitura são obrigatórias.");
        }
        if (vibracao < 0 || corrente < 0 || tensao < 0 || velocidade < 0) {
            throw new IllegalArgumentException("Vibração, corrente, tensão e velocidade não podem ser negativas.");
        }
        if (nivelOleo < 0 || nivelOleo > 100) {
            throw new IllegalArgumentException("O nível de óleo deve estar entre 0 e 100%. Valor recebido: " + nivelOleo);
        }
        this.dataHora = dataHora;
        this.vibracao = vibracao;
        this.temperatura = temperatura;
        this.corrente = corrente;
        this.tensao = tensao;
        this.nivelOleo = nivelOleo;
        this.velocidade = velocidade;
    }

    /**
     * Retorna a data e hora da leitura.
     *
     * @return data e hora
     */
    public String getDataHora() {
        return dataHora;
    }

    /**
     * Retorna a vibração.
     *
     * @return vibração em mm/s
     */
    public double getVibracao() {
        return vibracao;
    }

    /**
     * Retorna a temperatura.
     *
     * @return temperatura em graus Celsius
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * Retorna a corrente.
     *
     * @return corrente em ampères
     */
    public double getCorrente() {
        return corrente;
    }

    /**
     * Retorna a tensão.
     *
     * @return tensão em volts
     */
    public double getTensao() {
        return tensao;
    }

    /**
     * Retorna o nível de óleo.
     *
     * @return nível de óleo em %
     */
    public double getNivelOleo() {
        return nivelOleo;
    }

    /**
     * Retorna a velocidade dos degraus.
     *
     * @return velocidade em m/s
     */
    public double getVelocidade() {
        return velocidade;
    }

    /**
     * Calcula a potência do motor no momento da leitura.
     *
     * @return potência em kVA
     */
    public double calcularPotencia() {
        return Math.sqrt(3) * tensao * corrente / 1000;
    }

    /**
     * Retorna os dados da leitura em texto.
     *
     * @return texto com os valores da leitura
     */
    @Override
    public String toString() {
        return "Leitura " + dataHora
                + " | vibração " + vibracao + " mm/s"
                + " | temperatura " + temperatura + " °C"
                + " | corrente " + corrente + " A"
                + " | tensão " + tensao + " V"
                + " | óleo " + nivelOleo + "%"
                + " | velocidade " + velocidade + " m/s";
    }
}
