angular.module("studentApp", ['ui.bootstrap'])
    .controller("editstuCtrl", function ($scope,$http, $state,$stateParams,$rootScope) {

        $scope.selectParams = {

            datetime : '',
            stuId : $stateParams.id,
            stuInfo : {
                stuid : $rootScope.info.stuInfo.stuid,
                stuname: '',
                stupassword: '',
                sex: '',
                birthday: null,
                birthplace: '',
                origo: '',
                idnumber: '',
                nation: '',
                nationality: '',
                phonenumber: '',
                address: '',
                grade: '',
                classname: '',
                majorid: 0,
                power: '',
                picpath: '',
                majorname:'',
                depid:'',
                depname:''
            },
            depId:null,
            depList:[],
            majorList:null,
            maxNum : '',
        }

        $scope.loadData = function () {

            $scope.selectParams.stuInfo.stupassword = '********';

            if ($scope.selectParams.stuId != null && $scope.selectParams.stuId != ''){
                $http({
                    method: "POST",
                    url: 'student/selectStuById',
                    params: {
                        stuId : $scope.selectParams.stuId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.selectParams.stuInfo = response.data;
                    $scope.selectMajorByDep($scope.selectParams.stuInfo.depid);
                    $scope.selectParams.stuInfo.birthday = new Date($scope.selectParams.stuInfo.birthday);
                }, function errorCallback(response) {
                    //请求失败
                    $scope.tip = "请求失败，请重试";
                });
            }

            $http({
                method: "POST",
                url: 'department/selectAllDep',
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectParams.depList = response.data;
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

        }

        $scope.selectMajorByDep = function (depId){

            if (depId != null && depId != ''){
                $http({
                    method: "POST",
                    url: 'major/selectMajorByDepId',
                    params: {
                        depId : depId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.selectParams.majorList = response.data;
                }, function errorCallback(response) {
                    //请求失败
                    $scope.tip = "请求失败，请重试";
                });
            }
        }

        $scope.selectStuNum = function (majorid,classname,grade){

            $http({
                method: "POST",
                url: 'student/selectStuNumByMajorClass',
                params: {
                    majorid : majorid,
                    classname : classname,
                    grade : grade,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectParams.stuInfo.stuid = parseInt($scope.selectParams.stuInfo.grade)  //2016
                    + (Array(4).join(0) + $scope.selectParams.stuInfo.majorid).slice(-4) //0608
                    + (Array(2).join(0) + parseInt($scope.selectParams.stuInfo.classname)).slice(-2) //02
                    + (Array(2).join(0) + parseInt(response.data + 1)).slice(-2); //01
                $scope.selectParams.stuInfo.stupassword = $scope.selectParams.stuInfo.stuid;
                $scope.insertStu();
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });
        }

        $scope.insertStu = function (){

            $http({
                method: "POST",
                url: 'student/insertSelective',
                params: $scope.selectParams.stuInfo
            }).then(function successCallback(response) {
                //请求成功
                $state.go("stumanage");
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

        }

        $scope.save = function (){

            $scope.selectParams.stuInfo.birthday = new Date($scope.selectParams.stuInfo.birthday.valueOf() + 60 * 60 * 1000 *24);
            if ($scope.selectParams.stuId != null && $scope.selectParams.stuId != ''){
                // delete $scope.selectParams.stuInfo.majorname;
                $http({
                    method: "POST",
                    url: 'student/updateByStuId',
                    params: $scope.selectParams.stuInfo
                }).then(function successCallback(response) {
                    //请求成功
                    $state.go("stumanage");
                }, function errorCallback(response) {
                    //请求失败
                    $scope.tip = "请求失败，请重试";
                });
            }else{
                $scope.selectStuNum($scope.selectParams.stuInfo.majorid,$scope.selectParams.stuInfo.classname,$scope.selectParams.stuInfo.grade);
            }

        }

        $scope.cancel = function (){
            $state.go("stumanage");
        }


        $scope.loadData();

    });