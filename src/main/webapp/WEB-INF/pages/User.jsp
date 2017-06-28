<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>User List</title>
</head>
<body>
<div align="center">
  <h1 style="background-color: lightgreen; color: darkgreen">Employee
    List
  </h1>
  <table cellspacing="0" cellpadding="6" border="1">
    <tr bgcolor="grey" style="color: white">
      <th>No</th>
      <th>Username</th>
      <th>Email</th>

    </tr>
    <c:forEach var="user" items="${userList}" varStatus="status">
      <tr bgcolor="lightyellow">
        <td><b>${status.index + 1}</b></td>
        <td>${user.username}</td>
        <td>${user.email}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>