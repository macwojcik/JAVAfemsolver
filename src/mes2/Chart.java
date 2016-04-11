/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes2;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Maciek
 */
public class Chart {
    XYSeries dataSet= new XYSeries("Na powierzchni wsadu");
    XYSeries dataSet2= new XYSeries("W osi wsadu");
  
    
    
      public Chart(){
              
      }
      public void  inputDataSet1(double d1,double d2 ){
             dataSet.add(d1,d2); 
        
        
      }
       public void  inputDataSet2(double d1,double d2 ){
             dataSet2.add(d1,d2); 
        
        
      }
      public void createwykres(){
      XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSet); 
        // Dodanie kolejnych serii do kolekcji:
        xySeriesCollection.addSeries(dataSet2);
               
        // tworzenie XYDataSet 
        XYDataset xyDataset = xySeriesCollection;      
        // tworzenie wykresu 
        JFreeChart lineGraph = ChartFactory.createXYLineChart 
                    ("Wykres nagrzewania wsadu",  // Title 
                      "Czas",           // X-Axis label 
                      "Temperatura",           // Y-Axis label 
                      xyDataset,          // Dataset 
                      PlotOrientation.VERTICAL,        //Plot orientation 
                      true,                //show legend 
                      true,                // Show tooltips 
                      false               //url show 
                     ); 
      ChartFrame frame1 = new ChartFrame("Szybkie wyswietlanie wykresu - klasa ChartFrame", lineGraph); 
        frame1.pack(); 
        frame1.setVisible(true); 
        frame1.setLocationRelativeTo(null);
        //frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
        ChartPanel chartPanel = new ChartPanel(lineGraph);
        //frame.getContentPane().add(chartPanel);
       // frame.getContentPane().add(new JLabel("<<< wykres dodany jako ChartPanel"));
      
      
      
      }
 
}
