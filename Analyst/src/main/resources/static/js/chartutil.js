var getChart = function(ctx, chartType, datatsetsArray, labelsArray) {
	var chart = new Chart(ctx, {
		type : chartType,
		data : {
			datasets : datatsetsArray,
			labels : labelsArray
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});

	return chart;
}

var getMultiAxisChart = function(ctx, datatsetsArray, labelsArray) {
	var yaxis = getYAxis();

	var chart = new Chart.Line(ctx, {
		data : {
			datasets : datatsetsArray,
			labels : labelsArray
		},
		options : {
			scales : {
				yAxes : yaxis
			}
		}
	});

	return chart;
}

var getYAxis = function() {
	var yaxis = [];
	
	var yxais1 = {};
	yxais1.type = 'linear';
	yxais1.display = true;
	yxais1.position = 'left';
	yxais1.id = YAXIS1;
	
	var yxais2 = {};
	yxais2.type = 'linear';
	yxais2.display = true;
	yxais2.position = 'right';
	yxais2.id = YAXIS2;
	
	yaxis.push(yxais1);
	yaxis.push(yxais2);
	
	return yaxis;
}


var getMultiAxisDataset = function(labelValue, dataArr, chartType, yaxisId, bgColor, borderColor) {
	var dataset = {};
	dataset.label = labelValue;
	dataset.data = dataArr;
	dataset.type = chartType;
	dataset.yAxisID = yaxisId;

	if (bgColor) {
		dataset.backgroundColor = bgColor;
	}

	if (borderColor) {
		dataset.borderColor = borderColor;
	}
	
	return dataset;
}

var getDataset = function(labelValue, dataArr, chartType, bgColor, borderColor) {
	var dataset = {};
	dataset.label = labelValue;
	dataset.data = dataArr;
	dataset.type = chartType;

	if (bgColor) {
		dataset.backgroundColor = bgColor;
	}

	if (borderColor) {
		dataset.borderColor = borderColor;
	}
	
	return dataset;
}