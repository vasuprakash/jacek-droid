package pl.looksok.logic;

public class PersonDataUtils {
	public static double returnMoneyToPersonB(String personBName, double howMuchPersonBPaid,
			double howMuchPersonBShouldPay, double howMuchRefundPersonBNeeds,
			PersonData pd) {
		double tmpToReturn = pd.getHowMuchPersonShouldPay() - pd.getHowMuchIPaid();
		
		if(personBNeedsMoreThanIShouldGive(tmpToReturn, pd.getAlreadyReturned(), pd.getToReturn())){
			tmpToReturn = splitMyReturnAmount(pd);
		}else if(personBWantsLessThanIHaveToReturn(pd, howMuchPersonBPaid, howMuchPersonBShouldPay, tmpToReturn, howMuchRefundPersonBNeeds)){
			tmpToReturn = givePersonBNoMoreThanHeWants(pd, howMuchPersonBPaid, howMuchPersonBShouldPay);
		}

		if(tmpToReturn > howMuchRefundPersonBNeeds){
			tmpToReturn = howMuchRefundPersonBNeeds;
		}

		if(tmpToReturn<0.0)
			tmpToReturn = 0.0;

		pd.setAlreadyReturned(pd.getAlreadyReturned() + tmpToReturn);
		pd.getOtherPeoplePayments().get(personBName).addToAlreadyRefunded(tmpToReturn);
		return tmpToReturn;
	}
	
	private static double givePersonBNoMoreThanHeWants(PersonData pd, double howMuchPersonBPaid, double howMuchPersonBShouldPay) {
		double tmpToReturn;
		double refundForPersonBNeeded = howMuchPersonBPaid - howMuchPersonBShouldPay;
		if(refundForPersonBNeeded<0)
			tmpToReturn = 0.0;

		tmpToReturn = howMuchPersonBPaid - pd.getHowMuchPersonShouldPay();
		if(tmpToReturn<0)
			tmpToReturn = 0;
		return tmpToReturn;
	}

	private static boolean personBWantsLessThanIHaveToReturn(
			PersonData pd, double howMuchPersonBPaid, double howMuchPersonBShouldPay, double tmpToReturn, double howMuchRefundPersonBNeeds) {
		return howMuchRefundPersonBNeeds < pd.getHowMuchPersonShouldPay() - pd.getHowMuchIPaid();
	}

	private static double splitMyReturnAmount(PersonData pd) {
		double tmpToReturn;
		tmpToReturn = pd.getToReturn() - pd.getAlreadyReturned();
		return tmpToReturn;
	}
	
	private static boolean personBNeedsMoreThanIShouldGive(double tmpToReturn, double alreadyReturned, double toReturn) {
		return alreadyReturned + tmpToReturn > toReturn;
	}
}
