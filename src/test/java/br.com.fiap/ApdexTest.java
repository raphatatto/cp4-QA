package br.com.fiap;// Raphaela — RM554983

import br.com.fiap.Apdex;
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
        // Preparação comum aos testes (se necessário)
    }

    // ---------- Testes de classificação por limiar (boundary) ----------

    @DisplayName("1) Classificação: Excelente (limiares)")
    @ParameterizedTest
    @CsvSource({
            "0.94,Excelente",
            "0.97,Excelente",
            "1.00,Excelente"
    })
    void deveClassificarExcelente(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("2) Classificação: Bom (limiares)")
    @ParameterizedTest
    @CsvSource({
            "0.85,Bom",
            "0.90,Bom",
            "0.93,Bom"
    })
    void deveClassificarBom(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("3) Classificação: Razoável (limiares)")
    @ParameterizedTest
    @CsvSource({
            "0.70,Razoável",
            "0.77,Razoável",
            "0.84,Razoável"
    })
    void deveClassificarRazoavel(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("4) Classificação: Ruim (limiares)")
    @ParameterizedTest
    @CsvSource({
            "0.50,Ruim",
            "0.60,Ruim",
            "0.69,Ruim"
    })
    void deveClassificarRuim(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    @DisplayName("5) Classificação: Inaceitável (limiares)")
    @ParameterizedTest
    @CsvSource({
            "0.00,Inaceitável",
            "0.40,Inaceitável",
            "0.49,Inaceitável"
    })
    void deveClassificarInaceitavel(double apdex, String esperado) {
        assertEquals(esperado, Apdex.classificar(apdex));
    }

    // ---------- Testes do cálculo usando TOTAL = RM ----------

    @DisplayName("6) Cálculo: Excelente com TOTAL = RM")
    @Test
    void calcularExcelenteComRM() {
        int total = RM_TOTAL_AMOSTRAS;
        int satisfeitas = (int) Math.ceil(0.97 * total);
        int toleradas = 0;
        double apdex = Apdex.calcularApdex(satisfeitas, toleradas, total);
        assertEquals("Excelente", Apdex.classificar(apdex));
    }

    @DisplayName("7) Cálculo: Bom com TOTAL = RM")
    @Test
    void calcularBomComRM() {
        int total = RM_TOTAL_AMOSTRAS;
        int satisfeitas = (int) Math.ceil(0.89 * total);
        int toleradas = 0;
        double apdex = Apdex.calcularApdex(satisfeitas, toleradas, total);
        assertEquals("Bom", Apdex.classificar(apdex));
    }

    @DisplayName("8) Cálculo: Razoável com TOTAL = RM")
    @Test
    void calcularRazoavelComRM() {
        int total = RM_TOTAL_AMOSTRAS;
        int satisfeitas = (int) Math.ceil(0.77 * total);
        int toleradas = 0;
        double apdex = Apdex.calcularApdex(satisfeitas, toleradas, total);
        assertEquals("Razoável", Apdex.classificar(apdex));
    }

    @DisplayName("9) Cálculo: Ruim com TOTAL = RM")
    @Test
    void calcularRuimComRM() {
        int total = RM_TOTAL_AMOSTRAS;
        int satisfeitas = (int) Math.ceil(0.60 * total);
        int toleradas = 0;
        double apdex = Apdex.calcularApdex(satisfeitas, toleradas, total);
        assertEquals("Ruim", Apdex.classificar(apdex));
    }

    @DisplayName("10) Cálculo: Inaceitável com TOTAL = RM")
    @Test
    void calcularInaceitavelComRM() {
        int total = RM_TOTAL_AMOSTRAS;
        int satisfeitas = (int) Math.ceil(0.40 * total);
        int toleradas = 0;
        double apdex = Apdex.calcularApdex(satisfeitas, toleradas, total);
        assertEquals("Inaceitável", Apdex.classificar(apdex));
    }

    // ---------- Testes de exceções/validações ----------

    @DisplayName("11) Exceção: total <= 0")
    @Test
    void deveLancarExcecaoTotalInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                Apdex.calcularApdex(1, 1, 0));
    }

    @DisplayName("12) Exceção: negativos não permitidos")
    @Test
    void deveLancarExcecaoNegativos() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.calcularApdex(-1, 0, 10)),
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.calcularApdex(0, -1, 10))
        );
    }

    @DisplayName("13) Exceção: S + T > total")
    @Test
    void deveLancarExcecaoSmaisTMaiorQueTotal() {
        assertThrows(IllegalArgumentException.class, () ->
                Apdex.calcularApdex(8, 5, 10));
    }

    @DisplayName("14) Exceção: classificar(apdex) fora de [0,1]")
    @Test
    void deveLancarExcecaoClassificarForaDoIntervalo() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.classificar(-0.1)),
                () -> assertThrows(IllegalArgumentException.class, () -> Apdex.classificar(1.1))
        );
    }
}
