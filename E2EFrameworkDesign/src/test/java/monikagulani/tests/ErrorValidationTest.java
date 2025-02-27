package monikagulani.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import monikagulani.TestComponents.BaseTest;
import monikagulani.TestComponents.Retry;
import monikagulani.pageobjects.CartPageTst;
import monikagulani.pageobjects.ProductCatalogueTst;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() {
		ProductCatalogueTst p = lp.LoginApplication("anshika@gmail.com", "Iamking232342342322@000");
		Assert.assertEquals("Incorrect email or password.",lp.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {

		ProductCatalogueTst p = lp.LoginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = p.getProductList();
		p.addProductToCart("QWERTY");

		Thread.sleep(4000);
		CartPageTst cart=p.goToCartPage();
		Boolean match = cart.verifyProductDisplay("QWERTY33");
		Assert.assertFalse(match);
	}
}
