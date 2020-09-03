<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<style>
	img { 
		max-height:250px;
	}
 	.carousel-item {
		transition: transform 5s ease, opacity 2s ease-in-out;
	} 
</style>
<script>
	$(function(){ /*현재문서가 로딩되면 = 현재문서가 dom구축되면*/
		$('.carousel').carousel({
			  interval: 10000,
			});	

/* 		$("#btn1").on("click",function(){
			console.log('btn1');
			$(".carousel").carousel('cycle');
		});
		$("#btn2").on("click",function(){
			console.log('btn2');
			$(".carousel").carousel('pause');
		});
		$("#btn3").on("click",function(){
			console.log('btn3');
			$(".carousel").carousel('prev');
		});
		$("#btn4").on("click",function(){
			console.log('btn4');
			$(".carousel").carousel('next');
		}); */
	});
</script>  	
<header>
  <div class="container container-h">
		<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
		  <ol class="carousel-indicators">
		    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
		    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
		    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
		  </ol>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="https://cdn.pixabay.com/photo/2015/10/30/20/13/sunrise-1014712__340.jpg" class="d-block w-100" alt="...">
		      <div class="carousel-caption d-none d-md-block">
		        <h5>First slide label</h5>
		        <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
		      </div>
		    </div>
		    <div class="carousel-item">
		      <img src="https://cdn.pixabay.com/photo/2014/07/31/22/50/photographer-407068__340.jpg" class="d-block w-100" alt="...">
		      <div class="carousel-caption d-none d-md-block">
		        <h5>Second slide label</h5>
		        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
		      </div>
		    </div>
		    <div class="carousel-item">
		      <img src="https://cdn.pixabay.com/photo/2017/10/27/20/25/leopard-2895448__340.jpg" class="d-block w-100" alt="...">
		      <div class="carousel-caption d-none d-md-block">
		        <h5>Third slide label</h5>
		        <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
		      </div>
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div> 
<!-- 		<div>
			<button id="btn1">순환</button>
			<button id="btn2">정지</button>
			<button id="btn3">이전</button>
			<button id="btn4">다음</button>
		</div>  -->
  </div>
</header>
 
  
  
  