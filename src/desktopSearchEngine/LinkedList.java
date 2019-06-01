/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopSearchEngine;

/**
 * @file linkedlist
 * @description mini desktop search engine:
 * The program adds words in files folder except in ignoreList to bst. 
 * Then it orders with preorder and writes the output another file.
 * At the same time it calculates the word counts in files and keeps in heap.
 * @assigment homework2
 * @date 17.04.2019
 * @author yasemin aksoy yasemiiinaksoy@gmail.com
 */
public class LinkedList {
    NodeLinkedList head;
    
    void addToList(String fileName){ //creates a list for every word according to existing file
        NodeLinkedList newNode = new NodeLinkedList(fileName);
        if(head==null){ 
            newNode.count=1;    //if the list is empty, determines the count=1 and adds to head
            head = newNode;
        }else{
            newNode.count=1;    //addFirst
            newNode.next = head;
            head = newNode;
        }
    }
    void updateList(String fileName, LinkedList liste){  //if the same word comes to BST, changes the count value and updates the list
        NodeLinkedList newNode = new NodeLinkedList(fileName);  //the node of word
        NodeLinkedList tmp = liste.head;    //roaming the list of word that encountered with the same word
        while(tmp!=null){   
            if(tmp.data.equals(fileName)){
                tmp.count=tmp.count+1;  //increasing the count value
                break;  
            }
            tmp=tmp.next;
        }   
        if(tmp==null){  //if program process on here,it means the word is not in the same file with the encountered word 
            liste.addToList(fileName);    //so, it goes to addToList to add its own list
        }
    }
    
    String print(){
        NodeLinkedList tmp=head;
        String a="";    //adds to String because of bufferedWriter method takes String parametres
        while(tmp!=null){
            a+=tmp.data+" , "+tmp.count+" -> ";
            tmp=tmp.next;
        }
        a+="null";
        return a;
    }
    
    String words(){
        NodeLinkedList tmp=head;
        String a="";    
        while(tmp!=null){
            a+=tmp.data+" ";
            tmp=tmp.next;
        }
        return a;
    }
    
    int wordCount(){
        NodeLinkedList tmp=head;
        int a=0;    
        while(tmp!=null){
            a+=tmp.count;
            tmp=tmp.next;
        }
        return a;
    }
    void addLast(String data, int count) {
        NodeLinkedList newNode = new NodeLinkedList(data,count);

        if (head == null) {
            head = newNode;
        } else {
            NodeLinkedList temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }
    }
    
}
