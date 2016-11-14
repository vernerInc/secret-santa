package secret.santa.standalone;

import org.junit.Test;

public class CronUtilsTest {
    @Test
    public void nextExecutionDate() throws Exception {
        System.out.println(CronUtils.nextExecutionDate("0 0 13 14 11 ?"));
    }

}