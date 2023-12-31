package com.ninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop, dataprop;

	public Base() {
		prop = new Properties();
		File file = new File(
				System.getProperty("user.dir") + "//src//main//java//com//ninja//qa//config//config.properties");

		dataprop = new Properties();
		File datafile = new File(
				System.getProperty("user.dir") + "//src//main//java//com//ninja//qa//testdata//testdata.properties");

		FileInputStream fis, datafis;
		try {
			datafis = new FileInputStream(datafile);
			dataprop.load(datafis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public WebDriver intializeBrowser(String browser) {

		String browserName = "chrome";

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		}

		// driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
