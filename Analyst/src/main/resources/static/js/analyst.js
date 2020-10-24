var optionsChainBarChart;
var optionsChainMiniData;

var pcrChart;
var optionsCEOITimeSeriesChart;
var optionsCEPriceTimeSeriesChart;
var optionsPEOITimeSeriesChart;
var optionsPEPriceTimeSeriesChart;

var selectedStrikeCEOptionChart;
var selectedStrikePEOptionChart;
var selectedStrikeOIChangeChart;
var selectedStrikeOIChart;
var selectedStrikeIVChart;
var selectedStrikePCRChart;

var infiniteLoader = function() {
	var date = new Date();
	date = date.toISOString().split("T")[0];
	$(TRADING_DAY).val(date);
	updateData();
	setInterval(reloadData, 3 * 60 * 1000);
}

var reloadData = function() {
	var date = new Date();
	var day = date.getDay();
	var hour = date.getHours();
	var minute = date.getMinutes();

	if (day > 0 && day < 6) {
		if ((hour >= 9 && minute >= 14) || (hour <= 15 && minute <= 32)) {
			updateData();
		}
	}
}

/**
 * Function which updates all the data
 */
var updateData = function() {
	// Get the selected symbol from select dropdown
	var selectedSymbol = $(SYMBOL_SELECTOR).find(":selected").text();

	//var indicatorsURL = getURLWithParams(INDICATORS_DATA, selectedSymbol);
	
	var optionsChainDataURL = getURLWithParams(OPTIONS_CHAIN_DATA,
			selectedSymbol);
	var optionsChainInterpretationURL = getURLWithParams(
			OPTIONS_CHAIN_INTERPRETATION, selectedSymbol);
	var optionsChainTimeSeriesURL = getURLWithParams(OPTIONS_CHAIN_TIMESERIES,
			selectedSymbol);

	/*$.get(indicatorsURL, function(data, status) {
		updateIndicators(data);
	});*/
	
	var selectedStrike;
	
	$.get(optionsChainDataURL, function(data, status) {
		updateStrikeButtons(data);
		updateOptionsChain(data);
		updateOptionsChainBarChart(data);
		selectedStrike = data.price - (data.price % 100);
	});
	
	$.get(optionsChainInterpretationURL, function(data, status) {
		updateOptionChainInterpretations(data);
	});
	
	$.get(optionsChainTimeSeriesURL, function(data, status) {
		optionsChainMiniData = data;
		updateStrikeCharts(selectedStrike);
	});
}

/**
 * Function to update the indicators
 * 
 * @data Indicators data
 */
var updateIndicators = function(data) {
	if (data != undefined) {
		$(INDICATORS_TABLE).find(TABLE_BODY).empty();
		for (var i = 0; i < data.length; i++) {
			var row = "<tr>";
			row += getTableColumnWithColour(data[i].interval, COLOUR_GREY);
			row += getColumnForIndicator(data[i].indicators.SUPERTREND);
			row += getColumnForIndicator(data[i].indicators.PSAR);
			row += getColumnForIndicator(data[i].indicators.MACD);
			row += "</tr>";
			$(INDICATORS_TABLE).find(TABLE_BODY).append(row);
		}
	}
}

/**
 * Function to update the options chain table
 * 
 * @data latest option chain data
 */
var updateOptionsChain = function(data) {
	if (data != undefined) {

		var symbol = data.symbol;
		var spotPrice = data.price;
		var maxPain = data.maxPainAt;

		// Range for indexes 2.5%, for stocks 6%
		var range = (spotPrice * 3) / 100;
		if (symbol != "NIFTY" && symbol != "BANKNIFTY") {
			range = (spotPrice * 3) / 100;
		}

		$(SYMBOL).text(symbol);
		$(SYMBOL_VALUE).text(spotPrice.split(".")[0]);
		$(MAXPAIN_VALUE).text(maxPain);

		$(OPTIONS_CHAIN_TABLE).find(TABLE_BODY).empty();
		for (var i = 0; i < data.callOptions.length; i++) {
			var callOption = data.callOptions[i];
			var putOption = data.putOptions[i];
			var strikePrice = callOption.strikePrice;

			// Show data for strikes within range only
			if (Math.abs(strikePrice - spotPrice) > range) {
				continue;
			}

			var callColour = strikePrice <= spotPrice ? COLOUR_LIGHT_YELLOW
					: COLOUR_WHITE;
			var putColour = strikePrice >= spotPrice ? COLOUR_LIGHT_YELLOW
					: COLOUR_WHITE;

			var row = "<tr>";

			row += getColumnForIndicator(callOption.trend, callColour);
			row += getTableColumnWithColour(callOption.interpretation,
					getInterpretationColour(callOption.interpretation,
							callOption.optionType, callColour));
			row += getTableColumnWithColour(callOption.IV, callColour);
			row += getTableColumnWithColour(callOption.openInterest, callColour);
			row += getTableColumnWithColour(callOption.openInterestChange,
					callColour);
			row += getTableColumnWithColour(callOption.LTP, callColour);
			row += getTableColumnWithColour(callOption.netChange, callColour);

			row += getTableColumnWithColour(callOption.strikePrice);

			row += getTableColumnWithColour(putOption.netChange, putColour);
			row += getTableColumnWithColour(putOption.LTP, putColour);
			row += getTableColumnWithColour(putOption.openInterestChange,
					putColour);
			row += getTableColumnWithColour(putOption.openInterest, putColour);
			row += getTableColumnWithColour(putOption.IV, putColour);
			row += getTableColumnWithColour(putOption.interpretation,
					getInterpretationColour(putOption.interpretation,
							putOption.optionType, putColour));
			row += getColumnForIndicator(putOption.trend, putColour);

			row += "</tr>";

			$(OPTIONS_CHAIN_TABLE).find(TABLE_BODY).append(row);
		}
	}
}

/**
 * Create the strikes buttons
 * 
 * @data options chain data
 * 
 */
var updateStrikeButtons = function(data) {
	if (data != undefined) {
		$(STRIKE_BUTTONS).empty();
		var spotPrice = data.price;
		var range = (spotPrice * 6) / 100
		for (var i = 0; i < data.callOptions.length; i++) {
			var strikePrice = data.callOptions[i].strikePrice;
			if (Math.abs(strikePrice - spotPrice) <= range) {
				var link = '<div class="col-lg-2 text-center"><a href="#" onclick = updateStrikeCharts(\'' + strikePrice  +'\')>' 
					+ strikePrice + '</a></div>';
				$(STRIKE_BUTTONS).append(link);
			}
		}
	}
}

/**
 * Update the strike charts
 * 
 * @strike Strike Price
 * @optionsChain Options Chain 
 */
var updateStrikeCharts = function(strike) {
	if (optionsChainMiniData != undefined && optionsChainMiniData.length > 0) {
		var length = optionsChainMiniData.length;
		
		// Variables to hold data points
		var time = [];
		
		var ceOpenInterest = [];
		var ceOpenInterestChange = [];
		var cePrice = [];
		var ceIV = [];
		
		var peOpenInterest = [];
		var peOpenInterestChange = [];
		var pePrice = [];
		var peIV = [];
		
		var pcr = [];
		
		// Prepare time data for the charts
		for (var i = 0; i < length; i++) {
			time.push(optionsChainMiniData[i].time.split(".")[0]);
		}
		
		//Prepare data for the charts
		for (var i = 0; i < length; i++) {
			var datai = optionsChainMiniData[i];
			
			var callOptions = datai.callOptions;	
			for (var j = 0; j < callOptions.length; j++) {
				var callOption = callOptions[j];
				var strikePrice = callOption.strikePrice;
				if (strikePrice == strike) {
					ceOpenInterest.push(callOption.openInterest);
					ceOpenInterestChange.push(callOption.openInterestChange);
					cePrice.push(callOption.LTP);
					ceIV.push(callOption.IV);
				}
			}
			
			var putOptions = datai.putOptions;	
			for (var j = 0; j < putOptions.length; j++) {
				var putOption = putOptions[j];
				var strikePrice = putOption.strikePrice;
				if (strikePrice == strike) {
					peOpenInterest.push(putOption.openInterest);
					peOpenInterestChange.push(putOption.openInterestChange);
					pePrice.push(putOption.LTP);
					peIV.push(putOption.IV);
				}
			}
		}
		
		for (var i=0; i<length; i++) {
			pcr.push(peOpenInterest[i] / ceOpenInterest[i]);
		}
		
		// Update the ce oi vs price chart
		var ceOpenInterestDs = getMultiAxisDataset('CE OI', ceOpenInterest, CHART_TYPE_LINE, YAXIS1, null, COLOUR_RED);
		var cePriceDs = getMultiAxisDataset('CE Price', cePrice, CHART_TYPE_LINE, YAXIS2, null, COLOUR_BLACK);
		ctx = $(SELECTED_STRIKE_CE_OPTION_CHART);
		if (selectedStrikeCEOptionChart) {
			selectedStrikeCEOptionChart.destroy();
		}
		var datasets = [];
		datasets.push(ceOpenInterestDs);
		datasets.push(cePriceDs);
		selectedStrikeCEOptionChart = getMultiAxisChart(ctx, datasets, time, strike);
		
		
		// Update the pe oi vs price chart
		var peOpenInterestDs = getMultiAxisDataset('PE OI', peOpenInterest, CHART_TYPE_LINE, YAXIS1, null, COLOUR_RED);
		var pePriceDs = getMultiAxisDataset('PE Price', pePrice, CHART_TYPE_LINE, YAXIS2, null, COLOUR_BLACK);
		ctx = $(SELECTED_STRIKE_PE_OPTION_CHART);
		if (selectedStrikePEOptionChart) {
			selectedStrikePEOptionChart.destroy();
		}
		datasets = [];
		datasets.push(peOpenInterestDs);
		datasets.push(pePriceDs);
		selectedStrikePEOptionChart = getMultiAxisChart(ctx, datasets, time, strike);
		
		//Update the ce and pe oi chart
		var ceOIDs = getMultiAxisDataset("CE OI", ceOpenInterest, CHART_TYPE_LINE, YAXIS1, null, COLOUR_GREEN);
		var peOIDs = getMultiAxisDataset("PE OI", peOpenInterest, CHART_TYPE_LINE, YAXIS2, null, COLOUR_RED);
		ctx = $(SELECTED_STRIKE_OI_CHART);
		if (selectedStrikeOIChart) {
			selectedStrikeOIChart.destroy();
		}
		datasets = [];
		datasets.push(ceOIDs);
		datasets.push(peOIDs);
		selectedStrikeOIChart = getMultiAxisChart(ctx, datasets, time, strike);
		
		//Update the ce and pe oi change chart
		var ceOIChangeDs = getMultiAxisDataset("CE OI Change", ceOpenInterestChange, CHART_TYPE_LINE, YAXIS1, null, COLOUR_GREEN);
		var peOIChangeDs = getMultiAxisDataset("PE OI Change", peOpenInterestChange, CHART_TYPE_LINE, YAXIS2, null, COLOUR_RED);
		ctx = $(SELECTED_STRIKE_OI_CHANGE_CHART);
		if (selectedStrikeOIChangeChart) {
			selectedStrikeOIChangeChart.destroy();
		}
		datasets = [];
		datasets.push(ceOIChangeDs);
		datasets.push(peOIChangeDs);
		selectedStrikeOIChangeChart = getMultiAxisChart(ctx, datasets, time, strike);
		
		//Update the ce and pe IV chart
		var ceIVDs = getDataset("CE IV", ceIV, CHART_TYPE_LINE, null, COLOUR_GREEN);
		var peIVDs = getDataset("PE IV", peIV, CHART_TYPE_LINE, null, COLOUR_RED);
		ctx = $(SELECTED_STRIKE_IV_CHART);
		if (selectedStrikeIVChart) {
			selectedStrikeIVChart.destroy();
		}
		datasets = [];
		datasets.push(ceIVDs);
		datasets.push(peIVDs);
		selectedStrikeIVChart = getChart(ctx, CHART_TYPE_LINE, datasets, time, strike);
		
		
		//Update the pcr chart
		var pcrDs =  getDataset("PCR", pcr, CHART_TYPE_LINE, null, COLOUR_BLACK);
		ctx = $(SELECTED_STRIKE_PCR_CHART);
		if (selectedStrikePCRChart) {
			selectedStrikePCRChart.destroy();
		}
		datasets = [];
		datasets.push(pcrDs);
		selectedStrikePCRChart = getChart(ctx, CHART_TYPE_LINE, datasets, time, strike);
	}
}

/**
 * Draws the options chain bar chart
 * 
 * @data options chain data
 */
var updateOptionsChainBarChart = function(data) {
	if (data != undefined) {
		var callOptionsData = [];
		var putOptionsData = [];
		var strikesList = [];
		for (var i = 0; i < data.callOptions.length; i++) {
			var callOpenInterest = data.callOptions[i].openInterest;
			var putOpenInterest = data.putOptions[i].openInterest;
			var strikePrice = data.callOptions[i].strikePrice;

			callOptionsData.push(callOpenInterest);
			putOptionsData.push(putOpenInterest);
			strikesList.push(strikePrice);
		}

		var ctx = $(OPEN_INTEREST_BAR_CHART);
		if (optionsChainBarChart) {
			optionsChainBarChart.destroy();
		}

		var datasets = [];
		datasets.push(getDataset("Call OI", callOptionsData, CHART_TYPE_BAR, COLOUR_RED));
		datasets.push(getDataset("Put OI", putOptionsData, CHART_TYPE_BAR, COLOUR_BLACK));
		optionsChainBarChart = getChart(ctx, CHART_TYPE_BAR, datasets,
				strikesList, "OI Bar Chart");
	}
}

/**
 * Updates option chain interpretations table and draws the pcr chart
 * 
 * @data Option Chain Interpretations
 */
var updateOptionChainInterpretations = function(data) {
	if (data != undefined) {
		// Update pcr
		var length = data.length;
		var lastInterpretation = data[length - 1];
		$(PCR_VALUE).text(lastInterpretation.pcr.toFixed(4));
		$(PCR_SIGNAL).text(lastInterpretation.signal);
		$(PCR_SIGNAL).removeClass("pcrbuy");
		$(PCR_SIGNAL).removeClass("pcrsell");
		if (lastInterpretation.signal == TRADE_ACTION_BUY) {
			$(PCR_SIGNAL).addClass("pcrbuy");
		} else {
			$(PCR_SIGNAL).addClass("pcrsell");
		}

		// Prepare data for the chart
		var shortEMA = [];
		var longEMA = [];
		var pcrValues = [];
		var time = [];
		for (var i = 0; i < length; i++) {
			var interpretation = data[i];
			shortEMA.push(interpretation.shortEMA);
			longEMA.push(interpretation.longEMA);
			pcrValues.push(interpretation.pcr);
			time.push(interpretation.time.split(".")[0]);
		}

		// Get Context
		var ctx = $(PCR_CHART);
		if (pcrChart != undefined) {
			pcrChart.destroy();
		}

		// Create datasets
		var datasets = [];
		datasets.push(getDataset('PCR', pcrValues, CHART_TYPE_BAR, COLOUR_RED));
		datasets.push(getDataset('Short EMA', shortEMA, CHART_TYPE_LINE, null,
				COLOUR_GREEN));
		datasets.push(getDataset('Long EMA', longEMA, CHART_TYPE_LINE, null,
				COLOUR_BLACK));

		// Create Chart
		pcrChart = getChart(ctx, CHART_TYPE_BAR, datasets, time, "Total PCR");
	}
}

var getInterpretationColour = function(interpretation, optionType,
		selectedColour) {
	var colour = COLOUR_GREEN;

	if (interpretation == NO_DATA) {
		return selectedColour;
	}

	if (optionType == OPTION_TYPE_CALL) {
		if (interpretation == INTERPRETATION_SHORT_BUILDUP
				|| interpretation == INTERPRETATION_LONG_UNWINDING) {
			colour = COLOUR_RED;
		}
	}

	if (optionType == OPTION_TYPE_PUT) {
		if (interpretation == INTERPRETATION_LONG_BUILDUP
				|| interpretation == INTERPRETATION_SHORT_UNWINDING) {
			colour = COLOUR_RED;
		}
	}

	return colour;
}

var getColumnForIndicator = function(data, selectedColour) {
	var colour = COLOUR_LIGHT_YELLOW;
	if (data == TRADE_ACTION_SELL || data == TREND_BEARISH) {
		colour = COLOUR_RED;
	} else if (data == TRADE_ACTION_BUY || data == TREND_BULLISH) {
		colour = COLOUR_GREEN;
	}

	if (data == NO_DATA) {
		colour = selectedColour;
	}

	return getTableColumnWithColour(data, colour);
}

var getTableColumnWithColour = function(data, colour) {
	if (colour == undefined) {
		colour = COLOUR_WHITE;
	}
	return "<td align='center' bgcolor=" + colour + ">" + data + "</td>";
}
