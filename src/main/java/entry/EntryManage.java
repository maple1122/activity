package entry;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/29 11:11
 */
public class EntryManage extends LoginPortal {
    static WebDriver driver;

    //报名下线
    public static void offline() throws InterruptedException {
        search(2);//搜索已发布
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {//校验是否有数据
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='vote-left']/a[@class='referrals-btn']")).click();//点击下线
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定下线
            Thread.sleep(1000);
            System.out.println("~~~ offline()，报名下线，执行成功 ~~~");
        } else System.out.println("没有可下线的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名发布
    public static void online() throws InterruptedException {
        Boolean canOnline = true;
        search(1);//搜索未发布的测试数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {//校验是否有数据
            search(3);//搜索已下线的测试数据
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li")))//校验是否有数据
                canOnline = false;
        }
        if (canOnline) {
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='vote-left']/a[@class='publish-btn']")).click();//点击发布
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ online()，报名发布，执行成功 ~~~");
        } else System.out.println("没有已下线的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名签发
    public static void publish() throws InterruptedException {
        search(2);//搜索已发布的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {//校验是否有数据
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='vote-left']/a[@class='sign-btn']")).click();//点击签发
            Thread.sleep(1000);
            Boolean selected = CommonMethod.getPublishChannel(driver, "测试test");//搜索测试频道
            if (selected) {
                driver.findElement(By.className("sign-affirm")).click();//点击确定
                System.out.println("~~~ publish()，报名签发，执行成功 ~~~");
            } else {
                driver.findElement(By.className("sign-cancel")).click();//点击取消
                System.out.println("没找到签发频道");
            }
        } else System.out.println("没有可签发的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名编辑
    public static void edit() throws InterruptedException {
        Boolean canEdit = true;
        //获取可编辑的数据
        search(1);//搜索未发布的测试数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {//校验是否有数据
            search(3);//搜索已下线的测试数据
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {//校验是否有数据
                offline();//已发布的测试数据进行下线
                search(3);//搜索已下线的数据
                if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li")))//校验是否有数据
                    canEdit = false;
            }
        }
        if (canEdit) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='edit-box']/a"))).perform();//光标悬浮
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='edit-box']/ul/li[@class='edit-btn']")).click();//点击编辑
            Thread.sleep(1000);
            //编辑页进行编辑
            for (int i = 1; 1 < 20; i++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                    driver.switchTo().frame("layui-layer-iframe" + i);
                    break;
                }
            }
            Thread.sleep(500);
            driver.findElement(By.name("title")).clear();//清空标题
            driver.findElement(By.name("title")).sendKeys("autoTest-edit报名-" + System.currentTimeMillis());//编辑标题
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='save-box']/button[1]")).click();//点击保存
            Thread.sleep(2000);

//            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='step-list']/li[3]"))) {
//                driver.findElement(By.xpath("//ul[@class='step-list']/li[3]")).click();
//                Thread.sleep(500);
////                if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='showBox']/div[@class='no-show']"))) {
////            编辑选项设置
////                Actions actions1 = new Actions(driver);
//                WebElement draggable, target;//拖拽元素及目标元素位置
//                target = driver.findElement(By.xpath("//div[@class='show-box']"));//目标元素
//                for (int i = 1; i < 4; i++) {
//                    draggable = driver.findElement(By.xpath("//div[@id='controlBox']/div[1]/div[@id='controlList1']/div[" + i + "]"));//拖拽元素
////                    System.out.println("draggable1:" + draggable.getAttribute("draggable"));
////                    actions1.clickAndHold(draggable).perform();
////                    System.out.println("draggable2:" + draggable.getAttribute("draggable"));
//                    CommonMethod.moveWebElement(driver, draggable, target);//A拖动到B
//                    Thread.sleep(1000);
//                }
//            }
//            }
            driver.findElement(By.className("back-icon")).click();//点击返回
            driver.switchTo().parentFrame();
            System.out.println("~~~ edit()，报名编辑，执行成功 ~~~");
        } else System.out.println("没有可编辑的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名删除
    public static void delete() throws InterruptedException {
        Boolean canDelete = true;
        //获取可编辑的数据
        search(1);//搜索未发布的测试数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
            search(3);//搜索已下线的测试数据
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
                offline();//已发布的测试数据进行下线
                search(3);//搜索已下线的数据
                if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li")))
                    canDelete = false;
            }
        }
        if (canDelete) {
            List<WebElement> list = driver.findElements(By.xpath("//ul[@id='voteList']/li"));
            for (int i = list.size(); i > 0; i--) {
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(By.xpath("//ul[@id='voteList']/li[" + i + "]/div[@class='vote-btns']/div[@class='edit-box']/a"))).perform();
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@id='voteList']/li[" + i + "]/div[@class='vote-btns']/div[@class='edit-box']/ul/li[@class='delete-btn']")).click();
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();
                Thread.sleep(2000);
            }
            System.out.println("~~~ delete()，报名删除，执行成功 ~~~");
        } else System.out.println("没有可删除的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名搜索
    private static void search(int type) throws InterruptedException {
        List<WebElement> liStatus = driver.findElements(By.xpath("//div[@class='vote-state']/span"));
        if (type > 0 && type <= 3) liStatus.get(type).click();
        Thread.sleep(1500);
        driver.findElement(By.name("keyword")).clear();
        driver.findElement(By.name("keyword")).sendKeys("autoTest");
        driver.findElement(By.id("searchBtn")).click();
        Thread.sleep(2000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/entry/entry/list");
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
