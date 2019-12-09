/*================================================
[  Table of contents  ]
==================================================
 15. ScrollUp Activation
================================================*/

(function ($) {

    "use Strict";

    //轮播图效果
    var c = 0;

    //加一个时间事件
    function timer() {
        //获得序号
        c++;
        if (c == 6) {
            c = 0;
        }
//				    alert(c);
        //让自己显示，兄弟元素隐藏
        $("#inBaner .big_eye_pic li").eq(c).stop().fadeIn(1800).siblings().fadeOut(800);
        //改变颜色
        $("#inBaner .btn ul li").eq(c).stop().addClass("cur").siblings().removeClass("cur");
    }

    time = setInterval(timer, 2000);
    //给图片添加一个移入移出效果
    $("#inBaner").hover(function () {
        //停止时间
        clearInterval(time);
    }, function () {
        //时间继续
        time = setInterval(timer, 2000);
    });
    //给色块添加一个移入移出事件
    $("#inBaner .btn ul li").mouseenter(function () {
        var n = $(this).index();
        //让第一个人图片显示 其余的隐藏
        $("#inBaner .big_eye_pic li").eq(n).stop().show().siblings().hide();
        //改变颜色
        $("#inBaner .btn ul li").eq(n).stop().addClass("cur").siblings().removeClass("cur");
    });
    //添加一个点击事件
    $(".her_top .her_title .her_menu li").click(function () {
        var n = $(this).index();
        //切换颜色
        $(".her_menu li").eq(n).addClass("current").siblings().removeClass("current");
        //切换商品
        $(".her_top .pro_con").eq(n).show().siblings(".pro_con").hide();
    })
    //搜索框下滑
    $(window).scroll(function () {
        //获得滚动条距离顶部距离
        var t = $(document).scrollTop();
        if (t > 530) {
            $("#nav").show();
        } else {
            $("#nav").hide();
        }
    });

    /*----------------------------
    ScrollUp Activation
    -----------------------------*/
    $.scrollUp({
        scrollName: 'scrollUp', // Element ID
        topDistance: '550', // Distance from top before showing element (px)
        topSpeed: 1000, // Speed back to top (ms)
        animation: 'fade', // Fade, slide, none
        scrollSpeed: 900,
        animationInSpeed: 1000, // Animation in speed (ms)
        animationOutSpeed: 1000, // Animation out speed (ms)
        scrollText: '<img src="images/arrow_up.png" style="width: 36px;height: 36px">', // Text for element
        activeOverlay: false // Set CSS color to display scrollUp active point, e.g '#00FFFF'
    });

})(jQuery);