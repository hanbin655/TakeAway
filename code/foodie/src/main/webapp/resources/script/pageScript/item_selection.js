function renderPage() {
    console.log("Start calling api");   
    viewModel = new localViewModel();
}

function localViewModel(){	
	var self = this;
	
	self.restaurantInfo = {
		"restaurantId":1,
		"name":"比格比萨（海淀桥店）",
		"description":"比格比萨创立于2002年，是经营自助比萨为主，兼营零点及外卖的休闲餐厅，历经十多年的发展，比格自助比萨这个连锁品牌，得到了众多消费群体的喜爱。目前比格拥有150多家连锁餐厅，遍布全国20多个省50多个城市。 休闲Leisure 百余款丰富多样的美食，口味地道的纯手工比萨，营养健康的果蔬沙拉，精选西式热菜和甜品……还有餐饮流行风尚的主推新品，随意盛取，不限量。休闲浪漫的环境、温暖周到的服务让您轻松惬意无拘束。 时尚Fashion用餐环境简约时尚，全开放舞台式厨房，独有的明厨设计，使您边享受各式美味，边欣赏制作比萨的过程；独创的比格“比萨吧”，既能让厨师量身定做您想吃的比萨，又能受厨师专业指导，自己动手DIY比萨。 健康Health 比格追求高品质，与国际顶级原料供应商构建了高品质产品体系，奶制品的供应均来自纯净新西兰，调料从全球最好的产地采集，松软的饼底选自优质的小麦绝对意大利品质，肉类食品与五大知名品牌合作，100%保证肉质来源，新鲜果蔬当日配送。严格的选料加上标准的制作确保了比格科学健康、营养均衡的产品出品，让您放心、安心、舒心。特色Vitality 做中国人喜爱的特色比萨 Do the Chinese love pizza specialties 专业研发人员的经验积累和持续创新形成了比格既符合西餐流行趋势，又适合中国人口味的产品特色。在坚持制作高品质比萨的同时，还不断的探索研究新的比萨品种，继传统的厚饼比萨、薄饼比萨后，比格又独创了双层比萨，深受顾客的喜爱。比格致力于做中国人喜爱的特色比萨！ 比格官网地址：www.bigpizza.bj.cn",
		"businessHour": "11:30-21:00",
		"deliveryDistance":1200,
		"currentMenuId":1,
		"menues":null,
		"location":{
			"locationId":1,
			"province":"北京",
			"city":"北京",
			"zipcode":"100001",
			"address1":"东城区",
			"address2":"王府井大街东华金街购物中心五层（王府井书店正对面）"
		},
		"telNum":"010-12321312",
		"email": "littlestone@foodie.com",
		"score":"4.3"
	}
	self.restaurantLocation = ko.computed(function(){
		return self.restaurantInfo.location.city + self.restaurantInfo.location.address1 + self.restaurantInfo.location.address2;
	});

	var mealData=[
		{ "itemName":"咸鱼酱焖茄瓜", "itemPrice": "32.80"}, 
		{ "itemName":"段氏烧茄子", "itemPrice": "32.80"},
		{ "itemName":"招牌虾饺皇", "itemPrice": "23.80"},
		{ "itemName":"豉汁蒸凤爪", "itemPrice": "16.80"},
		{ "itemName":"鲜虾烧麦", "itemPrice": "18.80"},
		{ "itemName":"千层榴莲酥", "itemPrice": "16.80"},
		{ "itemName":"芦蒿炒香干", "itemPrice": "35.60"},
		{ "itemName":"黄汤菜尖", "itemPrice": "32.80"},
		{ "itemName":"蟹粉狮子头", "itemPrice": "25.80"},
		{ "itemName":"百叶结红烧肉", "itemPrice": "49.80"},
		{ "itemName":"菠萝咕噜肉", "itemPrice": "38.80"},
		{ "itemName":"米饭", "itemPrice": "5.00"},
		{ "itemName":"蜜汁叉烧", "itemPrice": "38.80"},
		{ "itemName":"酱萝卜条", "itemPrice": "20.80"}
	];
	self.menuItems = ko.observableArray([]);
	self.menuItems(mealData);

	self.commentData = ko.observableArray(		
		[
			{ 
				"comment_made_time":"2013-01-01 13:00",
				"comment_maker_name":"呆呆的stone",
				"comment_title":"真心不好吃",
				"comment_detail":"做的跟老大做的似的，以后再也不来吃饭了。说话有点怀念在爱尔兰的生活了",
				"flavor_score":"1",
				"quantity_score":"1",
				"timing_score":"1"
			}, 
			{
				"comment_made_time":"2013-08-01 13:00",
				"comment_maker_name":"呆呆的stone",
				"comment_title":"真好吃",
				"comment_detail":"太好吃了，以后一定常来",
				"flavor_score":"4",
				"quantity_score":"4",
				"timing_score":"5"
			}
		]

	);
	computeAverageScore(self.commentData());
	/*
		
	//self.getList = function () {
		api.getMenuItem( function(response){
			console.log("Api retuns data"); 
			if (response.success) {
				console.log("Api retuns data with success");        
				self.menuItems(response.data);
			} else {
				handleUnsuccess(response);
			}
		}, handleError);                  
   // };
   */
}

//This function is used to handle unsuccess return from api, usual show an error message and redirect to other page
function handleUnsuccess(result) {
	console.log(result);
    console.log("api returns data with unsuccess");
}

//This function is use to handle error when calling api.
function handleError(response) {
    console.log("can't get api data");
}


function updateNameList(response,model) {
	if(response.success){
		//alert(response.data);
		model(response.data);
	}
};

function computeAverageScore(tmpData) {
	var maxRate = 5;
	for (c in tmpData) {
		var tmp = tmpData[c];
		tmp.flavor_score = parseDataIntoNumber(tmp.flavor_score,maxRate);
		tmp.quantity_score = parseDataIntoNumber(tmp.quantity_score,maxRate);
		tmp.timing_score = parseDataIntoNumber(tmp.timing_score,maxRate);

		tmp_data = (tmp.flavor_score + tmp.quantity_score + tmp.timing_score) / 3;
		tmp.average_score = tmp_data.toFixed(1); 
		//console.log(tmp.average_score)
	}
}
