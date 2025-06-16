import java.util.ArrayList;
import java.util.List;

public class Dados {
    // Lista de candidatos
    private List<Candidato> candidatos;

    // Lista de seções
    private List<Seccao> seccoes ;

    // Construtor padrão
    public Dados() {
        // as listas já foram inicializadas acima,
        // mas você pode recriá‑las aqui se preferir
        this.candidatos = new ArrayList<>();
        this.seccoes = new ArrayList<>();
    }

    // Getters
    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public List<Seccao> getSeccoes() {
        return seccoes;
    }

    // Métodos para adicionar itens
    public void addCandidato(Candidato c) {
        candidatos.add(c);
    }

    public void addSeccao(Seccao s) {
        seccoes.add(s);
    }

}
