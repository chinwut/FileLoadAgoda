<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <spring:url value="/resources/css/bootstrap.min.css" var="coreCss"/>
    <spring:url value="/resources/js/jquery.min.js" var="jquery"/>
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrap"/>
    <link href="${coreCss}" rel="stylesheet">
    <script src="${jquery}"></script>
    <script src="${bootstrap}"></script>
</head>
<body>
<div class="container">
    ${message}
    <form action="/downloadResource" method="post">
        Download Path : <input type="text" name="fileName"><br>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
