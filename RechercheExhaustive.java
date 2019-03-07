
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


public class RechercheExhaustive {
	public static void main (String args[]) throws IOException {
		
		/*
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
		
		
		//int s = 45;
		//int[] v= {1,2,5};
		//int k = 3;
		
		//int cpt = 0;
		
		
		int result = 0;
		//long temps = 0;
		
		
		result = RechercheExhaustive(k,v,s);
		
		System.out.println("le nombre de bocaux minimal: " + result);
		
		//System.out.println("le temps pour executer: "+(temps)+"ns"); 
		
		*/
		
		
		//la partie pour tracer le graphe
		  
		int k = 5;
		int[] v = {1,2,4,8,16};
		
		XYSeries series = new XYSeries("xySeries");
		for (int s=0; s<40; s++) {
			
			long startTime=System.nanoTime();   //start 
			
			int result = RechercheExhaustive(k,v,s);
			
			long endTime=System.nanoTime(); //finish
			
			long temps = endTime-startTime;
			
			int x = s;
			long y = temps;
			series.add(x, y);
		}
		
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"RechercheExhaustive", // chart title
				"total(dg)", // x axis label
				"temps(ns)", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
 
		ChartFrame frame = new ChartFrame("RechercheExhaustive", chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		
		
		
	}
	
	public static int RechercheExhaustive(int k, int[] v, int s) {
		
		int NbCont=0;
		int x=0;
		
		
		if(s < 0) {
			return 10000;
		}
		else {
			if(s == 0) {
				return 0;
			}
			else {
				NbCont = s;
				//System.out.println("NbCont:  " + NbCont);
				for(int i=0; i<k; i++) {
					//System.out.println("迭代前i: " + i);
					//System.out.println("s: " + s);
					x = RechercheExhaustive(k, v, s-v[i]);
					//System.out.println("x: " + x);
					//System.out.println("迭代后i: " + i);
					//System.out.println("NbCont:  " + NbCont);
					if(x+1 < NbCont) {
						//System.out.println("进入if： ");
						NbCont = x+1;
						//System.out.println("NbCont加1后:  " + NbCont);
					}
				}
				return NbCont;
			}
		}
	}
}
