<%@page import="java.util.ArrayList" %>

<%@page import="com.example.webapphr1_2023.Beans.Country" %>

<jsp:useBean id="listaPaises" type="ArrayList<Country>" scope="request" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nueva locacion</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="LocationServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nueva locacion</h1>
            <hr>
            <div class="mb-3">
                <label for="street_address">Direccion</label>
                <input type="text" class="form-control form-control-sm" name="street_address" id="street_address">
            </div>
            <div class="mb-3">
                <label for="postal_code">Codigo Postal</label>
                <input type="text" class="form-control form-control-sm" name="postal_code" id="postal_code">
            </div>
            <div class="mb-3">
                <label for="city">Ciudad</label>
                <input type="text" class="form-control form-control-sm" name="city" id="city">
            </div>
            <div class="mb-3">
                <label for="state_province">Provincia de estado</label>
                <input type="text" class="form-control form-control-sm" name="state_province" id="state_province">
            </div>

            <div class="mb-3">
                <label for="country_id">Pais</label>
                <select name="country_id" class="form-select" id="country_id">
                    <% for(Country country: listaPaises){ %>
                    <option value="<%=country.getCountryId()%>"> <%=country.getCountryName()%> </option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/LocationServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>

</html>
