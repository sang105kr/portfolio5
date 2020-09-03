<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
 <style>
 
 	ul, h2, p{
 	 margin:0; 
 	 padding:0;
	}  
 	 
	main .container .content h2{
		font-size: 1.2rem;
	} 
	main .container .content .wrapper-fluit {
	  display: flex;
	  flex-direction: column;
	  justify-content: center;
	  align-items: center;
	  height:30vh;
	  
	  background-color: #ccc;
	  color:#999;	  
	}
		
	main .container .content .fluit {
	  /* background-color:aqua; */
	  display: flex;
	  justify-content: center;
	  align-items: center;
	}
	
	main .container .content .fluit-item{
	  flex-basis:120px;
	  flex-shrink:0;
	  margin:0 10px;
	  transition: all .4s;
	}
	
	main .container .content .fluit-item:hover .profile-contents p{
	  max-height:6em;
	  opacity: 1;
	  margin-top:0.2em;
	}
	
	main .container .content .fluit-item img{
	  width:100%;
	}
	
	main .container .content .fluit-item:hover{
	  flex-basis:180px;
	  margin: 0 2em;
	}
	
	main .container .content .fluit-item:hover .profile:before{
		background:transparent;
	}	
	
	main .container .content .profile {
	  background-color: #222;
	  position: relative;
	  overflow: hidden;
	}
		
	main .container .content .profile:before{
	  content:" ";
	  /* background-color:red; */
	/* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#000000+0,000000+100&0.65+0,0.95+100;Neutral+Density */
	background: -moz-linear-gradient(top,  rgba(0,0,0,0.25) 0%, rgba(0,0,0,0.75) 100%); /* FF3.6-15 */
	background: -webkit-linear-gradient(top,  rgba(0,0,0,0.25) 0%,rgba(0,0,0,0.75) 100%); /* Chrome10-25,Safari5.1-6 */
	background: linear-gradient(to bottom,  rgba(0,0,0,0.25) 0%,rgba(0,0,0,0.75) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#a6000000', endColorstr='#f2000000',GradientType=0 ); /* IE6-9 */
	
	  position: absolute;
	  left:0;
	  right:0;
	  bottom:0;
	  height:250px;
	  
	}
			
	main .container .content .profile_red{
	  color:darkred;
	}
	
	main .container .content .profile_beige {
	  color:darkseagreen;
	}
	
	main .container .content .profile_pink {
	  color:pink;
	}
	
	main .container .content .profile_purple {
	  color:violet;
	}
	main .container .content .profile-contents {
	  /* background-color:aqua; */
	  position:absolute;
	  left:0;
	  bottom:0;
	  padding:10px;
	  /* color:white; */
	}
	main .container .content .profile-contents h2{
	  font-size:20px;
	}
	main .container .content .profile-contents h2 span {
	  display:block;
	}
	
	main .container .content .profile-contents p{
	  /* display:none; */
	  color:white;
	  font-size: 10px;
	  min-width:140px;
	  max-height:0;
	  overflow: hidden;
	  line-height:1.2;
	  transition: all 0.4s;
	  opacity:0;
	  transition-delay: 0.4s;
	}
 	main .container .content .wrapper-card {
 		display:grid;
 		grid-template-columns: repeat(3,1fr);
 		grid-auto-rows: minmax(200px,auto);
  	grid-gap: 10px;
 	}
 	main .container .content section article {
 		/*outline: 1px dotted red;*/
 		display:flex;
 		justify-content: center;
 	}
 	main .container .content section article:nth-child(1){

 	}
 	main .container .content section article:nth-child(2){

 	}
 	main .container .content section article:nth-child(3){
		
 	}
 	main .container .content section article:nth-child(4){

 	}
 	main .container .content section article:nth-child(5){
 	}
 	main .container .content section article:nth-child(6){
 		
 	}
 </style>
  <main>
    <div class="container">
      <div class="content">
		  	<h2>강사진 소개 >></h2>
		  <section class="wrapper-fluit">
		    <ul class="fluit">
		      <li class="fluit-item">
		        <div class="profile profile_green">
		            	<img src="https://images.unsplash.com/photo-1544717305-2782549b5136?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60" alt="">
		            	<div class="profile-contents">
		            	  <h2>웹표준 <span>Amelia</span></h2>
		            	  <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia excepturi iste, perspiciatis earum, fugit corporis ratione odio, libero numquam at aliquid velit modi facere rerum et expedita alias culpa ullam?</p>
		            	</div>
		        </div>
		      </li>
		      <li class="fluit-item">
		        <div class="profile profile_pink">
		          <img src="https://images.unsplash.com/photo-1548449112-96a38a643324?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60" alt="">
		              <div class="profile-contents">
		                <h2>자바 <span>Antonio</span></h2>
		                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia excepturi iste, perspiciatis earum, fugit corporis ratione odio, libero numquam at aliquid velit modi facere rerum et expedita alias culpa ullam?</p>
		            </div>
		
		        </div>
		      </li>
		      <li class="fluit-item">
		        <div class="profile profile_red">
		          <img src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60" alt="">
		              <div class="profile-contents">
		                <h2>안드로이드 <span>Adela</span></h2>
		                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia excepturi iste, perspiciatis earum, fugit corporis ratione odio, libero numquam at aliquid velit modi facere rerum et expedita alias culpa ullam?</p>
		            </div>
		
		        </div>
		      </li>
		      <li class="fluit-item">
		        <div class="profile profile_beige">
		          <img src="https://images.unsplash.com/photo-1573496800808-56566a492b63?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60" alt="">
		              <div class="profile-contents">
		                <h2>데이타베이스 <span>Bibiane</span></h2>
		                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia excepturi iste, perspiciatis earum, fugit corporis ratione odio, libero numquam at aliquid velit modi facere rerum et expedita alias culpa ullam?</p>
		            </div>
		
		        </div>
		      </li>
		      <li class="fluit-item">
		        <div class="profile profile_purple">
		          <img src="https://images.unsplash.com/photo-1583440532242-d83a45ca2b4c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60" alt="">
		              <div class="profile-contents">
		                <h2>스프링 <span>Austin</span></h2>
		                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia excepturi iste, perspiciatis earum, fugit corporis ratione odio, libero numquam at aliquid velit modi facere rerum et expedita alias culpa ullam?</p>
		            </div>
		        </div>
		      </li>
		    </ul>
		  </section>    
		  	<h2>강좌 소개 >> </h2> 
        <section class="wrapper-card">
					<article>
						<div class="card" >
						  <img src="https://cdn.pixabay.com/photo/2016/06/03/13/57/digital-marketing-1433427__340.jpg" class="card-img-top" alt="...">
						  <div class="card-body">
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  </div>
						  <div class="card-footer">
      					<small class="text-muted">Last updated 3 mins ago</small>
    					</div>
						</div>					
					</article>
					<article>
						<div class="card" >
						  <img src="https://cdn.pixabay.com/photo/2020/06/25/15/40/taking-5340011__340.jpg" class="card-img-top" alt="...">
						  <div class="card-body">
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  </div>
						  <div class="card-footer">
      					<small class="text-muted">Last updated 3 mins ago</small>
    					</div>											
						</div>	
					</article>
					<article>
						<div class="card" >
						  <img src="https://cdn.pixabay.com/photo/2020/08/09/14/25/business-5475661__340.jpg" width="100%" alt="...">
						  <div class="card-body">
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  </div>
						  <div class="card-footer">
      					<small class="text-muted">Last updated 3 mins ago</small>
    					</div>						  
						</div>						
					</article>
					<article>
						<div class="card text-white bg-primary mb-3" style="max-width: 18rem;">
						  <div class="card-header">Header</div>
						  <div class="card-body">
						    <h5 class="card-title">Primary card title</h5>
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  </div>
						</div>					
					</article>
					<article>
						<div class="card border-danger mb-3" style="max-width: 18rem;">
						  <div class="card-header">Header</div>
						  <div class="card-body text-danger">
						    <h5 class="card-title">Danger card title</h5>
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  </div>
						</div>					
					</article>
					<article></article>
        </section>
      </div>
    </div>
  </main>