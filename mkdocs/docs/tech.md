# Documentation technique

`@Auteur : TONDON César`

## Structure du projet

    fr.ul.miage.simulation.App.java
    fr.ul.miage.simulation.Launcher.java
    fr.ul.miage.simulation.controller/
        Controller.java     # controleur principal de l'application
    model/                  # package contenant l'ensemble des objets
        Baignoire.java
        Robinet.java
        Trou.java
    fr.ul.miage.simulation.service/                # package contenant l'ensemble des creations de services
        FuiteService.java
        RemplissageService.java

## Commandes

* `[../bindist/bin/] run.bat ` - Lancement Windows OS
* `[../bindist/bin/] ./run ` - Lancement Linux OS
* `[../src/main/java/] java fr.ul.miage.simulation.Launcher.class` - Lancement depuis le fichier .class

## Conception Interface

**MVC** (Model-View-Controller) :  
Il met l'accent sur la séparation entre la logique métier et l'affichage du logiciel.
Les 3 parties du modèle de conception de logiciel MVC peuvent être décrites comme suit :

- Model (modèle) : gère les données et la logique métier.
- View (vue) : gère la disposition et l'affichage.
- Controller (contrôleur) : achemine les commandes des parties "model" et "view".

L'interface est divisée en trois parties : 

- D'une part, la centrale représentant la simulation depuis un schéma animé.
- D'autre part, la partie à gauche se focusant sur le paramètrage de la simulation.
- Enfin, en bas, les résultats suite à la simulation.

## Conception Objets

- **Class Baignoire** : Une baignoire est représentée selon sa quantité, capacité et son débordement. Elle voit sa quantité d'eau (L) augmenter ou diminuer. Elle ne peut pas avoir une quantité supérieur à sa capacité ou inférieur à 0.
- **Class Robinet** : Un robinet appartient à une baignoire selon un débit fixe. Le robinet a pour fonction de remplir la baignoire.
- **Class Trou** : Un trou appartient à une baignore selon un débit fixe. Le trou a pour fonction vider la baignoire.

- **Class FuiteService** : Représente l'action de fuite d'une baignoire réalisée par un trou. Toutes les secondes, le fr.ul.miage.simulation.service va exécuter l'action de trou sur la baignoire.
- **Class RemplissageService** :  Représente l'action de remplissage d'une baignoire réalisée par un robinet. Toutes les secondes, le fr.ul.miage.simulation.service va exécuter l'action du robinet sur la baignoire. 

- **Class Controller** : Controleur du modèle MVC pour permettre les intéractions entre l'utilisation et la machine.

## Choix et principes de parallélisations des tâches

Pour la réalisation de cette simulation, nous utilisons la **programmation** **concurrente**. Elle permet d'éxécuter plusieurs calculs **simultanément** au lieu d'être séquentiels.

Ici, le remplissage de la baignoire s'éxécute en même temps que ses **multiples** fuites. 

C'est pourquoi, nous avons choisi de créer un fr.ul.miage.simulation.service _**RemplissageService**_ afin de représenter l'unique remplissage de la baignoire se déroulant toute les secondes.

De plus, nous avons créé le fr.ul.miage.simulation.service _**FuiteService**_ pour représenter le calcul d'une fuite se déroulant toute les secondes. 

Pour que les calculs des fuites se réalisent simultanément, nous avons listé ses services, afin de les éxécuter via un mécanisme de verrouillage : **Synchronisation**.

Lors du lancement de la simulation, l'ensemble des services sont éxécutés sur une période d'une seconde d'éxécution.

Lors de l'arrêt de la simulation, les calculs en cours sont stoppés pour nous permettre d'afficher les résultats.

`Contact : cesar.tondon1@etu.univ-lorraine.fr`
