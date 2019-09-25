package Controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Data.RealTimeIndicatorProvider;

@RestController
@RequestMapping("/indicators")
public class IndicatorsController {

	@GetMapping("/get/data")
	public Map<String, Map<String, String>> getIndicators() throws IOException {
		return RealTimeIndicatorProvider.getIndicatorsFor();
	}
}
