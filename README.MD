# NoSQL_miroservice

C'est un petit projet de Spring. (un TP)

C'est une API Rest avec comme base de donnée des animés.

L'api propose les services suivants sur ces routes :
- Routes en GET :
  - Récupérer la liste entière :  
  ``http://localhost:8080/animes``
  - Récupérer un anime par son id :  
  ``http://localhost:8080/anime/3000``
  - Récupérer des animes par leur titre :  
  ``http://localhost:8080/animes/shingeki``
  - Récupérer un anime par un de ses genres :  
  ``http://localhost:8080/animes/genre/action``
- Route en POST :
  - Ajouter un anime à la liste :  
  ``http://localhost:8080/animes``
- Route en DELETE :
  - Supprimer un anime par son id :  
    ``http://localhost:8080/anime/id``
- Route en PUT :
  - Ajouter/Remplacer un anime à la liste :  
    ``http://localhost:8080/anime``
  

## Format de l'objet Anime à POST/PUT :
Content-Type: application/json  
body:  
```json
{  
  "id": "int", 
  "title": "string",  
  "note": "string",
  "type": "string",
  "status": "string",
  "episodes": "string",
  "start_date": "string",
  "end_date": "string",
  "genre": "string[]",
  "synopsis": "string"
}
```

