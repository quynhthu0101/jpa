<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>Video List</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/admin/video/add">Add New Video</a>
<table border="1">
    <tr>
        <th>Video ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Category</th>
        <th>Views</th>
        <th>Poster</th>
        <th>Active</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="video" items="${listVideos}">
        <tr>
            <td>${video.videoId}</td>
            <td>${video.title}</td>
            <td>${video.description}</td>
            <td>${video.category.categoryname}</td>
            <td>${video.views}</td>
            <td>
                <c:if test="${video.poster.substring(0, 5) != 'https'}">
                    <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
                </c:if>
                <c:if test="${video.poster.substring(0, 5) == 'https'}">
                   <c:url value="${video.poster}" var="imgUrl"></c:url>
                </c:if>
                <img src="${imgUrl}" alt="Video Poster" height="100" width="150"/> 
            </td>
			<td>
					<c:if test="${video.active == true}">
						<span>Hoat Dong</span>
					</c:if> <c:if test="${video.active == false}">
						<span>Khoa</span>
					</c:if>
			</td>
			<td>
                <a href="${pageContext.request.contextPath}/admin/video/edit?id=${video.videoId}">Edit</a>
                <a href="${pageContext.request.contextPath}/admin/video/delete?id=${video.videoId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>