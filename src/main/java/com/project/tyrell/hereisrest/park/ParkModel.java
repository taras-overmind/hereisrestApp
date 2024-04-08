package com.project.tyrell.hereisrest.park;

import com.project.tyrell.hereisrest.shared.RootModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkModel extends RootModel {
    boolean playground;
    boolean picnic;
    boolean hiking;
    boolean sport;
    boolean cycling;

    public boolean matchesFilter(ParkModel model, ParkFilterBody filter) {
        return super.matchesFilter(model, filter)
                && (!filter.playground || model.playground)
                && (!filter.picnic || model.picnic)
                && (!filter.hiking || model.hiking)
                && (!filter.sport || model.sport)
                && (!filter.cycling || model.cycling);
    }

    public int getRelevancyRating(ParkModel foodModel, ParkFilterBody foodFilterBody) {
        int rating = 0;
        if (foodModel.playground == foodFilterBody.playground) {
            rating += 10;
        }
        if (foodModel.picnic == foodFilterBody.picnic) {
            rating += 10;
        }
        if (foodModel.hiking == foodFilterBody.hiking) {
            rating += 10;
        }
        if (foodModel.sport == foodFilterBody.sport) {
            rating += 10;
        }
        if (foodModel.cycling == foodFilterBody.cycling) {
            rating += 10;
        }

        return rating;
    }
}
