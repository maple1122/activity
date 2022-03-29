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
 * @date 2022/3/25 16:12
 */
public class VoteManage extends LoginPortal {

    static WebDriver driver;

    //投票下线
    public static void offline() throws InterruptedException {
        search(2);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='vote-left']/a[@class='referrals-btn']")).click();
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();
            Thread.sleep(1000);
            System.out.println("~~~ offline()，投票下线，执行成功 ~~~");
        } else System.out.println("没有可下线的自动化测试投票数据");
        Thread.sleep(3000);
    }

    //投票发布
    public static void online() throws InterruptedException {
        search(3);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='vote-left']/a[@class='publish-btn']")).click();
            System.out.println("~~~ online()，投票发布，执行成功 ~~~");
        } else System.out.println("没有已下线的自动化测试投票数据");
    }

    //投票签发
    public static void publish() throws InterruptedException {
        search(2);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='vote-left']/a[@class='sign-btn']")).click();
            Thread.sleep(500);
            Boolean selected = CommonMethod.getPublishChannel(driver, "测试test");
            if (selected) {
                driver.findElement(By.className("sign-affirm")).click();
                System.out.println("~~~ publish()，投票签发，执行成功 ~~~");
            } else {
                driver.findElement(By.className("sign-cancel")).click();
                System.out.println("没找到签发频道");
            }
        } else System.out.println("没有可签发的自动化测试投票数据");
        Thread.sleep(3000);
    }

    //投票编辑
    public static void edit() throws InterruptedException {
        Boolean canEdit = true;
        //获取可编辑的数据
        search(1);//搜索未发布的测试数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
            search(3);//搜索已下线的测试数据
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
                offline();//已发布的测试数据进行下线
                search(3);//搜索已下线的数据
                if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li")))
                    canEdit = false;
            }
        }
        if (canEdit) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='edit-box']/a"))).perform();
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@id='voteList']/li[1]/div[@class='vote-btns']/div[@class='edit-box']/ul/li[@class='edit-btn']")).click();
            Thread.sleep(1000);
            //编辑页进行编辑
            for (int i = 1; 1 < 20; i++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                    driver.switchTo().frame("layui-layer-iframe" + i);
                    break;
                }
            }
            Thread.sleep(500);
            driver.findElement(By.name("title")).clear();
            driver.findElement(By.name("title")).sendKeys("autoTest-edit投票-" + System.currentTimeMillis());
            Thread.sleep(500);
            driver.findElement(By.id("saveBtn")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("back-icon")).click();
            driver.switchTo().parentFrame();
            System.out.println("~~~ edit()，投票编辑，执行成功 ~~~");
        } else System.out.println("没有可编辑的自动化测试投票数据");
        Thread.sleep(3000);
    }

    //投票删除
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
                Thread.sleep(1000);
            }
            System.out.println("~~~ delete()，投票删除，执行成功 ~~~");
        } else System.out.println("没有可删除的自动化测试投票数据");
        Thread.sleep(3000);
    }

    //修改投票票数
    public static void editResult() throws InterruptedException {

        Boolean canedit = true;
        search(2);
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li"))) {
            search(3);
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='voteList']/li")))
                canedit = false;
        }
        if (canedit) {
            List<WebElement> list = driver.findElements(By.xpath("//ul[@id='voteList']/li"));
            Actions actions = new Actions(driver);
            actions.moveToElement(list.get(0).findElement(By.xpath("div[@class='vote-btns']/div[@class='edit-box']/a"))).perform();//光标悬浮新建按钮
            Thread.sleep(200);
            list.get(0).findElement(By.className("result-btn")).click();
            Thread.sleep(1000);
            List<WebElement> result = driver.findElements(By.xpath("//div[@id='voteResultList']/div"));
            int count = 0;
            int resultCount;
            for (int i = 1; i < result.size() + 1; i++) {
                count = (int) (1 + Math.random() * 10);
                driver.findElement(By.xpath("//div[@id='voteResultList']/div[" + i + "]/div/a")).click();
                Thread.sleep(500);
                resultCount = Integer.parseInt(driver.findElement(By.id("editVoteCount")).getAttribute("value"));
                if (resultCount != 0) count = resultCount + 5;
                driver.findElement(By.id("editVoteCount")).clear();
                driver.findElement(By.id("editVoteCount")).sendKeys(String.valueOf(count));
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();
                Thread.sleep(1000);
            }
            driver.findElement(By.cssSelector("a.layui-layer-ico.layui-layer-close.layui-layer-close2")).click();
            System.out.println("~~~ editResult()，编辑投票结果，执行成功 ~~~");
        } else System.out.println("没有可编辑结果的自动化测试投票数据");
    }

    //投票搜索
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
                    driver.get(domain + "/vote/vote/list");
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
