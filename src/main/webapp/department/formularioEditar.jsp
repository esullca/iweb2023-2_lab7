<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@page import="com.example.webapphr1_2023.Beans.Department" %>
<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<jsp:useBean id="listaJefes" type="ArrayList<Employee>" scope="request" />
<jsp:useBean id="listaLocaciones" type="ArrayList<Location>" scope="request" />
<jsp:useBean id="departamento" type="Department" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Editar Departmaneto</title>
</head>
<body>
<div class='container mb-4'>
    <div class="row justify-content-center">
        <h1 class='mb-3'>Editar departamento</h1>
        <hr>
        <form method="POST" action="DepartmentServlet?action=actualizar" class="col-md-6 col-lg-6">
            <input type="hidden" name="departmentId" value="<%= departamento.getDepartmentId()%>"/>
            <div class="mb-3">
                <label for="street_address">street_address</label>
                <input type="text" class="form-control form-control-sm" name="street_address" id="street_address"
                       value="<%= departamento.getDepartmentName() == null ? "" : departamento.getDepartmentName()%>">
            </div>


            <div class="mb-3">

                <label for="manager_id">Manager</label>
                <select name="manager_id" class="form-select" id="manager_id">
                    <option value="0">-- Sin jefe --</option>
                    <% for(Employee manager: listaJefes){ %>
                    <option value="<%=manager!=null ? manager.getEmployeeId() : 0%>" <%=manager!=null && departamento.getManager()!=null && (manager.getEmployeeId() == departamento.getManager().getEmployeeId())?"selected":""%>  > <%=manager!=null? manager.getFullName(): ""%> </option>
                    <% } %>
                </select>
            </div>


            <div class="mb-3">
                <label for="location_id">Department</label>
                <select name="location_id" class="form-select" id="location_id">
                    <option value="0">-- Sin locacion --</option>
                    <% for(Location location: listaLocaciones){ %>
                    <option value="<%=location.getLocationId()%>" <%=location.getLocationId() == departamento.getLocation().getLocationId()?"selected":""%>   > <%=location.getStreet_address()%> </option>
                    <% } %>
                </select>
            </div>


            <a href="<%= request.getContextPath()%>/DepartmentServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Actualizar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
