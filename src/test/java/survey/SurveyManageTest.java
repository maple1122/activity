package survey;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/30 9:18
 */
public class SurveyManageTest {

    @Test(priority = 4)//下线问卷
    public void testOffline() throws InterruptedException {
        SurveyManage.offline();
    }

    @Test(priority = 2)//发布问卷
    public void testOnline() throws InterruptedException {
        SurveyManage.online();
    }

    @Test(priority = 3)//签发问卷
    public void testPublish() throws InterruptedException {
        SurveyManage.publish();
    }

    @Test(priority = 1)//编辑问卷
    public void testEdit() throws InterruptedException {
        SurveyManage.edit();
    }

    @Test(priority = 5)//删除问卷
    public void testDelete() throws InterruptedException {
        SurveyManage.delete();
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}