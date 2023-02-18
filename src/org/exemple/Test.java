package org.exemple;

public class Test {
    public static void main(String[] args)  {
        CargaisonRoutiere cargaison1=new CargaisonRoutiere(200);
        Marchandise marchandise1=new Marchandise(1,1,2);
        Marchandise marchandise2=new Marchandise(2,1,2);
        Marchandise marchandise3=new Marchandise(3,1,2);
        cargaison1.addMarchandise(marchandise1);
        cargaison1.addMarchandise(marchandise2);
        cargaison1.addMarchandise(marchandise3);
        try {
            cargaison1.getMarchandiseByNum(9);
        } catch (MarchandiseIntrouvableException e) {
        }
        cargaison1.getMarchandises().forEach(System.out::println);
        System.out.println("cout de la cargaison ayant l'ID "+cargaison1.getId()+" est: "+cargaison1.cout());

        CargaisonAerienne cargaison2=new CargaisonAerienne(200);
        Marchandise marchandise4=new Marchandise(4,1,2);
        Marchandise marchandise5=new Marchandise(5,1,2);
        Marchandise marchandise6=new Marchandise(6,1,2);
        cargaison2.addMarchandise(marchandise4);
        cargaison2.addMarchandise(marchandise5);
        cargaison2.addMarchandise(marchandise6);
        cargaison2.getMarchandises().forEach(System.out::println);
        System.out.println("cout de la cargaison ayant l'ID "+cargaison2.getId()+" est: "+cargaison2.cout());




    }
}
