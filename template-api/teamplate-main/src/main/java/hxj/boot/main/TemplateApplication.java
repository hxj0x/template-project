package hxj.boot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"hxj.boot.biz", "hxj.boot.main"})
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }
}
