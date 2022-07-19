<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Senarai Haiwan</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>

	<style>

	body {
		padding: 0;
		margin: 0;
		font-family: Arial, Helvetica, sans-serif;
	}

	table{
                border-collapse: collapse; 
                border: 1px solid #bdc3c7;
                box-shadow: 2px 2px 12px rgba(0,0,0,0.2), -1px -1px 8px rgba(0,0,0,0.2);
                margin-left: auto;
                margin-right: auto;
                width: 100%;
            }

	th,
	td {
		padding: 12px;
		text-align: center;
		border-bottom: 1px solid #ddd;
	}

	.header{
			background-color: #037247;
			color: #fff;
            padding: 12px;
	}

	h2 {
			font-weight: 600;
			text-align: center;
			color: rgb(5, 5, 5);
			padding: 10px 0px;
            font-size: 30px;
			
			
		}
		.title {
			color: #0c0c0c;
			border: #035317 2px solid;
			height: 50px;
			width: 90vh;
			margin: auto;
			margin-top: 30px;
			margin-bottom: 20px;
			margin-right: 300px;
			padding : auto;
			border-radius: 10px;
			background-color: #037247;

			
		}
		.title h2{
			color: white;
		}

	@media only screen and (max-width: 768px) {
		table{
			width: 90%;
		}
	}

	.button {
		border: none;
		width: 150px;
		color: white;
		padding: 15px 32px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 14px;
		margin: 4px 2px;
		cursor: pointer;
		-webkit-transition-duration: 0.4s; /* Safari */
		transition-duration: 0.4s;
		font-family: Arial, Helvetica, sans-serif;
		border-radius: 5px;
		
	}

	.button:hover {
		box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
	}

	@import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

	*{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	font-family: 'Josefin Sans', sans-serif;
	}

	body{
	background-color: #f3f5f9;
	}

	.wrapper{
		display: flex;
		position: absolute;
		top:0px;
		}

	.wrapper .sidebar{
	width: 250px;
	height: 100%;
	background: #037247;
	padding: 30px 0px;
	position: fixed;
	}

	.wrapper .sidebar h2{
	color: #fff;
	text-transform: uppercase;
	text-align: center;
	margin-bottom: 30px;
	}

	.wrapper .sidebar ul li{
	padding: 15px;
	border-bottom: 1px solid #bdb8d7;
	border-bottom: 1px solid rgba(0,0,0,0.05);
	border-top: 1px solid rgba(255,255,255,0.05);
	}    

	.wrapper .sidebar ul li a{
	color: #fafafa;
	display: block;
	}

	.wrapper .sidebar ul li a .fas{
	width: 25px;
	}

	.wrapper .sidebar ul li:hover{
	background-color: #5a595e;
	}
		
	.wrapper .sidebar ul li:hover a{
	color: #fff;
	}
	
	.wrapper .sidebar .social_media{
	position: absolute;
	bottom: 0;
	left: 50%;
	transform: translateX(-50%);
	display: flex;
	}

	.wrapper .sidebar .social_media a{
	display: block;
	width: 40px;
	background: #565558;
	height: 40px;
	line-height: 45px;
	text-align: center;
	margin: 0 5px;
	color: #ffffff;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	}

	.wrapper .main_content{
	width: 100%;
	margin-left: 200px;
	}

	.wrapper .main_content .header{
	padding: 20px;
	background: #fff;
	color: #717171;
	border-bottom: 1px solid #e0e4e8;
	}

	.wrapper .main_content .info{
	margin: 20px;
	color: #717171;
	line-height: 25px;
	}

	.wrapper .main_content .info div{
	margin-bottom: 20px;
	}

	@media (max-height: 500px){
	.social_media{
		display: none !important;
	}
	}

	</style>
	
</head>
<body>
    	<%!
		int committee_id;
    	boolean isManager;
		%>
		
<%-- LOGIN REQUIREMENT  -------------------------------------------------%>
		
	<%
		//user has not logged in
		if(session.getAttribute("committeeID") == null) {
		
			//redirect user to login page
			response.sendRedirect("login.jsp");
		}
		else {
		
			// Get client
			committee_id = (int)session.getAttribute("committeeID");
		}
			
		isManager = (boolean)session.getAttribute("isManager");
	%>
<%------------------------------------------------- LOGIN REQUIREMENT  --%>

	<%-- DATABASE --%>
	<sql:setDataSource	var="qurbanDatabase" driver="org.postgresql.Driver"
						url="jdbc:postgresql://ec2-3-223-169-166.compute-1.amazonaws.com:5432/dfcvvjuvqh9c4k?sslmode=require"
						user="mledzxdxfykycr"
						password="9d9f02cdbcf786cb80ebf7cdbcabfa637a4c84994673ed9256a9e83e39131589" />
						
	<%-- SQL QUERY - DISPLAY ALL ANIMAL DETAILS --%>
	<sql:query dataSource="${qurbanDatabase}" var="resultAnimal">
	   SELECT * FROM animaldetails ORDER BY animaldetailsid
	</sql:query>
	
	<%-- SQL QUERY - DISPLAY COMMITTEE --%>
	<sql:query dataSource="${qurbanDatabase}" var="resultCommittee">
	   SELECT * FROM committee JOIN management USING (committeeid) WHERE committeeid = <%=committee_id %>
	</sql:query>
	
		
		<div class="header" style="height: 50px">
			<div>
				<span style="position: absolute; right: 20px; top:15px; font-size: 16px; height: 100px">NETGREEN</span>
				<span></span>
			</div>
		</div>
		

	
		<div class="wrapper">
			<div class="sidebar">
				<h2>PENGURUSAN</h2>
					<c:forEach var="committee" items="${resultCommittee.rows}">
						<p style="position:relative;Left:18px;color: rgb(253, 253, 253); font-weight: bold;">
						<c:out value="${committee.committeefullname}"/>
						</p>
						<p style="position:relative;Left:18px;color: rgb(253, 253, 253);">
						<c:out value="${committee.managementposition}"/>
						</p>
					</c:forEach>
				<ul>
					<li><a href="index-committee.jsp" onclick="location.href='index-committee.jsp'"><i class="fas fa-home"></i>Halaman Utama</a></li>
					<li><a href="committee-booking-list.jsp" onclick="location.href='committee-booking-list.jsp'"><i class="fas fa-address-book"></i>Senarai Tempahan</a></li>
					<li><a href="animal-details-list.jsp" onclick="location.href='animal-details-list.jsp'"><i class="fas fa-address-book"></i>Senarai Maklumat Haiwan</a></li>
					<% if (isManager) { %>
					<li><a href="committee-list.jsp" onclick="location.href='committee-list.jsp'"><i class="fas fa-address-book"></i>Senarai AJK</a></li>
					<li><a href="client-list.jsp" onclick="location.href='client-list.jsp'"><i class="fas fa-address-book"></i>Senarai Klien</a></li>
					<% } %>  
					<li><a href="view-committee-account.jsp"><i class="fas fa-user"></i> Akaun</a></li>
					<li><a href="LoginHandler?action=logout" onclick="location.href='LoginHandler?action=logout'"><i class="fas fa-sign-out-alt"></i> Log Keluar</a></li>
		
				</ul> 
			</div>
		</div>
	
	

	<div class="title" style="text-align: center;">
	<h2>SENARAI HAIWAN</h2>
	</div>
	
	<%-- ADD NEW ANIMAL DETAILS BUTTON --%>
	<button class="button" style="background-color: #4CAF50; width: 200px; position:relative; left:280px; top:2px;" onclick="location.href='create-animal-details.jsp'">TAMBAH HAIWAN BARU</button><br><br>
	
	<%-- LIST OF ANIMAL DETAILS --%>
	<table style="width: 50%">
       
                <tr id="header">
				<th style="width: 200px;">Jenis Haiwan</th>
				<th style="width: 200px;">Harga Seekor (RM)</th>
				<th style="width: 200px;">Tindakan</th>
			</tr>
		<c:forEach var="animalDetails" items="${resultAnimal.rows}">
			<tr>
				<td>
					<c:out value="${animalDetails.animaltype}"/>
				</td>
				<td>
					<c:out value="${animalDetails.animalprice}"/>
				</td>
				<td> <%-- TINDAKAN --%>
					<form method="post"> <%-- UPDATE/KEMASKINI BUTTON --%>
						<input type="hidden" name="animalDetailsID" value="${animalDetails.animaldetailsid}">
						<button class="button" style="background-color: #4CAF50;" formaction="edit-animal-details.jsp">KEMASKINI</button>
					</form>
					<form method="post"> <%-- DELETE/PADAM BUTTON --%>
						<input type="hidden" name="animalDetailsID" value="${animalDetails.animaldetailsid}"> 	<%-- Set Animal Details ID --%> <%-- SERVLET REFERENCE --%>
						<input type="hidden" name="action" value="deleteAnimalDetails"> 						<%-- Set 'action' to 'deleteAnimalDetails' --%> <%-- SERVLET REFERENCE --%>
						<button class="button" style="background-color: #db0f31;" formaction="AnimalDetailsServlet" onclick="deleteConfirm()">PADAM</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<script>
	function deleteConfirm() {
		var result = confirm('Anda pasti padam maklumat haiwan ini?');
		if (result == false) {
			event.preventDefault();
		}
	}

	</script>

	
</body>
</html>