package app.models;

public class Planta {
    private String nomeCientifico;
    private double altura; // em metros
    private String tipoPlanta;

    //construtor
    public Planta(String nomeCientifico, double altura, String tipoPlanta) {
        this.nomeCientifico = nomeCientifico;
        this.altura = altura;
        this.tipoPlanta = tipoPlanta;
    }

    //métodos
    public void crescer(double incremento) {
        if (incremento > 0) {
            altura += incremento;
            System.out.println("A planta cresceu " + incremento + " metros. Nova altura: " + altura + " metros.");
        } else {
            System.out.println("Incremento inválido. A planta não pode crescer.");
        }
    }

    public void fazerFotossintese() {
        System.out.println("A planta está realizando fotossíntese...");
    }

    public boolean produzirFlor() {
        if ("Florífera".equalsIgnoreCase(tipoPlanta)) {
            System.out.println("A planta produziu flores!");
            return true;
        } else {
            System.out.println("A planta não é do tipo florífera e não produziu flores.");
            return false;
        }
    }
}
