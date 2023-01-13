import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class SistemaAstronomico {
  // un sistema astronomico è una insieme ordinato (non null, in cui l'ordine è
  // dato dall'ordine naturale nome del corpo celeste) di corpi celesti (ciascuno
  // non null); questo è vero in costruzione e mai invalidato da alcun metodo
  private final SortedSet<CorpoCeleste> corpiCelesti = new TreeSet<>(
    new Comparator<>() {
      @Override
      public int compare(CorpoCeleste o1, CorpoCeleste o2) {
        return o1.nome.compareTo(o2.nome);
      }
    }
  );

  public void aggiungi(final CorpoCeleste c) { // proteggere per copia
    corpiCelesti.add(Objects.requireNonNull(c, "c non può essere null"));
  }

  // esegue il dato numero di passi di evoluzione (se il numero di passi è
  // negativo solleva opportuna eccezione); la dinamica segue le specifiche, i
  // cicli che aggiornano velocità e posizione possono essere eseguiti in ordine
  // arbitrario date le garanzie sui metodi aggiornaVelocità e aggiornaPosizione
  public void simula(final int passi) {
    if (passi < 0)
      throw new IllegalArgumentException("passi non può essere negativo");
    for (int i = 0; i < passi; i++) {
      for (final CorpoCeleste p : corpiCelesti)
        for (final CorpoCeleste c : corpiCelesti) {
          if (p == c)
            continue;
          p.aggiornaVelocità(c);
        }
      for (final CorpoCeleste c : corpiCelesti)
        c.aggiornaPosizione();
    }
  }

  public long energia() {
    long res = 0;
    for (final CorpoCeleste c : corpiCelesti)
      res += c.energia();
    return res;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer();
    final Iterator<CorpoCeleste> it = corpiCelesti.iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) sb.append('\n');
    }
    return sb.toString();
  }
}
