package OptionsStrategy;

import java.util.List;

import Entities.OptionsChain;
import Entities.OptionsDataRow;

public class OptionsChainHelper {

	public static double getCEPrice(OptionsChain optionsChain, double strike) {
		double price = 0;
		for (OptionsDataRow optionsDataRow : optionsChain.callOptions) {
			if (optionsDataRow.strikePrice == strike) {
				price = optionsDataRow.LTP;
			}
		}
		return price;
	}

	public static double getPEPrice(OptionsChain optionsChain, double strike) {
		double price = 0;
		for (OptionsDataRow optionsDataRow : optionsChain.putOptions) {
			if (optionsDataRow.strikePrice == strike) {
				price = optionsDataRow.LTP;
			}
		}
		return price;
	}

	public static double getOpenInterestChange(List<OptionsDataRow> options) {
		double openInerestchange = 0;
		for (OptionsDataRow optionsDataRow : options) {
			openInerestchange += optionsDataRow.openInterestChange;
		}
		return openInerestchange;
	}
}
