// Flatlaf LAF

import com.formdev.flatlaf.FlatLightLaf;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUI {

    private static patricktest usb;
    private static PciTJ pci;
    private static memory memory;


    private static void createAndShowGUI() {

        FlatLightLaf.setup();  //Must be called first of all Swing code as this sets the look and feel to FlatLAF.
        final JFrame frame = new JFrame("TaskSys"); // Set Title
        DefaultTableModel USBTableModel; // Declare the table models
        DefaultTableModel PCITableModel;
        DefaultTableModel DISKTableModel;


        // Layout Code all styling is here
        GridBagConstraints CPUChart = new GridBagConstraints();
        CPUChart.weightx = 0.7; //Horizontal priority
        CPUChart.weighty = 0.6; // vertical priority
        CPUChart.fill = GridBagConstraints.BOTH; // Expand in both directions
        CPUChart.gridx = 0; // Row 0
        CPUChart.gridy = 1; // Column 1
        CPUChart.insets = new Insets(10, 10, 10, 10); // 10 Padding on each side

        GridBagConstraints CPURightInfo = new GridBagConstraints();
        CPURightInfo.weightx = 0.1;
        CPURightInfo.weighty = 0.6;
        CPURightInfo.fill = GridBagConstraints.VERTICAL;
        CPURightInfo.gridx = 1;
        CPURightInfo.gridy = 1;
        CPURightInfo.anchor = GridBagConstraints.NORTH;

        GridBagConstraints TitleConstraints = new GridBagConstraints();
        TitleConstraints.gridx = 0;
        TitleConstraints.gridy = 0;
        TitleConstraints.weightx = 1;
        TitleConstraints.weighty = 0;
        TitleConstraints.fill = GridBagConstraints.HORIZONTAL; // only expand Horizontally
        TitleConstraints.ipadx = 0;
        TitleConstraints.ipady = 30; // 30 padding vertically on both top and bottom
        TitleConstraints.anchor = GridBagConstraints.WEST;  // Align to left
        TitleConstraints.gridwidth = 2;

        GridBagConstraints TableConstraints = new GridBagConstraints();
        TableConstraints.gridx = 0;
        TableConstraints.gridy = 1;
        TableConstraints.weightx = 1;
        TableConstraints.weighty = 1;
        TableConstraints.fill = GridBagConstraints.BOTH;
        TableConstraints.anchor = GridBagConstraints.WEST;  // Align to left

        GridBagConstraints DISKTableConstraints = new GridBagConstraints();
        DISKTableConstraints.gridx = 1;
        DISKTableConstraints.gridy = 1;
        DISKTableConstraints.weightx = 0.3;
        DISKTableConstraints.weighty = 1;
        DISKTableConstraints.fill = GridBagConstraints.BOTH;
        DISKTableConstraints.anchor = GridBagConstraints.NORTH;  // Align to top

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

        GridBagConstraints MEMChartPanelConstraints = new GridBagConstraints();
        MEMChartPanelConstraints.weightx = 0.7;
        MEMChartPanelConstraints.weighty = 0.6;
        MEMChartPanelConstraints.fill = GridBagConstraints.BOTH;
        MEMChartPanelConstraints.gridx = 0;
        MEMChartPanelConstraints.gridy = 1;
        MEMChartPanelConstraints.insets = new Insets(10, 10, 10, 10);


        // Attempt to load the image and handle exception if not available
        // Initialise to null in case it all breaks
        ImageIcon cpuIcon = null;
        ImageIcon usbIcon = null;
        ImageIcon pciIcon = null;
        ImageIcon diskIcon = null;
        ImageIcon memIcon = null;

        try {
            // Load the logo image
            Image icon = ImageIO.read(new File("images/icon.png"));
            // Set the icon for the JFrame
            frame.setIconImage(icon);

            //The SCALE_SMOOTH option gives a better quality result for the scaling.

            // Load and scale the CPU icon
            cpuIcon = new ImageIcon("images/cpuicon.png");
            Image cpuImage = cpuIcon.getImage();
            Image scaledCpuImage = cpuImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH); // Have to Resize image or it's too big otherwise
            cpuIcon = new ImageIcon(scaledCpuImage);

            // Load and scale the USB icon
            usbIcon = new ImageIcon("images/usbicon.png");
            Image usbImage = usbIcon.getImage();
            Image scaledUsbImage = usbImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
            usbIcon = new ImageIcon(scaledUsbImage);

            // Load and scale the PCI icon
            pciIcon = new ImageIcon("images/pciicon.png");
            Image pciImage = pciIcon.getImage();
            Image scaledPciImage = pciImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
            pciIcon = new ImageIcon(scaledPciImage);

            // Load and scale the Disk icon
            diskIcon = new ImageIcon("images/diskicon.png");
            Image diskImage = diskIcon.getImage();
            Image scaledDiskImage = diskImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
            diskIcon = new ImageIcon(scaledDiskImage);

            // Load and scale the Memory icon
            memIcon = new ImageIcon("images/memoryicon.png");
            Image memImage = memIcon.getImage();
            Image scaledMemImage = memImage.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
            memIcon = new ImageIcon(scaledMemImage);


        } catch (IOException e) {

            // Only here to stop the code from crashing if images are missing
            e.printStackTrace();
        }

        JTabbedPane tabbedPane = new JTabbedPane(); // Create The main tabbed Pane

        // We create a wrapper for each panel and set add an empty border of 10 all around
        JPanel CPUPanelWrapper = new JPanel(new BorderLayout());
        CPUPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // This creates the main Panel for CPU using the Gridbaglayout as it seems to work the best
        JPanel CPUPanel = new JPanel(new GridBagLayout());

        // Create the CPU title (We can use HTML for Styling which is cool)
        JLabel CPUTitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>CPU Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh </span></html>");

        // We have to add everything to the panel and add constraints to dictate the styling
        CPUPanel.add(CPUTitle, TitleConstraints);

        // Chart Code

        // Create a series to hold the data
        XYSeries CPU1 = new XYSeries("Utilisation");
        XYSeries CPU2 = new XYSeries("IdleTimes");
        XYSeries CPU3 = new XYSeries("UserTimes");
        XYSeries CPU4 = new XYSeries("SystemTimes");

        // Create a dataset and add the series to it
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(CPU1);
        dataset.addSeries(CPU2);
        dataset.addSeries(CPU3);
        dataset.addSeries(CPU4);

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
        plot.getRangeAxis().setRange(0, 100); // Set y-axis range from 0 to 100

        // Create a panel for the chart
        ChartPanel chartPanel = new ChartPanel(chart);

        // Set preferred dimensions
        chartPanel.setPreferredSize(new Dimension(200, 200));
        // Add panel with constraints
        CPUPanel.add(chartPanel, CPUChart);

        // Get all the CPU info and format it with html calls Sam's CPU class
        JLabel cpu_right_info_text = new JLabel("<html>" +
                "<font size=>" + SamMclCPU.cpuName() + "</font><br><br>" +
                "<table cellpadding='5' align='center'>" +
                "<tr><td><font size=-1> Sockets: </font></td><td align='center'>" + SamMclCPU.socketCount() + " </td></tr>" +
                "<tr><td><font size=-1> Cores: </font></td><td align='center'>" + SamMclCPU.coreCount() + " </td></tr>" +
                "<tr><td><font size=-1> Logical Processors: </font></td><td align='center'>" + SamMclCPU.logicalCoreCount() + " </td></tr>" +
                "<tr><td><font size=-1> Base Speed: </font></td><td align='center'>" + SamMclCPU.baseSpeed() + " <font size=-2> GHz </font></td></tr>" +
                "<tr><td><font size=-1> L1 Data Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel1d()) + " <font size=-2> KB </font></td></tr>" +
                "<tr><td><font size=-1> L1 Intr Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel1i()) + " <font size=-2> KB </font></td></tr>" +
                "<tr><td><font size=-1> L1 Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel1()) + " <font size=-2> KB </font></td></tr>" +
                "<tr><td><font size=-1> L2 Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel2()) + " <font size=-2> MB </font></td></tr>" +
                "<tr><td><font size=-1> L3 Cache: </font></td><td align='center'>" + String.format("%.1f", SamMclCPU.cacheSizel3()) + " <font size=-2> MB </font></td></tr>" +
                "</table></html>");

        CPUPanel.add(cpu_right_info_text, CPURightInfo);

        // Wrap up of the cpu panel here we add the man Panel to the wrapper and add the wrapper to the tabbed plane
        CPUPanelWrapper.add(CPUPanel);
        // Set Name, Image, Panel and Tooltip here
        tabbedPane.addTab("CPU Info", cpuIcon, CPUPanelWrapper, "CPU Info");
        // Set Keyboard shortcut toAlt+n and set appropriate tab index
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        // Similar to CPU above
        JPanel MEMPanelWrapper = new JPanel(new BorderLayout());
        MEMPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel MEMPanel = new JPanel(new GridBagLayout());
        JLabel MEMTitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>Memory Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh </span></html>");
        MEMPanel.add(MEMTitle, TitleConstraints);

        // Chart Code
        // Create a series to hold the data
        XYSeries MEMSeries = new XYSeries("");
        // Create a dataset and add the series to it
        XYSeriesCollection MEMDataset = new XYSeriesCollection();
        MEMDataset.addSeries(MEMSeries);

        // Create the chart using JFreeChart
        JFreeChart MEMChart = ChartFactory.createXYLineChart(
                "", // Chart title
                "",           // X-Axis Label
                "",          // Y-Axis Label
                MEMDataset,          // Dataset for the chart
                PlotOrientation.VERTICAL,
                false, true, false
        );

        // Get the plot from the chart and set the range for the y-axis
        XYPlot MEMPlot = MEMChart.getXYPlot();
        MEMPlot.getRangeAxis().setRange(0, 100); // Set y-axis range from 0 to 400

        // Create a panel for the chart
        ChartPanel MEMChartPanel = new ChartPanel(MEMChart);
        MEMChartPanel.setPreferredSize(new Dimension(200, 200));
        MEMPanel.add(MEMChartPanel, CPUChart);

        JLabel MEM_right_info_text = new JLabel("<html>" +
                "<font size=+2> Memory Info </font><br><br>" +
                "<table cellpadding='5' align='center'>" +
                "<tr><td><font size=> Total Memory: </font></td><td align='center'>" + String.format("%.2f", memory.getTotalMemory()) + "<font size=-2> GB </font> </td></tr>" +
                "<tr><td><font size=> Used Memory: </font></td><td align='center'>" + String.format("%.2f", memory.getUsedMemory()) + "<font size=-2> GB </font> </td></tr>" +
                "<tr><td><font size=> Free Memory: </font></td><td align='center'>" + String.format("%.2f", memory.getFreeMemory()) + "<font size=-2> GB </font> </td></tr>" +
                "<tr><td><font size=> Percentage Used: </font></td><td align='center'>" + String.format("%.2f", memory.getMemoryAsAPercentage()) + "<font size=-2> % </font> </td></tr>" +
                "</table></html>");

        MEMPanel.add(MEM_right_info_text, CPURightInfo);

        MEMPanelWrapper.add(MEMPanel);
        tabbedPane.addTab("Memory Info", memIcon, MEMPanelWrapper, "Memory Info");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        // Disk Info here
        // Create Wrapper
        JPanel DISKPanelWrapper = new JPanel(new BorderLayout());
        // Add empty border
        DISKPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        // Create Main Tab Panel
        JPanel DISKPanel = new JPanel(new GridBagLayout());
        JLabel DISKTitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>DISK Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh every 10 seconds </span></html>");
        DISKPanel.add(DISKTitle, TitleConstraints);

        // Get Disk Info to populate table and pie charts
        // Very Similar SamDiskInfo.diskTable() calls but this one does no formatting and the other one does
        String[][] DiskInfo = SamDiskInfo.diskTable();

        // Create new chart panel to hold all the charts Dynamically create rows, with 2 columns
        JPanel DISKChartPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        for (String[] strings : DiskInfo) {  // Loop through each disk

            // Create a dataset for the current disk
            DefaultPieDataset<String> diskDataset = new DefaultPieDataset<>();

            // Add data to the dataset for the current disk
            String diskName = strings[0]; // Disk name
            double usedSpace = Double.parseDouble(strings[2]); // Used space
            double totalSpace = Double.parseDouble(strings[1]); // Total space
            double freeSpace = totalSpace - usedSpace; // Calculate free space

            // Populate the dataset with used and free space
            diskDataset.setValue("Used " + String.format("%.2f", usedSpace) + " GB", usedSpace);
            diskDataset.setValue("Free " + String.format("%.2f", freeSpace) + " GB", freeSpace);

            // Create the pie chart for the current disk
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Disk Usage - " + diskName, // Chart title
                    diskDataset,               // Dataset for the chart
                    true,                       // Include legend
                    true,
                    false
            );

            // Wrap the pie chart in a ChartPanel
            ChartPanel chartPanel2 = new ChartPanel(pieChart);
            chartPanel2.setPreferredSize(new Dimension(200, 200));

            // Add each pie chart to the chart Panel
            DISKChartPanel.add(chartPanel2);
        }

        // Place DISKChartPanel into a JScrollPane for scrolling
        //  is only set to Vertical and horizontal is suppressed as otherwise it was overflowing
        JScrollPane DISKScrollPane = new JScrollPane(DISKChartPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // Add the scrollPane to the main panel
        DISKPanel.add(DISKScrollPane, MEMChartPanelConstraints);

        // Table Code
        String[] DISKColumnNames = {"Name", "Capacity", "Used", "% Used"}; // Column Names

        // Initializing the JTable
        DISKTableModel = new DefaultTableModel(DISKColumnNames, 0); // Initialize the table model
        JTable DISKTable = new JTable(DISKTableModel);
        DISKTable.setBounds(30, 40, 100, 300);

        // adding it to JScrollPane
        JScrollPane DISKScrollPane2 = new JScrollPane(DISKTable);
        DISKPanel.add(DISKScrollPane2, DISKTableConstraints);

        DISKPanelWrapper.add(DISKPanel);
        tabbedPane.addTab("Disk Info", diskIcon, DISKPanelWrapper, "Disk Info");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        refreshTable(DISKTableModel, SamDiskInfo.diskTable2()); // Fetch Initial Data for Disk


        JPanel USBPanelWrapper = new JPanel(new BorderLayout());
        USBPanelWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JPanel USBPanel = new JPanel(new GridBagLayout());
        JLabel USBTitle = new JLabel("<html><span style='font-size:14px; font-weight:bold;'>USB Info</span> " +
                "<span style='font-size:11px;'> - Data will automatically refresh every 10 seconds or manually</span></html>");
        USBPanel.add(USBTitle, TitleConstraints);

        JButton USBRefresh = new JButton("Refresh");
        USBPanel.add(USBRefresh, RefreshButtonConstraints);

        // Column Names
        String[] USBColumnNames = {"Bus", "Device", "Vendor ID", "Vendor", "Device ID", "Device"};

        // Initializing the JTable
        USBTableModel = new DefaultTableModel(USBColumnNames, 0); // Initialize the table model
        JTable USBTable = new JTable(USBTableModel);
        USBTable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane USBScrollPane = new JScrollPane(USBTable);
        USBPanel.add(USBScrollPane, TableConstraints);

        // Create a Text area with line wrapping and non-editable
        JTextArea USBText = new JTextArea("Please Select a Device to view Details");
        USBText.setLineWrap(true);               // Enable line wrap
        USBText.setWrapStyleWord(true);          // Wrap at word boundaries
        USBText.setEditable(false); // Set non-editable

        // Set preferred sze and add to the Panel
        USBText.setPreferredSize(new Dimension(120, USBText.getPreferredSize().height));
        USBPanel.add(USBText, TextAreaConstraints);

        // Selection Listener for displaying selected row in USBText
        USBTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            // override default so we can add our own code
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the row is done being selected and check if the row is actually selected overall - Good practice as not doing it can lead to errors
                if (!e.getValueIsAdjusting() && USBTable.getSelectedRow() != -1) {
                    // Get selected row index
                    int selectedRow = USBTable.getSelectedRow();

                    // Display row details in the JTextArea
                    StringBuilder details = new StringBuilder();
                    for (int i = 0; i < USBTable.getColumnCount(); i++) {
                        // Get selected data in the line
                        String columnName = USBTable.getColumnName(i);
                        Object value = USBTable.getValueAt(selectedRow, i);
                        // Prepare for printing and add style
                        details.append(columnName).append(": ").append(value).append("\n");
                    }
                    USBText.setText(details.toString());
                }
            }
        });

        // Refresh Button logic ads a listener and when clicked executes the refresh table method
        DefaultTableModel finalUSBTableModel = USBTableModel;
        USBRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable(finalUSBTableModel, usb.getUSBInfoAs2DArray()); // Pass the table model to the refresh method
            }
        });

        // Add USB to the tabbedPane
        USBPanelWrapper.add(USBPanel);
        tabbedPane.addTab("USB Info", usbIcon, USBPanelWrapper, "USB Info");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);
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
        String[] PCIColumnNames = {"Bus", "Device", "Function", "Vendor ID", "Vendor", "Device ID", "Device"};

        // Initializing the JTable
        PCITableModel = new DefaultTableModel(PCIColumnNames, 0); // Initialize the table model
        JTable PCITable = new JTable(PCITableModel);
        PCITable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane PCIScrollPane = new JScrollPane(PCITable);
        PCIPanel.add(PCIScrollPane, TableConstraints);

        // Create a Text area with line wrapping and non-editable
        JTextArea PCIText = new JTextArea("Please Select a Device to view Details");
        PCIText.setLineWrap(true);               // Enable line wrap
        PCIText.setWrapStyleWord(true);          // Wrap at word boundaries
        PCIText.setEditable(false); // Set non-editable
        PCIText.setPreferredSize(new Dimension(120, PCIText.getPreferredSize().height));
        // Add it to the main panel
        PCIPanel.add(PCIText, TextAreaConstraints);

        // Selection Listener for displaying selected row in PCIText
        PCITable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the row is done being selected and check if the row is actually selected overall - Good practice as not doing it can lead to errors
                if (!e.getValueIsAdjusting() && PCITable.getSelectedRow() != -1) {
                    // Get selected row index
                    int selectedRow = PCITable.getSelectedRow();

                    // Display row details in the JTextArea
                    StringBuilder details = new StringBuilder();
                    for (int i = 0; i < PCITable.getColumnCount(); i++) {
                        // Get selected data in the line
                        String columnName = PCITable.getColumnName(i);
                        Object value = PCITable.getValueAt(selectedRow, i);
                        // Prepare for printing and add style
                        details.append(columnName).append(": ").append(value).append("\n");
                    }
                    // Convert output to string
                    PCIText.setText(details.toString());
                }
            }
        });

        // PCI Refresh Code very similar to USB
        DefaultTableModel finalPCITableModel = PCITableModel;
        PCIRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable(finalPCITableModel, pci.getPCIInfo()); // Pass the table model to the refresh method
            }
        });

        // Add PCI stuff to the Tabbed Pane
        PCIPanelWrapper.add(PCIPanel);
        tabbedPane.addTab("PCI Info", pciIcon, PCIPanelWrapper, "PCI Info");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        refreshTable(PCITableModel, pci.getPCIInfo()); // Fetch Initial Data for PCI


        // Final draw code populates the frame
        // Ad the Tabbed Pane here
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        // Set Action on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set Preferred Size
        frame.setPreferredSize(new Dimension(1100, 700));
        // Draw the stuff
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Timer to update the chart every second
        Timer timer = new Timer(10, new ActionListener() {
            // Set Up local variables
            int time = 0;

            // override the default behavior of action so we can run our code
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> { // Apparently this is good practice for UI Updates

                    // Add New Data
                    CPU1.add(time, SamMclCPU.utilisationTime());
                    CPU2.add(time, SamMclCPU.averageIdleTimes());
                    CPU3.add(time, SamMclCPU.averageUserTimes());
                    CPU4.add(time, SamMclCPU.averageSystemTimes());
                    MEMSeries.add(time, memory.getMemoryAsAPercentage());

                    // Update label for memory with new text uses same HTML formatting
                    MEM_right_info_text.setText("<html>" +
                            "<font size=+2> Memory Info </font><br><br>" +
                            "<table cellpadding='5' align='center'>" +
                            "<tr><td><font size=> Total Memory: </font></td><td align='center'>" + String.format("%.2f", memory.getTotalMemory()) + "<font size=-2> GB </font> </td></tr>" +
                            "<tr><td><font size=> Used Memory: </font></td><td align='center'>" + String.format("%.2f", memory.getUsedMemory()) + "<font size=-2> GB </font> </td></tr>" +
                            "<tr><td><font size=> Free Memory: </font></td><td align='center'>" + String.format("%.2f", memory.getFreeMemory()) + "<font size=-2> GB </font> </td></tr>" +
                            "<tr><td><font size=> Percentage Used: </font></td><td align='center'>" + String.format("%.2f", memory.getMemoryAsAPercentage()) + "<font size=-2> % </font> </td></tr>" +
                            "</table></html>");

                    // Increment time for charts
                    time++;

                    // Check if the series has more than 60 entries, remove the oldest if true
                    // For CPU we can assume if one value has hit 60 the rest have as well so we can clear them

                    if (CPU1.getItemCount() > 60) {
                        CPU1.remove(0); // Remove the first (oldest) entry
                        CPU2.remove(0); // Remove the first (oldest) entry
                        CPU3.remove(0); // Remove the first (oldest) entry
                        CPU4.remove(0); // Remove the first (oldest) entry
                    }
                    // Check if the series has more than 60 entries, remove the oldest if true
                    // Same logic for memory
                    if (MEMSeries.getItemCount() > 60) {
                        MEMSeries.remove(0); // Remove the first (oldest) entry
                    }
                });
            }
        });

        // Start Timer
        timer.start();


        Timer timer2 = new Timer(1000, new ActionListener() {

            // override the default behavior of action so we can run our code
            @Override //Function that executes in the timer
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> { // Apparently this is good practice for UI Updates

                    // Update PCI and USB tables with the refreshTable method
                    refreshTable(PCITableModel, pci.getPCIInfo());
                    refreshTable(USBTableModel, usb.getUSBInfoAs2DArray());

                });
            }
        });

        // Start the timer
        timer2.start();

    }

    // Helper to make updating Tables Easier
    private static void refreshTable(DefaultTableModel table, String[][] newData) {
        SwingUtilities.invokeLater(() -> {
            // Get new data (2D array from another function)
            table.setRowCount(0); // Clear existing rows
            // Add new data to the table model
            for (String[] row : newData) {
                table.addRow(row); // Add each row to the table model
            }
        });
    }

    public static void main(final String[] args) {

        // Startup loading libraries and instancing classes
        // Don't need to instance the CPU class as it's a utility class and can be called directly
        SwingUtilities.invokeLater(GUI::createAndShowGUI);
        System.loadLibrary("sysinfo");
        usb = new patricktest();
        pci = new PciTJ();
        memory = new memory();

    }
}