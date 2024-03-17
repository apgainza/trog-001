package org.tfoc;

import lombok.Setter;

@Setter
public class ChildrensMovie extends Movie {

    public static final double AMOUNT = 1.5;

    public ChildrensMovie(String title) {
        super(title);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented > 3
                ? AMOUNT + (daysRented - 3) * AMOUNT
                : AMOUNT;
    }
}
