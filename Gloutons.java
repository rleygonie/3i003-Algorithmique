import org.jfree.data.xy.XYSeries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Gloutons {
	    public static void main(String[] args) throws IOException {
	        
	    	File f = new File("src/donnee");
			
			BufferedReader in;
			in = new BufferedReader(new FileReader(f));
			String sS = in.readLine();
			String kS = in.readLine();
			String vS = in.readLine();
			
			int s = Integer.parseInt(sS);
			int k = Integer.parseInt(kS);
			
			String[] vTab = vS.split("\\s+");
			
			int[] v = new int[vTab.length];
			for(int i=0; i<vTab.length; i++){
				v[i] = Integer.parseInt(vTab[i]);
			}
	    	
	    	
	    	
	    	//int s = 17;
	        //int k = 3;
	        //int v[]={1,2,5};
	        int result = 0;
	        int[] nb = new int[k];
	        int[] result2 = new int[k];
	        //long temps = 0;
	        
	        
	        result = Glouton(s, v, k);
	        System.out.println("le nombre de bocaux minimal: " + result);
	        
	        //System.out.println("le temps pour executer: "+(temps)+"ns"); 
	        
	        System.out.println();
	        result2 = Glouton2(s, v, k, nb);
			
	        
	        System.out.println("les bocaux qu'on utilise: ");
	        for(int i=0; i<k; i++) {
	        	System.out.print(result2[i] + "\t");
	        }
	        
	        
	        
	        
	        /*la partie pour tracer le graphe
	        
	        int k = 5;
			int[] v = {1,2,4,8,16};
	         
	        XYSeries series = new XYSeries("xySeries");
			for(int s=0; s<10000000; s++) {
				long startTime=System.nanoTime();   //start 
				
				int result = Glouton(s, v, k);
				
				long endTime=System.nanoTime(); //finish
				
				long temps = endTime-startTime;
				
				int x = s;
				long y = temps;
				series.add(x, y);
			}
	        
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series);
			JFreeChart chart = ChartFactory.createXYLineChart(
					"Glouton", // chart title
					"total(dg)", // x axis label
					"temps(ns)", // y axis label
					dataset, // data
					PlotOrientation.VERTICAL,
					false, // include legend
					false, // tooltips
					false // urls
					);
	 
			ChartFrame frame = new ChartFrame("Glouton", chart);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        */
	        
	        
	        
	    }

	    //retourne nombre total
	    public static int Glouton(int s, int[] v, int k){
	    	int nbt = 0;
	    	int n = 0;
	    	
	    	for(int i=k-1; i>=0; i--) {
	    		n = s/v[i];
	    		s = s - n*v[i];
	    		nbt += n;
	    	}
	    	
	        return nbt;
	    }
	    
	    //retourne le tableau ce qu'on utilise
	    public static int[] Glouton2(int s, int[] v, int k, int nb[]){

	    	int n = 0;
	    	
	    	for(int i=k-1; i>=0; i--) {
	    		n = s/v[i];
	    		s = s - n*v[i];
	    		nb[i] = n;
	    	}
	    	
	        return nb;
	    }
	    
	}
