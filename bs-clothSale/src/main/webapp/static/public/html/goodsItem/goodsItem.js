angular.module("clothSalePublicApp")
    .controller("goodsItemCtrl", function ($scope, $rootScope, $state, $stateParams, $http,$anchorScroll,$location) {

        $anchorScroll.yOffset = 30;   // 总是滚动额外的30像素（此处是因为项目中样式设置原因，需要加上以offset）
        $location.hash();
        $anchorScroll();

        $scope.spu_id = $stateParams.spu_id == undefined ? '' : $stateParams.spu_id;
        $scope.spuInfo = {};
        $scope.total_num = 1;
        $scope.payState = 0;
        $scope.isChangeAddress = 0;
        $scope.memberAddressList = [];
        $scope.payGoodsParams = {
            order_info_id : '',
            pay_way : 1,
            order_state : 2
        }
        $scope.commentsParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            spu_id : $scope.spu_id
        }
        $scope.order_info_id = '';
        $scope.user_info = $scope.getUserInfoBySession();
        $scope.specList = {
            spec_id: [],
            spec_name: [],
            spec_value: []
        };
        $scope.selectSpecValue = [];
        $scope.goodsSku = '';

        $scope.changeAddress = function (state){
            if (state == 1){
                $scope.isChangeAddress = 1;
                $scope.payState = -1;
            }else{
                $scope.isChangeAddress = 0;
                $scope.payState = 0;
            }
        }

        $scope.changeAddressId = function(address_id){

            $http({
                method: "POST",
                url: '../../memberUserinfo/updateMemberUserinfo',
                params : {
                    user_id : $scope.user_info.user_id,
                    address_id : address_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.updateLocalUserInfo();
                $scope.showAlert('成功:',response.data.msg,'success');
            }, function errorCallback(response) {
                //请求失败
            });

        }

        $scope.updateLocalUserInfo = function (){
            $http({
                method: "POST",
                url: '../../memberUserinfo/selectUserinfoByUserId',
                params : {
                    user_id : $scope.user_info.user_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                sessionStorage.setItem("user_info", JSON.stringify(response.data.item)); //修改session默认地址信息
                $scope.user_info = $scope.getUserInfoBySession();
                $scope.selectAllAddress();
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectAllAddress = function (){
            $http({
                method: "POST",
                url: '../../memberAddress/selectAllMemberAddress',
                params : {
                    pageIndex : 0,
                    pageSize : 1000,
                    user_id : $scope.getUserInfoBySession().user_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.memberAddressList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //获取评论
        $scope.selectComments = function (){
            $http({
                method: "POST",
                url: '../../OrderComment/selectCartBySpuId',
                params : $scope.commentsParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.commentsList = response.data.item;
                angular.forEach($scope.commentsList,function(comment,index,objs){
                    var num = comment.comment_score;
                    var arrays = {'arrayNum':[]};
                    for (var i=0;i<num;i++){
                        arrays.arrayNum.push('0');
                    }
                    $scope.commentsList[index] = angular.merge($scope.commentsList[index],arrays);
                });
                $scope.commentsParams.totalNum = response.data.extdata==null?0:response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.changeSelectSpecValue = function (index, selectSpecValue) {
            if ($scope.selectSpecValue[index] == selectSpecValue){
                $scope.selectSpecValue[index] = null;
            }else {
                $scope.selectSpecValue[index] = selectSpecValue;
            }
            $scope.selectSkuBySpecSpu();
        }

        $scope.buyGoods = function (){
            if (sessionStorage.getItem("token_user")){
                if ($scope.goodsSku != null && $scope.goodsSku != ''){
                    if ($scope.goodsSku[0].stock < $scope.total_num){
                        $scope.showAlert('错误','库存不足，请重新选择数量','danger');
                    }else {
                        $('#addModal').modal('show');
                    }
                }else {
                    $scope.showAlert('提示','暂无该规格商品','danger');
                }
            }else {
                $state.go('public.login',{url:'public.goodsItem',extraData:'{"spu_id":"'+$stateParams.spu_id + '"}'});
            }
        }

        $scope.insertOrderInfo = function (){
            if ($scope.user_info.address == null || $scope.user_info.address == ''){
                $scope.showAlert('错误 : ','收货地址为空，请前往个人中心的地址管理','danger');
            }else {
                $scope.order_info_id = $scope.getUUID();
                $http({
                    method: "POST",
                    url: '../../OrderInfo/insertOrderInfo',
                    params: {
                        order_info_id : $scope.order_info_id,
                        user_id : $scope.getUserInfoBySession().user_id,
                        sku_id : $scope.goodsSku[0].sku_id,
                        total_num : $scope.total_num,
                        total_price : $scope.total_num * $scope.goodsSku[0].price_sale,
                        stock : $scope.goodsSku[0].stock,
                        order_address : $scope.user_info.address,
                        phone_number : $scope.user_info.liaison_phone,
                        liaison_person : $scope.user_info.liaison_person
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示',response.data.msg,'success');
                    $scope.payGoodsParams.order_info_id = $scope.order_info_id;
                    $scope.payState = 1;
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误',response.data.msg,'danger');
                });
            }
        }

        //付款
        $scope.payGoods = function (){
            $http({
                method: "POST",
                url: '../../OrderInfo/updateOrderInfo',
                params: $scope.payGoodsParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示','付款成功，请等待发货','success');
                $('#addModal').modal('hide');
                $scope.payState = 0;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误',response.data.msg,'danger');
            });
        }

        $scope.addCart = function (){

            if (sessionStorage.getItem("token_user")){
                if ($scope.goodsSku != null && $scope.goodsSku != ''){
                    if ($scope.goodsSku[0].stock < $scope.total_num){
                        $scope.showAlert('错误','库存不足，请重新选择数量','danger');
                    }else {
                        $http({
                            method: "POST",
                            url: '../../OrderCart/insertOrderCart',
                            params: {
                                cart_id : $scope.getUUID(),
                                user_id : JSON.parse(sessionStorage.getItem("user_info"))[0].user_id,
                                sku_id : $scope.goodsSku[0].sku_id,
                                total_num : $scope.total_num
                            }
                        }).then(function successCallback(response) {
                            //请求成功
                            $scope.showAlert('提示',response.data.msg,'success');
                        }, function errorCallback(response) {
                            //请求失败
                            $scope.showAlert('错误',response.data.msg,'danger');
                        });
                    }
                }else {
                    $scope.showAlert('提示','暂无该规格商品','danger');
                }
            }else {
                $state.go('public.login',{url:'public.goodsItem',extraData:'{"spu_id":"'+$stateParams.spu_id + '"}'});
            }

        }

        $scope.selectSingleSpu = function () {

            $http({
                method: "POST",
                url: '../../GoodsSpu/selectSingleGoodsSpu',
                params: {
                    spu_id: $scope.spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                angular.forEach(response.data.item, function (value, key) {
                    $scope.specList.spec_id.push(value.spec_id);
                    $scope.specList.spec_name.push(value.spec_name);
                    $scope.temp = [];
                    $scope.selectSpecValue.push(null);
                    angular.forEach(value.spec_value.split(","), function (value1, key) {
                        $scope.temp.push(value1.split(":"));
                    })
                    $scope.specList.spec_value.push($scope.temp);
                })
                $scope.spuInfo = response.data.extdata;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectSkuBySpecSpu = function () {
            $http({
                method: "POST",
                url: '../../GoodsSku/selectSkuBySpecSpu',
                params: {
                    specIds: $scope.selectSpecValue,
                    spu_id: $scope.spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.goodsSku = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            })
        }

        $scope.insertOrderCollect = function () {
            if (sessionStorage.getItem("token_user")){
                $http({
                    method: "POST",
                    url: '../../OrderCart/selectOrderCollectBySpuId',
                    params: {
                        user_id : $scope.getUserInfoBySession().user_id,
                        spu_id: $scope.spu_id
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    if (!response.data.success){ //不包括该服饰
                        $http({
                            method: "POST",
                            url: '../../OrderCart/insertOrderCollect',
                            params: {
                                collect_id: $scope.getUUID(),
                                user_id : $scope.getUserInfoBySession().user_id,
                                spu_id: $scope.spu_id
                            }
                        }).then(function successCallback(response) {
                            //请求成功
                            $scope.showAlert('成功:',response.data.msg,'success');
                        }, function errorCallback(response) {
                            //请求失败
                        })
                    }else{
                        $scope.showAlert('失败:','已存在相同服饰','danger');
                    }
                }, function errorCallback(response) {
                    //请求失败
                })
            }else {
                $state.go('public.login',{url:'public.goodsItem',extraData:'{"spu_id":"'+$stateParams.spu_id + '"}'});
            }
        }

        $scope.changeTotalNum = function (flag){
            if (flag == 1){
                $scope.total_num++;
            }else {
                if ($scope.total_num > 1){
                    $scope.total_num--;
                }
            }
        }

        $scope.loadData = function () {
            $scope.selectSingleSpu();
            $scope.selectAllAddress();
            $scope.selectComments();
        };

        $scope.loadCommentData = function (){
            $scope.commentsParams.pageIndex = ($scope.commentsParams.pageNum - 1) * $scope.commentsParams.pageSize;
            $scope.selectComments();
        }

        $scope.loadData();

        //添加一个点击事件
        $(".color_ul li").click(function () {
            //切换颜色
            $(this).addClass("on").siblings().removeClass("on");
        });

    });