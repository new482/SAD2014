<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hello world EJB</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/sayHello">
      <input type="text" name="name" /><input type="submit" value="Press me!" />
    </form>
  </body>
</html>