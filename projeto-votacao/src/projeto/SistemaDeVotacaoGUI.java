package projeto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SistemaDeVotacaoGUI {
    private JFrame frame;
    private JTextField nomeField, cpfField, tituloField;
    private JComboBox<String> opcaoComboBox;
    private JButton votarButton, sairButton;
    private JLabel messageLabel;

    public SistemaDeVotacaoGUI() {
        // Configuração do Frame
        frame = new JFrame("Sistema de Votação");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel Principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Bordas ao redor

        // Painel de Dados do Votante
        JPanel voterPanel = new JPanel(new GridBagLayout());
        voterPanel.setBackground(new Color(240, 240, 240)); // Cor de fundo leve
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
        voterPanel.add(new JLabel("Título de Eleitor:"), gbc);
        tituloField = new JTextField(20);
        gbc.gridx = 1;
        voterPanel.add(tituloField, gbc);

        mainPanel.add(voterPanel);

        // Seção de Opção de Voto
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionPanel.setBackground(new Color(230, 230, 230)); // Cor de fundo leve
        optionPanel.setBorder(BorderFactory.createTitledBorder("Escolha uma opção:"));
        opcaoComboBox = new JComboBox<>(new String[] {"Candidato A", "Candidato B"});
        optionPanel.add(opcaoComboBox);

        mainPanel.add(optionPanel);

        // Botões de Votação e Sair
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        votarButton = new JButton("Votar");
        votarButton.addActionListener(new VoteAction());
        buttonPanel.add(votarButton);

        sairButton = new JButton("Sair");
        sairButton.addActionListener(new SairAction());
        buttonPanel.add(sairButton);

        mainPanel.add(buttonPanel);

        // Label de Mensagem
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

            // Validação dos campos
            if (nome.isEmpty() || cpf.isEmpty() || titulo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Processa o voto usando ServicoDeVotacao
            ServicoDeVotacao servico = new ServicoDeVotacao();
            OpcaoDeVoto opcaoDeVoto = new OpcaoDeVoto(opcao);
            String mensagem = servico.votar(nome, cpf, titulo, opcaoDeVoto);

            JOptionPane.showMessageDialog(frame, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SairAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Tem certeza de que deseja encerrar a votação?", "Encerrar Votação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                GerenciadorDeVotacao.getInstancia().encerrarVotacao();
                JOptionPane.showMessageDialog(frame, "A votação foi encerrada.", "Encerramento", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Fecha a janela
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaDeVotacaoGUI::new);
    }
}
