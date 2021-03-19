package Exercitii;
import java.util.Scanner;
import java.util.Random;

public class Tema2Ex1 {

    static void printMatrix(int[][] matrix){
        for(int[] vector : matrix) {
            for (int value : vector) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    static void randomMatrix(int[][] matrix){
        Random random = new Random();
        for(int i = 0; i < matrix.length; i++)   {
            for(int j = 0; j < matrix[i].length; j++)    {
                matrix[i][j] = random.nextInt(100);
            }
        }
    }

    static int sumMatrix(int[][] matrix){
        int sum = 0;

        for(int[] vector : matrix)
            for (int value : vector)
                sum += value;

        return sum;
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        randomMatrix(matrix);
        printMatrix(matrix);
        System.out.println(sumMatrix(matrix));





    }
}