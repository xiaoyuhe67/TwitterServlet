<%@page import="customtools.Dataget"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

	
%> 


<fmt:setLocale value="en_US"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
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
      <li ><a href="home.jsp" style="color: #fff; font-weight: bold;font-size:20px" class="active">Home</a></li>
      <li><a href="newsfeed.jsp" name="allpost" type="button" style="color: #fff; font-weight: bold;font-size:20px">News Feed</a></li>
      <li ><a href="<%=request.getContextPath() %>/logout"  style="color: #fff; font-weight: bold;font-size:20px">Log Out</a></li>
    </ul>
  
  </div>
</nav>


<form action="homeServlet"   id="myform" name="myform" >
<div class="container">
<table align="center" border="0" class="table">
<thead>
<tr ><th>Search posts</th></tr>
</thead>
<tbody>
<tr style="backgroundcolor:#4db6ac "><td >Please enter post, at most 141 characters:</td></tr>
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
<tr align="center"><td>
<input type="submit" name="method" value="Add" class="button"/>
<input type="submit" name="method" value="Search" class="button"/>
</td>
</tr>
</tbody>
</table>
</div>



<div class="container">


<c:if test="false">
    <input type="button" onclick="location.href='home.jsp'" class="button"
            value="Reset List" />
</c:if>
<c:if test="true">
    <input type="submit" name="method" value="Save" class="button" />
</c:if>
<input type="submit" name="method" value="Edit" class="button"/>
<input type="submit" name="method" value="Delete" class="button" />

<br /><br />

<table border="1" align="center" class="table responstable table-bordered table-hover">
<thead align="center">
<tr align="center">
<th align="center"><div align="center"></div></th>
<th align="center"><div align="center">Post Date</div></th>
<th align="center"><div align="center">Post Text</div></th>
<th align="center"><div align="center">Sentiment</div></th>
</tr>
</thead>
 <tbody>
 
 <c:forEach var="post" items="${posts}">
<tr>
	<td  style="width:5%" align="center" >
	<input type="checkbox" name="postid" value="${post.postid}" 
    <c:if test="${param.postid == post.postid and param.method != 'Save'}" > checked="checked"</c:if> 
    style="margin: 0 0 0 4px" onclick="radio(this)"></input>
    </td>
    
	<td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.postid == post.postid}">
            <input type="text" name="postdate" style="padding: 0"
                value="<fmt:formatDate pattern="yy-MMM-dd" value="${post.postdate}" />" />
        </c:when>
        <c:otherwise><fmt:formatDate pattern="yy-MMM-dd" value="${post.postdate}" /></c:otherwise>
    </c:choose>
 	</td> 
	<td align="center">
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.postid == post.postid}">
            <input type="text" name="posttext" style="padding: 0"
                value="<c:out value="${post.posttext}"/>" />
        </c:when>
        <c:otherwise><c:out value="${post.posttext}"/></c:otherwise>
    </c:choose>
	</td>
	
	<td align="center">
   <c:set var="mytext" value="${post.posttext}"/>   
   <img src=${happysadurlsbyuser[mytext] }  width="20" height="20" ></img>
    
   </td> 
	 </tr> 
	 </c:forEach>
	  </tbody> 
 </table> 

 </div> 
 
</form>

</body>
</html>


