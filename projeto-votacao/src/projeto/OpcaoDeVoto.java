package projeto;

public class OpcaoDeVoto {
    private String nome;

    public OpcaoDeVoto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
