package fr.ul.nosql_microservice.dao;

import fr.ul.nosql_microservice.model.Anime;

import java.util.List;

public interface IAnimeDAO {

    List<Anime> findAll();

    Anime findByIndex(int index);

    List<Anime> findByGenre(String genre);

    List<Anime> findByName(String name);

    void save(Anime anime);

    void deleteByIndex(int id);

    void updateByIndex(Anime a);
}
