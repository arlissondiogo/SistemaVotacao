package projetoUrna;

public abstract class EstadoDaVotacao {
    public static final EstadoDaVotacao ABERTA = new EstadoAberta();
    public static final EstadoDaVotacao FECHADA = new EstadoFechada();

    public abstract void manipularRequisicao(Votacao votacao);
}

class EstadoAberta extends EstadoDaVotacao {
    @Override
    public void manipularRequisicao(Votacao votacao) {
        System.out.println("A votação está aberta.");
    }
}

class EstadoFechada extends EstadoDaVotacao {
    @Override
    public void manipularRequisicao(Votacao votacao) {
        System.out.println("A votação está fechada.");
    }
}
