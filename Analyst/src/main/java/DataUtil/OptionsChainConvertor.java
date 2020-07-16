package DataUtil;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import Entities.OptionsChain;
import Entities.OptionsChainMini;
import Entities.OptionsDataRow;
import Entities.OptionsDataRowMini;

public class OptionsChainConvertor {

	public static List<OptionsChainMini> convertToMiniOptionsChain(List<OptionsChain> optionsChainList) {
		List<OptionsChainMini> miniOptionsChainList = new ArrayList<OptionsChainMini>();

		for (OptionsChain optionsChain : optionsChainList) {
			OptionsChainMini optionsChainMini = new OptionsChainMini();
			optionsChainMini.symbol = optionsChain.symbol;
			optionsChainMini.price = optionsChain.price;
			optionsChainMini.time = LocalTime.ofInstant(Instant.ofEpochMilli(optionsChain.timeStamp),
					ZoneId.systemDefault());

			// Call Options
			for (OptionsDataRow optionsDataRow : optionsChain.callOptions) {
				OptionsDataRowMini optionsDataRowMini = new OptionsDataRowMini();
				optionsDataRowMini.optionType = optionsDataRow.optionType;
				optionsDataRowMini.strikePrice = optionsDataRow.strikePrice;
				optionsDataRowMini.openInterest = optionsDataRow.openInterest;
				optionsDataRowMini.openInterestChange = optionsDataRow.openInterestChange;
				optionsDataRowMini.LTP = optionsDataRow.LTP;
				optionsDataRowMini.netChange = optionsDataRow.netChange;
				optionsDataRowMini.IV = optionsDataRow.IV;
				optionsChainMini.callOptions.add(optionsDataRowMini);
			}

			// Put Options
			for (OptionsDataRow optionsDataRow : optionsChain.putOptions) {
				OptionsDataRowMini optionsDataRowMini = new OptionsDataRowMini();
				optionsDataRowMini.optionType = optionsDataRow.optionType;
				optionsDataRowMini.strikePrice = optionsDataRow.strikePrice;
				optionsDataRowMini.openInterest = optionsDataRow.openInterest;
				optionsDataRowMini.openInterestChange = optionsDataRow.openInterestChange;
				optionsDataRowMini.LTP = optionsDataRow.LTP;
				optionsDataRowMini.netChange = optionsDataRow.netChange;
				optionsDataRowMini.IV = optionsDataRow.IV;
				optionsChainMini.putOptions.add(optionsDataRowMini);
			}

			miniOptionsChainList.add(optionsChainMini);
		}

		return miniOptionsChainList;
	}
}
