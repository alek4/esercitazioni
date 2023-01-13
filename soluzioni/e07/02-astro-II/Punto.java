import java.util.Objects;

// Un punto è un oggetto immutabile a tre coordinate intere, esse sono pubbliche
// ed immutabili; questo è vero in costruzione e resta tale per assenza di
// metodi mutazionali.

public record Punto(int x, int y, int z) {

  // restituisce un nuovo punto ottenuto sommando questo e il punto dato come
  // argomento (sollevando eccezione qualora quest'ultimo sia null)
  public Punto somma(final Punto q) {
    if (q == null) throw new NullPointerException("q non può essere null");
    //Objects.requireNonNull(q, "q non può essere null");
    return new Punto(x + q.x, y + q.y, z + q.z);
  }

  // restituisce un nuovo punto ottenuto sottraendo da questo il punto dato come
  // argomento (sollevando eccezione qualora quest'ultimo sia null)

  public Punto sottrai(final Punto q) {
    Objects.requireNonNull(q, "q non può essere null");
    return new Punto(x - q.x, y - q.y, z - q.z);
  }

  public int norma() {
    return (x > 0 ? x : -x) + (y > 0 ? y : -y) + (z > 0 ? z : -z);
  }

  @Override
  public String toString() {
    return String.format("(%d, %d, %d)", x, y, z);
  }
}