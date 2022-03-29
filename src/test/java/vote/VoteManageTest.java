package vote;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/28 15:40
 */
public class VoteManageTest {

    @Test(priority = 1)//下线投票
    public void testOffline() throws InterruptedException {
        VoteManage.offline();
    }

    @Test(priority = 2)//发布投票
    public void testOnline() throws InterruptedException {
        VoteManage.online();
    }

    @Test(priority = 3)//签发投票
    public void testPublish() throws InterruptedException {
        VoteManage.publish();
    }

    @Test(priority = 4)//编辑投票
    public void testEdit() throws InterruptedException {
        VoteManage.edit();
    }

    @Test(priority = 6)//删除投票
    public void testDelete() throws InterruptedException {
        VoteManage.delete();
    }

    @Test(priority = 5)//编辑投票结果
    public void testEditResult() throws InterruptedException {
        VoteManage.editResult();
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