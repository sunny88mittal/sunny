package com.infa.amazon;

import java.io.IOException;

import org.testng.annotations.Test;

public class CommandsTest {
	@Test
	public void createDefineGatewayNodeCommand() throws IOException {
		System.out.println(CommandRepository.getDefineGatewayNodeCommand(
				"C:\\Informatica\\961911Worker\\source", "inx178777:1433",
				"sunny3", "sunny", "MSSQLSERVER", "ISPsunny3", "testdomain",
				"Worker", "inx178777:8765", "$INFA_HOME\\logs"));
	}

	public void createDefineWorkerNodeCommand() throws IOException {
		System.out.println(CommandRepository.getDefineWorkerNodeCommand(
				"C:\\Informatica\\961911Worker\\source", "testdomain",
				"Worker", "inx178777:9765", "inx178777:8765", "Administrator",
				"Administrator"));
	}
}
