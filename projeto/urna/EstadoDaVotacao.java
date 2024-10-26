package urna;

class EstadoDaVotacao {
    public static final EstadoDaVotacao ABERTA = new EstadoAberta();
    public static final EstadoDaVotacao FECHADA = new EstadoFechada();

    public void manipularRequisicao(Votacao votacao) {
        System.out.println("A votação está em um estado indefinido.");
    }
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
