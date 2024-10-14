<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/" var="URL"></c:url>
<html>
<head>
    <title>Add Video</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="POST" enctype="multipart/form-data">
    
    <label for="videoId">Video ID</label>
    <input type="text" name="videoId" required>
	 <br>
	 
    <label for="title">Title</label>
    <input type="text" name="title" required>
     <br>

    <label for="views">Views</label>
    <input type="text" name="views" required>
     <br>

    <label for="description">Description</label>
    <textarea name="description" required></textarea>
     <br>

    <label for="category">Category</label>
    <select name="categoryid" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.categoryid}">${category.categoryname}</option>
        </c:forEach>
    </select>
     <br>

    <label for="active">Active</label>
    <input type="checkbox" name="active" value="1"> Hoạt động
     <br>

    <label for="poster">Poster</label>
    <input type="file" name="poster">
     <br>

    <button type="submit">Save</button>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>
