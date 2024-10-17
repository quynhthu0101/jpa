<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/updateProfile"	method="post" enctype="multipart/form-data">

    <h1>Update Profile</h1>
        <input type="hidden" name="username" value="${account.username}"/>
       
        
        <label for="fullname">Full Name:</label>
        <input type="text" id="fullname" name="fullname" value="${account.fullname}" required/><br/><br/>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${account.phone}" required/><br/><br/>
        
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${account.email}" required/><br/><br/>
        
        <label for="password">Password:</label>
        <input type="text" id="password" name="password" value="${account.password}" required/><br/><br/>
        
        <label for="lname">Images:</label><br>
		<c:if test="${account.avatar.substring(0,5) == 'https' }">
			<c:url value="${account.avatar}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${account.avatar.substring(0,5) != 'https' }">
			<c:url value="/image?fname=${account.avatar}" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" id = "imagess" />		
		<input type="file" id="images1" name="images1" onchange = "chooseFile(this)" value = "${account.avatar}"> 
	
    <input type="submit" value="Update"/>
    
</form>
<script src = "${URL}assets/global/plugins/jquery.min.js" type = "text/javascript"></script>
<script >
	function chooseFile(fileInput){
		if (fileInput.files && fileInput.files[0]){
			console.log(fileInput.files[0]);
			var reader = new FileReader();
			reader.onload = function(e){
				$('#imagess').attr('src', e.target.result);
			}
			reader.readAsDataURL(fileInput.files[0]);
		}
	}
</script>