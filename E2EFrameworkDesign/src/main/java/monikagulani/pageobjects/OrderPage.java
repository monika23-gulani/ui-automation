package monikagulani.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import monikagulani.AbstractComponents.Ab8st7ractComponent;

public class OrderPage extends Ab8st7ractComponent  {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> listOfOrderProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public Boolean verifyOrderDisplay(String order) {
		return listOfOrderProducts.stream().anyMatch(item -> item.getText().equalsIgnoreCase(order));
	}

}
