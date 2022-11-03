package Practise.Practise;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonScenario {
	
	 static int lowprice;
	 static int highprice;

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/ref=nav_logo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement searchbox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchbox.sendKeys("doll");
		searchbox.submit();
		
		WebElement sortby = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']"));
		sortby.click();
		
		List<WebElement> sortdropdown = driver.findElements(By.xpath("//ul[@class='a-nostyle a-list-link']//li"));
		/*
		 * WebElement watch = driver.findElement(By.
		 * xpath("//div[@class='rush-component s-featured-result-item s-expand-height']//span[@class='a-size-base-plus a-color-base a-text-normal'][contains(text(),'Noise Pulse Go Buzz Advanced Bluetooth Calling Sma')]"
		 * )); watch.click();
		 */
		for(WebElement option:sortdropdown ) {
			if (option.getText().equals("Price: Low to High")) {
				option.click();
				break;
			}
		}
		
		WebElement firstproduct = driver.findElement(By.xpath("//span[normalize-space()='SBS Barbie Doll (Multicolour)']"));
			firstproduct.click();
			
			String parentwin = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			
			for(String window:windows) {
				if(!window.equals(parentwin)) {
					driver.switchTo().window(window);
					String str = driver.findElement(By.xpath("//span[text()=' Inclusive of all taxes']/ancestor::div[2]/descendant::span[6]")).getText();
					lowprice = Integer.parseInt(str);
					System.out.println(lowprice);
				}
			}
			driver.close();
			
			driver.switchTo().window(parentwin);
			
			driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).click();
			List<WebElement> sortdropdown2 = driver.findElements(By.xpath("//ul[@class='a-nostyle a-list-link']//li"));
			
			for(WebElement option:sortdropdown2 ) {
				if (option.getText().equals("Price: High to Low")) {
					option.click();
					break;
				}
			}
			
			WebElement second_product = driver.findElement(By.xpath("//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']//span[@class='a-size-base-plus a-color-base a-text-normal']"));
			second_product.click();
			
			String parentwin1 = driver.getWindowHandle();
			Set<String> windows1 = driver.getWindowHandles();
			
			for(String window:windows1) {
				if(!window.equals(parentwin1)) {
					driver.switchTo().window(window);
					String str2 = driver.findElement(By.xpath("//span[text()=' Inclusive of all taxes']/ancestor::div[2]/descendant::span[6]")).getText();
					highprice = Integer.parseInt(str2.replace(",", ""));
					System.out.println(highprice);
					
				}
				
			}
			
		Assert.assertNotEquals(lowprice, highprice);
	
			
		
	}
	
}
