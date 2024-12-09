package app.models;

public class Disciplina {
    private String nome;
    private int cargaHoraria;
    private String areaConhecimento;

    //construtor
    public Disciplina(String nome, int cargaHoraria, String areaConhecimento) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.areaConhecimento = areaConhecimento;
    }

    //métodos
    public void exibirInformacoes() {
        System.out.println("Disciplina: " + nome);
        System.out.println("Carga Horária: " + cargaHoraria + " horas");
        System.out.println("Área de Conhecimento: " + areaConhecimento);
    }

    public void associarProfessor(String nome) {
        System.out.println("Professor " + nome + " associado à disciplina " + nome + ".");
    }

    public double calcularNotaFinal() {
        //implementação simples para calcular a nota final.
        //nota fixa como exemplo
        return 7.5; //exemplo de nota final
    }
}
