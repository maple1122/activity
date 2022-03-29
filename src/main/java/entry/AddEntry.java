package entry;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wufeng
 * @date 2022/3/29 10:18
 */
public class AddEntry extends LoginPortal {

    static WebDriver driver;

    //新建普通报名
    public static void addEntry() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='add-box']/button"))).perform();//光标悬浮新建按钮
        Thread.sleep(200);
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[1]")).click();
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-普通报名-" + System.currentTimeMillis());
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();
        Thread.sleep(500);
        CommonMethod.getImg(driver);
        driver.findElement(By.id("endtime")).click();
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();
        driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();
        Thread.sleep(2000);
        //报名设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[2]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[3]")).click();
        driver.switchTo().defaultContent();
        System.out.println("~~~ addEntry()，新建报名，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //新建自定义报名
    public static void addDefineEntry() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='add-box']/button"))).perform();//光标悬浮新建按钮
        Thread.sleep(200);
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[2]")).click();
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-自定义报名-" + System.currentTimeMillis());
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();
        Thread.sleep(500);
        CommonMethod.getImg(driver);
        driver.findElement(By.id("endtime")).click();
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();
        Thread.sleep(2000);
        //选项设置
        //拖动组件
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();
        Thread.sleep(500);
        Actions action = new Actions(driver);
        WebElement moveButton = driver.findElement(By.xpath("//div[@id='controlList1']/div[1]/div"));
        //移到滑块元素并悬停
        action.moveToElement(moveButton).clickAndHold(moveButton);
        action.dragAndDropBy(moveButton, 1000, 0).perform();
        Thread.sleep(500);
        action.release();
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain+"/entry/entry/list");
                    Thread.sleep(2000);
                } else break;
            }

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains("爱富县")) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText("爱富县")).click();
                Thread.sleep(3000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
