package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;

import javax.management.RuntimeErrorException;

public class Heap<T extends Comparable<T>> implements IHeap{

	ArrayList<INode<T>> heap = new ArrayList<INode<T>>();
	@Override
	public INode getRoot() {
		// TODO Auto-generated method stub
		if(heap.size()==0) {
			return null;
		}
		return heap.get(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	@Override
	public void heapify(INode node) {
		// TODO Auto-generated method stub
		if(node == null) {
			return;
		}
		INode left = node.getLeftChild();
		INode right = node.getRightChild();
		INode largest = node;
		if(left!=null && ((Comparable<T>) left.getValue()).compareTo((T) node.getValue()) > 0) {
			largest = left;
		}else {
			largest = node;
		}
		if(right != null && ((Comparable<T>) right.getValue()).compareTo((T) largest.getValue()) > 0){
			largest = right;
		}
		if(largest != node) {
			Comparable temp = node.getValue();
			node.setValue(largest.getValue());
			largest.setValue(temp);
			if(node != getRoot()) {
			heapify(node.getParent());
			}
			heapify(largest);
		}
	}

	@Override
	public Comparable extract() {
		// TODO Auto-generated method stub
		Comparable root;
		if(heap.size()==0) {
			root = null;
		}else  {
		 root = (Comparable)(heap.get(0)).getValue();
		}
		if(root == null) {
			return null;
		}
		((INode)(heap.get(0))).setValue(((INode)(heap.get(heap.size()-1))).getValue());
		((Node)(heap.get(0))).setIndex(0);
		heap.remove(heap.size()-1);
		if(heap.size()!=0) {
		heapify(heap.get(0));
		}
		return root;
	}

	@Override
	public void insert(Comparable element) {
		if(element == null) {
			return;
		}
		Node n= new Node<>(heap.size());
		n.setValue(element);
		heap.add(n);
		if(n.getParent() != null) {
		heapify(n.getParent());
		}
	}

	public ArrayList<INode<T>> getHeap(){
		return heap;
	}
	public void setHeap(INode[] tempo){
		heap.clear();
		for(int i=0;i<tempo.length;i++) {
			heap.add(tempo[i]);
		}
	}
	
	@Override
	public void build(Collection unordered) {
		if(unordered==null ) {
			throw new RuntimeErrorException(null);
		}
		for(int i=0;i<unordered.size();i++) {
			Node n = new Node<>(i);
			n.setValue((((ArrayList<T>) unordered).get(i)));
			heap.add(n);
		}
		for(int i=(unordered.size()/2)-1;i>=0;i--) {
			heapify(heap.get(i));
		}
		for(int i = 0; i<heap.size();i++) {
			((ArrayList<T>) unordered).set(i, heap.get(i).getValue());
		}
	}
	private class Node<T extends Comparable<T>> implements INode{
		int index ;
	    Comparable<T> value ;
		public Node(int i) {
			index = i+1;
		}
		public void setIndex(int i) {
			index = i+1;
		}

		@Override
		public INode getLeftChild() {
			if(2*index - 1 >= heap.size()) {
				return null;
			}
			return heap.get(2*index - 1);
		}

		@Override
		public INode getRightChild() {
			if(2*index+1 - 1 >= heap.size()) {
				return null;
			}
			return heap.get(2*index+1 - 1);
		}

		@Override
		public INode getParent() {
			if(index/2 == 0) {
				return null;
			}
			return heap.get((index/2) - 1);
		}

		@Override
		public Comparable<T> getValue() {
			
			return (Comparable<T>) value;
		}

		@Override
		public void setValue(Comparable value) {
			this.value = value;
			
		}
		
	}
	}
