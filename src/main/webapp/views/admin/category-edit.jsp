<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/category/update"
	method="post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" value="${cate.categoryid }" hidden="hidden"> <br> <label
		for="fname">Category Name:</label><br> 
		<input type="text" 	id="categoryname" name="categoryname" value="${cate.categoryname }"> <br> 
	<label for="lname">Images:</label><br>
		<c:if test="${cate.images.substring(0,5) == 'https' }">
			<c:url value="${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${cate.images.substring(0,5) != 'https' }">
			<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" id = "imagess" />		
	<input type="file" id="images1" name="images1" onchange = "chooseFile(this)" value = "${cate.images}"> <br> 
	<label for="status">Status:</label><br> <input type="text" id="status"
		name="status" value="${cate.status }"> <br> <br> <input
		type="submit" value="Submit">

</form>
<script src = "${URL}assets/global/plugins/jquery.min.js" type = "text/javascript"></script>
	<script >
	function chooseFile(fileInput){
		if (fileInput.files && fileInput.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#imagess').attr('src', e.target.result);
			}
			reader.readAsDataURL(fileInput.files[0]);
		}
	}
	</script>
