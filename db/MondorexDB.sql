DROP DATABASE IF EXISTS MondoRex;
CREATE DATABASE MondoRex;
USE MondoRex;

DROP TABLE IF EXISTS utente;
DROP TABLE IF EXISTS prodotto;
DROP TABLE IF EXISTS recensione;
DROP TABLE IF EXISTS collegamento;
DROP TABLE IF EXISTS prodotto_ridondante;
DROP TABLE IF EXISTS ordine;
DROP TABLE IF EXISTS metodo_pagamento;
DROP TABLE IF EXISTS contenimento_ordine;
DROP TABLE IF EXISTS contatto;

CREATE TABLE utente (	
  email varchar(256) primary key,
  nome char(20) not null,
  password varchar(256) not null,
  cognome char(20) not null,
  timestamp_registrazione DATETIME,
  N_punti_fedelta int default 0	
);

CREATE TABLE prodotto (
 codice_prodotto char (13) primary key,
 nome varchar (100) not null,
 prezzo_totale DECIMAL(6,2) AS (ROUND( (((prezzo_netto - ((prezzo_netto / 100)) * percentuale_di_sconto) + ((((prezzo_netto - ((prezzo_netto / 100)) * percentuale_di_sconto)) /100) * IVA))) , 2 )) ,
 prezzo_netto DECIMAL(6,2) not null,
 percentuale_di_sconto int default 0 not null,
 IVA int default 22 not null,
 descrizione_breve varchar(500) not null,
 descrizione_dettagliata varchar(2200) not null,
 quantita_disponibile int not null,
 link_immagine varchar(200) not null default (CONCAT(CONCAT('img/prodotti/', REPLACE(nome, ' ', '')), '.png')),
 brand varchar(50),
 microcategoria varchar(50),
 macrocategoria varchar(50)
 );

INSERT INTO utente values ("adminaccount@email.dominio", "nomeadmin", "ADRex56@", "cognomeadmin", CURRENT_TIMESTAMP, default);
INSERT INTO utente values ("francescopuca@email.dominio","Francesco","passwordFP", "Puca",CURRENT_TIMESTAMP, default);
INSERT INTO utente values ("andreabucci@email.dominio","Andrea","passwordFP", "Bucci",CURRENT_TIMESTAMP, default);
INSERT INTO utente values ("nicolamodugno@email.dominio","Nicola","passwordFP", "Modugno",CURRENT_TIMESTAMP, default);
INSERT INTO utente values ("denyshrynevych@email.dominio","Denys","passwordFP", "Hrynevych",CURRENT_TIMESTAMP, default); 

INSERT INTO prodotto
VALUES
("8019262211573", "Monge Natural Puppy & Junior Salmone e Riso", default, 10.00, default, default, "alimento secco completo per cuccioli di tutte le razze e taglie.", "Monge Natural Puppy & Junior Salmone e Riso è un alimento secco completo per cuccioli di tutte le razze e taglie. La sua formula esclusiva è stata sviluppata per garantire il benessere del cane grazie alla presenza di elementi nutritivi di altissima qualità, frutto della ricerca Made in Italy.", 20, default, "Monge", "Cibosecco", "Cibo"),
("8014902356494", "Acana Classic Red", default, 17.90, default, default, "alimento secco completo per cani adulti di tutte le taglie e in ogni fase di vita.", "Acana Classic Red è un alimento secco completo per cani adulti di tutte le taglie e in ogni fase di vita. Ricco di carni nutrienti, Acana Classic Red è caratterizzato da manzo Angus, agnello allevato ad erba e maiale Yorkshire, tutti cresciuti nei ranch locali.", 20, default, "Acana", "Cibosecco", "Cibo"),
("8019530548125", "Disugual Monoproteico 400 gr", default, 9.30, default, default, "alimento completo e monoproteico adatto ai cani di tutte le taglie con normale livello di attività fisica.", "Disugual Monoproteico 400 gr è un alimento completo e monoproteico adatto ai cani di tutte le taglie con normale livello di attività fisica, formulato pensando a loro e mettendo al primo posto la loro salute, il loro benessere e le loro esigenze nutritive. Preparato esclusivamente con pesce di alta qualità, è un alimento altamente appetibile e digeribile ed adatto anche a cani con problemi di sensibilità alimentare. È privo di conservanti e coloranti aggiunti.", 20, default, "Disugual", "Ciboumido", "Cibo"),
("8019029960106", "Oasy Monoproteico Adult 400 g", default, 10.74, default, default, " alimento umido completo monoproteico per cani adulti di tutte le taglie, a partire da un anno di età.", "Oasy Monoproteico Adult 400 gr è un alimento umido completo monoproteico per cani adulti di tutte le taglie, a partire da un anno di età. La sua formula esclusiva è stata ideata per il giusto mantenimento del fabbisogno energetico del vostro amico a quattro zampe. Questo alimento è realizzato con ingredienti accuratamente selezionati, senza glutine e cereali, senza OGM e Soia e senza sperimentazione sugli animali.", 15, default, "Oasy", "Ciboumido", "Cibo"),
("8015964786057", "Pedigree Dentastix Large", default, 3.30, default, default, "ideale per l'igiene di denti e gengive di cani di taglia grande.", "Pedigree Dentastix Large è lo snack a forma ad X, ideale per l'igiene di denti e gengive di cani di taglia grande. Se somministrato quotidianamente, questo snack è in grado di ridurre la formazione di tartaro e placca fino all'80%, grazie ai principi attivi ad azione detergente in esso contenuti, e aiuta a prevenire problemi dentali come paradontiti, infiammazioni e alitosi. L'utilizzo quotidiano offre una valida alternativa allo spazzolino!", 10, default, "Pedigree", "Snack", "Cibo"),
("8019768577498", "Lilys Kitchen Snack Treats 70 gr", default, 2.49, default, default, "Lily's Kitchen Treats Snack o The Best Ever Beef Mini Burgers sono degli ottimi snack per cani adulti realizzati con prodotti freschissimi di primissima qualità.", "Lily's Kitchen Treats Snack o The Best Ever Beef Mini Burgers sono degli otttimi snack per cani adulti realizzati con prodotti freschissimi di primissima qualità. Molto ricchi di acidi grassi essenziali, potassio, ferro e vitamine, pieni di bontà ad alto contenuto di fibra essi contengono anche molte vitamine e minerali essenziali come potassio, tiamina, fosforo e anche vitamina E che promuovono il rinnovamento delle cellule sane e il rafforzamento delle ossa. Preparati a vedere il tuo cane allegro: questi mini hamburger sono pieni di carne adeguata e hanno un sapore assolutamente sensazionale.", 15, default, "LilysKitchen", "Snack", "Cibo"),
("8015417596659", "Sacchetti Igienici Neri", default, 1.40, default, default, "I Sacchetti Igienici Yes sono molto resistenti e robusti, in questo modo potrai raccogliere gli escrementi del tuo cane e potrai portarli senza difficoltà al bidone dell'immondizia più vicino.", "I Sacchetti Igienici Yes sono molto resistenti e robusti, in questo modo potrai raccogliere gli escrementi del tuo cane e potrai portarli senza difficoltà al bidone dell'immondizia più vicino. Come utilizzare i Sacchetti Igienici Yes? Rovesca il sacchetto come se fosse un guanto sulla mano, raccogli gli escrementi, rivolta il sacchetto e fagli un bel nodo. Il prodotto è disponibile in pratiche confezioni da 3 rotoli ognuno dei quali ha 20 strappi usa e getta. Colore nero.", 14, default, "Yes", "Igiene", "Igieneecura"),
("8011438920400", "Tappetini Igienici 60x60cm", default, 2.50, default, default, "I tappetini igienici 60x60cm Lovedì per cani sono super assorbenti e grazie alla trama a nido d'ape sono in grado di assorbire velocemente i liquidi senza rischiarne la fuoriuscita.", "I tappetini igienici 60x60cm Lovedì per cani sono super assorbenti e grazie alla trama a nido d'ape sono in grado di assorbire velocemente i liquidi senza rischiarne la fuoriuscita. Le zampe del tuo cane resteranno asciutte e pulite. I tappetini igienici  Lovedì sono adatti per essere usati in ogni luogo: in casa, durante i viaggi e nella fase di addestramento del tuo cucciolo. I tappetini igienici 60x60cm Lovedìi sono anche utili in caso di animali più anziani, ammalati o per le femmine durante il ciclo. Dimensione 60x60cm, nella quantità 10pz e 50 pz.", 10, default, "Lovedi", "Igiene", "Igieneecura"),
("8016754864411", "Cardatore Spazzola per Cane", default, 6.75, default, default, "Cardatore Spazzola per Cane è Ideale per districare tutti i tipi di pelo medio-lungo, renderlo voluminoso e lucente.", "Cardatore Spazzola per Cane è Ideale per districare tutti i tipi di pelo medio-lungo, renderlo voluminoso e lucente. Si consiglia di procedere regolarmente alla spazzolatura del pelo, è piacevole per il pet e svolge un effetto massaggiante sulla cute, oltre a rinforzare il rapporto padrone-pet. Il cardatore per cani di Yes! rimuove lo sporco, polvere e peli superflui.Ideale anche per rimuovere il pelo morto anche negli strati più profondi del manto e sottopelo, il cardatore per cani di Yes è dotato di denti coperti da polpastrelli antigraffio per non danneggiare la cute di Fido.", 7, default, "Yes", "Toelettatura", "Igieneecura"),
("8017777512102", "Tagliaunghie per Cane", default, 13.70, default, default, "Dotato di lame in acciaio inox, presa ergonomica antiscivolo e limitatore di taglio per operare in totale sicurezza durante il suo utilizzo.", "Il Perfect Grooming Cane Tagliaunghie Ideale per unghie di tutte le dimensioni grazie alle diverse varienti. Il tagliaunghie è dotato di lame in acciaio inox, presa ergonomica antiscivolo e limitatore di taglio per operare in totale sicurezza durante il suo utilizzo. Inoltre, la sicura evita le aperture accidentali.", 3, default, "Perfect", "Toelettatura", "Igieneecura"),
("8017982766293", "Advantix Spot on Cane", default, 22.30, default, default, "Advantix Spot on è una confezione da 4 pipette per il trattamento antiparassitario che agisce contro pulci, zecche, zanzare e pappataci.", "Advantix Spot on è una confezione da 4 pipette per il trattamento antiparassitario che agisce contro pulci, zecche, zanzare e pappataci. Le pipette Advantix eliminano pulci e zecche in modo rapido, dopo sole 24 ore dal trattamento e sono facili da applicare. Agiscono tramite un'attività repellente (anti-feeding) che oltre a trattare le infestazioni di pulci, ne previene il manifestarsi nelle successive 4 settimane. Advantix Spot-on funge da insetticida, acaricida a repellente, uccidendo sia le pulci adulte sia gli stadi larvali delle stesse. Questo prodotto riduce quindi il rischio di trasmissione delle malattie come borreliosi, rickettsiosi, ehrlichiosi, leishmaniosi). E' disponibile in diverse varianti a seconda del peso del tuo cane: è studiato sia per cani di taglia medio grande da 10 a 25 kg, da 25 a 40 kg e da 40 a 60 kg, sia per esemplari medio piccoli da 4 a 10 kg e fino a 4 kg in formato da 0,4 ml. Non utilizzare assolutamente nei gatti, e si consiglia l'utilizzo sui cani cuccioli con età superiore a 7 mesi. Prodotto a marchio Elanco.", 8, default, "Advantix", "Antiparassitari", "Igieneecura"),
("8017958558224", "Tri-Act 2-5Kg 6 Pipette", default, 16.50, default, default, "Frontline Tri-Act antiparassitario per cani, 6 pipette, protezione contro pulci, zecche e flebotomi, vettori di Leishmaniosi.", "Frontline Tri-Act è un antiparassitario spot-on per cani che protegge da pulci, zecche e flebotomi, vettori di Leishmaniosi. La soluzione in comode pipette da 0,5 ml ognuna contiene due principi attivi: Fipronil e Permetrina che permette di proteggere il vostro cane dai parassiti ed inoltre è un repellente per zanzare, pappataci e mosche cavalline. Frontline Tri-Act Spot-On per cani di taglia piccola, dai 2 ai 5 kg, offre protezione contro i parassiti ematofagi, causa di prurito e arrossamenti cutanei che possono provocare malattie, anche letali. Agisce per mezzo di una tripla azione che cura, elimina e previene le manifestazioni parassitarie. Le pulci vengono eliminate entro 1 giorno e previene possibili infestazioni da pulci nelle 4 settimane successive all'applicazione. Il tuo cane si libererà delle zecche entro massimo 7 giorni, e quelle già presenti al momento dell'applicazione saranno eliminate nel giro di 48 ore. Frontline Triact respinge anche zanzare (Culex pipiens) per 4 settimane, i pappataci (Plebotomus perniciosus) per almeno 3 settimane e le mosche cavalline (Stomoxys calcitrans) per oltre 5 settimane dall'applicazione. Il prodotto contrasta i vettori di malattie quali la leishmaniosi, la dirofilariosi e la babesiosi. Per una protezione duratura nel tempo è suggerita un'applicazione costante del prodotto. Per un risultato ottimale, assicurarsi che il prodotto sia applicato direttamente sulla pelle del cane e non sul pelo. Non massaggiare. La confezione risparmio con 6 pipette ti permette di proteggere più a lungo il tuo amico a quattro zampe. Il prodotto va applicato in due punti: metà alla base del collo sulle scapole e metà alla base della testa. Può essere usato su qualsiasi razza di cane, ma occorre ricordarsi che tra due trattamenti consecutivi di Frontline Tri-Act devono passare 4 settimane. Frontline Tri-Act è specifico per il cane tra i 2 e i 5 kg, non usare nei gatti. Se si hanno cani che convivono con gatti, si consiglia di separare i cani trattati dai gatti finchè le parti dove è stato applicato il prodotto non risultino asciutte.", 2, default, "Tri-Act", "Antiparassitari", "Igieneecura"),
("8013743984665", "LENIDERM Spuma", default, 13.30, default, default, "Leniderm spuma al latte di avena lava e rinfresca velocemente ed efficacemente senza l’impiego di acqua.", "Leniderm spuma al latte di avena lava e rinfresca velocemente ed efficacemente senza l’impiego di acqua. È un lenitivo delle manifestazioni cutanee derivanti dal prurito e agisce rapidamente e con un’azione decisa. Lucida il pelo e lo rende setoso e brillante. Leniderm evita lo stress termico da bagno, è quindi particolarmente indicato: - per la pulizia dei cuccioli - per gli animali convalescenti - dopo interventi particolari (vaccinazioni, etc.) - per mantenere la pulizia tra una toilettatura e l’altra - durante i periodi freddi. Leniderm spuma detergente è adatta a cani e gatti. Modo d'uso: Agitare la confezione e distribuire uniformemente la spuma detergente sul manto dell’animale. Frizionare contropelo onde far penetrare il prodotto in profondità. Lasciare agire per qualche minuto e spazzolare. Non è necessario risciacquare. Avvertenze: - Conservare fuori dalla portata dei bambini - Evitare il contatto con gli occhi - In caso di ingestione consultare immediatamente il medico e mostrargli il contenitore o l’etichetta   Validità: 36 mesi Contenuto: 200 ml.", 9, default, "Leniderm", "Parafarmacia", "Igieneecura"),
("8016234116126", "Florentero Act", default, 17.60, default, default, "Florentero Act compresse è consigliato per ripristinare una normale ed efficiente flora microbica intestinale dei cani e gatti in tutti i casi di dismicrobismo.", "Florentero Act compresse è consigliato per ripristinare una normale ed efficiente flora microbica intestinale dei cani e gatti in tutti i casi didismicrobismo. Esso può essere causato da: sindromi diarroiche, disturbi cronici della motilità, stress dietetici e climatici, intensa attività fisica e agonistica, cambiamenti ambientali o dietetici, trattamenti farmacologici che alterino la normale composizione della flora batterica intestinale, intossicazioni alimentari e debilitazione organica conseguente a malattie infettive e parassitarie del tratto digerente. Florentero Act è un mangime complementare in compresse per cani e gatti destinato a particolari fini nutrizionali, può essere usato per la riduzione dei disturbi acuti dell'assorbimento intestinale e come supporto nutrizionale nei periodo di diarrea acuta e nei periodi successivi di convalescenza.", 5, default, "Candioli", "Parafarmacia", "Igieneecura"),
("8012765723627", "Pouf Velvet Grafito", default, 27.90, default, default, "Il Pouf Sfoderabile Velvet Grafito è un accessorio morbido utile per proteggere il tuo cane dai pavimenti di casa, freddi e duri.", "Velvet Grafito è un morbido pouf sul quale il tuo cane adorerà sdraiarsi e riposare comodamente. Il materiale di cui è composta l'imbottitura lo rende il cuscino particolarmente soffice, queste caratteristiche permettono di proteggere Fido dal freddo del pavimento. I materiali utilizzati sono gli stessi con cui si realizzano i divani di casa, per questo motivo l’accessorio per cani Luna&Teo si adatta perfettamente all’arredamento casalingo e al gusto contemporaneo. Il Pouf Velvet è sfoderabile, così da semplificarne la pulizia. Produzione Made in Italy. Il Pouf Sfoderabile Velvet Grafito ha un diametro di 65 cm.", 8, default, "Luna&Teo", "Cucce", "Accessori"),
("8016682727108", "Cuccia Domus", default, 195.00, default, default, "Cuccia per cani in legno di pino nordico Domus Ferplast", "Domus Ferplast è la cuccia per cani da esterno ottimale per il comfort del tuo cane, durevole nel tempo, ben isolata dal terreno, accuratamente trattata per resistere alle intemperie climatiche. Il canile Domus di Ferplast è la casa ideale per il tuo amico a quattro zampe, per lasciarlo vivere all'aria aperta senza fargli mancare un rifugio sicuro. Domus è confortevole, indistruttibile e resistente. Costruita in legno di pino nordico certificato FSC, proveniente cioè da foreste gestite in maniera corretta e responsabile secondo rigorosi standard ambientali, sociali ed economici e altre fonti controllate, è trattata sia internamente che esternamente con una speciale vernice impregnante ecologica atossica e antimuffa, resistente ai raggi U.V. e repellente all'acqua, applicata mediante tecnologia flow-coating che permette una perfetta protezione del prodotto nel tempo. Le tavole di spessore maggiorato sono assemblate con cura al fine di evitare fessure e passaggi d'aria e garantire così protezione totale. I piedini in plastica garantiscono un maggior isolamento da terra mentre il sistema di ventilazione Vent System (escluso nel modello Domus Mini) assicura la massima ventilazione interna evitando il formarsi di umidità. La porta d'ingresso ha un particolare rivestimento antimorso in alluminio. Le cucce Domus sono facili da tenere in ordine e pulite grazie al tetto apribile, fissato saldamente alla cuccia tramite un gancio in metallo.", 1, default, "Ferplast", "Cucce", "Accessori"),
("8019481331999", "Ciotola Antiscivolo Mescal Rosso", default, 4.30, default, default, "in acciaio inox, lavabile in lavastoviglie e dotata di fondo antiscivolo.", "La ciotola rossa per cani Mescal Lovedì in acciaio inox è perfetta per conservare al meglio il pranzetto del tuo cane. Il suo fondo anti-scivolo permette di evitare lo scivolamento e il rovesciamento del contenuto dalla ciotola, garantendo la piena stabilità durante il pasto. Igienica, pratica e facile da pulire perché lavabile in lavastoviglie.", 4, default, "Lovedi", "Ciotole", "Accessori"),
("8017926620440", "Ciotola Foodie Bianco", default, 3.00, default, default, "Il fondo è dotato di gomma antiscivolo per garantire la stabilità e la sicurezza della ciotola.", "Si adatta ad ogni tipo di ambiente ed arredamento. La forma particolare della ciotola è stata appositamente studiata per favorire una corretta postura del collo del cane durante il pasto. L'eleganza della ciotola è data anche dalle rifiniture lucide all'interno e opache all'esterno con effetto soffice al tatto. Il fondo è dotato di gomma antiscivolo per garantire la stabilità e la sicurezza della ciotola. Lavabile in lavatrice. Il materiale utilizzato per realizzare la ciotola Foodie è plastica atossica, senza BPA.", 10, default, "Lovedi", "Ciotole", "Accessori"),
("8016986230921", "Collare Nylon Rosso", default, 2.50, default, default, "Goditi le giornate di sole all'aria aperta con il tuo cane.", "Il tuo cane è particolarmente vivace e forte? Il Collare Nylon per cani è allora l'ideale! Questo accessorio è realizzato in robusto nylon, regolabile in 5 lunghezze e facile da aprire e chiudere. Goditi le giornate di sole all'aria aperta con il tuo cane. Il colore rosso rende questo collare alla moda e non farà passare inosservato il tuo amico a quattro zampe.", 21, default, "Croci", "Guinzagli", "Accessori"),
("8017169765642", "Pettorina Fashion Regolabile Fucsia", default, 16.30, default, default, "Robusta, facile da indossare e comoda per il cane.", "Sei in cerca di una pettorina fashion per cane? La pettorina regolabile in similpelle con fibbia in argento è quello che fa per te! Pettorina color fucsia dallo stile classico e di tendenza, realizzata curando il design nei minimi particolari. Robusta, facile da indossare e comoda per il cane, questa pettorina punta sullo stile unico di Luna & Teo.", 6, default, "Luna&Teo", "Guinzagli", "Accessori"),
("8012711470303", "Frisbee in Gomma Naturale per Cane 22cm", default, 5.27, default, default, "Il gioco frisbee in gomma naturale è un simpatico gioco per cani, anche giovani e sensibili.", "Il gioco frisbee in gomma naturale è un simpatico gioco per cani, anche giovani e sensibili. E' molto sicuro e a basso rischio di lesioni. Ideale per far scorrazzare il tuo cane libero in spazi aperti dove potrà sfogare la sua necessità di corsa. Non lasciare che il tuo Fido giochi da solo. Controllare sempre lo stato di usura del gioco; nel caso fosse consumato o presentasse parti staccate non esitate a sostituirlo. Dimensioni: CM. 22. Colori assortiti non selezionabili.", 19, default, "Trixie", "Giocattoli", "Accessori"),
("8012954073114", "Osso Moderate Dental Chew Nylabone", default, 5.98, 10, default, "Dental Chew osso Moderate di Nylabone è un gioco di intrattenimento studiato appositamente, per l’igiene orale del cane.", "Dental Chew osso Moderate di Nylabone è un gioco di intrattenimento per cane studiato appositamente, per l’igiene orale di Fido: massaggia le gengive e pulisce i denti riducendo la formazione di tartaro e l’incidenza di malattie periodontali, il tutto senza rinunciare al gioco. Questo prodotto non è destinato a essere mangiato o ingerito ma contiene un delizioso aroma di pollo che invoglierà il tuo cane a morderlo, rosicchiarlo e masticarlo, sfogando così ogni ansia e stress. Ideale per cani anziani e poco aggressivi, dental chew si presta perfettamente a una masticazione morbida e prolungata, grazie soprattutto ai materiali di qualità da cui è composto. Disponibile in quattro taglie, per accontentare tutti- Ideale per essere rosicchiato e morsicato- Unione tra gioco e mantenimento di una corretta igiene orale.", 5, default, "Nylabone", "Giocattoli", "Accessori"),
("8012216402745", "Impermeabile Disney Star Wars", default, 17.80, default, default, "capo d'abbigliamento che donerà comfort e protezione al tuo fedele amico.", "L'impermeabile Disney Star Wars è un capo d'abbigliamento per cani regolabile e leggero. Il prodotto è stato realizzato in modo specifico per le esigenze dei cani di piccola e media taglia. Impermeabile dal design esclusivo e dallo stile casual. L'apertura sul retro permette a qualsiasi guinzaglio di essere facilmente agganciato attraverso l'impermeabile alla pettorina o all’anello del collare. Materiale: 100% PVC. Regolabile grazie alla fascia con velcro. Prodotto ufficiale DISNEY. Misure del prodotto: taglia S lunghezza 32 cm e circonferenza busto 50-58 cm.", 7, default, "Disney", "Abbigliamento", "Accessori"),
("8015097669666", "Mantella Sailor Rossa per Cane", default, 14.30, default, default, "mantella anti-vento impermeabile", "La Mantella Sailor Rossa è un impermeabile anti-vento perfetto per uscire all'aria aperta con il proprio cane, durante le brutte giornate. L'indumento firmato Ferplast ripara Fido dal vento e dalla pioggia, garantendo una leggera protezione contro il freddo. La mantella è dotato di una comoda apertura al collo che consentirà il passaggio del guinzaglio per legare il tuo cane. Sailor è dotata di un comodo cappuccio. La Mantellina Sailor Rossa si distingue per essere stata realizzata con un design italiano. Prodotto lavabile a mano con acqua e sapone. La taglia di ciascun articolo si riferisce alla lunghezza in centimetri del dorso del cane (dal garrese all’attacco della coda). Prodotto disponibile nel colore rossa.", 1, default, "Ferplast", "Abbigliamento", "Accessori");

CREATE TABLE recensione(
nome_utente varchar(256) not null,
timestamp_rec DATETIME not null,
valutazione int not null,
descrizione varchar(1000),
primary key (nome_utente, timestamp_rec),
INDEX(timestamp_rec),
INDEX(nome_utente)
);

CREATE TABLE contatto (
 email_utente_contatto varchar(256) not null,
 N_telefono varchar(15) not null,
 regione varchar(255) not null,
 provincia varchar(255) not null,
 via varchar(255) not null,
 CAP char(5) not null,
 Nazione varchar(255) not null,
 primary key (email_utente_contatto, N_telefono),
 INDEX N_telefono_idx (N_telefono ASC) VISIBLE,
 constraint email_utente_contatto
	foreign key (email_utente_contatto)
	references MondoRex.utente (email)
	on delete no action
	on update cascade
 );



INSERT INTO recensione 
VALUES
("francescopuca@email.dominio", "2022-04-29 10:53:11", 5, "Ottimo! Il mio cane lo adora!"),
("andreabucci@email.dominio", "2022-04-29 10:53:14", 1, "Il prodotto Ã¨ stato spedito danneggiato. Non lo raccomando."),
("nicolamodugno@email.dominio", "2022-04-29 10:53:13", 3, "Buon rapporto qualitÃ  prezzo."),

("francescopuca@email.dominio", "2022-05-26 10:53:11", 5, "Fantastico! Il mio cane è super carino con questo impermeabile"),
("andreabucci@email.dominio", "2022-04-30 12:12:14", 4, "Davvero divertente usarlo per giocare, in più è sicuro per la salute del mio cane."),
("nicolamodugno@email.dominio", "2022-05-04 10:53:13", 3, "Il prodotto è buono, ma difficile da lavare"),

("francescopuca@email.dominio", "2022-05-02 23:10:11", 1, "Il mio cane non lo usa praticamente mai, soldi buttati!!!"),
("andreabucci@email.dominio", "2022-05-26 18:09:14", 4, "L'ho usato sul mio cane ed ora non ha più il prurito, soddisfatto del prodotto"),
("nicolamodugno@email.dominio", "2022-05-26 19:53:13", 5, "Il mio Bobby le divora in un istante - TOP!"),

("francescopuca@email.dominio", "2022-05-27 07:10:11", 3, "Carina e comoda da indossare, funzionerebbe anche meglio se il mio cane non si buttasse nelle pozzanghere ;)"),
("andreabucci@email.dominio", "2022-05-26 18:29:24", 2, "Il prezzo è basso, ciò spiega anche la qualità della plastica usata..."),
("nicolamodugno@email.dominio", "2022-04-16 21:00:13", 5, "Davvero un prodotto eccellente, d'altronde 'Pedigree' è una grande marca"),

("francescopuca@email.dominio", "2022-05-01 10:10:10", 5, "Ora il mio cucciolo sta molto meglio, cinque stelle meritatissime"),
("andreabucci@email.dominio", "2022-04-10 18:18:18", 5, "Costerà anche tanto, ma da quando l'ho comprata il mio cane dorme come un ghiro ed è anche più rilassato"),
("nicolamodugno@email.dominio", "2022-05-01 07:07:07", 4, "La adoro! Prima il mio cane odiava farsi spazzolare, adesso sembra quasi divertirsi"),

("francescopuca@email.dominio", "2022-05-13 23:15:10", 4, "Buon prodotto"),
("andreabucci@email.dominio", "2022-04-17 12:09:31", 3, "Non sono un grande fan delle pettorine, ma il mio Smaug non sembra lamentarsi quindi penso vada bene"),
("nicolamodugno@email.dominio", "2022-04-03 18:47:45", 2, "Appena le vede il mio Goku scappa dalla paura, mai usate, come faccio?"),

("francescopuca@email.dominio", "2022-04-23 02:14:10", 5, "Mi trovo benissimo con il prodotto!"),
("andreabucci@email.dominio", "2022-04-27 12:27:31", 4, "Prese su consiglio del mio veterinario, acquisto azzeccato"),
("nicolamodugno@email.dominio", "2022-05-05 13:25:45", 5, "Le uso ormai da tempo ed hanno sempre protetto il mio cane. Le stra-consiglio"),

("francescopuca@email.dominio", "2022-06-01 12:12:10", 1, "Il prezzo è esagerato"),
("andreabucci@email.dominio", "2022-06-05 14:40:31", 5, "Finalmente il mio cane non rovescerà più il cibo sul pavimento!!"),
("nicolamodugno@email.dominio", "2022-06-06 10:30:15", 3, "Non sembra piacere molto ai miei cani, credo che gli alimenti a base di pesce siano più indicati per i gatti"),
("andreabucci@email.dominio", "2022-05-26 19:03:03", 5, "Snack gustosi, li ho assaggiati di persona! Complimenti per il sito, davvero intuitivo e facile da usare, se fosse un progetto universitario gli assegnerei un bel 30!");


CREATE TABLE collegamento(
autore_recensione varchar(256) not null,
timestamp_rec DATETIME not null,
codice_prodotto char(13),
primary key(autore_recensione, timestamp_rec, codice_prodotto),
INDEX autore_recensione_idx (autore_recensione ASC) VISIBLE,
INDEX timestamp_rec_idx (timestamp_rec ASC) VISIBLE,
INDEX codice_prodotto_idx (codice_prodotto ASC) VISIBLE,
CONSTRAINT autore_recensione
	FOREIGN KEY (autore_recensione)
	REFERENCES MondoRex.recensione (nome_utente)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
CONSTRAINT timestamp_rec
	FOREIGN KEY (timestamp_rec)
	REFERENCES MondoRex.recensione (timestamp_rec)
	ON DELETE NO ACTION
	ON UPDATE CASCADE,
CONSTRAINT codice_prodotto
	FOREIGN KEY (codice_prodotto)
	REFERENCES MondoRex.prodotto (codice_prodotto)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

INSERT INTO collegamento
VALUES
("francescopuca@email.dominio", "2022-04-29 10:53:11", "8011438920400"),
("nicolamodugno@email.dominio", "2022-04-29 10:53:13", "8011438920400"),
("andreabucci@email.dominio", "2022-04-29 10:53:14", "8019262211573"),

("francescopuca@email.dominio", "2022-05-26 10:53:11", '8012216402745'),
("andreabucci@email.dominio", "2022-04-30 12:12:14", "8012711470303"),
("nicolamodugno@email.dominio", "2022-05-04 10:53:13", "8012765723627"),

("francescopuca@email.dominio", "2022-05-02 23:10:11", '8012954073114'),
("andreabucci@email.dominio", "2022-05-26 18:09:14", "8013743984665"),
("nicolamodugno@email.dominio", "2022-05-26 19:53:13", "8014902356494"),

("francescopuca@email.dominio", "2022-05-27 07:10:11", '8015097669666'),
("andreabucci@email.dominio", "2022-05-26 18:29:24", "8015417596659"),
("nicolamodugno@email.dominio", "2022-04-16 21:00:13", "8015964786057"),

("francescopuca@email.dominio", "2022-05-01 10:10:10", '8016234116126'),
("andreabucci@email.dominio", "2022-04-10 18:18:18", "8016682727108"),
("nicolamodugno@email.dominio", "2022-05-01 07:07:07", "8016754864411"),

("francescopuca@email.dominio", "2022-05-13 23:15:10", '8016986230921'),
("andreabucci@email.dominio", "2022-04-17 12:09:31", "8017169765642"),
("nicolamodugno@email.dominio", "2022-04-03 18:47:45", "8017777512102"),

("francescopuca@email.dominio", "2022-04-23 02:14:10", '8017926620440'),
("andreabucci@email.dominio", "2022-04-27 12:27:31", "8017958558224"),
("nicolamodugno@email.dominio", "2022-05-05 13:25:45", "8017982766293"),

("francescopuca@email.dominio", "2022-04-23 02:14:10", '8019029960106'),
("andreabucci@email.dominio", "2022-06-05 14:40:31", "8019481331999"),
("nicolamodugno@email.dominio", "2022-06-06 10:30:15", "8019530548125"),
("andreabucci@email.dominio", "2022-05-26 19:03:03", "8019768577498");

CREATE TABLE prodotto_ridondante(
 codice_prodotto varchar (13) not null,
 nome varchar (100) not null,
 quantita int default 1, 
 prezzo_totale DECIMAL(6,2) AS (ROUND(((prezzo_netto - ((prezzo_netto / 100)) * percentuale_di_sconto) + ((((prezzo_netto - ((prezzo_netto / 100)) * percentuale_di_sconto)) /100) * IVA)), 2)),
 prezzo_netto DECIMAL(6,2) not null,
 percentuale_di_sconto int default 0 not null,
 IVA int default 22 not null,
 microcategoria varchar(50),
 macrocategoria varchar(50),
 link_immagine varchar(200) not null,
 primary key (codice_prodotto, nome, quantita, prezzo_netto, percentuale_di_sconto, IVA, microcategoria, macrocategoria, link_immagine)
 );
CREATE TABLE ordine (
 email_utente_ordine varchar (256) not null,
 timestamp_ordine DATETIME not null,
 prezzo_totale DECIMAL(6,2),
fornitore varchar(50),
 uso_PF int not null,
 tel_utente varchar(15) not null,
	primary key(email_utente_ordine, timestamp_ordine),
	KEY email_utente_ordine (email_utente_ordine),
	KEY timestamp_ordine (timestamp_ordine),
	CONSTRAINT email_utente_ordine
	FOREIGN KEY (email_utente_ordine)
	REFERENCES MondoRex.utente (email)
	ON DELETE NO ACTION
	ON UPDATE CASCADE,
    FOREIGN KEY (tel_utente)
	REFERENCES MondoRex.contatto (N_telefono)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
 );
 
 CREATE TABLE metodo_pagamento (
 email_utente_pagamento varchar (256) not null,
 numero char(16) not null,
 CVCCVV int not null,
 tipo varchar(50) not null,
 fornitore varchar(50),
 scadenza date not null,
	primary key(email_utente_pagamento, scadenza, CVCCVV),
	CONSTRAINT email_utente_pagamento
	FOREIGN KEY (email_utente_pagamento)
	REFERENCES MondoRex.utente (email)
	ON DELETE NO ACTION
	ON UPDATE CASCADE
 );
 
 CREATE TABLE contenimento_ordine (
 email_utente_contenimento varchar(256) not null,
 timestamp_ordine_contenimento DATETIME not null,
 codice_prodotto_ridondante varchar(13) not null,
 progressivo_ordine int AUTO_INCREMENT not null,
 primary key (progressivo_ordine),
constraint timestamp_ordine_contenimento
	foreign key (timestamp_ordine_contenimento)
	references ordine (timestamp_ordine)
	on delete cascade
	on update cascade,
 constraint email_utente_contenimento
	foreign key (email_utente_contenimento)
	references MondoRex.utente (email)
	on delete cascade
	on update cascade,
 constraint codice_prodotto_ridondante
	foreign key (codice_prodotto_ridondante)
    references MondoRex.prodotto_ridondante (codice_prodotto)
	on delete no action
	on update cascade
 );
 