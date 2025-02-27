package monikagulani.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import monikagulani.pageobjects.CartPageTst;
import monikagulani.pageobjects.OrderPage;

public class Ab8st7ractComponent {



	WebDriver driver;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public Ab8st7ractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitForWebElementToAppear(WebElement by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(by));
	}

	public void waitForElementToDisappear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public CartPageTst goToCartPage() {
		cartIcon.click();
		CartPageTst ct= new CartPageTst(driver);
		return ct;
		
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage ct= new OrderPage(driver);
		return ct;
		
	}
}
