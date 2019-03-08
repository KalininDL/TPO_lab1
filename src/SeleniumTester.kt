import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import org.junit.*
import org.junit.jupiter.api.BeforeAll
import org.junit.runners.Suite.SuiteClasses
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait


class SeleniumTester() {


    companion object {

        lateinit var driver: WebDriver
        lateinit var JSExecutor: JavascriptExecutor
        lateinit var wait: WebDriverWait
        @BeforeAll
        fun process() {
            try {
                print("Setting up...")
                System.setProperty(
                    "webdriver.chrome.driver",
                    "C:\\Users\\User\\Desktop\\FinalProject\\TPO_lab1\\src\\drivers\\chromedriver.exe"
                )
                //setting up driver for chrome web browser
                driver = ChromeDriver()
                driver.manage().window().maximize()
                driver.manage().deleteAllCookies()
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
                driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS)

                driver.get("https://www.google.ru/?gws_rd=ssl")
                wait = WebDriverWait(driver, 3)
//            Thread.sleep(3000)
//            driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[3]/center/input[2]")).click()
                //loadWindow()
                var chromeCapabilities: WebDriver

            } catch (e: Exception) {
                print(e.stackTrace)
            }
        }
    }


    @Test
    fun loadWindow(){
        //var wait : WebDriverWait = WebDriverWait(driver, 3)
//        driver.get("https://www.google.ru/?gws_rd=ssl")
        var expectedElement : WebElement = SeleniumTester.driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input"))
        var actualElement : Boolean = SeleniumTester.wait.until(ExpectedConditions.visibilityOf(expectedElement)).isDisplayed
        Assert.assertEquals(true, actualElement)
    }

}
