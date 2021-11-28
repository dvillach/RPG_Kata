package model;

import java.util.ArrayList;

public class Personaje {
    public double vida;
    public int nivel;
    public double daño;
    public boolean estaVivo;
    public int poderCurativo;
    public Posicion posicion;
    public ArrayList<Faccion> facciones;

    public Personaje(){
        this.vida = 1000;
        this.nivel = 1;
        this.daño = 10;
        this.poderCurativo = 10;
        this.posicion = new Posicion(0);
        this.estaVivo = true;
        this.facciones = new ArrayList<>();
    }

    public Personaje(int vida, int nivel, int daño, int posicion){
        this.vida = vida;
        this.nivel = nivel;
        this.daño = daño;
        this.poderCurativo = 10;
        this.posicion = new Posicion(posicion);
        this.estaVivo = (vida > 0 ? true : false);
        this.facciones = new ArrayList<>();
    }

    public void recibeDaño(Personaje atacante){

        double dañoInflingido = calculoDañoPorNivel(atacante);

        if (vida - dañoInflingido <= 0){
            vida = 0;
            estaVivo = false;
        }
        else{
            vida = vida - dañoInflingido;
            estaVivo = true;
        }
    }

    public void curacion(int curacion){
        if (estaVivo){
            this.vida = (this.vida+curacion > 1000 ? 1000 : this.vida+curacion);
        }
    }

    public void curacionAliado(Personaje personaje){
        if (personaje.estaVivo && esAliado(personaje.facciones)){
            personaje.curacion(this.poderCurativo);
        }
    }

    private double calculoDañoPorNivel(Personaje atacante){
        int diferenciaNivel = this.nivel - atacante.nivel;

        if (diferenciaNivel >= 5 ){
            return atacante.daño - (0.5 * atacante.daño);
        }
        else if ( diferenciaNivel <= -5){
            return atacante.daño + (0.5 * atacante.daño);
        }
        else{
            return atacante.daño;
        }
    }

    public int calculoDistanciaEntrePersonajes(Posicion posicionDefensor){
        return Math.abs(posicion.posicionX - posicionDefensor.posicionX);
    }

    public void moverPersonaje(int nuevaPosicion){
        this.posicion.posicionX = nuevaPosicion;
    }

    public void universeFaccion(Faccion nuevaFaccion){
        if (!facciones.contains(nuevaFaccion)){
            facciones.add(nuevaFaccion);
        }
    }

    public void abandonarFaccion(Faccion faccionAbandonar){
        if (facciones.contains(faccionAbandonar)){
            facciones.remove(faccionAbandonar);
        }
    }

    public boolean esAliado(ArrayList<Faccion> faccionesOtroPersonaje){
        return this.facciones.stream().anyMatch(faccion -> faccionesOtroPersonaje.contains(faccion));
    }

}
