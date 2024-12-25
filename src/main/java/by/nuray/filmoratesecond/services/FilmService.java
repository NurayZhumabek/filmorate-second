package by.nuray.filmoratesecond.services;


import by.nuray.filmoratesecond.models.Film;
import by.nuray.filmoratesecond.models.User;
import by.nuray.filmoratesecond.storages.FilmStorage;
import by.nuray.filmoratesecond.storages.UserStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

     public User getUserById(int id) {
        return userStorage.getById(id).orElseThrow(()->new IllegalArgumentException("User not found"));
     }

    public Film getFilmById(int id) {
        return filmStorage.getById(id).orElseThrow(()->new IllegalArgumentException("Film not found"));
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAll();
    }

    public void create(Film film) {
        filmStorage.save(film);
    }

    public void update(Film film,int id) {
        filmStorage.update(id, film);
    }

    public void delete(int id) {
        filmStorage.delete(id);
    }

    public void addLike(int filmId,int userId) {
        getFilmById(filmId);
        getUserById(userId);

        filmStorage.addLike(filmId, userId);
    }

    public void removeLike(int filmId,int userId) {
        getFilmById(filmId);
        getUserById(userId);
        filmStorage.removeLike(filmId, userId);
    }

    public List<Film> getPopularFilms(int count) {
        return filmStorage.getPopularFilms(count);
    }



}
