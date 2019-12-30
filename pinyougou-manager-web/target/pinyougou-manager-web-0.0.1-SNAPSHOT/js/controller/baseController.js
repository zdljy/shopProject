app.controller('baseController', function($scope) {
	// 重新加载列表 数据
	$scope.reloadList = function() {
		// 切换页码
		$scope.search($scope.paginationConf.currentPage,
				$scope.paginationConf.itemsPerPage);
	}
	// 分页控件配置
	$scope.paginationConf = {
		currentPage : 1,
		totalItems : 10,// 总数
		itemsPerPage : 10,// 页面数
		perPageOptions : [ 10, 20, 30, 40, 50 ],// 分页选项
		// 页码变更后自动触发
		onChange : function() {
			$scope.reloadList();// 重新加载
		}
	}
	$scope.selectIds = [];// 选中的ID集合
	// 更新复选
	$scope.updateSelection = function($event, id) {
		if ($event.target.checked) {// 如果是被选中,则增加到数组
			$scope.selectIds.push(id);
		} else {
			var index = $scope.selectIds.indexOf(id);// 查找值的位置
			$scope.selectIds.splice(index, 1);// 删除,参数一移除的位置,参数二移除的个数
		}
	}
	// 提取json字符串数据中某个属性，返回拼接字符串 逗号分隔
	$scope.jsonToString = function(jsonString, key) {
		var json = JSON.parse(jsonString);// 将json字符串转换为json对象
		var value = "";
		for (var i = 0; i < json.length; i++) {
			if (i > 0) {
				value += ","
			}
			value += json[i][key];
		}
		return value;
	}

});