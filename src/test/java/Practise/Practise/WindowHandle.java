package Practise.Practise;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandle {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		String parantwin = driver.getWindowHandle();
		driver.findElement(By.id("newTabBtn")).click();
		Set<String> handles= driver.getWindowHandles();
		for(String handle:handles) {
			if(!handle.equals(parantwin)) {
				driver.switchTo().window(handle);
				driver.findElement(By.id("alertBox")).click();
				driver.switchTo().alert().accept();
				driver.close();
			}
		}
		driver.switchTo().window(parantwin);
		driver.findElement(By.id("name")).sendKeys("sankar");
		Thread.sleep(3000);
		driver.quit();
	}

}
