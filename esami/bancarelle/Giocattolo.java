public class Giocattolo {
  final String nome;
  final String materiale;

  // AF: "nome" di "materiale"
  // IR: nome != dalla stringa vuota
  //     materiale != dalla stringa vuota

  // Constructor for class Giocattolo
  // @param n setta il nome del Giocattolo
  // @param m setta il materiale del Giocattolo
  public Giocattolo(final String n, final String m) {
    if (n == "" || m == "") throw new IllegalArgumentException("il nome o il materiale non possono essere stringhe nulle");
    nome = n;
    materiale = m;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append(nome);
    sb.append(" di ");
    sb.append(materiale);
    return sb.toString();
  }
}
