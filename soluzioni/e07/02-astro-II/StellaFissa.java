public class StellaFissa extends CorpoCeleste {
  // Una stella fissa è un corpo celeste con posizione non null, velocità pari a
  // zero ed entrambe immutabili; questo fatto è vero in costruzione e mai
  // modificato da alcun metodo; inoltre posizione e velocità possono essere
  // esposti senza rischio in quanto immutabili

  private final Punto posizione, velocità;

  public StellaFissa(final String nome, final int x, final int y, final int z) {
    super(nome);
    posizione = new Punto(x, y, z);
    velocità = new Punto(0, 0, 0);
  }

  @Override
  public void aggiornaPosizione() {
  }

  @Override
  public void aggiornaVelocità(final CorpoCeleste c) {
  }

  @Override
  public String toString() {
    return String.format("Stella fissa, nome: %s, pos: %s", nome, posizione);
  }

  @Override
  public Punto velocità() {
    return velocità;
  }

  @Override
  public Punto posizione() {
    return posizione;
  }

}