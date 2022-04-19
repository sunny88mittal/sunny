package OptionsData;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import Constants.TradeConstants;
import Entities.NSEOptionsChain;
import Entities.NSEOptionsData;
import Entities.NSEOptionsDataRow;
import Entities.OptionsChain;
import Entities.OptionsDataRow;

public class NewOptionsChainBuilder {

	public static OptionsChain getOptionsChain(String fileContents, long lastModifiedTime) {
		Gson gson = new Gson();
		NSEOptionsChain nseOptionsChain = gson.fromJson(fileContents, NSEOptionsChain.class);
		OptionsChain optionsChain = convertToOptionsChain(nseOptionsChain, lastModifiedTime);
		return optionsChain;
	}

	private static OptionsChain convertToOptionsChain(NSEOptionsChain nseOptionsChain, long lastModifiedTime) {
		NSEOptionsDataRow optionsDataRowSample = nseOptionsChain.filtered.data.get(0);

		OptionsChain optionsChain = new OptionsChain();
		optionsChain.callOI = nseOptionsChain.filtered.CE.totOI;
		optionsChain.callOIVol = nseOptionsChain.filtered.CE.totVol;
		optionsChain.putOI = nseOptionsChain.filtered.PE.totOI;
		optionsChain.putOIVol = nseOptionsChain.filtered.PE.totVol;
		optionsChain.price = nseOptionsChain.records.index.last;
		optionsChain.expiryDate = optionsDataRowSample.expiryDate;
		if (optionsDataRowSample.CE != null) {
			optionsChain.symbol = optionsDataRowSample.CE.underlying;
		} else {
			optionsChain.symbol = optionsDataRowSample.PE.underlying;
		}
		
		optionsChain.timeStamp = lastModifiedTime;

		optionsChain.callOptions = getCEList(nseOptionsChain);
		optionsChain.putOptions = getPEList(nseOptionsChain);

		return optionsChain;
	}

	private static List<OptionsDataRow> getCEList(NSEOptionsChain nseOptionsChain) {
		List<OptionsDataRow> ceOptionsList = new ArrayList<OptionsDataRow>();

		for (NSEOptionsDataRow nseOptionsDataRow : nseOptionsChain.filtered.data) {
			if (nseOptionsDataRow.CE == null || nseOptionsDataRow.PE == null) {
				continue;
			}

			NSEOptionsData nseOptionsData = nseOptionsDataRow.CE;

			OptionsDataRow optionsDataRow = new OptionsDataRow();
			optionsDataRow.strikePrice = nseOptionsData.strikePrice;
			optionsDataRow.optionType = TradeConstants.CALL;
			optionsDataRow.openInterest = nseOptionsData.openInterest;
			optionsDataRow.openInterestChange = nseOptionsData.changeinOpenInterest;
			optionsDataRow.volume = nseOptionsData.totalTradedVolume;
			optionsDataRow.IV = nseOptionsData.impliedVolatility;
			optionsDataRow.LTP = nseOptionsData.lastPrice;
			optionsDataRow.netChange = (int)nseOptionsData.change;
			optionsDataRow.bidQty = nseOptionsData.bidQty;
			optionsDataRow.bidPrice = nseOptionsData.bidprice;
			optionsDataRow.askQty = nseOptionsData.askQty;
			optionsDataRow.askPrice = nseOptionsData.askPrice;
			ceOptionsList.add(optionsDataRow);
		}

		return ceOptionsList;
	}

	private static List<OptionsDataRow> getPEList(NSEOptionsChain nseOptionsChain) {
		List<OptionsDataRow> peOptionsList = new ArrayList<OptionsDataRow>();

		for (NSEOptionsDataRow nseOptionsDataRow : nseOptionsChain.filtered.data) {
			if (nseOptionsDataRow.CE == null || nseOptionsDataRow.PE == null) {
				continue;
			}
			NSEOptionsData nseOptionsData = nseOptionsDataRow.PE;

			OptionsDataRow optionsDataRow = new OptionsDataRow();
			optionsDataRow.strikePrice = nseOptionsData.strikePrice;
			optionsDataRow.optionType = TradeConstants.PUT;
			optionsDataRow.openInterest = nseOptionsData.openInterest;
			optionsDataRow.openInterestChange = nseOptionsData.changeinOpenInterest;
			optionsDataRow.volume = nseOptionsData.totalTradedVolume;
			optionsDataRow.IV = nseOptionsData.impliedVolatility;
			optionsDataRow.LTP = nseOptionsData.lastPrice;
			optionsDataRow.netChange = (int)nseOptionsData.change;
			optionsDataRow.bidQty = nseOptionsData.bidQty;
			optionsDataRow.bidPrice = nseOptionsData.bidprice;
			optionsDataRow.askQty = nseOptionsData.askQty;
			optionsDataRow.askPrice = nseOptionsData.askPrice;
			peOptionsList.add(optionsDataRow);
		}

		return peOptionsList;
	}
}
