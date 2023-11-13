<%@page import="java.util.ArrayList" %>

<%@page import="com.example.webapphr1_2023.Beans.Country" %>
<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<jsp:useBean id="listaPaises" type="ArrayList<Country>" scope="request" />
<jsp:useBean id="location" type="Location" scope="request"/>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Editar locacion</title>
</head>
<body>
<div class='container mb-4'>
    <div class="row justify-content-center">
        <h1 class='mb-3'>Editar locacion</h1>
        <hr>
        <form method="POST" action="LocationServlet?action=actualizar" class="col-md-6 col-lg-6">
            <input type="hidden" name="location_id" value="<%= location.getLocationId()%>"/>
            <div class="mb-3">
                <label for="street_address">First Name</label>
                <input type="text" class="form-control form-control-sm" name="street_address" id="street_address"
                       value="<%= location.getStreetAddress() == null ? "" : location.getStreetAddress()%>">
            </div>
            <div class="mb-3">
                <label for="postal_code">Last Name</label>
                <input type="text" class="form-control form-control-sm" name="postal_code" id="postal_code"
                       value="<%= location.getPostalCode() == null ? "" : location.getPostalCode()%>">
            </div>
            <div class="mb-3">
                <label for="city">Email</label>
                <input type="text" class="form-control form-control-sm" name="city" id="city"
                       value="<%= location.getCity() == null ? "" : location.getCity()%>">
            </div>
            <div class="mb-3">
                <label for="state_province">Phone number</label>
                <input type="text" class="form-control form-control-sm" name="state_province" id="state_province"
                       value="<%= location.getStateProvince() == null ? "" : location.getStateProvince()%>">
            </div>

            <div class="mb-3">
                <label for="country_id">Pais</label>
                <select name="country_id" class="form-select" id="country_id">
                    <% for(Country country: listaPaises){ %>
                    <option value="<%=country.getCountryId()%>"  <%=country.getCountryId().equals(location.getCountry().getCountryId())?"selected":""%>   > <%=country.getCountryName()%> </option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/LocationServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Actualizar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
