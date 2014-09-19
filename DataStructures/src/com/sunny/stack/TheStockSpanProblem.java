package com.sunny.stack;


public class TheStockSpanProblem {
	
	public static void main (String args[]) {
	   int stockPrices[] = new int[] {2,3,10,4,5,6,7};
	   
	   int stockSpans[] =  new int[stockPrices.length];
	   
	   stockSpans[0] = 1;
	   
	   for (int i=1; i<stockPrices.length; i++) {
		   stockSpans[i] = 1;
		   int temp = i-1;
		   while (temp >= 0 && stockPrices[i] > stockPrices[temp]) {
			   stockSpans[i] += stockSpans[temp];
			   temp = temp - stockSpans[temp];
		   }
	   }
	   
	   for (int i=0; i<stockPrices.length; i++) {
		   System.out.println(stockSpans[i]);
	   }
	}
}
