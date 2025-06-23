package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Scanner;

public class DriverUtil {
    public static WebDriver getDriver() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose browser (chrome/edge): ");
        String browser = scanner.nextLine().trim().toLowerCase();
        scanner.close();

        switch (browser) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
