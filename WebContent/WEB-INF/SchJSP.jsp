<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script type="text/javascript" src="/Schedulator/includes/control.js"></script>
<link rel="stylesheet" type="text/css" href="/Schedulator/css/StyleSchJSP.css">
<title>Insert title here</title>

</head>
<body class="container">	
	<!--   MobilePhone Desktop  -->
	<!--  col-xs-? col-md-? -->
	<!--  1 < 12 -->
		
	<div class="row">
		<label class="center-block">  </label>
	</div>
	<!--  premiere ligne  -->
	<div class="row">
		<div class="form-group">
			<label for=""addBlocName"" class="col-sm-2 control-label">Nom du bloc : </label>
			<div class="col-xs-12 col-md-5">
		  		<input type="text" class="form-control" id="addBlocName" placeholder="Nom du boc" onchange=""> <!--  verification onchange  -->
			</div>
		</div>
	</div>
	<br />
	<!--  Deuxieme ligne divisant la page en 2 poru Fixe et Variable -->
	<div class="row" id="volletFixe"> 
		<!--  vollet de gauche - FIXE -->
		<div class=""> <!-- brd -->
			<button type="button" class="btn btn-primary center-block">Default button</button>
		</div>
		
		<!--  vollet de droite - VARIABLE -->
		<div class="">
			<button type="button" class="btn btn-primary center-block">Default button</button>
		</div>
	</div>
</body>
</html>