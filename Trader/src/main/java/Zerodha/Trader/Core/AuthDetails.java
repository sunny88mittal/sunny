package Zerodha.Trader.Core;

public class AuthDetails {

	public String userId;

	public String accessToken;

	public String publicToken;

	public AuthDetails(String userId, String accesstoken, String publicToken) {
		super();
		this.userId = userId;
		this.accessToken = accesstoken;
		this.publicToken = publicToken;
	}
}
