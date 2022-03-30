package survey;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/29 15:40
 */
public class AddSurvey extends LoginPortal {

    static WebDriver driver;

    //新建问卷
    public static void addSurvey() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.el-button.el-button--default")).click();//点击新建问卷
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='el-input']/input")).sendKeys("autoTest-问卷调查-" + System.currentTimeMillis());//设置标题
        driver.findElement(By.className("pic")).click();//点击设置封面图
        Thread.sleep(500);
        CommonMethod.getImg(driver);//在线资源库设置图片
        driver.findElement(By.cssSelector("div.el-date-editor.el-input.el-input--prefix.el-input--suffix.el-date-editor--datetime")).click();//点击设置截止日期
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.el-picker-panel__icon-btn.el-date-picker__next-btn.el-icon-arrow-right")).click();//点击后一月
        Thread.sleep(200);
        driver.findElement(By.xpath("//table[@class='el-date-table']/tbody/tr[3]/td[6]/div")).click();//点击某一天
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.el-button.el-picker-panel__link-btn.el-button--default.el-button--mini.is-plain")).click();//确定日期
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='operation']/button[2]")).click();//点击保存
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div.setBox.problemSetting")).click();//点击问题设置
        Thread.sleep(500);
        // 开始拖拽
        WebElement draggable, target;//拖拽元素及目标元素位置
        target = driver.findElement(By.cssSelector("div.drag-box_item-content.form"));//目标元素
        for (int i = 1; i < 4; i++) {
            draggable = driver.findElement(By.xpath("//div[@class='assembly']/div[" + i + "]/div"));//拖拽元素
            CommonMethod.moveWebElement(driver, draggable, target);//A拖动到B
            Thread.sleep(1000);
        }
        //设置第三个问题的问题名称，改为非必填项
        driver.findElement(By.xpath("//div[@class='drag-box_item-content form']/div[3]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='drag-box_item-content dragItem']/div[1]/div/input")).click();//改为非必填
        driver.findElement(By.xpath("//div[@class='drag-box_item-content dragItem']/div[2]/div/input")).clear();//清空编辑框
        driver.findElement(By.xpath("//div[@class='drag-box_item-content dragItem']/div[2]/div/input")).sendKeys("描述");
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='operation']/button[2]")).click();//点击保存问题设置
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='operation']/button[4]")).click();//返回到问卷列表页
        System.out.println("~~~ addSurvey()，新建问卷调查，执行成功 ~~~");
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/survey/static/index.html#/home");
                    Thread.sleep(2000);
                } else break;
            }
            if (!driver.findElement(By.xpath("//div[@class='listBox']/ul/li[@class='Item ItemActive']")).getText().contains("爱富县")) {
                driver.findElement(By.className("communit-toggle")).click();
                Thread.sleep(500);
                List<WebElement> li = driver.findElements(By.xpath("//ul[@class='listParent']/li"));
                for (int i = 0; i < li.size(); i++) {
                    if (li.get(i).getText().contains("爱富县")) {
                        li.get(i).click();
                        break;
                    }
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
