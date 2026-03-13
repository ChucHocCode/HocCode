<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Transfer</title>
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

.container{
    width:400px;
    background:white;
    padding:30px;
    border-radius:12px;
    box-shadow:0 4px 12px rgba(0,0,0,0.15);
    text-align:center;
}

h2{
    color:#004080;
    margin-bottom:25px;
    font-size:26px;
}

input{
    width:90%;
    padding:10px;
    margin:10px 0;
    border-radius:6px;
    border:1px solid #ccc;
    box-sizing:border-box;
}

button{
    width:95%;
    padding:12px;
    background:#004080;
    color:white;
    border:none;
    border-radius:6px;
    margin-top:15px;
    cursor:pointer;
    transition:0.3s;
}

button:hover{
    background:#0066cc;
}

a.back{
    display:inline-block;
    margin-top:20px;
    padding:8px 16px;
    background:#004080;
    color:white;
    text-decoration:none;
    border-radius:6px;
    transition:0.3s;
}

a.back:hover{
    background:#0066cc;
    text-decoration:none;
}

.msg{
    color:green;
    margin-top:15px;
    font-weight:bold;
}
</style>
</head>

<body>
<div class="container">
    <h2>Transfer</h2>
    <form action="transfer" method="post">
        <p>Name</p>
        <input type="text" name="name" required>

        <p>toAccount</p>
        <input type="text" name="toAccount" required>

        <button type="submit">Check</button>
    </form>

    <a class="back" href="home">Back Home</a>

    <%
        String message = (String) request.getAttribute("message");
        if(message != null){
    %>
        <p class="msg"><%= message %></p>
    <%
        }
    %>
</div>
</body>
</html>