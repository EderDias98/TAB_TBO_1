

public class App {
    public static void main(String[] args) {

        Dados d = new Dados();
        Leitor.lerCandidatos(args[0], d);
        Leitor.lerSeccoes(args[1], d);
        // for(Candidato c: d.getCandidatos()){
        //     System.out.println(c.toString());
        // }

        // for(Seccao s: d.getSeccoes()){
        //     System.out.println(s.toString());
        // }
        int muni = Integer.parseInt(args[2]);
       
        Relatorio re = new Relatorio(d, muni);
       
        re.setEleitosPorMunicipio(d);
       
        re.gerarRelatorioEleitos(d);
       
        re.gerarRelatorioMaisVotados(d);
    }
}