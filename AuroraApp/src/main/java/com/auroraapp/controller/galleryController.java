package com.auroraapp.controller;


import com.auroraapp.model.Gallery;
import com.auroraapp.service.IFileService;
import com.auroraapp.service.IGalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gallery")
public class galleryController {

    @Resource
    private IGalleryService galleryService;

    @Resource
    private IFileService fileService;

    @RequestMapping("/insertGallery")
    @ResponseBody
    public int insertGallery(@RequestParam("galleryid") String galleryid,@RequestParam("galleryname") String galleryname,@RequestParam("fileid") String fileid,@RequestParam("galleryDescribe")String galleryDescribe) {
        Gallery gallery = new Gallery();
        gallery.setGalleryid(galleryid);
        gallery.setGalleryname(galleryname);
        gallery.setFileid(fileid);
        gallery.setPutdate(new Date());
        gallery.setGalleryDescribe(galleryDescribe);
        return galleryService.insert(gallery);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String,Object>> selectAll(@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize")int pageSize) {
        return galleryService.selectAll(pageIndex, pageSize);
    }

    @RequestMapping("/deleteGallery")
    @ResponseBody
    public int deleteGallery(@RequestParam("galleryId") String galleryId,@RequestParam("fileId") String fileId) {
        this.galleryService.deleteByPrimaryKey(galleryId);
        this.fileService.deleteByPrimaryKey(fileId);
        return 1;
    }
}
