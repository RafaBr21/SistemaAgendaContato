package br.com.agenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class AgendaView extends JFrame {
    private JTextField campoNome;
    private JTextField campoNumero;
    private JTable tabelaContatos;
    private DefaultTableModel modeloTabela;

    private AgendaController controller;

    public AgendaView() {
        controller = new AgendaController();

        setTitle("Agenda de Contatos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCadastro = new JPanel(new GridBagLayout());
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Contatos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField(15);
        campoNome.setFont(new Font("Arial",Font.ITALIC,18));

        JLabel labelNumero = new JLabel("Número:");
        campoNumero = new JTextField(15);
        campoNumero.setFont(new Font("Arial", Font.ITALIC,18));

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(e -> {
            String nome = campoNome.getText();
            String numero = campoNumero.getText();

            if (!nome.isEmpty() && !numero.isEmpty()) {
                controller.carregarContatos();
                controller.adiciornarContato(nome, numero);
                atualizarTabela();
                campoNome.setText("");
                campoNumero.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Trecho que irá adicionar os componentes ao painel com layout ajustado
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCadastro.add(labelNome, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCadastro.add(campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCadastro.add(labelNumero, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCadastro.add(campoNumero, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelCadastro.add(botaoAdicionar, gbc);

        add(painelCadastro, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Número"}, 0);
        tabelaContatos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaContatos);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Lista de Contatos"));

        add(scrollTabela, BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoCarregar = new JButton("Carregar");
        JButton botaoExbirGrafico = new JButton("Exibir Gráfico");

        botaoSalvar.addActionListener(e -> {
            controller.SalvarContatos();
            JOptionPane.showMessageDialog(this, "Contatos salvos com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoCarregar.addActionListener(e -> { controller.carregarContatos();
            atualizarTabela();
            if (controller.getContatos().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Não possui contatos cadastrados", "Informação",
                        JOptionPane.INFORMATION_MESSAGE);
            } else { JOptionPane.showMessageDialog(this,
                    "Contatos carregados com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE); } });

        botaoExbirGrafico.addActionListener(e -> {
            Map<String, Integer> dadosGraficos = controller.getDadosGrafico();
            AgendaChartView chartView = new AgendaChartView(dadosGraficos);
            chartView.setVisible(true);
        });

        painelAcoes.add(botaoSalvar);
        painelAcoes.add(botaoCarregar);
        painelAcoes.add(botaoExbirGrafico);

        add(painelAcoes, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (String[] contato : controller.getContatos()) {
            modeloTabela.addRow(new Object[]{contato[0], contato[1]});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaView().setVisible(true));
    }
}
