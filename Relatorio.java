import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Relatorio {
    private int municipio;
    List<Candidato> candidatosDoMunicipio;
    List<Seccao> seccoesDoMunicipio;

    int qtdEleitos;

    public Relatorio(Dados d, int municipio) {
        this.municipio = municipio;
        this.candidatosDoMunicipio = d.getCandidatos().stream()
                .filter(c -> c.getSgUE() == municipio)
                .toList();
        this.qtdEleitos = 0;

        this.seccoesDoMunicipio = d.getSeccoes().stream()
                .filter(s -> s.getCdMunicipio() == municipio)
                .toList();
    }

    public void setEleitosPorMunicipio(Dados d) {
        
        
        this.qtdEleitos = (int) d.getCandidatos().stream()
                .filter(c -> c.getSgUE() == municipio) // filtra o município
                .filter(c -> c.getCdSitTotTurno() == 2 || c.getCdSitTotTurno() == 3) // filtra eleitos
                .count(); // conta quantos são
    }

    public  void gerarRelatorioEleitos(Dados d) {
        // Filtrar os candidatos eleitos no município
        List<Candidato> eleitos = this.candidatosDoMunicipio.stream()
                .filter(c -> c.getCdSitTotTurno() == 2 || c.getCdSitTotTurno() == 3)
                .toList();

        Set<Integer> numerosEleitos = eleitos.stream()
                .map(Candidato::getNrCandidato) // pega o número do candidato
                .collect(Collectors.toSet());
        // Para cada eleito, buscar seus votos e partidos

        Map<Integer, Integer> votosPorCandidato = this.seccoesDoMunicipio.stream()
                .filter(s -> numerosEleitos.contains(s.getNrVotavel()))
                .collect(Collectors.groupingBy(
                        Seccao::getNrVotavel,
                        Collectors.summingInt(s -> s.getQtVotos())));

        // Ordenar os eleitos por quantidade de votos (decrescente)
        List<Candidato> eleitosOrdenados = eleitos.stream()
                .sorted(Comparator.comparingInt(
                        c -> -votosPorCandidato.getOrDefault(c.getNrCandidato(), 0)))
                .toList();

        // Imprimir no formato solicitado
        System.out.println("Vereadores eleitos:");
        for (int i = 0; i < eleitosOrdenados.size(); i++) {
            Candidato c = eleitosOrdenados.get(i);
            int votos = votosPorCandidato.getOrDefault(c.getNrCandidato(), 0);
            System.out.printf("%d - %s (%s, %d votos)%n",
                    i + 1,
                    c.getNmUrnaCandidato(),
                    c.getSgPartido(),
                    votos);
        }
    }

    public  void gerarRelatorioMaisVotados(Dados d) {


        // Obter todos os números dos candidatos do município
        Set<Integer> numerosCandidatos = this.candidatosDoMunicipio.stream()
                .map(Candidato::getNrCandidato)
                .collect(Collectors.toSet());

        // Contabilizar os votos para cada candidato no município
        Map<Integer, Integer> votosPorCandidato = this.seccoesDoMunicipio.stream()
                .filter(s -> numerosCandidatos.contains(s.getNrVotavel()))
                .collect(Collectors.groupingBy(
                        Seccao::getNrVotavel,
                        Collectors.summingInt(Seccao::getQtVotos)));

        // Ordenar os candidatos por quantidade de votos (decrescente)
        List<Candidato> maisVotados = this.candidatosDoMunicipio.stream()
                .sorted(Comparator.comparingInt(
                        c -> -votosPorCandidato.getOrDefault(c.getNrCandidato(), 0)))
                .limit(qtdEleitos) // Limitar aos N mais votados
                .toList();

        // Imprimir o relatório
        System.out.println("Candidatos mais votados:");
        for (int i = 0; i < maisVotados.size(); i++) {
            Candidato c = maisVotados.get(i);
            int votos = votosPorCandidato.getOrDefault(c.getNrCandidato(), 0);
            System.out.printf("%d - %s (%s, %d votos)%n",
                    i + 1,
                    c.getNmUrnaCandidato(),
                    c.getSgPartido(),
                    votos);
        }
    }


}
