<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
 <style>
 
 	main .container .content section {
 		display:grid;
 		grid-template-columns: repeat(3,1fr);
 		grid-auto-rows: 200px;
  	grid-gap: 10px;
 	}
 	main .container .content section article {
 		/*outline: 1px dotted red;*/
 		display:flex;
 		justify-content: center;
 		align-items: center;
 	}
 	main .container .content section article:nth-child(1){
 		background: #4bacb8;
 	}
 	main .container .content section article:nth-child(2){
 		background: #79b700;
 	}
 	main .container .content section article:nth-child(3){
 		background: #ff5131;
 	}
 	main .container .content section article:nth-child(4){
 		background: #aa00ff;
 	}
 	main .container .content section article:nth-child(5){
 		background: #eeff41;
 	}
 	main .container .content section article:nth-child(6){
 		background: #90caf9;
 	}
 	main .container .content section article:nth-child(4){
 		background: #aa00ff;
 	}
 	main .container .content section article:nth-child(5){
 		background: #eeff41;
 	}
 	main .container .content section article:nth-child(6){
 		background: #90caf9;
 	}
 	main .container .content section article:nth-child(7){
 		background: #afb42b;
 	}
 	main .container .content section article:nth-child(8){
 		background: #9fa8da;
 	}
 	main .container .content section article:nth-child(9){
 		background: #f48fb1;
 	} 	
 </style>
  <main>
    <div class="container">
      <div class="content">
        <section>
					<article>1</article>
					<article>2</article>
					<article>3</article>
					<article>4</article>
					<article>5</article>
					<article>6</article>
					<article>7</article>
					<article>8</article>
					<article>9</article>
        </section>
      </div>
    </div>
  </main>