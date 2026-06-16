package entidades;

public class Filmes {
      private int    codigo;
    private String nome;
    private String genero;
    private int    ano;
    private double nota;
 
    // ----------------------------------------------------------------
    // Construtor
    // ----------------------------------------------------------------
    public Filmes(int codigo, String nome, String genero, int ano, double nota) {
        this.codigo = codigo;
        this.nome   = nome;
        this.genero = genero;
        this.ano    = ano;
        this.nota   = nota;
    }
 
    // ----------------------------------------------------------------
    // Getters e Setters
    // ----------------------------------------------------------------
    public int getCodigo()             { return codigo; }
    public void setCodigo(int codigo)  { this.codigo = codigo; }
 
    public String getNome()            { return nome; }
    public void setNome(String nome)   { this.nome = nome; }
 
    public String getGenero()              { return genero; }
    public void setGenero(String genero)   { this.genero = genero; }
 
    public int getAno()                { return ano; }
    public void setAno(int ano)        { this.ano = ano; }
 
    public double getNota()            { return nota; }
    public void setNota(double nota)   { this.nota = nota; }
 
    // ----------------------------------------------------------------
    // Exibição
    // ----------------------------------------------------------------
    @Override
    public String toString() {
        return String.format(
            "  +--------------------------------------------+%n" +
            "  | Codigo : %-33d|%n" +
            "  | Nome   : %-33s|%n" +
            "  | Genero : %-33s|%n" +
            "  | Ano    : %-33d|%n" +
            "  | Nota   : %-33.1f|%n" +
            "  +--------------------------------------------+",
            codigo, nome, genero, ano, nota
        );
    }
 
    /** Linha compacta para listas */
    public String toLinhaTabela() {
        return String.format("  [%04d] %-38s | %-18s | %4d | %.1f",
            codigo, nome, genero, ano, nota);
    }

}
