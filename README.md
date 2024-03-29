# kiloutou
TP UML - JEE

![logo](https://user-images.githubusercontent.com/50581556/143511754-6aa9fa0b-d032-4a1d-b18d-5e8f5b1e7819.png)


## Team 06
 - DOUADY Tom
 - DUSE Mathieu
 - GUILLOUX Nathan
 - LE FORESTIER Erwan
 - PONTACQ Hugo


## Get started

 - Modifier le fichier src/main/java/config.properties et modifier les lignes ```DB_LOGIN``` et ```DB_PASSWORD```
 - Serveur Tomcat 9
 - Junit 4
 - ```src/main/webapp/Welcome.jsp``` correspond à la page d'accueil
 
## Ignore config.properties

Depuis la racine :  ```git update-index --assume-unchanged src/main/java/config.properties```

## To do
 ### Style
  - ~~Page d'accueil~~
  - ~~Page de connexion~~
  - ~~Page d'erreur~~
  - ~~Page de statistiques~~
  - ~~Emprunt (ajouter, lister, info)~~
  - ~~Utilisateur (ajouter, lister, info)~~
  - ~~Matériels (ajouter, ~~lister~~, info)~~
 ### Fonctionnalité
  - ~~Page de statistique~~
  - ~~Vérifier l'authentification de l'utilisateur dans chaque page et vérifier son profil~~
  - ~~Page d'erreur à compléter (afficher un message d'erreur correspondant à l'erreur produite + redirection vers l'accueil)~~
  - ~~Modifier un emprunt (date de début et de fin si pas commencé ou date de fin uniquement si l'emprunt a déjà commencé)~~
  - ~~Déconnexion (supprimer le user et l'employee de la session et rediriger vers la page login)~~
  - ~~Rechercher un utilisateur par son nom et prénom (recherche par @ mail pour l'instant)~~
  - ~~Retourner un emprunt avec l'interface décrite dans l'énoncé~~
  - ~~Ajout d'un matériel : si on ajoute un ordinateur, il faut pouvoir choisir un processeur et une carte graphique parmi les processeurs et carte graphique déjà existants ou alors créer une nouvelle carte graphique et un nouveau processeur~~

### Problème
  - ~~# OBLIGATION de garder les emprunts une fois rendu, 1 emprunt = 1 matériel + 1 utilisateur (as primary key) alors impossible de supprimer les utilisateurs et les matériels ???~~ -> solution : on ne supprime un équipement dans la BDD, en revanche on modifie un flag  ```canBeLoaned``` pour les équipements. SI on souhaite supprimer un utilisateur, celui-ci est remplacé par un autre utilisateur non identifiable dans les emprunts existants (adresse mail aléatoire terminant par '@unknown.com'), un flag isReal est vrai si l'utilisateur correspond vraiment à un utilisateur, est faux s'il s'agit d'un utilisateur de substitution.

## License
![CC png](https://user-images.githubusercontent.com/50581556/143511708-a5133dcf-41f6-4a34-98c5-57488f75197a.png)
