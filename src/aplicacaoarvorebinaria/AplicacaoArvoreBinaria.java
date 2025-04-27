package aplicacaoarvorebinaria;
import arvorebinaria.*;

public class AplicacaoArvoreBinaria {

    public static void main(String[] args) {
        ABB arvore = new ABB();
        
        arvore.insere(20);
        arvore.insere(8);
        arvore.insere(50);
        arvore.insere(12);
        arvore.insere(30);
        arvore.insere(15);
        arvore.insere(10);
        arvore.insere(40);
        arvore.insere(28);
        arvore.printIncurso();       
        
        System.out.println("");
        
//        arvore.removeGrau0(50);
        arvore.removeGrau1(50);
        
        arvore.printIncurso(); 
        
    }
    
}
