package app.models;

public class Catedral {
    private String nome;
    private String localizacao;
    private int capacidade; //em número de pessoas

    //construtor
    public Catedral(String nome, String localizacao, int capacidade) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }

    //métodos
    public void exibirInformacoes() {
        System.out.println("Catedral: " + nome + ", Localização: " + localizacao + ", Capacidade: " + capacidade + " pessoas.");
    }

    public void tocarSinos() {
        System.out.println("Os sinos da catedral estão tocando!");
    }

    public boolean aberto() {
        //catedral fica sempre aberta
        return true;
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
