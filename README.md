#Projekt na Bazy Danych II

1. Skład zespołu:

   Wojciech Sikora

   Kacper Jankowski

   Krzysztof Wójtowicz

2. Link do projektu.

GitHub: https://github.com/wojsikora/Database-project

3. Propozycja projektu.

W ramach projektu zostanie zrealizowana aplikacja internetowa do obsługi usług wydruku 3D.

Poniżej wypisane zostały główne funkcjonalności aplikacji i założenia projektu.

Z perspektywy klienta:

-   logowanie/rejestrowanie użytkowników, przechowywanie danych teleadresowych;

 -  składanie zamówień:

  -    załączanie pliku w formacie STL,

   -    specyfikowanie parametrów wydruku (m. in. grubość warstwy, rodzaj materiału, wypełnienie),

    -    automatyczna wycena zamówienia na podstawie projektu w pliku STL,

     -   zapis historii wydruków danego klienta,

      -  *możliwość płatności online,

       - wybór opcji dostawy wydruku;

    - składanie zapytania o wycenę:
    -  załączanie plików graficznych itp.,

    - specyfikowanie parametrów wydruku,

    - zapisywanie zapytań w historii klienta.


Z perspektywy osoby zarządzającej:

- zarządzanie treścią na stronie (forum aktualności / galeria zrealizowanych projektów);

-  przegląd złożonych zamówień, możliwość zmian statusu zamówień;

- przegląd zapytań o wycenę, odpowiadanie na wycenę.


Technologie, które planujemy zastosować:

- Java

-  Spring Boot

-   **Spring HATEOAS**

-   **Spring Data REST**

-   **Spring Data JPA (Hibernate)**

-   **MySQL**

-   **Spring Validation**

-   Spring Web

-   Angular

-   Spring Security

- Spring Boot Actuator

-  Maven

Pogrubione elementy mają większe powiązanie z zarządzaniem danymi.

Wybraliśmy Javę z powodu naszego wcześniejszego zaznajomienia z tym językiem oraz MySQL z podobnych względów.

Chcemy zastosować pakiet Spring Boot, ponieważ jest szeroko stosowany w rynku IT, nadaje się do tego problemu i jest ukierunkowany na wspieranie tworzenia aplikacji internetowych korzystających z bazy danych. Udostępniane są w nim narzędzia, które zautomatyzują wiele procesów oraz zapewnią bezpieczeństwo realnych danych.

Planujemy wystawić RESTowe endpointy, które będą obsługiwały frontend w postaci Angulara.

Spring Boot Actuator zapewni monitoring i diagnostykę działającej aplikacji.