<details>
  <summary>Français</summary>

# Projet Chatop

Comme vous pouvez le constater le projet est constitué d'un front-end et d'un back-end. Ce projet a été développé dans le cadre d'une formation où la partie front-end est fourni à l'étudiant pour qu'il puisse développer toute la partie back-end et mettre en place la connexion entre le front-end et le back-end.

Le front est un projet développé sur Angular 14 et le back sur Springboot 2.7.

## Par où commencer ?

Pour la partie back du projet, il vous faudra tout d'abord exécuter la commande suivante `docker-compose up` à la racine du projet afin de générer la base de donnée à l'aide de docker, puis importer le dossier **back-end** dans votre IDE dédié (IntelliJ, Eclipse...).

Avant de `build` et `run` l'application, veuillez tout d'abord paramétrer les **variables d'environnements** de votre IDE afin que l'application puisse interagir avec la **base de données** dont les variables en question se situent dans le fichier **application.properties** (les valeurs sont paramétrées aux préalables dans le **docker-compose.yml**).

DB_URL=jdbc:mysql://localhost:`port`/`db_name`
DB_USER=`user`
DB_PASSWORD=`password`

Exemple sur IntelliJ IDEA: DB_URL=jdbc:mysql://localhost:3306/oc_chatop_db;DB_USER=oc_user;DB_PASSWORD=oc_pwd

<img src='/ressources/images/IntelliJ.png' width='500'/>


Pour la partie front du projet, aller dans le dossier **front-end** pour générer le **node_module** en exécutant la commande suivante `npm install`.
Une fois l'installation complète, executer la commande `npm start` pour exécuter l'application et naviguer sur l'URL fourni (l'URL par défaut `http://localhost:4200/`).

<details>
  <summary>Organisation de développement</summary>

## Kanban

<img src='/ressources/images/Kanban.png' width='500'/>

Suite à une lecture des spécifications, chaque **issue** (ticket) correspond à une fonctionnalité de l'application et donc à une branche qui lui est spécifique dont le premier numéro du ticket correspond à une partie de l'application.

Bien entendu, le nombre de tickets dépendent du développement en question et de son avancement (nombre de fonctionnalité additionnelle nécessaire, bug rencontré...).

Ce qui résulte à l'historique suivant à travers les différents commit détaillant brièvement les modifications apportées.

<img src='/ressources/images/branch-git.png' width='500'/>

</details>

<details>
  <summary>Structure de l'architecture du projet</summary>

<details>
  <summary>Arborescence</summary>
back-end
├── HELP.md
├── images
│   └── rentals
├── lib
│   └── webjars-locator-core-0.48.jar
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── openclassrooms
│       │           └── occhatop
│       │               ├── OcChatopApplication.java
│       │               ├── configuration
│       │               │   ├── AuthEntryPointJwt.java
│       │               │   ├── JwtAuthenticationFilter.java
│       │               │   ├── SecurityConfig.java
│       │               │   └── SwaggerConfiguration.java
│       │               ├── controllers
│       │               │   ├── AuthenticationController.java
│       │               │   ├── ImageController.java
│       │               │   ├── MessageController.java
│       │               │   ├── RentalController.java
│       │               │   └── UserController.java
│       │               ├── dao
│       │               │   ├── AuthenticationRequest.java
│       │               │   ├── AuthenticationResponse.java
│       │               │   └── RegisterRequest.java
│       │               ├── dto
│       │               │   ├── RentalDTO.java
│       │               │   └── UserDTO.java
│       │               ├── exceptions
│       │               │   ├── RentalNotFoundException.java
│       │               │   ├── UserIdNotFoundException.java
│       │               │   └── UserNotFoundException.java
│       │               ├── models
│       │               │   ├── authentication
│       │               │   │   └── User.java
│       │               │   ├── message
│       │               │   │   └── Message.java
│       │               │   └── rental
│       │               │       └── Rental.java
│       │               ├── repositories
│       │               │   ├── MessageRepository.java
│       │               │   ├── RentalRepository.java
│       │               │   └── UserRepository.java
│       │               └── services
│       │                   ├── AuthenticationService.java
│       │                   ├── JwtService.java
│       │                   ├── MessageService.java
│       │                   ├── RentalService.java
│       │                   └── UserService.java
│       └── resources
│           ├── application.properties
│           ├── static
│           └── templates
</details>

Vous pourrez constater que l'architecture du projet suit une structure assez commune pour les applications développées sous Spring Boot.

- `configuration` : Ce dossier contient les **configurations** spécifiques de l'application, notamment la configuration de la sécurité. Dans ce projet, un système de sécurité est mis en place pour filtrer les accès à certaines URL en fonction des utilisateurs à l'aide du JSON Web Token (JWT).

- `controllers` : Ce dossier contient les classes de **contrôleurs** qui gèrent le mappage des API. Les contrôleurs sont responsables de recevoir les requêtes HTTP, de traiter les données et de renvoyer les réponses appropriées.

- `models` : Ce dossier contient les classes de **modèles** qui représentent les entités métier de l'application. Les modèles sont généralement des classes Java avec des annotations pour la persistance des données et la validation.

- `repositories` : Ce dossier contient les **interfaces de dépôt** (repositories) qui définissent les opérations de persistance des données. Les interfaces de dépôt sont utilisées pour interagir avec la base de données ou tout autre système de stockage des données (l'application utilise actuellement MySQL).

- `services` : Ce dossier contient les classes de **services** qui implémentent la logique métier de l'application. Les services sont responsables de la manipulation des données, de la coordination des opérations et de l'exécution des règles métier tel qu'enregistrer un nouvel utilisateur, génerer le token pour l'authentification et mettre à jour les annonces des utilisateurs.

</details>

<details>
  <summary>Les enjeux du développement de l'application</summary>
Étant donné que la partie frontend a déjà été fourni dans le cadre de la formation se concentrant donc sur la partie backend.
Ce projet aborde les enjeux suivants :

## Mise en place de l'authentification avec JSON Web Token (JWT)

L'authentification étant le cœur d'une grande majorité des applications sur toutes les plateformes confondues, ce projet utilise **JSON Web Token** (JWT) pour sécuriser l'accès à certaines ressources de l'API. Les JWT offrent une méthode sécurisée pour l'échange d'informations d'authentification entre le client et le serveur, tout en évitant la nécessité de stocker l'état de l'utilisateur sur le serveur.

L'utilisation de JWT présente de nombreuses avantages tel que :

- `Sécurité` : Les JWT sont cryptés et signés numériquement, ce qui garantit l'intégrité des données et empêche toute altération non autorisée.

- `Passage d'informations` : Les JWT permettent de transmettre des informations supplémentaires dans le token lui-même, ce qui évite la nécessité de consulter la base de données à chaque demande d'accès protégé.

- `Stateless` : Les JWT sont "stateless", ce qui signifie que le serveur n'a pas besoin de stocker l'état de l'utilisateur. Cela permet une meilleure évolutivité et une réduction des appels à la base de données.

Schéma d'un processus d'authentification :

<img src='/ressources/images/JWT-works.png' width='500'/>

## Intéraction de la base de donnée à l'aide du Mapping API

Ce projet utilise les mappings API pour permettre l'interaction avec la base de données. Les mappings API permettent de définir les points d'entrée (endpoints) de l'API et spécifient les opérations HTTP (GET, POST, PUT, DELETE) afin que la partie frontend puissent interagir avec la base de données.

Voici quelques exemples de mappings API couramment utilisés :

- `GET` : Utilisé pour récupérer des données à partir de la base de données. Dans le cadre du projet il est utilisé afin de récupérer des informations sur l'utilisateur authentifié, vous pouvez utiliser l'endpoint `/api/auth/me` avec la méthode HTTP GET.

- `POST` : Utilisé pour créer de nouvelles ressources dans la base de données. Dans le cadre du projet il est utilisé pour enregistrer un nouvel utilisateur ou ajouter une nouvelle annonce, vous pouvez utiliser l'endpoint `/auth/register` avec la méthode HTTP POST et fournir les données du futur utilisateur dans le corps de la requête.

- `PUT` : Utilisé pour mettre à jour des ressources existantes dans la base de données. Dans le cadre du projet il est utilisé pour modifier le contenu d'une annonce, vous pouvez utiliser l'endpoint `/api/rentals/{id}` avec la méthode HTTP PUT et fournir les nouvelles données de l'annonce dans le corps de la requête.

- `DELETE` : Utilisé pour supprimer des ressources de la base de données. Il n'est pas utilisé dans le cadre du projet.

Les mappings API permettent d'exposer les fonctionnalités de l'application aux clients, ce qui fait que toute autre application développé sur un framework web, mobile ou autre service puissent y interagir.

## Swagger

Vous avez la possibilitté consulté l'ensemble des API et y expérimenter à travers Swagger via le lien suivant `http://localhost:3000/swagger-ui/index.html` lorsque l'application est exécuté.
Une grande partie des API nécessitent d'un token (JWT) et il est donc préférable de commencer par l'authentification.

</details>
<details>
  <summary>Les dépendances</summary>

| Dépendance      |                                           Lien                                           |
| :-------------- | :--------------------------------------------------------------------------------------: |
| Springboot JPA  |           https://docs.spring.io/spring-data/jpa/docs/current/reference/html/            |
| Springboot web  |         https://docs.spring.io/spring-boot/docs/current/reference/html/web.html          |
| Spring Security |               https://docs.spring.io/spring-security/reference/index.html                |
| MySql Driver    |                    https://spring.io/guides/gs/accessing-data-mysql/                     |
| JWT             | https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html |
| Lombok          |                     https://www.baeldung.com/intro-to-project-lombok                     |
| Swagger         |                https://www.baeldung.com/spring-rest-openapi-documentation                |

</details>
</details>
<details>
  <summary>English</summary>

# Chatop project

As you can see, the project consists of a front-end and a back-end. This project was developed as part of a training program in which the front-end is provided to the student so that I develop the entire back-end and set up the connection between the front-end and the back-end.

The front-end is a project developed on Angular 14 and the back-end on Springboot 2.7.

## Where to start ?

For the back end of the project, you'll first need to run the following command `docker-compose up` at the project root to generate the database using docker.
Then import the **back-end** folder into your dedicated IDE (IntelliJ, Eclipse...), `build` and `run` the application.

For the front-end part of the project, go to the **front-end** folder to generate the **node_module** by executing the following command `npm install`.
Once the installation is complete, run the command `npm start` to execute the application and navigate to the URL provided (the default URL is `http://localhost:4200/`).

Before `build` and `run` the application, please first set the **environment variables** in your IDE so that the application can interact with the **database** whose variables are located in the **application.properties** file (the values are set beforehand in the **docker-compose.yml**).

DB_URL=jdbc:mysql://localhost:`port`/`db_name`
DB_USER=`user`
DB_PASSWORD=`password`

Example on IntelliJ IDEA: DB_URL=jdbc:mysql://localhost:3306/oc_chatop_db;DB_USER=oc_user;DB_PASSWORD=oc_pwd

<img src='/ressources/images/IntelliJ.png' width='500'/>

<details>
  <summary>Development organization</summary>

## Kanban

<img src='/ressources/images/Kanban.png' width='500'/>

Following a reading of the specifications, each **issue** corresponds to an application feature and therefore to a specific branch, the first ticket number of which corresponds to a part of the application.

Of course, the number of tickets depends on the development and its progress (number of additional functions required, bugs encountered...).

This results in the following history through the various commits, briefly detailing the modifications made.

<img src='/ressources/images/branch-git.png' width='500'/>

</details>

<details>
  <summary>Project architecture structure</summary>

<details>
  <summary>Tree</summary>
back-end
├── HELP.md
├── images
│   └── rentals
├── lib
│   └── webjars-locator-core-0.48.jar
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── openclassrooms
│       │           └── occhatop
│       │               ├── OcChatopApplication.java
│       │               ├── configuration
│       │               │   ├── AuthEntryPointJwt.java
│       │               │   ├── JwtAuthenticationFilter.java
│       │               │   ├── SecurityConfig.java
│       │               │   └── SwaggerConfiguration.java
│       │               ├── controllers
│       │               │   ├── AuthenticationController.java
│       │               │   ├── ImageController.java
│       │               │   ├── MessageController.java
│       │               │   ├── RentalController.java
│       │               │   └── UserController.java
│       │               ├── dao
│       │               │   ├── AuthenticationRequest.java
│       │               │   ├── AuthenticationResponse.java
│       │               │   └── RegisterRequest.java
│       │               ├── dto
│       │               │   ├── RentalDTO.java
│       │               │   └── UserDTO.java
│       │               ├── exceptions
│       │               │   ├── RentalNotFoundException.java
│       │               │   ├── UserIdNotFoundException.java
│       │               │   └── UserNotFoundException.java
│       │               ├── models
│       │               │   ├── authentication
│       │               │   │   └── User.java
│       │               │   ├── message
│       │               │   │   └── Message.java
│       │               │   └── rental
│       │               │       └── Rental.java
│       │               ├── repositories
│       │               │   ├── MessageRepository.java
│       │               │   ├── RentalRepository.java
│       │               │   └── UserRepository.java
│       │               └── services
│       │                   ├── AuthenticationService.java
│       │                   ├── JwtService.java
│       │                   ├── MessageService.java
│       │                   ├── RentalService.java
│       │                   └── UserService.java
│       └── resources
│           ├── application.properties
│           ├── static
│           └── templates
</details>

As you can see, the architecture of the project follows a fairly common structure for applications developed with Spring Boot.

- `configuration`: This folder contains the **configurations** specific to the application, in particular the security configuration. In this project, a security system is set up to filter access to certain URLs according to users, using the JSON Web Token (JWT).

- `controllers`: This folder contains the **controller** classes that manage API mapping. Controllers are responsible for receiving HTTP requests, processing the data and returning the appropriate responses.

- `models`: This folder contains the **models** classes, which represent the application's business entities. Models are generally Java classes with annotations for data persistence and validation.

- `repositories`: This folder contains the **repository interfaces** that define data persistence operations. Repository interfaces are used to interact with the database or other data storage system (the application currently uses MySQL).

- `services`: This folder contains the **services** classes that implement the application's business logic. Services are responsible for manipulating data, coordinating operations and executing business rules such as registering a new user, generating the token for authentication and updating user announcements.

</details>

<details>
  <summary>Application development challenges</summary>

Since the frontend has already been provided as part of the training course, we'll concentrate on the backend.
This project addresses the following issues:

## Setting up authentication with JSON Web Token (JWT)

The authentication is at the heart of the vast majority of applications on all platforms, this project uses **JSON Web Token** (JWT) to secure access to certain API resources. JWTs offer a secure method of exchanging authentication information between client and server, while avoiding the need to store user state on the server.

The JWT offers a number of advantages:

- `Security`: JWTs are encrypted and digitally signed, guaranteeing data integrity and preventing unauthorized alteration.

- `Information passing`: JWTs enable additional information to be transmitted in the token itself, avoiding the need to consult the database each time a protected access request is made.

- `Stateless`: JWTs are "stateless", meaning that the server doesn't need to store the user's state. This means greater scalability and fewer database calls.

Authentication process diagram :

<img src='/ressources/images/JWT-works.png' width='500'/>

## Mapping API database interaction

This project uses API mappings to enable interaction with the database. API mappings define API endpoints and specify HTTP operations (GET, POST, PUT, DELETE) so that the frontend can interact with the database.

Here are a few examples of commonly used API mappings:

- `GET`: Used to retrieve data from the database. In the context of this project, it is used to retrieve information about the authenticated user. You can use the endpoint `/api/auth/me` with the HTTP GET method.

- `POST`: Used to create new resources in the database. In the context of the project, it is used to register a new user or add a new advert. You can use the `/auth/register` endpoint with the HTTP POST method and supply the data of the future user in the body of the request.

- `PUT`: Used to update existing resources in the database. In the context of the project it is used to modify the content of an advert, you can use the endpoint `/api/rentals/{id}` with the HTTP PUT method and supply the new advert data in the request body.

- `DELETE`: Used to remove resources from the database. It is not used in this project.

API mappings are used to expose the application's functionality to clients, so that any other application developed on a web, mobile or other service framework can interact with it.

## Swagger

You can view and experiment with all the APIs through Swagger through the following link `http://localhost:3000/swagger-ui/index.html` when the application is running.
Many APIs require a token (JWT), so it's best to start with authentication.

</details>

<details>
  <summary>Dependencies</summary>

| Dependency      |                                           Link                                           |
| :-------------- | :--------------------------------------------------------------------------------------: |
| Springboot JPA  |           https://docs.spring.io/spring-data/jpa/docs/current/reference/html/            |
| Springboot web  |         https://docs.spring.io/spring-boot/docs/current/reference/html/web.html          |
| Spring Security |               https://docs.spring.io/spring-security/reference/index.html                |
| MySql Driver    |                    https://spring.io/guides/gs/accessing-data-mysql/                     |
| JWT             | https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html |
| Lombok          |                     https://www.baeldung.com/intro-to-project-lombok                     |
| Swagger         |                https://www.baeldung.com/spring-rest-openapi-documentation                |

</details>
</details>
