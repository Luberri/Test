package com.example.test;
import com.itu.demo.annotations.Controller;
import com.itu.demo.annotations.HandleURL;
@Controller
public class Test {
    @HandleURL("/test1")
    public String testHandler() {
        return "Test handler invoked";
    }
}
