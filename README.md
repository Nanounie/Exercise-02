Software Engineering
#  Exercise-02

a) Refractoring number 1: Testing what happens if images arent loading

Spezifikation für das Highscore-System im Snake-Spiel

1. Zweck des Systems
Das Highscore-System speichert die besten Punktzahlen (Scores) der Spieler, die das Snake-Spiel gespielt haben. Es ermöglicht es, die Leistung der Spieler zu verfolgen und die höchsten Punktzahlen anzuzeigen. Spieler können nach jedem Spiel sehen, ob sie eine neue Bestleistung erzielt haben und wie sie im Vergleich zu den besten Ergebnissen abschneiden.

2. Datenstruktur
Die Highscores werden als Liste von Ganzzahlen gespeichert, wobei jede Zahl den Punktestand eines Spielers darstellt. Um die besten Ergebnisse anzuzeigen, wird die Liste nach den höchsten Punktzahlen sortiert. Nur die besten 5 Punktzahlen werden gespeichert.

Speicherung der Highscores: Eine Textdatei (highscores.txt), in der jede Zeile einen Punktestand repräsentiert.
Datenstruktur: Die Highscores werden in einer List<Integer> (Java-ArrayList) gespeichert.

3. Funktionen des Systems
Highscore Laden (loadScores):

Lädt die Punktzahlen aus der Datei highscores.txt, wenn das Spiel startet.
Wenn die Datei nicht existiert, wird eine leere Liste initialisiert.
Highscore Speichern (saveScores):

Speichert die aktuellen besten Punktzahlen zurück in die Datei highscores.txt.
Wird automatisch nach jedem neuen Spiel gespeichert.
Punktestand Hinzufügen (addScore):

Fügt den neuen Punktestand des Spielers zur Liste der Highscores hinzu.
Die Liste wird nach den höchsten Punktzahlen sortiert.
Wenn mehr als 5 Punktzahlen in der Liste vorhanden sind, wird der niedrigste entfernt, sodass nur die 5 besten Punktzahlen gespeichert werden.
Top Scores Abrufen (getTopScores):

Gibt die Liste der besten Punktzahlen zurück (maximal 5 Punkte).

4. Benutzeroberfläche

Anzeige der Highscores im Spiel: Nach dem Spielende wird der Spieler auf den Bildschirm die besten Punktzahlen sehen können.
Anzeige der Punktzahlen: Die Punktzahlen werden in absteigender Reihenfolge angezeigt (die höchste Punktzahl oben).
Maximal 5 Punktzahlen: Nur die besten 5 Punktzahlen werden gespeichert und angezeigt.

5. Fehlerbehandlung

Fehler beim Laden der Datei: Falls beim Laden der Highscore-Datei ein Fehler auftritt (z.B. die Datei existiert nicht oder ist beschädigt), wird eine leere Liste verwendet, und das Spiel funktioniert weiterhin.
Fehler beim Speichern der Datei: Falls beim Speichern der Highscores ein Fehler auftritt, wird eine Fehlermeldung ausgegeben, und die Daten werden nicht gespeichert.

6. Teststrategie
   
Unit-Tests für die Highscore-Klasse:
Teste, ob Punktzahlen korrekt hinzugefügt und gespeichert werden.
Vergewissere dich, dass die Liste immer korrekt sortiert ist.
Überprüfe, ob nach dem Hinzufügen einer neuen Punktzahl die Liste der Highscores korrekt bleibt (maximal 5 Scores).
Integrationstests:
Teste das System in Kombination mit dem Snake-Spiel, indem du das Spiel spielst und sicherstellst, dass die Highscores korrekt angezeigt werden und bei Spielende die neuen Punktzahlen gespeichert werden.

7. Persistenz der Daten
Die Highscore-Daten werden in einer einfachen Textdatei gespeichert. Jedes Mal, wenn das Spiel beendet wird und ein neuer Score hinzugefügt wird, wird diese Datei aktualisiert.

Dateipfad: Die Datei wird im Verzeichnis des Spiels unter dem Namen highscores.txt gespeichert.
Dateiformat: Jede Zeile der Datei enthält einen Punktestand. Keine zusätzlichen Informationen oder Formatierungen werden in der Datei gespeichert.

8. Erweiterungsmöglichkeiten
Benutzernamen: Es könnte erweitert werden, um auch den Benutzernamen des Spielers zu speichern, damit die Highscore-Liste die Namen der besten Spieler anzeigt.
Persistente Speicherung im Web: Anstatt nur lokal zu speichern, könnte man das System so erweitern, dass die Highscores auf einem Server gespeichert und über das Internet abgerufen werden.
