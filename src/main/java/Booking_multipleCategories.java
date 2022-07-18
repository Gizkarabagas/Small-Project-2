import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Booking_multipleCategories {
    WebDriver driver;
    @Test
    public void NavigateURL() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://qa.cilsy.id:8080/en/");

        //verify hotel dominic
        if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1"))!=null){
            System.out.println("Hotel Dominic Parks");
        } else {
            System.out.println("Empty");
        }

        //booking home page
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();

        //verify tiitle url
        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = "http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2022-08-12&date_to=2022-08-14";
        System.out.println("url yang benar adalah "+UrlHotel);
        Assert.assertEquals(UrlHotel,Urlbenar);

        //Room Information Page
        int i=1;
        while (i<2)  {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_2\"]/div/div[2]/a[1]/span/i")).click();
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_1\"]/div/div[2]/a[1]/span/i")).click();
            i++;}
        driver.findElement(By.xpath("//a[@rm_product_id='2']") ).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
        driver.findElement((By.xpath("//a[@rm_product_id='1']"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();

        //verify room information
        if (driver.getPageSource().contains("rooms information")){
            System.out.println("Room Infromation");
        } else {
            System.out.println("Yah ga muncul");
        }
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[3]/div/a")).click();

        /// Data Guest Information
        String FirstName = "Albus";
        String lastName = "Gizkara";
//        String email = "Albus122@gmail.com";
        Random random = new Random();
        int nomoremail = random.nextInt(1000);
        String emailrandom = String.format("Yonkou"+nomoremail+"@gmail.com");
        String pass = "tester";
        String mobile = "081234567789";

        //input Guest Information
        driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
        driver.findElement(By.id("email")).sendKeys(emailrandom);
        driver.findElement(By.id("passwd")).sendKeys(pass);
        driver.findElement(By.id("phone_mobile")).sendKeys(mobile);
        driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();

        //verify Guest Information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        if (driver.getPageSource().contains("Albus Gizkara")){
            System.out.println("Name passed");
        } else {
            System.out.println("notvisble");
        }


        driver.findElement(By.name("cgv")).click();

        //verify payment information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
        if (driver.getPageSource().contains("Check payment")){
            System.out.println("order processing will be longer");
        } else {
            System.out.println("notvisble");
        }

        //Bank Wire Payment
        if(driver.getPageSource().contains("Bank-wire payment")){
            System.out.println("Bank wire payment passed");
        } else {
            System.out.println("notvisible");
        }
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();

        //Order Confirmation
        if(driver.getPageSource().contains("Your order on Small Project QA13 is complete.")){
            System.out.println("SP2 Done");
        }else {
            System.out.println("notvisible");
        }



    }
}
