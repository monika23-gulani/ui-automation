package monikagulani.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import monikagulani.AbstractComponents.Ab8st7ractComponent;

public class CartPageTst extends Ab8st7ractComponent  {
	WebDriver driver;

	public CartPageTst(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	private List<WebElement> listOfCartProducts;
	
	@FindBy(css = ".totalRow button")
	private WebElement checkOut;

	public Boolean verifyProductDisplay(String name) {
		return listOfCartProducts.stream().anyMatch(cartItm -> cartItm.getText().equalsIgnoreCase(name));
	}
	
	public CheckOut goToCheckOut()
	{
		checkOut.click();
		return new CheckOut(driver);
	}
}
