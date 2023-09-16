package hxj.boot.biz.system.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author huangxj
 * @since 2023/9/16
 */
@Component
public class TestStartUpRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("TestStartUpRunner.run");
    }
}
