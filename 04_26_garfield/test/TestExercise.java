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
        @DisplayName("test Gat.nom és privat")
        public void testGatNomEsPrivate() {
            assertTrue(UtilTests.classHasPrivateField(Gat.class, "nom"),
                    "Cal que nom sigui privada per grantir que no pugui ser modificada malament");
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

        @ParameterizedTest
        @CsvSource(value={
        "estirat,true,false,false",
        "assegut,false,true,false",
        "dret,false,false,true"
        })
        @DisplayName("test consultes de posició")
        public void testGatNeixEstirat(String posicio,
                boolean estaEstirat,
                boolean estaAssegut,
                boolean estaDret) {
            Gat gat = new Gat(null);
            gat.setPosicio(posicio);
            assertAll(
                    () -> assertEquals(estaEstirat, gat.estaEstirat()),
                    () -> assertEquals(estaAssegut, gat.estaAssegut()),
                    () -> assertEquals(estaDret, gat.estaDret())
                    );
        }

        @Test
        @DisplayName("test Gat neix viu")
        public void testGatNeixViu() {
            assertTrue(new Gat(null).estaViu(), "Gat ha de néixer viu");
        }

        @Test
        @DisplayName("test Gat pot morir")
        public void testGatPotMorir() {
            Gat gat = new Gat(null);
            gat.setVides(0);
            assertFalse(gat.estaViu(), "Gat no està viu amb 0 vides");
        }

        @Test
        @DisplayName("test Gat quan està dret")
        public void testGatDret() {
            Gat gat = new Gat(null);
            gat.setPosicio("dret");

            assertAll("dret", 
                    () -> assertTrue(gat.estaDret(), "Gat ha d'estar dret"),
                    () -> assertFalse(gat.estaEstirat(), "Gat no pot estar estirat quan està dret"),
                    () -> assertFalse(gat.estaAssegut(), "Gat no pot estar assegut quan està dret")
                    );
        }

        @Test
        @DisplayName("test Gat s'aixeca")
        public void testGatEstiratADret() {
            Gat gat = new Gat(null);
            assertAll("aixecat",
                    () -> assertEquals("m'aixeco", gat.aixecat(), "revisa el missatge de retorn quan se li demana aixecar-se"),
                    () -> assertTrue(gat.estaDret(), "hauria de quedar dret quan se li demana que s'aixequi")
                    );
        }

        @Test
        @DisplayName("test Gat seu")
        public void testGatEstiratAAssegut() {
            Gat gat = new Gat(null);
            assertAll("seu",
                    () -> assertEquals("m'assec", gat.seu(), "revisa el missatge de retorn quan se li demana seure's"),
                    () -> assertTrue(gat.estaAssegut(), "hauria de quedar dret quan se li demana que segui")
                    );
        }

        @Test
        @DisplayName("test Gat s'estira")
        public void testGatDretAEstirat() {
            Gat gat = new Gat(null);
            gat.setPosicio("dret");
            assertAll("estirat",
                    () -> assertEquals("m'estiro", gat.estirat(), "revisa el missatge de retorn quan se li demana estirar-se"),
                    () -> assertTrue(gat.estaEstirat(), "hauria de quedar dret quan se li demana que s'estiri")
                    );
        }

        @Test
        @DisplayName("test Gat no fa res quan ja està estirat i li demanen que s'estiri")
        public void testGatEstiratAEstirat() {
            Gat gat = new Gat(null);
            assertAll("reestirat",
                    () -> assertEquals("no faig res", gat.estirat(), "revisa el missatge de retorn quan no canvia de posició"),
                    () -> assertTrue(gat.estaEstirat(), "hauria de quedar estirat")
                    );
        }

        @Test
        @DisplayName("test Gat no fa res quan ja està dret i li demanen que s'aixequi")
        public void testGatDretADret() {
            Gat gat = new Gat(null);
            gat.setPosicio("dret");
            assertAll("reaixecat",
                    () -> assertEquals("no faig res", gat.aixecat(), "revisa el missatge de retorn quan no canvia de posició"),
                    () -> assertTrue(gat.estaDret(), "hauria de quedar dret")
                    );
        }


        @Test
        @DisplayName("test Gat no fa res quan ja està assegut i li demanen que segui")
        public void testGatAssegutAAssegut() {
            Gat gat = new Gat(null);
            gat.setPosicio("assegut");
            assertAll("reseu",
                    () -> assertEquals("no faig res", gat.seu(), "revisa el missatge de retorn quan no canvia de posició"),
                    () -> assertTrue(gat.estaAssegut(), "hauria de quedar assegut")
                    );
        }

        @Test
        @DisplayName("test Gat no ofereix setter pel nom")
        public void gatSenseSetterPerNom() {
            assertFalse(UtilTests.classHasPublicMethod(Gat.class,
                                                      "setNom",
                                                      new Type[] {String.class},
                                                      true));
        }

        @Test
        @DisplayName("test Gat es diu anònim quan nom no vàlid")
        public void gatAnonim() {
            assertAll(
                    () -> assertEquals("anònim", new Gat(null).getNom()),
                    () -> assertEquals("anònim", new Gat("").getNom()),
                    () -> assertEquals("anònim", new Gat("   ").getNom()),
                    () -> assertEquals("anònim", new Gat(null, 5).getNom()),
                    () -> assertEquals("anònim", new Gat("", 5).getNom()),
                    () -> assertEquals("anònim", new Gat("   ", 5).getNom()),
                    () -> assertEquals("anònim", new Gat(null, "dret").getNom()),
                    () -> assertEquals("anònim", new Gat("", "dret").getNom()),
                    () -> assertEquals("anònim", new Gat("   ", "dret").getNom()),
                    () -> assertEquals("anònim", new Gat(null, 5, "dret").getNom()),
                    () -> assertEquals("anònim", new Gat("", 5, "dret").getNom()),
                    () -> assertEquals("anònim", new Gat("   ", 5, "dret").getNom())
                    );
            }

        @Test
        @DisplayName("test Gat no ofereix constructor per defecte")
        public void gatSenseDefaultConstructor() {
            assertFalse(UtilTests.classHasPublicDefaultConstructor(Gat.class));
        }

    }

    @Nested
    @DisplayName("Tests de Garfield")
    class SobreGarfield {

        @Test
        @DisplayName("test Garfield neix com cal")
        public void testGarfieldEsDiuGarfield() {
            Garfield garfield = new Garfield();
            assertAll(
                    () -> assertEquals("Garfield", garfield.getNom()),
                    () -> assertEquals(9, garfield.getVides()),
                    () -> assertEquals("estirat", garfield.getPosicio())
                    );
        }

        @ParameterizedTest
        @CsvSource(value={ "nom", "vides", "posicio" })
        @DisplayName("test Garfield no redeclara els atributs de Gat")
        public void testGarfieldNoDuplicaAtributs(String atribut) {
            assertFalse(UtilTests.classHasField(Garfield.class, atribut, true),
                    "Garfield no redeclara " + atribut + " doncs l'hereta");
        }

        @Test
        @DisplayName("test Garfield no té getters i setters per vides, posicio ni nom")
        public void testGarfieldTeAccessors() {
            assertAll(
                    () -> assertFalse(UtilTests.classHasPublicMethod(Garfield.class,
                                                      "setNom",
                                                      new Type[] {String.class},
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(Garfield.class,
                                                      "getNom",
                                                      String.class,
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(Garfield.class,
                                                      "setPosicio",
                                                      new Type[] {String.class},
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(Garfield.class,
                                                      "getPosicio",
                                                      String.class,
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(Garfield.class,
                                                      "getVides",
                                                      int.class,
                                                      true))
                    );
        }

        @Test
        @DisplayName("test Garfield no ofereix constructors específics")
        public void gatSenseConstructorsEspecifics() {
            assertEquals(1, UtilTests.numberOfPublicConstructorsInClass(Garfield.class));
        }

        @ParameterizedTest
        @CsvSource(value={
        "estirat,estirat,'Bai Maitea, bai'",
        "assegut,dret,m'aixeco",
        "dret,dret,no faig res"
        })
        @DisplayName("test Garfield quan se li demana aixecar-se")
        public void testGarfieldAixecat(String posicioInicial, String posicioFinal, String missatgeEsperat) {
            Garfield gat = new Garfield();
            gat.setPosicio(posicioInicial);
            String missatgeObtingut = gat.aixecat();
            assertAll(
                () -> assertEquals(missatgeEsperat, missatgeObtingut, "Revisa què ha de dir en Garfield quan estava " + posicioInicial + " i se li demana aixecar-se"),
                () -> assertEquals(posicioFinal, gat.getPosicio(), "Revisa què ha de fer en Garfield quan estava " + posicioInicial + " i se li demana aixecar-se")
                );
        }

        @ParameterizedTest
        @CsvSource(value={
        "estirat,assegut,m'assec",
        "assegut,assegut,no faig res",
        "dret,assegut,m'assec"
        })
        @DisplayName("test Garfield quan se li demana seure's")
        public void testGarfieldSeu(String posicioInicial, String posicioFinal, String missatgeEsperat) {
            Garfield gat = new Garfield();
            gat.setPosicio(posicioInicial);
            String missatgeObtingut = gat.seu();
            assertAll(
                () -> assertEquals(missatgeEsperat, missatgeObtingut, "Revisa què ha de dir en Garfield quan estava " + posicioInicial + " i se li demana seure's"),
                () -> assertEquals(posicioFinal, gat.getPosicio(), "Revisa què ha de fer en Garfield quan estava " + posicioInicial + " i se li demana seure's")
                );
        }

        @ParameterizedTest
        @CsvSource(value={
        "estirat,estirat,no faig res",
        "assegut,estirat,m'estiro",
        "dret,dret,'Bai Maitea, bai'"
        })
        @DisplayName("test Garfield quan se li demana estirar-se")
        public void testGarfieldEstirat(String posicioInicial, String posicioFinal, String missatgeEsperat) {
            Garfield gat = new Garfield();
            gat.setPosicio(posicioInicial);
            String missatgeObtingut = gat.estirat();
            assertAll(
                () -> assertEquals(missatgeEsperat, missatgeObtingut, "Revisa què ha de dir en Garfield quan estava " + posicioInicial + " i se li demana estirar-se"),
                () -> assertEquals(posicioFinal, gat.getPosicio(), "Revisa què ha de fer en Garfield quan estava " + posicioInicial + " i se li demana estirar-se")
                );
        }

        @Test
        @DisplayName("test Garfield té limitades les vides màximes")
        public void testGarfieldLimitVides() {
            Garfield gat = new Garfield();
            gat.setVides(5);
            gat.setVides(-12);
            gat.setVides(100);
            assertEquals(5, gat.getVides(), "Revisa requeriments sobre les vides de Garfield");
        }

    }


    @Nested
    @DisplayName("Tests de GatRenat")
    class SobreRenat {

        @Test
        @DisplayName("test GatRenat es diu Renat neixi com neixi")
        public void testGatRenatEsDiuRenat() {
            assertAll(
                    () -> assertEquals("Renat", new GatRenat().getNom()),
                    () -> assertEquals("Renat", new GatRenat(5).getNom()),
                    () -> assertEquals("Renat", new GatRenat("dret").getNom()),
                    () -> assertEquals("Renat", new GatRenat(5, "dret").getNom())
                    );
        }

        @ParameterizedTest
        @CsvSource(value={ "nom", "vides", "posicio" })
        @DisplayName("test GatRenat no redeclara els atributs de Gat")
        public void testGatRenatNoDuplicaAtributs(String atribut) {
            assertFalse(UtilTests.classHasField(GatRenat.class, atribut, true),
                    "GatRenat no redeclara " + atribut + " doncs l'hereta");
        }

        @Test
        @DisplayName("test GatRenat no té getters i setters per vides, posicio ni nom")
        public void testGatTeAccessors() {
            assertAll(
                    () -> assertFalse(UtilTests.classHasPublicMethod(GatRenat.class,
                                                      "setNom",
                                                      new Type[] {String.class},
                                                      true)),
                    () -> assertFalse(UtilTests.classHasPublicMethod(GatRenat.class,
                                                      "getNom",
                                                      String.class,
                                                      true)),
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