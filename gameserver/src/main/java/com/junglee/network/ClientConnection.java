package com.junglee.network;

import com.google.gson.Gson;
import com.junglee.response.Packet;

public class ClientConnection {

	public void sendPacket (Packet packet) {
		Gson gson = new Gson();
		String packetJson = gson.toJson(packet);
		//Will send data on the actual connection
	}
}
