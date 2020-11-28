package Constants;

public class FileConstants {

	private static String FILE_BASE_PATH = "C:\\Users\\sunmitta\\Desktop\\Perosnal\\Stocks\\";

	private static String DATA_FILE_BASE_PATH = FILE_BASE_PATH + "Data\\";

	public static String ANALYSIS_FILE_BASE_PATH = FILE_BASE_PATH + "Analysis\\";

	public static String FNO_BASE_PATH = DATA_FILE_BASE_PATH + "\\FnoData\\";

	public static String OI_BASE_PATH = DATA_FILE_BASE_PATH + "\\OIData\\";
	
	public static String STOCKS_DATA_ZERODHA_BASE_PATH = DATA_FILE_BASE_PATH + "Stocks Data\\";

	public static String STOCKS_RAW_DATA_FILE_BASE_PATH = DATA_FILE_BASE_PATH + "StocksRawData\\";
	
	public static String STOCKS_DATA_FOLDER_PATH = DATA_FILE_BASE_PATH + "StocksData\\";
	
	public static String STOCKS_DATA_FILE_NAME = STOCKS_RAW_DATA_FILE_BASE_PATH + "cmDDMONYYYYbhav.csv";

	public static String DAILY_ANALYSIS_FILE_BASE_PATH = ANALYSIS_FILE_BASE_PATH + "DailyAnalysis\\";

	public static String PAST_ANALYSIS_FILE_BASE_PATH = ANALYSIS_FILE_BASE_PATH + "PastAnalysis\\";

	public static String OPTIONS_FILE_BASE_PATH = DATA_FILE_BASE_PATH + "LiveOptionsChain\\";

	public static String FNO_FILE_FORMAT = "foDATEMONTHYEARbhav.csv";
	
	public static String STOCKS_POINTS_FILE = FILE_BASE_PATH + "Data\\Metadata\\StockPoints.csv";
}
