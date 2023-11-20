package Exercises;

import java.util.Random;
import java.util.Scanner;

//Method to create a random password with lowercase, uppercase, symbols, numbers with specified length according to the
//inputs from the user

public class Third {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        System.out.print("How many lowercase do you want it to include?");
        int x = scan.nextInt();
        System.out.print("How many uppercase do you want it to include?");
        int y = scan.nextInt();
        System.out.print("How many symbols do you want it to include?");
        int z = scan.nextInt();
        System.out.print("How many numbers do you want it to include?");
        int t = scan.nextInt();
        System.out.println("Your generated password is : "+passwordGenerator(x,y,z,t));
    }

    public static String passwordGenerator(int x, int y, int z, int t){
        String lowerCase = "qwertyuiopasdfghjklmnbvcxz";
        String upperCase = "QWERTYUIOPASDFGHJKLMNBVCXZ";
        String symbols = "@#$_&-+()/?!;:'";
        String numbers = "1234567890";
        String[] among = {lowerCase,upperCase,symbols,numbers};
        Random ran = new Random();
        String pass="";

        int first = 0;
        int second = 0;
        int third = 0;
        int fourth = 0;

        for(int i = 0; i<x+y+z+t; i++){
            int random;
            boolean ok;
            do{
                random = ran.nextInt(4);
                if(random == 0){
                    first++;
                }else if(random == 1){
                    second++;
                }else if(random == 2){
                    third++;
                }else{
                    fourth++;
                }
                ok = first<=x && second<=y && third<=z && fourth<=t;
                if(!ok){
                    if(random == 0){
                        first--;
                    }else if(random == 1){
                        second--;
                    }else if(random == 2){
                        third--;
                    }else{
                        fourth--;
                    }
                }
            }while(!ok);
            String hehe = among[random];
            int otherrand = ran.nextInt(hehe.length());
            pass+=hehe.charAt(otherrand);
        }
        return pass;
    }
}
