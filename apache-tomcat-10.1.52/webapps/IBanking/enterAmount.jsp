<%@ page language="java" contentType="text/html ;charset=UTF-8" %>
<!DOCTYPE html>

<%
    String username=(String) session.getAttribute("username");
    if(username==null){
        response.sendRedirect("transfer.jsp");
        return;
    }
%>
<html>
<head>
    <title> EnterAmount</title>
<style>

body{
    margin:0;
    font-family:Arial, Helvetica, sans-serif;
    background:#f4f6f9;

    display:flex;
    justify-content:center;
    align-items:center;
    height:100vh;
}

form{
    width:350px;
    background:white;
    padding:30px;
    border-radius:10px;
    box-shadow:0 4px 12px rgba(0,0,0,0.15);
    text-align:center;
}

input{
    width:100%;
    padding:10px;
    margin-top:10px;
    border-radius:6px;
    border:1px solid #ccc;
    box-sizing:border-box;
}

button{
    margin-top:15px;
    padding:10px 20px;
    border:none;
    background:#004080;
    color:white;
    border-radius:6px;
    cursor:pointer;
}

button:hover{
    background:#0066cc;
}

p{
    text-align:center;
    font-weight:bold;
}

</style>
</head>
<body>
<form action="enterAmount" method="post">
    Account: <input type="number" name="amount" required>
    <button type="submit">Chuyển Tiền</button>
</form>
</body>
<%
    String message=(String) request.getAttribute("message");
    if(message !=null){
%>
<p style="color:green"><%= message%></p>
<%
    }
%>
</html>