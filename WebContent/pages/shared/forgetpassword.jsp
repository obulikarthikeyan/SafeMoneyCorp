<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Forget Password</title>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">  

<style>
    body {
        margin-top: 50px;
        position: relative;
    }
</style>
    
</head>



<body>
<jsp:include page="/pages/sidebar.jsp"></jsp:include>
<div id="page-wrapper">

<br>
<br><h4>Forget Password</h4>
<br>
<form class="form-horizontal" role="form" action="secQuestionValidation" method="post"  style="padding-left: 2%; padding-top: 2%;"> 
	<!-- <form class="form-horizontal" role="form" action="LoginController" method="post"> -->
  
  
  <div class="form-group">
    <label for="Q1" class="col-sm-2 control-label">${secQuestions.question1 }</label>
    <div class="col-sm-10">
      <input type="input" id="answer1" class="form-control"  name="answer1">
    </div>
    <br/>
     <label for="Q2" class="col-sm-2 control-label">${secQuestions.question2 }</label>
     <div class="col-sm-10">
      <input type="input" class="form-control"  id="answer2" name="answer2">
    </div>
    <br/>
    <label for="Q3" class="col-sm-2 control-label">${secQuestions.question3 }</label>
     <div class="col-sm-10">
      <input type="input" class="form-control" id="answer3" name="answer3">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Generate OTP</button>
    </div>
  </div>
</form>
</div>
<script src="js/jquery-1.11.0.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>