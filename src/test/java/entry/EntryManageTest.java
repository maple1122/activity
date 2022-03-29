package entry;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/29 11:13
 */
public class EntryManageTest {

    @Test(priority = 3)//下线报名
    public void testOffline() throws InterruptedException {
        EntryManage.offline();
    }

    @Test(priority = 1)//发布报名
    public void testOnline() throws InterruptedException {
        EntryManage.online();
    }

    @Test(priority = 2)//签发报名
    public void testPublish() throws InterruptedException {
        EntryManage.publish();
    }

    @Test(priority = 4)//编辑报名
    public void testEdit() throws InterruptedException {
        EntryManage.edit();
    }

    @Test(priority = 5)//删除报名
    public void testDelete() throws InterruptedException {
        EntryManage.delete();
    }

}