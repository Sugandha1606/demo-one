package com.saxena.microservice.demoone;

import com.saxena.microservice.demoone.model.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller
public class DemoController {

    private Devices result;
    private ModelAndView modelAndView;

    @Autowired
    private  RestTemplate restTemplate;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("{brandName}")
    public ModelAndView samsung(@PathVariable("brandName") String brandName){


        modelAndView = new ModelAndView("viewDevices");
        switch(brandName.toLowerCase()){
            case "samsung":
                //connect with the microservice


                result= restTemplate.getForObject("http://SAMSUNG/samsung/devices", Devices.class);
                //this will return a string and we need to pass this string to our view, for that we have modelandview
                modelAndView.addObject("devices", result);
                return modelAndView;


            case "apple":



                result= restTemplate.getForObject("http://APPLE/apple/devices", Devices.class);
                //this will return a string and we need to pass this string to our view, for that we have modelandview
                modelAndView.addObject("devices", result);
                return modelAndView;

            default:
                return new ModelAndView("redirect: /error");


        }

    }
}
