var PARAM_SYMBOL = "symbol";
var PARAM_DATE = "date";
var INDICATORS_DATA = "/indicators/get/data";
var OPTIONS_CHAIN_DATA = "/optionschain/get/data";
var OPTIONS_CHAIN_INTERPRETATION = "optionschain/get/interpretations";
var OPTIONS_CHAIN_TIMESERIES = "optionschain/get/timeseries";
var AVIALABLE_DATES_URL = "optionschain/get/availableDates";

var getURLWithParams = function(baseURL, symbol, date) {
	return baseURL + "?" + PARAM_SYMBOL + "=" + symbol + "&" + PARAM_DATE + "=" + date;
}