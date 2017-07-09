<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<li><a href="<spring:url value="/signup"/>">Sign Up</a></li>--%>
<%--<li><a href="<spring:url value="/login/"/>">Login</a></li>--%>
<%--<li><a href="<spring:url value="/allstor"/>">Users</a></li>--%>
<%--<li><a href="<spring:url value="/newStory"/>">Write</a></li>--%>

<c:choose>
        <c:when test="${empty username}">
                <%--var1 is empty or null.--%>
                <li><a href="<spring:url value="/signup"/>">Sign Up</a></li>
                <li><a href="<spring:url value="/login"/>">Login</a></li>
        </c:when>
        <c:otherwise>
                <%--var1 is NOT empty or null.--%>

                <li><a href="<spring:url value="/story/home"/>">Home</a></li>
                <li><a href="<spring:url value="/newStory"/>">Write</a></li>
                <li><a href="<spring:url value="/logout"/>">logout</a></li>


        </c:otherwise>
</c:choose>
