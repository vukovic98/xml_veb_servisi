# Informacioni sistem za pristup informacijama od javnog značaja

# --Projekat iz predmeta XML i Web Servisi 2020/21--


Tim 3
------
SW3-2017	Vuković	Vladimir vladimirvukovic98@hotmail.rs

SW78-2017	Vlaisavljević Ivana	ivanazvucnik@gmail.com

SW9-2017	Madžarević Dušan	ducom98@gmail.com

SW43-2015	Petrović Nikola	petrovicnik96@gmail.com


UPUTSTVA ZA POKRETANJE:

- Službenik:
  - Backend:
    - Projekat se nalazi u sluzbenik-be direktorijumu. Potrebno je otvoriti ga u razvojnom okruženju koje podržava Javu i Spring Boot framework (Eclipse, Spring Tool Suite, IntelliJ...) i pokrenuti kao Spring Boot aplikaciju. Aplikacija se pokreće na portu 8081.
  - Frontend:
    - Projekat se nalazi u sluzbenik-fe direktorijumu. Za razvoj je upotrebljen Angular framework. Potrebno je u terminalu otvorenom u projektnom direktorijumu prvo pokrenuti komandu npm install, a zatim pokrenuti aplikaciju komandom ng serve. Aplikacija se pokreće na portu 4200.
    
- Poverenik:
  - Backend:
    - Projekat se nalazi u sluzbenik-be direktorijumu. Potrebno je otvoriti ga u razvojnom okruženju koje podržava Javu i Spring Boot framework (Eclipse, Spring Tool Suite, IntelliJ...) i pokrenuti kao Spring Boot aplikaciju. Aplikacija se pokreće na portu 8082.
  - Frontend:
    - Projekat se nalazi u sluzbenik-fe direktorijumu. Za razvoj je upotrebljen Angular framework. Potrebno je u terminalu otvorenom u projektnom direktorijumu prvo pokrenuti komandu npm install, a zatim pokrenuti aplikaciju komandom ng serve. Aplikacija se pokreće na portu 4201.
    
- Mail server
  - Backend:
    - Projekat se nalazi u email direktorijumu. Potrebno je otvoriti ga u razvojnom okruženju koje podržava Javu i Spring Boot framework (Eclipse, Spring Tool Suite, IntelliJ...) i pokrenuti kao Spring Boot aplikaciju. Aplikacija se pokreće na portu 8084.
    
    
- Baze:

  Preuzeti Apache TomEE plus aplikativni server: http://tomee.apache.org/download-ng.html
    
  - XML baze
    -  Preuzeti distribuciju eXist XML baze podataka: https://bintray.com/existdb/releases/exist/4.6.1/view
    -  Preimenovati exist-x.x.x.war fajl u exist.war
    -  Deployovati tj. kopirati exist.war u /webapps direktorijum TomEE-a **dvaput**, jednom sa nazivom existS.war, a drugi put sa nazivom existP.war
    -  Pokrenuti aplikativni server i pristupiti dashboardu exist-a: http://localhost:8080/existS za bazu službenika, a http://localhost:8080/existP za bazu poverenika
    
  - RDF baze
    -  Preuzeti distribuciju Apache Jena Fuseki SPARQL servera: https://jena.apache.org/download/index.cgi#apache-jena-fuseki
    -  Raspakovati apache-jena-fuseki-x.x.x.zip fajl
    -  Deployovati tj. kopirati ekstrahovani fuseki.war u /webapps direktorijum TomEE-a **dvaput**, jednom sa nazivom fusekiS.war, a drugi put sa nazivom fusekiP.war
    -  Pokrenuti aplikativni server i pristupiti admin interfejsu Fuseki servera: http://localhost:8080/fusekiS za bazu službenika, a http://localhost:8080/fusekiP za bazu poverenika.
    
  XML baze moraju da imaju određenu strukturu foldera i fajlova koja je data u [db_sluzbenik.txt](db_sluzbenik.txt) i [db_poverenik.txt](db_poverenik.txt) dokumentima.
