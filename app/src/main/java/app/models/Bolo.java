package app.models;

public class Bolo {
    private String sabor;
    private int tamanho;
    private double peso;

    public Bolo(String sabor, int tamanho, double peso) {
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.peso = peso;
    }

    public boolean assado() {
        return true;
    }

    public void decorar(String estilo) {
        System.out.println("O bolo foi decorado no estilo: " + estilo);
    }

    public void exibirDetalhes() {
        System.out.println("Sabor: " + sabor);
        System.out.println("Tamanho: " + tamanho + " cm");
        System.out.println("Peso: " + peso + " kg");
    }
}
