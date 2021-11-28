import model.Faccion;
import model.Personaje;
import model.PersonajeMelee;
import model.PersonajeRanged;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestRPGKata {


    @Test
    public void creacionPersonajePorDefecto(){
        Personaje personaje = new Personaje();
        Assert.assertEquals(personaje.vida ,1000, 0.0);
        Assert.assertEquals(personaje.nivel,1);
        Assert.assertEquals(personaje.estaVivo,true);
    }


    @Test
    public void creacionPersonajeVivo(){
        Personaje personaje = new Personaje(100, 10, 10, 0);
        Assert.assertEquals(personaje.vida ,100, 0.0);
        Assert.assertEquals(personaje.nivel,10);
        Assert.assertEquals(personaje.estaVivo,true);

    }

    @Test
    public void creacionPersonajeMuerto(){
        Personaje personaje = new Personaje(0, 10, 10, 0);
        Assert.assertEquals(personaje.vida ,0, 0.0);
        Assert.assertEquals(personaje.nivel,10);
        Assert.assertEquals(personaje.estaVivo,false);
    }

    @Test
    public void recibirDaño(){
        Personaje defensor = new Personaje();
        Personaje atacante = new Personaje();
        defensor.recibeDaño(atacante);
        Assert.assertEquals(defensor.vida ,990, 0.0);
        Assert.assertEquals(defensor.estaVivo,true);
    }

    @Test
    public void recibirDañoMortal(){
        Personaje defensor = new Personaje();
        Personaje atacante = new Personaje(1000, 10, 1000, 0);
        defensor.recibeDaño(atacante);
        Assert.assertEquals(defensor.vida ,0, 0.0);
        Assert.assertEquals(defensor.estaVivo,false);
    }

    @Test
    public void curacionMuerto(){
        Personaje personaje = new Personaje(0, 10, 10, 0);
        personaje.curacion(1000);
        Assert.assertEquals(personaje.vida ,0.0, 0.0);
        Assert.assertEquals(personaje.estaVivo,false);
    }

    @Test
    public void curacionVivo(){
        Personaje personaje = new Personaje(100, 10, 10, 0);
        personaje.curacion(1000);
        Assert.assertEquals(personaje.vida ,1000, 0.0);
        Assert.assertEquals(personaje.estaVivo,true);
    }



    @Test
    public void infligirDañoMelee(){
        PersonajeMelee personajeMeleeAtacante = new PersonajeMelee();
        PersonajeMelee personajeMeleeDefensor = new PersonajeMelee();
        personajeMeleeDefensor.moverPersonaje(2);
        personajeMeleeAtacante.atacar(personajeMeleeDefensor);
        Assert.assertEquals(personajeMeleeDefensor.vida ,990, 0.0);
        Assert.assertEquals(personajeMeleeDefensor.estaVivo,true);
    }

    @Test
    public void infligirDañoMortalMelee(){
        PersonajeMelee personajeMeleeAtacante = new PersonajeMelee(1000, 1, 10, 0);
        PersonajeMelee personajeMeleeDefensor = new PersonajeMelee(10, 1, 10, 2);
        personajeMeleeAtacante.atacar(personajeMeleeDefensor);
        Assert.assertEquals(personajeMeleeDefensor.vida ,0, 0.0);
        Assert.assertEquals(personajeMeleeDefensor.estaVivo,false);

    }

    @Test
    public void fallaDañoMeleePorDistancia(){
        PersonajeMelee personajeMeleeAtacante = new PersonajeMelee();
        PersonajeMelee personajeMeleeDefensor = new PersonajeMelee();
        personajeMeleeDefensor.moverPersonaje(3);
        personajeMeleeAtacante.atacar(personajeMeleeDefensor);
        Assert.assertEquals(personajeMeleeDefensor.vida ,1000, 0.0);
        Assert.assertEquals(personajeMeleeDefensor.estaVivo,true);
    }

    @Test
    public void infligirMasDañoPorNivelMelee(){
        PersonajeMelee personajeMeleeAtacante = new PersonajeMelee(1000, 6, 100, 2);
        PersonajeMelee personajeMeleeDefensor = new PersonajeMelee(1000, 1, 10, 0);
        personajeMeleeAtacante.atacar(personajeMeleeDefensor);
        Assert.assertEquals(personajeMeleeDefensor.vida ,850, 0.0);
        Assert.assertEquals(personajeMeleeDefensor.estaVivo,true);
    }

    @Test
    public void infligirMenosDañoPorNivelMelee(){
        PersonajeMelee personajeMeleeAtacante = new PersonajeMelee(1000, 1, 100, 0);
        PersonajeMelee personajeMeleeDefensor = new PersonajeMelee(1000, 6, 10, 2);
        personajeMeleeDefensor.moverPersonaje(2);
        personajeMeleeAtacante.atacar(personajeMeleeDefensor);
        Assert.assertEquals(personajeMeleeDefensor.vida ,950, 0.0);
        Assert.assertEquals(personajeMeleeDefensor.estaVivo,true);
    }


    @Test
    public void infligirDañoRange(){
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged();
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged();
        personajeRangedDefensor.moverPersonaje(20);
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,990, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,true);
    }

    @Test
    public void infligirDañoMortalRange(){
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged(1000, 1, 10, 0);
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged(10, 1, 10, 20);
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,0, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,false);

    }

    @Test
    public void fallaDañoRangePorDistancia(){
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged();
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged();
        personajeRangedDefensor.moverPersonaje(21);
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,1000, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,true);
    }

    @Test
    public void infligirMasDañoPorNivelRange(){
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged(1000, 6, 100, 20);
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged(1000, 1, 10, 0);
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,850, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,true);
    }

    @Test
    public void infligirMenosDañoPorNivelRange(){
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged(1000, 1, 100, 0);
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged(1000, 6, 10, 20);
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,950, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,true);
    }

    @Test
    public void unirseFaccion(){
        Personaje personaje = new Personaje(1000, 1, 100, 0);
        Faccion nuevaFaccion = new Faccion("Ferrienses");
        personaje.universeFaccion(nuevaFaccion);
        Assert.assertEquals(personaje.facciones.size() ,1);
        Assert.assertTrue(personaje.facciones.contains(nuevaFaccion));
    }

    @Test
    public void salirFaccion(){
        Personaje personaje = new Personaje(1000, 1, 100, 0);
        Faccion nuevaFaccion = new Faccion("Ferrienses");
        personaje.universeFaccion(nuevaFaccion);
        Assert.assertEquals(personaje.facciones.size() ,1);
        personaje.abandonarFaccion(nuevaFaccion);
        Assert.assertEquals(personaje.facciones.size() ,0);
    }



    @Test
    public void unirseMismaFaccionDosVeces(){
        Personaje personaje = new Personaje(1000, 1, 100, 0);
        Faccion nuevaFaccion = new Faccion("Ferrienses");
        personaje.universeFaccion(nuevaFaccion);
        personaje.universeFaccion(nuevaFaccion);
        Assert.assertEquals(personaje.facciones.size() ,1);
    }

    @Test
    public void infligirDañoMortalRangeDiferenteFaccion(){
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged(1000, 1, 10, 0);
        personajeRangedAtacante.facciones.add(new Faccion("Ferrienses"));
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged(10, 1, 10, 20);
        personajeRangedDefensor.facciones.add(new Faccion("Javienses"));
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,0, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,false);

    }

    @Test
    public void noInfligirDañoMortalRangeMismaFaccion(){
        Faccion faccion = new Faccion("Ferrienses");
        PersonajeRanged personajeRangedAtacante = new PersonajeRanged(1000, 1, 10, 0);
        personajeRangedAtacante.facciones.add(faccion);
        PersonajeRanged personajeRangedDefensor = new PersonajeRanged(10, 1, 10, 20);
        personajeRangedDefensor.facciones.add(faccion);
        personajeRangedAtacante.atacar(personajeRangedDefensor);
        Assert.assertEquals(personajeRangedDefensor.vida ,10, 0.0);
        Assert.assertEquals(personajeRangedDefensor.estaVivo,true);
    }

    @Test
    public void curarPersonajeAliado(){
        Faccion faccion = new Faccion("Ferrienses");
        PersonajeRanged personajeAliado1 = new PersonajeRanged(1000, 1, 10, 0);
        personajeAliado1.facciones.add(faccion);
        PersonajeRanged personajeAliado2 = new PersonajeRanged(10, 1, 10, 20);
        personajeAliado2.facciones.add(faccion);
        personajeAliado1.curacionAliado(personajeAliado2);
        Assert.assertEquals(personajeAliado2.vida ,20, 0.0);
        Assert.assertEquals(personajeAliado2.estaVivo,true);
    }


}
