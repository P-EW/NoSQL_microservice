package fr.ul.nosql_microservice.web.controller;

import fr.ul.nosql_microservice.dao.IAnimeDAO;
import fr.ul.nosql_microservice.model.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimeController {

    @Autowired
    private IAnimeDAO dao;

    @GetMapping(value ="/animes")
    public List<Anime> getAnimeList() {
        return dao.findAll();
    }

    @GetMapping(value ="/anime/{id}")
    public Anime getAnime(@PathVariable int id){
        return dao.findByIndex(id);
    }

    @PostMapping(value = "animes")
    public void addAnime(@RequestBody Anime a){
        dao.save(a);
    }

    @GetMapping(value ="/animes/{title}")
    public List<Anime> getAnimeByTitle(@PathVariable String title){
        return dao.findByName(title);
    }

    @GetMapping(value ="/animes/genre/{genre}")
    public List<Anime> getAnimeByGenre(@PathVariable String genre){
        return dao.findByGenre(genre);
    }

    @DeleteMapping(value ="/anime/{id}")
    public void deleteAnime(@PathVariable int id){
        dao.deleteByIndex(id);
    }

    @PutMapping(value ="/anime/")
    public void replaceAnime(@RequestBody Anime a){
        dao.updateByIndex(a);
    }
}
