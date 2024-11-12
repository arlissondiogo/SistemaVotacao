package projetoUrna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaDeVotacaoGUI {
    private JFrame frame;
    private JTextField nomeField, cpfField, tituloField;
    private JComboBox<String> opcaoComboBox;
    private JButton votarButton, sairButton;
    private JLabel messageLabel;
    private GerenciadorDeVotacaoProxy gerenciadorProxy;

    public SistemaDeVotacaoGUI() {
        gerenciadorProxy = new GerenciadorDeVotacaoProxy();
        exibirJanelaAutenticacao();
    }

    private void exibirJanelaAutenticacao() {
        JFrame authFrame = new JFrame("Autenticação do Administrador");
        authFrame.setSize(300, 200);
        authFrame.setLocationRelativeTo(null);
        authFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel authPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        authPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nomeAdminField = new JTextField();
        JTextField cpfAdminField = new JTextField();
        JPasswordField senhaAdminField = new JPasswordField();

        authPanel.add(new JLabel("Nome:"));
        authPanel.add(nomeAdminField);
        authPanel.add(new JLabel("CPF:"));
        authPanel.add(cpfAdminField);
        authPanel.add(new JLabel("Senha:"));
        authPanel.add(senhaAdminField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String nome = nomeAdminField.getText().trim();
            String cpf = cpfAdminField.getText().trim();
            String senha = new String(senhaAdminField.getPassword()).trim();

            if (gerenciadorProxy.autenticar(nome, cpf, senha)) {
                gerenciadorProxy.iniciarVotacao(nome, cpf, senha);
                authFrame.dispose();
                exibirJanelaVotacao();
            } else {
                JOptionPane.showMessageDialog(authFrame, "Credenciais inválidas. Tente novamente.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        });

        authPanel.add(loginButton);
        authFrame.add(authPanel);
        authFrame.setVisible(true);
    }

    private void exibirJanelaVotacao() {
        frame = new JFrame("Sistema de Votação");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel voterPanel = new JPanel(new GridBagLayout());
        voterPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        voterPanel.add(new JLabel("Nome do Votante:"), gbc);
        nomeField = new JTextField(20);
        gbc.gridx = 1;
        voterPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        voterPanel.add(new JLabel("CPF:"), gbc);
        cpfField = new JTextField(20);
        gbc.gridx = 1;
        voterPanel.add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        voterPanel.add(new JLabel("Título de Elseitor:"), gbc);
        tituloField = new JTextField(20);
        gbc.gridx = 1;
        voterPanel.add(tituloField, gbc);

        mainPanel.add(voterPanel);

        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionPanel.setBackground(new Color(230, 230, 230));
        optionPanel.setBorder(BorderFactory.createTitledBorder("Escolha uma opção:"));
        opcaoComboBox = new JComboBox<>(new String[] {"Taylor Swift", "Kanye West"});
        optionPanel.add(opcaoComboBox);

        mainPanel.add(optionPanel);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        votarButton = new JButton("Votar");
        votarButton.addActionListener(new VoteAction());
        controlPanel.add(votarButton);

        sairButton = new JButton("Sair");
        sairButton.addActionListener(new SairAction());
        controlPanel.add(sairButton);

        mainPanel.add(controlPanel);

        messageLabel = new JLabel(" ");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(messageLabel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private class VoteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText().trim();
            String cpf = cpfField.getText().trim();
            String titulo = tituloField.getText().trim();
            String opcao = (String) opcaoComboBox.getSelectedItem();

            if (nome.isEmpty() || cpf.isEmpty() || titulo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ServicoDeVotacao servico = new ServicoDeVotacao();
            OpcaoDeVoto opcaoDeVoto = new OpcaoDeVoto(opcao);
            String mensagem = servico.votar(nome, cpf, titulo, opcaoDeVoto);

            JOptionPane.showMessageDialog(frame, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SairAction implements ActionListener {
        @Override
        public void actionPerformed(Action e) {
            JFrame authFrame = new JFrame("Autenticação para Encerrar Votação");
            authFrame.setSize(300, 200);
            authFrame.setLocationRelativeTo(frame);
            authFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel authPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            authPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JTextField nomeAdminField = new JTextField();
            JTextField cpfAdminField = new JTextField();
            JPasswordField senhaAdminField = new JPasswordField();

            authPanel.add(new JLabel("Nome:"));
            authPanel.add(nomeAdminField);
            authPanel.add(new JLabel("CPF:"));
            authPanel.add(cpfAdminField);
            authPanel.add(new JLabel("Senha:"));
            authPanel.add(senhaAdminField);

            JButton confirmButton = new JButton("Confirmar");
            confirmButton.addActionListener(evt -> {
                String nome = nomeAdminField.getText().trim();
                String cpf = cpfAdminField.getText().trim();
                String senha = new String(senhaAdminField.getPassword()).trim();

                if (gerenciadorProxy.autenticar(nome, cpf, senha)) {
                    gerenciadorProxy.encerrarVotacao(nome, cpf, senha);
                    authFrame.dispose();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(authFrame, "Credenciais inválidas. Tente novamente.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            });

            authPanel.add(confirmButton);
            authFrame.add(authPanel);
            authFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaDeVotacaoGUI::new);
    }
}
