# MondoRex
Sito E-Commerce di prodotti per cani.

Progetto di gruppo realizzato per il corso universitario di Tecnologie Software per il Web.
Migrato dalla repository FraPCATempTSW per motivi di consegna.

#Tech stack
Il progetto utilizza i pattern MVC e DAO:
 Per realizzare il lato front-end, sono stati utilizzati HTML, CSS e Javascript, con la libreria JQuery. 
 Per la memorizzazione delle informazioni, si è scelto l'utilizzo di MySQL come DBMS.
 Il sito utilizza Java per servire pagine all'utente, attraverso le Java Servlet, oltre che per la logica applicativa.
 
Vengono adoperate alcune librerie, presenti nella cartella lib, per la creazione di fatture elettroniche in formato PDF e per la connessione al DB.

#Istruzioni per l'installazione
Il progetto è stato testato con Tomcat 10.0, ed è stato utilizzato originalmente come sistema di build Maven.
Le librerie nella cartella lib devono essere aggiunte nella propria cartella tomcat/lib per far funzionare correttamente il progetto.

Partendo da un'installazione di Tomcat, il modo più semplice per far funzionare il progetto è quello di scaricare questo progetto come zip, rinominarlo in modo da cambiare l'estensione da .zip a .war.
Successivamente, copiare il file nella cartella tomcat/webapps.
A questo punto, è sufficiente avviare il server, aprire il proprio browser ed inserire nella barra url: localhost:MondoRex
