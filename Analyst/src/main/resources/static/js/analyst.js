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
	$.get("/indicators/get/data", function(data, status) {
		updateIndicators(data);
	});
	$.get("/optionschain/get/data", function(data, status) {
		updateOptionsChain(data);
	});
	$.get("optionschain/get/interpretations", function(data, status) {
		updateOptionChainInterpretations(data);
	});
}

var updateIndicators = function(data) {
	if (data != undefined) {
		$("#indicators_table").find('tbody').empty();
		for (var i = 0; i < data.length; i++) {
			var row = "<tr align='left'>";
			row += "<td bgcolor='grey'>" + data[i].interval + "</td>";
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
		var spotPrice = data.price;
		for (var i = 0; i < data.callOptions.length; i++) {
			var callOption = data.callOptions[i];
			var putOption = data.putOptions[i];
			var strikePrice = callOption.strikePrice;
			var callColour = strikePrice <= spotPrice ? 'LightYellow' : 'White';
			var putColour = strikePrice >= spotPrice ? 'LightYellow' : 'White';

			var row = "<tr>";
			row += getColumnForIndicator(callOption.trend);
			row += "<td bgcolor=" + callColour  + ">" + callOption.interpretation + "</td>";
			row += "<td bgcolor=" + callColour  + ">" + callOption.openInterest + "</td>";
			row += "<td bgcolor=" + callColour  + ">" + callOption.openInterestChange + "</td>";
			row += "<td bgcolor=" + callColour  + ">" + callOption.LTP + "</td>";
			row += "<td bgcolor=" + callColour  + ">" + callOption.netChange + "</td>";

			row += "<td>" + callOption.strikePrice + "</td>";

			row += "<td bgcolor=" + putColour  + ">" + putOption.netChange + "</td>";
			row += "<td bgcolor=" + putColour  + ">" + putOption.LTP + "</td>";
			row += "<td bgcolor=" + putColour  + ">" + putOption.openInterestChange + "</td>";
			row += "<td bgcolor=" + putColour  + ">" + putOption.openInterest + "</td>";
			row += "<td bgcolor=" + putColour  + ">" + putOption.interpretation + "</td>";
			row += getColumnForIndicator(putOption.trend);
			row += "</tr>";
			$("#optionschain_table").find('tbody').append(row);
		}
	}
}

var updateOptionChainInterpretations = function(data) {

}

var getColumnForIndicator = function(data) {
	if (data == "SELL" || data == "Bearish") {
		return "<td bgcolor='red'>" + data + "</td>";
	} else if (data == "BUY" || data == "Bullish") {
		return "<td bgcolor='green'>" + data + "</td>";
	}
	return "<td bgcolor='LightYellow'>" + data + "</td>";
}
