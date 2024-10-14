<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/" var="URL"></c:url>

<form action= "${pageContext.request.contextPath}/admin/category/insert" method = "post"
	enctype="multipart/form-data">
	<label for="fname">Category Name:</label><br> 
	<input type="text"	id="categoryname" name="categoryname"> <br> 
	
	<label	for="lname">Images:</label><br> 
		<img id = "imagess" height="150" width="200" src="" />
	<input type="file" onchange = "chooseFile(this)" id="images1" name = "images1"> <br>
		
		
		
	<label	for="status">Status:</label><br> 
	<input type="text" id="status" name = "status"> <br>
	
	<br> <input type = "submit" value = "insert">
	
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