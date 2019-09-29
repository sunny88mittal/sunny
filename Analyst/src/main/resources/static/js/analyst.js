var infiniteLoader = function() {
	setDefaultValues();
	setInterval(reloadData, 3 * 60 * 1000);
}

var reloadData = function() {
	var date = new Date();
	var day = date.getDay();
	var hour = date.getHours();
	var minute = date.getMinutes();

	if (day > 0 && day < 6) {
		if ((hour >= 9 && minute >= 14) || (hour <= 15 && minute <= 32)) {
			// Call to get Indicators

			// Call to get Options Data

			// Call to get Option Chain Interpretations
		}

	}
}

var setDefaultValues = function() {
	$.get(INDICATORS_DATA, function(data, status) {
		updateIndicators(data);
	});
	$.get(OPTIONS_CHAIN_DATA, function(data, status) {
		updateOptionsChain(data);
	});
	$.get(OPTIONS_CHAIN_INTERPRETATION, function(data, status) {
		updateOptionChainInterpretations(data);
	});
}

var updateIndicators = function(data) {
	if (data != undefined) {
		$("#indicators_table").find('tbody').empty();
		for (var i = 0; i < data.length; i++) {
			var row = "<tr align='left'>";
			row += getTableColumnWithColour(data[i].interval, COLOUR_GREY);
			row += getColumnForIndicator(data[i].indicators.SUPERTREND);
			row += getColumnForIndicator(data[i].indicators.PSAR);
			row += getColumnForIndicator(data[i].indicators.MACD);
			row += "</tr>";
			$("#indicators_table").find('tbody').append(row);
		}
	}
}

var updateOptionsChain = function(data) {
	if (data != undefined) {
		$("#optionschain_table").find('tbody').empty();
		var symbol = data.symbol;
		var spotPrice = data.price;
		for (var i = 0; i < data.callOptions.length; i++) {
			var callOption = data.callOptions[i];
			var putOption = data.putOptions[i];
			var strikePrice = callOption.strikePrice;
			var callColour = strikePrice <= spotPrice ? COLOUR_LIGHT_YELLOW
					: COLOUR_WHITE;
			var putColour = strikePrice >= spotPrice ? COLOUR_LIGHT_YELLOW
					: COLOUR_WHITE;

			var row = "<tr>";

			row += getColumnForIndicator(callOption.trend, callColour);
			row += getTableColumnWithColour(callOption.interpretation,
					getInterpretationColour(callOption.interpretation,
							callOption.optionType, callColour));
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
			row += getTableColumnWithColour(putOption.interpretation,
					getInterpretationColour(putOption.interpretation,
							putOption.optionType, putColour));
			row += getColumnForIndicator(putOption.trend, putColour);

			row += "</tr>";

			$("#optionschain_table").find('tbody').append(row);
		}
	}
}

var updateOptionChainInterpretations = function(data) {

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
	return "<td bgcolor=" + colour + ">" + data + "</td>";
}
