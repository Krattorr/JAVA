import java.lang.reflect.Type;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class TestExercise {


    @Nested
    @DisplayName("Tests de Gat")
    class SobreGat {
        @Test
        @DisplayName("test Gat.vides és privat")
        public void testGatVidesEsPrivate() {
            assertTrue(UtilTests.classHasPrivateField(Gat.class, "vides"),
                    "Cal que vides sigui privada per grantir que no pugui ser modificada malament");
        }

        @Test
        @DisplayName("test Gat.posicio és privat")
        public void testGatPosicioEsPrivate() {
            assertTrue(UtilTests.classHasPrivateField(Gat.class, "posicio"),
                    "Cal que posició sigui privada per grantir que no pugui ser modificada malament");
        }

        @Test
        @DisplayName("test Gat té getters i setters per vides i posicio")
        public void testGatTeAccessors() {
            assertAll(
                    () -> assertTrue(UtilTests.classHasPublicMethod(Gat.class,
                                                      "setPosicio",
                                                      new Type[] {String.class},
                                                      true)),
                    () -> assertTrue(UtilTests.classHasPublicMethod(Gat.class,
                                                      "setVides",
                                                      new Type[] {int.class},
                                                      true)),
                    () -> assertTrue(UtilTests.classHasPublicMethod(Gat.class,
                                                      "getPosicio",
                                                      String.class,
                                                      true)),
                    () -> assertTrue(UtilTests.classHasPublicMethod(Gat.class,
                                                      "getVides",
                                                      int.class,
                                                      true))
                    );
        }
    }

    @Nested
    @DisplayName("Tests de GatRenat")
    class SobreRenat {
        // Test mètodes de consulta
        @ParameterizedTest
        @CsvSource(value={
        "estirat,true,false,false",
        "assegut,false,true,false",
        "dret,false,false,true"
        })
        @DisplayName("test consultes de posició")
        public void testRenatNeixEstirat(String posicio,
                boolean estaEstirat,
                boolean estaAssegut,
                boolean estaDret) {
            GatRenat renat = new GatRenat();
            renat.setPosicio(posicio);
            assertAll(
                    () -> assertEquals(estaEstirat, renat.estaEstirat()),
                    () -> assertEquals(estaAssegut, renat.estaAssegut()),
                    () -> assertEquals(estaDret, renat.estaDret())
                    );
        }

        @Test
        @DisplayName("test Renat neix viu")
        public void testRenatNeixViu() {
            assertTrue(new GatRenat().estaViu(), "Renat ha de néixer viu");
        }

        @Test
        @DisplayName("test Renat pot morir")
        public void testRenatPotMorir() {
            GatRenat renat = new GatRenat();
            renat.setVides(0);
            assertFalse(renat.estaViu(), "Renat no està viu amb 0 vides");
        }


        @Test
        @DisplayName("test Renat quan està dret")
        public void testRenatDret() {
            GatRenat renat = new GatRenat();
            renat.setPosicio("dret");

            assertAll("dret", 
                    () -> assertTrue(renat.estaDret(), "Renat ha d'estar dret"),
                    () -> assertFalse(renat.estaEstirat(), "Renat no pot estar estirat quan està dret"),
                    () -> assertFalse(renat.estaAssegut(), "Renat no pot estar assegut quan està dret")
                    );
        }




        @Test
        @DisplayName("test Renat s'aixeca")
        public void testRenatEstiratADret() {
            GatRenat renat = new GatRenat();
            assertAll("aixecat",
                    () -> assertEquals("m'aixeco", renat.aixecat(), "revisa el missatge de retorn quan se li demana aixecar-se"),
                    () -> assertTrue(renat.estaDret(), "hauria de quedar dret quan se li demana que s'aixequi")
                    );
        }

        @Test
        @DisplayName("test Renat seu")
        public void testRenatEstiratAAssegut() {
            GatRenat renat = new GatRenat();
            assertAll("seu",
                    () -> assertEquals("m'assec", renat.seu(), "revisa el missatge de retorn quan se li demana seure's"),
                    () -> assertTrue(renat.estaAssegut(), "hauria de quedar dret quan se li demana que segui")
                    );
        }

        @Test
        @DisplayName("test Renat s'estira")
        public void testRenatDretAEstirat() {
            GatRenat renat = new GatRenat();
            renat.setPosicio("dret");
            assertAll("estirat",
                    () -> assertEquals("m'estiro", renat.estirat(), "revisa el missatge de retorn quan se li demana estirar-se"),
                    () -> assertTrue(renat.estaEstirat(), "hauria de quedar dret quan se li demana que s'estiri")
                    );
        }

        @Test
        @DisplayName("test Renat no fa res quan ja està estirat i li demanen que s'estiri")
        public void testRenatEstiratAEstirat() {
            GatRenat renat = new GatRenat();
            assertAll("reestirat",
                    () -> assertEquals("no faig res", renat.estirat(), "revisa el missatge de retorn quan no canvia de posició"),
                    () -> assertTrue(renat.estaEstirat(), "hauria de quedar estirat")
                    );
        }

        @Test
        @DisplayName("test Renat no fa res quan ja està dret i li demanen que s'aixequi")
        public void testRenatDretADret() {
            GatRenat renat = new GatRenat();
            renat.setPosicio("dret");
            assertAll("reaixecat",
                    () -> assertEquals("no faig res", renat.aixecat(), "revisa el missatge de retorn quan no canvia de posició"),
                    () -> assertTrue(renat.estaDret(), "hauria de quedar dret")
                    );
        }


        @Test
        @DisplayName("test Renat no fa res quan ja està assegut i li demanen que segui")
        public void testRenatAssegutAAssegut() {
            GatRenat renat = new GatRenat();
            renat.setPosicio("assegut");
            assertAll("reseu",
                    () -> assertEquals("no faig res", renat.seu(), "revisa el missatge de retorn quan no canvia de posició"),
                    () -> assertTrue(renat.estaAssegut(), "hauria de quedar assegut")
                    );
        }

        @Test
        @DisplayName("test GatRenat no declara vides ni posició")
        public void testGatRenatNoDuplicaAtributs() {
            assertAll(
                    () -> assertFalse(UtilTests.classHasField(GatRenat.class, "vides", true),
                    "GatRenat no redeclara vides doncs l'hereta"),
                    () -> assertFalse(UtilTests.classHasField(GatRenat.class, "posicio", true),
                    "GatRenat no redeclara posicio doncs l'hereta")
                    );
        }


        @Test
        @DisplayName("test GatRenat no té getters i setters per vides i posicio")
        public void testGatTeAccessors() {
            assertAll(
                    () -> assertFalse(UtilTests.classHasPublicMethod(GatRenat.class,
                                                      "setPosicio",
                                                      new Type[] {String.class},
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(GatRenat.class,
                                                      "setVides",
                                                      new Type[] {int.class},
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(GatRenat.class,
                                                      "getPosicio",
                                                      String.class,
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(GatRenat.class,
                                                      "getVides",
                                                      int.class,
                                                      true))
                    );
        }
    }
}