package appiumtests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class android {
	static DesiredCapabilities cap = new DesiredCapabilities();
	static final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
	
	public static AppiumDriver<MobileElement> driver() throws MalformedURLException {
		cap.setCapability("deviceName", "Pixel 5");
		cap.setCapability("udid", "emulator-5554"); // I used an Android emulator
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("appPackage", "com.optiktv");
		cap.setCapability("appActivity", "com.telus.mediaroom.tvx.ui.main.MainActivity");
		
		return new AppiumDriver<MobileElement>(new URL(URL_STRING), cap);
	}
}
