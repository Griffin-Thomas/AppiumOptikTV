package appiumtests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class appiumOA {
	static final String RATING = "18A"; // Change this to whatever you want for various ratings
	static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) throws MalformedURLException, Exception {
		// Start the Android Optik TV app driver
		driver = android.driver();
		System.out.println("Android Optik TV app driver initialized.");
		
		System.out.println("\nStarting the Optik TV rating test now:");
		try {
			System.out.println("\t1. Selecting \"View in Guest Mode\".");
			driver.findElementById("com.optiktv:id/enter_guest_mode").click();
			
			System.out.println("\t2. Selecting \"On Demand\".");
			driver.findElementByAccessibilityId("On Demand").click();
			
			System.out.println("\t3. Scrolling down until \"Movies\" is within view.");
			MobileElement movies = null;
			boolean moviesFound = false;
			int scrollCount = 0; // To ensure we don't do this forever, let's just scroll up to 10 times
			while (!moviesFound && scrollCount < 10) {
				try {
					movies = driver.findElement(By.xpath("//*[@text='Movies >']"));
					moviesFound = movies.isDisplayed();
				} catch (Exception e) {
					driver.findElement(MobileBy.AndroidUIAutomator(
					         "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()")
							);
					scrollCount++;
				}
			}
			
			System.out.println("\t4. Selecting \"Movies\".");
			movies.click();
			
			System.out.println("\t5. Selecting an asset with rating \"18A\".");
			driver.findElement(By.xpath("//*[@text='18A']")).click();
			
			System.out.println("\t6. Validating the rating of \"18A\" is displayed in the details.");
			MobileElement detail = null;
			boolean detailAvailable = false;
			try {
				detail = driver.findElementById("com.optiktv:id/subText1");
				detailAvailable = true;
			} catch (Exception e) {
				// Do nothing
			}
			if (detailAvailable && detail.getText().contains(RATING)) {
				System.out.println("Test successfully passed!");
			}
			else {
				System.out.println("Test failed.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		} finally {
			// Close the Android Optik TV app driver
			System.out.println("\nShutting down the Android Optik TV app driver now.");
			driver.quit();
		}
	}

}
