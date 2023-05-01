package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import calculator.Calc;
import calculator.Mortage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Controller{

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private TextField amountField, interestField, yearField, monthField, delayFromField, delayToField, delayInterestField, filterFromField, filterToField;
	@FXML
	private Label errorLable;
	@FXML
	private ToggleGroup option;
	@FXML
	private RadioButton linear, annuity;
	@FXML
	private TextArea textArea;
    @FXML
    private LineChart<?, ?> lineChart;

	double amount, interest, delayInterest;
	int time, delayFrom, delayTo, filterFrom, filterTo;
	Mortage mortage;

	String emptyInput = "Įveskite visus reikiamus duomenis!", badInput = "Įveskite tinkamus duomenis";

	@SuppressWarnings("unchecked")
	public void calculate(ActionEvent event) throws IOException{

		if(amountField.getText().equals("") || interestField.getText().equals("") || yearField.getText().equals("") || monthField.getText().equals("")) {
			errorLable.setText(emptyInput);
		}
		else {
			try {
				amount = Double.parseDouble(amountField.getText());
				interest = Double.parseDouble(interestField.getText());
				interest = interest / 1200;
				time = 12 * Integer.parseInt(yearField.getText()) + Integer.parseInt(monthField.getText());
			}
			catch(NumberFormatException e){
				errorLable.setText(badInput);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		if(delayFromField.getText().equals("") && delayToField.getText().equals("") && delayInterestField.getText().equals("")) {
			System.out.println("Atidejimo nera");
		}
		else if(delayFromField.getText().equals("") || delayToField.getText().equals("") || delayInterestField.getText().equals("")) {
			errorLable.setText(emptyInput);
		}
		else{
			try {
				delayInterest = Double.parseDouble(delayInterestField.getText());
				delayInterest = delayInterest / 1200;
				delayFrom = Integer.parseInt(delayFromField.getText());
				delayTo = Integer.parseInt(delayToField.getText());
			}
			catch(NumberFormatException e){
				errorLable.setText(badInput);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		if(filterFromField.getText().equals("") && filterToField.getText().equals("")) {
			System.out.println("Filtro nera");
		}
		else if(filterFromField.getText().equals("") || filterToField.getText().equals("")){
			errorLable.setText(emptyInput);
		}
		else{
			try {
				filterFrom = Integer.parseInt(filterFromField.getText());
				filterTo = Integer.parseInt(filterToField.getText());
			}
			catch(NumberFormatException e){
				errorLable.setText(badInput);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}


		mortage = new Mortage(amount, interest, time);

		Calc calc = new Calc();

		if(filterFromField.getText().equals("")) {
			filterFrom = 0;
			filterTo = mortage.getTime();
		}

		if(delayInterestField.getText().equals("")) {
			delayFrom = 1;
			delayTo = 0;
			delayInterest = 0;
		}

		if(linear.isSelected()) {
			textArea.setText(calc.computationLinear(mortage, filterFrom, filterTo, delayFrom, delayTo, delayInterest));
		}
		else if(annuity.isSelected()){
			textArea.setText(calc.computationAnnuity(mortage, filterFrom, filterTo, delayFrom, delayTo, delayInterest));
		}
		
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		
		series1.setName("Kreditas");
		series2.setName("Palūkanos");
		
		lineChart.getData().clear();
		lineChart.layout();
		
		for(int j = 1; j <= mortage.getTime(); ++j)
		{
			String number = String.valueOf(j);
			series1.getData().add(new XYChart.Data(number, mortage.creditPayment.get(j - 1)));
			series2.getData().add(new XYChart.Data(number, mortage.interestPayment.get(j - 1)));

		}
		
		lineChart.getData().addAll(series1);
		lineChart.getData().addAll(series2);
		
	}
	
	public void print () throws IOException {
		
		File file = new File("Duomenys.txt");
		try(FileWriter writer = new FileWriter(file)){
			for(int j = 1; j <= mortage.getTime(); ++j) {
				writer.write("Kreditas: " + mortage.creditPayment.get(j - 1).toString() + "  Palūkanos: " + mortage.interestPayment.get(j - 1).toString() + "\n");
			}
		}
		
	}
	
}
