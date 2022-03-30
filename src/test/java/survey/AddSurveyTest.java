package survey;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/29 17:01
 */
public class AddSurveyTest {

    @Test//新建问卷调查
    public void testAddSurvey() throws InterruptedException {
        AddSurvey.addSurvey();
    }
}