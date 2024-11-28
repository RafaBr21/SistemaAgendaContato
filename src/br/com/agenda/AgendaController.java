package br.com.agenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgendaController {
    private final List<String[]> listaContatos;
    private final SalvarECarregar salvarECarregar;

    public AgendaController() {
        listaContatos = new ArrayList<>();
        salvarECarregar = new SalvarECarregar();
    }

    public void adiciornarContato(String nome, String numero) {
        listaContatos.add(new String[]{nome, numero});
    }

    public List<String[]> getContatos() {
        return listaContatos;
    }

    public void SalvarContatos() {
        salvarECarregar.salvarContatos(listaContatos);
    }

    public void carregarContatos() {
        listaContatos.clear();
        List<String[]> contatosCarregados = salvarECarregar.carregarContatos();
        for (String[] contato : contatosCarregados) {
            String nome = contato[0];
            String numero = contato[1];
            listaContatos.add(new String[]{nome, numero});
        }
    }

    public Map<String, Integer> getDadosGrafico() {
        Map<String, Integer> dados = new HashMap<>();

        for (String[] contato : listaContatos) {
            if (contato[0] != null && !contato[0].isEmpty()) {
                String inicial = contato[0].substring(0, 1).toUpperCase();
                dados.put(inicial, dados.getOrDefault(inicial, 0) + 1);
            }
        }
        return dados;
    }
}
