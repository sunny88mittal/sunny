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