# MoAutomation - Trading App Test Automation Framework

A comprehensive mobile test automation framework for trading applications using Appium, Selenium, and TestNG with advanced reporting capabilities.

## 🚀 Features

- **Mobile Automation**: Android app testing using Appium WebDriver
- **Web Automation**: Chrome browser testing with Selenium WebDriver  
- **Data-Driven Testing**: Excel integration with Apache POI for test data management
- **Advanced Reporting**: ExtentReports with HTML output and TestNG integration
- **Page Object Model**: Modular design with reusable page objects and methods
- **Thread-Safe**: ThreadLocal driver management for parallel execution
- **BDD Support**: Cucumber integration for behavior-driven development
- **Performance Testing**: Order routing timestamp analysis

## 📋 Prerequisites

- **Java 8+**
- **Maven 3.6+**
- **Android SDK** (for mobile testing)
- **Appium Server 2.x**
- **Chrome Browser** (for web testing)
- **Android device** with USB debugging enabled

## 🛠️ Installation

### 1. Clone Repository
```bash
git clone <repository-url>
cd MoAutomation
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Start Appium Server
```bash
appium --port 4723
```

## ⚙️ Configuration

### Device Configuration
Update device capabilities in `config.properties`:
```properties
Rise_app_package=com.mosl.mobile
Rise_app_activity=com.mosl.mobile.MainActivity
```

### Driver Configuration
Update driver paths in `DriverFactory.java` for your system:
```java
capabilities.setCapability("deviceName", "YOUR_DEVICE_NAME");
capabilities.setCapability("udid", "YOUR_DEVICE_UDID");
```

## 🏃♂️ Running Tests

### Execute All Tests
```bash
mvn test
```

### Execute Specific Test Suite
```bash
mvn test -Dtest=AppRegression
```

### Execute with TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 📁 Project Structure

```
MoAutomation/
├── src/test/java/
│   ├── drivers/
│   │   ├── DriverFactory.java          # WebDriver management
│   │   └── SharedDriver.java           # Shared driver instance
│   ├── nadeem/                         # Test classes
│   │   ├── AppRegression.java          # Main regression suite
│   │   ├── MutualFunds.java           # MF testing
│   │   ├── OrderRouting.java          # Order flow tests
│   │   └── Reports.java               # Reporting tests
│   ├── pageobjects/                   # Page Object Model
│   │   ├── HomePage.java              # Home page elements
│   │   ├── LoginPage.java             # Login functionality
│   │   ├── OrderForm.java             # Order placement
│   │   ├── Portfolio.java             # Portfolio management
│   │   └── Watchlist.java             # Watchlist operations
│   ├── stepDefinations/               # Cucumber step definitions
│   ├── utils/
│   │   └── Commons.java               # Utility methods
│   └── config.properties              # Configuration file
├── src/test/resources/
│   └── features/                      # Cucumber feature files
├── target/                           # Build outputs
├── test-output/                      # TestNG reports
├── pom.xml                          # Maven dependencies
├── testng.xml                       # TestNG configuration
└── README.md
```

## 🔧 Key Components

### DriverFactory
- Thread-safe WebDriver management
- Support for Android (Appium) and Chrome
- Automatic cleanup with shutdown hooks
- Configurable device capabilities

### Page Objects
- **LoginPage**: User authentication and biometric login
- **HomePage**: Main dashboard interactions and navigation
- **OrderForm**: Trade order placement and modification
- **Portfolio**: Investment portfolio management
- **Watchlist**: Stock watchlist operations
- **GetQuote**: Stock quote and market data

### Test Suites
- **AppRegression**: Comprehensive app functionality testing
- **OrderRouting**: Order placement performance analysis
- **MutualFunds**: Mutual fund operations testing
- **Reports**: Report generation and download testing

## 📊 Reporting

### ExtentReports
- HTML reports with screenshots on failure
- Test execution timeline and statistics
- Pass/Fail status with detailed logs

### TestNG Reports
- Built-in HTML and XML reports
- Test suite execution summary
- Failed test retry mechanism

### Custom Table Logging
- Performance metrics tracking
- Execution time analysis
- Status-based color coding

## 🧪 Test Categories

### Functional Testing
- User login and authentication
- Global search functionality
- Quote retrieval (Cash, Futures, Options)
- Order placement and modification
- Portfolio management
- Watchlist operations

### UI Testing
- Element visibility and interaction
- Tab navigation and switching
- Swipe gestures and scrolling
- Button and form validations

### Performance Testing
- Order routing timestamp analysis
- Page load time measurements
- API response time tracking

### Data-Driven Testing
- Excel-based test data management
- Parameterized test execution
- Configuration-driven testing

## 🔍 Dependencies

### Core Testing
- **Selenium WebDriver 4.12.0**: Web automation
- **Appium Java Client 8.5.1**: Mobile automation
- **TestNG 7.10.2**: Test framework and assertions

### Reporting & Logging
- **ExtentReports 5.0.9**: Advanced HTML reporting
- **Log4j 2.23.1**: Logging framework
- **Apache Commons IO 2.16.1**: File operations

### Data Management
- **Apache POI 5.4.0**: Excel file handling
- **Apache XMLBeans 5.1.1**: XML processing

### BDD Support
- **Cucumber Java 7.20.1**: BDD framework
- **Cucumber TestNG 7.20.1**: TestNG integration

## 🚀 Advanced Features

### Thread-Safe Execution
```java
private static ThreadLocal<AndroidDriver> drivers = new ThreadLocal<>();
```

### Custom Reusable Methods
- Element interaction utilities
- Swipe and scroll operations
- Text input and validation
- Screenshot capture on failure

### Configuration Management
- Environment-specific properties
- Dynamic capability configuration
- Flexible test data management

## 🐛 Troubleshooting

### Common Issues

**Appium Connection Failed**
- Ensure Appium server is running on `localhost:4723`
- Verify Android device connection and USB debugging
- Check device UDID matches configuration

**Element Not Found**
- Verify element locators in page objects
- Check implicit wait timeouts
- Ensure app state is correct before interaction

**Test Data Issues**
- Verify Excel file paths and formats
- Check Apache POI version compatibility
- Validate test data structure

## 📈 Performance Optimization

- ThreadLocal driver management for parallel execution
- Implicit and explicit wait strategies
- Efficient element location strategies
- Memory management with proper driver cleanup

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue on GitHub
- Contact the development team
- Review documentation and logs

---

**Built for comprehensive trading app automation testing**
