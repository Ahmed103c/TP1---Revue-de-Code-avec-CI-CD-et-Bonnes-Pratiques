package com.f2.revue_code;

import java.io.File;
import java.io.IOException;

public class Main {
       public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Rzeigui Ahmed\\Documents\\Revue de code\\TP1---Revue-de-Code-avec-CI-CD-et-Bonnes-Pratiques\\aipasswordcheck\\src\\main\\resources\\cluster_centers_HAC_aff.csv");
            AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance(file);
            String password = "password123!";
            System.out.println("Distance: " + checker.getDIstance(password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
