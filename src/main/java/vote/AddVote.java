package vote;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/25 11:37
 */
public class AddVote extends LoginPortal {

    static WebDriver driver;

    //新建普通投票
    public static void addVote() throws InterruptedException {

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
        driver.findElement(By.name("title")).sendKeys("autoTest-普通投票-" + System.currentTimeMillis());
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();
        Thread.sleep(500);
        CommonMethod.getImg(driver);
        driver.findElement(By.id("endtime")).click();
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(2000);
        //选项设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();
        Thread.sleep(500);
        for (int j = 1; j < 4; j++) {
            driver.findElement(By.id("addTable")).click();
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/div")).click();
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/input")).sendKeys("这是第" + j + "个选项autoTest！");
            Thread.sleep(500);
        }
        driver.findElement(By.id("saveOptions")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[2]")).click();
        Thread.sleep(500);
        driver.switchTo().defaultContent();
        System.out.println("~~~ addVote()，添加普通投票，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //新建新媒体投票
    public static void addMultiMediaVote() throws InterruptedException {
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
        driver.findElement(By.name("title")).sendKeys("autoTest-多媒体投票-" + System.currentTimeMillis());
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();
        Thread.sleep(500);
        CommonMethod.getImg(driver);
        driver.findElement(By.id("endtime")).click();
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(2000);
        //选项设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();
        Thread.sleep(500);
        for (int j = 1; j < 4; j++) {
            driver.findElement(By.id("addTable")).click();
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='coverImgShow']/div/div/div")).click();
            Thread.sleep(1000);
            CommonMethod.getImg(driver);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/div")).click();
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/input")).sendKeys("这是第" + j + "个选项autoTest！");
            Thread.sleep(500);
        }
        driver.findElement(By.id("saveOptions")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[2]")).click();
        driver.switchTo().defaultContent();
        System.out.println("~~~ addMultiMediaVote()，添加多媒体投票，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //新建多组投票
    public static void addMultiGroupVote() throws InterruptedException {
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
        driver.findElement(By.name("title")).sendKeys("autoTest-多组投票-" + System.currentTimeMillis());
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();
        Thread.sleep(500);
        CommonMethod.getImg(driver);
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='isGroupConfig']/div/div[2]/i")).click();
        Thread.sleep(500);
        driver.findElement(By.id("endtime")).click();
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();
        driver.findElement(By.id("saveBtn")).click();
        Thread.sleep(1500);
        //选项设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();
        Thread.sleep(500);
        //分组管理
        driver.findElement(By.id("groupMange")).click();
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        for (int i = 1; i < 3; i++) {
            driver.findElement(By.id("title")).sendKeys("分组" + i);
            Thread.sleep(500);
            driver.findElement(By.cssSelector("i.icon.ok")).click();
            Thread.sleep(1000);
        }
        driver.findElement(By.cssSelector("button.layui-btn.layui-btn-primary.page-close")).click();
        Thread.sleep(1000);
        driver.switchTo().parentFrame();
        Thread.sleep(500);

        //录入选项
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='gl']/li"))) {
            Thread.sleep(500);
        }
        String groupName;
        List<WebElement> listG = driver.findElements(By.xpath("//ul[@id='gl']/li"));
        for (int g = 1; g < listG.size() + 1; g++) {
            driver.findElement(By.xpath("//ul[@id='gl']/li[" + g + "]")).click();
            groupName = driver.findElement(By.xpath("//ul[@id='gl']/li[" + g + "]")).getText();
            for (int j = 1; j < 4; j++) {
                driver.findElement(By.id("addTable")).click();
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/div")).click();
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/input")).sendKeys(groupName + "的第" + j + "个选项autoTest！");
                Thread.sleep(500);
            }
            driver.findElement(By.id("saveOptions")).click();
            Thread.sleep(1000);
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[2]")).click();
        driver.switchTo().defaultContent();
        System.out.println("~~~ addMultiGroupVote()，添加多组投票，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain+"/vote/vote/list");
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
