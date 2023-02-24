package portfolio.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.me.service.ResumeService;


@Controller
public class ResumeController {

  private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }


    @RequestMapping("/")
    public String index() {

        return "index";

    }








}