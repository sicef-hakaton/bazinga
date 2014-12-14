# Aplikacija Okreni me
## Tim „Bazinga!” - osvojeno 1. mesto

Aplikacija nastala na [SICEF hakatonu](http://sicef.info/hakaton/), 24-časovnom takmičenju u programiranju održanom na Elektronskom fakultetu u Nišu, 22. i 23. novembra 2014. godine. 

Učesnici su imali zadatak da naprave **aplikaciju koja će olakšati studiranje**.


## Članovi tima
* Aleksandar Nikolić
* Petar Slović
* Ivan Nikolić
* Dušan Stanojević


## Opis aplikacije
Cilj aplikacije je da olakša studentima polaganje ispita na fakultetu na interaktivni način. Svako ko se registruje mogao bi da za određeni predmet na fakultetu pogleda već dodate kartice (kvitice) ostalih studenata, a može da dodaje i svoje kartice sa pitanjem i odgovorom. Kada želi da uči, student treba da pročita pitanje sa kartice, prepita se da li zna odgovor, a zatim klikne na dugme koje će okrenuti karticu i pokazati odgovor. 

Student može da sačuva omiljene kartice kako bi ih kasnije lakše pregledao ponovo. Pitanja mogu da se puste u interaktivnom "kviz" modu ili da se nasumično otvaraju u željenom redosledu. Takođe,  svako pitanje se može oceniti na skali od 1 do 5, a ta ocena će kasnije uticati na rangiranje same kartice. Ukoliko smatra da odgovor na kartici nije tačan student ima mogućnost da prijavi grešku kako bi administrori reagovali. 

Student može na lak način da nađe pitanja putem pretrage, kao i da generiše PDF dokumente koji sadrže sve kartice. Kako se kroz celu aplikaciju provlači okretanje kao lajt motiv u animacijama, aplikaciju smo nazvali "okreni me". Aplikacija je prilagođena da radi kako u modernim browser-ima i na svim veličinama ekrana.


## Tehnologije
* Java Development Kit 1.7.0_67
* Apache Maven 3.0.4
* Apache Tomcat 7.0.57
* Spring framework 4.1.1
* JPA 2.0 (Hibernate 4.3.7 kao provajder)
* MySQL 5.6
* C3P0 DB Connection pool 0.9.1.2
* Angular 1.3
* Bootstrap 3.2.0
* Animate.css 3.2.0


## Uputstvo za pokretanje (backend)
In order to compile and run backend part of project you must have following tools:
* Any Java Servlet 3.0 compatible container
* Apache Maven 3.x
* JDK 1.7 or higher
* Relational database (MySQL, Oracle, PostgreSQL, Sybase, DB2...)

If you installed these tools, make sure they are on your path. Next step is to 
specify location of your database and credentials for it. You can find them here:
	- ${project_root}/repository/src/main/resources/database.properties

Afterwards, follow these steps to build and run project:
1. Run `mvn clean install` in project root to build it
2. Go into _${project_root}/rest/target_
3. Pick up _rest.war_ and put it into your servlet container (eg. Apache Tomcat)
4. Start servlet container


## Uputstvo za pokretanje (frontend)
In order to run frontend part of project you must have following tools:
* Install Git: http://git-scm.com/downloads
* Install NodeJS: http://nodejs.org/
* If using Windows, you have to add Node to your PATH 
(http://stackoverflow.com/questions/8768549/node-js-doesnt-recognize-system-path)
* Install Ruby: https://www.ruby-lang.org/en/installation/
* Install Sass i Compass gems
* Now, in terminal type `gem install sass` and then `gem install compass`
* Install npm packages [grunt](http://gruntjs.com/getting-started) and [bower](http://bower.io/#install-bower).

Running the code:
* Clone our repo
* In your terminal navigate to the folder where you want to download project
* Run `git clone ‘INSERT PATH TO REPO HERE’`
* `cd` into the newly created project folder
* Install npm and bower packages with `npm install` and then `bower install`
* Run `grunt serve` and you should be good to go

----------
Više o SICEF-u, organizatoru prvog hakatona na jugu Srbije, možete saznati na sajtu: http://www.sicef.info. 