package Exercitii;

import java.util.Scanner;

public class Tema1 {

    public static String order(int x, int y){
        if(x > y)
            return  "  " + x + " > " + y;
        else
        if(x == y)
            return  "  " + x + " = " + y;
        else
            return  "  " + x + " < " + y;

    }

    public static Boolean checkPrime(int n){
        if(n <= 1)
            return false;
        else {
            for(int i = 2; i <= Math.sqrt(n); i++)
                if(n % i == 0)
                    return false;

        return true;
        }
    }

    public static void prime50(){
        for(int i = 2; i <= 47; i++)
            if(checkPrime(i))
                System.out.print(i + " ");
    }

    public static void avg(){
        Scanner scanner = new Scanner(System.in);
        int n, sum = 0, nr = 0;
        n = scanner.nextInt();

        while(n != -1){
            System.out.println(n);
            sum += n;
            nr ++;
            n = scanner.nextInt();
        }

        if(nr > 0)
            System.out.println(sum / nr);
        else
            System.out.println("Nu exista numere pentru a calcula media!");

    }

    public static int pow(int n, int exp){
        int nCopy = n;
        if(n == 0)
            if(exp != 0)
                return 0;
            else
                return -1;

        if(exp == 0)
            return 1;

        for(int i = 1; i <= exp; i++)
            nCopy *= n;

        return nCopy;
    }


    public static void main(String[] args) {

        // 1
       /* Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti primul termen: ");
        int n = scanner.nextInt();

        System.out.println(0);
        for(int i = 5; i <= n; i += 10)
            System.out.println(i);*/

        // 2
        /* Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti primul termen: ");
        int x = scanner.nextInt();
        System.out.println("Introduceti al doilea termen: ");
        int y = scanner.nextInt();
        System.out.println("Introduceti al treilea termen: ");
        int z = scanner.nextInt();

        System.out.println(order(x, y) + order(y, z) + order(x, z));*/

        // 3
        /* System.out.println(checkPrime(2));
        prime50();*/

        // 4
        // avg();

        // 5
        // System.out.println(pow(2,4));



    }

}
