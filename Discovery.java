package SpiceJet;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.WebDriverServlet;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Discovery 
{
	public WebDriver driver;
  @Test
  public void f() 
  {
	  Actions act=new Actions(driver);
	  WebElement hover=driver.findElement(By.xpath("//*[@id='mod-header-1']/div/div[9]/div/span"));
	  act.moveToElement(hover).build().perform();
	  WebElement dropdownhover=driver.findElement(By.xpath("(//div[@class='o-Header__m-DropdownMenu'])[2]"));
	  List<WebElement>li=dropdownhover.findElements(By.tagName("a"));
	  System.out.println(li.size());
	  for(int i=0;i<li.size();i++)
	  {
		  System.out.println(li.get(i).getText());
		  if(li.get(i).getText().equalsIgnoreCase("My Videos"))
		  {
			  li.get(i).click();
		  }
	  }
	  String parentwindow=driver.getWindowHandle();
	  Set<String>windows=driver.getWindowHandles();
	  Iterator<String>itr=windows.iterator();
	  while(itr.hasNext())
	  {
		  String childwindow=itr.next();
		  if(!parentwindow.equals(childwindow))
		  {
			  driver.switchTo().window(childwindow);
		  }
	  }
	  
	  System.out.println(driver.findElement(By.xpath("//h1[text()='My Videos']")).getText());
	  
	  WebElement shows=driver.findElement(By.xpath("//*[@id='react-root']/div/div[1]/div[3]/main/div/div[2]/section[2]/div/div"));
	  List<WebElement>showslist=shows.findElements(By.tagName("a"));
	  for(int i=0;i<showslist.size();i++)
	  {
		  System.out.println(showslist.get(i).getAttribute("href"));
		  
		  act.moveToElement(showslist.get(i)).build().perform();
		  
		  //driver.findElement(By.xpath("(//i[@class='flipIconCore__icon icon-plus '])[17]")).click();
		  driver.findElement(By.xpath("//span[contains(text(),'Add to Favorites')]/..//div")).click();
		  
		  //driver.navigate().back();
		  
		  if(i==1)
		  {
			  break;
		  }
	  }
	  WebElement verifyElement=driver.findElement(By.xpath("//h2[text()='Favorite Shows']"));
	  JavascriptExecutor je=(JavascriptExecutor)driver;
	  je.executeScript("arguments[0].scrollIntoView(true);",verifyElement);
	  
	  System.out.println("adding to favourite is successfully done");
	  
	  WebElement selectedShows=driver.findElement(By.xpath("(//div[@class='showTileSquare__content'])[1]"));
	  act.moveToElement(selectedShows).build().perform();
	  
	  String text=driver.findElement(By.xpath("(//div[text()='Naked and Afraid XL'])[1]")).getText();
	  
	  System.out.println("the text is "+text);
	  
	  Assert.assertEquals(text, "NAKED AND AFRAID XL");
	  
	  
	  
  }
  @BeforeTest
  public void beforeTest() 
  {
	  System.setProperty("webdriver.chrome.driver", "D:\\seleniumsoftware\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("https://www.discovery.com");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().deleteAllCookies();
	}

  @AfterTest
  public void afterTest() 
  {
	  driver.quit();
  }

}
