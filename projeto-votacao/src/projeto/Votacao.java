package projeto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Votacao {
    private EstadoDaVotacao estado;
    private List<OpcaoDeVoto> opcoes;
    private List<Votante> votantes;

    public Votacao() {
        this.estado = EstadoDaVotacao.ABERTA;
        this.opcoes = new ArrayList<>();
        this.votantes = new ArrayList<>();

        // Adicionando opções de voto
        opcoes.add(new OpcaoDeVoto("Taylor Swift"));
        opcoes.add(new OpcaoDeVoto("Kanye West"));
    }

    public void setEstado(EstadoDaVotacao novoEstado) {
        this.estado = novoEstado;
        estado.manipularRequisicao(this);

        // Conta os votos e exibe os resultados ao encerrar a votação
        if (novoEstado == EstadoDaVotacao.FECHADA) {
            contarVotos();
        }
    }

    public EstadoDaVotacao getEstado() {
        return estado;
    }

    public void adicionarVotante(Votante votante) {
        votantes.add(votante);
    }

    public void registrarVoto(Votante votante, String opcaoEscolhida) {
        if (estado != EstadoDaVotacao.ABERTA) {
            System.out.println("A votação está fechada. Não é possível registrar votos.");
            return;
        }

        if (votante.isJaVotou()) {
            System.out.println("Votante já votou.");
            return;
        }

        for (OpcaoDeVoto opcao : opcoes) {
            if (opcao.getNome().equalsIgnoreCase(opcaoEscolhida)) {
                opcao.adicionarVoto();
                votante.setJaVotou(true);
                System.out.println("Voto registrado para " + opcaoEscolhida);
                break;
            }
        }
    }

    private void contarVotos() {
        StringBuilder resultado = new StringBuilder("Resultado da Votação:\n");
        for (OpcaoDeVoto opcao : opcoes) {
            resultado.append(opcao.getNome()).append(": ").append(opcao.getVotos()).append(" votos\n");
        }

        System.out.println(resultado);
        JOptionPane.showMessageDialog(null, resultado.toString(), "Resultado da Votação", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<OpcaoDeVoto> getOpcoes() {
        return opcoes;
    }

    public List<Votante> getVotantes() {
        return votantes;
    }

    @Override
    public String toString() {
        return "Votação - Estado: " + estado.getClass().getSimpleName() + ", Opções: " + opcoes;
    }
}
