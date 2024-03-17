package org.tfoc;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.tfoc.MovieType.NEW_RELEASE;

@Getter
@AllArgsConstructor
public class Movie {

    private final String title;
    private final MovieType movieType;

    public Double calculatePrice(int daysRented) {
        double thisAmount = 0;
        //determine amounts for each line
        switch (movieType) {
            case REGULAR:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public int calculatePoints(int daysRented) {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental

        if (NEW_RELEASE == movieType && daysRented > 1)
            frequentRenterPoints++;

        return frequentRenterPoints;
    }

}
