
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

        // Create a panel for the chart

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(200, 200));

        c.weightx = 0.6;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        panel1.add(chartPanel, c);


        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        JLabel cpu_right_info_text = new JLabel("<html>CPU" + cpu.getModel() + "<br><table cellpadding='5'>"+
                "<tr><td><font size=-2> Sockets: </font></td><td>" +cpu.socketCount() + " </td></tr>"+
                "<tr><td><font size=-2> Cores: </font></td><td>" + cpu.coresPerSocket() * cpu.socketCount() + " </td></tr></table></html>");
        cpu_right_info_text.setHorizontalAlignment(JLabel.CENTER);
        c.weightx = 0.4;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        panel1.add(cpu_right_info_text, c);

        JLabel cpu_right_info_text_2 = new JLabel("l1d="+cpu.l1dCacheSize()+
                ", l1i="+cpu.l1iCacheSize()+
                ", l2="+cpu.l2CacheSize()+
                ", l3="+cpu.l3CacheSize());

        cpu_right_info_text_2.setHorizontalAlignment(JLabel.CENTER);
        c.weightx = 0.4;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        panel1.add(cpu_right_info_text_2, c);

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
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate real-time data (you can replace this with actual data fetching)
                double newValue = Math.sin(time * 0.1);
                series.add(time, newValue);
                if (series.getItemCount() > 200) {
                    series.remove(0); // Remove the first (oldest) entry
                }
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
        JLabel filler4 = new JLabel("PCI Info");
        filler4.setHorizontalAlignment(JLabel.CENTER);
        panel4.setLayout(new GridLayout(1, 1));
        panel4.add(filler4);
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
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        //showCPU();
    }
}