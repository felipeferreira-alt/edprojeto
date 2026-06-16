package entidades;

public class no {
     private Filmes filme;
    private no    esquerdo;
    private no    direito;
 
   
    public no(Filmes filme) {
        this.filme    = filme;
        this.esquerdo = null;
        this.direito  = null;
    }
 
  
    public Filmes getFilme()              { return filme; }
    public void  setFilme(Filmes filme)   { this.filme = filme; }
 
    public no getEsquerdo()              { return esquerdo; }
    public void setEsquerdo(no esquerdo) { this.esquerdo = esquerdo; }
 
    public no getDireito()               { return direito; }
    public void setDireito(no direito)   { this.direito = direito; }

}
