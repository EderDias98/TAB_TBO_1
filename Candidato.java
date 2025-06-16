import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Candidato {

    public enum Genero {
        MASCULINO,
        FEMININO,
        OUTRO,
        NAO_INFORMADO;
    }

    // Código do município onde o candidato concorre (SG_UE no CSV)
    private int sgUE;

    // Código do cargo (CD_CARGO no CSV)
    // No nosso caso, sempre deve ser 13, que representa vereador.
    private String cargo;

    // Número do candidato (NR_CANDIDATO no CSV)
    // É o número que aparece na urna para o eleitor.
    private int nrCandidato;

    // Nome do candidato que aparece na urna (NM_URNA_CANDIDATO no CSV)
    private String nmUrnaCandidato;

    // Número do partido político (NR_PARTIDO no CSV)
    // Ex.: 13 para PT, 45 para PSDB, 22 para PL, etc.
    private int nrPartido;

    // Sigla do partido político (SG_PARTIDO no CSV)
    // Ex.: PT, PSDB, PL, etc.
    private String sgPartido;

    // Número da federação partidária (NR_FEDERACAO no CSV)
    // Se for -1, significa que o partido não faz parte de nenhuma federação.
    private int nrFederacao;

    // Data de nascimento do candidato (DT_NASCIMENTO no CSV)
    // Representada como um objeto LocalDate para facilitar cálculos e manipulação
    // de datas.
    private LocalDate dtNascimento;

    // Código da situação total no turno (CD_SIT_TOT_TURNO no CSV)
    // Define se o candidato foi eleito (2 ou 3) ou não. Se for -1, é candidatura
    // inválida e deve ser ignorada.
    private int cdSitTotTurno;

    // Gênero do candidato (CD_GENERO no CSV)
    // Usamos uma enum (Genero) para representar os valores:
    // 2 → MASCULINO, 4 → FEMININO. Outros códigos podem ser tratados como
    // NAO_INFORMADO.
    private Genero genero;

    public Candidato(String sgUE, String nrCandidato, String nmUrnaCandidato,
            String nrPartido, String sgPartido, String nrFederacao, String dtNascimento,
            String cdSitTotTurno, String cdGenero) {

        this.sgUE = Integer.parseInt(sgUE);

        this.cargo = "VEREADOR";

        this.nrCandidato = Integer.parseInt(nrCandidato);
        this.nmUrnaCandidato = nmUrnaCandidato;
        this.nrPartido = Integer.parseInt(nrPartido);
        this.sgPartido = sgPartido;
        this.nrFederacao = Integer.parseInt(nrFederacao);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.dtNascimento = LocalDate.parse(dtNascimento, formatter);

        this.cdSitTotTurno = Integer.parseInt(cdSitTotTurno);

        switch (Integer.parseInt(cdGenero)) {
            case 2:
                this.genero = Genero.MASCULINO;
                break;
            case 4:
                this.genero = Genero.FEMININO;

            default:
                this.genero = Genero.OUTRO;
                break;
        }

    }

    @Override
    public String toString() {
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = this.dtNascimento.format(formatoBr);
        return String.format("Candidato: %s (%d) - Partido %s (%d) - Nascimento: %s - Gênero: %s - Situação: %d",
                nmUrnaCandidato, nrCandidato, sgPartido, nrPartido, dataFormatada,
                (this.genero == Genero.MASCULINO ? "Masculino" : "Feminino"), cdSitTotTurno);
    }

    // Getters e setters podem ser adicionados se necessário
    public String getCargo() {
        return cargo;
    }

    public int getCdSitTotTurno() {
        return cdSitTotTurno;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNmUrnaCandidato() {
        return nmUrnaCandidato;
    }

    public int getNrCandidato() {
        return nrCandidato;
    }

    public int getNrFederacao() {
        return nrFederacao;
    }

    public int getNrPartido() {
        return nrPartido;
    }

    public String getSgPartido() {
        return sgPartido;
    }

    public int getSgUE() {
        return sgUE;
    }
}
