<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@page import="com.example.webapphr1_2023.Beans.Department" %>
<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<jsp:useBean id="listaJefes" type="ArrayList<Employee>" scope="request" />
<jsp:useBean id="listaLocaciones" type="ArrayList<Location>" scope="request" />




<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nuevo Departamento</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="DepartmentServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nuevo Departamento</h1>
            <hr>
            <div class="mb-3">
                <label for="street_address">Nombre Departamento</label>
                <input type="text" class="form-control form-control-sm" name="street_address" id="street_address">
            </div>

            <div class="mb-3">
                <label for="location_id">Locacion</label>
                <select name="location_id" class="form-select" id="location_id">
                    <option value="0">-- Sin locacion --</option>
                    <% for(Location location: listaLocaciones){ %>
                    <option value="<%=location.getLocationId()%>"><%=location.getStreet_address()%></option>
                    <% } %>
                </select>
            </div>







            <div class="mb-3">
                <label for="manager_id">Manager</label>
                <select name="manager_id" class="form-select" id="manager_id">
                    <option value="0">-- Sin jefe --</option>
                    <% for(Employee manager: listaJefes){ %>
                    <option value="<%=manager.getEmployeeId()%>"> <%=manager.getFullName()%> </option>
                    <% } %>
                </select>
            </div>

            <a href="<%=request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Regresar</a>






            <a href="<%= request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Cancelar</a>

            <a href="<%=request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Regresar</a>


            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
        <a href="<%=request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Regresar</a>
    </div>

    <a href="<%=request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Regresar</a>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
