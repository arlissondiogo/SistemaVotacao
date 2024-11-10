package projeto;

public interface EventoDeVotacaoListener {
    void onEstadoMudou(String mensagem);
    void onVotoRegistrado(OpcaoDeVoto opcaoDeVoto);
}
