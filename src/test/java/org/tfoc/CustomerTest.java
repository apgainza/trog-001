package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class CustomerTest {

    @Test
    public void testCustomer() {
        Customer c = new CustomerBuilder().build();
        assertNotNull(c);
    }

    @Test
    public void testAddRental() {
        Customer customer2 = new CustomerBuilder().withName("Sallie").build();
        Movie movie1 = new NewReleaseMovie("Gone with the Wind");
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        customer2.addRental(rental1);
    }

    @Test
    public void testGetName() {
        Customer c = new Customer("David");
        assertEquals("David", c.getName());
    }

    @Test
    public void statementForRegularMovie() {
        Movie movie1 = new RegularMovie("Gone with the Wind");
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tGone with the Wind\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.calculateStatement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForNewReleaseMovie() {
        Movie movie1 = new NewReleaseMovie("Star Wars");
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tStar Wars\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        String statement = customer2.calculateStatement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForChildrensMovie() {
        Movie movie1 = new ChildrensMovie("Madagascar");
        Rental rental1 = new Rental(movie1, 3); // 3 day rental
        Customer customer2
                = new CustomerBuilder()
                .withName("Sallie")
                .withRentals(rental1)
                .build();
        String expected = "Rental Record for Sallie\n" +
                "\tMadagascar\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        String statement = customer2.calculateStatement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForManyMovies() {
        Movie movie1 = new ChildrensMovie("Madagascar");
        Rental rental1 = new Rental(movie1, 6); // 6 day rental
        Movie movie2 = new NewReleaseMovie("Star Wars");
        Rental rental2 = new Rental(movie2, 2); // 2 day rental
        Movie movie3 = new RegularMovie("Gone with the Wind");
        Rental rental3 = new Rental(movie3, 8); // 8 day rental
        Customer customer1
                = new CustomerBuilder()
                .withName("David")
                .withRentals(rental1, rental2, rental3)
                .build();
        String expected = "Rental Record for David\n" +
                "\tMadagascar\t6.0\n" +
                "\tStar Wars\t6.0\n" +
                "\tGone with the Wind\t11.0\n" +
                "Amount owed is 23.0\n" +
                "You earned 4 frequent renter points";
        String statement = customer1.calculateStatement();
        assertEquals(expected, statement);
    }

    //TODO make test for price breaks in code.
    @Test
    public void regularMovie() {
        double priceExpected = 6.5;
        Rental rentalRegular = new Rental(new RegularMovie("Entre vías"), 5);
        double calculatePrice = rentalRegular.calculatePrice();

        Assertions.assertEquals(calculatePrice, priceExpected);
    }

    @Test
    public void newReleaseMovie() {
        double priceExpected = 6;
        Rental rentalRegular = new Rental(new NewReleaseMovie("Entre vías"), 2);
        double calculatePrice = rentalRegular.calculatePrice();

        Assertions.assertEquals(calculatePrice, priceExpected);
    }

    @Test
    public void childrensMovie() {
        double priceExpected = 3;
        Rental rentalRegular = new Rental(new ChildrensMovie("Entre vías"), 4);
        double calculatePrice = rentalRegular.calculatePrice();

        Assertions.assertEquals(calculatePrice, priceExpected);
    }
}