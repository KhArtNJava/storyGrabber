package com.mycompany.grabberrasskazov.controllers;

import com.mycompany.grabberrasskazov.threads.MainThread;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        MainThread mt = new MainThread();
        mt.start();

        ModelAndView model = new ModelAndView("index");
        return model;
    }

}
