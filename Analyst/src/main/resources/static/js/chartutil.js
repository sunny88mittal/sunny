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