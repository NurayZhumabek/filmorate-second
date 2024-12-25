package by.nuray.filmoratesecond.controllers;


import by.nuray.filmoratesecond.models.Film;
import by.nuray.filmoratesecond.services.FilmService;
import by.nuray.filmoratesecond.util.FilmErrorResponse;
import by.nuray.filmoratesecond.util.FilmNotCreatedException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping()
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();

    }

    @PostMapping()
    public ResponseEntity<Film> createFilm(@RequestBody @Valid Film film, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }

        filmService.create(film);
        return new ResponseEntity<>(film,HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Film> updateFilm(@RequestBody @Valid Film film, BindingResult bindingResult) {

        if (film.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }

        if (filmService.getFilmById(film.getId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        filmService.update(film, film.getId());

        return new ResponseEntity( HttpStatus.OK);

    }

    @PutMapping("/{id}/like/{userId}")
    public ResponseEntity<Void> likeFilm(@PathVariable("id") int id, @PathVariable("userId") int userId) {

        filmService.addLike(id, userId);

        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/like/{userId}")
    public ResponseEntity<Void> unlikeFilm(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        filmService.removeLike(id, userId);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@RequestParam("count") int count) {

        return filmService.getPopularFilms(count);

    }


    @ExceptionHandler
    public ResponseEntity<FilmErrorResponse> handleException(FilmNotCreatedException ex) {
        FilmErrorResponse err=new FilmErrorResponse(
                ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }




}
