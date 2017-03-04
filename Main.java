package com.cleartax.test;

import java.util.LinkedList;
import java.util.List;

import com.cleartax.base.IAgent;
import com.cleartax.base.IAlgorithm;
import com.cleartax.base.ProcessState;
import com.cleartax.base.Token;
import com.cleartax.impl.Agent;
import com.cleartax.impl.GlobalFirstServeImpl;
import com.cleartax.impl.RandomServeImpl;
import com.cleartax.impl.StageWiseFirstServeImpl;
import com.cleartax.impl.TokenManager;

public class Main {
	
	public static void main(String args[]) {
		System.out.println();
		System.out.println("Processing using Global First global server");
		simulate(new GlobalFirstServeImpl());

		System.out.println();
		System.out.println("Processing using stage wise first serve");
		simulate(new StageWiseFirstServeImpl());

		System.out.println();
		System.out.println("Processing using random serve");
		simulate(new RandomServeImpl());
	}

	private static void simulate(IAlgorithm algorithm) {
		long totalTimeTaken = 0;
		long totalRecordsProcessed = 0;

		for (int j = 0; j < 10; j++) {
			List<Token> tokens = new LinkedList<Token>();
			for (int i = 1; i <= 18; i++) {
				tokens.add(new Token(i));
			}

			TokenManager tokenManager = new TokenManager(algorithm);
			tokenManager.addTokens(tokens);
			
			List<IAgent> agentsList = new LinkedList<IAgent>();
			agentsList.addAll(createAgents(15, 5, 15,ProcessState.DOCUMENT_VERIFICATION,tokenManager));
			agentsList.addAll(createAgents(10, 3, 8,ProcessState.POLICE_VERIFICATION,tokenManager));
			agentsList.addAll(createAgents(12, 5, 7,ProcessState.BIOMETRIC_COLLECTION,tokenManager));
			
	        for (int k=1; k<=60; k++) {
	           for (IAgent agent : agentsList) {
	        	   agent.takeAction();
	           }
	           tokenManager.updateTokenTimes(k);
	        }
			
			List<Token> processedOnes = tokenManager.getProcessedTokens();
			totalRecordsProcessed += processedOnes.size();
			for (Token token : processedOnes) {
				totalTimeTaken += token.getTimeConsumed();
			}
		}

		System.out.println("----------------------");
		System.out.println("Average Processed Tokens : " + totalRecordsProcessed / 10);
		System.out.println("Average Time taken in min : " + totalTimeTaken / totalRecordsProcessed);
	}

	private static List<IAgent> createAgents(int count, int min, int max, ProcessState processState, TokenManager tokenManager) {
		List<IAgent> docProcessAgents = new LinkedList<IAgent>();
		for (int i = 1; i <= count; i++) {
			docProcessAgents.add(createAgent(min, max, processState, tokenManager));
		}
		return docProcessAgents;
	}

	private static IAgent createAgent(int min, int max,ProcessState processState, TokenManager tokenManager) {
		IAgent agent = new Agent(processState, min, max, tokenManager);
		return agent;
	}
}