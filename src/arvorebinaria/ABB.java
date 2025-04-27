package arvorebinaria;

public class ABB {
   
    private No raiz;
    
    public ABB(){
        this.raiz = null;
    }
    
    public boolean vazia(){
        return (this.raiz == null);
    }    
    
    private boolean direitaLivre(No temp){
        return (temp.getDireita() == null);
    }    
    private boolean esquerdaLivre(No temp){
        return (temp.getEsquerda() == null);
    }
    
    public void printPrecurso(){
        System.out.println("--------------------------------");
        precurso(this.raiz);
        System.out.printf("\n---------------------------------\n");
    }    
    private void precurso(No aux){
        System.out.printf("%.1f - ",aux.getValor());
        if(aux.getEsquerda() != null){precurso(aux.getEsquerda());}
        if (aux.getDireita() != null){precurso(aux.getDireita());}
    }    
    public void printIncurso(){
        System.out.println("--------------------------------");
        incurso(this.raiz);
        System.out.printf("\n---------------------------------\n");
    }    
    private void incurso(No aux){
        if(aux.getEsquerda() != null){incurso(aux.getEsquerda());}
        System.out.printf("%.1f - ",aux.getValor());
        if (aux.getDireita() != null){incurso(aux.getDireita());}
    }
    
    public boolean insere(double valor){
        if (vazia()){
            No aux = new No(valor);
            this.raiz = aux;
            return true;
        }
        if (busca(valor) != -1){ return false; }
        
        //testar repetido
        No aux = this.raiz;
        while(true){
            if (valor > aux.getValor() && direitaLivre(aux)){
                No temp = new No(valor);
                aux.setDireita(temp);
                return true;
            }
            if (valor < aux.getValor() && esquerdaLivre(aux)){
                No temp = new No(valor);
                aux.setEsquerda(temp);
                return true;
            }
            if (valor > aux.getValor() && !direitaLivre(aux)){
                aux = aux.getDireita();
            } else if (valor < aux.getValor() && !esquerdaLivre(aux)){
                aux = aux.getEsquerda();
            }
        }
    }    
    
//  verifica se NO existe e retorna o grau da raiz
    public int busca (double valor) {
        if (vazia()){ return -1;}
        if (this.raiz.getValor() == valor ) {
            return this.raiz.grau() ;}
        
        No aux = this.raiz;
//      percorre ENQUANTO aux for diferente de null (nulo)
        while (aux != null) {
            if (aux.getValor() == valor) { return aux.grau(); }
            if (valor > aux.getValor() ){
                aux = aux.getDireita();
            } else {
                aux = aux.getEsquerda();
            }
        }
        return -1; 
    }
    
    public int contarAltura (double valor){
        if (vazia()){ return -1;}
        //retorna 0 se for a raiz
        int altura = 0;
        No aux = this.raiz;
//      percorre ENQUANTO aux for diferente de NULL
        while (aux != null) {
            if (aux.getValor() == valor) { return altura; }
            if (valor > aux.getValor() ){
                aux = aux.getDireita();
            } else {
                aux = aux.getEsquerda();
            }
            altura++;
        }
        return altura = 0;
    }
    
//  método que remove folha ou raiz sem galho
    public boolean removeGrau0 (double valor){
        if (busca(valor) == -1){ return false; }
        if (this.raiz.grau() == 0 ){ 
            this.raiz = null;
            return true; }
//      bloco de codigo que busca o NO
//      e salva o No anterior ao que precisa ser removido
        No aux = this.raiz;
        No ant = aux;
        while (aux.getValor() != valor) {
            if (valor > aux.getValor() ){
                ant = aux;
                aux = aux.getDireita();
            } else {
                ant = aux;
                aux = aux.getEsquerda();
            }      
        }
        
        if (ant.getDireita().getValor() == valor){
            ant.setDireita(null);
        } else {
            ant.setEsquerda(null);
        }
        return true;
    }
    
    public boolean removeGrau1 (double valor){
        if (busca(valor) == -1){ return false; }
        if (this.raiz.getValor() == valor){ 
            if (!direitaLivre(raiz)){
                this.raiz = this.raiz.getDireita();
                return true;
            }else {
                this.raiz = this.raiz.getEsquerda();
                return true;
            }
        }
        
        No aux = this.raiz;
        No ant = aux;
        while (aux.getValor() != valor) {
            if (valor > aux.getValor() ){
                ant = aux;
                aux = aux.getDireita();
            } else {
                ant = aux;
                aux = aux.getEsquerda();
            }      
        }
        
        if (ant.getDireita().getValor() == valor){
                if (!direitaLivre(aux)){
                ant.setDireita(aux.getDireita());
                return true;
                }else {ant.setDireita(aux.getEsquerda());
                       return true;
            }
        } else {
            if (!esquerdaLivre(ant)){
                ant.setEsquerda(aux.getDireita());
                return true;
            }else {ant.setEsquerda(aux.getEsquerda());
                   return true;
            }
        }
    }
    
    public boolean removeGrau2 (double valor){
        if (busca(valor) == -1){ return false; }
//      Encontrar o valor a ser analisado para remover
        No aux = this.raiz;
        while (aux.getValor() != valor) {
            if (valor > aux.getValor() ){
                aux = aux.getDireita();
            } else {
                aux = aux.getEsquerda();
            }      
        }
        
//      OBS: o melhor seria verificar a altura da arvore
//      menor entre os maiores
        double menorMaiores = menor(aux.getDireita());
        double maiorMenores = maior(aux.getEsquerda());
        
        if(contarAltura(menorMaiores) > contarAltura(maiorMenores) ){
    //      remove o valor a ser realocado
            if(busca(menorMaiores) == 0){ removeGrau0(menorMaiores); }
            if(busca(menorMaiores) == 1){ removeGrau1(menorMaiores); }
            //      atualiza o NO com o valor realocado
            aux.setValor(menorMaiores);
        } else {
            if(busca(maiorMenores) == 0){ removeGrau0(maiorMenores); }
            if(busca(maiorMenores) == 1){ removeGrau1(maiorMenores); }
            aux.setValor(maiorMenores);
        }
        return true;
    }
    
    public boolean removeT (double valor) {
        if (busca(valor) == -1){ return false;}
        if (busca(valor) == 0){removeGrau0(valor);}
        if (busca(valor) == 1){removeGrau1(valor);}
        if (busca(valor) == 2){removeGrau2(valor);}
        return true;        
    }
    
//  Retorna o maior valor partindo do No escolhido
    public double maior (No no){
        No aux = no;
        while (!direitaLivre(aux)){
            aux = aux.getDireita();
        }
        return aux.getValor();
    }
//  Retorna o menor valor partindo do No escolhido
    public double menor (No no){
        No aux = no;
        while (!esquerdaLivre(aux)){
            aux = aux.getEsquerda();
        }
        return aux.getValor();
    }
    
    
    
//  Retorna o No anterior ao No buscado
    public No buscaNoAnterior (double valor){ 
        if (vazia()){ 
            No aux2 = new No(-1);
            return aux2; }
        No aux = this.raiz;
        if (valor == aux.getValor() ){
            No aux2 = new No(-1);
            return aux2;
        }
//        usa a base do insere para buscar o Nó
        while(true){
            if (!esquerdaLivre(aux) ){
                if (valor == aux.getEsquerda().getValor() ){
                return aux;
                }
            }
            if (!direitaLivre(aux) ){
                if (valor == aux.getDireita().getValor() ){
                return aux;
                }
            }
            
            if (valor > aux.getValor() && !direitaLivre(aux)){
                aux = aux.getDireita();
            } else if (valor < aux.getValor() && !esquerdaLivre(aux)){
                aux = aux.getEsquerda();
            } else { 
                No aux2 = new No(-2);
                return aux2; }
        }
    }
    
    //Metodo usado para testar a função BuscaNo
    public void printBusca (double valor){ 
        No aux = new No();
        aux = buscaNoAnterior(valor);
        System.out.println(aux);
        System.out.println(aux.getValor());
        if (aux.getDireita() != null) {
            System.out.println(aux.getDireita().getValor());
        }
        if (aux.getEsquerda()!= null) {
            System.out.println(aux.getEsquerda().getValor());
        }
    }
    
//    metodo completo de remove com todas as possiveis condições...
//    assim como o metodo insere
    public boolean remove (double valor){
        No aux = buscaNoAnterior(valor);
        
        if (aux.getValor() == -1){
            No aux2 = this.raiz;
            this.raiz = null;
            realocarNo(aux2 );
            return true;
        } 
        if (!direitaLivre(aux) ){
            System.out.println(aux.getDireita().getValor());
            if (aux.getDireita().getValor() == valor ){
                No aux2 = aux.getDireita();
                aux.setDireita(null);
                realocarNo(aux2 );
                return true;
            }
        }
        if (!esquerdaLivre(aux) ){
            if (aux.getEsquerda().getValor() == valor){
                No aux2  = aux.getEsquerda();
                aux.setEsquerda(null);
                realocarNo(aux2 );
                return true;
            }
        }
        return false;
    }
    
//    metodo que realoca os NOs após a remoção
    public boolean realocarNo(No aux) {
        System.out.println(aux.getValor());
        if (!direitaLivre(aux) ){
            if (aux.getDireita() != null){
                insere(aux.getDireita().getValor() );
                realocarNo(aux.getDireita() );
            }
        }
        if (!esquerdaLivre(aux) ){
            if (aux.getEsquerda() != null){
                insere(aux.getEsquerda().getValor() );
                realocarNo(aux.getEsquerda() );
            }
        }
        return true;
    }
   
}






