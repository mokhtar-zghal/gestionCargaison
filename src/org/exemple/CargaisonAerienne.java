package org.exemple;

public class CargaisonAerienne extends Cargaison {
    public CargaisonAerienne(double distance) {
        super(distance);
    }
    public double cout(){
        if(getVolume()<80000)
            return 10*getDistance()*getPoids();
        else
            return 12*getDistance()*getPoids();
    }

    public String toString() {
        return super.toString();
    }
}
