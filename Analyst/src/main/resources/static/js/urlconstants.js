var PARAM_SYMBOL = "symbol";
var INDICATORS_DATA = "/indicators/get/data";
var OPTIONS_CHAIN_DATA = "/optionschain/get/data";
var OPTIONS_CHAIN_INTERPRETATION = "optionschain/get/interpretations";

var getURLWithParams = function(baseURL, paramValue) {
	return baseURL + "?" + PARAM_SYMBOL + "=" + paramValue;
}