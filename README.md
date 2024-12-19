# TP1---Revue-de-Code-avec-CI-CD-et-Bonnes-Pratiques

Ce projet vise à mettre en place les bonnes pratiques de développement en utilisant Maven, les tests unitaires avec JUnit, la génération de documentation Java avec Javadoc, l'intégration du style de code avec Checkstyle, et enfin l'automatisation du déploiement via CI/CD avec GitHub Actions. L'objectif est de garantir la qualité du code tout en facilitant son déploiement et sa documentation.

## Étapes du projet :

### 1. **Création de la structure du projet Java avec Maven**

- Nous avons utilisé **Maven** pour initialiser la structure du projet Java et gérer les dépendances et les plugins nécessaires. Maven facilite la gestion de projet en automatisant des tâches comme la compilation, les tests et le déploiement.

### 2. **Ajout du code de stagiaire dans le dossier `main`**

- Le code fourni a été intégré dans le répertoire `src/main/java`. Ce code comprend des fonctions comme `maskAff` qui prend un mot de passe en entrée et génère un tableau d'entiers représentant la transformation de chaque caractère du mot de passe.

### 3. **Résumé du projet et utilisation de la bibliothèque**

- La fonction `maskAff` prend un mot de passe de type `String` en paramètre et retourne un tableau d'entiers. Chaque entier représente la transformation d'un caractère dans le mot de passe. Ce processus aide à l'analyse ou à la vérification du mot de passe.

### 4. **Écriture des tests unitaires dans le fichier `Test.java`**

- Des tests unitaires ont été écrits pour tester la fonctionnalité de la classe `AwesomePasswordChecker`. Ces tests vérifient la méthode `maskAff`, la méthode `getDIstance` ainsi que la méthode `ComputeMD5`.
- Les tests ont été réalisés avec **JUnit** et les assertions sont basées sur des méthodes telles que `assertEquals`, `assertNotNull`, `assertSame`, etc.

### 5. **Génération de documentation Java avec Javadoc**

- La documentation a été générée en utilisant **Javadoc**. Pour cela, des commentaires de type Javadoc ont été ajoutés aux méthodes et classes du code source.
- Exemple de commentaire Javadoc :
  ```java
  /**
   * Description de la méthode.
   * @param param1 Description du paramètre 1
   * @return Description du résultat
   */
  ```
- Pour générer la documentation, nous avons exécuté la commande suivante dans le répertoire contenant les fichiers source :
  ```bash
  javadoc *.java -d dossier_destination
  ```
- La documentation générée a été placée dans le dossier `dossier_destination`.

### 6. **Mise en place de Checkstyle pour le style de code**

- **Checkstyle** a été intégré au projet pour garantir que le code respecte un style uniforme et cohérent. Le rapport Checkstyle peut être généré avec la commande suivante :
  ```bash
  mvn site
  ```
- Cette commande génère un rapport détaillant les violations de style dans le projet.

### 7. **Configuration CI/CD avec GitHub Actions**

- Un pipeline CI/CD a été configuré à l'aide de **GitHub Actions** pour automatiser plusieurs tâches, telles que :
  - La compilation et l'exécution des tests avec Maven (`mvn clean test`).
  - Le déploiement du projet dans le **GitHub Maven Registry** pour permettre à d'autres projets d'utiliser la bibliothèque.
  - Le déploiement de la documentation Javadoc sur **GitHub Pages**.
  - Le déploiement du rapport de couverture Cobertura sur **GitHub Pages**.

Exemple de fichier de workflow GitHub Actions (`.github/workflows/ci.yml`) :

```yaml
name: Build and Deploy

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v2

      - name: Set up Java
        uses: actions/setup-java@v3
        with:
          distribution: "temurin"
          java-version: "17"

      - name: Build and Test
        run: mvn clean test

      - name: Deploy to GitHub Maven Registry
        run: mvn deploy -DskipTests
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}

      - name: Generate Javadoc
        run: mvn javadoc:javadoc

      - name: Deploy Javadoc to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          personal_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: target/site/apidocs
          publish_branch: gh-pages
          destination_dir: javadoc/${{ github.sha }}

      - name: Generate Cobertura Report
        run: mvn cobertura:cobertura

      - name: Deploy Cobertura Report to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          personal_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: target/site/cobertura
          publish_branch: gh-pages
          destination_dir: cobertura/${{ github.sha }}
```
