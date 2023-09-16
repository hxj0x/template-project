package hxj.boot.biz.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxj
 * @since 2023/9/16
 */
@RestController
@RequestMapping("/test")
public class HelloWorldController {

    @GetMapping("helloWorld")
    public void helloWorld() {
        System.out.println("HelloWorldController.helloWorld");
    }

}
