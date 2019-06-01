/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopSearchEngine;

/**
 * @file fileLinkedList
 * @description mini desktop search engine:
 * The program adds words in files folder except in ignoreList to bst. 
 * Then it orders with preorder and writes the output another file.
 * At the same time it calculates the word counts in files and keeps in heap.
 * @assigment homework2
 * @date 17.04.2019
 * @author yasemin aksoy yasemiiinaksoy@gmail.com
 */
public class FileLinkedList {

    NodeFile head;
    
    void addToList(String veri){  //addFirst for file names
        NodeFile newNode = new NodeFile(veri);
        if(head == null){
            head = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }
    
    boolean inIgnoreList(String data){ //checks the word is in ignoreList
            NodeFile tmp=head;
            while(tmp!=null){
                if(tmp.data.equals(data)){  
                    return true;
                }else{
                    tmp=tmp.next;
                }
            }
            return false;
    }
    
    int size() {
        NodeFile temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }
}
