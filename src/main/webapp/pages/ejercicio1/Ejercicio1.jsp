<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ejercicio1.beans.Estudiante" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ficha de Estudiante</title> <!-- Título de la página -->
</head>
<body>
<h2>Ficha de Estudiante</h2> <!-- Encabezado principal de la página -->
<!-- Formulario para ingresar los datos del estudiante -->
<form action="${pageContext.request.contextPath}/ejercicio1/submit" method="post">
    <!-- Campos de entrada del formulario -->
    <label>Carnet:</label><input type="text" name="carnet" required><br>
    <label>Nombres:</label><input type="text" name="nombres" required><br>
    <label>Apellidos:</label><input type="text" name="apellidos" required><br>
    <label>Dirección:</label><input type="text" name="direccion" required><br>
    <label>Teléfono:</label><input type="text" name="telefono" required><br>
    <label>Email:</label><input type="email" name="email" required><br>
    <label>Fecha de Nacimiento:</label><input type="text" name="fechaNacimiento" required><br>

    <input type="submit" value="Enviar"> <!-- Botón de envío del formulario -->
</form>

<%
    // Recuperamos el mensaje de error o el objeto Estudiante desde el contexto de la solicitud
    String error = (String) request.getAttribute("error");
    Estudiante est = (Estudiante) request.getAttribute("estudiante");

    // Si hay un mensaje de error, se muestra en la página
    if (error != null) {
%>
<p style="color:red;"><%= error %></p> <!-- Mensaje de error en color rojo -->
<%
} else if (est != null) { // Si hay un objeto Estudiante válido
%>
<h3>Datos del Estudiante</h3> <!-- Sección para mostrar los datos del estudiante -->
<table border="1"> <!-- Tabla de datos -->
    <tr><td>Carnet</td><td><%= est.getCarnet() %></td></tr>
    <tr><td>Nombres</td><td><%= est.getNombres() %></td></tr>
    <tr><td>Apellidos</td><td><%= est.getApellidos() %></td></tr>
    <tr><td>Dirección</td><td><%= est.getDireccion() %></td></tr>
    <tr><td>Teléfono</td><td><%= est.getTelefono() %></td></tr>
    <tr><td>Email</td><td><%= est.getEmail() %></td></tr>
    <tr><td>Fecha Nacimiento</td><td><%= est.getFechaNacimiento() %></td></tr>
</table>
<%
    } // Fin de la condición
%>
</body>
</html>