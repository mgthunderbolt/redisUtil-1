function queryKey () {
	var db = $("#db").val();
	var key = $("#key4query").val()+"*";

	$.ajax({
	  type: 'GET',
	  url: URL+url_queryKeys,
	  data: {
	  	db:db,
	  	key:key
	  },
	  success: function(result){
	  	queryKeysuccess(result);
	  },
	  error:function(result){
	  	ajaxerror(result);
	  }
	});
}


function queryKeysuccess (result) {
	$("#keyList").html("");
	result = result.replace("[","");
	result = result.replace("]","");
	
	var resultResult = result.split(",");
	for (var i = 0 ; i < resultResult.length; i++) {
		var item = resultResult[i].replace("\"","");
		item = item.replace("\"","");
		$("#keyList").append("<div class=\"am-panel am-panel-default\">"+
			"<div class=\"am-panel-hd\">"+
			""+
			"<h4 id=\"parent-do-not-say-"+i+"\" class=\"am-panel-title\" data-am-collapse=\"{parent: '#accordion', target: '#do-not-say-"+i+"'}\">"+
			item+
			"</h4>"+
			"</div>"+
			"<div id=\"do-not-say-"+i+"\" class=\"am-panel-collapse am-collapse\">"+
			"<div class=\"am-panel-bd\">"+
			"加载中"+
			"</div></div></div>");
	};
}


$('#keyList').on('open.collapse.amui', function(e) {
	var id = "parent-"+e.target.id;
	var key = $("#"+id).html();
	queryValue(key,e.target.id);
  	console.log('折叠菜单打开了！');
}).on('close.collapse.amui', function(e) {
  	console.log('折叠菜单关闭鸟！');
});


function queryValue (key,id) {
	var db = $("#db").val();
	$.ajax({
	  type: 'GET',
	  url: URL+url_queryValue,
	  data: {
	  	db:db,
	  	key:key
	  },
	  success: function(result){
	  	queryValuesuccess(result,id);
	  },
	  error:function(result){
	  	ajaxerror(result);
	  }
	});
}

function queryValuesuccess (result,id) {
	$("#"+id).html("<div class=\"am-panel-bd\">"+result+"</div");
}