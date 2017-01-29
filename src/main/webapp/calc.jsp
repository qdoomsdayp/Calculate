<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wital
  Date: 23.01.2017
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src = "q1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}q2.js"></script>
    <meta cgarset="utf-8">
    <title>Калькулятор</title>
    <link rel="stylesheet" href="/cal.css" type="text/css">
</head>
<body>
<script>q1()</script>
<script>q2()</script>
<h1>
    Калькулятор
</h1>
<form target="my" action="Calculate.java" method="post">
    <div>
        <p>Введите выражение </p>
        <input type="text" width="300px" id="val" name="expression">
        <input type="button" name="Input" value="Рассчитать" onclick="OutputValues()">
    </div>
    <div>
        <p>Результат</p>
        <p>
            <input type="text" id="rez">
        </p>
    </div>


</form>
<script>

    function OutputValues() {

        $.ajax({
            type: "POST",
            url: "",
            data: {type: "selectList", value: document.getElementById("val").value, id: '123'},
            async: false,
            dataType: "json",

//if received a response from the server
            success: function (data, textStatus, jqXHR) {
//our country code was correct so we have some information to display


                $("#rez").val(data.valu);


            },
            error: function (data) {
                alert("not correct input");
            }
        })
    }
    function inspection() {
        var str = document.getElementById("val").value;


    }
</script>

</body>

</html>

