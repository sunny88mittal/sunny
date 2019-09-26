package Data;

public class URLConstants {

	public static final String FNO_URL = "https://www.nseindia.com/content/historical/DERIVATIVES/2019/MONTH/foDATEMONTHYEARbhav.csv.zip";

	public static final String DATA_URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3210&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=2014-01-01" + "&to=TODATE&" + "ciqrandom=1564301727399";

	public static final String REAL_TIME_URL = "https://kitecharts-aws.zerodha.com" + "/api/chart/SYMBOL/INTERVAL?"
			+ "public_token=px7RlNQX3W7rg9Vm2WOUDbGzo6WBxUqy&" + "user_id=YF3210&oi=1" + "&api_key=kitefront&"
			+ "access_token=&" + "from=FROMDATE" + "&to=TODATE&" + "ciqrandom=1564301727399";

	public static final String OPTIONS_CHAIN_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?"
			+ "symbolCode=-10006&symbol=SYMBOL&symbol=SYMBOL&instrument=OPTIDX&date=DATE&segmentLink=17&symbolCount=2&segmentLink=17";

}
