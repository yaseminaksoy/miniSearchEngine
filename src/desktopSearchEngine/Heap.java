package desktopSearchEngine;

/**
 * @file heap
 * @description mini desktop search engine:
 * The program adds words in files folder except in ignoreList to bst. 
 * Then it orders with preorder and writes the output another file.
 * At the same time it calculates the word counts in files and keeps in heap.
 * @assigment homework2
 * @date 17.04.2019
 * @author yasemin aksoy yasemiiinaksoy@gmail.com
 */
public class Heap {

    private NodeLinkedList[] heap;
    private int size;

    public Heap(int capacity) {
        heap =  new NodeLinkedList[capacity];
        size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        NodeLinkedList temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    void insert(NodeLinkedList data) {
        int current=0;
        boolean update=false;   //to check the file names 
        if (size < heap.length) {
            
            for (int i = 0; i < heap.length; i++) {
                if(heap[i]==null){  //if heap is empty, break
                    break;
                }
                else if(heap[i].data.equals(data.data)){    //file name == file name in heap
                    heap[i].count+=data.count;  //sums the count values
                    update=true;    //has been updated
                }
            }
            if(update==false){  //if no update
                heap[size] = data;  //adds to heap
                current = size++;
            }
            
            while (heap[current].count>(heap[parent(current)].count)) { //heap controls
                swap(current, parent(current));
                current = parent(current);
            }
        } else {
            System.out.println("array is full !");
        }
    }

    void heapify() {
        int lastIndex = size - 1;

        for (int i = parent(lastIndex); i >= 0; i--) {
            minHeap(i);
        }
    }

    private void minHeap(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int min = i;

        if (left < size && heap[min].count>(heap[left].count)) {
            min = left;
        }
        if (right < size && heap[min].count>(heap[right].count)) {
            min = right;
        }

        if (min != i) {
            swap(min, i);
            minHeap(min);
        }
    }

    NodeLinkedList deleteMin() {
        NodeLinkedList deletedElement = heap[0];

        heap[0] = heap[size - 1];
        size--;
        heapify();

        return deletedElement;
    }

    void printArray() {
        System.out.print("The Count of Word/s on Files = ");
        for (NodeLinkedList element : heap) {
            if(element!=null){
                System.out.print(element.data + ": "+ element.count + " ");
            }
            
        }
        System.out.println();
    }

    // prints parent nodes with their left child and right child
    void print() {
        for (int k = 0; k <= parent(size - 1); k++) {
            int level = (int) (Math.log(k + 1) / Math.log(2));
            System.out.print(level + ". Level ");

            System.out.print("  Parent : " + heap[k]);

            int left = 2 * k + 1;
            int right = 2 * k + 2;

            System.out.print("\t\tLeft Child: " + heap[left]);

            if (right < size) {
                System.out.print("\t\tRight Child :" + heap[right]);
            } else {
                System.out.print("\t\tRight Child : -");
            }

            System.out.println();
        }
    }

}
