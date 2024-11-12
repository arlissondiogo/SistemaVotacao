package projetoUrna;

public class OpcaoDeVoto {
    private String nome;
    private int votos;  

    public OpcaoDeVoto(String nome) {
        this.nome = nome;
        this.votos = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVotos() {
        return votos;
    }

    public void adicionarVoto() {
        this.votos++;
    }

    @Override
    public String toString() {
        return nome;
    }
}
