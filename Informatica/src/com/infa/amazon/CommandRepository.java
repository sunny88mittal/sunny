package com.infa.amazon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandRepository {

	private static final String ARG_INFA_HOME = "_infa_home";

	private static final String ARG_DATABASE_ADDRESS = "_db_address";

	private static final String ARG_DB_USERNAME = "_db_username";

	private static final String ARG_DB_PASSWORD = "_db_password";

	private static final String ARG_DB_TYPE = "_db_type";

	private static final String ARG_DB_SERVICENAME = "_db_servicename";

	private static final String ARG_NODE_NAME = "_nodename";

	private static final String ARG_NODE_ADDRESS = "_node_address";

	private static final String ARG_LOG_DIRECTORY = "_log_directory";

	private static final String ARG_DOMAIN_NAME = "_domainname";

	private static final String ARG_DOMAIN_GATEWAY = "_domain_gateway";

	private static final String ARG_ADMINISTRATOR_NAME = "_administrator_name";

	private static final String ARG_ADMINISTRATOR_PASSWORD = "_administrator_password";

	private static String defineGatewayNodeCommand;

	private static String defineWorkerNodeCommand;

	public static String getDefineGatewayNodeCommand(String infaHome,
			String databaseAddress, String databaseUserName,
			String databasePassword, String databaseType,
			String databaseServiceName, String domainName, String nodeName,
			String nodeAddress, String logDirectory) throws IOException {
		if (defineGatewayNodeCommand == null) {
			defineGatewayNodeCommand = readFile("DefineGatewayNode.txt");
		}
		String commandTemplate = defineGatewayNodeCommand;
		commandTemplate = commandTemplate.replace(ARG_INFA_HOME, infaHome);
		commandTemplate = commandTemplate.replace(ARG_DOMAIN_NAME, domainName);
		commandTemplate = commandTemplate.replace(ARG_DATABASE_ADDRESS,
				databaseAddress);
		commandTemplate = commandTemplate.replace(ARG_DB_USERNAME,
				databaseUserName);
		commandTemplate = commandTemplate.replace(ARG_DB_PASSWORD,
				databasePassword);
		commandTemplate = commandTemplate.replace(ARG_DB_TYPE, databaseType);
		commandTemplate = commandTemplate.replace(ARG_DB_SERVICENAME,
				databaseServiceName);
		commandTemplate = commandTemplate.replace(ARG_NODE_NAME, nodeName);
		commandTemplate = commandTemplate
				.replace(ARG_NODE_ADDRESS, nodeAddress);
		commandTemplate = commandTemplate.replace(ARG_LOG_DIRECTORY,
				logDirectory);
		return commandTemplate;
	}

	public static String getDefineWorkerNodeCommand(String infaHome,
			String domainName, String nodeName, String nodeAddress,
			String gatewayAddress, String userName, String password)
			throws IOException {
		if (defineWorkerNodeCommand == null) {
			defineWorkerNodeCommand = readFile("DefineWorkerNode.txt");
		}
		String commandTemplate = defineWorkerNodeCommand;
		commandTemplate = commandTemplate.replace(ARG_INFA_HOME, infaHome);
		commandTemplate = commandTemplate.replace(ARG_DOMAIN_NAME, domainName);
		commandTemplate = commandTemplate.replace(ARG_NODE_NAME, nodeName);
		commandTemplate = commandTemplate.replace(ARG_NODE_ADDRESS, nodeAddress);
		commandTemplate = commandTemplate.replace(ARG_DOMAIN_GATEWAY,gatewayAddress);
		commandTemplate = commandTemplate.replace(ARG_ADMINISTRATOR_NAME,userName);
		commandTemplate = commandTemplate.replace(ARG_ADMINISTRATOR_PASSWORD,password);
		return commandTemplate;
	}

	private static String readFile(String filename) throws IOException {
		StringBuilder commandTemplate = new StringBuilder();
		InputStreamReader is = new InputStreamReader(
				CommandRepository.class.getResourceAsStream(filename));
		BufferedReader br = new BufferedReader(is);
		String line = null;
		while ((line = br.readLine()) != null) {
			commandTemplate.append(line);
			commandTemplate.append("\n");
		}
		return commandTemplate.toString();
	}
}
