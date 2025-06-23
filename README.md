![Java](https://img.shields.io/badge/Java-17-blue.svg)
![TestNG](https://img.shields.io/badge/TestNG-Framework-green)
![Selenium](https://img.shields.io/badge/Selenium-Automation-43B02A)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

# Automation Test Framework

**Author**: Anik Mitra  
**Email**: anikmitradell@gmail.com

## Overview

This project is a Selenium-based Test Automation Framework built using Java and TestNG. 
It includes runtime browser selection, ExtentReports integration, element highlighting, 
and automatic screenshot capture during test execution.

---

## Features

- ✅ **Browser Selection Prompt**: Choose Chrome or Edge at runtime via terminal.
- ✅ **Selenium WebDriver Integration** using WebDriverManager.
- ✅ **TestNG Framework** for test management.
- ✅ **ExtentReports** with step logging and embedded screenshots.
- ✅ **Dynamic Element Highlighting** with red border and yellow background.
- ✅ **Configurable properties** via `config.properties`.

---

## Setup Instructions

### 1. Prerequisites

- Java JDK 8 or above
- Maven
- Internet connection (for WebDriverManager to download browser drivers)

### 2. Clone or Extract the Project

Extract the provided ZIP file.

### 3. Open the Project

Use any Java IDE (e.g., IntelliJ, Eclipse) or a code editor.

### 4. Run Tests

Run the test via TestNG. When prompted in the terminal, input your browser choice:

```
Choose browser (chrome/edge):
```

### 5. View Results

- **Extent Report**: Located at `test-output/ExtentReport.html`
- **Screenshots**: Captured per step in `test-output/screenshots/`

---

## Project Structure

```
automationtest/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utils/
│   │           └── DriverUtil.java
│   └── test/
│       └── java/
│           └── tests/
│               ├── AutomationTest.java
│               └── config.properties
├── test-output/
│   ├── ExtentReport.html
│   └── screenshots/
```

---

## Custom Logic

### Browser Prompt (DriverUtil.java)

```java
Scanner scanner = new Scanner(System.in);
System.out.println("Choose browser (chrome/edge): ");
String browser = scanner.nextLine().trim().toLowerCase();
```

### Element Highlighting

```java
js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.background='yellow'", element);
```

### Screenshot Capture

```java
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
```

### Logging to ExtentReports

```java
test.log(Status.INFO, "Action Description").addScreenCaptureFromPath(screenshotPath);
```

---

## Author

- **Name**: Anik Mitra
- **Email**: anikmitradell@gmail.com

---

## License

This project is provided for educational and professional demonstration purposes.
