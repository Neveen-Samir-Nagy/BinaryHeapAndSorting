package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;


import javax.management.RuntimeErrorException;

public class Sort<T extends Comparable<T>> implements ISort {

	@Override
	public IHeap heapSort(ArrayList unordered) {
		// TODO Auto-generated method stub
		if(unordered==null) {
			throw new RuntimeErrorException(null);
		}
		Heap heap = new Heap();
		heap.build(unordered);
		INode[] tempo = new INode[heap.getHeap().size()];
		
		for(int i= (heap.getHeap().size())-1; i>0; i--) {
			Comparable temp =  ((INode)heap.getHeap().get(0)).getValue();
			((INode)(heap.getHeap().get(0))).setValue(((INode)(heap.getHeap().get(i))).getValue());
			((INode)(heap.getHeap().get(i))).setValue(temp);
			((ArrayList<T>) unordered).set(i, (T) (((INode)heap.getHeap().get(i)).getValue()));
			tempo[i]=(INode) heap.getHeap().get(i);
			heap.getHeap().remove(i);
			heap.heapify((INode) heap.getHeap().get(0));
			
		}
		if(unordered.size()!=0) {
		tempo[0]=(INode) heap.getHeap().get(0);
		((ArrayList<T>) unordered).set(0, (T) (((INode)heap.getHeap().get(0)).getValue()));
		}
		heap.setHeap(tempo);
		return heap;
	}

	@Override
	public void sortSlow(ArrayList unordered) {
		// TODO Auto-generated method stub
		// BuBBle_Sort
		if(unordered==null) {
			throw new RuntimeErrorException(null);
		}
		for (int i = 0; i < unordered.size(); i++) {
			for (int j = 0; j < unordered.size() - 1; j++) {
				if (((Comparable<T>) unordered.get(j)).compareTo((T) unordered.get(j + 1)) > 0) {
					T temp = (T) unordered.get(j);
					unordered.set(j, unordered.get(j + 1));
					unordered.set(j + 1, temp);
				}
			}
		}
	}

	@Override
	public void sortFast(ArrayList unordered) {
		// TODO Auto-generated method stub
		if(unordered==null) {
			throw new RuntimeErrorException(null);
		}
		int first =0;
		int last = unordered.size()-1;
		MergeSort m = new MergeSort<>();
		m.mergeSort(unordered, first, last);
	}

	private void insertionSort(ArrayList unordered) {
		for (int i = 1; i < unordered.size(); i++) {
			T item = (T) unordered.get(i);
			int j = i;
			for (j = i; j > 0 && ((Comparable<T>) item).compareTo((T) unordered.get(j - 1)) < 0; j--) {
				unordered.set(j, unordered.get(j - 1));
			}
			unordered.set(j, item);
		}
	}

	private void selectionSort(ArrayList unordered) {
		int size = 0;
		int found = 0;
		boolean flag = false;
		while (size != unordered.size() - 1) {
			flag = false;
			T min = (T) unordered.get(size);
			for (int i = size; i < unordered.size(); i++) {
				Object comp = unordered.get(i);
				if (((Comparable<T>) min).compareTo((T) unordered.get(i)) > 0) {
					min = (T) unordered.get(i);
					found = i;
					flag = true;
				}
			}
			if (flag) {
				T temp = (T) unordered.get(size);
				unordered.set(size, min);
				unordered.set(found, temp);
			}
			size++;
		}
	}
}
