<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${param} 
${errors }
	<form action="list">
		<input type="text" name="projectName" value="${project.projectName}"/>
		<c:if test="${!empty errors.projectName}"><b><font color="red">${errors.projectName}</font></b></c:if>
		
		<input type="submit"/>
	</form>

</body>
</html>