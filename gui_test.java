// Java Program to demonstrate
// JTabbedPane with Labels
import javax.swing.*;
import java.awt.*;

// Driver Class
public class gui_test {
    // main function
    public static void main(String[] args) {
        // Run the Swing application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create a new JFrame (window)
                JFrame window = new JFrame("JTabbedPane Example");
                // Close operation when the window is closed
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation when the window is closed
                // Set the initial size of the window
                window.setSize(400, 300);

                // Create a JTabbedPane, which will hold the tabs
                JTabbedPane tabPanel = new JTabbedPane();

                // Create the first tab (page1) and add a JLabel to it
                JPanel page1 = new JPanel();
                page1.add(new JLabel("This is Tab 1"));

                // Create the second tab (page2) and add a JLabel to it
                JPanel page2 = new JPanel();
                page2.add(new JLabel("This is Tab 2"));

                // Create the third tab (page3) and add a JLabel to it
                JPanel page3 = new JPanel();
                page3.add(new JLabel("This is Tab 3"));

                // Add the three tabs to the JTabbedPane
                tabPanel.addTab("Tab 1", page1);
                tabPanel.addTab("Tab 2", page2);
                tabPanel.addTab("Tab 3", page3);

                // Add the JTabbedPane to the JFrame's content
                window.add(tabPanel);

                // Make the JFrame visible
                window.setVisible(true);
            }
        });
    }
}
