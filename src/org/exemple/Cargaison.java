package org.exemple;

import java.util.Vector;

abstract class Cargaison {
    protected Vector<Marchandise> marchandises;
    protected double distance;
    protected static int id;

    public Cargaison(double distance) {
        marchandises = new Vector<Marchandise>();
        this.distance = distance;
        id=id+1;
    }

    public void addMarchandise(Marchandise marchandise) {
        marchandises.add(marchandise);
    }

    public Vector<Marchandise> getMarchandises() {
        return marchandises;
    }
    public double getDistance() {
        return distance;
    }


    public static int getId() {
        return id;
    }


    public Marchandise getMarchandiseByNum(int num) throws MarchandiseIntrouvableException {
        for (Marchandise marchandise : marchandises){
            if(marchandise.getNum()==num)
                return marchandise;
        }
        throw new MarchandiseIntrouvableException();
    }
    public double getVolume(){
        double volume=0;
        for(Marchandise marchandise : marchandises)
        {
            volume=volume+ marchandise.getVolume();
        }
        return volume;
    }
    public double getPoids(){
        double poids=0;
        for(Marchandise marchandise : marchandises)
        {
            poids=poids+ marchandise.getWeight();
        }
        return poids;
    }
    @Override
    public String toString() {
        return "Cargo " +
                " distance='" + distance + '\''+
                " id= "+id;
    }
    abstract double cout();
}
