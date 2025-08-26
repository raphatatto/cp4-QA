// Raphaela — RM554983
package br.com.fiap;

public class Apdex {

    /**
     * Calcula o índice Apdex: (Satisfeitas + Toleradas/2) / Total
     */
    public static double calcularApdex(int satisfeitas, int toleradas, int total) {
        if (total <= 0) throw new IllegalArgumentException("Total deve ser > 0");
        if (satisfeitas < 0 || toleradas < 0) throw new IllegalArgumentException("Valores não podem ser negativos");
        if (satisfeitas + toleradas > total) throw new IllegalArgumentException("S + T não pode exceder o total");
        return (satisfeitas + (toleradas / 2.0)) / total;
    }

    /**
     * Classificação (exemplo usado nas aulas):
     * Excelente: 0.94–1.00
     * Bom:       0.85–0.93
     * Razoável:  0.70–0.84
     * Ruim:      0.50–0.69
     * Inaceitável: 0.00–0.49
     */
    public static String classificar(double apdex) {
        if (apdex < 0.0 || apdex > 1.0) throw new IllegalArgumentException("Apdex deve estar entre 0 e 1");
        if (apdex >= 0.94) return "Excelente";
        if (apdex >= 0.85) return "Bom";
        if (apdex >= 0.70) return "Razoável";
        if (apdex >= 0.50) return "Ruim";
        return "Inaceitável";
    }
}
