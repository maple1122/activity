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
public class SurveyManage extends LoginPortal {

    static WebDriver driver;

    //报名下线
    public static void offline() throws InterruptedException {
        search(2);//搜索已发布的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div"))) {//校验是否有数据
            List<WebElement> survey = driver.findElements(By.xpath("//div[@class='list infinite-list']/div"));//数据列表
            for (int i = survey.size(); i > 0; i--) {
                driver.findElement(By.xpath("//div[@class='list infinite-list']/div[" + i + "]/div[@class='describe']/div[@class='bottomDescribe']/div[@class='operation']/span[4]")).click();//点击下线
                Thread.sleep(200);
                driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//点击确定
                Thread.sleep(2000);
            }
            System.out.println("~~~ offline()，报名下线，执行成功 ~~~");
        } else System.out.println("没有可下线的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名发布
    public static void online() throws InterruptedException {
        Boolean canOnline = true;
        search(1);//搜索未发布的测试数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div"))) {//校验是否有数据
            search(3);//搜索已下线的测试数据
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div")))//校验是否有数据
                canOnline = false;
        }
        if (canOnline) {//可以发布
            driver.findElement(By.xpath("//div[@class='list infinite-list']/div[1]/div[@class='describe']/div[@class='bottomDescribe']/div[@class='operation']/span[1]")).click();//点击发布
            Thread.sleep(200);
            System.out.println("~~~ online()，报名发布，执行成功 ~~~");
        } else System.out.println("没有已下线的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名签发
    public static void publish() throws InterruptedException {
        search(2);//搜索已发布的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div"))) {//校验是否有数据
            driver.findElement(By.xpath("//div[@class='list infinite-list']/div[1]/div[@class='describe']/div[@class='bottomDescribe']/div[@class='operation']/span[3]")).click();//点击签发
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='checkPeople']/div[2]/input")).sendKeys("测试test");//搜索测试频道
            Thread.sleep(1000);
            if (driver.findElement(By.xpath("//div[@class='el-tree']/div[@class='el-tree-node is-expanded is-focusable']/div[@class='el-tree-node__children']/div[@class='el-tree-node is-expanded is-focusable']/div[@class='el-tree-node__children']/div[@class='el-tree-node is-expanded is-focusable']/div/div/label/span[2]")).getText().contains("测试test")) {//校验是否是测试频道
                driver.findElement(By.xpath("//div[@class='el-tree']/div[@class='el-tree-node is-expanded is-focusable']/div[@class='el-tree-node__children']/div[@class='el-tree-node is-expanded is-focusable']/div[@class='el-tree-node__children']/div[@class='el-tree-node is-expanded is-focusable']/div/div/label/span[1]/span")).click();//选中测试频道
                Thread.sleep(1000);
                driver.findElement(By.cssSelector("div.footerBtn.trueBtn")).click();//点击确定
                System.out.println("~~~ publish()，报名签发，执行成功 ~~~");
            } else {
                driver.findElement(By.cssSelector("div.footerBtn.cancelBtn")).click();//点击取消
                System.out.println("没找到签发频道");
            }
        } else System.out.println("没有可签发的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名编辑
    public static void edit() throws InterruptedException {
        //获取可编辑的数据
        search(1);//搜索未发布的测试数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div"))) {//校验是否有数据
            Thread.sleep(200);
            List<WebElement> survey = driver.findElements(By.xpath("//div[@class='list infinite-list']/div"));//自动化测试数据列表
            Actions action = new Actions(driver);
            action.moveToElement(survey.get(0).findElement(By.xpath("div[@class='describe']/div[@class='bottomDescribe']/div[2]"))).perform();//光标悬浮“其他操作”icon
            Thread.sleep(200);
            survey.get(0).findElement(By.xpath("div[@class='describe']/div[@class='bottomDescribe']/div[2]/div/span[1]")).click();//点击编辑
            Thread.sleep(1000);
            //编辑页进行编辑
            driver.findElement(By.xpath("//div[@class='el-input']/input")).clear();//清空标题
            driver.findElement(By.xpath("//div[@class='el-input']/input")).sendKeys("autoTest-edit问卷-" + System.currentTimeMillis());//编辑标题
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='operation']/button[2]")).click();//点击保存
            Thread.sleep(2000);

            //编辑选项设置
            driver.findElement(By.cssSelector("div.setBox.problemSetting")).click();//点击切换到问题设置
            Thread.sleep(1000);
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='drag-box_item-content form']/div"))) {//校验问题设置是否有数据
                // 开始拖拽
                WebElement draggable, target;//拖拽元素及目标元素位置
                target = driver.findElement(By.cssSelector("div.drag-box_item-content.form"));//目标元素
                for (int i = 1; i < 4; i++) {
                    draggable = driver.findElement(By.xpath("//div[@class='assembly']/div[" + i + "]/div"));//拖拽元素
                    CommonMethod.moveWebElement(driver, draggable, target);//A拖动到B
                    Thread.sleep(1000);
                }
            }
            driver.findElement(By.xpath("//div[@class='operation']/button[2]")).click();//点击保存问题设置
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='operation']/button[4]")).click();//返回到问卷列表页
            System.out.println("~~~ edit()，问卷编辑，执行成功 ~~~");
        } else System.out.println("没有可编辑的自动化测试问卷数据");
        Thread.sleep(3000);
    }

    //报名删除
    public static void delete() throws InterruptedException {
        Boolean canDelete = true;
        //获取可编辑的数据
        search(1);//搜索未发布的测试数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div"))) {
            search(3);//搜索已下线的测试数据
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div"))) {
                offline();//已发布的测试数据进行下线
                search(3);//搜索已下线的数据
                if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='list infinite-list']/div")))
                    canDelete = false;
            }
        }
        if (canDelete) {
            List<WebElement> survey = driver.findElements(By.xpath("//div[@class='list infinite-list']/div"));//数据列表
            for (int i = survey.size(); i > 0; i--) {
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(By.xpath("//div[@class='list infinite-list']/div[" + i + "]/div[@class='describe']/div[@class='bottomDescribe']/div[2]/img"))).perform();//光标悬浮操作icon
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@class='list infinite-list']/div[" + i + "]/div[@class='describe']/div[@class='bottomDescribe']/div[2]/div/span[3]")).click();//点击删除
                Thread.sleep(500);
                driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//确定删除
                Thread.sleep(2000);
            }
            System.out.println("~~~ delete()，报名删除，执行成功 ~~~");
        } else System.out.println("没有可删除的自动化测试报名数据");
        Thread.sleep(3000);
    }

    //报名搜索
    private static void search(int type) throws InterruptedException {
        List<WebElement> liStatus = driver.findElements(By.xpath("//div[@class='time-filter']/span"));//筛选状态列表
        if (type > 0 && type <= 3) liStatus.get(type).click();//1、未发布；2、已发布；3、已下线
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='keyword-search']/input")).clear();//清空搜索框
        driver.findElement(By.xpath("//div[@class='keyword-search']/input")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.xpath("//div[@class='keyword-search']/button")).click();//点击搜索
        Thread.sleep(2000);
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
            if (!driver.findElement(By.xpath("//ul[@class='listParent']/li[1]")).getText().contains("爱富县")) {
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
