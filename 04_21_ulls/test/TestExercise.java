/*
    Unit testing methods for exercise 03_11_show
*/

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class TestExercise {

    // test UllDeGat
    @Test
    @DisplayName("test UllDeGat neix tancat per defecte")
    public void ullDeGatNeixTancatPerDefecte() {
        UllDeGat ull = new UllDeGat();
        assertFalse(ull.estaObert());
    }
    @Test
    @DisplayName("test UllDeGat pot nèixer obert amb constructor específic")
    public void ullDeGatNeixObertAmbConstructorEspecific() {
        UllDeGat ull = new UllDeGat(true);
        assertTrue(ull.estaObert());
    }
    @Test
    @DisplayName("test UllDeGat pot nèixer tancat amb constructor específic")
    public void ullDeGatNeixTancatAmbConstructorEspecific() {
        UllDeGat ull = new UllDeGat(false);
        assertFalse(ull.estaObert());
    }


    // test GatRenat

    @Test
    @DisplayName("test el constructor per defecte deixa els ulls correctes")
    public void quanNeixPerDefecte() {
        GatRenat renat = new GatRenat();
        assertAll(
                () -> assertEquals("estirat", renat.getPosicio()),
                () -> assertFalse(renat.getUllDret().estaObert()),
                () -> assertFalse(renat.getUllEsquerre().estaObert())
                );
    }

    @ParameterizedTest
    @CsvSource(value={
        "estirat,false,false",
        "assegut,true,false",
        "dret,true,true",
    })
    @DisplayName("test el constructor específic deixa els ulls correctes")
    public void quanNeixEspecific(String posicio, boolean esperatDret, boolean esperatEsquerre) {
        GatRenat renat = new GatRenat(posicio);
        assertAll(
                () -> assertEquals(posicio, renat.getPosicio()),
                () -> assertEquals(esperatDret, renat.getUllDret().estaObert()),
                () -> assertEquals(esperatEsquerre, renat.getUllEsquerre().estaObert())
                );
    }

    @Test
    @DisplayName("test s'aixeca amb bons ulls")
    public void aixecat() {
        GatRenat renat = new GatRenat();
        renat.aixecat();
        assertAll(
                () -> assertEquals("dret", renat.getPosicio()),
                () -> assertTrue(renat.getUllDret().estaObert()),
                () -> assertTrue(renat.getUllEsquerre().estaObert())
                );
    }

    @Test
    @DisplayName("test seu amb bons ulls")
    public void seu() {
        GatRenat renat = new GatRenat();
        renat.seu();
        assertAll(
                () -> assertEquals("assegut", renat.getPosicio()),
                () -> assertTrue(renat.getUllDret().estaObert()),
                () -> assertFalse(renat.getUllEsquerre().estaObert())
                );
    }

    @Test
    @DisplayName("test s'estira amb bons ulls")
    public void estirat() {
        GatRenat renat = new GatRenat("dret");
        renat.estirat();
        assertAll(
                () -> assertEquals("estirat", renat.getPosicio()),
                () -> assertFalse(renat.getUllDret().estaObert()),
                () -> assertFalse(renat.getUllEsquerre().estaObert())
                );
    }

    @Test
    @DisplayName("test els ulls són còpies")
    public void ullsSonCopies() {
        GatRenat renat = new GatRenat();
        UllDeGat dret = renat.getUllDret();
        UllDeGat esquerre = renat.getUllEsquerre();
        dret.obret();
        esquerre.obret();
        assertAll(
                () -> assertFalse(renat.getUllDret().estaObert(), "UllDeGat.getUllDret() ha de tornar una còpia"),
                () -> assertFalse(renat.getUllEsquerre().estaObert(), "UllDeGat.getUllEsquerre() ha de tornar una còpia")
                );
    }

}
