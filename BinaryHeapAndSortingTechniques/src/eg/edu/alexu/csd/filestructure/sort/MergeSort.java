package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> {

	public void mergeSort(ArrayList  unordered, int first, int last) {
		
		if(first < last) {
		int mid = (last+first)/2;
		mergeSort(unordered, first, mid);
		mergeSort(unordered, mid+1, last);
		merge(unordered, first, mid, last);
		
		}
	}
	private void merge(ArrayList array, int first, int mid, int last) {
		int first1 = first;
		int last1 =mid;
		int first2 = mid+1;
		int last2= last;
		int index =0;
		ArrayList  temp = new ArrayList<>();
		while(first1<=last1 && first2<=last2 ) {
			if(((Comparable<T>)array.get(first1)).compareTo((T)array.get(first2)) > 0) {
				temp.add(array.get(first2));
				first2++;
			}
			else {
				temp.add(array.get(first1));
				first1++;
			}
		}
		while(first1<=last1) {
			temp.add(array.get(first1));
			first1++;
		}
		while(first2<=last2) {
			temp.add(array.get(first2));
			first2++;
		}
		
		for(int i=first;i<=last;i++) {
			array.set(i, temp.get(index));
			index++;
			
		}
	}
}
