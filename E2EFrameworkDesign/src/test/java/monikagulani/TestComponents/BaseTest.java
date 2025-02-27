package monikagulani.TestComponents;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import monikagulani.pageobjects.LandingPageTst;

public class BaseTest {

	public WebDriver driver;
	public LandingPageTst lp;

	public WebDriver InitializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//monikagulani//resources//GlobalData.properties");
		prop.load(fis);

		String browser = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		if (browser.contains("chrome")) {
			ChromeOptions ops = new ChromeOptions();
			if(browser.contains("headless"))
			{
				ops.addArguments("headless");
			}
			ops.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(ops);
			driver.manage().window().setSize(new Dimension(1440,900));

		} else if (browser.equals("firefox")) {
			// firef7ox
		} else if (browser.equals("edge")) {

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPageTst launchApplication() throws IOException {
		driver = InitializeDriver();

		lp = new LandingPageTst(driver);
		lp.goTo();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

		// String to hashmap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	public String getScreenshot(String testName, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String filepath = System.getProperty("user.dir")+"//reports//"+testName+".png";
		File des = new File(filepath);
		FileUtils.copyFile(src, des);
		return filepath;
	}

}
