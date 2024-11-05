package projeto;

import javax.swing.JOptionPane;

public class GerenciadorDeVotacaoProxy {
    private GerenciadorDeVotacao gerenciador;
    private static final String ADMIN_NOME = "admin";       // Nome de administrador pré-definido
    private static final String ADMIN_CPF = "1234567890";   // CPF de administrador pré-definido
    private static final String ADMIN_SENHA = "senha123";   // Senha de administrador pré-definida

    public GerenciadorDeVotacaoProxy() {
        this.gerenciador = GerenciadorDeVotacao.getInstancia();
    }

    // Alterado de private para public para permitir acesso externo
    public boolean autenticar(String nome, String cpf, String senha) {
        return ADMIN_NOME.equals(nome) && ADMIN_CPF.equals(cpf) && ADMIN_SENHA.equals(senha);
    }

    public void iniciarVotacao(String nome, String cpf, String senha) {
        if (autenticar(nome, cpf, senha)) {
            gerenciador.iniciarVotacao();
            JOptionPane.showMessageDialog(null, "Votação iniciada com sucesso!", "Início de Votação", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Autenticação falhou! Credenciais inválidas.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void encerrarVotacao(String nome, String cpf, String senha) {
        if (autenticar(nome, cpf, senha)) {
            gerenciador.encerrarVotacao();
            JOptionPane.showMessageDialog(null, "Votação encerrada com sucesso!", "Encerramento de Votação", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Autenticação falhou! Credenciais inválidas.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
}