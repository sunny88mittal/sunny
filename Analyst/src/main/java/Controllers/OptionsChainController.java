package Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Data.OptionsChainDownloader;
import Entities.OptionsChain;
import Entities.OptionsChainInterpretation;

@RestController
@RequestMapping("/optionschain")
public class OptionsChainController {

	@RequestMapping("/data")
	OptionsChain getOptionsChainData() throws IOException, InterruptedException {
		return OptionsChainDownloader.getOptionsChain();
	}

	@RequestMapping("/interpretations")
	List<OptionsChainInterpretation> getOptionsChainInterpretations() throws IOException, InterruptedException {
		return OptionsChainDownloader.getOptionschainInterpretations();
	}
}
