package app.models;

public class Pintura {
    private String cor;
    private String tipoTinta;
    private double dimensao;

    //construtor
    public Pintura(String cor, String tipoTinta, double dimensao) {
        this.cor = cor;
        this.tipoTinta = tipoTinta;
        this.dimensao = dimensao;
    }

    //metodo para aplicar tinta
    public void aplicarTinta(String superficie) {
        System.out.println("Aplicando tinta " + cor + " do tipo " + tipoTinta + " na superfície: " + superficie);
    }

    //metodo para misturar cores
    public void misturarCor(String novaCor) {
        System.out.println("Misturando a cor " + cor + " com " + novaCor);
        this.cor = novaCor; // Atualiza a cor
    }

    //metodo para calcular custo
    public double calcularCusto() {
        double custoPorMetro = 5.0; //valor fictício por metro quadrado
        double custoTotal = dimensao * custoPorMetro;
        System.out.println("Custo total para pintar " + dimensao + " m²: R$ " + custoTotal);
        return custoTotal;
    }

    //getters e Setters
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoTinta() {
        return tipoTinta;
    }

    public void setTipoTinta(String tipoTinta) {
        this.tipoTinta = tipoTinta;
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }
}
