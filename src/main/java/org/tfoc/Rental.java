package org.tfoc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The rental class represents a customer renting a movie.
 */
@Getter
@AllArgsConstructor
public class Rental {

    private AbstractMovie movie;
    private int daysRented;

    public Double calculatePrice() {
        return movie.calculatePrice(daysRented);
    }

    public int calculateFrequentRenterPoints() {
        return movie.calculatePoints(daysRented);
    }
}
