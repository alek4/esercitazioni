import java.util.Objects;

public abstract class CorpoCeleste {
  // Questa classe (astratta) rappresenta corpi celesti.
  // Un corpo celeste ha un nome (non null), questo è vero in costruzione e mai
  // modificato da alcun metodo (dato che il campo è final); inoltre esso ha
  // posizione, velocità dipendenti dall'implementazione (gli unici metodi
  // mutazionali sono aggiornaPosizione e aggiornaVelocità), l'energia è
  // calcolata come da definizione

  public final String nome;

  public CorpoCeleste(final String nome) {
    this.nome = Objects.requireNonNull(nome, "nome non può essere null");
  }

  // EFFECTS: Restituisce l'energia totale, come da definizione
  public final long energia() {
    return posizione().norma() * velocità().norma();
  };

  // EFFECTS: Le implementazioni restituiscono la posizione (non null)
  public abstract Punto velocità();

  // EFFECTS: Le implementazioni restituiscono la velocità (non null)
  public abstract Punto posizione();

  // EFFECTS: Le implementazioni devono garantire che questo metodo modifichi
  //          esclusivamente la posizione di questo corpo celeste
  public abstract void aggiornaPosizione();

  // EFFECTS: Le implementazioni devono garantire che questo metodo modifichi
  //          esclusivamente la velocità di questo corpo celeste a partire dalle
  //          informazioni riguardo al corpo celeste dato come argomento; si assume che c
  //          non sia null, altrimenti sarà sollevata opportuna eccezione e che questo
  //          metodo non invochi alcun metodo mutazionale su c
  public abstract void aggiornaVelocità(final CorpoCeleste c);

}