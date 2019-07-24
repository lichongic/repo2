 //控制层 
app.controller('settingController' ,function($scope,$controller ,settingService) {


    $scope.tbUser = {};
    $scope.tbUser.phone = "15212545788";
    $scope.username = "taiba";

    $scope.findUser = function () {
        settingService.findUser($scope.tbUser.phone).success(
            function (response) {
                $scope.tbUser = response;
            }
        )
    }


    $scope.sendMessage = function () {
        settingService.sendMessage($scope.tbUser.phone).success(
            function (response) {
                if (!response.success) {
                    alert(response.message);
                }

            }
        );

    }


    $scope.checkSmsCode = function () {
        settingService.checkSmsCode($scope.tbUser.phone, $scope.code).success(
            function (response) {
                if (response.success) {
                    //页面跳转
                    alert(response.message);
                    // $state.go('home-setting-address-complete.html');
                    location.href = "home-setting-address-phone.html";
                } else {
                    alert(response.message);
                }
            }
        );

    }


    $scope.setPassWord = function () {
        if ($scope.newPassWord == $scope.copyNewPassWord) {
            settingService.setPassWord($scope.username, $scope.newPassWord).success(
                function (response) {
                    if (response.message) {
                          location.href="home-setting-address-complete.html" ;

                    } else {
                        alert(response.message)
                    }
                }
            )

        } else {
            alert("两次密码不一致！")
        }

    }


   /* $scope.showName = function () {
     loginService.showName().success(
         function (response) {
             $scope.loginName = response.loginName;
            }
        );
    }
*/



 }