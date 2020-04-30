package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.ActivityInfo;
import com.clothSale.model.GoodsBrand;
import com.clothSale.service.IActivityInfoService;
import com.clothSale.service.IGoodsBrandService;
import com.clothSale.service.IUploadFileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/ActivityInfo")
public class ActivityInfoController {

    @Resource
    private IActivityInfoService activityInfoService;

    @RequestMapping("/selectActivityInfo")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectActivityInfo(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam(required = false) String activity_name, @RequestParam(required = false) int activity_state) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = activityInfoService.selectActivityInfo(pageIndex, pageSize, activity_name, activity_state);
        HashMap<String,Object> total = activityInfoService.selectNumActivityInfo(activity_name, activity_state);

        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(total);
            res.setMsg("查询成功");
            res.setSuccess(true);
        } else {
            res.setMsg("查询失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/selectSpuAndActivity")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectSpuAndActivity(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam("activity_id") String activity_id, @RequestParam(required = false) String goods_name) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = activityInfoService.selectSpuAndActivity(pageIndex, pageSize, activity_id,goods_name);
        HashMap<String,Object> total = activityInfoService.selectNumSpuAndActivity(activity_id,goods_name);

        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(total);
            res.setMsg("查询成功");
            res.setSuccess(true);
        } else {
            res.setMsg("查询失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/selectNumActivityInfo")
    @ResponseBody
    public HashMap<String,Object> selectNumActivityInfo(@RequestParam(required = false) String activity_name) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        HashMap<String,Object> total = activityInfoService.selectNumActivityInfo(activity_name, -1);

        return total;
    }

    @RequestMapping("/selectSingleActivityInfo")
    @ResponseBody
    public RequsetData<ActivityInfo> selectSingleActivityInfo(@RequestParam("activity_id") String activity_id) {

        RequsetData<ActivityInfo> res = new RequsetData<>();

       res.setItem(activityInfoService.selectByPrimaryKey(activity_id));

        return res;
    }

    @RequestMapping("/selectSpuOfActivity")
    @ResponseBody
    public RequsetData<List<HashMap<String, Object>>> selectSpuOfActivity(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam("activity_id") String activity_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = activityInfoService.selectSpuOfActivity(pageIndex, pageSize, activity_id);
        HashMap<String,Object> total = activityInfoService.selectNumSpuOfActivity(activity_id);

        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(total);
            res.setMsg("查询成功");
            res.setSuccess(true);
        } else {
            res.setMsg("查询失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/insertSelective")
    @ResponseBody
    public RequsetData<Integer> insertSelective(@RequestParam("activity_id") String activity_id,@RequestParam("activity_name")String activity_name,
                                                @RequestParam("activity_describe")String activity_describe,@RequestParam("activity_date_begin")String activity_date_begin,@RequestParam("activity_date_end")String activity_date_end) throws Exception{

        RequsetData<Integer> res = new RequsetData<>();

        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(activity_id);
        activityInfo.setActivityName(activity_name);
        activityInfo.setActivityDescribe(activity_describe);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date date_begin = df.parse(activity_date_begin.replace("Z", " UTC"));
        Date date_end = df.parse(activity_date_end.replace("Z", " UTC"));
        activityInfo.setActivityDateBegin(date_begin);
        activityInfo.setActivityDateEnd(date_end);
        Date now = new Date();
        if (now.compareTo(date_begin) >= 0){
            activityInfo.setActivityState(1);
        }else {
            activityInfo.setActivityState(0);
        }
        activityInfo.setGmtCreate(now);

        int count = activityInfoService.insertSelective(activityInfo);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/deleteByPrimaryKey")
    @ResponseBody
    public RequsetData<Integer> deleteByPrimaryKey(@RequestParam("activity_id") String activity_id){

        RequsetData<Integer> res = new RequsetData<>();

        int count = activityInfoService.deleteByPrimaryKey(activity_id);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/insertActivitySpu")
    @ResponseBody
    public RequsetData<Integer> insertActivitySpu(@RequestParam("activity_id") String activity_id,@RequestParam("spu_id")String spu_id){

        RequsetData<Integer> res = new RequsetData<>();

        int count = activityInfoService.insertActivitySpu(activity_id,spu_id,new Date());

        if (count == 1) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/deleteActivitySpu")
    @ResponseBody
    public RequsetData<Integer> deleteActivitySpu(@RequestParam("activity_id") String activity_id,@RequestParam("spu_id")String spu_id){

        RequsetData<Integer> res = new RequsetData<>();

        int count = activityInfoService.deleteActivitySpu(activity_id, spu_id);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }
        return res;
    }

    @RequestMapping("/updateActivityInfo")
    @ResponseBody
    public RequsetData<Integer> updateActivityInfo(@RequestParam("activityId") String activityId,@RequestParam("activityName") String activityName,
                                                   @RequestParam("activityDescribe") String activityDescribe, @RequestParam("activityDateBegin") String activityDateBegin,
                                                   @RequestParam("activityDateEnd") String activityDateEnd) throws Exception{

        RequsetData<Integer> res = new RequsetData<>();

        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(activityId);
        activityInfo.setActivityName(activityName);
        activityInfo.setActivityDescribe(activityDescribe);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date date_begin = df.parse(activityDateBegin.replace("Z", " UTC"));
        Date date_end = df.parse(activityDateEnd.replace("Z", " UTC"));
        activityInfo.setActivityDateBegin(date_begin);
        activityInfo.setActivityDateEnd(date_end);
        Date now = new Date();
        if (now.compareTo(date_begin) >= 0){
            activityInfo.setActivityState(1);
        }else {
            activityInfo.setActivityState(0);
        }
        activityInfo.setGmtUpdate(new Date());

        int count = activityInfoService.updateByPrimaryKeySelective(activityInfo);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("修改成功");
            res.setSuccess(true);
        } else {
            res.setMsg("修改失败");
            res.setSuccess(false);
        }
        return res;
    }

}
