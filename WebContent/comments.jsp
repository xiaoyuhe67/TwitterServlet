<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<fmt:setLocale value="en_US"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="mycss.css" />
</head>
<body >

<script language="javascript" type="text/javascript">
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}


</script>

<nav class="navbar navbar-default" style="background-color: #01579b">
  <div class="container-fluid ">
    <div class="navbar-header" >
      <a class="navbar-brand " href="login.jsp" style="color: #fff; font-weight: bold;font-size:20px">Bullhorn</a>
    </div>
    <ul class="nav navbar-nav">
     <li><a href="userprofile.jsp" style="color: #fff; font-weight: bold;font-size:20px"><img src= ${ images}  width="20" height="20"></img> ${username}</a></li>
      <li ><a href="home.jsp" style="color: #fff; font-weight: bold;font-size:20px" >Home</a></li>
      <li><a href="newsfeed.jsp" name="allpost" type="button" style="color: #fff; font-weight: bold;font-size:20px" >News Feed</a></li>
      <li ><a href="<%=request.getContextPath() %>/logout"  style="color: #fff; font-weight: bold;font-size:20px">Log Out</a></li>
    </ul>
  
  </div>
</nav>


<form action="commentServlet" method="get">


<div class="container">
<table align="center" border="0" class="table">
<thead>
<tr ><th>Search comments</th></tr>
</thead>
<tbody>
<tr style="backgroundcolor:#4db6ac "><td >Please enter comment, at most 141 characters:</td></tr>
<tr>
<td>
<textarea name="limitedtextarea" rows="5" cols="30" onKeyDown="limitText(this.form.limitedtextarea,this.form.countdown,141);" 
onKeyUp="limitText(this.form.limitedtextarea,this.form.countdown,141);">
</textarea>
</td>
</tr>
<tr>
<td><font size="2" >(Maximum characters: 141)<br>
You have <input readonly type="text" name="countdown" size="3" value="141" style="width: 50px; border:none" > characters left.</font><br>
</td>
</tr>
<tr><td>
<input type="submit" name="method" value="Add" class="button"/>
<input type="submit" name="method" value="Search" class="button"/>
</td>
</tr>
</tbody>
</table>

</div>

<div class="container">
<table border="0" align="center" class="table table-bordered table-hover table-strip">
<thead>
<tr ><th>The comments of this post</th></tr>
</thead>
<tbody>
<tr>
<td>
<a  style=" font-weight: bold;font-size:20px"><img src= ${ postuserimage}  width="20" height="20"></img> ${postusername}</a>
</td>
</tr>

<tr>
<td align="center">${postforcomment.posttext}</td>
</tr>

</tbody>


</table>

<table border="1" align="center" class="table table-bordered table-hover table-strip">
<thead> 
<tr>

	<th><div align="center">User</div></th>
	<th><div align="center">Comment</div></th>
	<th><div align="center">Comment date</div></th>
</tr>
</thead>
<tbody>


<c:forEach var="comment" items="${commentsofpost}"> 
<tr> 

<td align="center"> 
   <c:set var="mypostid" value="${comment.postid}"/>
     <c:out value="${fromusernames[mypostid]}" />
   </td>   
   <td align="center"> 
   
     <c:out   value="${comment.commenttext}" /> 
   </td> 
   <td align="center"> 
     <fmt:formatDate value="${comment.commentdate}" pattern="yy-MMM-dd"/>
   </td> 
  </tr> 

 </c:forEach> 
 
</tbody>
</table>
 </div> 
</form>

</body>
</html>