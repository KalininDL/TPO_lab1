import org.eclipse.jetty.util.security.Password
import org.junit.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.junit.BeforeClass
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable as groupAssertExecutable
import org.junit.runners.MethodSorters
import java.lang.reflect.Executable
import kotlin.test.assertEquals


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
open class MainClass{

companion object {

    lateinit var driver: WebDriver
    lateinit var wait: WebDriverWait

    @JvmStatic
    @BeforeAll
    fun setUpClass() {
        try {
            print("Setting up...")
            System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\User\\Desktop\\FinalProject\\TPO_lab1\\src\\drivers\\chromedriver.exe"
            )

            //setting up driver for chrome web browser
            driver = ChromeDriver()
            //driver.manage().window().maximize()
            driver.manage().deleteAllCookies()
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS)
            driver.get("https://www.r21.spb.ru/main.htm")
            wait = WebDriverWait(driver, 3)
            if(!signUp("dlk48@yandex.ru", "uCuy7!AEfSXNqUw")){
                print("Failed to sign in!")
                readLine()
                System.exit(-1)
            }
            else {
                print("Signed in sucsessfully")
            }
            driver.findElement(By.xpath("//*[@id=\"centerObj\"]/table/thead/tr/td/a[1]/u")).click()
        } catch (e: Exception) {
            print(e.stackTrace)
        }
    }

    fun signUp(login : String, password: String) : Boolean {
        var signInButton : WebElement = driver.findElement(By.xpath("//*[@id=\"cab1\"]/div/div[2]/a[3]/img"))
        signInButton.click()
        var logInField = wait.until { driver.findElement(By.xpath("//*[@id=\"mobileOrEmail\"]")) }
        var passWoedField = wait.until { driver.findElement(By.xpath("//*[@id=\"password\"]")) }
        logInField.sendKeys(login)
        passWoedField.sendKeys(password)
        driver.findElement(By.xpath("//*[@id=\"loginByPwdButton\"]")).click()
        var signOutButton = driver.findElement(By.xpath("//*[@id=\"hdr\"]/div[3]/b/a[2]/u"))
        var isSucsess : Boolean = wait.until(ExpectedConditions.visibilityOf(signOutButton)).isDisplayed
        return isSucsess
    }
}

//    @AfterClass
//    fun setDownClass() {
//        driver.quit()
//    }



    @Test
    @DisplayName("Base elements of the page loaded")
    fun test1(){
        Assertions.assertAll(
            groupAssertExecutable { assert(isDisplayed(byXPath("//*[@id=\"target\"]/div/table[2]/tbody"))) },
            groupAssertExecutable { assert(isDisplayed(byXPath("//*[@id=\"centerObj\"]/p/a[1]"))) },
            groupAssertExecutable { assert(isDisplayed(byXPath("//*[@id=\"centerObj\"]/p/a[2]"))) },
            groupAssertExecutable { assert(isDisplayed(byXPath("//*[@id=\"target\"]/button[1]"))) },
            groupAssertExecutable { assert(isDisplayed(byXPath("//*[@id=\"subButton\"]"))) },
            groupAssertExecutable { assert(isDisplayed(byXPath("//*[@id=\"ftr\"]"))) },
            groupAssertExecutable { assert(isDisplayed((byXPath("//*[@id=\"right\"]/div[1]")))) }
            )
    }

    @Test
    @DisplayName("Input fields test")
    fun test2(){
        val expectedInput : String = "Hello!"
        Assertions.assertAll(
            groupAssertExecutable { assertEquals(expectedInput, checkInput(byXPath("//*[@id=\"id_post\"]"), expectedInput))},
            groupAssertExecutable { assertEquals(expectedInput, checkInput(byXPath("//*[@id=\"id_livingAddress\"]"), expectedInput))},
            groupAssertExecutable { assertEquals(expectedInput, checkInput(byXPath("//*[@id=\"ftr\"]/div[1]/form/input[1]"), expectedInput))}
        )}

    @Test
    @DisplayName("Drop-down lists test")
    fun test3(){
        Assertions.assertAll(
            groupAssertExecutable {
                byXPath("//*[@id=\"id_publicPeriod_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_publicPeriod_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_publicPeriod_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_citizenship_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_citizenship_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_citizenship_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_familyStatus_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_familyStatus_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_familyStatus_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_stage_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_stage_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_stage_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_livingDistrict_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_livingDistrict_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_livingDistrict_sel\"]")?.click()
            }
        )
    }


    @Test
    @DisplayName("Bar buttons test")
    fun test4(){
        Assertions.assertAll(
            groupAssertExecutable {
                byXPath("//*[@id=\"hdr\"]/div[3]/div/a[1]/u")?.click()
                assertEquals("https://www.r21.spb.ru/empl/competitor.htm", driver.currentUrl)
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"hdr\"]/div[3]/div/a[2]")?.click()
                assertEquals("https://www.r21.spb.ru/empl/vacancies.htm", driver.currentUrl)
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"hdr\"]/div[3]/div/a[3]")?.click()
                assertEquals("https://www.r21.spb.ru/empl/competitor/monitoring/otklik.htm", driver.currentUrl)
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"hdr\"]/div[3]/div/a[4]")?.click()
                assertEquals("https://www.r21.spb.ru/empl/competitor/monitoring/event.htm", driver.currentUrl)
                driver.navigate().back()
            }
        )
    }

    @Test
    @DisplayName("Right dropdown panel set")
    fun test5(){
        Assertions.assertAll(
            groupAssertExecutable {
                byXPath("//*[@id=\"right\"]/div[2]/div[2]/a")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"centerObj\"]/form[1]/h2")))
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"right\"]/div[2]/div[5]/a")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"centerObj\"]/ul[2]/li[1]/a")))
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"right\"]/div[2]/div[8]/a")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"centerObj\"]/ul[2]/li[1]/a")))
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"right\"]/div[2]/div[11]/a")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"centerObj\"]/h1")))
                driver.navigate().back()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"right\"]/div[2]/div[14]/a")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"list1\"]/table/tbody/tr[2]/td[2]/a")))
                driver.navigate().back()
            }
        )
    }

    @Test
    @DisplayName("Upper Menu items test")
    fun test6(){
        Assertions.assertAll(
            groupAssertExecutable {
                byXPath("//*[@id=\"id_publicPeriod_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_publicPeriod_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_publicPeriod_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_citizenship_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_citizenship_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_citizenship_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_familyStatus_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_familyStatus_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_familyStatus_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_stage_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_stage_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_stage_sel\"]")?.click()
            },
            groupAssertExecutable {
                byXPath("//*[@id=\"id_livingDistrict_sel\"]")?.click()
                assert(isDisplayed(byXPath("//*[@id=\"id_livingDistrict_sel\"]/b/a[2]")))
                byXPath("//*[@id=\"id_livingDistrict_sel\"]")?.click()
            }
        )
    }


//<editor-fold desc="Auxiliary functions">

    //Get element by XPath
    fun byXPath(xpath : String) : WebElement?{
        try {
            return driver.findElement(By.xpath(xpath))
        }
        catch (e : Exception){
            println(e.message)
            return null
        }
    }

    //Check is element displayed on the page
    fun isDisplayed(element : WebElement?) : Boolean{
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed
        }
        catch (e : Exception){
            println(e.message)
            return false
        }
    }

    //Check input field (returns entered text)
    fun checkInput(element: WebElement?, text : String) : String{
        try{
        element!!.sendKeys(text)
        var actual : String = element!!.getAttribute("value")
        element.clear()
        return actual}
        catch (e : Exception){
            println(e.message)
            return "Error!"
        }
    }
    //</editor-fold>

}






