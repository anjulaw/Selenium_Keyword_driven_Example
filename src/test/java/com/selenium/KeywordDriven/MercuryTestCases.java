package com.selenium.KeywordDriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;
import com.Excel.util.ReadFromExcel;

/**
 * Created by Anjulaw on 12/25/2015.
 */
public class MercuryTestCases extends Commands {

    String baseUrl = "http://newtours.demoaut.com";

    private WebDriver driver=null;

    private static Commands commands;

    @BeforeSuite
    public void initDriver(){
        commands = new Commands();
        driver = commands.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @DataProvider(name = "Authentication")
    public static Object[][] logInCredentials() throws Exception{

        ReadFromExcel excelReader = new ReadFromExcel("data"+ File.separator+"TestData.xlsx", "LoginDetails");
        return excelReader.getSheetData();
    }

    @Test
    public void registerUser(){
        launch(baseUrl);
        click(MercuryRegistrationPage.registration_link);
        type(MercuryRegistrationPage.firstName,"Anjula");
        type(MercuryRegistrationPage.lastName,"Weerasooriya");
        type(MercuryRegistrationPage.phone,"0771234567");
        type(MercuryRegistrationPage.email,"waaanjula@gmail.com");
        type(MercuryRegistrationPage.address_1,"Test Street 1");
        type(MercuryRegistrationPage.address_2,"Test Street 2");
        type(MercuryRegistrationPage.city,"Colombo");
        type(MercuryRegistrationPage.province,"Western");
        type(MercuryRegistrationPage.postalCode,"123456");
        select(MercuryRegistrationPage.country,"SRI LANKA ");
        type(MercuryRegistrationPage.userName,"anjulaw");
        type(MercuryRegistrationPage.password,"anjula@123");
        type(MercuryRegistrationPage.confirm_password,"anjula@123");
        click(MercuryRegistrationPage.btn_submit);

    }

    @Test(dataProvider = "Authentication")
    public void logIn(String userName,String password){
        launch(baseUrl);
        type(MercuryLoginPage.userName, userName);
        type(MercuryLoginPage.password, password);
        click(MercuryLoginPage.btnLogin);
    }

    @Test(dependsOnMethods = {"logIn"})
    public void bookFlight(){
        click(FlightFinder.flight_Link);
        select(FlightFinder.paxCount,"2");
        select(FlightFinder.arrival,"London");
        select(FlightFinder.from_Month,"January");
        select(FlightFinder.from_Day,"10");
        select(FlightFinder.destination,"New York");
        select(FlightFinder.to_Month,"January");
        select(FlightFinder.to_day,"17");
        click(FlightFinder.search);
        click(SelectFlight.reserve_Flights);
        type(BookFlight.pax1_FirstName,"Test1");
        type(BookFlight.pax1_LastName,"Test");
        type(BookFlight.pax2_FirstName,"Test2");
        type(BookFlight.pax2_LastName,"Test");
        select(BookFlight.card_Type,"Visa");
        type(BookFlight.card_Number,"4111111111111111");
        select(BookFlight.card_exp_month,"5");
        select(BookFlight.card_exp_year,"2010");
        type(BookFlight.cc_first_Name,"TestA");
        type(BookFlight.cc_middle_Name,"ucsc");
        type(BookFlight.cc_last_Name,"Test");
        click(BookFlight.confirm_Flight);

        String confirm_msg = new String(String.valueOf(findElement(SummaryPage.confirm_msg)));

        Assert.assertEquals("Your itinerary has been booked!",confirm_msg);

    }

    @AfterSuite
    public void closeDriver(){
        driver.close();
        driver.quit();
    }


}
