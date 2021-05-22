var chartDatastr = decodeHTML(chartData);
var chartJsonArray=JSON.parse(chartDatastr);


var arrayLength =chartJsonArray.length;
var numericData=[];
var labelData=[];

for(var i=0; i<arrayLength;i++){
	
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}


new Chart(document.getElementById("myPieChart"), {
	type: 'pie',
	data: {
		labels: labelData,
		datasets: [{
			label: 'My First Dataset',
			backgroundColor: ["#107fc7","#8e5ea2","#3cba9f"],
			data: numericData

		}]

	},
	options:{
		title:{
			display : true,
			text:'Project Statuses'
		}
	}
}
);

//[{"value":1,"label":"COMPLETED"},{"value":2,"label":"INPROGRESS"},{"value":1,"label":"NOTSTARTED"}]
function decodeHTML(html){
	
	var txt = document.createElement("textarea");
	
	txt.innerHTML =html;
	return txt.value;
}