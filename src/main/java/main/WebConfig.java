package main;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class WebConfig extends Config {
    public Proxy seleniumProxy;
    public WebDriverWait wait;
    Process process;
    String driverCofnigName;
    List<String> beforePidList = new ArrayList();
    List<String> AfterPidList = new ArrayList();
    List<String> chromePidList;
    String currentLine;
    BufferedReader stdInput;
    String defaultOutput;

    public WebConfig() {
    }

    @BeforeMethod
    public void beforeMethod() {
        try {
            this.defaultOutput = this.prop.getProperty("DEFAULT_OUTPUT", "console");
            if (!this.defaultOutput.equals("console")) {
                System.setOut(new PrintStream(new File(this.defaultOutput)));
            }

            System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER"));
            this.process = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq chrome.exe\"");
            this.stdInput = new BufferedReader(new InputStreamReader(this.process.getInputStream()));

            while((this.currentLine = this.stdInput.readLine()) != null) {
                this.beforePidList.add(this.currentLine);
            }

            ChromeOptions opt = new ChromeOptions();
            if (this.prop.getProperty("HDS", "TRUE").equals("TRUE")) {
                opt.addArguments(new String[]{"--headless", "--no-sandbox", "--disable-gpu", "--disable-popup-blocking"});
                this.driver = new ChromeDriver(opt);
            } else {
                System.out.println("Initiating driver");
                this.driver = new ChromeDriver();
            }

            this.threadSleep(1000L);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.wait = new WebDriverWait(this.driver, 10L, 200L);
        this.driver.manage().timeouts().implicitlyWait((long)Integer.parseInt(this.prop.getProperty("DRIVER_IMPLICIT_WAIT_MILLISECOND", "5000")), TimeUnit.MILLISECONDS);
        Actions action = new Actions(this.driver);
        this.actionG = action;

        try {
            this.process = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq chrome.exe\"");
            this.stdInput = new BufferedReader(new InputStreamReader(this.process.getInputStream()));

            while((this.currentLine = this.stdInput.readLine()) != null) {
                this.AfterPidList.add(this.currentLine);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.chromePidList = new ArrayList(this.AfterPidList);
        this.chromePidList.removeAll(this.beforePidList);
    }

    @AfterMethod
    public void closeDriver() {
        this.driver.close();
        this.driver.quit();
    }

    public void writeToElement(WebElement webElement, String data, boolean append) {
        this.logger.trace("Writing data to WebElement..");
        if (this.isVisible(webElement) && webElement.isEnabled()) {
            try {
                this.logger.trace("Sending keys to WebElement");
                webElement.sendKeys(new CharSequence[]{data});
                this.logger.trace("Keys sent to WebElement");
            } catch (WebDriverException var7) {
                this.logger.fatal("WebDriverException");

                try {
                    this.logger.trace("Sending keys (ACTION) to WebElement");
                    this.actionG.moveToElement(webElement);
                    this.actionG.sendKeys(new CharSequence[]{data});
                    if (append) {
                        this.actionG.sendKeys(new CharSequence[]{Keys.ENTER});
                    }

                    this.actionG.build().perform();
                    this.logger.trace("Keys (ACTION) sent to WebElement");
                } catch (Exception var6) {
                    this.logger.fatal("Failed to write to WebElement");
                    this.logger.trace("Retrying..");
                    this.jsGoto(webElement);
                    this.actionG.sendKeys(new CharSequence[]{data});
                    if (append) {
                        this.actionG.sendKeys(new CharSequence[]{Keys.ENTER});
                    }

                    this.actionG.build().perform();
                }
            }
        } else {
            this.logger.trace("WebElement not visible or disabled");
        }

    }

    public void goToUrl(String url) {
        boolean timeout = false;

        try {
            String currentWindowHandle = this.driver.getWindowHandle();
            this.driver.get(url);
            this.driver.switchTo().window(currentWindowHandle);
        } catch (TimeoutException var5) {
            timeout = true;
        }

        while(timeout) {
            try {
                this.driver.navigate().refresh();
                timeout = false;
            } catch (TimeoutException var4) {
                timeout = true;
            }
        }

    }

    public boolean isUrl(String url) {
        try {
            this.wait.until(ExpectedConditions.urlToBe(url));
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public WebElement findElementByPartialLinkText(String partialLinkText) {
        WebElement webElement = null;

        try {
            webElement = this.driver.findElement(By.partialLinkText(partialLinkText));
            this.logger.trace("WebElement found by partialLinkText @tagName: " + partialLinkText);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @partialLinkText: " + partialLinkText);
        }

        return webElement;
    }

    public List<WebElement> findElementsByPartialLinkText(String partialLinkText) {
        List webElementList = null;

        try {
            webElementList = this.driver.findElements(By.partialLinkText(partialLinkText));
            this.logger.trace("WebElements found by partialLinkText @partialLinkText: " + partialLinkText);
            this.logger.trace(webElementList);
        } catch (NoSuchElementException var4) {
            this.logger.fatal("NoSuchElementException @partialLinkText: " + partialLinkText);
        }

        return webElementList;
    }

    public void getAutoComplete(WebElement webElement) {
        this.logger.trace("getAutoComplete for WebElement");
        WebElement firstItem = this.driver.findElement(By.xpath("//*[@class='auto_suggest']/*[@name='" + webElement.getAttribute("name") + "']"));
        firstItem.click();
    }

    public void jsVisible(WebElement webElement) {
        this.logger.trace("Change element visibility (js) to visible");
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
        ((JavascriptExecutor)this.driver).executeScript(js, new Object[]{webElement});
    }

    public void jsScrollTopto(WebElement webElement) {
        this.logger.trace("jsScrollTopto on WebElement");
        ((JavascriptExecutor)this.driver).executeScript("var evt = document.createEvent('MouseEvents');evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);arguments[0].dispatchEvent(evt);", new Object[]{webElement});
    }

    public void jsGoto(WebElement webElement) {
        this.logger.trace("jsGoto on WebElement");
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{webElement});
    }

    public void jsPreviousPage() {
        this.logger.trace("jsPreviousPage");
        ((JavascriptExecutor)this.driver).executeScript("window.history.go(-1)", new Object[0]);
    }

    public void append(WebElement webElement) {
        this.actionG.moveToElement(webElement);
        this.actionG.click();
        this.actionG.sendKeys(new CharSequence[]{Keys.ENTER});
        this.actionG.build().perform();
        this.logger.trace("Append action on WebElement");
    }

    public void fillAppend(WebElement webElement, String data) {
        this.actionG.moveToElement(webElement);
        this.clickElement(webElement);
        this.actionG.sendKeys(new CharSequence[]{data});
        this.actionG.sendKeys(new CharSequence[]{Keys.ENTER});
        this.actionG.build().perform();
        this.logger.trace("Fill & append action on WebElement");
    }

    public void fill(WebElement webElement, String data) {
        try {
            this.actionG.moveToElement(webElement);
            this.actionG.click();
            this.actionG.sendKeys(new CharSequence[]{data});
            this.actionG.build().perform();
            this.logger.trace("Fill action on WebElement");
        } catch (Exception var4) {
            this.logger.fatal("Fill action failed on WebElement");
        }

    }

    public boolean fluentVisibility(WebElement webElement) {
        FluentWait fluentWait = new FluentWait(this.driver);

        try {
            fluentWait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception var4) {
            return false;
        }
    }

    public WebElement noStaleElementByClassName(int index, String className) {
        WebElement element = null;
        boolean T = true;

        while(T) {
            try {
                element = (WebElement)this.driver.findElements(By.className(className)).get(index - 1);
                T = false;
                this.logger.trace("WebElement noStale @className: " + className);
            } catch (Exception var6) {
            }
        }

        return element;
    }

    public WebElement noStaleElementById(WebElement webElement) {
        WebElement element = null;
        boolean T = true;
        String id = webElement.getAttribute("id");

        while(T) {
            try {
                element = this.driver.findElement(By.id(id));
                T = false;
                this.logger.trace("WebElement noStale @id: " + id);
            } catch (Exception var6) {
            }
        }

        return element;
    }

    public WebElement noStaleElementByXpath(int index, String xpath) {
        WebElement element = null;
        boolean T = true;

        while(T) {
            try {
                element = (WebElement)this.driver.findElements(By.xpath(xpath)).get(index - 1);
                T = false;
                this.logger.trace("WebElement noStale @xpath: " + xpath);
            } catch (Exception var6) {
            }
        }

        return element;
    }

    public WebElement noStaleElementByText(int index, String text) {
        WebElement element = null;
        boolean T = true;

        while(T) {
            try {
                element = (WebElement)this.driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]")).get(index - 1);
                T = false;
                this.logger.trace("WebElement noStale @text: " + text);
            } catch (Exception var6) {
            }
        }

        return element;
    }

    public String getStaleElementText(int i, String className) {
        String text = null;

        try {
            text = this.noStaleElementByClassName(i, className).getText();
            this.logger.trace("StaleElement text retrieved @className: " + className + " text : " + text);
            return text;
        } catch (StaleElementReferenceException var5) {
            this.logger.trace("Returning noStale element byClassName text");
            return this.noStaleElementByClassName(i, className).getText();
        }
    }

    public void SetIdForElement(WebElement webelement, Integer id) {
        String js = "sId=function(e, i){e.id = i;};sId(arguments[0], arguments[1]);";
        ((JavascriptExecutor)this.driver).executeScript(js, new Object[]{webelement, "911xYz*" + id.toString()});
    }

    public void setIdForElements(List<WebElement> elementsList) {
        int i = 0;
        Iterator var4 = elementsList.iterator();

        while(var4.hasNext()) {
            WebElement webElement = (WebElement)var4.next();
            ++i;
            this.SetIdForElement(webElement, i);
        }

    }

    public String getAbsoluteXPath(WebElement webElement) {
        return (String)((JavascriptExecutor)this.driver).executeScript("function absoluteXPath(element) {var comp, comps = [];var parent = null;var xpath = '';var getPos = function(element) {var position = 1, curNode;if (element.nodeType == Node.ATTRIBUTE_NODE) {return null;}for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {if (curNode.nodeName == element.nodeName) {++position;}}return position;};if (element instanceof Document) {return '/';}for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {comp = comps[comps.length] = {};switch (element.nodeType) {case Node.TEXT_NODE:comp.name = 'text()';break;case Node.ATTRIBUTE_NODE:comp.name = '@' + element.nodeName;break;case Node.PROCESSING_INSTRUCTION_NODE:comp.name = 'processing-instruction()';break;case Node.COMMENT_NODE:comp.name = 'comment()';break;case Node.ELEMENT_NODE:comp.name = element.nodeName;break;}comp.position = getPos(element);}for (var i = comps.length - 1; i >= 0; i--) {comp = comps[i];xpath += '/' + comp.name.toLowerCase();if (comp.position !== null) {xpath += '[' + comp.position + ']';}}return xpath;} return absoluteXPath(arguments[0]);", new Object[]{webElement});
    }

    public void attachFile(String url) {
        StringSelection ss = new StringSelection(url);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, (ClipboardOwner)null);

        try {
            Robot robot = new Robot();
            robot.keyPress(17);
            robot.keyPress(86);
            robot.keyRelease(86);
            robot.keyRelease(17);
            robot.keyPress(10);
            robot.keyRelease(10);
        } catch (AWTException var4) {
            var4.printStackTrace();
        }

    }

    public void newTab() {
        this.logger.trace("Opening new browser TAB (js)");
        ((JavascriptExecutor)this.driver).executeScript("window.open('about:blank', '_blank');", new Object[0]);
    }

    public void switchTab(Integer tabId) {
        this.logger.trace("Switching to tab @id: " + tabId);
        ArrayList tabs = new ArrayList(this.driver.getWindowHandles());

        try {
            this.driver.switchTo().window((String)tabs.get(tabId));
        } catch (Exception var5) {
            this.test.fail("Failed to switch tab");
            var5.printStackTrace();
        }

        try {
            this.driver.switchTo().window((String)tabs.get(tabId));
        } catch (IndexOutOfBoundsException var4) {
            System.out.println("Failed to switch tab gona wait and retry");
            this.threadSleep(2000L);
            this.driver.switchTo().window((String)tabs.get(tabId));
        }

    }

    public WebElement jsFindParent(WebElement childElement) {
        this.logger.trace("Finding parentElement (js) for childElement");
        WebElement parentElement = (WebElement)((JavascriptExecutor)this.driver).executeScript("return arguments[0].parentNode;", new Object[]{childElement});
        return parentElement;
    }

    public void closeCurrentTab() {
        String js = "window.close();";
        ((JavascriptExecutor)this.driver).executeScript(js, new Object[0]);
    }

    /** @deprecated */
    @Deprecated
    private void killChromeProcess() {
        for(int i = 0; i < this.chromePidList.size(); ++i) {
            try {
                this.process = Runtime.getRuntime().exec("taskkill /pid " + (String)this.chromePidList.get(i));
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

    }

    /** @deprecated */
    @Deprecated
    private void killDriverProcess() {
        this.driverCofnigName = this.prop.getProperty("DRIVEREXENAME", "chromedriver.exe");

        try {
            this.process = Runtime.getRuntime().exec("taskkill /IM " + this.driverCofnigName + " /F");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}
