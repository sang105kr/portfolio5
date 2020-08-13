<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>    
/* Video player */
.container-h {
 	position:relative;
}
.player {
  position: sticky;
  top: 0;
  text-align: center;
  background-color: black;
  
  width:100%;
  height:300px;
  overflow:hidden;
}

.player video {
    min-width: 100%;
    min-height: 100%;
}    

.overlay {
	position : absolute;
	bottom: 50px;
	z-index:1;
	text-align:center;
	width:100%;
}
.overlay h1{
	color:white;
	font-size:2em;
}
</style>
  <header>
    <div class="container container-h">
    	<section class="player">
    		<video autoplay loop contros muted>
    			<source src="${contextPath }/video/main.mp4" type="video/mp4">
    		</video>
    	</section>
	    <div class="overlay">
	    	<h1>방문을 환영합니다.</h1>
	    </div>
    </div>
  </header>
  
  
  
  