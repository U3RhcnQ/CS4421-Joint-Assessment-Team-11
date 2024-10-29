import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {
    private DefaultPieDataset dataset;
    private JFreeChart chart;

    public PieChartPanel(String title, DefaultPieDataset initialData) {
        // Initialize dataset and chart with initial data
        this.dataset = initialData;
        this.chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        // Add chart to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
    }

    public void updateData(String category, double value) {
        // Update the dataset with new values
        dataset.setValue(category, value);
    }

    public DefaultPieDataset getDataset() {
        return dataset;
    }
}
