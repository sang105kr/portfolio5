<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>
<title>메모리현황</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<style>
	.content{
		display:flex;
		justify-content: center;
		align-items: center;
		height:70vh;
	}
	#memoryMonitorContainer{
		flex-basis: 100%;
	}
</style>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script>
let heapMemoryChart;
$(function() {
	heapMemoryChart = new Highcharts.Chart({
		chart : {
			renderTo : 'memoryMonitorContainer',
			defaultSeriesType : 'spline',
		},
		title : {
			text : 'Memory Monitor'
		},
		xAxis : {
			type : 'datetime',
			tickPixelInterval : 100,
			maxZoom : 20 * 1000,
			title : {
				text: 'Date',
				margin: 10
			}
		},
		yAxis : {
			minPadding : 0.2,
			maxPadding : 0.2,
			title : {
				text : '힙 메모리',
				margin : 30
			}
		},
    tooltip: {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: '{point.x:%H:%M:%S} - {point.y:.0f}MB'
    },
    plotOptions: {
        spline: {
            marker: {
                enabled: true
            }
        }
    },
		series : [
			{
				name : 'used',
				data : []
			}, {
				name : 'max',
				data : []
			}, {
				name : 'committed',
				data : []
			}
		],
		colors: ['#ff0000', '#0d233a', '#8bbc21', '#910000', '#1aadce',
	           '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a']
	});
});

function requestMemoryInfo() {
	const ws = new WebSocket("ws://localhost:9080/portfolio/websocket/memorymonitor");
	ws.addEventListener("message",(event)=>{
		console.log(event.data);
		const series = heapMemoryChart.series[0];
		const shift = series.data.length > 20;

		const data = JSON.parse(event.data);
		const time = parseInt(data.time, 10);
		const used = parseInt(data.used, 10);
		const max = parseInt(data.max, 10);
		const committed = parseInt(data.committed, 10);

	 	heapMemoryChart.series[0].addPoint([ time, used ], true, shift);
		heapMemoryChart.series[1].addPoint([ time, max ], true, shift);
		heapMemoryChart.series[2].addPoint([ time, committed ], true, shift); 
		
		ws.send(event.data);
	},false);
}

requestMemoryInfo();
</script>
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

  <!-- header -->
<%--   <%@ include file="/WEB-INF/views/include/header.jsp" %> --%>

  <!-- 메뉴 -->
  <%@ include file="/WEB-INF/views/include/menu.jsp" %>
  <!-- 본문 -->
  <main>
    <div class="container">
      <div class="content">    
				<div id="memoryMonitorContainer"></div>
			</div>
		</div>
	</main>
  <!-- 푸터 -->
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>  

</body>
</html>