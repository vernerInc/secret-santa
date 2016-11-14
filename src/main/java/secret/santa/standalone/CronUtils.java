package secret.santa.standalone;

import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;

class CronUtils {
    static Date nextExecutionDate(String cron) {
        return new CronSequenceGenerator(cron).next(new Date());
    }
}
