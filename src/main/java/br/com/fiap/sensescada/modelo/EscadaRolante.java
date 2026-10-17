package br.com.fiap.sensescada.modelo;

import java.util.ArrayList;

/**
 * Representa uma escada rolante. É um tipo de Equipamento (herança).
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class EscadaRolante extends Equipamento {
    private double desnivel;
    private double velocidadeNominal;
    private String sentido;
    private String grupo;
    private ArrayList<Sensor> sensores;
    private ArrayList<Leitura> leituras;

    /**
     * Cria uma escada rolante com velocidade nominal de 0,5 m/s.
     *
     * @param codigo        código da escada
     * @param fabricante    fabricante
     * @param modelo        modelo
     * @param anoInstalacao ano de instalação
     * @param localizacao   onde a escada está
     * @param desnivel      altura que a escada vence, em metros
     * @param sentido       "Subida", "Descida" ou "Reversível"
     * @param grupo         grupo de manutenção
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public EscadaRolante(String codigo, String fabricante, String modelo, int anoInstalacao,
                         Localizacao localizacao, double desnivel, String sentido, String grupo) {
        this(codigo, fabricante, modelo, anoInstalacao, localizacao, desnivel, 0.5, sentido, grupo);
    }

    /**
     * Cria uma escada rolante informando a velocidade nominal.
     *
     * @param codigo            código da escada
     * @param fabricante        fabricante
     * @param modelo            modelo
     * @param anoInstalacao     ano de instalação
     * @param localizacao       onde a escada está
     * @param desnivel          altura que a escada vence, em metros
     * @param velocidadeNominal velocidade normal, em m/s
     * @param sentido           "Subida", "Descida" ou "Reversível"
     * @param grupo             grupo de manutenção
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public EscadaRolante(String codigo, String fabricante, String modelo, int anoInstalacao,
                         Localizacao localizacao, double desnivel, double velocidadeNominal,
                         String sentido, String grupo) {
        super(codigo, fabricante, modelo, anoInstalacao, localizacao);
        setDesnivel(desnivel);
        setVelocidadeNominal(velocidadeNominal);
        setSentido(sentido);
        setGrupo(grupo);
        this.sensores = new ArrayList<>();
        this.leituras = new ArrayList<>();
    }

    /**
     * Instala um sensor na escada.
     *
     * @param sensor sensor a ser instalado
     * @throws IllegalArgumentException se o sensor for nulo
     */
    public void adicionarSensor(Sensor sensor) {
        if (sensor == null) {
            throw new IllegalArgumentException("O sensor não pode ser nulo.");
        }
        sensores.add(sensor);
    }

    /**
     * Guarda uma nova leitura no histórico da escada.
     *
     * @param leitura leitura recebida dos sensores
     * @throws IllegalArgumentException se a leitura for nula
     */
    public void registrarLeitura(Leitura leitura) {
        if (leitura == null) {
            throw new IllegalArgumentException("A leitura não pode ser nula.");
        }
        leituras.add(leitura);
    }

    /**
     * Cria uma leitura com os valores informados e guarda no histórico.
     *
     * @param dataHora    data e hora da leitura
     * @param vibracao    vibração em mm/s
     * @param temperatura temperatura em graus Celsius
     * @param corrente    corrente em ampères
     * @param tensao      tensão em volts
     * @param nivelOleo   nível de óleo em %
     * @param velocidade  velocidade em m/s
     * @throws IllegalArgumentException se algum valor for impossível
     */
    public void registrarLeitura(String dataHora, double vibracao, double temperatura, double corrente,
                                 double tensao, double nivelOleo, double velocidade) {
        Leitura leitura = new Leitura(dataHora, vibracao, temperatura, corrente, tensao, nivelOleo, velocidade);
        registrarLeitura(leitura);
    }

    /**
     * Retorna a leitura mais recente.
     *
     * @return última leitura, ou null se a escada ainda não tiver leituras
     */
    public Leitura getUltimaLeitura() {
        if (leituras.isEmpty()) {
            return null;
        }
        return leituras.get(leituras.size() - 1);
    }

    /**
     * Avalia a escada pela última leitura.
     *
     * @return pior nível de alerta entre os sensores (0 a 3)
     */
    public int avaliarCondicao() {
        Leitura ultima = getUltimaLeitura();
        if (ultima == null) {
            return Sensor.NORMAL;
        }

        int piorNivel = Sensor.NORMAL;
        for (Sensor sensor : sensores) {
            int nivel = sensor.avaliar(ultima);
            if (nivel > piorNivel) {
                piorNivel = nivel;
            }
        }
        return piorNivel;
    }

    /**
     * Retorna o peso do grupo de manutenção, usado no cálculo da criticidade.
     *
     * @return peso do grupo
     */
    public double getPesoGrupo() {
        if (grupo.equals("Crítica A")) {
            return 1.5;
        } else if (grupo.equals("Crítica B")) {
            return 1.35;
        } else if (grupo.equals("Não crítica A")) {
            return 1.1;
        } else {
            return 1.0;
        }
    }

    /**
     * Calcula o índice de criticidade da escada, de 0 a 100.
     *
     * @return índice de criticidade de 0 a 100
     */
    @Override
    public double calcularCriticidade() {
        if (getStatus().equals("Parada por falha")) {
            return 100;
        }

        Leitura ultima = getUltimaLeitura();
        if (ultima == null || sensores.isEmpty()) {
            return 0;
        }

        int soma = 0;
        for (Sensor sensor : sensores) {
            soma = soma + sensor.avaliar(ultima);
        }

        double maximo = 3 * sensores.size();
        double indice = soma / maximo * 100 * getPesoGrupo();

        if (indice > 100) {
            indice = 100;
        }
        return indice;
    }

    /**
     * Mostra no console o resultado de cada sensor na última leitura.
     */
    public void imprimirRelatorio() {
        Leitura ultima = getUltimaLeitura();
        if (ultima == null) {
            System.out.println("  Escada " + getCodigo() + " ainda não tem leituras.");
            return;
        }
        System.out.println("  " + ultima);
        for (Sensor sensor : sensores) {
            System.out.println(sensor.gerarLaudo(ultima));
        }
    }

    /**
     * Retorna o tipo do equipamento.
     *
     * @return "Escada rolante"
     */
    @Override
    public String getTipo() {
        return "Escada rolante";
    }

    /**
     * Retorna o desnível.
     *
     * @return desnível em metros
     */
    public double getDesnivel() {
        return desnivel;
    }

    /**
     * Altera o desnível.
     *
     * @param desnivel novo desnível, em metros
     * @throws IllegalArgumentException se o desnível for zero ou negativo
     */
    public void setDesnivel(double desnivel) {
        if (desnivel <= 0) {
            throw new IllegalArgumentException("O desnível deve ser maior que zero.");
        }
        this.desnivel = desnivel;
    }

    /**
     * Retorna a velocidade nominal.
     *
     * @return velocidade em m/s
     */
    public double getVelocidadeNominal() {
        return velocidadeNominal;
    }

    /**
     * Altera a velocidade nominal.
     *
     * @param velocidadeNominal nova velocidade, em m/s
     * @throws IllegalArgumentException se a velocidade for zero ou negativa
     */
    public void setVelocidadeNominal(double velocidadeNominal) {
        if (velocidadeNominal <= 0) {
            throw new IllegalArgumentException("A velocidade nominal deve ser maior que zero.");
        }
        this.velocidadeNominal = velocidadeNominal;
    }

    /**
     * Retorna o sentido da escada.
     *
     * @return "Subida", "Descida" ou "Reversível"
     */
    public String getSentido() {
        return sentido;
    }

    /**
     * Altera o sentido da escada.
     *
     * @param sentido "Subida", "Descida" ou "Reversível"
     * @throws IllegalArgumentException se o sentido não for um dos três valores aceitos
     */
    public void setSentido(String sentido) {
        if (!sentido.equals("Subida") && !sentido.equals("Descida") && !sentido.equals("Reversível")) {
            throw new IllegalArgumentException("Sentido inválido: " + sentido);
        }
        this.sentido = sentido;
    }

    /**
     * Retorna o grupo de manutenção.
     *
     * @return grupo de manutenção
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * Altera o grupo de manutenção.
     *
     * @param grupo "Crítica A", "Crítica B", "Não crítica A" ou "Não crítica B"
     * @throws IllegalArgumentException se o grupo não for um dos quatro valores aceitos
     */
    public void setGrupo(String grupo) {
        if (!grupo.equals("Crítica A") && !grupo.equals("Crítica B")
                && !grupo.equals("Não crítica A") && !grupo.equals("Não crítica B")) {
            throw new IllegalArgumentException("Grupo inválido: " + grupo);
        }
        this.grupo = grupo;
    }

    /**
     * Retorna os sensores instalados.
     *
     * @return lista de sensores
     */
    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    /**
     * Retorna as leituras recebidas.
     *
     * @return lista de leituras
     */
    public ArrayList<Leitura> getLeituras() {
        return leituras;
    }

    /**
     * Retorna os dados da escada em texto.
     *
     * @return texto com os dados da escada
     */
    @Override
    public String toString() {
        return super.toString()
                + "\n    Desnível " + desnivel + " m | " + velocidadeNominal + " m/s | " + sentido
                + " | Grupo " + grupo + " | " + sensores.size() + " sensores | " + leituras.size() + " leituras"
                + " | condição " + Sensor.nomeDoNivel(avaliarCondicao())
                + " | criticidade " + String.format("%.1f", calcularCriticidade());
    }
}
