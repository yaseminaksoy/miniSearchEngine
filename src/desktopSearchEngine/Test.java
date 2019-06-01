/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopSearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * @file test
* @description mini desktop search engine:
 * The program adds words in files folder except in ignoreList to bst. 
 * Then it orders with preorder and writes the output another file.
 * At the same time it calculates the word counts in files and keeps in heap.
 * @assigment homework2
 * @date 17.04.2019
 * @author yasemin aksoy yasemiiinaksoy@gmail.com
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        BinaryTree bst= new BinaryTree();
        bst.insertIgnore();
       
        bst.readFile("src\\files");
    //    bst.preorder();
        bst.print();
        
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the word you want to search for: ");
        String input = s.nextLine();
        bst.search(input);
    }
}
