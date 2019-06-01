/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopSearchEngine;

/**
 * @file nodeFile
* @description mini desktop search engine:
 * The program adds words in files folder except in ignoreList to bst. 
 * Then it orders with preorder and writes the output another file.
 * At the same time it calculates the word counts in files and keeps in heap.
 * @assigment homework2
 * @date 17.04.2019
 * @author yasemin aksoy yasemiiinaksoy@gmail.com
 */
public class NodeFile {
    String data;    //File name
    NodeFile next; 
    
    public NodeFile(String data) {
        this.data = data;
    }

    
    
}
