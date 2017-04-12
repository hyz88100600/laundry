<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/taglib.jsp"%>
<%@ page import="com.laundry.domain.type.StatusCode" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>返回码</title>

<style type="text/css">
  table
  {
  border-collapse:collapse;
  }

  table,th, td
  {
  border: 1px solid black;
  }
  
  table
  {
  width:100%;
  }

  th
  {
  height:50px;
  }
  
</style>

</head>
<body>
	<table>
		<thead>
			<tr>
				<td>编码</td>
				<td>描述</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${StatusCode.values() }" var="statusCode">
				<tr>
					<td>${statusCode.code }</td>
					<td>${statusCode.message }</td>
				</tr>
			</c:forEach>		
		</tbody>
	</table>	
</body>
</html>