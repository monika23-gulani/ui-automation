package monikagulani.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import monikagulani.AbstractComponents.Ab8st7ractComponent;

public class Confirmation extends Ab8st7ractComponent {
	WebDriver driver;

	public Confirmation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmMsg;

	public String getConfirmationMsg() {
		return confirmMsg.getText();
	}
}
