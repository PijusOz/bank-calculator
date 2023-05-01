package calculator;

public class Calc {

	double payment, credit, balance;
	public StringBuilder sb = new StringBuilder("   Įmoka:       Kreditas:          Palūkanos:     Liko nesumokėta:\n");

	public String computationLinear(Mortage mortage, int filterFrom, int filterTo, int delayFrom, int delayTo, double delayInterest){

		credit = mortage.getAmount() / (mortage.getTime() - delayTo + delayFrom - 1);
		balance = mortage.getAmount();

		for(int i = 1; i <= mortage.getTime(); ++i) {

			if(filterFrom <= i && i <= filterTo) {
				if(delayFrom <= i && i <= delayTo) {
					sb.append(i + ".  ");
					sb.append(Math.round(100.0 * (balance * delayInterest)) / 100.0);
					sb.append("          ");
					sb.append(0);
					mortage.getCreditPayment().add((double) 0);
					sb.append("          ");
					sb.append(Math.round(balance * delayInterest * 100.0) / 100.0);
					mortage.interestPayment.add(Math.round(balance * delayInterest * 100.0) / 100.0);
					sb.append("          ");
				}
				else {
					sb.append(i + ".  ");
					sb.append(Math.round(100.0 * (credit + balance * mortage.getInterest())) / 100.0);
					sb.append("          ");
					sb.append(Math.round(credit * 100.0) / 100.0);
					mortage.getCreditPayment().add(Math.round(credit * 100.0) / 100.0);
					sb.append("          ");
					sb.append(Math.round(balance * mortage.getInterest() * 100.0) / 100.0);
					mortage.interestPayment.add(Math.round(balance * mortage.getInterest() * 100.0) / 100.0);
					sb.append("          ");
				}
				
			}

			if(i < delayFrom || i > delayTo) {
				balance -= credit;
			}
			
			if(filterFrom <= i && i <= filterTo) {
				
				sb.append(Math.round(balance * 100.0) / 100.0);
				sb.append("\n");
			}

		}

		return sb.toString();
	}

	public String computationAnnuity(Mortage mortage, int filterFrom, int filterTo, int delayFrom, int delayTo, double delayInterest){

		payment = mortage.getAmount() * ((mortage.getInterest() * Math.pow((1 + mortage.getInterest()), (mortage.getTime() - delayTo + delayFrom - 1)))/(Math.pow((1 + mortage.getInterest()), (mortage.getTime() - delayTo + delayFrom - 1))  - 1));
		balance = mortage.getAmount();

		for(int i = 1; i <= mortage.getTime(); ++i) {

			if(filterFrom <= i && i <= filterTo) {
				if(delayFrom <= i && i <= delayTo) {
					sb.append(i + ".  ");
					sb.append(Math.round(100.0 * (balance * delayInterest)) / 100.0);
					sb.append("          ");
					sb.append(Math.round(0));
					mortage.getCreditPayment().add((double) 0);
					sb.append("          ");
					sb.append(Math.round(balance * delayInterest * 100.0) / 100.0);
					mortage.interestPayment.add(Math.round(balance * delayInterest * 100.0) / 100.0);
					sb.append("          ");
				}
				else {
					sb.append(i + ".  ");
					sb.append(Math.round(100.0 * payment) / 100.0);
					sb.append("          ");
					sb.append(Math.round(100.0 * (payment - balance * mortage.getInterest())) / 100.0);
					mortage.getCreditPayment().add(Math.round(100.0 * (payment - balance * mortage.getInterest())) / 100.0);
					sb.append("          ");
					sb.append(Math.round(100.0 * balance * mortage.getInterest()) / 100.0);
					mortage.interestPayment.add(Math.round(100.0 * balance * mortage.getInterest()) / 100.0);
					sb.append("          ");
				}
				
			}

			if(i < delayFrom || i > delayTo) {
				balance -= payment - balance * mortage.getInterest();
			}
			

			if(filterFrom <= i && i <= filterTo) {
				sb.append(Math.round(balance * 100.0) / 100.0);
				sb.append("\n");
			}
		}

		return sb.toString();

	}
}
