package com.project.tyrell.hereisrest.shared.RootModelClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoursModel {
    DayOfWeek day;
    String opens;
    String closes;
}
