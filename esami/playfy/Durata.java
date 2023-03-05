import java.util.Objects;

/**
 * Record che rappresenta una durata
 */
public record Durata(int secondi) {

    /**
     * Costruisce una durata partendo dai secondi
     *
     * @param secondi
     * @throws IllegalArgumentException se i secondi sono negativi
     */
    public Durata {
        if (secondi <= 0) throw new IllegalArgumentException("I secondi devono essere positivi");
    }

    public static Durata valueOf(final String durata) {
        if (Objects.requireNonNull(durata, "La durata non puo' essere nulla").isEmpty()) {
            throw new IllegalArgumentException("La durata non puo' essere vuota");
        }

        String[] parti = durata.split(":");
        int numParti = parti.length;

        if (numParti > 3) 
            throw new IllegalArgumentException("La durata non puo' contenere piu' di due ':'");
        
        int ore = numParti < 3 ? 0 : toHMS(parti[numParti - 3], false);
        int minuti = numParti < 2 ? 0 : toHMS(parti[numParti - 2], true);
        int secondi = toHMS(parti[numParti - 1], true);
        return new Durata(3600 * ore + 60 * minuti + secondi);
    }

    private static int toHMS(final String componente, final boolean bounded) {
        if (Objects.requireNonNull(componente, "La componente non puo' essere nulla").isEmpty()) {
            throw new IllegalArgumentException("La componente non puo' essere vuota");
        }

        int value;
        try {
            value = Integer.parseInt(componente);
        } catch (NumberFormatException e) {
            throw new IllegalAccessError("La componente " + componente + " deve essere un numero");
        }

        if (value < 0) {
            throw new IllegalArgumentException("La componente" + componente + "deve essere positiva");
        }

        if (bounded && value > 59) 
            throw new IllegalArgumentException("Il valore della componente " + componente + " deve essere minore di 60");
        
        return value;
    }

    public Durata add(Durata other) {
        Objects.requireNonNull(other, "La durata non puo' essere nulla");
        return new Durata(this.secondi + other.secondi);
    }

    public Durata remove(Durata other) {
        Objects.requireNonNull(other, "La durata non puo' essere nulla");
        return new Durata(this.secondi - other.secondi);
    }

    @Override
    public String toString() {
        int hh = secondi/3600;
        return String.format("%s%02d:%02d", hh > 0 ? hh + ":" : "", (secondi/60) % 60, secondi % 60);
    }
}