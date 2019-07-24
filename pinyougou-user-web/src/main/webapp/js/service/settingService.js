//服务层
app.service('settingService',function($http){

	this.sendMessage=function (phone) {
		return $http.get('../setting/sendMessage.do?phone='+phone);
    }


    this.checkSmsCode=function (phone,smscode) {
        return $http.get('../setting/checkSmsCode.do?phone='+phone+'&smscode='+smscode);
    }

    this.setPassWord=function (username,password) {
	    return $http.post('../setting/setPassWord.do?username='+username+'&password='+password);

    }
});
