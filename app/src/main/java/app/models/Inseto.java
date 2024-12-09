package app.models;

public class Inseto {
    private String especie;
    private double tamanho;
    private int quantidadePatas;

    //construtor
    public Inseto(String especie, double tamanho, int quantidadePatas) {
        this.especie = especie;
        this.tamanho = tamanho;
        this.quantidadePatas = quantidadePatas;
    }

    //metodo para mover o inseto
    public void mover() {
        System.out.println("O inseto da espécie " + especie + " está se movendo.");
    }

    //metodo para emitir som
    public void emitirSom() {
        System.out.println("O inseto da espécie " + especie + " está emitindo som.");
    }

    //metodo para alimentar o inseto
    public void alimentar(String tipoAlimento) {
        System.out.println("O inseto da espécie " + especie + " está se alimentando de " + tipoAlimento + ".");
    }

    //getters e Setters
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidadePatas() {
        return quantidadePatas;
    }

    public void setQuantidadePatas(int quantidadePatas) {
        this.quantidadePatas = quantidadePatas;
    }
}
