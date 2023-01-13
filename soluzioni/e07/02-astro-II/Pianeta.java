import static java.lang.Math.signum;

import java.util.Objects;

public class Pianeta extends CorpoCeleste {
  // Un pianeta è un corpo celeste con posizione velocità non null, questo fatto
  // è vero in costruzione e mai modificato da alcun metodo; sebbene velocità e
  // posizione siano sostituite dai metodi mutazionali, i campi possono essere
  // esposti senza rischio in quanto immutabili
  
  
  private Punto posizione, velocità;

  public Pianeta(final String nome, final int x, final int y, final int z) {
    super(nome);
    posizione = new Punto(x, y, z);
    velocità = new Punto(0, 0, 0);
  }

  @Override
  public void aggiornaPosizione() {
    posizione = posizione.somma(velocità);
  }

  @Override
  public void aggiornaVelocità(final CorpoCeleste c) {
    Objects.requireNonNull(c, "x non può essere null");
    Punto d = posizione.sottrai(c.posizione());
    velocità = velocità.somma(new Punto(-(int)signum(d.x()), -(int)signum(d.y()), -(int)signum(d.z())));
  }

  @Override
  public String toString() {
    return String.format("Pianeta, nome: %s, pos: %s, vel: %s", nome, posizione, velocità);
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