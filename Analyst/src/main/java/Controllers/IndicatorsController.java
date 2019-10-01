package Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Constants.StockSymbols;
import Data.RealTimeIndicatorProvider;
import Entities.RealTimeIndicatorValues;

@RestController
@RequestMapping("/indicators")
public class IndicatorsController {

	@GetMapping("/get/data")
	public List<RealTimeIndicatorValues> getIndicators(@RequestParam String symbol) throws IOException {
		StockSymbols stockSymbol = StockSymbols.BANKNIFTYFUT;
		if (symbol == StockSymbols.NIFTY.name) {
			stockSymbol = StockSymbols.NIFTYFUT;
		}
		return RealTimeIndicatorProvider.getIndicatorsFor(stockSymbol);
	}
}
