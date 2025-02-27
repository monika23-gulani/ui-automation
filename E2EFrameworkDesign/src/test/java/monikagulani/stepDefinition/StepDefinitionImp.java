package monikagulani.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import monikagulani.TestComponents.BaseTest;
import monikagulani.pageobjects.CartPageTst;
import monikagulani.pageobjects.CheckOut;
import monikagulani.pageobjects.Confirmation;
import monikagulani.pageobjects.LandingPageTst;
import monikagulani.pageobjects.ProductCatalogueTst;

public class StepDefinitionImp extends BaseTest{
	
	public LandingPageTst lp;
	ProductCatalogueTst p;
	Confirmation confirm;
	
	@Given("I landed on ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password) {
	    // Write code here that turns the phrase above into concrete actions
		 p = lp.LoginApplication(username, password);
	}

	@When("^I add product (.+) from cart$")
	public void i_add_produt_from_cart(String productName) {
		List<WebElement> products = p.getProductList();
	    // Write code here that turns the phrase above into concrete actions
		p.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(4000);
		CartPageTst cart=p.goToCartPage();
		Boolean match = cart.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckOut check = cart.goToCheckOut();
		check.selectCountry("india");
		confirm = check.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
		String actmessage = confirm.getConfirmationMsg();
		AssertJUnit.assertEquals(actmessage, string);
	}
}
