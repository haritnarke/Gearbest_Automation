package com.test.automation.UiActions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductSelection {

	public WebDriver driver;

	public ProductSelection(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void SelectMobile() {
		driver.findElement(By.xpath("//*[@id=\"topMian\"]/div[1]/div[3]/div[1]/a/img")).click();
		driver.findElement(By.xpath("//*[@id='js_nav_list']//*[text()='Mobile Phones']")).click();
		driver.findElement(By.xpath("//*[@id='js_cate_attr']//*[text()='Cell phones']")).click();
		driver.findElement(By.xpath("//*/a[contains(text(),'CLEARANCE')]")).click();

		List<WebElement> linksize = driver.findElements(By.xpath("//*[@id='catePageList']/li/div/p[1]/a"));

		int linksCount = linksize.size();
		System.out.println("Total no of links Available: " + linksCount);
		String[] links = new String[linksCount];
		System.out.println("List of links Available: ");
		// print all the links from webpage
		for (int i = 0; i < linksCount; i++) {
			links[i] = linksize.get(i).getAttribute("href");
			//if (links[i].contains("cell-phones/pp_") && !links[i].contains("Reviews"))
				System.out.println(links[i]);
					}
		// navigate to each Link on the webpage

		for (int j = 0; j < linksCount; j++) {
			//if (links[j].contains("cell-phones/pp")) {
				System.out.println("Link Clicked"+" "+links[j]);
				driver.get(links[j]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generaetted catch block
				e.printStackTrace();
			}
		
	}
	
	}
}
