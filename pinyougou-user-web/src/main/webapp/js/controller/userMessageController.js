 //控制层 
app.controller('userMessageController' ,function($scope,$controller,$filter ,loginService,uploadService  ,userMessageService){


    /*"username":'',"nickName":'',"sex":'',"birthday":'',"provinceId":'',"cityId":'',"townId":'',"notes":''*/

    $scope.userMessage={ }

    $scope.userMessage.tbAddress={"headPic":'',"provinceId":'',"cityId":'',"townId":'',"notes":''};
    $scope.userMessage.tbUser={"username":'',"nickName":'',"sex":'',"birthday":''};

    $scope.notesList=[
        {id:"0",name:"程序员"},
        {id:"1",name:"产品经理"},
        {id:"2",name:"UI设计师"}
    ]



    $scope.$watch("day",function(newValue,oldValue){
        var year = $scope.year ;
        var month = $scope.month  ;
        var day = $scope.day   ;
       var date = new Date(year,month ,day );
       $scope.userMessage.tbUser.birthday=date;
    });

    $scope.yearList = [
        { id:0 ,name:"1995"},
        { id:1 ,name:"1996"},
        { id:2 ,name:"1997"},
        { id:3 ,name:"1998"},
        { id:4 ,name:"1999"},
        { id:5 ,name:"2001"},
        { id:6 ,name:"2002"},
        { id:7 ,name:"2003"},
        { id:8 ,name:"2004"},
        { id:9 ,name:"2005"},
        { id:10 ,name:"2006"},
        { id:11 ,name:"2007"},
        { id:12 ,name:"2008"},
        { id:13 ,name:"2009"},
        { id:14 ,name:"2010"},
        { id:15 ,name:"2011"},
        { id:16 ,name:"2012"},
        { id:17 ,name:"2013"},
        { id:18 ,name:"2014"}
    ];

    $scope.monthList=[
        {id:0,name:"01"},
        {id:1,name:"02"},
        {id:2,name:"03"},
        {id:3,name:"04"},
        {id:4,name:"05"},
        {id:5,name:"06"},
        {id:6,name:"07"},
        {id:7,name:"08"},
        {id:8,name:"09"},
        {id:9,name:"10"},
        {id:10,name:"11"},
        {id:11,name:"12"}
    ]
    $scope.dayList=[
        {id:0,name:"01"},
        {id:1,name:"02"},
        {id:2,name:"03"},
        {id:3,name:"04"},
        {id:4,name:"05"},
        {id:5,name:"06"},
        {id:6,name:"07"},
        {id:7,name:"08"},
        {id:8,name:"09"},
        {id:9,name:"10"},
        {id:10,name:"11"},
        {id:10,name:"12"},
        {id:10,name:"13"},
        {id:10,name:"14"},
        {id:10,name:"15"},
        {id:10,name:"16"},
        {id:10,name:"17"},
        {id:10,name:"18"},
        {id:10,name:"19"},
        {id:10,name:"20"},
        {id:10,name:"21"},
        {id:10,name:"22"},
        {id:10,name:"23"},
        {id:10,name:"24"},
        {id:10,name:"25"},
        {id:10,name:"26"},
        {id:10,name:"27"},
        {id:11,name:"28"},
        {id:11,name:"29"},
        {id:11,name:"30"},
        {id:11,name:"31"}
    ]
	//添加个人信息
	$scope.add=function () {
        userMessageService.add($scope.userMessage).success(
			function (response) {

				if(response.success){
				    alert(response.message);
                }else{
                    alert(response.message);
                }
            }
		)
    }



    //一级下拉框
    $scope.findAddressList = function(){
        userMessageService.findAddressList().success(
        	function(response){
        	   // $scope.userMessage.tbAddress.provinceId=response[0].provinceid;
            $scope.addressCat1List = response;

        });
    }

    // 查询二级分类列表:
    $scope.$watch("userMessage.tbAddress.provinceId",function(newValue,oldValue){
        userMessageService.findCities(newValue).success(function(response){
            //$scope.userMessage.tbAddress.cityId=response[0].cityId;
            $scope.addressCat2List = response;
        });
    });

	// 查询三级分类列表:
    $scope.$watch("userMessage.tbAddress.cityId",function(newValue,oldValue){
        userMessageService.findAreas(newValue).success(function(response){
            //$scope.userMessage.tbAddress.tbAddress=response[0].tbAddress;
            $scope.addressCat3List = response;
        });
    });


    $scope.showName=function(){
        loginService.showName().success(
            function(response){
                $scope.userMessage.tbUser.username=response.loginName;
                findOneUser(response.loginName);
            }
        );
    }

   findOneUser =function( ){
        userMessageService.findOneUser($scope.userMessage.tbUser.username).success(
            function (response) {
                $scope.userMessage.tbAddress.provinceId=response.tbAddress.provinceId;
                $scope.userMessage.tbAddress.cityId=response.tbAddress.cityId;
                $scope.userMessage.tbAddress.townId=response.tbAddress.townId;

                var  year =JSON.parse(JSON.stringify( response.tbUser.birthday));
                var month =JSON.parse(JSON.stringify( response.tbUser.birthday));
                var day = JSON.parse(JSON.stringify( response.tbUser.birthday));

                $scope.userMessage=response;
                $scope.year = $filter('limitTo')(year, 4,1);
                var month1 = $filter('limitTo')(month, -14);
                var day1 = $filter('limitTo')(day, -11);

                $scope.month = $filter('limitTo')(month1,2);
                $scope.day = $filter('limitTo')(day1,2);
            }
        )
    }



    $scope.uploadFile = function(){
        // 调用uploadService的方法完成文件的上传
        uploadService.uploadFile().success(function(response){
            if(response.flag){
                // 获得url
                $scope.userMessage.tbUser.headPic =  response.message;
            }else{
                alert(response.message);
            }
        });
    }

});	
