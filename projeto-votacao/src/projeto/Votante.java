package projeto;

public class Votante {
    private String nome;
    private String cpf;
    private String titulo;
    private boolean jaVotou;

    public Votante(String nome, String cpf, String titulo) {
        this.nome = nome;
        this.cpf = cpf;
        this.titulo = titulo;
        this.jaVotou = false;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isJaVotou() {
        return jaVotou;
    }

    public void setJaVotou(boolean jaVotou) {
        this.jaVotou = jaVotou;
    }
}
