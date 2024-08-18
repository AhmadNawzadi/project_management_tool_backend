### La procedure de l'Intégration continue et déploiement continu de backend du projet 'Project Management Tool'

## Déclencheurs (on)
Le pipeline est déclenché automatiquement dans deux situations :

Push sur la branche main : Chaque fois qu'un changement est poussé sur la branche main, le pipeline s'exécute.
Pull Request vers main : Le pipeline s'exécute également pour chaque pull request créée ou mise à jour vers la branche main.

## Jobs
Le pipeline comporte un job principal nommé build, qui s'exécute sur un environnement virtuel ubuntu-latest.

## Étapes du Job
Checkout du Code

Action utilisée : actions/checkout@v3
Description : Cette étape récupère le code source de notre dépôt GitHub dans l'environnement de build.

## Configuration de JDK 17
Action utilisée : actions/setup-java@v3
Description : Configure l'environnement Java en installant JDK 17 avec la distribution temurin. Cela prépare l'environnement pour la compilation de notre projet Spring Boot.

## Compilation avec Maven
Commande exécutée : mvn clean package
Description : Cette commande Maven compile le projet et génère un fichier JAR ou WAR, prêt à être packagé dans une image Docker.

## Construction de l'Image Docker
Commande exécutée : docker build -t ahmad200/pmt-backend:latest .
Description : Utilise le Dockerfile situé à la racine du projet pour construire une image Docker de notre application Spring Boot. L'image est taguée en tant que latest et sera associée à notre compte Docker Hub sous le nom ahmad200/pmt-backend.

## Connexion à Docker Hub
Action utilisée : docker/login-action@v2
Description : Se connecte à Docker Hub en utilisant les identifiants stockés dans les secrets GitHub (DOCKER_HUB_USERNAME et DOCKER_HUB_ACCESS_TOKEN). Cette étape est nécessaire pour pousser l'image Docker vers Docker Hub.

## Poussée de l'Image Docker
Commande exécutée : docker push ahmad200/pmt-backend:latest
Description : Cette étape pousse l'image Docker construite précédemment sur Docker Hub, rendant l'image disponible pour le déploiement sur des serveurs ou d'autres environnements.

## Exécution des Tests
Commande exécutée : mvn test
Description : Exécute les tests unitaires de notre projet pour vérifier que l'application fonctionne comme prévu.
