package com.clothSale.controller;

import com.clothSale.controller.jsonmodel.RequsetData;
import com.clothSale.model.OrderCart;
import com.clothSale.model.OrderComment;
import com.clothSale.service.IOrderCartService;
import com.clothSale.service.IOrderCommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/OrderComment")
public class OrderCommentController {

    @Resource
    private IOrderCommentService orderCommentService;

    @RequestMapping("/selectCartBySpuId")
    @ResponseBody
    //有参数要加 @RequestParam("参数名")
    public RequsetData<List<HashMap<String, Object>>> selectCommentBySpuId(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,@RequestParam("spu_id") String spu_id) {

        RequsetData<List<HashMap<String, Object>>> res = new RequsetData<>();

        List<HashMap<String, Object>> list = orderCommentService.selectCommentBySpuId(pageIndex, pageSize, spu_id);
        HashMap<String,Object> total = orderCommentService.selectCommentNumBySpuId(spu_id);

        if (!list.isEmpty()) {
            res.setItem(list);
            res.setExtdata(total);
            res.setMsg("成功");
            res.setSuccess(true);
        } else {
            res.setMsg("失败");
            res.setSuccess(false);
        }

        return res;
    }

    @RequestMapping("/insertOrderComment")
    @ResponseBody
    public RequsetData<Integer> insertOrderComment(@RequestParam("comment_id") String comment_id,@RequestParam("user_id") String user_id,
                                                @RequestParam("sku_id") String sku_id,@RequestParam("order_info_id")String order_info_id,
                                                   @RequestParam("comment_content")String comment_content,@RequestParam("comment_score")int comment_score) {

        RequsetData<Integer> res = new RequsetData<>();

        OrderComment orderComment = new OrderComment();
        orderComment.setCommentId(comment_id);
        orderComment.setSkuId(sku_id);
        orderComment.setUserId(user_id);
        orderComment.setCommentContent(comment_content);
        orderComment.setCommentScore(comment_score);
        orderComment.setOrderInfoId(order_info_id);
        orderComment.setGmtCreate(new Date());

        int count = orderCommentService.insert(orderComment);

        if (count == 1) {
            res.setItem(count);
            res.setMsg("评论成功");
            res.setSuccess(true);
        } else {
            res.setMsg("评论失败");
            res.setSuccess(false);
        }

        return res;
    }

}
