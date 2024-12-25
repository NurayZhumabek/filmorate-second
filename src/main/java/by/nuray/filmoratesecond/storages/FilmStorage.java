package by.nuray.filmoratesecond.storages;

import by.nuray.filmoratesecond.models.Film;

import java.util.List;
import java.util.Optional;

public interface FilmStorage {

    public Optional<Film> getById(int  id);

    public List<Film> getAll();

    public void save(Film film);
    public void update(int id, Film film);

    public void delete(int id);

    void addLike(int id1, int id2);

    void removeLike(int id1, int id2);

    public List<Film> getPopularFilms(int count);
}
