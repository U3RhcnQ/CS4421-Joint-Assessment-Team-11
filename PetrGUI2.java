// Flatlaf LAF
import com.formdev.flatlaf.FlatLightLaf;
import javax.imageio.ImageIO;
// Swing Libraries
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
//Java Events
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Java Charting Library
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class PetrGUI2 {

    private static void createAndShowGUI() {

        FlatLightLaf.setup();  //Must be called first of all Swing code as this sets the look and feel to FlatDark.
        final JFrame frame = new JFrame("TaskSys"); // Title

        // Layout
        GridBagConstraints CPUChart = new GridBagConstraints();
        CPUChart.weightx = 0.7;
        CPUChart.weighty = 0.6;
        CPUChart.fill = GridBagConstraints.BOTH;
        CPUChart.gridx = 0;
        CPUChart.gridy = 0;
        CPUChart.insets = new Insets(10, 10, 10, 10);

        GridBagConstraints CPURightInfo = new GridBagConstraints();
        CPURightInfo.weightx = 0.1;
        CPURightInfo.weighty = 0.6;
        CPURightInfo.fill = GridBagConstraints.NONE;
        CPURightInfo.gridx = 1;
        CPURightInfo.gridy = 0;

        GridBagConstraints CPUBottomInfo = new GridBagConstraints();
        CPUBottomInfo.weightx = 1.0;
        CPUBottomInfo.weighty = 0.4;
        CPUBottomInfo.fill = GridBagConstraints.BOTH;
        CPUBottomInfo.gridx = 0;
        CPUBottomInfo.gridy = 1;

        GridBagConstraints TitleConstraints = new GridBagConstraints();
        TitleConstraints.gridx = 0;
        TitleConstraints.gridy = 0;
        TitleConstraints.weightx = 1;
        TitleConstraints.weighty = 0;
        TitleConstraints.fill = GridBagConstraints.HORIZONTAL;
        TitleConstraints.ipadx = 0;
        TitleConstraints.ipady = 30;

        GridBagConstraints TableConstraints = new GridBagConstraints();
        TableConstraints.gridx = 0;
        TableConstraints.gridy = 1;
        TableConstraints.weightx = 1;
        TableConstraints.weighty = 1;
        TableConstraints.fill = GridBagConstraints.BOTH;

        // Attempt to load the image and handle exception if not available
        try {
            // Load the logo image
            Image icon = ImageIO.read(new File("images/icon.png"));
            // Set the icon for the JFrame
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JTabbedPane tabbedPane = new JTabbedPane(); // Create The tabbed Pane

        // Add the First Panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        // Chart Code
        XYSeries series;

        // Create a series to hold the data
        series = new XYSeries("");

        // Create a dataset and add the series to it
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Create the chart using JFreeChart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", // Chart title
                "",           // X-Axis Label
                "",          // Y-Axis Label
                dataset,          // Dataset for the chart
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Get the plot from the chart and set the range for the y-axis
        XYPlot plot = chart.getXYPlot();
        plot.getRangeAxis().setRange(0, 100); // Set y-axis range from 0 to 400

        // Create a panel for the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
        panel1.add(chartPanel, CPUChart);

        JLabel cpu_right_info_text = new JLabel("<html>" +
                "<font size=-1>" + "</font><br><br>" +
                "<table cellpadding='5' align='center'>"+
                "<tr><td><font size=-1> Sockets: </font></td><td align='center'>" + " </td></tr>"+
                "<tr><td><font size=-1> Cores: </font></td><td align='center'>" +  " </td></tr>" +
                "<tr><td><font size=-1> L1 Data Cache: </font></td><td align='center'>" +  " </td></tr>" +
                "<tr><td><font size=-1> L1 Intr Cache: </font></td><td align='center'>" + " </td></tr>" +
                "<tr><td><font size=-1> L1 Cache: </font></td><td align='center'>" +  " </td></tr>" +
                "<tr><td><font size=-1> L2 Cache: </font></td><td align='center'>" +  " </td></tr>" +
                "<tr><td><font size=-1> L3 Cache: </font></td><td align='center'>" +  " </td></tr>" +
                "" +
                "</table></html>");

        panel1.add(cpu_right_info_text, CPURightInfo);

        JLabel cpu_bottom_info_panel = new JLabel("CPU Info");
        cpu_bottom_info_panel.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(cpu_bottom_info_panel, CPUBottomInfo);

        // Timer to update the chart every second
        Timer timer = new Timer(10, new ActionListener() {
            int time = 0;
            int counter;
            int sum;
            int cores;
            @Override
            public void actionPerformed(ActionEvent e) {

                //cores = cpu.coresPerSocket();
                //cpu.read(1);
                time++;

            }
        });
        timer.start();


        tabbedPane.addTab("CPU Info", null, panel1,"CPU Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        JPanel panel2 = new JPanel(false);
        JLabel filler2 = new JLabel("Memory Info");
        panel2.add(new JButton("FlatDarkLaf button!"));
        panel2.add(new JTextField("FlatDarkLaf text field!"));
        filler2.setHorizontalAlignment(JLabel.CENTER);
        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(filler2);
        tabbedPane.addTab("Memory Info", null, panel2,"Memory Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);


        JPanel USBPanelWrapper = new JPanel(new BorderLayout());
        USBPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel USBPanel = new JPanel(new GridBagLayout());
        JLabel USBTitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>USB Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh every 10 seconds or manually</span></html>");
        USBPanel.add(USBTitle, TitleConstraints);

        // Data to be displayed in the JTable
        String[][] USBData = {
                { "Kundan Kumar Jha", "4031", "CSE", "1", "2", "3", "4" },
                { "Kundan Kumar Jha", "4031", "CSE", "1", "2", "3", "4"}
        };

        // Column Names
        String[] USBColumnNames = { "Bus", "Device", "FFunction", "Vendor ID", "Vendor", "Device ID", "Device" };

        // Initializing the JTable
        JTable USBTable = new JTable(USBData, USBColumnNames);
        USBTable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane USBScrollPane = new JScrollPane(USBTable);
        USBPanel.add(USBScrollPane, TableConstraints);
        USBPanelWrapper.add(USBPanel);
        tabbedPane.addTab("USB Info", null, USBPanelWrapper,"USB Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

        JPanel panel4 = new JPanel(false);

        // Data to be displayed in the JTable
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE", "1", "2", "3", "4" },
                { "Kundan Kumar Jha", "4031", "CSE", "1", "2", "3", "4"}
        };

        // Column Names
        String[] columnNames = { "Bus", "Device", "FFunction", "Vendor ID", "Vendor", "Device ID", "Device" };

        // Initializing the JTable
        JTable table1 = new JTable(data, columnNames);
        table1.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table1);

        JLabel filler4 = new JLabel("PCI Info");
        filler4.setHorizontalAlignment(JLabel.CENTER);
        panel4.setLayout(new GridLayout(1, 1));
        panel4.add(sp);
        tabbedPane.addTab("PCI Info", null, panel4,"PCI Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tabbedPane);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(final String[] args) {

        SwingUtilities.invokeLater(PetrGUI2::createAndShowGUI);
        //System.loadLibrary("sysinfo");
        //sysInfo info = new sysInfo();
        //cpuInfo cpu = new cpuInfo();
        //cpu.read(0);

        //showCPU();
    }
}