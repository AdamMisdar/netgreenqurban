<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> <%-- Number formatter --%>
<%@ page import="java.sql.*" %>
<%@ page import="qurban.connection.ConnectionManager" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Senarai Tempahan</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>

<style>

	body {
			padding: 0;
			margin: 0;
			font-family: Arial, Helvetica, sans-serif;
		}

	table {
			position: absolute;
			
			left: 50%;
			top: 50%;
			transform: translate(-50%, -50%);
			border-collapse: collapse;
			width: 70%;
			height: 100px;
			border: 1px solid #bdc3c7;
			box-shadow: 2px 2px 12px rgba(0,0,0,0.2), -1px -1px 8px rgba(0,0,0,0.2);
			margin-left: 110px;
		}

		input {
			width: 100%;
			padding: 12px 20px;
			margin: 8px 0;
			display: inline-block;
			border: 1px solid #ccc;
			border-radius: 4px;
			box-sizing: border-box;
		}

		th,
		td {
			padding: 10px;
			text-align: center;
			border-bottom: 1px solid #ddd;
		}

		.header{
			background-color: #037247;
			color: rgb(8, 8, 8);
            padding: 20px;
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
			padding: 15px 20px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 4px 2px;
			cursor: pointer;
			-webkit-transition-duration: 0.4s; 
			transition-duration: 0.4s;
			font-family: Arial, Helvetica, sans-serif;
            border-radius: 5px;
			
		}
		#buttonhome{
			position: absolute;
			font-size: 16px;
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
  position: relative;
}

.wrapper .sidebar{
  width: 200px;
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
		int client_id;
		int counter;
	%>
	<%
		if (session.getAttribute("clientID")==null) {
			response.sendRedirect("login.jsp");
		}
		else {
			client_id = (int)session.getAttribute("clientID");
		}
		counter = 0;
		
		long todayMillis = System.currentTimeMillis();
		Date dateToday = new Date(todayMillis);
		
	%>
<%-- DATABASE -----------------------------------------------------------%>
<sql:setDataSource	var="qurbanDatabase" driver="org.postgresql.Driver"
						url="jdbc:postgresql://ec2-3-223-169-166.compute-1.amazonaws.com:5432/dfcvvjuvqh9c4k?sslmode=require"
						user="mledzxdxfykycr"
						password="9d9f02cdbcf786cb80ebf7cdbcabfa637a4c84994673ed9256a9e83e39131589" />
<%----------------------------------------------------------- DATABASE --%>

<%-- CLIENT DETAILS -----------------------------------------------------%>
	<sql:query dataSource="${qurbanDatabase}" var="clientResult">
		SELECT * FROM client WHERE clientid = <%=client_id%>
	</sql:query>
<%----------------------------------------------------- CLIENT DETAILS --%>
		<div class="header">
			<div>
				<span style="position: absolute; right: 20px; top:7px; font-size: 16px; color white;">NETGREEN</span>
				<span></span>
			</div>
		</div>
		

	
		<div class="wrapper">
			<div class="sidebar">
				<h2>KLIEN</h2>
					<c:forEach var="client" items="${clientResult.rows}">
						<p style="position:relative; color: rgb(253, 253, 253); font-weight: bold; text-align:left; left: 18px">
						<c:out value="${client.clientfullname}"/>
						</p>
						<p style="position:relative; color: rgb(253, 253, 253); text-align:left; left: 18px;"> ID Akaun: 
						<c:out value="${client.clientid}"/>
						</p>
					</c:forEach>
				<ul>
					<li><a href="index-client.jsp" onclick="location.href='index-client.jsp'"><i class="fas fa-home"></i>Halaman Utama</a></li>
					<li><a href="view-client-account.jsp" onclick="location.href='view-client-account.jsp'"><i class="fas fa-user"></i>Profil</a></li>
					<li><a href="location.href='BookingHandler?action=createBooking&bookingDate=<%=dateToday%>&clientID=<%=client_id%>'" onclick="location.href='BookingHandler?action=createBooking&bookingDate=<%=dateToday%>&clientID=<%=client_id%>'"><i class="fas fa-address-book"></i>Buat Tempahan</a></li>
					<li><a href="client-booking-list.jsp" onclick="location.href='client-booking-list.jsp'"><i class="fas fa-address-book"></i>Senarai Tempahan</a></li>
		
				</ul> 
			</div>
		</div>
	

	<%-- BOOKING DETAILS --%>
	<sql:query dataSource="${qurbanDatabase}" var="bookingResult">
		SELECT * 
		FROM payment
		JOIN booking
		USING (bookingid)
		WHERE clientid = <%=client_id%>
		ORDER BY bookingid
	</sql:query>
	<div class="title">
	<h2>SENARAI TEMPAHAN</h2>
	</div>
	<div class="tablecontainer">
	<table>
		<tr>
			<th>No.</th>
			<th>Tarikh Tempah</th>
			<th>Harga Tempahan (RM)</th>
			<th></th>
		</tr>
	
	<c:forEach var="booking" items="${bookingResult.rows}">
		<% counter++; %>
		<tr>
			<td><c:out value="<%=counter%>"/></td>
			<td><c:out value="${booking.bookingdate}"/></td>
			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${booking.paymenttotal}" /></td>
			<td>
                <button class="button" onclick="location.href='BookingHandler?action=viewBooking&bookingID=${booking.bookingid}'" style="background-color: #2c752f; color: #fff;">Lihat&nbsp;&nbsp;<i class="bi bi-eye-fill"></i></button>
			</td>
		</tr>
	</c:forEach>
	</table>

</div>
</body>
</html>