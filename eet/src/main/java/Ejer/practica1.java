package Ejer;

import java.util.Scanner;

public class practica1 {

    practica1() {
        int [][] matriz = {{1,3,6,10,12,20},{4,7,12,13,15,21},{5,10,13,14,16,22},{6,12,14,15,17,23}};
        //int [][] matriz = {{1,3,6,10,12},{2,7,12,13,15}};
        //int [][] matriz = {{1,3,6,10,12,14,16,18},{2,7,12,13,15,17,19,21}};
        //int [][] matriz = {{1,2},{3,7},{6,12},{10,13},{12,15},{14,17},{16,19},{18,21}};

        System.out.println("Dada la matriz");
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[0].length; j++)
                System.out.printf("%2d ", matriz[i][j]);
            System.out.println("");
        }

        System.out.println("Elige el numero a buscar: ");
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Ha de elegir un numero: ");
            sc.next();
        }
        int numero = sc.nextInt();

        if (buscar(matriz, numero)) {
            System.out.println("El elemento " + numero + " si se encuentra en la matriz");
        } else {
            System.out.println("El elemento " + numero + " no se encuentra en la matriz");
        }
        sc.close();
    }

    private  boolean buscar(int[][] matriz, int numero){

        return buscarAux(matriz, 0, matriz.length-1, 0, matriz[0].length-1, numero);
    }

    private  boolean buscarAux(int[][] matriz, int fi, int fn, int ci, int cn, int numero) {
        if (fi == fn && ci == cn) { //Caso base
            return matriz[fn][cn] == numero;
        } else {

                if (numero > matriz[fn][cn] || numero < matriz[fi][ci]) //Si ese cuadrante no puede contener el numero
                    return false;
                else {
                    //Si el numero se encuentra en las esquinas o el pivote
                    if (matriz[fn][cn] == numero || matriz[fi][ci] == numero || matriz[(fi + fn)/2][(ci + cn)/2] == numero) {
                        return true;
                    }
                    if (fi == fn) { //Si solo hay una fila
                        if(matriz[fi][(ci+cn)/2]>numero)
                            return buscarAux(matriz, fi, fn, ci, (ci + cn)/2, numero);      //Lado izquierdo
                        else
                            return buscarAux(matriz, fi, fn, (ci + cn)/2 + 1, cn, numero);  //Lado derecho
                    }
                    else if (ci == cn) { //Si solo hay una columna
                        if(matriz[(fi+fn)/2][ci]>numero)
                            return buscarAux(matriz, fi, (fi + fn)/2, ci, cn, numero);      //Parte arriba
                        else
                            return buscarAux(matriz, (fi + fn)/2 + 1, fn, ci, cn, numero);  //Parte abajo
                    } else {   //Si hay cuatro cuadrantes, coge tres. ignorando el primero o el cuarto

                        if (matriz[(fi + fn)/2][(ci + cn)/2] > numero) {
                            return  buscarAux(matriz, fi, (fi + fn)/2, ci, (ci + cn)/2, numero)     ||    //Primer Cuadrante
                                    buscarAux(matriz, fi, (fi + fn)/2, (ci + cn)/2 + 1, cn, numero) ||    //Segundo Cuadrante
                                    buscarAux(matriz, (fi + fn)/2 + 1, fn, ci, (ci + cn)/2, numero);      //Tercer Cuadrante
                        } else {
                            return  buscarAux(matriz, fi, (fi + fn)/2, (ci + cn)/2 + 1, cn, numero) ||    //Segundo Cuadrante
                                    buscarAux(matriz, (fi + fn)/2 + 1, fn, ci, (ci + cn)/2, numero) ||    //Tercer Cuadrante
                                    buscarAux(matriz, (fi + fn)/2 + 1, fn, (ci + cn)/2 + 1, cn, numero);  //Cuarto Cuadrante
                        }
                    }
                }
            }
    }
     public static void main(String args[]){
        new practica1();
     }
}

