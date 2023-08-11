public class PriceCalculator {
    int tracks1495, tracks1195, cars, combinedQty, nonLegQty, legacyCars, techTracks,
            lastCars, last1195Tracks, legacyTracks, bulkDiscountPercent;
    // TODO: implement a sales tax function
    double total, lastTotal;
    boolean loyalty20, loyalty30, bulk10, bulk15;
    public PriceCalculator() {
        // Junk number to init
        lastTotal = -1.01;
    }
    // Reset the total and quantity, emptying the cart
    // Unused as of v1.1, as calc is no longer final
    public void resetTotal () {
        total = 0;
        combinedQty = 0;
    }
    // Add the amount of cars to the total cost and quantity
    public void addCars () {
        combinedQty += cars;
        nonLegQty += cars;
        total += (cars * 11.95);
    }
    // Add the amount of $14.95 tracks to the total cost and quantity
    public void add1495Tracks () {
        combinedQty += tracks1495;
        nonLegQty += tracks1495;
        total += (tracks1495 * 14.95);
    }
    // Add the amount of $11.95 tracks to the total cost and quantity
    public void add1195Tracks () {
        combinedQty += tracks1195;
        nonLegQty += tracks1195;
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
    public void addTechTracks() {
        combinedQty += techTracks;
        total += (techTracks * 5.00);
    }

    // Check to see if any bulk discounts are qualified for
    // bulkDiscountPercent used to notify user of discount amount
    public void checkBulkDiscount() {
        // buying 3-5 items gives 10% off
        if (nonLegQty >= 3 && nonLegQty <= 5) {
            total *= 0.9;
            bulk10 = true;
            bulk15= false;
            bulkDiscountPercent = 10;
        }
        // buying 6+ items gives 15% off
        else if (nonLegQty >= 6) {
            total *= 0.85;
            bulk15 = true;
            bulk10 = false;
            bulkDiscountPercent = 15;
        }
        // no bulk discount
        else {
            bulk10 = false;
            bulk15 = false;
            bulkDiscountPercent = 0;
        }
    }

    // check to see if the 20 or 30 percent loyalty discount applies
    public void checkLoyaltyDiscount() {
        if (loyalty20) {
            total *= 0.8;
        }
        else if (loyalty30) {
            total *= 0.7;
        }
    }
}
