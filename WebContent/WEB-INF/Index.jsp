<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/includes/header.html" %>

<script type="text/javascript" src="/Schedulator/includes/control.js"></script>
<link rel="stylesheet" type="text/css" href="/Schedulator/css/styleIndex.css">

<title>Schedulator</title>

</head>
<body class="container text-center">
	<h1> Schedulator</h1>
	<h3>
		<a href="http://cours-info.iut-bm.univ-fcomte.fr/">Angeline Biot - </a> 
		<a href="http://cours-info.iut-bm.univ-fcomte.fr/"> Jordan Laplace - </a>
		<a href="https://github.com/sla2z">Samir Lazzali </a>
	</h3>
	
	<h3> Notre application calcul un emploi du temps optimal. L'utilisateur
	 saisit tout ce qu'il a à faire durant une semaine ainsi que les contraintes 
	 liées à chaque activité. Il précise ainsi quelles activités ont une plage horaire fixe 
	 non modifiable, lesquelles ont plusieurs plages horaire possibles et lesquelles ont 
	 simplement une durée et peuvent être effectuées à n'importe quel moment de la semaine.
	  Notre programme emploie alors un solveur pour résoudre un problème de « programmation 
	  par contraintes » qui calcul l'organisation horaire optimale pour que toutes les activités 
	  soient faisables durant cette semaine. Le rendu graphique de ce calcul est un emploi du temps 
	  classique sous format PDF. Nous avons choisi ce format car nous souhaitions que l'utilisateur 
	  puisse sauvegarder son emploi du temps sans avoir besoin de revenir sur le site web une fois le 
	  calcul effectué.</h3>
	<h2><a href="/Schedulator/CreateEdt">Créer un emploi du temps </a> </h2>
	2017
	
</body>
</html>