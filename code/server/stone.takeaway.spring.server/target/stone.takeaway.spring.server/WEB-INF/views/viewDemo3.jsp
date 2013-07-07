<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    hi, this is viewDemo3, <c:out value="${caller}" default="viewDemo3"/> call me.
</html>