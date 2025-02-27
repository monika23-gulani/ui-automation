package monikagulani.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import monikagulani.TestComponents.BaseTest;
import monikagulani.TestComponents.Retry;
import monikagulani.pageobjects.CartPageTst;
import monikagulani.pageobjects.CheckOut;
import monikagulani.pageobjects.Confirmation;
import monikagulani.pageobjects.OrderPage;
import monikagulani.pageobjects.ProductCatalogueTst;

public class IndependentTest extends BaseTest {

	@Test(dataProvider="getData",groups= {"Purchase"},retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String,String> map) throws IOException, InterruptedException {
		// ==
		
		ProductCatalogueTst p = lp.LoginApplication(map.get("email"), map.get("password"));
		List<WebElement> products = p.getProductList();
		p.addProductToCart(map.get("product"));

		Thread.sleep(4000);
		CartPageTst cart=p.goToCartPage();
		Boolean match = cart.verifyProductDisplay(map.get("product"));
		Assert.assertTrue(match);

		CheckOut check = cart.goToCheckOut();
		check.selectCountry("india");
		Confirmation confirm = check.submitOrder();

		String message = confirm.getConfirmationMsg();
		AssertJUnit.assertEquals(message, "THANKYOU FOR THE ORDER.");

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws IOException, InterruptedException {
		ProductCatalogueTst p = lp.LoginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage op=p.goToOrderPage();
		Assert.assertEquals(true,op.verifyOrderDisplay("QWERTY"));
		
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String , String> map = new HashMap<String,String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "QWERTY");
//		HashMap<String , String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");	
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ZARA COAT 3");
		String filename = System.getProperty("user.dir") + "//src//test//java//monikagulani//data//PurchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filename);
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
