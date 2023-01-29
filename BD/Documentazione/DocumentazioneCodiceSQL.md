# Documentazione Codice SQL

---

Questa vuole essere una documentazione al codice sql da accompagnare alla visione e all'uso di quanto scritto per costruire una base dati che componga una biblioteca digitale.

## Documentazione del file: TableCreation.sql

[DropSchema](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L1)→Viene utilizzato questa riga di codice per fare il dump di tutto il database, in particolare per ricreare la base dati da zero.

[CreazioneSchema](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L2)→Viene utilizzata questa riga di codice per la creazione dello schema “mtl” che sta per marciano-tecchia-library.

[Author](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L5-L11)→Creazione della tabella “Author”.

[Series](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L14-L21)→Creazione della tabella “Series”.

[Magazine](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L23-L33)→Creazione della tabella “Magazine”

[Book](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L37-L58)→Creazione della tabella “Book”.

[Article](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/TableCreation.sql#L61-L78)→Creazione della tabella “Article”

---

## Documentazione del file: Views.sql

[Bibliography](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L1-L4)→ Creazione di una vista user-ready riassuntiva delle informazioni principali riguardanti un libro, il suo autore e la sua data di rilascio, ordinati per data di rilascio decrescente.

[History](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L6-L9)→ Creazione di una vista user-ready riassuntiva delle informazioni principali riguardanti un articolo, il suo autore, la sua data di rilascio e il suo editore ordinati per data di rilascio decrescente.

[DigitalGoods](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L11-L18)→ Creazione di una vista riassuntiva per evidenziare tutti gli oggetti fruibili in digitale.

[PaperGoods](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L20-L27)→ Creazione di una vista riassuntiva per evidenziare tutti gli oggetti fruibili in cartaceo.

[AudioGoods](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L29-L36)→Creazione di una vista riassuntiva per evidenziare tutti gli oggetti fruibili in versione audio.

[Presentation](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L38-L40)→ Creazione di una vista user-ready per evidenziare per ogni libro: l’autore e tutte le informazioni riguardanti la pubblicazione del libro stesso.

[Discussion](https://github.com/lorenzotecchia/ProgettoOOBD/blob/02c2dc4dc0c10a1ecd004aec9ed6b2980ba3c8e8/BD/SQLFiles/views.sql#L42-L45)→ Creazione di una vista user-ready per evidenziare per ogni articolo: l’autore e tutte le informazioni riguardanti le discussioni dell’articolo stesso; ordinati per cognome dell’autore.

---

## Documentazione del file: Domains.sql

[ISSN](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/domains.sql#L1-L2)→Dominio che controlla il corretto inserimento del formato degli issn all’interno delle tuple. Secondo il seguente esempio: 1234-5678

[ISBN](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/domains.sql#L4-L5)→Dominio che controlla il corretto inserimento del formato degli isbn all’interno delle tuple. Secondo il seguente esempio: 123-4-5678-234-1

[DOI](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/domains.sql#L7-L8)→Dominio che controlla il corretto inserimento del formato dei doi all’interno delle tuple. Secondo il seguente esempio: 10.1109/5.771073

[Access](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/domains.sql#L10-L12)→ Dominio che controlla il corretto inserimento dei tipi di accesso nelle tuple, sono esclusi caratteri speciali e numeri, inoltre l’attributo non può essere messo a null. Secondo il seguente esempio: Digital.

[Names](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/domains.sql#L14-L15)→ Dominio che controlla il corretto inserimento dei titoli all’interno delle tuple, sono esclusi i caratteri speciali. Secondo il seguente esempio: NomeFantasia 

[Location](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/domains.sql#L17-L18)→Dominio che controlla il corretto inserimento delle location all’interno delle tuple. Devono essere inserite secondo il seguente esempio: Viale dei Platani, 1, Cercola, 80040, Italia.

---

## Documentazione del file: Functions_Triggers.sql

[Validity_ISBN](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/functions_triggers.sql#L1-L32)→Trigger per il controllo di validità del codice ISBN, segue il link all’algoritmo di riferimento.[[1]](https://it.wikipedia.org/wiki/ISBN#Verifica_di_un_codice_ISBN-10)

[Validity_ISSN_S](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/functions_triggers.sql#L34-L68)→ Trigger per il controllo di validità del codice ISSN per le serie, segue il link all’algoritmo di riferimento.[[2]](https://it.wikipedia.org/wiki/ISSN#Validazione_del_codice_di_controllo)

[Validity_ISSN_M](https://github.com/lorenzotecchia/ProgettoOOBD/blob/13b1b81e5a403fe981fcf7016a458db3a17ff69a/BD/SQLFiles/functions_triggers.sql#L70-L104)→ Trigger per il controllo di validità del codice ISSN per le riviste, segue il link all’algoritmo di riferimento.[[2]](https://it.wikipedia.org/wiki/ISSN#Validazione_del_codice_di_controllo)
