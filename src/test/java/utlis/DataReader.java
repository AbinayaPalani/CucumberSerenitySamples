package utlis;

import java.net.URL;
import java.util.ArrayList;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

public class DataReader {
	

    public static String SheetURL1 = "https://spreadsheets.google.com/feeds/list/1ZbYzlALEl7K2TXKX1E_KiPPYa170OLULjIcrhexJ_aY/1/public/values";

    public static SpreadsheetService service;
	
  //ArrayList of createUser 	

    public ArrayList<String> PaymentTypeCreateUser 			= 			new ArrayList<String>(); 
    public ArrayList<String> Amounts	 					= 			new ArrayList<String>();
    public ArrayList<String> creditAmounts 					= 			new ArrayList<String>();
    public ArrayList<String> debitAmounts 					= 			new ArrayList<String>();
    public ArrayList<String> brandIds						=			new ArrayList<String>();
    public ArrayList<String> cardNum						=			new ArrayList<String>();
    public ArrayList<String> expDate						=			new ArrayList<String>();
    public ArrayList<String> vaultIds						=			new ArrayList<String>();
    public ArrayList<String> btCustIds						=			new ArrayList<String>();
    
	//Create a user sheet reader
			public void createUserSheet() throws Throwable
			{
				service = new SpreadsheetService("User-API");
				URL url1 = new URL(SheetURL1);
				ListFeed feed = service.getFeed(url1, ListFeed.class);
				for(ListEntry le: feed.getEntries()){
					
					CustomElementCollection collection = le.getCustomElements();
					
					String brandId = collection.getValue("BrandId");
					brandIds.add(brandId);
					
					String paymentMethod = collection.getValue("PaymentType");
					PaymentTypeCreateUser.add(paymentMethod);
					
					String amount = collection.getValue("Amount");
					Amounts.add(amount);
					
					String creditAmount = collection.getValue("creditAmount");
					creditAmounts.add(creditAmount);
					
					String debitAmount = collection.getValue("debitAmount");
					debitAmounts.add(debitAmount);
					
					String cardNumber = collection.getValue("CardNumber");
					cardNum.add(cardNumber);
					
					String expirationDate = collection.getValue("ExpDate");
					expDate.add(expirationDate);
					
					String vaultId = collection.getValue("valutId");
					vaultIds.add(vaultId);
					
					String btCustomerId = collection.getValue("CustomerID");
					btCustIds.add(btCustomerId);
					
				}
			}

}
