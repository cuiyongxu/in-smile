package com.facishare;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuiyongxu on 17/9/25.
 */
@RequestMapping("/")
public class BaseController {

    @RequestMapping("/main")
    public ModelAndView Main(){
        return new ModelAndView("index");
    }
}
