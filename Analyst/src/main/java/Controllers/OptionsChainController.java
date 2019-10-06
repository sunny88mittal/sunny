package Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Data.OptionsChainDataManager;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

@RestController
@RequestMapping("/optionschain")
public class OptionsChainController {

	@GetMapping("/get/data")
	public OptionsChain getOptionsChainData(@RequestParam String symbol) throws IOException, InterruptedException {
		return OptionsChainDataManager.getLatestOptionsChain(symbol);
	}

	@GetMapping("/get/interpretations")
	public List<OptionsChainInterpretation> getOptionsChainInterpretations(@RequestParam String symbol)
			throws IOException, InterruptedException {
		return OptionsChainDataManager.getOptionschainInterpretations(symbol);
	}

	@GetMapping("/get/timeseries")
	public List<OptionsChain> getOptionsChainTimeSeries(@RequestParam String symbol) {
		return OptionsChainDataManager.getOptionsChainTimeSeries(symbol);
	}
}
