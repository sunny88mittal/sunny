package week6;

public class MinHeap {
	
	public static void main (String args[]) {
		MinHeap minHeap = new MinHeap(9);
		
		minHeap.addElement(4);
		minHeap.addElement(9);
		minHeap.addElement(8);
		minHeap.addElement(9);
		minHeap.addElement(4);
		minHeap.addElement(12);
		minHeap.addElement(9);
		minHeap.addElement(11);
		minHeap.addElement(13);
		
		for (int i=0; i<9;i ++) {
			System.out.println(minHeap.getMin());	
			minHeap.delete();
			//minHeap.printHeap();
		}
	}
	
	private int[] elements;
	
	private int currentSize = 0; 
	
	public MinHeap (int size) {
		elements =  new int[size];
	}
	
	public void addElement(int element) {
		elements[currentSize] = element;
		currentSize++;
		if (currentSize > 1) {
			int currentPos = currentSize - 1;
			while (true) {
				int parent = currentPos / 2;
				
				if (elements[currentPos] < elements[parent]) {
					int temp = elements[currentPos];
					elements[currentPos] = elements[parent];
					elements[parent] = temp;
					currentPos = parent;
				} else {
					break;
				}
			}
		}
	}
	
    public void delete() {
    	currentSize--;
    	if (currentSize == 0) {
    		return;
    	}
    	
		elements[0] = elements[currentSize];
		if (currentSize > 1) {
			int currentPos = 0;
			while (true) {
				int leftChildPos = 2 * currentPos;
				if (leftChildPos == 0) {
					leftChildPos +=1;
				}
				int rightChildPos = leftChildPos + 1;
				
				int currentElement = elements[currentPos];
				
				if (leftChildPos < currentSize && rightChildPos < currentSize) {
					int leftElemnt = elements[leftChildPos];
					int rightElement = elements[rightChildPos];
					if (currentElement < leftElemnt && currentElement < rightElement) {
						break;
					} else {
						if (leftElemnt < rightElement) {
							elements[currentPos] = leftElemnt;
							elements[leftChildPos] = currentElement;
							currentPos = leftChildPos;
						} else {
							elements[currentPos] = rightElement;
							elements[rightChildPos] = currentElement;
							currentPos = rightChildPos;
						}
					}
				} else if (leftChildPos < currentSize){
					int leftElemnt = elements[leftChildPos];
					if (currentElement < leftElemnt) {
						break;
					} else {
						elements[currentPos] = leftElemnt;
						elements[leftChildPos] = currentElement;
						currentPos = leftChildPos;							
					}
				} else {
					break;
				}
			}
		}
	}
    
    public int getMin() {
		return elements[0];
	}
    
    public int getCurrentSize() {
    	return currentSize;
    }
    
    public void printHeap() {
    	System.out.println();
    	for (int i=0; i< currentSize; i++) {
    		System.out.print(elements[i] + "\t");
    	}
    }
}
