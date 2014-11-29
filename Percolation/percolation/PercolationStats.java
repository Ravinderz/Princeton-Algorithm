///////////////////////////////
//Yichi Zhang, Monte Carlo Simulation of percolation system
///////////////////////////////
import java.lang.*;
public class PercolationStats {
  /*
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   public double mean()                     // sample mean of percolation threshold
   public double stddev()                   // sample standard deviation of percolation threshold
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   public static void main(String[] args)   // test client, described below
*/
   private double threshmean=0;     //mean thresh value
   private double[] thresh;			//thresh value array used to compute the mean
   private double standdev=0;		// standard deviation
   private double confidenceLo=0;   //confidence low
   private double confidenceHi=0;	// confidence high
   private int count=0;				// open site count
   private Percolation test;        // percolation system instance

   public PercolationStats(int N, int T){ //main method used to simulate the process
	   thresh=new double[T];
   		for(int m=0;m<T;m++){
   			count=0;
   			test = new Percolation(N);
   			while(!test.percolates()){
   				int i=(int)(Math.random()*N)+1;
   				int j=(int)(Math.random()*N)+1;//generate random site indices
   				if(!test.isOpen(i,j)){
   					test.open(i,j); //open a new site
   					count++;		//count opened site numbers
   				}
   				//System.out.println(count);
   			}
   			thresh[m]=(double)count/(N*N); //compute thresh value array
   		}
   		mean();
   		stddev();
   		System.out.println("mean        ="+threshmean);
   		System.out.println("95% confidence interval   =  "+confidenceLo()+"     "+confidenceHi());
   }
   public double mean(){ //compute the mean thresh value
   		double total=0;
   		for(int m=0; m<thresh.length;m++){
   			 total+=thresh[m];
   		}
   		threshmean=(double)total/thresh.length;

   		return threshmean;
   }

   public double stddev(){ // compute standard deviation
   	    threshmean=mean();
   	    for(int m=0; m<thresh.length;m++){
   	    	standdev+=Math.pow((thresh[m]-threshmean),2)/(thresh.length-1);
   	    }
   	    standdev=Math.sqrt(standdev);
   	    System.out.println("stddev      ="+standdev);
   	    return standdev;
   }

   public double confidenceLo(){//compute confidence range
   		return confidenceLo=threshmean-1.96*standdev/Math.sqrt(thresh.length);
   }
   public double confidenceHi(){//compute confidence range
   		return confidenceHi=threshmean+1.96*standdev/Math.sqrt(thresh.length);
   }
   public static void main(String[] args) {// main function
   		new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
   		
   }
}