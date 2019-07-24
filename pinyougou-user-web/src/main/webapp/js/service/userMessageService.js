//服务层
app.service('userMessageService',function($http){

	//添加个人信息
	this.add=function (userMessage) {
		return $http.post('../message/add.do',userMessage);
    }

    //查找地址集合
	this.findAddressList=function () {
		return $http.post('../message/findAddressList.do')
    }

    //查询市级列表
    this.findCities=function (provinceId) {
		return $http.post('../message/findCities.do?provinceId='+provinceId)
    }


    this.findAreas=function (cityId) {
		return $http.post('../message/findAreas.do?cityId='+cityId)
    }

    this.findOneUser=function (loginName) {
        return $http.post('../message/findOneUser.do?loginName='+loginName)
    }
});
