import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookingMultipleCategorieWithLogin {
    WebDriver driver;

    @Test
    public void NavigateURL() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://qa.cilsy.id:8080/en/");

        //verify hotel dominic
        if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1")) != null) {
            System.out.println("Hotel Dominic Parks");
        } else {
            System.out.println("Empty");
        }

        driver.get("http://qa.cilsy.id:8080/en/");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a/span")).click();
        driver.findElement(By.id("email")).sendKeys("tester@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("tester");
        driver.findElement(By.name("SubmitLogin")).click();
        driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img")).click(); //direct to home page

        //booking home page
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        int i=1;
        while (i<3)  {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            i++;}
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();

        //verify tiitle url
        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = "http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2022-09-09&date_to=2022-09-11";
        System.out.println("url yang benar adalah "+UrlHotel);
        Assert.assertEquals(UrlHotel,Urlbenar); 

        //Room Information Page
        //Room Information Page
        int a=1;
        while (a<2)  {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_2\"]/div/div[2]/a[1]/span/i")).click();
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_1\"]/div/div[2]/a[1]/span/i")).click();
            a++;}
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