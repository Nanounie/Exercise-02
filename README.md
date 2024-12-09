Software Engineering
#  Exercise-02

a) Refractoring number 1: Testing what happens if images arent loading

Spezifikation für das Highscore-System im Snake-Spiel

1. Zweck des Systems
Das Highscore-System speichert die besten Punktzahlen (Scores) der Spieler, die das Snake-Spiel gespielt haben.
Spieler können nach jedem Spiel sehen, ob sie eine neue Bestleistung erzielt haben und wie sie im Vergleich zu den besten Ergebnissen abschneiden.

2. Datenstruktur
Die Highscores werden als Liste von Ganzzahlen gespeichert, wobei jede Zahl den Punktestand eines Spielers darstellt. Um die besten Ergebnisse anzuzeigen, wird die Liste nach den höchsten Punktzahlen sortiert. Nur die besten 10 Punktzahlen werden gespeichert. Man könnte als Erweiterung noch einen Benutzernamen erstellen. 

Speicherung der Highscores: Eine Textdatei z.B. mit der Hilfe von einem Array, in der jede Zeile einen Punktestand repräsentiert wäre eine weitere Möglichkeit oder um sich mit Leuten auf der ganzen Welt zu vergleichen könnte man auch eine Datenbank einpflegen.

3. Benötigte funktionen des Systems
Highscore Laden:
Lädt die Punktzahlen aus der Datei Textdatei oder Datenbank, wenn das Spiel startet.
Wenn die Datei nicht existiert, wird eine leere Liste initialisiert.

Highscore Speichern:
Speichert die aktuellen besten Punktzahlen zurück in die Textdatei und Datenbank.
Sollte automatisch nach jedem neuen Spiel gespeichert werden.

Punktestand Hinzufügen:
Fügt den neuen Punktestand des Spielers zur Liste der Highscores hinzu.
Die Liste wird nach den höchsten Punktzahlen sortiert.
Wenn mehr als 10 Punktzahlen in der Liste vorhanden sind, wird der niedrigste entfernt, sodass nur die 10 besten Punktzahlen gespeichert werden.

Top Scores Abrufen:
Gibt die Liste der besten Punktzahlen zurück (maximal 10 Punkte).

4. Benutzeroberfläche
Nach dem Spielende wird der Spieler auf den Bildschirm die besten Punktzahlen sehen können.
Die Punktzahlen werden in absteigender Reihenfolge angezeigt (die höchste Punktzahl oben).
Nur die besten 10 Punktzahlen werden gespeichert und angezeigt.

5. Teststrategie
	1. Wurden die Punktzahlen korrekt hinzugefügt und gespeichert
	2. Ist die Liste korrekt sortiert (absteigend)
	3. Ist auch nach einem hinzufügen die Liste korrekt sortiert 

6. Refactoring
Bei Umsetzung sollten die Funktionen ausgelagert werden, für eine bessere Übersicht und Trennung zu dem normalen Snake Spiel.


Spezifikation für das Hindernis-System im Snake-Spiel
1.Ziel der Erweiterung
Das Hindernis-System fügt Hindernisse zum Snake-Spiel hinzu, die das Spielfeld blockieren und die Schlange daran hindern, diese zu durchqueren. Wenn die Schlange mit einem Hindernis kollidiert, endet das Spiel. Das Spiel soll dadurch schwieriger und herausfordernder werden.

2.Datenstruktur
Die Hindernisse werden, wie beim Apfel mit x und y Werten initialisiert und mit einem roten Rechteck dargestellt.

3.Benötigte Funktionen des Systems
Hindernis Initialisieren:
Es werden beim Start des Spiels eine feste Anzahl von Hindernissen auf dem Spielfeld erzeugt (MAX_OBSTACELS).
Die Hindernisse werden in einem Array obstacle_x, obstacle_y gespeichert, und jede Position wird als Punkt gespeichert.

Hindernis Zeichnen (doDrawing):
Diese Funktion zeichnet alle Hindernisse auf dem Spielfeld vgl. Apfel.
Es wird ein rotes Quadrat verwendet, um ein Hindernis darzustellen. Beim Neustart des Spiels werden die Hindernisse neu generiert.

Kollision mit Hindernissen Überprüfen (checkObstacleCollision):
Diese Funktion überprüft, ob die Schlange mit einem der Hindernisse kollidiert.
Wenn der Kopf der Schlange mit einem Hindernis kollidiert, endet das Spiel.

Hindernisse Lokalisieren (locateObstacles):
Diese Funktion wählt zufällig Positionen für die Hindernisse aus. Dabei wird sichergestellt, dass keine Hindernisse auf der Schlange oder auf dem Apfel platziert werden vgl. Apfel.

4.Benutzeroberfläche
Nach jedem Frame wird die Methode doDrawing(?) aufgerufen, um die Hindernisse an ihren Positionen auf dem Spielfeld anzuzeigen.
Hindernisse werden als rote Rechtecke  dargestellt.

5.Mögliche auftretende Fehler
Ob die Schlange nicht durch die Hindernisse hindurchgehen kann.

6.Refactorings
Man könnte die Funktionen auslagern.
