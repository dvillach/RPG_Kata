package model;

public class PersonajeRanged extends Personaje{
    public int maximoRango;

    public PersonajeRanged(){
        super();
        maximoRango = 20;
    }

    public PersonajeRanged(int vida, int nivel, int daño, int posicion){
        super(vida, nivel, daño, posicion);
        maximoRango = 20;
    }

    public void atacar(Personaje defensor){
        if(maximoRango >= calculoDistanciaEntrePersonajes(defensor.posicion) && !esAliado(defensor.facciones)){
            defensor.recibeDaño(this);
        }
    }
}
