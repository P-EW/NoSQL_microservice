package fr.ul.nosql_microservice.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ul.nosql_microservice.model.Anime;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnimeDAO  implements IAnimeDAO{

    private List<Anime> animeList;

    public AnimeDAO(){
        try {
            //Read the json file as string
            File jsonFile = ResourceUtils.getFile("classpath:animes.json");
            String jsonString = new String(Files.readAllBytes(jsonFile.toPath()));

            // Build the list from the JSON
            ObjectMapper mapper = new ObjectMapper();
            animeList = new ArrayList<>(List.of(mapper.readValue(jsonString, Anime[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Anime> findAll() {
        return animeList;
    }

    @Override
    public Anime findByIndex(int index) {
        return animeList.stream()
                .filter(anime -> index == anime.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Anime> findByGenre(String genre) {
        return animeList.stream()
                .filter(anime -> Arrays.stream(anime.getGenre()).anyMatch(genre::equalsIgnoreCase))
                .collect(Collectors.toList());
    }

    @Override
    public List<Anime> findByName(String name) {
        return animeList.stream()
                .filter(anime -> anime.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Anime anime) {
        animeList.add(anime);

        saveFile();
    }

    @Override
    public void deleteByIndex(int id) {
        animeList = animeList.stream()
                .filter(anime -> anime.getId() != id)
                .collect(Collectors.toList());

        saveFile();
    }

    @Override
    public void updateByIndex(Anime a) {
        Anime old = animeList.stream().filter(anime -> a.getId() == anime.getId())
                .findFirst()
                .orElse(null);

        if(old != null){
            animeList.set(animeList.indexOf(old),a);
        }
        else{
            animeList.add(a);
        }

        saveFile();
    }

    private void saveFile(){
        try {
            //Update the Json File
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, animeList);

            BufferedWriter writer = new BufferedWriter(new FileWriter(ResourceUtils.getFile("classpath:animes.json")));
            writer.write(out.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
