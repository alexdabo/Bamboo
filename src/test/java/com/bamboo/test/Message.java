/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.test;

/**
 *
 * @author alexander
 */
public class Message {

    public void printTitle(String title) {
        System.out.print("\n\n\033[1m------------------------------------< \033[0m");
        System.out.print("\033[36m" + title + " \033[0m");
        System.out.println("\033[1m>------------------------------------\033[0m\n");
    }

    public void printDependency() {
        System.out.print("\n\033[1m<\033[0m");
        System.out.print("\033[36m Dependencies \033[0m");
        System.out.println("\033[1m>\033[0m\n");
    }

    public void printImplementation() {
        System.out.print("\n\033[1m<\033[0m");
        System.out.print("\033[36m Implementations \033[0m");
        System.out.println("\033[1m>\033[0m\n");
    }

}
