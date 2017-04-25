/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matriz;

import java.util.ArrayList;

/**
 *
 * @author Aldo
 */
public class Matriz {
    private int M;
    private int N;
    private int R;
    private String matriz[][][];
    private String cadena[];
    private final ArrayList<String> centro = new ArrayList<String>();
    
    public int getM() {
        return M;
    }
    private void setM(int M) {
        this.M = M;
    }
    public int getN() {
        return N;
    }
    private void setN(int N) {
        this.N = N;
    }
    public int getR() {
        return R;
    }

    private void setR(int R) {
        this.R = R;
    }
    
    public Matriz(int f, int c, int r){
        if(f < 0 || c < 0 || r < 0){
            System.out.println("Los números deben ser positivos");
            System.exit(0);
        }
        if(f <= c){
            if(f % 2 == 0){
                this.matriz = new String[f/2][f][c];
            } else if(f < c){
                System.out.println("El número menor debe ser par");
                System.exit(0);
            } else {
                System.out.println("Las filas o las columnas deben ser pares");
                System.exit(0);
            }
        } else if(c < f){
            if(c % 2 == 0){
                this.matriz = new String[c/2][f][c];
            } else {
                System.out.println("El número menor debe ser par");
                System.exit(0);
            }
        }
        this.setM(f);
        this.setN(c); 
        this.setR(r);
        for(int i = 0; i < this.matriz.length; i++){
            for(int fm = 0; fm < this.matriz[i].length; fm++){
                for(int cm = 0; cm < this.matriz[i][fm].length; cm++){
                    matriz[i][fm][cm] = "";
                }
            }
        }
        this.llenarMatrizPrincipal();
        this.imprimirMatriz();
        if(matriz.length > 1){
            this.rellenarMatriz();
        }
        this.girar();
        this.reconstruirMatriz();
        this.imprimirMatriz();
    }
    
    private void llenarMatrizPrincipal(){
        int num = 1;
        for(int f = 0; f < this.getM(); f++){
            for(int c = 0; c < this.getN(); c++){
                if(num < 10){
                    matriz[0][f][c] = "00"+num+" ";
                } else if(num < 100){
                    matriz[0][f][c] = "0"+num+" ";
                } else {
                    matriz[0][f][c] = num+" ";
                }
                num++;
            }
        }
    }
    private void rellenarMatriz(){
        int fm;
        int cm;
        for(int i = 1; i < this.matriz.length; i ++){
            fm = i;
            cm = i;
            for(int f = 0; f < this.getM() - i - i; f++){
                for(int c = 0; c < this.getN() - i - i; c++){
                    this.matriz[i][fm][cm] = this.matriz[0][fm][cm];
                    cm++;
                }
                cm = i;
                fm++;
            }
        }
    }
    
    private void girar(){
        this.hacerCadena();
        for(int i = 0; i < this.matriz.length; i++){
            for(int r = 0; r < this.getR(); r++){
                cadena[i] = cadena[i].substring(4) + cadena[i].charAt(0) + cadena[i].charAt(1) + cadena[i].charAt(2) + cadena[i].charAt(3);
            }
        }
    }
    
    public void hacerCadena(){
        cadena = new String[this.matriz.length];
        int c;
        int f;
        for(int i = 0; i < this.matriz.length; i++){
            cadena[i] = "";
            f = i;
            c = i;
            for(c = i; c < this.getN() -i; c++){
                cadena[i] = cadena[i] + matriz[i][f][c];
            }
            for(f = f+1; f < this.getM() - i; f++){
                cadena[i] = cadena[i] + matriz[i][f][c-1];
            }
            for(c = c-2; c > i; c--){
                cadena[i] = cadena[i] + matriz[i][f-1][c];
            }       
            for(f = f-1; f > i; f--){
                cadena[i] = cadena[i] + matriz[i][f][c];
            }
        }
    }
    
    private void reconstruirMatriz(){
        int c;
        int f;
        int p0;
        int p1;
        int p2;
        int p3;
        for(int i = 0; i < this.matriz.length; i++){
            f = i;
            c = i;
            p0 = 0;
            p1 = 1;
            p2 = 2;
            p3 = 3;
            for(c = i; c < this.getN() -i; c++){
                matriz[0][f][c] = "" + cadena[i].charAt(p0) + cadena[i].charAt(p1) + cadena[i].charAt(p2) + cadena[i].charAt(p3);
                p0 = p0 + 4;
                p1 = p1 + 4;
                p2 = p2 + 4;
                p3 = p3 + 4;
            }
            for(f = f+1; f < this.getM() - i; f++){
                matriz[0][f][c-1] = "" + cadena[i].charAt(p0) + cadena[i].charAt(p1) + cadena[i].charAt(p2) + cadena[i].charAt(p3);
                p0 = p0 + 4;
                p1 = p1 + 4;
                p2 = p2 + 4;
                p3 = p3 + 4;
            }
            for(c = c-2; c > i; c--){
                matriz[0][f-1][c] = "" + cadena[i].charAt(p0) + cadena[i].charAt(p1) + cadena[i].charAt(p2) + cadena[i].charAt(p3);
                p0 = p0 + 4;
                p1 = p1 + 4;
                p2 = p2 + 4;
                p3 = p3 + 4;
            }       
            for(f = f-1; f > i; f--){
                matriz[0][f][c] = "" + cadena[i].charAt(p0) + cadena[i].charAt(p1) + cadena[i].charAt(p2) + cadena[i].charAt(p3);
                p0 = p0 + 4;
                p1 = p1 + 4;
                p2 = p2 + 4;
                p3 = p3 + 4;
            }
        }
    }
    
    private void imprimirMatriz(){
        for(int f = 0; f < this.getM(); f++){
            for(int c = 0; c < this.getN(); c++){
                System.out.print(matriz[0][f][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
  
    public static void main(String[] args) {
        Matriz m = new Matriz(4, 4, 1);
    }
    
}
