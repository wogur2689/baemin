<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/link.jsp"%>

<link rel="stylesheet" href="/css/modal.css">
<link rel="stylesheet" href="/css/store/detail.css">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=580ed6ceef0e0119b6f83716432542a7&libraries=services,clusterer,drawing"></script>

<%@ include file="/WEB-INF/view/include/header.jsp"%>


<!-- 메인 -->
<%@ include file="/WEB-INF/view/store/storeDetail.jsp"%>
<!-- 메인 -->

<!-- 푸터 -->
<%@ include file="/WEB-INF/view/include/footer.jsp"%>
<!-- 푸터 -->

<!-- 메뉴 모달 -->
<%@ include file="/WEB-INF/view/modal/modal_food.jsp"%>
<!-- 메뉴 모달 -->




<script type="text/javascript" src="/js/store/storeDetail.js"></script>
</body>
</html>