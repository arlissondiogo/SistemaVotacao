package urna;
import java.util.Scanner;

public class SistemaDeVotacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeVotacao gerenciador = GerenciadorDeVotacao.getInstancia();
        ServicoDeVotacao servico = new ServicoDeVotacao();

        gerenciador.iniciarVotacao();
        System.out.println(gerenciador.getVotacao());

        while (true) {
            System.out.println("Digite o nome do votante (ou 'sair' para encerrar):");
            String nomeVotante = scanner.nextLine();

            if (nomeVotante.equalsIgnoreCase("sair")) {
                break; 
            }

            System.out.println("Escolha uma opção para votar:");
            for (int i = 0; i < gerenciador.getVotacao().getOpcoes().size(); i++) {
                System.out.println((i + 1) + ": " + gerenciador.getVotacao().getOpcoes().get(i));
            }

            int escolha = Integer.parseInt(scanner.nextLine());
            OpcaoDeVoto opcaoSelecionada = gerenciador.getVotacao().getOpcoes().get(escolha - 1);
            
            servico.votar(nomeVotante, opcaoSelecionada);
        }

        gerenciador.encerrarVotacao();
        System.out.println("\nEstado final da votação:");
        System.out.println(gerenciador.getVotacao());
        scanner.close();
    }
}
