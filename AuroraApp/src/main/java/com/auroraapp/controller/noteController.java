package com.auroraapp.controller;


import com.auroraapp.model.Gallery;
import com.auroraapp.service.IGalleryService;
import com.auroraapp.service.INoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class noteController {

    @Resource
    private INoteService noteService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String,Object>> selectAll(@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize")int pageSize) {
        return noteService.selectAll(pageIndex, pageSize);
    }
}
