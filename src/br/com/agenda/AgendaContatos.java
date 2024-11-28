package br.com.agenda;

import javax.swing.*;

public class AgendaContatos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgendaView agendaView = new AgendaView();
            agendaView.setVisible(true);
        });
    }
}
