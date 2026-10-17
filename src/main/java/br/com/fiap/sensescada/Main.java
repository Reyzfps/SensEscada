package br.com.fiap.sensescada;

import br.com.fiap.sensescada.modelo.Equipamento;
import br.com.fiap.sensescada.modelo.EscadaRolante;
import br.com.fiap.sensescada.modelo.Estacao;
import br.com.fiap.sensescada.modelo.Leitura;
import br.com.fiap.sensescada.modelo.Linha;
import br.com.fiap.sensescada.modelo.Localizacao;
import br.com.fiap.sensescada.modelo.RegistroManutencao;
import br.com.fiap.sensescada.modelo.Sensor;
import br.com.fiap.sensescada.modelo.SensorCorrenteTensao;
import br.com.fiap.sensescada.modelo.SensorNivelOleo;
import br.com.fiap.sensescada.modelo.SensorTemperatura;
import br.com.fiap.sensescada.modelo.SensorVelocidade;
import br.com.fiap.sensescada.modelo.SensorVibracao;

/**
 *
 * @author RM567780 Felipe Estevo Santos
 * @author RM567663 Igor Grave Teixeira
 * @author RM568540 Renan dos Reis Santos
 */
public class Main {

    /**
     * Construtor padrão.
     */
    public Main() {
    }

    /**
     * Método que inicia o programa.
     *
     */
    static void main() {

        titulo("1. LOCALIZAÇÃO");

        Linha linha4 = new Linha(4, "Amarela", "ViaQuatro (Motiva)");

        Estacao paulista = new Estacao("PTA", "Paulista", "Rua da Consolação", linha4);
        Estacao fariaLima = new Estacao("FLM", "Faria Lima", "Av. Brigadeiro Faria Lima", linha4);
        Estacao luz = new Estacao("LUZ", "Luz", "Praça da Luz", linha4);

        Localizacao local1 = new Localizacao(paulista, "Acesso Rua da Consolação", "Mezanino", "Saída Consolação");
        Localizacao local2 = new Localizacao(fariaLima, "Acesso principal", "Plataforma", "Mezanino");
        Localizacao local3 = new Localizacao(luz, "Conexão CPTM", "Plataforma", "Mezanino");
        Localizacao local4 = new Localizacao(paulista, "Acesso Av. Paulista", "Plataforma", "Mezanino");

        System.out.println(linha4);
        System.out.println(paulista);
        System.out.println(fariaLima);
        System.out.println(luz);
        System.out.println(local1);
        System.out.println(local2);
        System.out.println(local3);
        System.out.println(local4);

        titulo("2. ESCADAS ROLANTES");

        EscadaRolante escada1 = new EscadaRolante("ECR-PTA-01", "Fabricante A", "Modelo X",
                2010, local1, 9.6, "Subida", "Crítica A");

        EscadaRolante escada2 = new EscadaRolante("ECR-FLM-03", "Fabricante B", "Modelo Y",
                2011, local2, 5.2, 0.5, "Reversível", "Não crítica A");

        EscadaRolante escada3 = new EscadaRolante("ECR-LUZ-02", "Fabricante A", "Modelo X",
                2011, local3, 7.4, "Descida", "Crítica B");

        EscadaRolante escada4 = new EscadaRolante("ECR-PTA-04", "Fabricante B", "Modelo Y",
                2010, local4, 4.1, "Descida", "Não crítica B");

        System.out.println("4 escadas criadas: " + escada1.getCodigo() + ", " + escada2.getCodigo()
                + ", " + escada3.getCodigo() + ", " + escada4.getCodigo());

        titulo("3. SENSORES");

        instalarSensores(escada1, "PTA01", "10/02/2026");
        instalarSensores(escada2, "FLM03", "05/04/2026");
        instalarSensores(escada3, "LUZ02", "30/08/2025");
        instalarSensores(escada4, "PTA04", "12/01/2026");

        SensorVibracao vibracaoRedutor = new SensorVibracao("VIB-PTA01-RED", "Redutor", "10/02/2026",
                "Redutor", 1.4, 2.3, 3.5);
        escada1.adicionarSensor(vibracaoRedutor);

        System.out.println("Sensores da escada " + escada1.getCodigo() + ":");
        for (Sensor sensor : escada1.getSensores()) {
            System.out.println(sensor);
        }

        titulo("4. LEITURAS");

        escada1.registrarLeitura("14/10/2026 06:00", 1.10, 52.0, 18.5, 381, 82, 0.500);
        escada1.registrarLeitura("14/10/2026 18:00", 1.15, 53.5, 18.9, 379, 81, 0.499);
        escada1.registrarLeitura("15/10/2026 06:00", 1.20, 54.0, 19.2, 380, 81, 0.498);

        escada2.registrarLeitura("14/10/2026 06:00", 1.60, 61.0, 20.1, 378, 74, 0.494);
        escada2.registrarLeitura("14/10/2026 18:00", 2.30, 68.5, 21.8, 376, 72, 0.489);
        escada2.registrarLeitura("15/10/2026 06:00", 3.10, 76.0, 23.6, 372, 70, 0.482);

        escada3.registrarLeitura("14/10/2026 06:00", 2.00, 66.0, 22.0, 365, 41, 0.480);
        escada3.registrarLeitura("14/10/2026 18:00", 2.60, 74.0, 25.3, 352, 33, 0.468);
        escada3.registrarLeitura("15/10/2026 06:00", 3.90, 83.0, 28.4, 338, 18, 0.443);

        escada4.registrarLeitura("14/10/2026 06:00", 4.80, 91.0, 29.5, 341, 55, 0.430);

        Leitura leituraExtra = new Leitura("15/10/2026 18:00", 1.22, 54.2, 19.1, 380, 81, 0.498);
        escada1.registrarLeitura(leituraExtra);

        System.out.println("Leituras da escada " + escada3.getCodigo() + ":");
        for (Leitura leitura : escada3.getLeituras()) {
            System.out.println(leitura);
        }
        System.out.println("Potência do motor na última leitura: "
                + String.format("%.2f", escada3.getUltimaLeitura().calcularPotencia()) + " kVA");

        titulo("5. VALIDAÇÕES");

        try {
            new Leitura("15/10/2026 19:00", 1.0, 50.0, 18.0, 380, 120, 0.5);
        } catch (IllegalArgumentException erro) {
            System.out.println("Leitura recusada: " + erro.getMessage());
        }

        try {
            escada1.setGrupo("Grupo Z");
        } catch (IllegalArgumentException erro) {
            System.out.println("Grupo recusado: " + erro.getMessage());
        }

        try {
            new EscadaRolante("ECR-X", "Fabricante A", "Modelo X", 2020, local3, -3.0, "Subida", "Crítica A");
        } catch (IllegalArgumentException erro) {
            System.out.println("Escada recusada: " + erro.getMessage());
        }

        titulo("6. MANUTENÇÕES");

        escada1.adicionarManutencao(new RegistroManutencao("OS-2026-0912", "Preventiva", "P1", "12/09/2026",
                "Equipe Noturna", "Inspeção mensal de degraus e corrimão", 1850.00, 3));
        escada1.adicionarManutencao(new RegistroManutencao("OS-2026-0418", "Preventiva", "P6", "18/04/2026",
                "Equipe Noturna", "Troca de óleo do redutor e ajuste de freio", 6400.00, 6));
        escada2.adicionarManutencao(new RegistroManutencao("OS-2026-0921", "Preventiva", "P1", "21/09/2026",
                "Equipe Pinheiros", "Inspeção mensal", 1850.00, 3));
        escada3.adicionarManutencao(new RegistroManutencao("OS-2026-0805", "Corretiva", "", "05/08/2026",
                "Equipe Luz", "Reaperto de conexão no painel", 2900.00, 9.5));
        escada4.adicionarManutencao(new RegistroManutencao("OS-2026-1014", "Corretiva", "", "14/10/2026",
                "Equipe Noturna", "Rolamento do motor travado, aguardando peça", 12800.00, 26));

        escada4.setStatus("Parada por falha");

        System.out.println("Manutenções da escada " + escada1.getCodigo() + ":");
        for (RegistroManutencao manutencao : escada1.getManutencoes()) {
            System.out.println(manutencao);
        }
        System.out.println("Custo total da escada " + escada1.getCodigo() + ": R$ " + escada1.calcularCustoTotal());
        System.out.println("Idade da escada " + escada1.getCodigo() + ": " + escada1.calcularIdade(2026) + " anos");

        titulo("7. POLIMORFISMO");

        Leitura ultimaLeitura = escada3.getUltimaLeitura();
        System.out.println("Avaliação de cada sensor da escada " + escada3.getCodigo() + ":");
        for (Sensor sensor : escada3.getSensores()) {
            int nivel = sensor.avaliar(ultimaLeitura);
            System.out.println("  " + sensor.getTipo() + " -> " + Sensor.nomeDoNivel(nivel)
                    + " (" + sensor.diagnosticar(nivel) + ")");
        }

        System.out.println("Tipo do equipamento: " + escada3.getTipo());
        System.out.println("Criticidade calculada pela EscadaRolante: "
                + String.format("%.1f", escada3.calcularCriticidade()));

        titulo("8. RELATÓRIO DAS ESCADAS");

        EscadaRolante[] escadas = {escada1, escada2, escada3, escada4};

        for (EscadaRolante escada : escadas) {
            System.out.println(escada);
            escada.imprimirRelatorio();
            System.out.println();
        }

        titulo("9. RANKING DE CRITICIDADE");

        for (int i = 0; i < escadas.length - 1; i++) {
            for (int j = 0; j < escadas.length - 1 - i; j++) {
                if (escadas[j].calcularCriticidade() < escadas[j + 1].calcularCriticidade()) {
                    EscadaRolante troca = escadas[j];
                    escadas[j] = escadas[j + 1];
                    escadas[j + 1] = troca;
                }
            }
        }

        for (int i = 0; i < escadas.length; i++) {
            EscadaRolante escada = escadas[i];
            System.out.println((i + 1) + "º " + escada.getCodigo()
                    + " | " + String.format("%.1f", escada.calcularCriticidade()) + " pontos"
                    + " | " + escada.getGrupo()
                    + " | " + escada.getLocalizacao().getEstacao().getNome()
                    + " | " + escada.getStatus()
                    + " | " + Sensor.nomeDoNivel(escada.avaliarCondicao()));
        }
    }

    /**
     * Instala os 5 sensores em uma escada (vibração, temperatura, corrente/tensão, óleo e velocidade).
     *
     * @param escada         escada que vai receber os sensores
     * @param sufixo         texto usado no código dos sensores, por exemplo "PTA01"
     * @param dataCalibracao data de calibração dos sensores
     */
    public static void instalarSensores(EscadaRolante escada, String sufixo, String dataCalibracao) {
        escada.adicionarSensor(new SensorVibracao("VIB-" + sufixo, "Motor", dataCalibracao, "Motor"));
        escada.adicionarSensor(new SensorTemperatura("TMP-" + sufixo, "Motor", dataCalibracao));
        escada.adicionarSensor(new SensorCorrenteTensao("ELE-" + sufixo, "Painel elétrico", dataCalibracao, 22, 380));
        escada.adicionarSensor(new SensorNivelOleo("OLE-" + sufixo, "Reservatório do redutor", dataCalibracao, 8));
        escada.adicionarSensor(new SensorVelocidade("VEL-" + sufixo, "Trecho de retorno", dataCalibracao,
                escada.getVelocidadeNominal()));
    }

    /**
     * Mostra um título de seção no console.
     *
     * @param texto título da seção
     */
    public static void titulo(String texto) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(texto);
        System.out.println("==================================================");
    }
}
