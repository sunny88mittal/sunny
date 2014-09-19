package week1;

public class VertexMinHeap {
	
	private HeapVertex[] elements;
	
	private int vertexPositions[];
	
	private int currentSize = 0; 
	
	public VertexMinHeap (int size) {
		elements =  new HeapVertex[size];
		vertexPositions = new int[size+1];
	}
	
	public void addElement(HeapVertex element) {
		elements[currentSize] = element;
		vertexPositions[element.vertex] = currentSize;
		currentSize++;
		if (currentSize > 1) {
			int currentPos = currentSize - 1;
			while (true) {
				int parent = currentPos / 2;
				
				if (elements[currentPos].minWeight < elements[parent].minWeight) {
					HeapVertex temp = elements[currentPos];
					elements[currentPos] = elements[parent];
					elements[parent] = temp;
					currentPos = parent;
					
					vertexPositions[elements[currentPos].vertex] = currentPos;
					vertexPositions[elements[parent].vertex] = parent;					
				} else {
					break;
				}
			}
		}
	}
	
    public void deleteMin() {
    	currentSize--;
    	if (currentSize == 0) {
    		return;
    	}
    	
		elements[0] = elements[currentSize];
		vertexPositions[elements[0].vertex] = 0;
		if (currentSize > 1) {
			int currentPos = 0;
			while (true) {
				int leftChildPos = 2 * currentPos;
				if (leftChildPos == 0) {
					leftChildPos +=1;
				}
				int rightChildPos = leftChildPos + 1;
				
				HeapVertex currentElement = elements[currentPos];
				
				if (leftChildPos < currentSize && rightChildPos < currentSize) {
					HeapVertex leftElemnt = elements[leftChildPos];
					HeapVertex rightElement = elements[rightChildPos];
					if (currentElement.minWeight < leftElemnt.minWeight && currentElement.minWeight < rightElement.minWeight) {
						break;
					} else {
						if (leftElemnt.minWeight < rightElement.minWeight) {
							elements[currentPos] = leftElemnt;
							elements[leftChildPos] = currentElement;
							currentPos = leftChildPos;
							
							vertexPositions[elements[currentPos].vertex] = currentPos;
							vertexPositions[elements[leftChildPos].vertex] = leftChildPos;
						} else {
							elements[currentPos] = rightElement;
							elements[rightChildPos] = currentElement;
							currentPos = rightChildPos;
							
							vertexPositions[elements[currentPos].vertex] = currentPos;
							vertexPositions[elements[rightChildPos].vertex] = rightChildPos;
						}
					}
				} else if (leftChildPos < currentSize){
					HeapVertex leftElemnt = elements[leftChildPos];
					if (currentElement.minWeight < leftElemnt.minWeight) {
						break;
					} else {
						elements[currentPos] = leftElemnt;
						elements[leftChildPos] = currentElement;
						currentPos = leftChildPos;				
						
						vertexPositions[elements[currentPos].vertex] = currentPos;
						vertexPositions[elements[leftChildPos].vertex] = leftChildPos;
					}
				} else {
					break;
				}
			}
		}
	}
    
    public HeapVertex delete (int vertex) {
    	int indexInArray = vertexPositions[vertex];
    	currentSize--;
    	if (currentSize == 0) {
    		return null;
    	}
    	
    	HeapVertex toReturn = elements[indexInArray];
    	
		elements[indexInArray] = elements[currentSize+1];
		vertexPositions[elements[indexInArray].vertex] =  indexInArray;
		
		if (currentSize > 1) {
			int currentPos = indexInArray;
			while (true) {
				int leftChildPos = 2 * currentPos;
				if (leftChildPos == 0) {
					leftChildPos +=1;
				}
				int rightChildPos = leftChildPos + 1;
				
				HeapVertex currentElement = elements[currentPos];
				
				if (leftChildPos < currentSize && rightChildPos < currentSize) {
					HeapVertex leftElemnt = elements[leftChildPos];
					HeapVertex rightElement = elements[rightChildPos];
					if (currentElement.minWeight < leftElemnt.minWeight && currentElement.minWeight < rightElement.minWeight) {
						break;
					} else {
						if (leftElemnt.minWeight < rightElement.minWeight) {
							elements[currentPos] = leftElemnt;
							elements[leftChildPos] = currentElement;
							currentPos = leftChildPos;
							
							vertexPositions[elements[currentPos].vertex] = currentPos;
							vertexPositions[elements[leftChildPos].vertex] = leftChildPos;
						} else {
							elements[currentPos] = rightElement;
							elements[rightChildPos] = currentElement;
							currentPos = rightChildPos;
							
							vertexPositions[elements[currentPos].vertex] = currentPos;
							vertexPositions[elements[rightChildPos].vertex] = rightChildPos;
						}
					}
				} else if (leftChildPos < currentSize){
					HeapVertex leftElemnt = elements[leftChildPos];
					if (currentElement.minWeight < leftElemnt.minWeight) {
						break;
					} else {
						elements[currentPos] = leftElemnt;
						elements[leftChildPos] = currentElement;
						currentPos = leftChildPos;				
						
						vertexPositions[elements[currentPos].vertex] = currentPos;
						vertexPositions[elements[leftChildPos].vertex] = leftChildPos;
					}
				} else {
					break;
				}
			}
		}
		
		return toReturn;
    }
    
    public HeapVertex getMin() {
		return elements[0];
	}
    
    public int getCurrentSize() {
    	return currentSize;
    }
    
    public void printHeap() {
    	System.out.println();
    	for (int i=0; i< currentSize; i++) {
    		System.out.print(elements[i] + "\n");
    	}
    }
}
