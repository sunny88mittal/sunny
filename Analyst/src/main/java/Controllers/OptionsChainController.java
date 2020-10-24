package Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DataUtil.OptionsChainConvertor;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;
import Entities.OptionsChainMini;
import OptionsData.OptionsChainDataManager;

@RestController
@RequestMapping("/optionschain")
public class OptionsChainController {

	@Autowired
	private OptionsChainDataManager optionsChainDataManager;

	@GetMapping("/get/availableDates")
	public List<String> getAvailableDates() throws IOException, InterruptedException {
		return optionsChainDataManager.getAvailableDates(20);
	}
	
	@GetMapping("/get/data")
	public OptionsChain getOptionsChainData(@RequestParam String symbol) throws IOException, InterruptedException {
		return optionsChainDataManager.getLatestOptionsChain(symbol);
	}

	@GetMapping("/get/interpretations")
	public List<OptionsChainInterpretation> getOptionsChainInterpretations(@RequestParam String symbol)
			throws IOException, InterruptedException {
		return optionsChainDataManager.getOptionschainInterpretations(symbol);
	}

	@GetMapping("/get/timeseries")
	public List<OptionsChainMini> getOptionsChainTimeSeries(@RequestParam String symbol) {
		List<OptionsChain> optionsChainList = optionsChainDataManager.getOptionsChainTimeSeries(symbol);
		return OptionsChainConvertor.convertToMiniOptionsChain(optionsChainList);
	}
}
