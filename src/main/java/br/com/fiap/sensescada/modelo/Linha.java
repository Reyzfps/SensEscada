package br.com.fiap.sensescada.modelo;

/**
 * Representa uma linha do metrô, por exemplo a Linha 4-Amarela.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class Linha {
    private int numero;
    private String nome;
    private String concessionaria;

    /**
     * Cria uma linha.
     *
     * @param numero         número da linha
     * @param nome           nome da linha, por exemplo "Amarela"
     * @param concessionaria empresa que opera a linha
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Linha(int numero, String nome, String concessionaria) {
        setNumero(numero);
        setNome(nome);
        setConcessionaria(concessionaria);
    }

    /**
     * Retorna o número da linha.
     *
     * @return número da linha
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Altera o número da linha.
     *
     * @param numero novo número (maior que zero)
     * @throws IllegalArgumentException se o número for zero ou negativo
     */
    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número da linha deve ser maior que zero.");
        }
        this.numero = numero;
    }

    /**
     * Retorna o nome da linha.
     *
     * @return nome da linha
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome da linha.
     *
     * @param nome novo nome
     * @throws IllegalArgumentException se o nome estiver vazio
     */
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome da linha é obrigatório.");
        }
        this.nome = nome;
    }

    /**
     * Retorna a concessionária.
     *
     * @return empresa que opera a linha
     */
    public String getConcessionaria() {
        return concessionaria;
    }

    /**
     * Altera a concessionária.
     *
     * @param concessionaria nova concessionária
     * @throws IllegalArgumentException se a concessionária estiver vazia
     */
    public void setConcessionaria(String concessionaria) {
        if (concessionaria == null || concessionaria.isEmpty()) {
            throw new IllegalArgumentException("A concessionária é obrigatória.");
        }
        this.concessionaria = concessionaria;
    }

    /**
     * Retorna o nome completo da linha, por exemplo "Linha 4-Amarela".
     *
     * @return nome completo
     */
    public String getNomeCompleto() {
        return "Linha " + numero + "-" + nome;
    }

    /**
     * Retorna os dados da linha em texto.
     *
     * @return texto com os dados da linha
     */
    @Override
    public String toString() {
        return getNomeCompleto() + " (concessionária: " + concessionaria + ")";
    }
}
