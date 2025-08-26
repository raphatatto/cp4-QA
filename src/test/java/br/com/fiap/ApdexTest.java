// Raphaela — RM554983
package br.com.fiap;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class ApdexTest {

    private static final int RM_TOTAL_AMOSTRAS = 554983;

    @BeforeAll
    static void beforeAll() {
        assertTrue(RM_TOTAL_AMOSTRAS > 0, "O Total de Amostras (RM) deve ser > 0");
    }

    @BeforeEach
    void beforeEach() {
        // Preparação comum caso precise
    }

    // -------- Classificações --------

    @DisplayName("1) Excelente")
    @ParameterizedTest
    @CsvSource({"0.94,Excelente","0.97,Excelente","1.00,Excelente"})
    void excelente(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("2) Bom")
    @ParameterizedTest
    @CsvSource({"0.85,Bom","0.90,Bom","0.93,Bom"})
    void bom(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("3) Razoável")
    @ParameterizedTest
    @CsvSource({"0.70,Razoável","0.77,Razoável","0.84,Razoável"})
    void razoavel(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("4) Ruim")
    @ParameterizedTest
    @CsvSource({"0.50,Ruim","0.60,Ruim","0.69,Ruim"})
    void ruim(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("5) Inaceitável")
    @ParameterizedTest
    @CsvSource({"0.00,Inaceitável","0.40,Inaceitável","0.49,Inaceitável"})
    void inaceitavel(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    // -------- Cálculo usando TOTAL = RM --------

    @Test @DisplayName("6) Cálculo Excelente (TOTAL=RM)")
    void calcExcelente() {
        int total = RM_TOTAL_AMOSTRAS;
        double apdex = Apdex.calcularApdex((int)Math.ceil(0.97*total), 0, total);
        assertEquals("Excelente", Apdex.classificar(apdex));
    }

    @Test @DisplayName("7) Cálculo Bom (TOTAL=RM)")
    void calcBom() {
        int total = RM_TOTAL_AMOSTRAS;
        double apdex = Apdex.calcularApdex((int)Math.ceil(0.89*total), 0, total);
        assertEquals("Bom", Apdex.classificar(apdex));
    }

    @Test @DisplayName("8) Cálculo Razoável (TOTAL=RM)")
    void calcRazoavel() {
        int total = RM_TOTAL_AMOSTRAS;
        double apdex = Apdex.calcularApdex((int)Math.ceil(0.77*total), 0, total);
        assertEquals("Razoável", Apdex.classificar(apdex));
    }

    @Test @DisplayName("9) Cálculo Ruim (TOTAL=RM)")
    void calcRuim() {
        int total = RM_TOTAL_AMOSTRAS;
        double apdex = Apdex.calcularApdex((int)Math.ceil(0.60*total), 0, total);
        assertEquals("Ruim", Apdex.classificar(apdex));
    }

    @Test @DisplayName("10) Cálculo Inaceitável (TOTAL=RM)")
    void calcInaceitavel() {
        int total = RM_TOTAL_AMOSTRAS;
        double apdex = Apdex.calcularApdex((int)Math.ceil(0.40*total), 0, total);
        assertEquals("Inaceitável", Apdex.classificar(apdex));
    }

    // -------- Exceções --------

    @Test @DisplayName("11) total <= 0 lança exceção")
    void totalInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Apdex.calcularApdex(1,1,0));
    }

    @Test @DisplayName("12) valores negativos lançam exceção")
    void negativos() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.calcularApdex(-1,0,10)),
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.calcularApdex(0,-1,10))
        );
    }

    @Test @DisplayName("13) S+T > total lança exceção")
    void somaMaiorQueTotal() {
        assertThrows(IllegalArgumentException.class, () -> Apdex.calcularApdex(8,5,10));
    }

    @Test @DisplayName("14) classificar fora de [0,1] lança exceção")
    void classificarForaIntervalo() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.classificar(-0.1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.classificar(1.1))
        );
    }
}
