package br.com.fiap.sensescada.modelo;

/**
 * Representa uma manutenção feita em um equipamento.
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 * @version 1.0
 * @since 1.0
 */
public class RegistroManutencao {
    private String numeroOrdem;
    private String tipo;
    private String rotina;
    private String data;
    private String responsavel;
    private String descricao;
    private double custo;
    private double horasParada;

    /**
     * Cria um registro de manutenção.
     *
     * @param numeroOrdem número da ordem de serviço
     * @param tipo        "Preventiva", "Corretiva" ou "Preditiva"
     * @param rotina      rotina da preventiva (P1, P6...). Use "" se não for preventiva
     * @param data        data da manutenção
     * @param responsavel equipe ou técnico responsável
     * @param descricao   o que foi feito
     * @param custo       custo em reais
     * @param horasParada horas de equipamento parado
     * @throws IllegalArgumentException se algum dado for inválido
     */
    public RegistroManutencao(String numeroOrdem, String tipo, String rotina, String data,
                              String responsavel, String descricao, double custo, double horasParada) {
        if (numeroOrdem == null || numeroOrdem.isEmpty()) {
            throw new IllegalArgumentException("O número da ordem de serviço é obrigatório.");
        }
        if (!tipo.equals("Preventiva") && !tipo.equals("Corretiva") && !tipo.equals("Preditiva")) {
            throw new IllegalArgumentException("Tipo inválido: " + tipo + ". Use Preventiva, Corretiva ou Preditiva.");
        }
        if (tipo.equals("Preventiva") && rotina.isEmpty()) {
            throw new IllegalArgumentException("Manutenção preventiva precisa informar a rotina (P1, P6...).");
        }
        if (custo < 0 || horasParada < 0) {
            throw new IllegalArgumentException("Custo e horas de parada não podem ser negativos.");
        }
        this.numeroOrdem = numeroOrdem;
        this.tipo = tipo;
        this.rotina = rotina;
        this.data = data;
        this.responsavel = responsavel;
        this.descricao = descricao;
        this.custo = custo;
        this.horasParada = horasParada;
    }

    /**
     * Retorna o número da ordem de serviço.
     *
     * @return número da ordem
     */
    public String getNumeroOrdem() {
        return numeroOrdem;
    }

    /**
     * Retorna o tipo da manutenção.
     *
     * @return "Preventiva", "Corretiva" ou "Preditiva"
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna a rotina da preventiva.
     *
     * @return rotina (P1, P6...) ou texto vazio
     */
    public String getRotina() {
        return rotina;
    }

    /**
     * Retorna a data da manutenção.
     *
     * @return data da manutenção
     */
    public String getData() {
        return data;
    }

    /**
     * Retorna o responsável.
     *
     * @return equipe ou técnico responsável
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * Retorna a descrição do serviço.
     *
     * @return o que foi feito
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o custo.
     *
     * @return custo em reais
     */
    public double getCusto() {
        return custo;
    }

    /**
     * Retorna as horas de parada.
     *
     * @return horas que o equipamento ficou parado
     */
    public double getHorasParada() {
        return horasParada;
    }

    /**
     * Retorna os dados da manutenção em texto.
     *
     * @return texto com os dados da manutenção
     */
    @Override
    public String toString() {
        return "OS " + numeroOrdem + " | " + data + " | " + tipo + " " + rotina
                + " | R$ " + custo + " | " + horasParada + " h parada | " + responsavel + " - " + descricao;
    }
}
