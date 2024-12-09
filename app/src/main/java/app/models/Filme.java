package app.models;

public class Filme {
    private String titulo;
    private String genero;
    private int duracao; // duração em minutos

    // Construtor
    public Filme(String titulo, String genero, int duracao) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
    }

    // Métodos
    public void exibirInformacoes() {
        System.out.println("Título: " + titulo);
        System.out.println("Gênero: " + genero);
        System.out.println("Duração: " + duracao + " minutos");
    }

    public void reproduzir() {
        System.out.println("Reproduzindo o filme \"" + titulo + "\"...");
    }

    public void avaliar(double nota) {
        if (nota >= 0 && nota <= 10) {
            System.out.println("Você avaliou o filme \"" + titulo + "\" com a nota: " + nota);
        } else {
            System.out.println("Nota inválida! A nota deve estar entre 0 e 10.");
        }
    }
}
