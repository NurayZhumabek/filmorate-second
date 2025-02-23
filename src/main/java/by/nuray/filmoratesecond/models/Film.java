package by.nuray.filmoratesecond.models;

import by.nuray.filmoratesecond.util.ValidReleaseDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;




@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Film {
    private int id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 200)
    private String description;


    @ValidReleaseDate
    private LocalDate releaseDate;

    @Min(1)
    private int duration;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
