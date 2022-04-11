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
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[1]")).click();//点击新建普通报名
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-普通报名-" + System.currentTimeMillis());//录入报名标题
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();//点击上传封面图
        Thread.sleep(500);
        CommonMethod.getImg(driver);//在线资源库获取图片
        driver.findElement(By.id("endtime")).click();//点击截止时间
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//下一月
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();//点击确定
        driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();//点击保存
        Thread.sleep(2000);
        //报名设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[2]")).click();//点击报名设置
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();//点击保存
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[3]")).click();//点击返回
        driver.switchTo().defaultContent();
        System.out.println("~~~ addEntry()，新建报名，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //新建自定义报名
    public static void addDefineEntry() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='add-box']/button"))).perform();//光标悬浮新建按钮
        Thread.sleep(200);
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[2]")).click();//点击新建自定义报名
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-自定义报名-" + System.currentTimeMillis());//录入报名标题
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();//点击上传封面图
        Thread.sleep(500);
        CommonMethod.getImg(driver);//在线资源库设置图片
        driver.findElement(By.id("endtime")).click();//点击截止时间
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//点击下一月
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();//点击截止时间的确定
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();//点击保存
        Thread.sleep(2000);

        //选项设置，！！！拖拽不生效！！！
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();//点击选项设置
        Thread.sleep(500);
        WebElement draggable, target;//拖拽元素及目标元素位置
        target = driver.findElement(By.id("showBox"));//目标元素
        for (int i = 1; i < 4; i++) {
            draggable = driver.findElement(By.xpath("//div[@id='controlBox']/div[1]/div[@id='controlList1']/div["+i+"]/div"));//拖拽元素
            CommonMethod.moveWebElement(driver, draggable, target);//A拖动到B
            Thread.sleep(1000);
        }

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

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains(siteName)) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
