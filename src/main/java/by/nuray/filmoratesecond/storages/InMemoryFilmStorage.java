package by.nuray.filmoratesecond.storages;

import by.nuray.filmoratesecond.models.Film;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.util.Pair.toMap;


@Component
public class InMemoryFilmStorage  implements FilmStorage {


    Map<Integer, Film> films = new HashMap<>();
    Map<Integer,Set<Integer>> likes = new HashMap<>();


    private int counter=1;

    @Override
    public Optional<Film> getById(int id) {
        return  Optional.ofNullable(films.get(id));  }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(films.values()) ;
    }

    @Override
    public void save(Film film) {
        film.setId(counter++);
        films.put(film.getId(), film);
    }

    @Override
    public void update(int id, Film film) {
     getById(id).ifPresent(f -> {films.put(id, film);});
    }

    @Override
    public void delete(int id) {
        films.remove(id);
    }

    @Override
    public void addLike(int id1, int id2) {
       likes.putIfAbsent(id1, new HashSet<>());
       likes.putIfAbsent(id2, new HashSet<>());
       likes.get(id1).add(id2);
       likes.get(id2).add(id1);

    }


    @Override
    public void removeLike(int id1, int id2){
        likes.getOrDefault(id1, Collections.emptySet()).remove(id2);
        likes.getOrDefault(id2, Collections.emptySet()).remove(id1);
    }

    @Override
    public List<Film> getPopularFilms(int count) {

        return likes.entrySet().stream()
                .sorted(Comparator.comparingInt(e->e.getValue().size()))
                .limit(count)
                .map(e ->getById(e.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());





    }
}
