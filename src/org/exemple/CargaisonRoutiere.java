package org.exemple;

public class CargaisonRoutiere extends Cargaison {
    public CargaisonRoutiere(double distance) {
        super(distance);
    }
    public double cout(){
        if(getVolume()<380000)
            return 4*getDistance()*getPoids();
        else
            return 6*getDistance()*getPoids();
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
