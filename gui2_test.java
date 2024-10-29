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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
//Java Events
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
// Java Charting Library
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class gui2_test {

    private static patricktest usb;
    private static PciTJ pci;
    private static SamMclCPU cpu;
    private DefaultPieDataset dataset;
    private JFreeChart chart;



    private static void createAndShowGUI() {

        FlatLightLaf.setup();  //Must be called first of all Swing code as this sets the look and feel to FlatDark.
        final JFrame frame = new JFrame("TaskSys"); // Title
        DefaultTableModel USBTableModel = null; // Declare the table model
        DefaultTableModel PCITableModel; // Declare the table model

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
        TitleConstraints.anchor = GridBagConstraints.WEST;  // Align to left
        TitleConstraints.gridwidth = 2;

        GridBagConstraints TableConstraints = new GridBagConstraints();
        TableConstraints.gridx = 0;
        TableConstraints.gridy = 1;
        TableConstraints.weightx = 1;
        TableConstraints.weighty = 1;
        TableConstraints.fill = GridBagConstraints.BOTH;
        TableConstraints.anchor = GridBagConstraints.WEST;  // Align to left

        GridBagConstraints RefreshButtonConstraints = new GridBagConstraints();
        RefreshButtonConstraints.gridx = 0;
        RefreshButtonConstraints.gridy = 0;
        RefreshButtonConstraints.weightx = 0;
        RefreshButtonConstraints.weighty = 0;
        RefreshButtonConstraints.fill = GridBagConstraints.NONE;
        RefreshButtonConstraints.ipadx = 0;
        RefreshButtonConstraints.ipady = 5;
        RefreshButtonConstraints.anchor = GridBagConstraints.EAST;  // Align to right
        RefreshButtonConstraints.gridwidth = 2;

        GridBagConstraints TextAreaConstraints = new GridBagConstraints();
        TextAreaConstraints.gridx = 1;
        TextAreaConstraints.gridy = 1;
        TextAreaConstraints.weightx = 0.2;
        TextAreaConstraints.weighty = 1;
        TextAreaConstraints.fill = GridBagConstraints.BOTH;
        TextAreaConstraints.ipadx = 0;
        TextAreaConstraints.ipady = 0;
        TextAreaConstraints.anchor = GridBagConstraints.EAST;  // Align to right
        //TextAreaConstraints.insets = new Insets(0, 10, 0, 0);

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
        JPanel CPUPanelWrapper = new JPanel(new BorderLayout());
        CPUPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel CPUpanel = new JPanel();
        CPUpanel.setLayout(new GridBagLayout());

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
        chartPanel.setPreferredSize(new Dimension(200, 200));
        CPUpanel.add(chartPanel, CPUChart);

        JLabel cpu_right_info_text = new JLabel("<html>" +
                "<font size=-1>" + "</font><br><br>" +
                "<table cellpadding='5' align='center'>"+
                "<tr><td><font size=-1> Sockets: </font></td><td align='center'>" + " </td></tr>"+
                "<tr><td><font size=-1> Cores: </font></td><td align='center'>" +  " </td></tr>" +
                "<tr><td><font size=-1> L1 Data Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel1d()) +  " <font size=-2> KB </font></td></tr>" +
                "<tr><td><font size=-1> L1 Intr Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel1i()) + " <font size=-2> KB </font></td></tr>" +
                "<tr><td><font size=-1> L1 Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel1()) + " <font size=-2> KB </font></td></tr>" +
                "<tr><td><font size=-1> L2 Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel2()) + " <font size=-2> MB </font></td></tr>" +
                "<tr><td><font size=-1> L3 Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel3()) + " <font size=-2> MB </font></td></tr>" +
                "</table></html>");

        CPUpanel.add(cpu_right_info_text, CPURightInfo);
        CPUPanelWrapper.add(CPUpanel);
        JLabel cpu_bottom_info_panel = new JLabel("CPU Info");
        cpu_bottom_info_panel.setHorizontalAlignment(JLabel.CENTER);
        CPUpanel.add(cpu_bottom_info_panel, CPUBottomInfo);

        // Timer to update the chart every second
        Timer timer = new Timer(10, new ActionListener() {
            int time = 0;
            int counter;
            int sum;
            int cores;
            @Override
            public void actionPerformed(ActionEvent e) {

                // Add Data
                series.add(time, SamMclCPU.utilisationTime());
                time++;

                // Check if the series has more than 60 entries, remove the oldest if true
                if (series.getItemCount() > 60) {
                    series.remove(0); // Remove the first (oldest) entry
                }
            }
        });
        timer.start();


        tabbedPane.addTab("CPU Info", null, CPUPanelWrapper,"CPU Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        JPanel panel2 = new JPanel(false);

        // Sample dataset for CPU pie chart
        DefaultPieDataset cpuDataset = new DefaultPieDataset();
        cpuDataset.setValue("Core 1", 25);
        cpuDataset.setValue("Core 2", 35);
        cpuDataset.setValue("Core 3", 20);
        cpuDataset.setValue("Core 4", 20);

        // Create a pie chart for CPU data
        PieChartPanel cpuPieChart = new PieChartPanel("CPU Utilization", cpuDataset);

        // Another dataset and pie chart example for a different component
        DefaultPieDataset memoryDataset = new DefaultPieDataset();
        memoryDataset.setValue("Used", 70);
        memoryDataset.setValue("Free", 30);
        PieChartPanel memoryPieChart = new PieChartPanel("Memory Utilization", memoryDataset);

        // Example layout for pie charts
        JPanel chartPanel2 = new JPanel(new GridLayout(1, 2));
        chartPanel2.add(cpuPieChart);
        chartPanel2.add(memoryPieChart);

        panel2.add(chartPanel2);

        tabbedPane.addTab("Memory Info", null, panel2,"Memory Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);


        JPanel USBPanelWrapper = new JPanel(new BorderLayout());
        USBPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel USBPanel = new JPanel(new GridBagLayout());
        JLabel USBTitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>USB Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh every 10 seconds or manually</span></html>");
        USBPanel.add(USBTitle, TitleConstraints);

        JButton USBRefresh = new JButton("Refresh");
        USBPanel.add(USBRefresh, RefreshButtonConstraints);

        // Column Names
        String[] USBColumnNames = { "Bus", "Device", "FFunction", "Vendor ID", "Vendor", "Device ID", "Device" };

        // Initializing the JTable
        USBTableModel = new DefaultTableModel(USBColumnNames, 0); // Initialize the table model
        JTable USBTable = new JTable(USBTableModel);
        USBTable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane USBScrollPane = new JScrollPane(USBTable);
        USBPanel.add(USBScrollPane, TableConstraints);

        JTextArea USBText = new JTextArea("Please Select a Device to view Details");
        USBText.setLineWrap(true);               // Enable line wrap
        USBText.setWrapStyleWord(true);          // Wrap at word boundaries
        USBText.setEditable(false); // Set non-editable
        USBText.setPreferredSize(new Dimension(120, USBText.getPreferredSize().height));
        //USBText.setBorder(new EmptyBorder(10, 10, 10, 10));  // Padding on all sides
        USBPanel.add(USBText, TextAreaConstraints);

        // Selection Listener for displaying selected row in USBText
        USBTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && USBTable.getSelectedRow() != -1) {
                    // Get selected row index
                    int selectedRow = USBTable.getSelectedRow();

                    // Display row details in the JTextArea
                    StringBuilder details = new StringBuilder();
                    for (int i = 0; i < USBTable.getColumnCount(); i++) {
                        String columnName = USBTable.getColumnName(i);
                        Object value = USBTable.getValueAt(selectedRow, i);
                        details.append(columnName).append(": ").append(value).append("\n");
                    }
                    USBText.setText(details.toString());
                }
            }
        });

        DefaultTableModel finalUSBTableModel = USBTableModel;
        USBRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable(finalUSBTableModel, usb.getUSBInfoAs2DArray()); // Pass the table model to the refresh method
            }
        });

        USBPanelWrapper.add(USBPanel);
        tabbedPane.addTab("USB Info", null, USBPanelWrapper,"USB Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);
        refreshTable(USBTableModel, usb.getUSBInfoAs2DArray()); // Fetch Initial Data for USB


        // PCI Screen
        JPanel PCIPanelWrapper = new JPanel(new BorderLayout());
        PCIPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel PCIPanel = new JPanel(new GridBagLayout());
        JLabel PCITitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>PCI Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh every 10 seconds or manually</span></html>");
        PCIPanel.add(PCITitle, TitleConstraints);

        JButton PCIRefresh = new JButton("Refresh");
        PCIPanel.add(PCIRefresh, RefreshButtonConstraints);

        // Column Names
        String[] PCIColumnNames = { "Bus", "Device", "Function", "Vendor ID", "Vendor", "Device ID", "Device" };

        // Initializing the JTable
        PCITableModel = new DefaultTableModel(PCIColumnNames, 0); // Initialize the table model
        JTable PCITable = new JTable(PCITableModel);
        PCITable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane PCIScrollPane = new JScrollPane(PCITable);
        PCIPanel.add(PCIScrollPane, TableConstraints);

        JTextArea PCIText = new JTextArea("Please Select a Device to view Details");
        PCIText.setLineWrap(true);               // Enable line wrap
        PCIText.setWrapStyleWord(true);          // Wrap at word boundaries
        PCIText.setEditable(false); // Set non-editable
        PCIText.setPreferredSize(new Dimension(120, PCIText.getPreferredSize().height));
        //PCIText.setBorder(new EmptyBorder(10, 10, 10, 10));  // Padding on all sides
        PCIPanel.add(PCIText, TextAreaConstraints);

        // Selection Listener for displaying selected row in PCIText
        PCITable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && PCITable.getSelectedRow() != -1) {
                    // Get selected row index
                    int selectedRow = PCITable.getSelectedRow();

                    // Display row details in the JTextArea
                    StringBuilder details = new StringBuilder();
                    for (int i = 0; i < PCITable.getColumnCount(); i++) {
                        String columnName = PCITable.getColumnName(i);
                        Object value = PCITable.getValueAt(selectedRow, i);
                        details.append(columnName).append(": ").append(value).append("\n");
                    }
                    PCIText.setText(details.toString());
                }
            }
        });

        DefaultTableModel finalPCITableModel = PCITableModel;
        PCIRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.print(Arrays.deepToString(pci.getPCIInfo()));
                refreshTable(finalPCITableModel, pci.getPCIInfo()); // Pass the table model to the refresh method
            }
        });

        PCIPanelWrapper.add(PCIPanel);
        tabbedPane.addTab("PCI Info", null, PCIPanelWrapper,"PCI Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);
        refreshTable(PCITableModel, pci.getPCIInfo()); // Fetch Initial Data for PCI
        

        tabbedPane.addTab("PCI Info", null, PCIPanelWrapper,"PCI Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tabbedPane);
        frame.setPreferredSize(new Dimension(1100, 700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private static void refreshTable(DefaultTableModel table, String[][] newData ) {
        // Get new data (2D array from another function)

        // Replace with your function to get new data

        // Clear existing data
        table.setRowCount(0); // Clear existing rows

        // Add new data to the table model
        for (String[] row : newData) {
            table.addRow(row); // Add each row to the table model
        }
    }



    public static void main(final String[] args) {

        SwingUtilities.invokeLater(gui2_test::createAndShowGUI);
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        usb = new patricktest();
        pci = new PciTJ();
        //cpu = new SamMclCPU();

    }
}