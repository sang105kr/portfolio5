<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
    <link
      rel="stylesheet"
      href="${contextPath }/webjars/owl.carousel/2.3.4/dist/assets/owl.carousel.css"
    />
    <link
      rel="stylesheet"
      href="${contextPath }/webjars/owl.carousel/2.3.4/dist/assets/owl.theme.default.css"
    />
    <script src="${contextPath }/webjars/owl.carousel/2.3.4/dist/owl.carousel.js"></script>
    <script>
      $(function () {
    	  $(".owl-carousel").css('background-color','black');
    	  
        $(".owl-carousel").owlCarousel({
          items: 3, //화면에 표시할 항목수
          loop: true, //무한반복
          margin: 10,
/*           dots: false,
          nav: false, */
          navText: ["이전", "다음"],
          autoplay: true, //자동 스크롤
          autoplayTimeout: 3000, //자동 스크롤시 시간간격
          autoplayHoverPause: true, //마우스 오버시 멈춤
          responsive: {
             0: {
               items: 1,
             },
             600: {
               items: 3,
             },
/*              1000: {
               items: 3,
             },
 */           },
        });
      });
    </script>    
<header>
  <div class="container container-h">
   <div class="owl-carousel owl-theme">
      <div class="item">
        <img
          src="https://cdn.pixabay.com/photo/2020/06/10/09/25/anemone-5281964__340.jpg"
          alt=""
        />
      </div>
      <div class="item">
        <img
          src="https://cdn.pixabay.com/photo/2020/07/22/08/39/waterfall-5428467__340.jpg"
          alt=""
        />
      </div>
      <div class="item">
        <img
          src="https://cdn.pixabay.com/photo/2019/09/23/16/39/square-4499056__340.jpg"
          alt=""
        />
      </div>
      <div class="item">
        <img
          src="https://cdn.pixabay.com/photo/2020/08/08/20/19/wave-5473869__340.jpg"
          alt=""
        />
      </div>
      <div class="item">
        <img
          src="https://cdn.pixabay.com/photo/2020/06/20/11/29/francisco-5320729__340.jpg"
          alt=""
        />
      </div>
      <div class="item">
        <img
          src="https://cdn.pixabay.com/photo/2020/06/30/22/46/butterfly-5357836__340.jpg"
          alt=""
        />
      </div>
      <div>
        <img
          src="https://cdn.pixabay.com/photo/2020/06/29/20/45/mother-5354379__340.jpg"
          alt=""
        />
      </div>
    </div>  
	</div>
</header>
 
  
  
  