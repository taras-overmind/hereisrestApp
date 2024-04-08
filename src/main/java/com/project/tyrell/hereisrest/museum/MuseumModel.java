package com.project.tyrell.hereisrest.museum;

import com.project.tyrell.hereisrest.shared.RootModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MuseumModel extends RootModel {

    MuseumType thematic;

    public boolean matchesFilter(MuseumModel museumModelEntity, MuseumFilterBody filter) {
        return super.matchesFilter(museumModelEntity, filter)
                && (filter.thematic == museumModelEntity.thematic);
    }

    public int getRelevancyRating(MuseumModel museumModelEntity, MuseumFilterBody filter) {
        int rating = 0;
        if (Objects.equals(museumModelEntity.thematic, filter.thematic)) {
            rating += 10;
        }
        return rating;
    }
}

