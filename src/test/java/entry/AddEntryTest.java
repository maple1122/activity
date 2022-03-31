package entry;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/29 10:49
 */
public class AddEntryTest {

    @Test(priority = 1)//新建普通报名
    public void testAddEntry() throws InterruptedException {
        AddEntry.addEntry();
        AddEntry.addEntry();
    }

//    @Test(priority = 2)//新建自定义报名
////    public void testAddDefineEntry() throws InterruptedException {
////        AddEntry.addDefineEntry();
////    }

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