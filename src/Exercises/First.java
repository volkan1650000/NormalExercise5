package Exercises;

import java.util.Scanner;

//A java code to facilitate scheduling meetings between two individuals.
//It prompts users for information regarding the meetings each person has scheduled, their time boundaries,
//and identifies potential overlapping time slots for meetings between the two individuals.

public class First {
    static int[][]x;
    static int[][]y;
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        System.out.print("How many meetings does person 1 have today? ");
        int amountOne = scan.nextInt();
        String[][]a = new String[amountOne][2];
        System.out.println("------------------------------------");
        for(int i = 1; i<=amountOne; i++){
            System.out.print((i)+". meeting starts from: ");
            String input = scan.next();
            if(!validTime(input)){
                i--;
                continue;
            }
            System.out.print("\n"+(i)+". meeting ends at: ");
            String other = scan.next();
            if(!validTime(other)){
                i--;
                continue;
            }
            if(!suitableTime(input,other)){
                i--;
                continue;
            }
            a[i-1][0]=input;
            a[i-1][1]=other;
            System.out.println("-------------------------------------");
        }
        String an;
        String bn;
        do{
            System.out.print("what are person 1s boundaries, starting from : ");
            an = scan.next();
            if(!validTime(an)){
                continue;
            }
            System.out.print("ending at : ");
            bn = scan.next();
            if(!validTime(bn)){
                continue;
            }
            if(!suitableTime(an,bn)){
                continue;
            }
            break;
        }while(true);
        String[]ak = {an,bn};

        System.out.print("How many meetings does person 2 have today? ");
        int amountTwo = scan.nextInt();
        String[][]b = new String[amountTwo][2];
        System.out.println("------------------------------------");
        for(int i = 1; i<=amountTwo; i++){
            System.out.print((i)+". meeting starts from: ");
            String input = scan.next();
            if(!validTime(input)){
                i--;
                continue;
            }
            System.out.print("\n"+(i)+". meeting ends at: ");
            String other = scan.next();
            if(!validTime(other)){
                i--;
                continue;
            }
            if(!suitableTime(input,other)){
                i--;
                continue;
            }
            b[i-1][0]=input;
            b[i-1][1]=other;
            System.out.println("------------------------------------");
        }
        String aka;
        String bka;
        do{
            System.out.print("\nwhat are person 2s boundaries, starting from : ");
            aka = scan.next();
            if(!validTime(aka)){
                continue;
            }
            System.out.print("ending at : ");
            bka = scan.next();
            if(!validTime(bka)){
                continue;
            }
            if(!suitableTime(aka,bka)){
                continue;
            }
            break;
        }while(true);

        String[]bk = {aka,bka};

        x = new int[a.length][2];
        y = new int[b.length][2];

        timesToMeet(a,b,ak,bk);
    }


    static void timesToMeet(String[][]a, String[][]b, String[]ak, String[]bk){
        int counter = 0;
        for(String[] w : a){
            String[] first = w[0].split(":");
            String[] second = w[1].split(":");

            int firstFirst = Integer.parseInt(first[0]);
            int firstSecond = Integer.parseInt(first[1]);
            int secondFirst = Integer.parseInt(second[0]);
            int secondSecond = Integer.parseInt(second[1]);

            x[counter][0] = firstFirst*60+firstSecond;
            x[counter][1] = secondFirst*60+secondSecond;
            counter++;
        }

        int count = 0;
        for(String[] w : b){
            String[] first = w[0].split(":");
            String[] second = w[1].split(":");

            int firstFirst = Integer.parseInt(first[0]);
            int firstSecond = Integer.parseInt(first[1]);
            int secondFirst = Integer.parseInt(second[0]);
            int secondSecond = Integer.parseInt(second[1]);

            y[count][0] = firstFirst*60+firstSecond;
            y[count][1] = secondFirst*60+secondSecond;
            count++;
        }
        String[]first = ak[0].split(":");
        String[]second = ak[1].split(":");
        String[]third = bk[0].split(":");
        String[]fourth = bk[1].split(":");

        int x = Integer.parseInt(first[0])*60+Integer.parseInt(first[1]);
        int y = Integer.parseInt(second[0])*60+Integer.parseInt(second[1]);

        int q = Integer.parseInt(third[0])*60+Integer.parseInt(third[1]);
        int w = Integer.parseInt(fourth[0])*60+Integer.parseInt(fourth[1]);

        int from = Math.max(x,q);
        int to = Math.min(y,w);

        for(;from<=to;from++){
            if(isBetween(from)){
                continue;
            }
            System.out.println();
            String add = "";
            int kalan = from%60;
            if(kalan>=0 && kalan<10){
                add = "0";
            }
            System.out.println("From = "+(from/60)+":"+add+kalan);
            while(!isBetween(from) && from<to){
                from++;
            }
            int kaln = from%60;
            add = "";
            if(kaln>=0 && kaln<10){
                add = "0";
            }
            System.out.println("To = "+(from/60)+":"+add+(kaln));
        }
    }


    static boolean validTime(String a){
        if(a.length()==0){
            System.out.println("Please enter a value!\n");
            return false;
        }
        if(!a.contains(":")){
            System.out.println("':'is missing, try again\n");
            return false;
        }
        if(a.indexOf(":")!=a.lastIndexOf(":")){
            System.out.println("Too many ':', please enter only one, try again\n");
            return false;
        }
        String[]b = a.split(":");
        if(b[1].length()!=2){
            System.out.println("Length of minutes part have to be 2 digits");
            return false;
        }
        int f;
        int l;
        try{
            f = Integer.parseInt(b[0]);
            l = Integer.parseInt(b[1]);
        }catch(Exception e){
            System.out.println("Please enter only digits before and after ':'\n");
            return false;
        }
        if(f>23 || f<0){
            System.out.println("Hour can only be from 0 to 24\n");
            return false;
        }
        if(l>59 || l<0){
            System.out.println("Minute can only be from 0 to 60\n");
            return false;
        }
        return true;
    }
    static boolean suitableTime(String a, String b){
        String[]ab = a.split(":");
        String[]bb = b.split(":");
        int x = Integer.parseInt(ab[0]);
        int y = Integer.parseInt(ab[1]);
        int z = Integer.parseInt(bb[0]);
        int j = Integer.parseInt(bb[1]);
        if(x==z && j<=y){
            System.out.println("Start cant be after ending\n");
            return false;
        }else if(x>z){
            System.out.println("Start cant be after ending\n");
            return false;
        }
        return true;
    }

    static boolean isBetween(int from){
        for(int[]w : x){
            if(w[0]<=from && w[1]>from){
                return true;
            }
        }
        for(int[]w : y){
            if(w[0]<=from && w[1]>from){
                return true;
            }
        }
        return false;
    }
}
