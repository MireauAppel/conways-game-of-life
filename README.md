# Jeu de la vie

## Description du projet 

Le [jeu de la vie](https://fr.wikipedia.org/wiki/Jeu_de_la_vie) est une implémentation d'un 
modèle de calcul : les automates cellulaires.

Le modèle se présente sous la forme d'une grille à deux dimensions dont les cases, qu’on 
appelle des cellules, peuvent prendre deux états distincts : vivantes ou mortes.

À chaque étape, de calcul, l'état de chaque cellule est recalculé. Le nouvel état d'une cellule 
est entièrement déterminée par l’état de ses huit voisines de la façon suivante :

- Une cellule morte possédant exactement trois voisines vivantes devient vivante.
- Une cellule vivante possédant deux ou trois voisines vivantes le reste, sinon elle devient morte.

Le but de ce TP est de compléter le programme fourni par le dépôt afin d'obtenir un simulateur de 
jeu de la vie.

## Membre du projet

- APPEL, Mireau

