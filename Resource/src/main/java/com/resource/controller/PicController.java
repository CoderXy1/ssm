package com.resource.controller;

import com.resource.jsoup.picJsoup;
import com.resource.model.Pic;
import com.resource.service.IPicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pic")
public class PicController {

    @Resource
    private IPicService picService;

    @RequestMapping("/insertPic")
    @ResponseBody
    public int insertPic() throws Exception{

        int picId = selectMaxPicId() + 1;
        List<Pic> list = picJsoup.getText(picId + 131100);
        for (Pic pic:list){
            picId = selectMaxPicId() + 1;
            this.picService.insertPic(picId,pic.getPicName(),pic.getPicPath(),pic.getPicSize(),pic.getPicDescribe(),pic.getPicType(),new Date());
        }
        return 1;
    }

    @RequestMapping("/selectMaxPicId")
    @ResponseBody
    public int selectMaxPicId(){

        return this.picService.selectMaxPicId();

    }

    @RequestMapping("/selectPicByName")
    @ResponseBody
    public List<Pic> selectPicByName(@RequestParam("picName")String picName, @RequestParam("pageIndex")int pageIndex, @RequestParam("pageSize")int pageSize){

        return this.picService.selectPicByName(picName, pageIndex, pageSize);

    }

    @RequestMapping("/selectPicNum")
    @ResponseBody
    public int selectPicNum(@RequestParam("picName")String picName){

        return this.picService.selectPicNum(picName);

    }

}
