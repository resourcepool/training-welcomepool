<%@ page import="io.takima.master3.HelloService" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>JSP - Hello World Tutorial - Programmer Gate</title>
</head>
<body>
    <%= // JSP can run java code.
            ((HelloService)request.getAttribute("helloService")).createHelloMessage("jsp").concat("!!!") %>

    <!-- JSP can also import request attributes: -->
    ${helloService.createHelloMessage("Remi")}

</body>
</html>
