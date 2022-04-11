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
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[1]")).click();//点击新建普通投票
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-普通投票-" + System.currentTimeMillis());//录入标题
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();//点击设置封面图
        Thread.sleep(500);
        CommonMethod.getImg(driver);//在线资源库选择图片
        driver.findElement(By.id("endtime")).click();//点击设置封面图
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//点击下个月
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();//点击确定
        Thread.sleep(500);
        driver.findElement(By.id("saveBtn")).click();//点击保存
        Thread.sleep(2000);
        //选项设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();//点击切换到选项设置
        Thread.sleep(500);
        for (int j = 1; j < 4; j++) {
            driver.findElement(By.id("addTable")).click();//点击添加选项
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/div")).click();//点击激活选项编辑框
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/input")).sendKeys("这是第" + j + "个选项autoTest！");//录入选项内容
            Thread.sleep(500);
        }
        driver.findElement(By.id("saveOptions")).click();//点击保存选项
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[2]")).click();//点击保存
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
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[2]")).click();//点击新建多媒体投票
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-多媒体投票-" + System.currentTimeMillis());//录入多媒体标题
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();//点击封面设置
        Thread.sleep(500);
        CommonMethod.getImg(driver);//在线资源库设置图片
        driver.findElement(By.id("endtime")).click();//点击截止时间
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//点击下个月
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();//点击确定时间
        Thread.sleep(500);
        driver.findElement(By.id("saveBtn")).click();//点击保存
        Thread.sleep(2000);
        //选项设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();//点击选项设置
        Thread.sleep(500);
        for (int j = 1; j < 4; j++) {
            driver.findElement(By.id("addTable")).click();//点击添加选项
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='coverImgShow']/div/div/div")).click();//点击添加选项封面图
            Thread.sleep(1000);
            CommonMethod.getImg(driver);//在线资源库添加图片
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/div")).click();//点击激活选项内容编辑框
            Thread.sleep(200);
            driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/input")).sendKeys("这是第" + j + "个选项autoTest！");//录入选项内容
            Thread.sleep(500);
        }
        driver.findElement(By.id("saveOptions")).click();//点击保存选项
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[2]")).click();//点击保存
        driver.switchTo().defaultContent();
        System.out.println("~~~ addMultiMediaVote()，添加多媒体投票，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //新建多组投票
    public static void addMultiGroupVote() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='add-box']/button"))).perform();//光标悬浮新建按钮
        Thread.sleep(200);
        driver.findElement(By.xpath("//ul[@id='addLayer']/li[1]")).click();//点击新建普通投票
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        //基本信息
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autoTest-多组投票-" + System.currentTimeMillis());//录入投票标题
        driver.findElement(By.cssSelector("div.upload-btn.addimg.addimg-btn")).click();//点击设置封面图
        Thread.sleep(500);
        CommonMethod.getImg(driver);//在线资源库设置图片
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='isGroupConfig']/div/div[2]/i")).click();//设置为多组投票
        Thread.sleep(500);
        driver.findElement(By.id("endtime")).click();//点击截止时间
        Thread.sleep(200);
        driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//点击下个月
        Thread.sleep(200);
        driver.findElement(By.className("laydate-btns-confirm")).click();//点击确定
        Thread.sleep(500);
        driver.findElement(By.id("saveBtn")).click();//点击保存
        Thread.sleep(1500);
        //选项设置
        driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();//点击选项设置
        Thread.sleep(500);
        //分组管理
        driver.findElement(By.id("groupMange")).click();//点击分组管理
        Thread.sleep(500);
        for (int i = 1; 1 < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);
                break;
            }
        }
        for (int i = 1; i < 3; i++) {
            driver.findElement(By.id("title")).sendKeys("分组" + i);//录入分组名称
            Thread.sleep(500);
            driver.findElement(By.cssSelector("i.icon.ok")).click();//点击保存分组名称
            Thread.sleep(1000);
        }
        driver.findElement(By.cssSelector("button.layui-btn.layui-btn-primary.page-close")).click();//点击返回
        Thread.sleep(1000);
        driver.switchTo().parentFrame();
        Thread.sleep(500);

        //录入选项
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='gl']/li"))) {//校验是否有分组列表
            Thread.sleep(500);
        }
        String groupName;
        List<WebElement> listG = driver.findElements(By.xpath("//ul[@id='gl']/li"));//分组的数据list
        for (int g = 1; g < listG.size() + 1; g++) {
            driver.findElement(By.xpath("//ul[@id='gl']/li[" + g + "]")).click();//点击分组
            groupName = driver.findElement(By.xpath("//ul[@id='gl']/li[" + g + "]")).getText();//获取当前分组名
            for (int j = 1; j < 4; j++) {
                driver.findElement(By.id("addTable")).click();//点击添加选项
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/div")).click();//点击激活选项编辑框
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + j + "]/td[@data-field='title']/input")).sendKeys(groupName + "的第" + j + "个选项autoTest！");//录入选项名称
                Thread.sleep(500);
            }
            driver.findElement(By.id("saveOptions")).click();//点击保存选项
            Thread.sleep(1000);
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='save-box']/button[2]")).click();//点击保存
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
