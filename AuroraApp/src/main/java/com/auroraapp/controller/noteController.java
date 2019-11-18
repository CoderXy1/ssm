package com.auroraapp.controller;


import com.auroraapp.model.Gallery;
import com.auroraapp.model.Note;
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

    @RequestMapping("/selectNoteById")
    @ResponseBody
    public Note selectNoteById(@RequestParam("noteId") String noteId) {
        return noteService.selectByPrimaryKey(noteId);
    }

    @RequestMapping("/deleteNote")
    @ResponseBody
    public int deleteNote(@RequestParam("noteId") String noteId) {
        return noteService.deleteByPrimaryKey(noteId);
    }

    @RequestMapping("/saveNote")
    @ResponseBody
    public int saveNote(@RequestParam("noteId") String noteId,@RequestParam("noteTitle") String noteTitle,@RequestParam("content") String content,@RequestParam("noteColor")String noteColor) {
        Note note = new Note();
        note.setNoteid(noteId);
        note.setNotetitle(noteTitle);
        note.setContent(content);
        note.setNotecolor(noteColor);
        return noteService.updateByPrimaryKeySelective(note);
    }

    @RequestMapping("/insertNote")
    @ResponseBody
    public int insertGallery(@RequestParam("noteId") String noteId,@RequestParam("noteTitle") String noteTitle,@RequestParam("content") String content,@RequestParam("noteColor")String noteColor) {
        Note note = new Note();
        note.setNoteid(noteId);
        note.setNotetitle(noteTitle);
        note.setContent(content);
        note.setNotecolor(noteColor);
        note.setNotedatetime(new Date());
        return noteService.insert(note);
    }

}
