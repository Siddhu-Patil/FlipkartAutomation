package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Cucumber.class)
public class StepDefinition {
	WebDriver driver=null;

	 @Given("^User is on FlipkartMobile page$")
	    public void user_is_on_flipkartmobile_page() {
		 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		 driver=new ChromeDriver();
    	driver.get("https://www.flipkart.com/mobiles/android~os/pr?sid=tyy%2C4io");
    	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	//driver.findElement(By.xpath("//div[@class='_10UF8M'][1]")).click();
	    }

	    
		@When("^Fetch the mobile name and rating if less than 40000$")
	    public void fetch_the_mobile_name_and_rating_if_less_than_40000() throws IOException  {
	        List<WebElement> mobileName=driver.findElements(By.xpath("//div[@class='_4rR01T']"));
	        
	        List<WebElement> price=driver.findElements(By.xpath("//div[@class='_30jeq3 _1_WHN1']"));
	        
	        List<WebElement> rating=driver.findElements(By.xpath("//div[@class='_3LWZlK']"));
	        
	        
	        //int n=price.size();
        	//int sortedArray[] =new int[n];
	        File file=new File("C:\\Users\\91831\\OneDrive\\Desktop\\NetCoreSolutions\\MobileAutomation.csv");
	        FileOutputStream fos=new FileOutputStream(file);
	        XSSFWorkbook workbook=new XSSFWorkbook();
	        XSSFSheet sheet=workbook.createSheet();
        	
	        
	        for(int i=0;i<price.size();i++) {
	        	
	        	
	        	String MobileName=mobileName.get(i).getText();
	        	String priceOfMobile=price.get(i).getText();
	        	String removeCommaInPrice=priceOfMobile.replaceAll("[???,]", "");
	        	String ratingOfMobile=rating.get(i).getText();
	        	int mRP=Integer.parseInt(removeCommaInPrice);
	        	
	        	if(mRP<=40000){
	        		
			        
	        		
	    	        
	        	
	        	sheet.createRow(i);
        		sheet.getRow(i).createCell(0).setCellValue(MobileName);
        		sheet.getRow(i).createCell(1).setCellValue(mRP);
        		sheet.getRow(i).createCell(2).setCellValue(ratingOfMobile);
        		
        		
	    		
	        	}
	        	
	        	
	        }
	        workbook.write(fos);
	        workbook.close();
	    }

	    @Then("^All the data is written in csv file$")
	    public void all_the_data_is_written_in_csv_file()  {
	    	System.out.println("The mobile name,price and rating is written to the csv file");
	    }

}