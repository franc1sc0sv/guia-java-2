<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, ejercicio3.beans.Contacto" %>
<%
    String accion = request.getParameter("accion");
    if (accion == null) {
        accion = (String) request.getAttribute("accion");
    }
    List<Contacto> contactos = (List<Contacto>) request.getAttribute("contactos");
    Contacto contacto = (Contacto) request.getAttribute("contacto");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agenda de Contactos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #2c3e50; }
        form { margin-bottom: 20px; }
        input, button { margin: 5px 0; padding: 5px; }
        ul { list-style-type: none; padding: 0; }
        li { margin: 5px 0; }
        .error { color: red; }
    </style>
</head>
<body>

<h1>Agenda de Contactos</h1>

<form method="get" action="<%= request.getContextPath() %>/ContactoServlet">
    <button type="submit" name="action" value="agregar">Agregar Contacto</button>
    <button type="submit" name="action" value="ver">Ver Contactos</button>
    <button type="submit" name="action" value="buscar">Buscar Contacto</button>
</form>

<hr>

<% if ("agregar".equals(accion)) { %>
<h2>Agregar Nuevo Contacto</h2>
<form method="post" action="<%= request.getContextPath() %>/ContactoServlet">
    <input type="hidden" name="action" value="agregar">
    Nombre: <input type="text" name="nombre" required><br>
    Teléfono: <input type="text" name="telefono" required><br>
    Correo: <input type="email" name="correo" required><br>
    <input type="submit" value="Agregar">
</form>
<% if (error != null) { %>
<p class="error"><strong><%= error %></strong></p>
<% } %>

<% } else if ("ver".equals(accion)) { %>
<h2>Lista de Contactos</h2>
<% if (contactos != null && !contactos.isEmpty()) { %>
<ul>
    <% for (Contacto c : contactos) { %>
    <li><strong><%= c.getNombre() %></strong> - <%= c.getTelefono() %> - <%= c.getCorreo() %></li>
    <% } %>
</ul>
<% } else { %>
<p>No hay contactos registrados.</p>
<% } %>

<% } else if ("buscar".equals(accion)) { %>
<h2>Buscar Contacto</h2>
<form method="post" action="<%= request.getContextPath() %>/ContactoServlet">
    <input type="hidden" name="action" value="buscarContacto">
    Nombre: <input type="text" name="nombre" required>
    <input type="submit" value="Buscar">
</form>

<% if (contacto != null) { %>
<h3>Resultado:</h3>
<p>Nombre: <%= contacto.getNombre() %><br>
    Teléfono: <%= contacto.getTelefono() %><br>
    Correo: <%= contacto.getCorreo() %></p>
<% } else if (error != null) { %>
<p class="error"><strong><%= error %></strong></p>
<% } %>

<% } else { %>
<p>Seleccione una acción arriba.</p>
<% } %>

</body>
</html>
