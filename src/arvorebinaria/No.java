/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvorebinaria;

/**
 *
 * @author bruno
 */
public class No {
    
    private double valor;
    private No direita;
    private No esquerda;
    
    public No(){
    }
    
    public No(double valor){
        this.valor = valor;
    }
    
//  metodo pra verificar o grau do No
    public int grau (){
        if (this.direita == null &&
            this.esquerda == null){
            return 0;
        }
        if (this.direita != null &&
            this.esquerda != null){
            return 2;
        }
        return 1;        
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }
    
    
    
    
    
    
}





