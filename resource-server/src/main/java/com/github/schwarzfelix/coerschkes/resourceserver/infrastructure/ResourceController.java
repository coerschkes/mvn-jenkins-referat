package com.github.schwarzfelix.coerschkes.resourceserver.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@RestController
public class ResourceController {
    @GetMapping("/hello")
    String all() throws InterruptedException {
        sleep(5000);
        return "hello world";
    }
}
