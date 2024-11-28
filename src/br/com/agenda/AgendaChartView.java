package br.com.agenda;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AgendaChartView extends JFrame {

    public AgendaChartView(Map<String, Integer> data) {
        setTitle("Gráfico de Contatos");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dataset = criarDataset(data);

        JFreeChart chart = ChartFactory.createBarChart(
                "Quantidade de Contatos por Inicial",
                "Inicial do Nome",
                "Quantidade",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(780, 550));
        add(chartPanel, BorderLayout.CENTER);
    }

    private DefaultCategoryDataset criarDataset(Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Contatos", entry.getKey());
        }

        return dataset;
    }
}
