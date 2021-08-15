/**
/**
 * @author Bindu
 *
 */
package test;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;


public class ShaadiTest {
	AndroidDriver driver;
	
	@Test
	public void login() throws Exception {
		 
		File appDir = new File("D:\\Bindu\\APK");
		File app = new File(appDir, "com.shaadi.android_2021-08-06.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("VERSION", "4.4.2"); 
		capabilities.setCapability("deviceName","android");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.shaadi.android_2021-08-06");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
		WebElement login=driver.findElement(By.id("login"));
		login.click();
		WebElement userName=driver.findElement(By.xpath("//input[@name='email']"));
		userName.sendKeys("testemail.bindu@gmail.com");
		WebElement password=driver.findElement(By.name("password"));
		password.sendKeys("test@shaadi");
		WebElement loginButton=driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		WebElement premiumMatches=driver.findElement(By.xpath("//span[contains(text(),'Premium')]"));
		WebElement matches=driver.findElement(By.xpath("//span[contains(text(),'Matches')]"));
		if(premiumMatches.isDisplayed()) {
			Assert.assertEquals(premiumMatches.getText(), "Premium");
			premiumMatches.click();
			driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		}
		if(matches.isDisplayed()) {
			Assert.assertEquals(premiumMatches.getText(), "Matches");
			matches.click();
			WebElement connectNow = driver.findElement(By.xpath("/descendant-or-self::span[contains(text(),'Connect Now')][1]"));
			connectNow.click();
		}			
	}
}

