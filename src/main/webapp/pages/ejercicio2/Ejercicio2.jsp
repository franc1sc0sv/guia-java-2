<%@ page import="ejercicio2.beans.Empleado" %>
<%@ page import="ejercicio2.services.VacacionesService" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cálculo de Vacaciones</title>
    <!-- Tailwind CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <jsp:include page="/components/Libraries.jsp"/>
</head>
<body class="bg-gray-100 min-h-screen flex flex-col items-center p-6">

<jsp:include page="/components/FloatingHomeButton.jsp"/>

<h1 class="text-3xl font-bold mb-6">Cálculo de Vacaciones</h1>

<form method="post" class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
    <div class="mb-4">
        <label class="block mb-1 font-medium">Nombre:</label>
        <input type="text" name="nombre" required class="w-full border border-gray-300 rounded px-3 py-2">
    </div>
    <div class="mb-4">
        <label class="block mb-1 font-medium">Apellido:</label>
        <input type="text" name="apellido" required class="w-full border border-gray-300 rounded px-3 py-2">
    </div>
    <div class="mb-4">
        <label class="block mb-1 font-medium">Salario:</label>
        <input type="text" name="salario" required class="w-full border border-gray-300 rounded px-3 py-2">
    </div>
    <div class="mb-6">
        <label class="block mb-1 font-medium">Fecha de Ingreso (AAAA-MM-DD):</label>
        <input type="text" name="fechaIngreso" required class="w-full border border-gray-300 rounded px-3 py-2">
    </div>
    <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold px-4 py-2 rounded w-full">
        Calcular Vacaciones
    </button>
</form>

<hr class="my-8 w-full max-w-2xl">

<div class="w-full max-w-2xl">
    <%
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                // Obtener parámetros
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String salarioStr = request.getParameter("salario");
                String fechaIngresoStr = request.getParameter("fechaIngreso");

                // Validar campos vacíos
                if (nombre == null || nombre.isEmpty() ||
                        apellido == null || apellido.isEmpty() ||
                        salarioStr == null || salarioStr.isEmpty() ||
                        fechaIngresoStr == null || fechaIngresoStr.isEmpty()) {
                    out.println("<p class='text-red-500'>Por favor complete todos los campos.</p>");
                } else {
                    // Validaciones adicionales
                    if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") || !apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        out.println("<p class='text-red-500'>El nombre y apellido solo deben contener letras.</p>");
                    } else {
                        double salario = Double.parseDouble(salarioStr);
                        if (salario <= 0) {
                            out.println("<p class='text-red-500'>El salario debe ser mayor a 0.</p>");
                        } else {
                            LocalDate fechaIngreso = LocalDate.parse(fechaIngresoStr);
                            LocalDate hoy = LocalDate.now();
                            if (fechaIngreso.isAfter(hoy)) {
                                out.println("<p class='text-red-500'>La fecha de ingreso no puede ser en el futuro.</p>");
                            } else {
                                // Crear empleado y calcular vacaciones
                                Empleado empleado = new Empleado(nombre, apellido, salario, fechaIngreso);
                                VacacionesService vacacionesService = new VacacionesService();
                                int diasVacaciones = vacacionesService.calcularDiasVacaciones(empleado);

                                // Mostrar resultados
                                out.println("<div class='bg-white p-6 rounded-lg shadow-md'>");
                                out.println("<h3 class='text-2xl font-bold mb-4'>Información del Empleado</h3>");
                                out.println("<p><strong>Nombre y Apellido:</strong> " + empleado.getNombre() + " " + empleado.getApellido() + "</p>");
                                out.println("<p><strong>Salario:</strong> $" + empleado.getSalario() + "</p>");
                                out.println("<p><strong>Fecha de Ingreso:</strong> " + empleado.getFechaIngreso() + "</p>");
                                out.println("<p class='mt-4 text-green-600 font-semibold'><strong>Días de Vacaciones Correspondientes:</strong> " + diasVacaciones + " días</p>");
                                out.println("</div>");
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                out.println("<p class='text-red-500'>Error: El salario debe ser un número válido.</p>");
            } catch (DateTimeParseException e) {
                out.println("<p class='text-red-500'>Error: El formato de la fecha debe ser AAAA-MM-DD.</p>");
            } catch (Exception e) {
                out.println("<p class='text-red-500'>Error al procesar la información: " + e.getMessage() + "</p>");
            }
        }
    %>
</div>

</body>
</html>
