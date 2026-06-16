
package programavel;
import java.util.Scanner;

import entidades.ArvoreBinaria;
import entidades.Filmes;
public class App {
    private static final Scanner sc = new Scanner(System.in);
 
    // ================================================================
    // ENTRADA SEGURA
    // ================================================================
 
    /** Lê um inteiro dentro do intervalo [min, max]. Rejeita lixo. */
    private static int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            String linha = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(linha);
                if (valor >= min && valor <= max) return valor;
                System.out.printf(
                    "  Valor fora do intervalo! Digite entre %d e %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada invalida! Digite um numero inteiro.");
            }
        }
    }
 
    /** Lê um double dentro do intervalo [min, max]. Aceita vírgula ou ponto. */
    private static double lerDouble(String mensagem, double min, double max) {
        while (true) {
            System.out.print(mensagem);
            String linha = sc.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(linha);
                if (valor >= min && valor <= max) return valor;
                System.out.printf(
                    "  Valor fora do intervalo! Digite entre %.1f e %.1f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  Entrada invalida! Digite um numero decimal.");
            }
        }
    }
 
    /** Lê uma String não-vazia. */
    private static String lerString(String mensagem, int maxLen) {
        while (true) {
            System.out.print(mensagem);
            String linha = sc.nextLine().trim();
            if (!linha.isEmpty()) {
                if (linha.length() <= maxLen) return linha;
                System.out.printf(
                    "  Texto muito longo! Maximo de %d caracteres.%n", maxLen);
            } else {
                System.out.println("  Campo obrigatorio! Nao pode ser vazio.");
            }
        }
    }
 
    // ================================================================
    // MENU
    // ================================================================
    private static void exibirMenu() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║      CATALOGO DE FILMES  —  ARVORE BST       ║");
        System.out.println("  ╠══════════════════════════════════════════════╣");
        System.out.println("  ║  1.  Inserir filme                           ║");
        System.out.println("  ║  2.  Buscar filme                            ║");
        System.out.println("  ║  3.  Remover filme                           ║");
        System.out.println("  ║  4.  Exibir filmes em ordem (in-order)       ║");
        System.out.println("  ║  5.  Exibir filmes em pre-ordem              ║");
        System.out.println("  ║  6.  Exibir filmes em pos-ordem              ║");
        System.out.println("  ║  7.  Filme com maior codigo                  ║");
        System.out.println("  ║  8.  Filme com menor codigo                  ║");
        System.out.println("  ║  9.  Quantidade de filmes                    ║");
        System.out.println("  ║  10. Altura da arvore                        ║");
        System.out.println("  ║  11. Filmes acima de determinada nota        ║");
        System.out.println("  ║  12. Encerrar sistema                        ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
    }
 
    // ================================================================
    // MAIN
    // ================================================================
    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();
        int opcao;
 
        do {
            exibirMenu();
            opcao = lerInteiro("  Opcao: ", 1, 12);
 
            switch (opcao) {
 
                // ------------------------------------------------
                // 1. INSERIR
                // ------------------------------------------------
                case 1 -> {
                    System.out.println("\n  --- INSERIR FILME ---");
                    int codigo = lerInteiro("  Codigo (1 - 9999): ", 1, 9999);
 
                    if (arvore.buscar(codigo) != null) {
                        System.out.println(
                            "  ERRO: Ja existe um filme com o codigo " + codigo + ".");
                        break;
                    }
 
                    String nome   = lerString("  Nome   (max 38 chars): ", 38);
                    String genero = lerString("  Genero (max 18 chars): ", 18);
                    int    ano    = lerInteiro("  Ano    (1888 - 2100): ", 1888, 2100);
                    double nota   = lerDouble("  Nota   (0.0 - 10.0): ", 0.0, 10.0);
 
                    arvore.inserir(new Filmes(codigo, nome, genero, ano, nota));
                }
 
                // ------------------------------------------------
                // 2. BUSCAR
                // ------------------------------------------------
                case 2 -> {
                    System.out.println("\n  --- BUSCAR FILME ---");
                    int codigo = lerInteiro("  Codigo do filme: ", 1, 9999);
                    Filmes f = arvore.buscar(codigo);
                    if (f != null) System.out.println(f);
                    else System.out.println(
                        "  Filme com codigo " + codigo + " nao encontrado.");
                }
 
                // ------------------------------------------------
                // 3. REMOVER
                // ------------------------------------------------
                case 3 -> {
                    System.out.println("\n  --- REMOVER FILME ---");
                    if (arvore.estaVazia()) {
                        System.out.println("  Catalogo vazio.");
                        break;
                    }
                    int codigo = lerInteiro("  Codigo do filme: ", 1, 9999);
                    if (arvore.remover(codigo))
                        System.out.println("  Filme removido com sucesso!");
                    else
                        System.out.println(
                            "  Filme com codigo " + codigo + " nao encontrado.");
                }
 
                // ------------------------------------------------
                // 4. EM ORDEM
                // ------------------------------------------------
                case 4 -> {
                    System.out.println("\n  --- FILMES EM ORDEM (in-order) ---");
                    if (!arvore.exibirEmOrdem())
                        System.out.println("  Catalogo vazio.");
                }
 
                // ------------------------------------------------
                // 5. PRÉ-ORDEM
                // ------------------------------------------------
                case 5 -> {
                    System.out.println("\n  --- FILMES EM PRE-ORDEM ---");
                    if (!arvore.exibirPreOrdem())
                        System.out.println("  Catalogo vazio.");
                }
 
                // ------------------------------------------------
                // 6. PÓS-ORDEM
                // ------------------------------------------------
                case 6 -> {
                    System.out.println("\n  --- FILMES EM POS-ORDEM ---");
                    if (!arvore.exibirPosOrdem())
                        System.out.println("  Catalogo vazio.");
                }
 
                // ------------------------------------------------
                // 7. MAIOR CÓDIGO
                // ------------------------------------------------
                case 7 -> {
                    System.out.println("\n  --- FILME COM MAIOR CODIGO ---");
                    Filmes f = arvore.maiorCodigo();
                    if (f != null) System.out.println(f);
                    else System.out.println("  Catalogo vazio.");
                }
 
                // ------------------------------------------------
                // 8. MENOR CÓDIGO
                // ------------------------------------------------
                case 8 -> {
                    System.out.println("\n  --- FILME COM MENOR CODIGO ---");
                    Filmes f = arvore.menorCodigo();
                    if (f != null) System.out.println(f);
                    else System.out.println("  Catalogo vazio.");
                }
 
                // ------------------------------------------------
                // 9. QUANTIDADE
                // ------------------------------------------------
                case 9 -> System.out.println(
                    "\n  Total de filmes no catalogo: " + arvore.quantidade());
 
                // ------------------------------------------------
                // 10. ALTURA
                // ------------------------------------------------
                case 10 -> System.out.println(
                    "\n  Altura da arvore: " + arvore.altura());
 
                // ------------------------------------------------
                // 11. FILMES ACIMA DE NOTA
                // ------------------------------------------------
                case 11 -> {
                    System.out.println("\n  --- FILMES ACIMA DE NOTA ---");
                    if (arvore.estaVazia()) {
                        System.out.println("  Catalogo vazio.");
                        break;
                    }
                    double nota = lerDouble("  Nota minima (0.0 - 10.0): ", 0.0, 10.0);
                    int total = arvore.exibirAcimaDaNota(nota);
                    System.out.println();
                    if (total == 0)
                        System.out.printf(
                            "  Nenhum filme com nota acima de %.1f.%n", nota);
                    else
                        System.out.printf("  %d filme(s) encontrado(s).%n", total);
                }
 
                // ------------------------------------------------
                // 12. ENCERRAR
                // ------------------------------------------------
                case 12 -> System.out.println("\n  Sistema encerrado. Ate mais!");
 
            }
 
        } while (opcao != 12);
 
        sc.close();
        
    }
}
