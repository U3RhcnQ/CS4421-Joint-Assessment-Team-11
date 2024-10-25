
import com.formdev.flatlaf.FlatLightLaf;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui2_test {

    private static void createAndShowGUI() {
        FlatLightLaf.setup();  //Must be called first of all Swing code as this sets the look and feel to FlatDark.

        // Title
        final JFrame frame = new JFrame("TaskSys");

        // Attempt to load the image and handle exception if not available
        try {
            // Load the logo image
            Image icon = ImageIO.read(new File("images/icon.png"));
            // Set the icon for the JFrame
            frame.setIconImage(icon);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create The tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add the First Panel
        JPanel panel1 = new JPanel(false);
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


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

        c.weightx = 0.7;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        panel1.add(chartPanel, c);


        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        JLabel cpu_right_info_text = new JLabel("<html>" +
                "<font size=-1>" + cpu.getModel() + "</font><br><br>" +
                "<table cellpadding='5' align='center'>"+
                "<tr><td><font size=-1> Sockets: </font></td><td align='center'>" +cpu.socketCount() + " </td></tr>"+
                "<tr><td><font size=-1> Cores: </font></td><td align='center'>" + cpu.coresPerSocket() * cpu.socketCount() + " </td></tr>" +
                "<tr><td><font size=-1> L1 Data Cache: </font></td><td align='center'>" + cpu.l1iCacheSize() + " </td></tr>" +
                "<tr><td><font size=-1> L1 Intr Cache: </font></td><td align='center'>" + cpu.l1dCacheSize() + " </td></tr>" +
                "<tr><td><font size=-1> L1 Cache: </font></td><td align='center'>" + (cpu.l1iCacheSize() + cpu.l1dCacheSize()) + " </td></tr>" +
                "<tr><td><font size=-1> L2 Cache: </font></td><td align='center'>" + cpu.l2CacheSize() + " </td></tr>" +
                "<tr><td><font size=-1> L3 Cache: </font></td><td align='center'>" + cpu.l3CacheSize() + " </td></tr>" +
                "" +
                "</table></html>");
        cpu_right_info_text.setHorizontalAlignment(JLabel.CENTER);
        c.weightx = 0.1;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 0;
        panel1.add(cpu_right_info_text, c);

        JLabel cpu_bottom_info_panel = new JLabel("CPU Info");
        cpu_bottom_info_panel.setHorizontalAlignment(JLabel.CENTER);
        c.weightx = 1.0;
        c.weighty = 0.4;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        panel1.add(cpu_bottom_info_panel, c);


        // Timer to update the chart every second
        Timer timer = new Timer(10, new ActionListener() {
            int time = 0;
            int counter;
            int sum;
            int cores;
            @Override
            public void actionPerformed(ActionEvent e) {

                cores = cpu.coresPerSocket();

                sum = 0;
                for (counter = 0; counter <= cores; counter++){
                    sum += (100 - cpu.getIdleTime(counter));
                }

                double avaerage = (double) sum / cores;
                //double avaerage =  sum;
                //double avaerage = 100 - cpu.getIdleTime(1);
                series.add(time, avaerage);
                if (series.getItemCount() > 60) {
                    series.remove(0); // Remove the first (oldest) entry
                }
                cpu.read(1);
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

        JPanel panel3 = new JPanel(false);
        JLabel filler3 = new JLabel("USB Info");
        filler3.setHorizontalAlignment(JLabel.CENTER);
        panel3.setLayout(new GridLayout(1, 1));
        panel3.add(filler3);
        tabbedPane.addTab("USB Info", null, panel3,"USB Info");
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

        SwingUtilities.invokeLater(gui2_test::createAndShowGUI);
        //System.loadLibrary("sysinfo");
        //sysInfo info = new sysInfo();
        //cpuInfo cpu = new cpuInfo();
        //cpu.read(0);

        //showCPU();
    }
}