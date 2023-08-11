import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class CalculatorGUI extends JFrame implements ActionListener {
    private JFrame guiFrame;
    private JPanel guiPanel;
    private JFormattedTextField carsField, tracks1495Field,
            tracks1195Field, legCarsField, legTracksField;
    private JButton calculateButton;
    private JLabel legTrackLabel, legCarLabel, tracks1195Label,
            tracks1495Label, carsLabel, countLabel, totalLabel;
    private JTextPane helpPane;
    private JFormattedTextField techTracksField;
    private JLabel techLabel;
    private JRadioButton noLoyaltyButton;
    private JRadioButton loyalty20Button;
    private JRadioButton loyalty30Button;
    private JLabel bulkLabel;
    private  PriceCalculator calc;
    private final NumberFormat moneyFormat;
    private Integer totalCount;
    private ButtonGroup loyaltyGroup;
    private String carsString;

    public CalculatorGUI () {
        calc = new PriceCalculator();
        // For making sure that proper decimal points are shown
        moneyFormat = new DecimalFormat("#0.00");
        totalCount = 0;

        loyaltyGroup = new ButtonGroup();
        loyaltyGroup.add(noLoyaltyButton);
        loyaltyGroup.add(loyalty20Button);
        loyaltyGroup.add(loyalty30Button);
    }
    // Build the window and display the GUI
    protected void build() {
        guiFrame = new JFrame();
        guiFrame.setTitle("iRacing Cart Price Calculator");
        guiFrame.add(guiPanel);
        calculateButton.addActionListener(this);
        guiFrame.pack();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setVisible(true);
    }
    // Perform calculations when user clicks button
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // check for loyalty discount
            if (noLoyaltyButton.isSelected()) {
                calc.loyalty20 = false;
                calc.loyalty30 = false;
            }
            else if(loyalty20Button.isSelected()) {
                calc.loyalty20 = true;
                calc.loyalty30 = false;
            }
            else if(loyalty30Button.isSelected()) {
                calc.loyalty30 = true;
                calc.loyalty20 = false;
            }

            // Grab the values entered by the user and add them to total
            if (carsField.getText().isEmpty()) {
                calc.cars = 0;
                calc.lastCars = -1;
            } else {
                calc.cars = Integer.parseInt(carsField.getText());
                calc.lastCars = calc.cars;
            }
            calc.addCars();
            if (tracks1495Field.getText().isEmpty()) {
                calc.tracks1495 = 0;
            } else {
                calc.tracks1495 = Integer.parseInt(tracks1495Field.getText());
            }
            calc.add1495Tracks();
            if (tracks1195Field.getText().isEmpty()) {
                calc.tracks1195 = 0;
                calc.last1195Tracks = -1;
            } else {
                calc.tracks1195 = Integer.parseInt(tracks1195Field.getText());
                calc.last1195Tracks = calc.tracks1195;
            }
            calc.add1195Tracks();

            // check for discounts BEFORE non-discountable items are added
            calc.checkBulkDiscount();
            calc.checkLoyaltyDiscount();

            if (legCarsField.getText().isEmpty()) {
                calc.legacyCars = 0;
            } else {
                calc.legacyCars = Integer.parseInt(legCarsField.getText());
            }
            calc.addLegacyCars();
            if (legTracksField.getText().isEmpty()) {
                calc.legacyTracks = 0;
            } else {
                calc.legacyTracks = Integer.parseInt(legTracksField.getText());
            }
            calc.addLegacyTracks();
            if (techTracksField.getText().isEmpty()) {
                calc.techTracks = 0;
            } else {
                calc.techTracks = Integer.parseInt(techTracksField.getText());
            }
            calc.addTechTracks();

                // Prepare total for display and output total to label
                // (Round to the nearest penny (5 rounds up))
                Double truncatedTotal = BigDecimal.valueOf(calc.total)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                    // TODO: notify of bulk discount
                    if (calc.combinedQty == 1) {
                        carsString = "Your total is " + calc.combinedQty + " item for $" + moneyFormat.format(truncatedTotal);
                    }
                    else {
                        carsString = "Your total is " + calc.combinedQty + " items for $" + moneyFormat.format(truncatedTotal);
                    }

            if (calc.bulk10 || calc.bulk15) {
                        bulkLabel.setText("   You qualify for the " + calc.bulkDiscountPercent + "% bulk discount!");
                    }
                    else {
                        bulkLabel.setText("");
                    }
                    // Add 1 calculation to total count if total more than $0
                    if (truncatedTotal > 0) {
                        ++totalCount;
                        calc.lastTotal = truncatedTotal;
                    }
                    countLabel.setText("Calculation count: " + totalCount);
                    totalLabel.setText(carsString);
                    guiFrame.pack();

            } catch(Exception f){
                totalLabel.setText("An error occurred. Check values.");
                guiFrame.pack();
            }
            // Reset the calculator for next use
            calc = new PriceCalculator();
    }
}
