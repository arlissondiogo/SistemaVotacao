package projeto;

public class ServicoDeVotacao {
    public String votar(String nomeVotante, String cpf, String titulo, OpcaoDeVoto opcao) {
        GerenciadorDeVotacao gerenciador = GerenciadorDeVotacao.getInstancia();
        Votacao votacao = gerenciador.getVotacao();

        if (votacao.getEstado() != EstadoDaVotacao.ABERTA) {
            return "Não é possível votar, a votação está fechada.";
        }

        for (Votante votante : votacao.getVotantes()) {
            if (votante.getCpf().equals(cpf) || votante.getTitulo().equals(titulo)) {
                return "Votante " + nomeVotante + " já votou.";
            }
        }

        Votante novoVotante = new Votante(nomeVotante, cpf, titulo);
        votacao.adicionarVotante(novoVotante);
        novoVotante.setJaVotou(true);

        for (OpcaoDeVoto opcaoDeVoto : votacao.getOpcoes()) {
            if (opcaoDeVoto.getNome().equalsIgnoreCase(opcao.getNome())) {
                opcaoDeVoto.adicionarVoto();
                gerenciador.notificarVotoRegistrado(opcaoDeVoto);
                break;
            }
        }

        return "Votante " + novoVotante.getNome() + " votou em " + opcao.getNome();
    }
}
