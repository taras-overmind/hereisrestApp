package com.project.tyrell.hereisrest.museum;

import com.project.tyrell.hereisrest.shared.RootModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
}

