package projeto;

public class ServicoDeVotacao {
    public String votar(String nomeVotante, String cpf, String titulo, OpcaoDeVoto opcao) {
        GerenciadorDeVotacao gerenciador = GerenciadorDeVotacao.getInstancia();
        Votacao votacao = gerenciador.getVotacao();

        // Verifica se o votante já votou pelo CPF ou título
        for (Votante votante : votacao.getVotantes()) {
            if (votante.getCpf().equals(cpf) || votante.getTitulo().equals(titulo)) {
                return "Votante " + nomeVotante + " já votou.";
            }
        }

        // Apenas registra o voto se a votação estiver aberta
        if (votacao.getEstado() == EstadoDaVotacao.ABERTA) {
            Votante novoVotante = new Votante(nomeVotante, cpf, titulo);
            votacao.adicionarVotante(novoVotante);
            novoVotante.setJaVotou(true);
            return "Votante " + novoVotante.getNome() + " votou em " + opcao.getNome();
        } else {
            return "Não é possível votar, a votação está fechada.";
        }
    }
}
