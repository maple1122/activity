package vote;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/25 15:45
 */
public class AddVoteTest {

    @Test(priority = 1)//新建普通投票
    public void testAddVote() throws InterruptedException {
        AddVote.addVote();
    }

    @Test(priority = 2)//新建新媒体投票
    public void testAddMultiMediaVote() throws InterruptedException {
        AddVote.addMultiMediaVote();
    }

    @Test(priority = 3)//新建多组投票
    public void testAddMultiGroupVote() throws InterruptedException {
        AddVote.addMultiGroupVote();
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