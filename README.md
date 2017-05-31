Shock_Android
==================

  Ce projet reprend celui fournis par Nordic Semiconductor "Android-nRF-UART" en ajoutant les fonctionnalités spécifiques pour le Shock d'Innovalab. 

Outils
------------
Le projet a été développé sous Android Studio 2.2.3 avec le SDK 18.
L'interface a été pensée pour la tablette Samsung Galaxy Tab 10.1"

Le projet a besoin de bibliothèques externes normalement intégrées automatiquement lors de la compilation.

Extrait du fichier "build.gradle (Module: app)"

dependencies {
    compile 'com.androidplot:androidplot:0.6.1'
    compile 'com.androidplot:androidplot-core:1.4.2'
    compile 'com.jjoe64:graphview:4.2.1'
}

Architecture du projet
==================

UartService et DeviceListActivity ont été fournies par Nordic Semiconductor sans subir de modifications.

MainActivity permet d'établir la connexion avec un appareil et de gérer toutes les phases du dialogue comme l'envoi ou la réception des données.
Elle permet également de modifier les classes modèles tout en mettant à jour et en écoutant les évenements des différentes vues. 

Modèle
------------
ObjetFrappe permet de stocker toutes les informations relatives à une frappe. Elle contient donc toutes les valeurs reçues de la nRF52 mais aussi des valeurs calculées pour les besoins des différentes représentations des vues.

Vector3 est une simple structure pour manipuler plus facilement les différentes données.

Vues
------------
CibleVue est la cible représentant le sac de frappe vu du dessus où est affiché l'emplacement où la frappe a eu lieu à un certains temps t. 

GraphView est le graphique représentant la puissance moyenne exercée sur les capteurs en fonction du temps. Une clic sur un point de la courbe permet d'afficher le point de frappe correspondant sur la cible. 


