package com.selenium.KeywordDriven;

import org.openqa.selenium.By;

/**
 * Created by Anjulaw on 12/25/2015.
 */
public class FlightFinder {

    public static By flight_Link = By.linkText("Flights");

    public static By paxCount = By.name("passCount");

    public static By arrival = By.name("fromPort");

    public static By from_Month = By.name("fromMonth");

    public static By from_Day = By.name("fromDay");

    public static By destination = By.name("toPort");

    public static By to_Month = By.name("toMonth");

    public static By to_day = By.name("toDay");

    public static By search = By.name("findFlights");

}
