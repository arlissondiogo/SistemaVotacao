package projetoUrna;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeVotacao {
    private static GerenciadorDeVotacao instancia;
    private Votacao votacao;
    private List<EventoDeVotacaoListener> listeners;

    private GerenciadorDeVotacao() {
        votacao = new Votacao();
        listeners = new ArrayList<>();
    }

    public static GerenciadorDeVotacao getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorDeVotacao();
        }
        return instancia;
    }

    public void iniciarVotacao() {
        votacao.setEstado(EstadoDaVotacao.ABERTA);
        notificarMudancaDeEstado("Votação iniciada");
    }

    public void encerrarVotacao() {
        votacao.setEstado(EstadoDaVotacao.FECHADA);
        notificarMudancaDeEstado("Votação encerrada");
        exibirResultadoFinal();
    }

    public void adicionarListener(EventoDeVotacaoListener listener) {
        listeners.add(listener);
    }

    public void removerListener(EventoDeVotacaoListener listener) {
        listeners.remove(listener);
    }

    private void notificarMudancaDeEstado(String mensagem) {
        for (EventoDeVotacaoListener listener : listeners) {
            listener.onEstadoMudou(mensagem);
        }
    }

    public void notificarVotoRegistrado(OpcaoDeVoto opcaoDeVoto) {
        for (EventoDeVotacaoListener listener : listeners) {
            listener.onVotoRegistrado(opcaoDeVoto);
        }
    }

    private void exibirResultadoFinal() {
        StringBuilder resultado = new StringBuilder("Resultado da votação:\n");
        for (OpcaoDeVoto opcao : votacao.getOpcoes()) {
            resultado.append(opcao.getNome()).append(": ").append(opcao.getVotos()).append(" votos\n");
        }
        System.out.println(resultado.toString());
    }

    public Votacao getVotacao() {
        return votacao;
    }
}
