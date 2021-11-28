package model;

public class PersonajeMelee extends Personaje{
    public int maximoRango;

    public PersonajeMelee(){
        super();
        maximoRango = 2;
    }

    public PersonajeMelee(int vida, int nivel, int daño, int posicion){
        super(vida, nivel, daño, posicion);
        maximoRango = 2;
    }

    public void atacar(Personaje defensor){
        if(maximoRango >= calculoDistanciaEntrePersonajes(defensor.posicion) && !esAliado(defensor.facciones) ){
            defensor.recibeDaño(this);
        }
    }
}
