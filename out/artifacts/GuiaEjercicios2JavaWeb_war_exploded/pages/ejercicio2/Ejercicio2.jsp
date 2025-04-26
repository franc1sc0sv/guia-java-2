<%@ page import="ejercicio2.beans.Empleado" %>
<%@ page import="ejercicio2.services.VacacionesService" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <titleßß>Cálculo de Vacaciones</titleßß>
</head>
<body>
<h1>Cálculo de Vacaciones</h1>

<form method="post">
    Nombre: <input type="text" name="nombre" required><br><br>
    Apellido: <input type="text" name="apellido" required><br><br>
    Salario: <input type="text" name="salario" required><br><br>
    Fecha de Ingreso (AAAA-MM-DD): <input type="text" name="fechaIngreso" required><br><br>
    <input type="submit" value="Calcular Vacaciones">
</form>

<hr>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        try {
            // Obtener los parámetros de la solicitud
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String salarioStr = request.getParameter("salario");
            String fechaIngresoStr = request.getParameter("fechaIngreso");

            // Validar si los campos están vacíos
            if (nombre == null || nombre.isEmpty() ||
                    apellido == null || apellido.isEmpty() ||
                    salarioStr == null || salarioStr.isEmpty() ||
                    fechaIngresoStr == null || fechaIngresoStr.isEmpty()) {
                out.println("<p style='color:red;'>Por favor complete todos los campos.</p>");
            } else {
                // Convertir los valores
                double salario = Double.parseDouble(salarioStr);
                LocalDate fechaIngreso = LocalDate.parse(fechaIngresoStr);

                // Crear el objeto Empleado
                Empleado empleado = new Empleado(nombre, apellido, salario, fechaIngreso);

                // Llamar al servicio para calcular los días de vacaciones
                VacacionesService vacacionesService = new VacacionesService();
                int diasVacaciones = vacacionesService.calcularDiasVacaciones(empleado);

                // Mostrar la información
                out.println("<h3>Información del Empleado</h3>");
                out.println("<p>Nombre y Apellido: " + empleado.getNombre() + " " + empleado.getApellido() + "</p>");
                out.println("<p>Salario: $" + empleado.getSalario() + "</p>");
                out.println("<p>Fecha de Ingreso: " + empleado.getFechaIngreso() + "</p>");
                out.println("<p><strong>Días de Vacaciones Correspondientes: " + diasVacaciones + " días</strong></p>");
            }
        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Error: El salario debe ser un número válido.</p>");
        } catch (DateTimeParseException e) {
            out.println("<p style='color:red;'>Error: El formato de la fecha debe ser AAAA-MM-DD.</p>");
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error al procesar la información: " + e.getMessage() + "</p>");
        }
    }
%>

</body>
</html>
