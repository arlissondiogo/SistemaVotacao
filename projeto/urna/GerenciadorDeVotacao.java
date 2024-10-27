package urna;

public class GerenciadorDeVotacao {
    private static GerenciadorDeVotacao instancia;
    private Votacao votacao;

    private GerenciadorDeVotacao() {
        votacao = new Votacao();
    }

    public static GerenciadorDeVotacao getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorDeVotacao();
        }
        return instancia;
    }

    public void iniciarVotacao() {
        votacao.setEstado(EstadoDaVotacao.ABERTA);
        System.out.println("Votação iniciada.");
    }

    public void encerrarVotacao() {
        votacao.setEstado(EstadoDaVotacao.FECHADA);
        System.out.println("Votação encerrada.");
    }

    public Votacao getVotacao() {
        return votacao;
    }
}
