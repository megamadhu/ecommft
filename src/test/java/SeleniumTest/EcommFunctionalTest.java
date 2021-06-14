package SeleniumTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;

public class EcommFunctionalTest {
	
	static WebDriver driver;
	String baseurl="http://localhost:5050/Herbal_India/";
  
  @BeforeClass
  public void beforeClass() {
	  FirefoxBinary firefoxBinary = new FirefoxBinary();
	  firefoxBinary.addCommandLineOptions("--headless");
      FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setBinary(firefoxBinary);
	  System.setProperty("webdriver.gecko.driver", "/opt/firefoxdriver/geckodriver");
      driver = new FirefoxDriver(firefoxOptions);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
	}


  @Test(priority=0)
  public void homePage(){
	  driver.get(baseurl);
	  String title =driver.getTitle();
      assertEquals("Herbal India",title);
      
      JavascriptExecutor js=(JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0,330)");
      
      driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[4]/div[3]/span[2]")).click();
      driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[4]/div[3]/span[3]")).click();
      js.executeScript("window.scrollBy(0,900)");
      driver.findElement(By.xpath("/html/body/footer/a")).click();
  }
 
  @Test(priority=1)
  public void signUp(){
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[3]")).click();
	  
	  JavascriptExecutor js=(JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,400)");
	  
	  driver.findElement(By.name("name")).sendKeys("DevOps");
	  driver.findElement(By.name("uname")).sendKeys("DevOps");
	  driver.findElement(By.name("pass")).sendKeys("DevOps");
	  driver.findElement(By.name("email")).sendKeys("devops@b31.com");
	  driver.findElement(By.xpath("/html/body/div[2]/form/div/button")).click();
  }
  
 @Test(priority=2)
  public void signIn(){
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/a")).click();
	  driver.findElement(By.name("uname")).sendKeys("DevOps");
	  driver.findElement(By.name("pass")).sendKeys("DevOps");
	  driver.findElement(By.xpath("/html/body/div[2]/form/div/button")).click();
	  assertTrue(driver.getPageSource().contains("Welcome DevOps"));
  }
 
 @Test(priority=3)
 public void editProfile(){
	 driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/a")).click();
	 
	 driver.findElement(By.name("name")).clear();
	 driver.findElement(By.name("pass")).clear();
	 driver.findElement(By.name("email")).clear();
	 driver.findElement(By.name("name")).sendKeys("DevOps Admin");
	 driver.findElement(By.name("pass")).sendKeys("DevOps");
	 driver.findElement(By.name("email")).sendKeys("devopsadmin@b31.com");
	  
	  driver.findElement(By.xpath("/html/body/div[2]/form/div/button")).click();
	  assertTrue(driver.getPageSource().contains("User profile saved"));
 }

 @Test(priority=4)
 public void signOut(){
	 driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/a")).click();
 }
 
 @Test(priority=5)
 public void signUpValidate(){
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[3]")).click();
	  
	  JavascriptExecutor js=(JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,400)");
	  
	  driver.findElement(By.name("name")).sendKeys("DevOps Admin");
	  driver.findElement(By.name("uname")).sendKeys("DevOps");
	  driver.findElement(By.name("pass")).sendKeys("DevOps");
	  driver.findElement(By.name("email")).sendKeys("devops@b31.com");
	  driver.findElement(By.xpath("/html/body/div[2]/form/div/button")).click();
	  
	  assertTrue(driver.getPageSource().contains("User with this user name already exists!"));
 } 
 
 @Test(priority=6)
 public void signInAgain(){
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/a")).click();
	  
	  driver.findElement(By.name("uname")).sendKeys("DevOps");
	  driver.findElement(By.name("pass")).sendKeys("DevOps");
	  driver.findElement(By.xpath("/html/body/div[2]/form/div/button")).click();
	  assertTrue(driver.getPageSource().contains("Welcome DevOps"));
 }
 
 @Test(priority=7)
 public void addProduct(){
	  driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/a")).click();
	  
	  driver.findElement(By.name("name")).sendKeys("Herbal Cream");
	  driver.findElement(By.name("description")).sendKeys("Get that Natural Glowing face just within days of usage of Olive extracts, Green Tea, Pure Pearl Powder, Saffron, Turmeric, Almond ,Honey extracts");
	  driver.findElement(By.name("price")).sendKeys("199");
	  driver.findElement(By.xpath("//*[@id=\"add\"]")).click();
	  
	  assertTrue(driver.getPageSource().contains("Successful"));
	  assertTrue(driver.getPageSource().contains("Herbal Cream"));
 }
 
 @Test(priority=8)
 public void EditProductDetails(){
	  driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/form/table/tbody/tr/td[4]/button[1]/img")).click();
	  driver.findElement(By.name("price")).clear();
	  driver.findElement(By.name("price")).sendKeys("299");
	  driver.findElement(By.xpath("//*[@id=\"add\"]")).click();
	  assertTrue(driver.getPageSource().contains("Successful"));
	  assertTrue(driver.getPageSource().contains("299"));
 }
 
 @Test(priority=9)
 public void DeleteProductDetails(){
	  driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/form/table/tbody/tr/td[4]/button[2]/img")).click();
	  assertTrue(driver.getPageSource().contains("Successful"));
 }
}
