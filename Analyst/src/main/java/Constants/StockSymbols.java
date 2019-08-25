package Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StockSymbols {
    
	VIX("VIX", "264969"),
	NIFTY("NIFTY", "256265"), 
	BANKNIFTY("BANKNIFTY", "260105"),
	
	ICICIBANK("ICICIBANK", "1270529"),
	SBIN("SBIN", "779521"),
	HDFCBANK("HDFCBANK", "341249"),
	AXISBANK("AXISBANK", "1510401"),
	KOTAKBANK("KOTAKBANK", "492033"),
	BANKBARODA("BANKBARODA", "1195009"),
	FEDERALBNK("FEDERALBNK", "261889"),
	INDUSINDBK("INDUSINDBK", "1346049"),
	RBLBANK("RBLBANK", "4708097"),
	PNB("PNB", "2730497"),
	YESBANK("YESBANK", "3050241"),
	UNIONBANK("UNIONBANK", "2752769"),
	CANBK("CANBK", "2763265"),
	IDBI("IDBI", "377857"),
	BANKINDIA("BANKINDIA", "1214721"),
	IBULHSGFIN("IBULHSGFIN", "7712001"),
	BAJFINANCE("BAJFINANCE", "81153"),
	SRTRANSFIN("SRTRANSFIN", "1102337"),
	RELCAPITAL("RELCAPITAL", "737793"),
	MMFIN("M&MFIN", "3400961"),
	CHOLAFIN("CHOLAFIN", "175361"),
	MUTHOOTFIN("MUTHOOTFIN", "6054401"),
	HDFC("HDFC", "340481"),
	DHFL("DHFL", "215553"),
	BAJAJFINSV("BAJAJFINSV", "4268801"),
	LTFH("L&TFH", "6386689"),
	MANAPPURAM("MANAPPURAM", "4879617"),
	UJJIVAN("UJJIVAN", "4369665"),
	LICHSGFIN("LICHSGFIN", "511233"),
	PFC("PFC", "3660545"),
	PEL("PEL", "617473"),
	RECLTD("RECLTD", "3930881"),
	ICICIPRULI("ICICIPRULI", "4774913"),
	
	TATAMOTORS("TATAMOTORS", "884737"),
	MARUTI("MARUTI", "2815745"),
	MM("M&M", "519937"),
	TVSMOTOR("TVSMOTOR", "2170625"),
	BAJAJAUTO("BAJAJ-AUTO", "4267265"),
	HEROMOTOCO("HEROMOTOCO", "345089"),
	ASHOKLEY("ASHOKLEY", "54273"),
	EICHERMOT("EICHERMOT", "232961"),
	ESCORTS("ESCORTS", "245249"),
	TATAMTRDVR("TATAMTRDVR", "4343041"),
	AMARAJABAT("AMARAJABAT", "25601"),
	EXIDEIND("EXIDEIND", "173057"),
	MOTHERSUMI("MOTHERSUMI", "1076225"),
	BALKRISIND("BALKRISIND", "85761"),
	APOLLOTYRE("APOLLOTYRE", "41729"),
	MRF("MRF", "582913"),
	
	DRREDDY("DRREDDY", "225537"),
    SUNPHARMA("SUNPHARMA", "857857"),
	BIOCON("BIOCON", "2911489"),
	CADILAHC("CADILAHC", "2029825"),
	AUROPHARMA("AUROPHARMA", "70401"),
	GLENMARK("GLENMARK", "1895937"),
	LUPIN("LUPIN", "2672641"),
	CIPLA("CIPLA", "177665"),
	TORNTPHARM("TORNTPHARM", "900609"),
	DIVISLAB("DIVISLAB", "2800641"),
	
	TATASTEEL("TATASTEEL", "895745"),
	JSWSTEEL("JSWSTEEL", "3001089"),
	VEDL("VEDL", "784129"),
	BHARATFORG("BHARATFORG", "108033"),
	NATIONALUM("NATIONALUM", "1629185"),
	JINDALSTEL("JINDALSTEL", "1723649"),
	HINDZINC("HINDZINC", "364545"),
	HINDALCO("HINDALCO", "348929"),
	SAIL("SAIL", "758529"),
	
	TECHM("TECHM", "3465729"),
	TCS("TCS", "2953217"),
	INFY("INFY", "408065"),
	TATAELXSI("TATAELXSI", "873217"),
	MINDTREE("MINDTREE", "3675137"),
	JUSTDIAL("JUSTDIAL", "7670273"),
	WIPRO("WIPRO", "969473"),
	HCLTECH("HCLTECH", "1850625"),
	HEXAWARE("HEXAWARE", "2747905"),
	NIITTECH("NIITTECH", "2955009"),
	BSOFT("BSOFT", "1790465"),
	
	BRITANNIA("BRITANNIA", "140033"),
	NESTLEIND("NESTLEIND", "4598529"),
	HINDUNILVR("HINDUNILVR", "356865"),
	MCDOWELL("MCDOWELL-N", "2674433"),
	JUBLFOOD("JUBLFOOD", "4632577"),
	DABUR("DABUR", "197633"),
	MARICO("MARICO", "1041153"),
	GODREJCP("GODREJCP", "2585345"),
	UBL("UBL", "4278529"),
	ITC("ITC", "424961"),
	TATAGLOBAL("TATAGLOBAL", "878593"),
	COLPAL("COLPAL", "3876097"),
	TITAN("TITAN", "897537"),
	BATAINDIA("BATAINDIA", "94977"),
	
	ASIANPAINT("ASIANPAINT", "60417"),
	BERGEPAINT("BERGEPAINT", "103425"),
	HINDPETRO("HINDPETRO", "359937"),
	BPCL("BPCL", "134657"),
	CASTROLIND("CASTROLIND", "320001"),
	PIDILITIND("PIDILITIND", "681985"),
	IOC("IOC", "415745"),
	OIL("OIL", "4464129"),
	ONGC("ONGC", "633601"),
	GAIL("GAIL", "1207553"),
	NTPC("NTPC", "2977281"),
	
	COALINDIA("COALINDIA", "5215745"),
	INDIGO("INDIGO", "2865921"),
	
	AMBUJACEM("AMBUJACEM", "325121"),
	ACC("ACC", "5633"),
	ULTRACEMCO("ULTRACEMCO", "2952193"),
	RAMCOCEM("RAMCOCEM", "523009"),
	SHREECEM("SHREECEM", "794369"),
	
	ZEEL("ZEEL", "975873"),
	SUNTV("SUNTV", "3431425"),
	DISHTV("DISHTV", "3721473"),
	BHARTIARTL("BHARTIARTL", "2714625"),
	IDEA("IDEA", "3677697"),
	INFRATEL("INFRATEL", "7458561"),
	
	LT("LT", "2939649"),
	DLF("DLF", "3771393"),
	
	UPL("UPL", "2889473"),
	RELIANCE("RELIANCE", "738561"),
	HAVELLS("HAVELLS", "2513665"),
	VOLTAS("VOLTAS", "951809"),
	
	APOLLOHOSP("APOLLOHOSP", "40193"),
	BEL("BEL", "98049"),
	BHEL("BHEL", "112129"),
	TORNTPOWER("TORNTPOWER", "3529217"),
	TATAPOWER("TATAPOWER", "877057"),
	TATACHEM("TATACHEM", "871681"),
	RAYMOND("RAYMOND", "731905"),
	MGL("MGL", "4488705"),
	MFSL("MFSL", "548353"),
	MCX("MCX", "7982337"),
	PETRONET("PETRONET", "2905857"),
	PAGEIND("PAGEIND", "3689729"),
	KAJARIACER("KAJARIACER", "462849"),
	IGL("IGL", "2883073"),
	IDFCFIRSTB("IDFCFIRSTB", "2863105"),
	ARVIND("ARVIND", "49409"),
	ADANIPOWER("ADANIPOWER", "4451329"),
	ADANIPORTS("ADANIPORTS", "3861249"),
	ADANIENT("ADANIENT", "6401"),
	EQUITAS("EQUITAS", "4314113"),
	ENGINERSIN("ENGINERSIN", "1256193"),
	SRF("SRF", "837889"),
	RELINFRA("RELINFRA", "141569"),
	NMDC("NMDC", "3924993"),
	SIEMENS("SIEMENS", "806401"),
	NCC("NCC", "593665"),
	NBCC("NBCC", "8042241"),
	STAR("STAR", "1887745"),
	GRASIM("GRASIM", "315393"),
	CUMMINSIND("CUMMINSIND", "486657"),
	CONCOR("CONCOR", "1215745"),
	CESC("CESC", "160769"),
	CENTURYTEX("CENTURYTEX", "160001"),
	OFSS("OFSS", "2748929"),
	PVR("PVR", "3365633"),
	POWERGRID("POWERGRID", "3834113"),
	GMRINFRA("GMRINFRA", "3463169"),
	BOSCHLTD("BOSCHLTD", "558337");
	
	public String name;

	public String code;

	private StockSymbols(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public static List<StockSymbols> getAllStocksList() {
		List<StockSymbols> stocksList = new ArrayList<StockSymbols>();
		stocksList.addAll(Arrays.asList(StockSymbols.values()));
		return stocksList;
	}
	
	public static List<StockSymbols> getAllStocksExIndiciesList() {
		List<StockSymbols> stocksList = new ArrayList<StockSymbols>();
		stocksList.addAll(Arrays.asList(StockSymbols.values()));
		stocksList.remove(StockSymbols.VIX);
		stocksList.remove(StockSymbols.NIFTY);
		stocksList.remove(StockSymbols.BANKNIFTY);
		return stocksList;
	}
	
	public static List<StockSymbols> getBankNiftyStocksList() {
		List<StockSymbols> stocksList = new ArrayList<StockSymbols>();
		stocksList.add(HDFCBANK);
		stocksList.add(ICICIBANK);
		stocksList.add(KOTAKBANK);
		stocksList.add(AXISBANK);
		stocksList.add(SBIN);
		stocksList.add(INDUSINDBK);
		stocksList.add(FEDERALBNK);
		stocksList.add(YESBANK);
		stocksList.add(RBLBANK);
		stocksList.add(BANKBARODA);
		return stocksList;
	}
	
	public static List<StockSymbols> getNiftyStocksList() {
		List<StockSymbols> stocksList = new ArrayList<StockSymbols>();
		stocksList.add(HDFCBANK);
		stocksList.add(KOTAKBANK);
		stocksList.add(ICICIBANK);
		stocksList.add(AXISBANK);
		stocksList.add(INDUSINDBK);
		stocksList.add(SBIN);
		stocksList.add(YESBANK);
		stocksList.add(BAJFINANCE);
		stocksList.add(BAJAJFINSV);
		stocksList.add(IBULHSGFIN);
		stocksList.add(HDFC);
		
		stocksList.add(INFY);
		stocksList.add(TCS);
		stocksList.add(WIPRO);
		stocksList.add(TECHM);
		stocksList.add(HCLTECH);
		
		stocksList.add(TATAMOTORS);
		stocksList.add(MM);
		stocksList.add(MARUTI);
		stocksList.add(EICHERMOT);
		stocksList.add(BAJAJAUTO);
		stocksList.add(HEROMOTOCO);
		
		stocksList.add(CIPLA);
		stocksList.add(SUNPHARMA);
		stocksList.add(DRREDDY);
		
		stocksList.add(ITC);
		stocksList.add(BRITANNIA);
		stocksList.add(HINDUNILVR);
		
		stocksList.add(RELIANCE);
		stocksList.add(LT);
		
		stocksList.add(TATASTEEL);
		stocksList.add(JSWSTEEL);
		stocksList.add(HINDALCO);
		stocksList.add(VEDL);
		stocksList.add(ONGC);
		stocksList.add(IOC);
		stocksList.add(BPCL);
		stocksList.add(GAIL);
		stocksList.add(NTPC);
		stocksList.add(COALINDIA);
		stocksList.add(POWERGRID);
		stocksList.add(ASIANPAINT);
		
		stocksList.add(INFRATEL);
		stocksList.add(ADANIPORTS);
		stocksList.add(ZEEL);
		stocksList.add(UPL);
		stocksList.add(TITAN);
		stocksList.add(ULTRACEMCO);
		stocksList.add(BHARTIARTL);
		stocksList.add(GRASIM);
		stocksList.add(TORNTPOWER);
		return stocksList;
	}
}
