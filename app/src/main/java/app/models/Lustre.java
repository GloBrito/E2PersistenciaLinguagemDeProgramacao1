package app.models;

public class Lustre {
    private String material;
    private int potencia;
    private String cor;

    // Construtor
    public Lustre(String material, int potencia, String cor) {
        this.material = material;
        this.potencia = potencia;
        this.cor = cor;
    }

    // Método para acender o lustre
    public void acender() {
        System.out.println("O lustre está aceso. Material: " + material + ", Cor: " + cor + ", Potência: " + potencia + "W.");
    }

    // Método para apagar o lustre
    public void apagar() {
        System.out.println("O lustre foi apagado.");
    }

    // Método para ajustar o brilho
    public void ajustarBrilho(int nivel) {
        System.out.println("O brilho do lustre foi ajustado para o nível: " + nivel);
    }

    // Getters e Setters (opcional)
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
