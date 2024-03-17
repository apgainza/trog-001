package org.tfoc;

import lombok.Setter;

@Setter
public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public Integer calculatePoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
