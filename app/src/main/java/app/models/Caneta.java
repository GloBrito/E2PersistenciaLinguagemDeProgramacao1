package app.models;

public class Caneta {
    //atributos
    private String cor;
    private String marca;
    private double tamanhoPonta;

    //construtor
    public Caneta(String cor, String marca, double tamanhoPonta) {
        this.cor = cor;
        this.marca = marca;
        this.tamanhoPonta = tamanhoPonta;
    }

    //métodos
    public void escrever(String texto) {
        System.out.println("Escrevendo: " + texto);
    }

    public void trocarTinta(String novaCor) {
        this.cor = novaCor;
        System.out.println("A tinta foi trocada para a cor: " + novaCor);
    }

    public boolean verificarNivelTinta() {
        //simula o nível de tinta (retorna true se o nível de tinta for suficiente)
        double nivelTinta = Math.random(); //valor aleatório entre 0.0 e 1.0
        if (nivelTinta > 0.2) {  //se o nível de tinta for maior que 20%, retorna true
            System.out.println("Nível de tinta suficiente.");
            return true;
        } else {
            System.out.println("Nível de tinta baixo.");
            return false;
        }
    }

    //Getters e Setters (para acessar os atributos)
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getTamanhoPonta() {
        return tamanhoPonta;
    }

    public void setTamanhoPonta(double tamanhoPonta) {
        this.tamanhoPonta = tamanhoPonta;
    }
}
