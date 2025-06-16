import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Leitor {

    public static void lerCandidatos(String caminhoArquivo, Dados d) {
        

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(caminhoArquivo), "ISO-8859-1"))) {

            String linha = br.readLine(); // Lê o cabeçalho
            if (linha == null) {
                throw new RuntimeException("Arquivo vazio");
            }

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");

                // Remove aspas de todos os campos
                for (int i = 0; i < campos.length; i++) {
                    campos[i] = campos[i].replaceAll("^\"|\"$", "").trim();
                }

                try {
                    String sgUE = campos[11]; // SG_UE
                    String cdCargo = campos[13]; // CD_CARGO

                    if (!cdCargo.equals("13"))
                        continue; // Filtra apenas vereadores

                    String nrCandidato = campos[16]; // NR_CANDIDATO
                    String nmUrnaCandidato = campos[18]; // NM_URNA_CANDIDATO
                    String nrPartido = campos[25]; // NR_PARTIDO
                    String sgPartido = campos[26]; // SG_PARTIDO
                    String nrFederacao = campos[28]; // NR_FEDERACAO
                    String dtNascimento = campos[36]; // DT_NASCIMENTO
                    String cdSitTotTurno = campos[48]; // CD_SIT_TOT_TURNO

                    if (cdSitTotTurno.equals("-1"))
                        continue; // Candidatura inválida

                    String cdGenero = campos[38];
                    Candidato candidato = new Candidato(
                            sgUE, nrCandidato, nmUrnaCandidato,
                            nrPartido, sgPartido, nrFederacao, dtNascimento,
                            cdSitTotTurno, cdGenero);

                    d.addCandidato(candidato);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Linha com erro: " + linha);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }


    //

    public static void lerSeccoes(String caminhoArquivo, Dados d) {
   

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(caminhoArquivo), "ISO-8859-1"))) {

            String linha = br.readLine(); // Lê o cabeçalho
            if (linha == null) {
                throw new RuntimeException("Arquivo vazio");
            }

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");

                // Remove aspas de todos os campos
                for (int i = 0; i < campos.length; i++) {
                    campos[i] = campos[i].replaceAll("^\"|\"$", "").trim();
                }

                try {
                    // Dentro do seu loop de leitura de linhas:
                    String cdCargo = campos[17]; // CD_CARGO
                    if (!cdCargo.equals("13"))
                        continue; // só vereador

                    String cdMunicipio = campos[13]; // CD_MUNICIPIO
                    String nrVotavel = campos[19]; // NR_VOTAVEL

                    // Ignora votos em branco/nulos/anulados
                    if (nrVotavel.equals("95") || nrVotavel.equals("96") ||
                            nrVotavel.equals("97") || nrVotavel.equals("98")) {
                        continue;
                    }

                    String qtVotos = campos[21]; // QT_VOTOS

                    // Cria o objeto Seccao/Cessao já recebendo Strings
                    Seccao seccao = new Seccao(
                            cdMunicipio,
                            nrVotavel,
                            qtVotos);

                    d.addSeccao(seccao);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Linha com erro: " + linha);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
