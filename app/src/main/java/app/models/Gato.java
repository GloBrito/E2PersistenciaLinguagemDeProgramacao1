package app.models;

public class Gato {
    private String nome;
    private int idade;
    private String cor;

    //construtor
    public Gato(String nome, int idade, String cor) {
        this.nome = nome;
        this.idade = idade;
        this.cor = cor;
    }

    //metodos
    public void miar() {
        System.out.println(nome + " está miando: Miau, miau!");
    }

    public void brincar() {
        System.out.println(nome + " está brincando com uma bolinha!");
    }

    public void comer(String comida) {
        System.out.println(nome + " está comendo " + comida + ".");
    }
}
