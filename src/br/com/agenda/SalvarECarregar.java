package br.com.agenda;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalvarECarregar {
    private static final String ARQUIVO_CONTATOS = "contatos.txt";

    // Irá Salvar os contatos em um arquivo
    public void salvarContatos(List<String[]> contatos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CONTATOS))) {
            for (String[] contato : contatos) {
                writer.write(contato[0] + ";" + contato[1]);  // Posição contato[0] - Nome, contato[1] - Numero
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Irá Carregar os contatos do arquivo
    public List<String[]> carregarContatos() {
        List<String[]> contatos = new ArrayList<>();
        File arquivo = new File(ARQUIVO_CONTATOS); // Verifica a existência do arquivo

        // Condição para  Verificar se o arquivo existe antes de tentar lê-lo
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONTATOS))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] contato = linha.split(";");
                    if (contato.length == 2) {
                        // Objetivo de  Inverter a ordem para (nome, número)
                        contatos.add(new String[]{contato[0], contato[1]});
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Arquivo não encontrado. Nenhum dado foi carregado.");
        }
        return contatos;
    }
}
