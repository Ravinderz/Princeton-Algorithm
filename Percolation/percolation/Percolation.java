import java.lang.*;

public class Percolation {
  /* 
   public Percolation(int N)                // create N-by-N grid, with all sites blocked
   public void open(int i, int j)           // open site (row i, column j) if it is not already
   public boolean isOpen(int i, int j)      // is site (row i, column j) open?
   public boolean isFull(int i, int j)      // is site (row i, column j) full?
   public boolean percolates()              // does the system percolate?
   public static void main(String[] args)   // test client, optional
   */
   private boolean[] open;  //site open boolean to tell whether a site is opened or not
   private boolean[] full;	//site full boolean to tell whether a site is full or not
   public WeightedQuickUnionUF PercolationSys;//QuickUnion instance
   public int dimension;	//dimension of percolate system
public Percolation(int N){//constructor construct a new WeightedQuickUnionUF instance
	int i, j;
	dimension=N;
	PercolationSys= new WeightedQuickUnionUF(N*N+2);//NxN sites plus two virtual sites at top and bottom respectively
	for(i=0;i<N;i++){
		PercolationSys.union(i,N*N);
		PercolationSys.union(N*(N-1)+i,N*N+1);
	}
	//System.out.println("connected?"+PercolationSys.connected(N*N,N*N+1));
	open=new boolean[N*N];
	full=new boolean[N*N];
	for(j=0;j<N*N;j++){
		open[j]=false;
		full[j]=false;
	}
}
///////////////////////////////
//Yichi Zhang, Monte Carlo Simulation of percolation system
///////////////////////////////
public void open(int i, int j){//open site method, took care of boundary cases
	int N=dimension;
	if(i>N||j>N) throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	if(open[(i-1)*N+j-1]==false) {
		open[(i-1)*N+j-1]=true;
		if(i>=2){
			if(open[(i-2)*N+j-1]) PercolationSys.union((i-1)*N+j-1,(i-2)*N+j-1);
		}
		if(i<N){
			if(open[i*N+j-1]) PercolationSys.union((i-1)*N+j-1,i*N+j-1);
		}
		if(j>=2){
			if(open[(i-1)*N+j-2]) PercolationSys.union((i-1)*N+j-1,(i-1)*N+j-2);
		}
		if(j<N){
			if(open[(i-1)*N+j]) PercolationSys.union((i-1)*N+j-1,(i-1)*N+j);
		}
		
	}
}
public boolean isOpen(int i, int j){//tells a site is opened or not
	if(i>dimension||j>dimension) throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	return open[(i-1)*dimension+j-1];
}
public boolean isFull(int i, int j){//tells a site is full or not
	if(i>dimension||j>dimension) throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
	return PercolationSys.connected(((i-1)*dimension+j-1),dimension*dimension);
}
public boolean percolates(){//tells whether the system is percolated
	return PercolationSys.connected(dimension*dimension+1,dimension*dimension);
}

}