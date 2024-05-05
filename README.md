# Game Chess WebService
Expose l'API webservice du moteur d'échec [GameChess](https://github.com/Nic0tiN/chess-gameInstance)

Une seule ressource `Board` est présentée au format `json`.
Deux endpoints sont disponibles :
- `/init` initialise l'instance de jeu ou réinitialise l'instance existante.
- `/start` réinitialise l'instance de jeu et nomme les joueurs 
- `/play` reçoit le déplacement de `from` à `to`.

# Documentation API
## Endpoint : /init
### Requête HTTP
`GET http://localhost:8080/init`

### Paramètres de la requête
Aucun paramètre

### Corps de la requête
Le corps doit être vide

### Corps de la réponse
Si la requête aboutit, le corps de la réponse contient une instance de `Game`.

## Endpoint : /start
### Requête HTTP
`GET http://localhost:8080/start?p1={player1}&p2={player2}`

### Paramètres de la requête
| Paramètres | Type   |                                                |
|------------|--------|------------------------------------------------|
| p1         | String | Nom du joueur 1. Valeur par défaut: `Player 1` |
| p2         | String | Nom du joueur 2. Valeur par défaut: `Player 2` |

### Corps de la requête
Le corps doit être vide

### Corps de la réponse
Si la requête aboutit, le corps de la réponse contient une instance de `Game`.

## Endpoint : /play
### Requête HTTP
`POST http://localhost:8080/play?from={position}&to={position}`

### Paramètres de la requête
Il n'existe aucun paramètre de la requête.

### Corps de la requête
Représentation JSON
```json lines
{
  "from": string,
  "to": string
}
```

| Champ | Type   |                                                 |
|-------|--------|-------------------------------------------------|
| from  | String | Position initiale de la pièce à déplacer.       |
| to    | String | Position de destination de la pièce à déplacer. |

### Corps de la réponse
Si la requête aboutit, le corps de la réponse contient une instance de `Game`.
En cas d'échec, l'API retourne une erreur 500 et la propriété `message` contient le message d'erreur.

## Ressource : Game

```json lines
{
  "board": [
    {
      "position": string,
      "figure": Figure(objet)
    }
  ]
}
```

### Position
La taille du plateau est de 8 x 8, la position va de `A` à `H` en horizontal, de gauche à droite et de `1` à `8` en vertical, de bas en haut.
La position représente la valeur du croisement des axes horizontal et vertical.
Le coin inférieur gauche est `A1`.

| A8 | B8 | C8 | D8 | E8 | F8 | G8 | H8 |
|----|----|----|----|----|----|----|----|
| A7 | B7 | C7 | D7 | E7 | F7 | G7 | H7 |
| A6 | B6 | C6 | D6 | E6 | F6 | G6 | H6 |
| A5 | B5 | C5 | D5 | E5 | F5 | G5 | H5 |
| A4 | B4 | C4 | D4 | E4 | F4 | G4 | H4 |
| A3 | B3 | C3 | D3 | E3 | F3 | G3 | H3 |
| A2 | B2 | C2 | D2 | E2 | F2 | G2 | H2 |
| A1 | B1 | C1 | D1 | E1 | F1 | G1 | H1 |

### Figure
Représente une figure avec sa couleur et son type.

La propriété `coulor` peut avoir soit la valeur `WHITE` ou `BLACK`.
Pour la propriété `type`, ça peut être une des valeurs suivantes :
- `PAWN`
- `BISHOP`
- `ROOK`
- `QUEEN`
- `KING`
- `KNIGHT`

```json lines
{
  "color": string,
  "type": string
}
```

# Évolutions futures
- Supporter de multiples instances de jeu simultanées
- Persister une partie pour la recharger
- Résolution de la dépendance statique de la library
- Remplacer statique Cross-Origin