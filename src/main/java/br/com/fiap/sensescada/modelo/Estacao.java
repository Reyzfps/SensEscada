package br.com.fiap.sensescada.modelo;

/**
 * Representa uma estação do metrô.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class Estacao {
    private String codigo;
    private String nome;
    private String endereco;
    private Linha linha;

    /**
     * Cria uma estação.
     *
     * @param codigo   sigla da estação
     * @param nome     nome da estação
     * @param endereco endereço da estação
     * @param linha    linha a que a estação pertence
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Estacao(String codigo, String nome, String endereco, Linha linha) {
        setCodigo(codigo);
        setNome(nome);
        setEndereco(endereco);
        setLinha(linha);
    }

    /**
     * Retorna a sigla da estação.
     *
     * @return sigla da estação
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Altera a sigla da estação.
     *
     * @param codigo nova sigla
     * @throws IllegalArgumentException se a sigla estiver vazia
     */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("O código da estação é obrigatório.");
        }
        this.codigo = codigo;
    }

    /**
     * Retorna o nome da estação.
     *
     * @return nome da estação
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome da estação.
     *
     * @param nome novo nome
     * @throws IllegalArgumentException se o nome estiver vazio
     */
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome da estação é obrigatório.");
        }
        this.nome = nome;
    }

    /**
     * Retorna o endereço da estação.
     *
     * @return endereço
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Altera o endereço da estação.
     *
     * @param endereco novo endereço
     * @throws IllegalArgumentException se o endereço estiver vazio
     */
    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isEmpty()) {
            throw new IllegalArgumentException("O endereço da estação é obrigatório.");
        }
        this.endereco = endereco;
    }

    /**
     * Retorna a linha da estação.
     *
     * @return linha da estação
     */
    public Linha getLinha() {
        return linha;
    }

    /**
     * Altera a linha da estação.
     *
     * @param linha nova linha
     * @throws IllegalArgumentException se a linha for nula
     */
    public void setLinha(Linha linha) {
        if (linha == null) {
            throw new IllegalArgumentException("A estação precisa ter uma linha.");
        }
        this.linha = linha;
    }

    /**
     * Retorna os dados da estação em texto.
     *
     * @return texto com os dados da estação
     */
    @Override
    public String toString() {
        return "Estação " + nome + " (" + codigo + ") - " + linha.getNomeCompleto() + " - " + endereco;
    }
}
