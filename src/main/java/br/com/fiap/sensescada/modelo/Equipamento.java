package br.com.fiap.sensescada.modelo;

import java.util.ArrayList;

/**
 * Classe abstrata com os dados comuns a todos os equipamentos de uma estação.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public abstract class Equipamento {
    private String codigo;
    private String fabricante;
    private String modelo;
    private int anoInstalacao;
    private String status;
    private Localizacao localizacao;
    private ArrayList<RegistroManutencao> manutencoes;

    /**
     * Construtor usado pelas subclasses. Todo equipamento começa "Em operação".
     *
     * @param codigo        código do equipamento
     * @param fabricante    fabricante
     * @param modelo        modelo
     * @param anoInstalacao ano de instalação
     * @param localizacao   onde o equipamento está
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Equipamento(String codigo, String fabricante, String modelo, int anoInstalacao, Localizacao localizacao) {
        setCodigo(codigo);
        setFabricante(fabricante);
        setModelo(modelo);
        setAnoInstalacao(anoInstalacao);
        setLocalizacao(localizacao);
        this.status = "Em operação";
        this.manutencoes = new ArrayList<>();
    }

    /**
     * Retorna o tipo do equipamento, por exemplo "Escada rolante".
     *
     * @return tipo do equipamento
     */
    public abstract String getTipo();

    /**
     * Calcula o quanto o equipamento precisa de atenção, de 0 a 100.
     *
     * @return índice de criticidade de 0 a 100
     */
    public abstract double calcularCriticidade();

    /**
     * Adiciona uma manutenção ao histórico do equipamento.
     *
     * @param manutencao manutenção feita
     * @throws IllegalArgumentException se a manutenção for nula
     */
    public void adicionarManutencao(RegistroManutencao manutencao) {
        if (manutencao == null) {
            throw new IllegalArgumentException("A manutenção não pode ser nula.");
        }
        manutencoes.add(manutencao);
    }

    /**
     * Soma o custo de todas as manutenções do equipamento.
     *
     * @return custo total em reais
     */
    public double calcularCustoTotal() {
        double total = 0;
        for (RegistroManutencao manutencao : manutencoes) {
            total = total + manutencao.getCusto();
        }
        return total;
    }

    /**
     * Soma as horas que o equipamento ficou parado em manutenções.
     *
     * @return total de horas parado
     */
    public double calcularHorasParado() {
        double total = 0;
        for (RegistroManutencao manutencao : manutencoes) {
            total = total + manutencao.getHorasParada();
        }
        return total;
    }

    /**
     * Calcula quantos anos o equipamento tem.
     *
     * @param anoAtual ano atual
     * @return idade em anos
     */
    public int calcularIdade(int anoAtual) {
        return anoAtual - anoInstalacao;
    }

    /**
     * Retorna o código do equipamento.
     *
     * @return código
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Altera o código do equipamento.
     *
     * @param codigo novo código
     * @throws IllegalArgumentException se o código estiver vazio
     */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("O código do equipamento é obrigatório.");
        }
        this.codigo = codigo;
    }

    /**
     * Retorna o fabricante.
     *
     * @return fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Altera o fabricante.
     *
     * @param fabricante novo fabricante
     * @throws IllegalArgumentException se o fabricante estiver vazio
     */
    public void setFabricante(String fabricante) {
        if (fabricante == null || fabricante.isEmpty()) {
            throw new IllegalArgumentException("O fabricante é obrigatório.");
        }
        this.fabricante = fabricante;
    }

    /**
     * Retorna o modelo.
     *
     * @return modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Altera o modelo.
     *
     * @param modelo novo modelo
     * @throws IllegalArgumentException se o modelo estiver vazio
     */
    public void setModelo(String modelo) {
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("O modelo é obrigatório.");
        }
        this.modelo = modelo;
    }

    /**
     * Retorna o ano de instalação.
     *
     * @return ano de instalação
     */
    public int getAnoInstalacao() {
        return anoInstalacao;
    }

    /**
     * Altera o ano de instalação.
     *
     * @param anoInstalacao novo ano (a partir de 1900)
     * @throws IllegalArgumentException se o ano for menor que 1900
     */
    public void setAnoInstalacao(int anoInstalacao) {
        if (anoInstalacao < 1900) {
            throw new IllegalArgumentException("Ano de instalação inválido: " + anoInstalacao);
        }
        this.anoInstalacao = anoInstalacao;
    }

    /**
     * Retorna o status do equipamento.
     *
     * @return status atual
     */
    public String getStatus() {
        return status;
    }

    /**
     * Altera o status do equipamento.
     *
     * @param status "Em operação", "Parada para manutenção" ou "Parada por falha"
     * @throws IllegalArgumentException se o status não for um dos três valores aceitos
     */
    public void setStatus(String status) {
        if (!status.equals("Em operação") && !status.equals("Parada para manutenção")
                && !status.equals("Parada por falha")) {
            throw new IllegalArgumentException("Status inválido: " + status);
        }
        this.status = status;
    }

    /**
     * Retorna a localização.
     *
     * @return onde o equipamento está
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Altera a localização.
     *
     * @param localizacao nova localização
     * @throws IllegalArgumentException se a localização for nula
     */
    public void setLocalizacao(Localizacao localizacao) {
        if (localizacao == null) {
            throw new IllegalArgumentException("O equipamento precisa ter uma localização.");
        }
        this.localizacao = localizacao;
    }

    /**
     * Retorna a lista de manutenções.
     *
     * @return lista de manutenções
     */
    public ArrayList<RegistroManutencao> getManutencoes() {
        return manutencoes;
    }

    /**
     * Retorna os dados do equipamento em texto.
     *
     * @return texto com os dados do equipamento
     */
    @Override
    public String toString() {
        return getTipo() + " " + codigo + " | " + fabricante + " " + modelo
                + " | instalada em " + anoInstalacao + " | " + status
                + "\n    Local: " + localizacao
                + "\n    Manutenções: " + manutencoes.size() + " | custo total R$ " + calcularCustoTotal()
                + " | " + calcularHorasParado() + " h parada";
    }
}
