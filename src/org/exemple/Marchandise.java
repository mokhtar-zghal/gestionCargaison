package org.exemple;

class Marchandise {
    private int num;
    private double weight;
    private double volume;

    public Marchandise(int num, double weight, double volume) {
        this.num = num;
        this.weight = weight;
        this.volume = volume;
    }

    public int getNum() {
        return num;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Merchandise{" +
                "num=" + num +
                ", weight=" + weight +
                ", volume=" + volume +
                '}';
    }
}



