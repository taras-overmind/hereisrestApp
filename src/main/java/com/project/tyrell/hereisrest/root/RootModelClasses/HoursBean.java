package com.project.tyrell.hereisrest.root.RootModelClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoursBean {
    DayOfWeek day;
    String opens;
    String closes;
}
