$(SYMBOL_SELECTOR).change(function() {
	updateData();
});

/*$(document).ready(function() {
	var stickyNavTop = $(OPTIONS_CHAIN_TABLE_HEADER).offset().top;

	$(OPTIONS_CHAIN_TABLE_HEADER).width($(OPTIONS_CHAIN_TABLE_HEADER).width());

	var stickyNav = function() {
		var scrollTop = $(window).scrollTop();
		if (scrollTop > stickyNavTop) {
			$(OPTIONS_CHAIN_TABLE_HEADER).addClass('sticky');
		} else {
			$(OPTIONS_CHAIN_TABLE_HEADER).removeClass('sticky');
		}
	};

	$(window).scroll(function() {
		stickyNav();
	});
});*/
