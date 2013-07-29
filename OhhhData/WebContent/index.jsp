<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>INDEX</h1>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript">
var asdf;
$.ajax({
    url: "example.svc",
    dataType: "json",
    cache: "false",
    success: function (response) {
        //alert("Returned " + response.length + " suppliers");
        asdf = response;
        document.write(asdf.d.EntitySets[0]);
        document.write(asdf.d.EntitySets[1]);
        document.write(asdf.d.EntitySets[2]);
    }
})
</script>
</body>
</html>