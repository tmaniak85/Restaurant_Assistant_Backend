# Tomasz Maniak
## Temat: Aplikacja wspierająca codzienne funkcjonowanie restauracji.

### Opis:
Zadaniem aplikacji będzie wspieranie codziennego funkcjonowania restauracji.

Dostęp do aplikacji będzie możliwy z poziomu:
* administratora (kierownika sali)
* kierownika kuchni
* kelnera

Aplikacja zrealizuje swój cel poprzez zarządzanie przepływem informacji oraz monitorowaniem procesu realizacji zamówienia zgodnie z poniższymi etapami:
* przydzielenie klienta do stolika oraz obsługującego go kelnera
* przyjęcie zamówienia i przekazanie go do realizacji
* poinformowanie kelnera o gotowym zamówieniu
* przekazanie informacji o zrealizowaniu zamówienia
* informacja o przyjęciu płatności i zwolnieniu stolika

Aplikacja będzie zapewniać dodatkowo możliwość prowadzenia statystyk dotyczących pracy kelnerów oraz popularności serwowanych dań.

### Założenia techniczne
* prosta aplikacja webowa
* dynamiczne wpieranie w czasie rzeczywistym procesu przyjmowania zamówień
* podział uprawnień pomiędzy kierownika sali, kierownika kuchni oraz kelnerów
* odrębne widoki w zależności od uprawnień danego użytkownika
* wybory zapisywane w bazie danych na poczet prowadzonych statystyk

### Stos technologiczny
* Java
* Gradle
* Spring Boot (Web, JPA, Security)
* Sql Server, H2
* Angular
* UIkit
* Lombok
* Heroku  

### Uruchomienie
* Aby uruchomić aplikację w trybie developerskim tj. korzystając w bazy H2 typu in-memory wybierz w  swoim 
środkowisku IDE w konfiguracji projektu aktywny profil: dev.

* Aby uruchomić aplikację w trybie produkcyjnym tj. korzystającym z bazy MS SQL Server wprowadź w pliku db.properties 
swoje dane dostępowe, a w swoim środowisku IDE w konfiguracji projektu wpisz aktywny profil: prod. 
