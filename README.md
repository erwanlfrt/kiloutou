# kiloutou
TP UML - JEE

## Get started

 - Modifier le fichier src/main/java/config.properties et modifier les lignes ```DB_LOGIN``` et ```DB_PASSWORD```
 - Serveur Tomcat 9
 - Junit 4
 - ```src/main/webapp/Welcome.jsp``` correspond à la page d'accueil
 
## Ignore config.properties

Depuis la racine :  ```git update-index --assume-unchanged src/main/java/config.properties```

## To do
 ### Style
  - Emprunt (ajouter, lister, info) -> attendre la fin du dev des fonctionnalités
  - Utilisateur (ajouter, lister, info)
  - Matériels (ajouter, lister, info)
 ### Fonctionnalité
  - ~~Page de statistique~~
  - Vérifier l'authentification de l'utilisateur dans chaque page et vérifier son profil
  - Page d'erreur à compléter (afficher un message d'erreur correspondant à l'erreur produite + redirection vers l'accueil)
  - Déconnexion (supprimer le user et l'employee de la session et rediriger vers la page login)
  - Rechercher un utilisateur par son nom et prénom (recherche par @ mail pour l'instant)
  - Ajout d'un matériel : si on ajoute un ordinateur, il faut pouvoir choisir un processeur et une carte graphique parmi les processeurs et carte graphique déjà existants ou alors créer une nouvelle carte graphique et un nouveau processeur

### Problème
  - OBLIGATION de garder les emprunts une fois rendu, 1 emprunt = 1 matériel + 1 utilisateur (as primary key) alors impossible de supprimer les utilisateurs et les matériels ???
