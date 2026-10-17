package br.com.fiap.sensescada.modelo;

/**
 * Classe abstrata que representa um sensor instalado na escada rolante.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public abstract class Sensor {

    /** Nível 0: valor normal. */
    public static final int NORMAL = 0;
    /** Nível 1: atenção, acompanhar o equipamento. */
    public static final int ATENCAO = 1;
    /** Nível 2: alerta, programar uma inspeção. */
    public static final int ALERTA = 2;
    /** Nível 3: crítico, manutenção urgente. */
    public static final int CRITICO = 3;

    private String codigo;
    private String posicao;
    private String dataCalibracao;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;

    /**
     * Construtor usado pelas subclasses.
     *
     * @param codigo         código do sensor
     * @param posicao        lugar da escada onde o sensor está
     * @param dataCalibracao data da última calibração
     * @param limiteAtencao  limite para ATENÇÃO
     * @param limiteAlerta   limite para ALERTA
     * @param limiteCritico  limite para CRÍTICO
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Sensor(String codigo, String posicao, String dataCalibracao,
                  double limiteAtencao, double limiteAlerta, double limiteCritico) {
        setCodigo(codigo);
        setPosicao(posicao);
        setDataCalibracao(dataCalibracao);
        setLimites(limiteAtencao, limiteAlerta, limiteCritico);
    }

    /**
     * Pega na leitura o valor que este sensor mede.
     *
     * @param leitura leitura com todos os valores
     * @return valor medido por este sensor
     */
    public abstract double lerValor(Leitura leitura);

    /**
     * Retorna o que o sensor mede, por exemplo "Vibração".
     *
     * @return nome da grandeza medida
     */
    public abstract String getTipo();

    /**
     * Retorna a unidade de medida, por exemplo "mm/s".
     *
     * @return unidade de medida
     */
    public abstract String getUnidade();

    /**
     * Retorna a causa provável do problema de acordo com o nível.
     *
     * @param nivel nível de alerta (0 a 3)
     * @return texto com a causa provável
     */
    public abstract String diagnosticar(int nivel);

    /**
     * Avalia a leitura e retorna o nível de alerta (0 a 3).
     *
     * @param leitura leitura a ser avaliada
     * @return nível de alerta: NORMAL, ATENCAO, ALERTA ou CRITICO
     */
    public int avaliar(Leitura leitura) {
        double valor = lerValor(leitura);

        if (valor >= limiteCritico) {
            return CRITICO;
        } else if (valor >= limiteAlerta) {
            return ALERTA;
        } else if (valor >= limiteAtencao) {
            return ATENCAO;
        } else {
            return NORMAL;
        }
    }

    /**
     * Transforma o número do nível em texto.
     *
     * @param nivel nível de alerta (0 a 3)
     * @return "NORMAL", "ATENÇÃO", "ALERTA" ou "CRÍTICO"
     */
    public static String nomeDoNivel(int nivel) {
        if (nivel == NORMAL) {
            return "NORMAL";
        } else if (nivel == ATENCAO) {
            return "ATENÇÃO";
        } else if (nivel == ALERTA) {
            return "ALERTA";
        } else {
            return "CRÍTICO";
        }
    }

    /**
     * Monta uma linha de relatório com o valor medido, o nível e o diagnóstico.
     *
     * @param leitura leitura a ser avaliada
     * @return texto do laudo
     */
    public String gerarLaudo(Leitura leitura) {
        int nivel = avaliar(leitura);
        String valor = String.format("%.2f", lerValor(leitura));
        return "  [" + codigo + "] " + getTipo() + ": " + valor + " " + getUnidade()
                + " -> " + nomeDoNivel(nivel) + " - " + diagnosticar(nivel);
    }

    /**
     * Retorna o código do sensor.
     *
     * @return código do sensor
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Altera o código do sensor.
     *
     * @param codigo novo código
     * @throws IllegalArgumentException se o código estiver vazio
     */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("O código do sensor é obrigatório.");
        }
        this.codigo = codigo;
    }

    /**
     * Retorna a posição do sensor.
     *
     * @return lugar onde o sensor está
     */
    public String getPosicao() {
        return posicao;
    }

    /**
     * Altera a posição do sensor.
     *
     * @param posicao nova posição
     * @throws IllegalArgumentException se a posição estiver vazia
     */
    public void setPosicao(String posicao) {
        if (posicao == null || posicao.isEmpty()) {
            throw new IllegalArgumentException("A posição do sensor é obrigatória.");
        }
        this.posicao = posicao;
    }

    /**
     * Retorna a data da última calibração.
     *
     * @return data de calibração
     */
    public String getDataCalibracao() {
        return dataCalibracao;
    }

    /**
     * Altera a data da última calibração.
     *
     * @param dataCalibracao nova data
     * @throws IllegalArgumentException se a data estiver vazia
     */
    public void setDataCalibracao(String dataCalibracao) {
        if (dataCalibracao == null || dataCalibracao.isEmpty()) {
            throw new IllegalArgumentException("A data de calibração é obrigatória.");
        }
        this.dataCalibracao = dataCalibracao;
    }

    /**
     * Retorna o limite de ATENÇÃO.
     *
     * @return limite de atenção
     */
    public double getLimiteAtencao() {
        return limiteAtencao;
    }

    /**
     * Retorna o limite de ALERTA.
     *
     * @return limite de alerta
     */
    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    /**
     * Retorna o limite de CRÍTICO.
     *
     * @return limite crítico
     */
    public double getLimiteCritico() {
        return limiteCritico;
    }

    /**
     * Altera os três limites do sensor.
     *
     * @param limiteAtencao novo limite de ATENÇÃO
     * @param limiteAlerta  novo limite de ALERTA
     * @param limiteCritico novo limite de CRÍTICO
     * @throws IllegalArgumentException se algum limite for negativo
     */
    public void setLimites(double limiteAtencao, double limiteAlerta, double limiteCritico) {
        if (limiteAtencao < 0 || limiteAlerta < 0 || limiteCritico < 0) {
            throw new IllegalArgumentException("Os limites do sensor não podem ser negativos.");
        }
        this.limiteAtencao = limiteAtencao;
        this.limiteAlerta = limiteAlerta;
        this.limiteCritico = limiteCritico;
    }

    /**
     * Retorna os dados do sensor em texto.
     *
     * @return texto com os dados do sensor
     */
    @Override
    public String toString() {
        return "Sensor de " + getTipo() + " [" + codigo + "] | posição: " + posicao
                + " | limites: " + limiteAtencao + " / " + limiteAlerta + " / " + limiteCritico + " " + getUnidade()
                + " | calibrado em " + dataCalibracao;
    }
}
