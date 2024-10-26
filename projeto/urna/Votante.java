package urna;
// Votante
public class Votante {
    private String nome;
    private boolean jaVotou;

    public Votante(String nome) {
        this.nome = nome;
        this.jaVotou = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isJaVotou() {
        return jaVotou;
    }

    public void setJaVotou(boolean jaVotou) {
        this.jaVotou = jaVotou;
    }
}