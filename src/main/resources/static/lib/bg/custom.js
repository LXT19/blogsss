// templatemo 467 easy profile

// PRELOADER

$(window).load(function(){
    $('.preloader').delay(100).fadeOut("slow"); // set duration in brackets
});

// HOME BACKGROUND SLIDESHOW
$(function(){
    jQuery(document).ready(function() {
		$('body').backstretch([
	 		 "https://picsum.photos/id/1033/1920/1280",
	 		 "https://picsum.photos/id/250/1920/1280",
			 "https://picsum.photos/id/112/1920/1280",
			"https://picsum.photos/id/1067/1920/1280"
	 			], 	{duration: 3200, fade: 1300});
		});
})