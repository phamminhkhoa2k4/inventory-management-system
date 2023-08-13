/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaproject;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.JFrame;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author TVD
 */
/**
 *
 * @author 84338
 */
public class StaticsChart extends javax.swing.JFrame {

    /**
     * Creates new form StaticsChart
     */
    public void JFreeChartTest() {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(1500, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static CategoryDataset createDataset() {
        Statics sourceFrame = new Statics();
        Object[][] tableData = sourceFrame.getTableData();
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] row : tableData) {
            System.out.println(row[1]);
            String column1 = String.valueOf(row[0]); // Lấy giá trị từ cột thứ nhất
            int column2 = Integer.parseInt(String.valueOf(row[1])); // Lấy giá trị từ cột thứ hai (giả sử là kiểu số nguyên)
            dataset.addValue(column2, "Số người", column1);
        }
        return dataset;
    }

    public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
        "INVENTORY STATICS",
        "PRODUCT", "QUANTITY",
        createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaticsChart();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
