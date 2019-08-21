angular.module('mainApp', [])
    .controller('mainCtrl', function ($scope, $http) {

        $scope.tip = "";

        $scope.allPic = {};
        $scope.picNum = 0;
        $scope.selectparams = {

            'picName': '',
            'pageIndex': Math.ceil(Math.random()*1000+1),
            'pageSize': 30,

        }

        $scope.data = [
            {
                title: "Video",
                context: "I guess it comes down to a simple choice, really.Get busy living, or get busy dying.Everything that has a beginning, has an end.",
                pic: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565976792032&di=64e920587de82fba810af0fb581bc58e&imgtype=0&src=http%3A%2F%2Fimg02.tooopen.com%2Fimages%2F20150805%2Ftooopen_sy_136828758653.jpg",
                htmlUrl: "../movie/movie.html",
            },
            {
                title: "Music",
                context: "Forget that there are place in the world that are not made out of stone,there is something inside that they can not get to that is hope ",
                pic: "http://dpic.tiankong.com/s3/u5/QJ8324388096.jpg",
                htmlUrl: "../music/music.html",
            },
            {
                title: "Book",
                context: "I love waking up in the morning and not knowing what's going to happen,or who I'm going to meet,where I'm going to wind up.",
                pic: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565927545840&di=cafb63a4a2f28f24bdbab2f803169d70&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201707%2F28%2F20170728132922_fLvXQ.jpeg",
                htmlUrl: "../book/book.html",
            },
        ];

        $scope.selectPic = function () {

            $http({
                method:"POST",
                url:'../../pic/selectPicByName',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != ""){
                    $scope.allPic = response.data;
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

        //初始化数据
        $scope.selectPic();

    });