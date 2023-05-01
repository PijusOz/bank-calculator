package calculator;

import java.util.ArrayList;

import application.Controller;

public class Mortage{

	private double amount;
	private double interest;
	private int time;
	public ArrayList<Double> creditPayment = new ArrayList();
	public ArrayList<Double> interestPayment = new ArrayList();
	

	public Mortage(double amount, double interest, int time){
		this.setAmount(amount);
		this.setInterest(interest);
		this.setTime(time);
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}

	public ArrayList<Double> getCreditPayment() {
		return creditPayment;
	}

	public void setCreditPayment(ArrayList<Double> creditPayment) {
		this.creditPayment = creditPayment;
	}





}
