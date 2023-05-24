# MondoRex    <img src="https://github.com/FraPCA/MondoRex/assets/106353047/f4d06705-483a-4273-864f-d7adb3541b3c" width = "64" height = "64"/> 
Sito E-Commerce di prodotti per cani.

Progetto di gruppo realizzato per il corso universitario di Tecnologie Software per il Web.


Migrato dalla repository FraPCATempTSW per motivi di consegna.
# Tech stack


Il progetto utilizza i pattern MVC e DAO:
 Per realizzare il lato front-end, sono stati utilizzati HTML, CSS e Javascript, con la libreria JQuery. 
 Per la memorizzazione delle informazioni, si è scelto l'utilizzo di MySQL come DBMS.
 Il sito utilizza Java per servire pagine all'utente, attraverso le Java Servlet, oltre che per la logica applicativa.
 
 
Vengono adoperate alcune librerie, presenti nella cartella lib, per la creazione di fatture elettroniche in formato PDF e per la connessione al DB.
# Istruzioni per l'installazione


Il progetto è stato testato con Tomcat 10.0, ed è stato utilizzato originalmente come sistema di build Maven.
Le librerie nella cartella lib devono essere aggiunte nella propria cartella tomcat/lib per far funzionare correttamente il progetto.

Prima di far partire il sito, è necessario avere un'installazione di MySQL in esecuzione: si deve eseguire in essa lo script MondoRexDB.sql, contenuto nella cartella db, che creerà automaticamente il database necessario. Questo passo deve essere eseguito una sola volta.
Successivamente, bisogna modificare il file DriverManagerConnectionPool con i dati del proprio utente MySQL in modo da accedere correttamente al database. Il file si trova in src/model . 


Partendo da un'installazione di Tomcat, il modo più semplice per far funzionare il progetto è quello di scaricare questo progetto come zip, rinominarlo in modo da cambiare l'estensione da .zip a .war.
Successivamente, copiare il file nella cartella tomcat/webapps.
A questo punto, è sufficiente avviare il server, aprire il proprio browser ed inserire nella barra url: localhost:8080/MondoRex
