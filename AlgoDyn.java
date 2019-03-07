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
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AlgoDyn {

	
	public static void main (String args[]) throws IOException {
		
		
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

		//int s = 100;
		//int[] v = {1,2,5};
		//int k = 3;
		//long temps = 0;
		
		int[][] m = AlgoDyn(v, s, k);
		afficher(m, s+1, k+1);
		System.out.println("le nombre de bocaux minimal: " + m[s][k]);
		
		//System.out.println("le temps pour executer: "+(temps)+"ns"); 
		
		
		
		//la partie pour tracer le graphe
		
		/*
		//varier s
		int k = 5;
		int[] v = {1,2,4,8,16};
		
		XYSeries series = new XYSeries("xySeries");
		for(int s=0; s<20000; s++) {
			long startTime=System.nanoTime();   //start 
			
			int[][] m = AlgoDyn(v, s, k);
			
			long endTime=System.nanoTime(); //finish
			
			long temps = (endTime-startTime)/(long)(1000);
			
			int x = s;
			long y = temps;
			series.add(x, y);
		}
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Dynamique(d=2 varier s)", // chart title
				"total(dg)", // x axis label
				"temps(us)", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
 
		ChartFrame frame = new ChartFrame("Dynamique", chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ValueAxis valueaxis = xyplot.getRangeAxis(); 
		
		//valueaxis .setUpperBound(10);
		*/
		
		/*
		//varier k;
		XYSeries series2 = new XYSeries("xySeries");
		
		int s2=10000;
		int d = 2;
		for(int k2=1; k2<10; k2++) {
			int[] v2 = new int[k2];
			for(int i=0; i<k2; i++) {
				v2[i] = (int) Math.pow(d,i);
			}
			long startTime=System.nanoTime();   //start 
			
			int[][] m = AlgoDyn(v2, s2, k2);
			
			long endTime=System.nanoTime(); //finish
			
			long temps = (endTime-startTime)/(long)(1000);
			
			int x = s2;
			long y = temps;
			series2.add(x, y);
		}
		
		XYSeriesCollection dataset2 = new XYSeriesCollection();
		dataset2.addSeries(series2);
		JFreeChart chart2 = ChartFactory.createXYLineChart(
				"Dynamique(d=2,s=1000,varier k)", // chart title
				"k(nombre de bocaux)", // x axis label
				"temps(us)", // y axis label
				dataset2, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
 
		ChartFrame frame2 = new ChartFrame("Dynamique", chart2);
		frame2.pack();
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		*/
	}
	
	
	public static int[][] AlgoDyn(int[] v, int s, int k) {
       
		int cptV = 0;
        int[][] m = new int[s+1][k+1];
        
        //inisialiser
        for (int i = 1; i < s+1; i++) {
			m[i][0] = Integer.MAX_VALUE;// infini positif
		}
        
        //afficher(m,s+1,k+1);
        //System.out.println();
        
        for (int i = 1; i < s+1; i++) {
			for (int j = 1; j < k+1; j++) {
				
				cptV = j - 1;
				
				if(i >= v[cptV]){
					m[i][j] = min(m[i][j-1], m[i - v[cptV]][j] + 1);//on peut utiliser la plus grand bocal
				}
				else {
					m[i][j] = min(m[i][j-1], Integer.MAX_VALUE);//on ne peut pas utiliser la plus grand bocal
				}
				
				//afficher(m,s+1,k+1);
				//System.out.println();
			}
		}
        return m;
    }
	
	public static int min(int x, int y) {
		if(x >= y) {
			return y;
		}
		else {
			return x;
		}
	}
	
	public static void afficher(int[][] m, int s, int k) {
		for(int i=0; i<s; i++) {
			for(int j=0; j<k; j++) {
				System.out.print(m[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
}

/*
 * 
 * resultat: 
 * 
	total	//on utilise rien   //on juste utilise bocal 1  //on utilise bocaux 1 et 2  //on utilise bocaux 1,2,5
	0		0					0							0							0	
	1		1000				1							1							1	
	2		1000				2							1							1	
	3		1000				3							2							2	
	4		1000				4							2							2	
	5		1000				5							3							1	
	6		1000				6							3							2	
	7		1000				7							4							2	
	8		1000				8							4							3	
	9		1000				9							5							3	
	10		1000				10							5							2	
	11		1000				11							6							3
 * 
 * 
 */
