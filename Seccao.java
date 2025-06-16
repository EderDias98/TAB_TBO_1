public class Seccao {

    private int cdMunicipio;
    private int nrVotavel;
    private int qtVotos;

    // Construtor recebendo strings e convertendo para int
    public Seccao(String cdMunicipio, String nrVotavel, String qtVotos) {

        this.cdMunicipio = Integer.parseInt(cdMunicipio);
        this.nrVotavel = Integer.parseInt(nrVotavel);
        this.qtVotos = Integer.parseInt(qtVotos);
    }

    // Getters

    public int getCdMunicipio() {
        return cdMunicipio;
    }

    public int getNrVotavel() {
        return nrVotavel;
    }

    public int getQtVotos() {
        return qtVotos;
    }

    // toString formatado
    @Override
    public String toString() {
        return String.format("Município: %d - Número Votável: %d - Quantidade de Votos: %d",
                cdMunicipio, nrVotavel, qtVotos);
    }
}