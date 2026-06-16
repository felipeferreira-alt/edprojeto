package entidades;

public class ArvoreBinaria {
     private no raiz;
 
    // ----------------------------------------------------------------
    // Construtor
    // ----------------------------------------------------------------
    public ArvoreBinaria() {
        this.raiz = null;
    }
 
    // ================================================================
    // 1. INSERIR
    // ================================================================
    public void inserir(Filmes filme) {
        raiz = inserirRecursivo(raiz, filme);
    }
 
    private no inserirRecursivo(no nos, Filmes filme) {
        if (nos == null) {
            System.out.println("  Filme inserido com sucesso!");
            return new no(filme);
        }
        if (filme.getCodigo() < nos.getFilme().getCodigo()) {
            nos.setEsquerdo(inserirRecursivo(nos.getEsquerdo(), filme));
        } else if (filme.getCodigo() > nos.getFilme().getCodigo()) {
            nos.setDireito(inserirRecursivo(nos.getDireito(), filme));
        } else {
            System.out.println("  AVISO: Ja existe um filme com o codigo "
                + filme.getCodigo() + ". Insercao ignorada.");
        }
        return nos;
    }
 
    // ================================================================
    // 2. BUSCAR
    // ================================================================
    public Filmes buscar(int codigo) {
        no resultado = buscarRecursivo(raiz, codigo);
        return (resultado != null) ? resultado.getFilme() : null;
    }
 
    private no buscarRecursivo(no nos, int codigo) {
        if (nos == null) return null;
        if (codigo == nos.getFilme().getCodigo()) return nos;
        if (codigo < nos.getFilme().getCodigo())
            return buscarRecursivo(nos.getEsquerdo(), codigo);
        return buscarRecursivo(nos.getDireito(), codigo);
    }
 
    // ================================================================
    // 3. REMOVER
    // ================================================================
    public boolean remover(int codigo) {
        if (buscar(codigo) == null) return false;
        raiz = removerRecursivo(raiz, codigo);
        return true;
    }
 
    private no removerRecursivo(no nos, int codigo) {
        if (nos == null) return null;
 
        if (codigo < nos.getFilme().getCodigo()) {
            nos.setEsquerdo(removerRecursivo(nos.getEsquerdo(), codigo));
        } else if (codigo > nos.getFilme().getCodigo()) {
            nos.setDireito(removerRecursivo(nos.getDireito(), codigo));
        } else {
            // Nó encontrado — três casos:
            // Caso 1: sem filhos
            if (nos.getEsquerdo() == null && nos.getDireito() == null)
                return null;
 
            // Caso 2: um filho
            if (nos.getEsquerdo() == null) return nos.getDireito();
            if (nos.getDireito()  == null) return nos.getEsquerdo();
 
            // Caso 3: dois filhos — substitui pelo sucessor in-order (menor da subárvore direita)
            no sucessor = menorNoRecursivo(nos.getDireito());
            nos.setFilme(sucessor.getFilme());
            nos.setDireito(removerRecursivo(nos.getDireito(), sucessor.getFilme().getCodigo()));
        }
        return nos;
    }
 
    // ================================================================
    // 4. EXIBIR EM ORDEM (in-order: esq → raiz → dir)
    // ================================================================
    public boolean exibirEmOrdem() {
        if (raiz == null) return false;
        cabecalhoTabela();
        emOrdemRecursivo(raiz);
        return true;
    }
 
    private void emOrdemRecursivo(no nos) {
        if (nos == null) return;
        emOrdemRecursivo(nos.getEsquerdo());
        System.out.println(nos.getFilme().toLinhaTabela());
        emOrdemRecursivo(nos.getDireito());
    }
 
    // ================================================================
    // 5. EXIBIR EM PRÉ-ORDEM (raiz → esq → dir)
    // ================================================================
    public boolean exibirPreOrdem() {
        if (raiz == null) return false;
        cabecalhoTabela();
        preOrdemRecursivo(raiz);
        return true;
    }
 
    private void preOrdemRecursivo(no nos) {
        if (nos == null) return;
        System.out.println(nos.getFilme().toLinhaTabela());
        preOrdemRecursivo(nos.getEsquerdo());
        preOrdemRecursivo(nos.getDireito());
    }
 
    // ================================================================
    // 6. EXIBIR EM PÓS-ORDEM (esq → dir → raiz)
    // ================================================================
    public boolean exibirPosOrdem() {
        if (raiz == null) return false;
        cabecalhoTabela();
        posOrdemRecursivo(raiz);
        return true;
    }
 
    private void posOrdemRecursivo(no nos) {
        if (nos == null) return;
        posOrdemRecursivo(nos.getEsquerdo());
        posOrdemRecursivo(nos.getDireito());
        System.out.println(nos.getFilme().toLinhaTabela());
    }
 
    // ================================================================
    // 7. MAIOR CÓDIGO
    // ================================================================
    public Filmes maiorCodigo() {
        if (raiz == null) return null;
        return maiorNoRecursivo(raiz).getFilme();
    }
 
    private no maiorNoRecursivo(no nos) {
        if (nos.getDireito() == null) return nos;
        return maiorNoRecursivo(nos.getDireito());
    }
 
    // ================================================================
    // 8. MENOR CÓDIGO
    // ================================================================
    public Filmes menorCodigo() {
        if (raiz == null) return null;
        return menorNoRecursivo(raiz).getFilme();
    }
 
    private no menorNoRecursivo(no nos) {
        if (nos.getEsquerdo() == null) return nos;
        return menorNoRecursivo(nos.getEsquerdo());
    }
 
    // ================================================================
    // 9. QUANTIDADE DE FILMES
    // ================================================================
    public int quantidade() {
        return quantidadeRecursivo(raiz);
    }
 
    private int quantidadeRecursivo(no nos) {
        if (nos == null) return 0;
        return 1 + quantidadeRecursivo(nos.getEsquerdo())
                 + quantidadeRecursivo(nos.getDireito());
    }
 
    // ================================================================
    // 10. ALTURA DA ÁRVORE
    // ================================================================
    public int altura() {
        return alturaRecursivo(raiz);
    }
 
    private int alturaRecursivo(no nos) {
        if (nos == null) return 0;
        int altEsq = alturaRecursivo(nos.getEsquerdo());
        int altDir = alturaRecursivo(nos.getDireito());
        return 1 + Math.max(altEsq, altDir);
    }
 
    // ================================================================
    // 11. FILMES ACIMA DE DETERMINADA NOTA
    // ================================================================
    public int exibirAcimaDaNota(double nota) {
        cabecalhoTabela();
        return acimaDaNotaRecursivo(raiz, nota);
    }
 
    private int acimaDaNotaRecursivo(no nos, double nota) {
        if (nos == null) return 0;
        int cont = 0;
        if (nos.getFilme().getNota() > nota) {
            System.out.println(nos.getFilme().toLinhaTabela());
            cont = 1;
        }
        return cont
            + acimaDaNotaRecursivo(nos.getEsquerdo(), nota)
            + acimaDaNotaRecursivo(nos.getDireito(), nota);
    }
 
    // ================================================================
    // VERIFICAÇÃO DE VAZIO
    // ================================================================
    public boolean estaVazia() {
        return raiz == null;
    }
 
    // ================================================================
    // AUXILIAR — cabeçalho da tabela
    // ================================================================
    private void cabecalhoTabela() {
        System.out.println();
        System.out.printf("  %-6s %-40s %-20s %-6s %s%n",
            "COD", "NOME", "GENERO", "ANO", "NOTA");
        System.out.println(
            "  " + "-".repeat(80));
    }

}
