package org.tfoc;

import lombok.Setter;

@Setter
public class RegularMovie extends AbstractMovie {

    public static final int AMOUNT = 2;

    public RegularMovie(String title) {
        super(title);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return daysRented > 2
                ? AMOUNT + (daysRented - 2) * 1.5
                : AMOUNT;
    }
}
