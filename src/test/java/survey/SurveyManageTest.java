package survey;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/30 9:18
 */
public class SurveyManageTest {

    @Test
    public void testOffline() throws InterruptedException {
        SurveyManage.offline();
    }

    @Test
    public void testOnline() throws InterruptedException {
        SurveyManage.online();
    }

    @Test
    public void testPublish() throws InterruptedException {
        SurveyManage.publish();
    }

    @Test
    public void testEdit() throws InterruptedException {
        SurveyManage.edit();
    }

    @Test
    public void testDelete() throws InterruptedException {
        SurveyManage.delete();
    }
}