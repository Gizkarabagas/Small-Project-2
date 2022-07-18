import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class MyStepdefs {
    protected static WebDriver driver;
    public SoftAssert softAssert;

    @Before
    public void driver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void QUIT() {
        driver.close();
    }

//    Scenario: user can booking hotel with login
    @Given("user go to url {string} hompage and user can login")
    public void userGoToUrlHompageAndUserCanLogin(String arg0) {

        driver.get("http://qa.cilsy.id:8080/en/");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a/span")).click();
        driver.findElement(By.id("email")).sendKeys("tester@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("tester");
        driver.findElement(By.name("SubmitLogin")).click();
        driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img")).click(); //direct to home page

        //verify dashboard Hotel Dominic
        if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1")) != null) {
            System.out.println("Hotel Dominic Parks");
        } else {
            System.out.println("Empty");
        }
        //verify title url
        String urlDirect = driver.getCurrentUrl();
        String urlExpected = "http://qa.cilsy.id:8080/en/";
        System.out.println("url benarnya " + urlDirect);
        Assert.assertEquals(urlDirect, urlExpected);
    }

    @When("user can search hotel and choose date")
    public void userCanSearchHotelAndChooseDate() {
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        int i=1;
        while (i<4)  {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            i++;}
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[3]/td[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[4]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();
        String actualrsult = driver.getTitle();
        Assert.assertEquals(actualrsult,"The Hotel Prime - Small Project QA13");


    }

    @And("user can choose a hotel with multiple categorie")
    public void userCanChooseAHotelWithMultipleCategories() throws InterruptedException {
        int i = 1;
        while (i < 2) {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_3\"]/div/div[2]/a[1]/span/i")).click();
            i++;
        }
        driver.findElement(By.xpath("//a[@rm_product_id='3']\n")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
    }

    @And("user go to payment and proceed to checkout")
    public void userGoToPaymentAndProceedToCheckout() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();
        if (driver.getPageSource().contains("luxury Rooms")) {
            System.out.println("Muncul luxury Rooms bro");
        } else {
            System.out.println("Yah ga muncul");
        }

        //Guest Information
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        if (driver.getPageSource().contains("Bagas Gizkara")) {
            System.out.println("Bagas Gizkara ");
        } else {
            System.out.println("Cobalagi");
        }

        //payment information
        driver.findElement(By.id("cgv")).click();
        Thread.sleep(3000);

        //Bank Wire Payment
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        if (driver.getPageSource().contains("Bank-wire payment")) {
            System.out.println("CieBener");
        } else {
            System.out.println("salah bro");
        }
    }

    @Then("user booking accept")
    public void userBookingAccept() {
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
        if (driver.getPageSource().contains("Your order on Small Project QA13 is complete.")) {
            System.out.println("Project Done");
        } else {
            System.out.println("yang cobalagi dong");
        }
    }

//    Scenario: user can booking hotel without login
    @Given("user go to url {string} hompage..............")
    public void userGoToUrlHompage(String arg0) {
        driver.get("http://qa.cilsy.id:8080/en/");

        //verify hotel dominic
        if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1")) != null) {
            System.out.println("Hotel Dominic Parks");
        } else {
            System.out.println("Empty");
        }
    }

    @When("user choose date and hotel")
    public void userChooseDateAndHotel() throws InterruptedException {
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        int i=1;
        while (i<5)  {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            i++;}
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[3]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();

        //verify tiitle url
        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = " http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2022-11-21&date_to=2022-11-23";
        System.out.println("url yang benar adalah " + UrlHotel);
        Assert.assertEquals(UrlHotel, Urlbenar);

        //Room Information Page
        int b = 1;
        while (b < 2) {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_3\"]/div/div[2]/a[1]/span/i")).click();
            b++;
        }
        driver.findElement(By.xpath("//a[@rm_product_id='3']\n")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();

        //verify Harga
        if (driver.getPageSource().contains("24 750,00 Rp")) {
            System.out.println("24 750,00 Rp");
        } else {
            System.out.println("Yah ga muncul");
        }

        //verify Hotel Rooms
        if (driver.getPageSource().contains("luxury Rooms")) {
            System.out.println("luxury Rooms");
        } else {
            System.out.println("Empty");
        }
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a/span")).click();

    }

    @And("user go to registerd account with email and address name")
    public void userGoToRegisterdAccountWithEmailAndAddressName() throws InterruptedException {
        String FirstName = "Albus";
        String lastName = "Gizkara";
//        String email = "Albus122@gmail.com";
        Random random = new Random();
        int nomoremail = random.nextInt(1000);
        String emailrandom = String.format("Yonkou" + nomoremail + "@gmail.com");
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
        if (driver.getPageSource().contains("Albus Gizkara")) {
            System.out.println("Name passed");
        } else {
            System.out.println("notvisble");
        }
    }

    @And("user go to payment and proceed checkout")
    public void userGoToPaymentAndProceedCheckout() throws InterruptedException {
        driver.findElement(By.name("cgv")).click();


        //verify payment information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
        if (driver.getPageSource().contains("Check payment")) {
            System.out.println("order processing will be longer");
        } else {
            System.out.println("notvisble");
        }

        //Bank Wire Payment
        if (driver.getPageSource().contains("Bank-wire payment")) {
            System.out.println("Bank wire payment passed");
        } else {
            System.out.println("notvisible");
        }
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();

    }

    @Then("user booking acceot")
    public void userBookingAcceot() {
        //Order Confirmation
        if (driver.getPageSource().contains("Your order on Small Project QA13 is complete.")) {
            System.out.println("SP2 Done");
        } else {
            System.out.println("notvisible");
        }
    }

//    Scenario: User can booking with multiple categories hotel
    @Given("user go to url {string} homepage Hotel")
    public void userGoToUrlHomepageHotel(String arg0) {
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

    }

    @When("user choose hotel and date")
    public void userChooseHotelAndDate() {
        //booking home page
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        int q = 1;
        while (q < 3) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            q++;
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();
    }

    @And("user can booking with defrent categories hotel")
    public void userCanBookingWithDefrentCategoriesHotel() throws InterruptedException {
        //verify tiitle url

        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = "http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2022-09-09&date_to=2022-09-11";
        System.out.println("url yang benar adalah " + UrlHotel);
        Assert.assertEquals(UrlHotel, Urlbenar);

        //Room Information Page
        //Room Information Page
        int a = 1;
        while (a < 2) {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_3\"]/div/div[2]/a[1]/span/i")).click();
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_4\"]/div/div[2]/a[1]/span/i")).click();
            a++;
        }
        driver.findElement(By.xpath("//a[@rm_product_id='3']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
        driver.findElement((By.xpath("//a[@rm_product_id='4']"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
    }

    @And("user go to payment and proceed checkout hotel")
    public void userGoToPaymentAndProceedCheckoutHotel() throws InterruptedException {
        //verify Guest Information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        if (driver.getPageSource().contains("Albus Gizkara")) {
            System.out.println("Name passed");
        } else {
            System.out.println("notvisble");
        }


        driver.findElement(By.name("cgv")).click();

        //verify payment information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
        if (driver.getPageSource().contains("Check payment")) {
            System.out.println("order processing will be longer");
        } else {
            System.out.println("notvisble");
        }

        //Bank Wire Payment
        if (driver.getPageSource().contains("Bank-wire payment")) {
            System.out.println("Bank wire payment passed");
        } else {
            System.out.println("notvisible");
        }
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
    }

    @Then("user booking acccepted")
    public void userBookingAcccepted() {
        if (driver.getPageSource().contains("Your order on Small Project QA13 is complete.")) {
            System.out.println("SP2 Done");
        } else {
            System.out.println("notvisible");
        }
    }

//    Scenario: User can booking with Payment Paypall
    @Given("user go to url {string}")
    public void userGoToUrl(String arg0) {
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
    }

    @When("user choose hotel and datee")
    public void userChooseHotelAndDatee()throws InterruptedException {
        //booking home page
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        int e = 1;
        while (e < 6) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            e++;
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();

    }

    @And("user can booking with multiple categories hotel")
    public void userCanBookingWithMultipleCategoriesHotel()throws InterruptedException {
        int a = 1;
        while (a < 2) {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_2\"]/div/div[2]/a[1]/span/i")).click();
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_1\"]/div/div[2]/a[1]/span/i")).click();
            a++;
        }
        driver.findElement(By.xpath("//a[@rm_product_id='2']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
        driver.findElement((By.xpath("//a[@rm_product_id='1']"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();

        if (driver.getPageSource().contains("rooms information")) {
            System.out.println("Room Infromation");
        } else {
            System.out.println("Yah ga muncul");
        }
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[3]/div/a")).click();

        //verify Guest Information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        if (driver.getPageSource().contains("Albus Gizkara")) {
            System.out.println("Name passed");
        } else {
            System.out.println("notvisble");
        }

    }

    @And("user go to payment and choose paypal method dan checkout")
    public void userGoToPaymentAndChoosePaypalMethodDanCheckout()throws InterruptedException {
        //payment information
        driver.findElement(By.name("cgv")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[3]/div/p/a")).click();

    }

    @Then("user see booking confirmed")
    public void userSeeBookingConfirmed()throws InterruptedException {
        if (driver.getPageSource().contains("Check payment")){
            System.out.println("order processing will be longer");
        } else {
            System.out.println("nError occurred:");
        }
    }

//     Scenario: user can payment with Bankwire
    @Given("user go to Url SP dua {string}")
    public void userGoToUrlSPDua(String arg0) {
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
    }

    @When("user chose hotel and date")
    public void userChoseHotelAndDate()throws InterruptedException {
        //booking home page
        driver.findElement(By.name("hotel_location")).sendKeys("Jakarta");
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.xpath("//*[@id=\"check_in_time\"]")).click();
        int g = 1;
        while (g < 7) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            g++;
        }
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();

        //verify tiitle url
        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = "http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2023-01-06&date_to=2023-01-08";
        System.out.println("url yang benar adalah " + UrlHotel);
        Assert.assertEquals(UrlHotel, Urlbenar);
    }

    @And("user booking with two hotels deffrent")
    public void userBookingWithTwoHotelsDeffrent()throws InterruptedException {
        int a = 1;
        while (a < 2) {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_1\"]/div/div[2]/a[1]/span/i")).click();
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_2\"]/div/div[2]/a[1]/span/i")).click();
            a++;
        }
        driver.findElement(By.xpath("//a[@rm_product_id='1']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
        driver.findElement((By.xpath("//a[@rm_product_id='2']"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();

        //verify room information
        if (driver.getPageSource().contains("rooms information")) {
            System.out.println("Room Infromation");
        } else {
            System.out.println("Yah ga muncul");
        }
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[3]/div/a")).click();

        //verify Guest Information
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
        if (driver.getPageSource().contains("Albus Gizkara")) {
            System.out.println("Name passed");
        } else {
            System.out.println("notvisble");
        }
    }

    @And("User choose payment with BankWire account")
    public void userChoosePaymentWithBankWireAccount()throws InterruptedException {
        //payment information
        driver.findElement(By.name("cgv")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        if (driver.getPageSource().contains("Bank-wire payment")){
            System.out.println("Bank-wire payment");
        } else {
            System.out.println("nError occurred:");
        }

        //i confirm my order confirmation
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();

    }

    @Then("User see bookihg confirmed")
    public void userSeeBookihgConfirmed()throws InterruptedException {
        //Order Confirmation
        if(driver.getPageSource().contains("Your order on Small Project QA13 is complete.")){
            System.out.println("SP2 Done");
        }else {
            System.out.println("notvisible");
        }
    }

//    Scenario: User login and booking withLogin\
    @Given("user got to Url Hotel {string}")
    public void userGotToUrlHotel(String arg0) {
        driver.get("http://qa.cilsy.id:8080/en/");

        //verify hotel dominic
        if (driver.findElement(By.xpath("//*[@id=\"header\"]/div[4]/div/div/div/div[1]/div/div/h1")) != null) {
            System.out.println("Hotel Dominic Parks");
        } else {
            System.out.println("Empty");
        }

        driver.get("http://qa.cilsy.id:8080/en/");
    }

    @When("user Login and choose hotel and date")
    public void userLoginAndChooseHotelAndDate()throws InterruptedException {
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
        int j=1;
        while (j<9)  {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();
            j++;}
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        driver.findElement(By.name("search_room_submit")).click();

        //verify tiitle url
        String UrlHotel = driver.getCurrentUrl();
        Thread.sleep(3000);
        String Urlbenar = "http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2023-03-10&date_to=2023-03-12 ";
        System.out.println("url yang benar adalah "+UrlHotel);
        Assert.assertEquals(UrlHotel,Urlbenar);


    }

    @And("user can booking with multiple Hotel Categories")
    public void userCanBookingWithMultipleHotelCategories()throws InterruptedException {
        int l=1;
        while (l<2)  {
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_2\"]/div/div[2]/a[1]/span/i")).click();
            driver.findElement(By.xpath("//*[@id=\"cat_rm_quantity_wanted_1\"]/div/div[2]/a[1]/span/i")).click();
            l++;}
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

    }

    @And("user choose payment method")
    public void userChoosePaymentMethod()throws InterruptedException {
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

    }

    @Then("user see booking accepted")
    public void userSeeBookingAccepted()throws InterruptedException {

        //Order Confirmation
        if(driver.getPageSource().contains("Your order on Small Project QA13 is complete.")){
            System.out.println("SP2 Done");
        }else {
            System.out.println("notvisible");
        }
    }
}

