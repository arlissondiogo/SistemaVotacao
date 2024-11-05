package projeto;
import java.util.ArrayList;
import java.util.List;

public class Votacao {
    private EstadoDaVotacao estado;
    private List<OpcaoDeVoto> opcoes;
    private List<Votante> votantes;

    public Votacao() {
        this.estado = EstadoDaVotacao.ABERTA; // Estado inicial
        this.opcoes = new ArrayList<>();
        this.votantes = new ArrayList<>();
        // Adicionando opções de voto
        opcoes.add(new OpcaoDeVoto("Candidato A"));
        opcoes.add(new OpcaoDeVoto("Candidato B"));
    }

    public void setEstado(EstadoDaVotacao novoEstado) {
        this.estado = novoEstado;
        estado.manipularRequisicao(this);
    }
    

    public EstadoDaVotacao getEstado() {
        return estado;
    }

    public void manipularRequisicao() {
        estado.manipularRequisicao(this);
    }

    public List<OpcaoDeVoto> getOpcoes() {
        return opcoes;
    }

    public List<Votante> getVotantes() {
        return votantes;
    }

    public void adicionarVotante(Votante votante) {
        votantes.add(votante);
    }

    @Override
    public String toString() {
        return "Votação - Estado: " + estado.getClass().getSimpleName() + ", Opções: " + opcoes;
    }
}
