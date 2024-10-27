package urna;

public class ServicoDeVotacao {
    public void votar(String nomeVotante, OpcaoDeVoto opcao) {
        GerenciadorDeVotacao gerenciador = GerenciadorDeVotacao.getInstancia();
        Votacao votacao = gerenciador.getVotacao();

        // Verifica se o votante já votou
        for (Votante votante : votacao.getVotantes()) {
            if (votante.getNome().equalsIgnoreCase(nomeVotante)) {
                System.out.println("Votante " + votante.getNome() + " já votou.");
                return;
            }
        }

        // Apenas registra o voto se a votação estiver aberta
        if (votacao.getEstado() == EstadoDaVotacao.ABERTA) {
            Votante novoVotante = new Votante(nomeVotante);
            votacao.adicionarVotante(novoVotante);
            novoVotante.setJaVotou(true);
            System.out.println("Votante " + novoVotante.getNome() + " votou em " + opcao.getNome());
        } else {
            System.out.println("Não é possível votar, a votação está fechada.");
        }
    }
}
