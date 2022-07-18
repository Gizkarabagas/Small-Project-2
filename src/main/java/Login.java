import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    @Test
    public void Hotel_Dominic_Park() throws InterruptedException{

        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Login Hotel room
        driver.get("http://qa.cilsy.id:8080/en/");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a/span")).click();
        driver.findElement(By.id("email")).sendKeys("tester@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("tester");
        driver.findElement(By.name("SubmitLogin")).click();
        driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img")).click(); //direct to home page

        //verify dashboard Hotel Dominic
        if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1"))!=null){
            System.out.println("Hotel Dominic Parks");
        } else {
            System.out.println("Empty");
        }


        //verify title url
        String urlDirect = driver.getCurrentUrl();
        String urlExpected = "http://qa.cilsy.id:8080/en/";
        System.out.println("url benarnya "+urlDirect);
        Assert.assertEquals(urlDirect,urlExpected);

        ///boking on home page
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[2]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();
        String actualrsult = driver.getTitle();

        //verify tiitle url
        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = "http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2022-07-15&date_to=2022-07-19";
        System.out.println("url yang benar adalah "+UrlHotel);
        Assert.assertEquals(UrlHotel,Urlbenar);

        //Room Information Page
        int i=1;
        while (i<2)  {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_2\"]/div/div[2]/a[1]/span/i")).click();
            i++;}
        driver.findElement(By.xpath("//a[@rm_product_id='2']") ).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();


        //Room confirmation
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();
        if (driver.getPageSource().contains("luxury Rooms")){
            System.out.println("Muncul luxury Rooms bro");
        } else {
            System.out.println("Yah ga muncul");
        }

        //Guest Information
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        if (driver.getPageSource().contains("Bagas Gizkara")){
            System.out.println("Bagas Gizkara ");
        } else {
            System.out.println("Cobalagi");
        }

        //payment information
        driver.findElement(By.id("cgv")).click();
        Thread.sleep(3000);

        //Bank Wire Payment
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        if (driver.getPageSource().contains("Bank-wire payment")){
            System.out.println("CieBener");
        } else {
            System.out.println("salah bro");
        }


        //Order Confirmation
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
        if (driver.getPageSource().contains("Your order on Small Project QA13 is complete.")){
            System.out.println("Project Done");
        } else {
            System.out.println("yang cobalagi dong");
        }
















    }

}
