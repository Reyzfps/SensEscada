package br.com.fiap.sensescada.modelo;

/**
 * Representa o local exato onde a escada rolante fica dentro da estação.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class Localizacao {
    private Estacao estacao;
    private String acesso;
    private String nivelInferior;
    private String nivelSuperior;

    /**
     * Cria uma localização.
     *
     * @param estacao       estação onde a escada está
     * @param acesso        acesso da estação
     * @param nivelInferior nível de baixo
     * @param nivelSuperior nível de cima
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public Localizacao(Estacao estacao, String acesso, String nivelInferior, String nivelSuperior) {
        setEstacao(estacao);
        setAcesso(acesso);
        setNivelInferior(nivelInferior);
        setNivelSuperior(nivelSuperior);
    }

    /**
     * Retorna a estação.
     *
     * @return estação onde a escada está
     */
    public Estacao getEstacao() {
        return estacao;
    }

    /**
     * Altera a estação.
     *
     * @param estacao nova estação
     * @throws IllegalArgumentException se a estação for nula
     */
    public void setEstacao(Estacao estacao) {
        if (estacao == null) {
            throw new IllegalArgumentException("A localização precisa ter uma estação.");
        }
        this.estacao = estacao;
    }

    /**
     * Retorna o acesso.
     *
     * @return acesso da estação
     */
    public String getAcesso() {
        return acesso;
    }

    /**
     * Altera o acesso.
     *
     * @param acesso novo acesso
     * @throws IllegalArgumentException se o acesso estiver vazio
     */
    public void setAcesso(String acesso) {
        if (acesso == null || acesso.isEmpty()) {
            throw new IllegalArgumentException("O acesso é obrigatório.");
        }
        this.acesso = acesso;
    }

    /**
     * Retorna o nível de baixo.
     *
     * @return nível inferior
     */
    public String getNivelInferior() {
        return nivelInferior;
    }

    /**
     * Altera o nível de baixo.
     *
     * @param nivelInferior novo nível inferior
     * @throws IllegalArgumentException se o nível estiver vazio
     */
    public void setNivelInferior(String nivelInferior) {
        if (nivelInferior == null || nivelInferior.isEmpty()) {
            throw new IllegalArgumentException("O nível inferior é obrigatório.");
        }
        this.nivelInferior = nivelInferior;
    }

    /**
     * Retorna o nível de cima.
     *
     * @return nível superior
     */
    public String getNivelSuperior() {
        return nivelSuperior;
    }

    /**
     * Altera o nível de cima.
     *
     * @param nivelSuperior novo nível superior
     * @throws IllegalArgumentException se o nível estiver vazio
     */
    public void setNivelSuperior(String nivelSuperior) {
        if (nivelSuperior == null || nivelSuperior.isEmpty()) {
            throw new IllegalArgumentException("O nível superior é obrigatório.");
        }
        this.nivelSuperior = nivelSuperior;
    }

    /**
     * Retorna os dados da localização em texto.
     *
     * @return texto com a localização completa
     */
    @Override
    public String toString() {
        return estacao.getLinha().getNomeCompleto() + " > " + estacao.getNome() + " > " + acesso
                + " > " + nivelInferior + " / " + nivelSuperior;
    }
}
