/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aop_group_prjct;

import java.util.Random;

/** 
 *
 * @author olegk
 */
public class User {

    private int id;
    private String card_number = "";
    private String user_name;
    private String email;
    private String telephone;
    private int pin;
    private int balance = 0;
    User() {
    }

    User(int i, String c, String u, String e, String t, int p, int b) {
        this.id = i;
        this.card_number = c;
        this.user_name = u;
        this.email = e;
        this.telephone = t;
        this.pin = p;
        this.balance = b;
    }

    // getters =================================================================
    int getId() {
        return id;
    }

    String getCard_Number() {
        return card_number;
    }

    String getUser_name() {
        return user_name;
    }

    String getEmail() {
        return email;
    }

    String getTelephone() {
        return telephone;
    }

    int getPin() {
        return pin;
    }
    int getBalance() {
        return balance;
    }
    // setter ==================================================================
    void Id(int i) {
        id = i;
    }

    void Card_number(String c) {
        card_number = c;
    }

    void Card_number() {
    Random rand = new Random();
    card_number = "1551-";
    for (int i = 0; i < 12; i++) {
        if (i % 4 == 0 && i != 0) {
            card_number += "-";
        }
        card_number += rand.nextInt(10);
    }
}


    void User_name(String u) {
        user_name = u;
    }

    void Email(String e) {
        email = e;
    }

    void Telephone(String t) {
        telephone = t;
    }

    void Pin(int p) {
        int lengthOfPassword = Integer.toString(p).length();
        
        if(lengthOfPassword != 4) {
            System.out.println("Error: not a number or non correct amount of symbols!");
            return;
            //
        }  
        pin = p;
    }
    
    void Pin() {
        Random rand = new Random();
        String res = "";
//        password =;
        for(int i = 0; i < 4; i++) {
            res += rand.nextInt(10);
        }
        pin = Integer.parseInt(res);
    }
    
    void Balance(int b) {
        balance = b;
    }
    
}
// Проробити помилки зчитування карти: тільки 16 цифр 