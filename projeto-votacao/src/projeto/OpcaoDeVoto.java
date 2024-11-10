package projeto;

public class OpcaoDeVoto {
    private String nome;
    private int votos;  // Adiciona contador de votos para cada opção

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
