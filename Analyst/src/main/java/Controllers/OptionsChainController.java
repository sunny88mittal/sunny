package Controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Data.OptionsChainDownloader;
import Entities.OptionsChain;

@RestController
public class OptionsChainController {

	@RequestMapping("/OptionsChain")
	OptionsChain hello() throws IOException, InterruptedException {
		return OptionsChainDownloader.getOptionsChain();
	}
}
