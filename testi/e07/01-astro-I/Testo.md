# Simulazioni astronomiche

## Descrizione

Scopo della prova è progettare e implementare una gerarchia di oggetti utili a
rappresentare e simulare il comportamento di un *sistema astronomico* composto
di alcuni *corpi celesti* (come *pianeti* e *stelle fisse*) soggetti alla
reciproca interazione gravitazionale.

La prima entità per cui dovete progettare una *classe* (concreta) è il **punto
tridimensionale** a coordinate intere che sarà utile a determinare sia la
posizione che la velocità dei corpi celesti. La classe, che si deve chiamare
`Punto` deve avere un *costruttore* con tre parametri, corrispondenti alle
coordinate `x`, `y` e `z` (di tipo `int`); il metodo `toString` deve essere
sovrascritto in modo che restituisca una stringa formata dai valori delle tre
coordinate racchiusi tra parentesi e separati da virgola. Ad esempio, il
frammento di codice

    Punto p = new Punto(1, -2, 3);
    System.out.println(p);

deve produrre la stringa

    (1, -2, 3)

nel flusso d'uscita standard (osservate la presenza di uno spazio dopo ogni
virgola).

La classe deve inoltre avere due metodi pubblici di segnatura `Punto somma(Punto
q)` e `Punto sottrai(Punto q)` che restituiscano un nuovo punto ottenuto,
rispettivamente, sommando o sottraendo (coordinata per coordinata) il punto dato `q`
a quello corrente. Infine, la classe deve avere un metodo pubblico di segnatura
`int norma()` che restituisca la somma dei valori assoluti delle coordinate.

### La classe di test

La classe `TestRunner`, il cui sorgente è fornito assieme al testo presente,
può essere utilizzata per validare il comportamento della classe da voi
implementata. Tale classe legge dal flusso di ingresso una sequenza di punti
(dati da terne di interi separati da spazi, o a-capo) e stampa, nel flusso
d'uscita, il punto ottenuto sommando i punti di posto dispari della sequenza e
sottraendo quelli di posto pari e la sua norma.


## Esempio

Eseguendo la classe `TestRunner` e avendo

    10 20 -30
    1 2 -3
    40 50 -60
    4 5 -6
    70 80 -90
    7 8 -9

nel flusso di ingresso, viene emesso

    |(108, 135, -162)| = 405

nel flusso d'uscita. Infatti (ragionando coordinata per coordinata):
10 - 1 + 40 - 4 + 70 - 7 = 108,
20 - 2 + 50 - 5 + 80 - 8 = 135 e -30 + 3 - 60 + 6 -90 + 9 = -162.
