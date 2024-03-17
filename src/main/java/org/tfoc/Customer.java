package org.tfoc;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String calculateStatement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName())
                .append("\n");

        for (Rental rental : rentals) {
            // show figures for this rental
            result.append("\t")
                    .append(rental.getMovie().getTitle())
                    .append("\t")
                    .append(rental.calculatePrice())
                    .append("\n");
        }

        // add footer lines
        result.append("Amount owed is ")
                .append(calculateTotalAmount())
                .append("\n");

        result.append("You earned ")
                .append(calculatePoints())
                .append(" frequent renter points");

        return result.toString();
    }

    private Double calculateTotalAmount() {
        return rentals.stream()
                .mapToDouble(Rental::calculatePrice)
                .sum();
    }

    private Integer calculatePoints() {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }
}
