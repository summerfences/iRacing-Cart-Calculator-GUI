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
    private PriceCalculator calc;
    private NumberFormat moneyFormat;
    private Integer totalCount;
    // empty constructor
    public CalculatorGUI () {
        // for calculating
        calc = new PriceCalculator();
        // for making sure that proper decimal points are shown
        moneyFormat = new DecimalFormat("#0.00");
        totalCount = 0;
    }
    // build the window and display the GUI
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
            // Grab the values entered by the user and add them to total
            if (carsField.getText().isEmpty() == true) {
                calc.cars = 0;
            } else {
                calc.cars = Integer.parseInt(carsField.getText());
            }
            calc.addCars();
            if (tracks1495Field.getText().isEmpty() == true) {
                calc.tracks1495 = 0;
            } else {
                calc.tracks1495 = Integer.parseInt(tracks1495Field.getText());
            }
            calc.add1495Tracks();
            if (tracks1195Field.getText().isEmpty() == true) {
                calc.tracks1195 = 0;
            } else {
                calc.tracks1195 = Integer.parseInt(tracks1195Field.getText());
            }
            calc.add1195Tracks();
            if (legCarsField.getText().isEmpty() == true) {
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
            // Prepare total for display and output total to label
            // (Round to the nearest penny (5 rounds up))
            Double truncatedTotal = BigDecimal.valueOf(calc.total)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            String carsString = "Your total is $" + moneyFormat.format(truncatedTotal);
            // add 1 calculation to total count if total more than $0
            if (truncatedTotal > 0) {
                ++totalCount;
            }
            countLabel.setText("Calculation count: " + totalCount);
            totalLabel.setText(carsString);
            guiFrame.pack();
        } catch (Exception f) {
            totalLabel.setText("An error occurred. Check values.");
            guiFrame.pack();
        }
        // reset the calculator for next use
        calc.resetTotal();
    }
}
