<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="schedPack.Bloc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@include file="/includes/header.html" %>
<script type="text/javascript" src="/Schedulator/includes/CreateEdt.js"></script>
<title>Créer un emplois du temps </title>
</head>
<body class="container">
   	
   	<h2 id="titreAddBloc"> Ajouter les blocs de votre emplois du temps : </h2>
   	<div id="divListBloc"></div>
   	<br>
	<input type="button" value="add" id="add" >

</body>
</html>