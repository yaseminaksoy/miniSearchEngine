/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopSearchEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @file binaryTree
* @description mini desktop search engine:
 * The program adds words in files folder except in ignoreList to bst. 
 * Then it orders with preorder and writes the output another file.
 * At the same time it calculates the word counts in files and keeps in heap.
 * @assigment homework2
 * @date 17.04.2019
 * @author yasemin aksoy yasemiiinaksoy@gmail.com
 */
public class BinaryTree {
    private Node root;

    FileLinkedList ignoreList = new FileLinkedList(); //crates linkedlist for ignore list file
    FileLinkedList files = new FileLinkedList();    //files ends with ".html"
    
    void insertIgnore() {    //addToIgnoreList
        try {
            BufferedReader in;
            in = new BufferedReader(new FileReader("src\\files\\ignoreList.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                ignoreList.addToList(str);
            }
            in.close();
        } catch (IOException e) {
        }
    }
    
    // iterative search method
    void search(String searchData) {
        String words [] = searchData.split(" ");    //splits words that user entered
        Heap heap=new Heap(files.size());    //creates heap
        for (int i = 0; i < words.length; i++) {
            if (root == null) {
            System.out.println("empty tree !!");
        } else {
            Node temp = root;
            while (temp != null) {
                if (words[i].compareTo(temp.data) > 0) {
                    temp = temp.right;
                } else if (words[i].compareTo(temp.data) < 0) {
                    temp = temp.left;
                } else {    //roaming the linkedlist of the node in BST 
                   NodeLinkedList head = temp.liste.head;    
                   NodeLinkedList tmp=head;
                   while(tmp!=null){
                       heap.insert(tmp);    //insert heap with fileName and count informations
                       tmp=tmp.next;
                   }
                   break;   //when tmp==null, prevents the endless loop
                }
                
            }
            
        }
        
        }
        heap.printArray();
    }
    
    void readFile(String Files) {   
        File file = new File(Files);
        File[] b = file.listFiles(); 

        for (int i = 0; i < b.length; i++) {
            if (!b[i].getName().equals("ignoreList.txt")) {  
                Scanner s;
                try {
                    s = new Scanner(b[i]);
                    files.addToList(b[i].getName());
                    while (s.hasNext()) {
                        String word = s.next();    
                        if (word.charAt(0) != '<' && !ignoreList.inIgnoreList(word) && !Pattern.matches("\\p{Punct}", word)) {    //cheks the states of unwanted
                            addToBST(word, b[i].getName());   //add to bst with its file name
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BinaryTree.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    void addToBST(String word, String dosyaAdi) {
        Node newNode = new Node(word);    //node of bst
        if (root == null) {
            newNode.liste.addToList(dosyaAdi);    //if the word's list is empty, adds its list
            root = newNode; 
        } else {    //if word's linkedlist is not empty
            Node temp = root;

            while (temp != null) {
                if (newNode.data.compareTo(temp.data) > 0) {    //word>word in bst
                    if (temp.right == null) {   
                        newNode.liste.addToList(dosyaAdi);    //adds node's list on right of bst
                        temp.right = newNode;   
                        return;
                    }
                    temp = temp.right;
                } else if (newNode.data.compareTo(temp.data) < 0) { //word<word in bst
                    if (temp.left == null) {
                        newNode.liste.addToList(dosyaAdi);    //adds node's list on left of bst
                        temp.left = newNode;    
                        return;
                    }

                    temp = temp.left;
                } else {    //word==word in bst
                    newNode.liste.updateList(dosyaAdi, temp.liste);  //updates the list to increase the count value
                    return;
                }
            }
        }
    }

    void preorder() {
        System.out.println("preorder : ");
        preorder(root);
        System.out.println();
    }

    private void preorder(Node node) { 
        if (node != null) {
            System.out.print(node.data + " ");
            node.liste.print();
            preorder(node.left);
            preorder(node.right);
        }
    }

    void print(Node node) {
        if (node != null) {
            File file = new File("src\\output.txt");    //creates a new file
            BufferedWriter bf;
            try {
                bf = new BufferedWriter(new FileWriter(file, true));
                bf.write(node.data + " ");
                bf.write(node.liste.print());
                bf.newLine();
                bf.close();
                print(node.left);
                print(node.right);
            } catch (IOException ex) {
                Logger.getLogger(BinaryTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void print() {

        File file = new File("src\\output.txt");
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            bf.write("preorder: ");
            bf.newLine();
            bf.close();
            print(root);
        } catch (IOException ex) {
            Logger.getLogger(BinaryTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
