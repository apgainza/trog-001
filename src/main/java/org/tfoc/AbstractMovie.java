package org.tfoc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public abstract class AbstractMovie {

    private final String title;

    public abstract double calculatePrice(int daysRented);

    public Integer calculatePoints(int daysRented) {
        return 1;
    }

}
