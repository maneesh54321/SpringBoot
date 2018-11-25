package com.showcase.todo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test {
	public static void main(String args[] ) throws Exception {
		
		Integer[] inputArr= new Integer[5];
		inputArr[0]=1;
		inputArr[1]=5;
		inputArr[2]=3;
		inputArr[3]=4;
		inputArr[4]=2;
        
        kDifference(Arrays.asList(inputArr), 2);
        
    }
	
	 public static int kDifference(List<Integer> a, int k) {
		 Collections.sort(a);
		 int count = 0;
		 for(int i=0; i<a.size();i++) {
			 int j=i+1;
			 int currEle = a.get(i);
			 while(j<a.size()) {
				 int nextEle = a.get(j);
				 if(a.get(j)-a.get(i)==k) {
					 count++;
					 break;
				 } else if(nextEle-currEle>k) {
					 break;
				 }
				 j++;
			 }
		 }
		 System.out.println(count);
		 return count;
	 }
	
}
