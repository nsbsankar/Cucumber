package Practise.Practise;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonSearch {
	
	
	public void scroll(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
	}
	public void clickUsingJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void main(String[] args) throws InterruptedException {

	WebDriver driver = WebDriverManager.chromedriver().create();
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	driver.get("https://www.amazon.in/ref=nav_logo");
	
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("apple watch");
	
	List<WebElement> list = driver.findElements(By.xpath("//div[@class='autocomplete-results-container']/descendant::div[@role='button']"));
	
	System.out.println(list.size());
	
	for (WebElement options : list) {
		
		if (options.getText().equals("apple watch")) {
			wait.until(ExpectedConditions.visibilityOfAllElements(options));
			options.click();
			break;
			
		}
		
		
	}
	
	Thread.sleep(5000);
	driver.quit();
	
		
		
	}

}
