<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:url value="/" var="URL"></c:url>
<html>
<head>
    <title>Edit Video</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="videoId" value="${video.videoId}">

    <label for="title">Title</label>
    <input type="text" name="title" value="${video.title}" required>
    <br>

    <label for="views">Views</label>
    <input type="text" name="views" value="${video.views}" required>
    <br>

    <label for="description">Description</label>
    <textarea name="description" required>${video.description}</textarea>
    <br>

    <label for="category">Category</label>
    <select name="categoryid" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.categoryid}" <c:if test="${category.categoryid == video.category.categoryid}">selected</c:if>>${category.categoryname}</option>
        </c:forEach>
    </select>
    <br>

	<label for="active">Active</label>
	<input type="checkbox" name="active" value="1"
	    <c:if test="${video != null && video.active == true}">
	        checked
	    </c:if> 
	> Hoạt động
	<br>
	
    <label for="poster">Poster</label>
    <input type="file" name="poster">
    <img src="${pageContext.request.contextPath}/upload/${video.poster}" alt="Current Poster" width="100">
    <br>

    <button type="submit">Update</button>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>
