public class PriceCalculator {
    int tracks1495, tracks1195, cars, combinedQty, legacyCars, legacyTracks;
    double total;
    // empty constructor
    public PriceCalculator() {
    }
    // Reset the total and quantity, emptying the cart
    public void resetTotal () {
        total = 0;
        combinedQty = 0;
    }
    // Add the amount of cars to the total cost and quantity
    public void addCars () {
        combinedQty += cars;
        total += (cars * 11.95);
    }
    // Add the amount of $14.95 tracks to the total cost and quantity
    public void add1495Tracks () {
        combinedQty += tracks1495;
        total += (tracks1495 * 14.95);
    }
    // Add the amount of $11.95 tracks to the total cost and quantity
    public void add1195Tracks () {
        combinedQty += tracks1195;
        total += (tracks1195 * 11.95);
    }
    // Add the amount of legacy cars to the total cost and quantity
    public void addLegacyCars() {
        combinedQty += legacyCars;
        total += (legacyCars * 2.95);
    }
    // Add the amount of legacy tracks to the total cost and quantity
    public void addLegacyTracks() {
        combinedQty += legacyTracks;
        total += (legacyTracks * 4.95);
    }
}
