package ro.cristian.activityrandomiser.model;

public class Cuvant{
    private String cuvant;
    private String tipCuvant;

    public Cuvant(){

    }

    public Cuvant(String cuvant, String tipCuvant){
        this.cuvant = cuvant;
        this.tipCuvant = tipCuvant;
    }

    public String getCuvant() {
        return cuvant;
    }

    public void setCuvant(String cuvant) {
        this.cuvant = cuvant;
    }

    public String getTipCuvant() {
        return tipCuvant;
    }

    public void setTipCuvant(String tipCuvant) {
        this.tipCuvant = tipCuvant;
    }
}
