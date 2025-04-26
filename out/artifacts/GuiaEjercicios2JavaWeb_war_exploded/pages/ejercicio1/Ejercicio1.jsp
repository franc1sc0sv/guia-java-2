<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ejercicio1.beans.Estudiante" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ficha de Estudiante</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 font-sans">

<div class="max-w-4xl mx-auto p-8 bg-white rounded-lg shadow-lg mt-10">
    <h2 class="text-3xl font-bold text-center text-blue-600 mb-6">Ficha de Estudiante</h2>

    <!-- Formulario para ingresar los datos del estudiante -->
    <form action="${pageContext.request.contextPath}/ejercicio1/submit" method="post" class="space-y-6">

        <!-- Campos de entrada del formulario -->
        <div>
            <label for="carnet" class="block text-lg font-medium text-gray-700">Carnet:</label>
            <input type="text" name="carnet" id="carnet" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Ej. AB1234">
        </div>

        <div>
            <label for="nombres" class="block text-lg font-medium text-gray-700">Nombres:</label>
            <input type="text" name="nombres" id="nombres" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div>
            <label for="apellidos" class="block text-lg font-medium text-gray-700">Apellidos:</label>
            <input type="text" name="apellidos" id="apellidos" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div>
            <label for="direccion" class="block text-lg font-medium text-gray-700">Dirección:</label>
            <input type="text" name="direccion" id="direccion" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div>
            <label for="telefono" class="block text-lg font-medium text-gray-700">Teléfono:</label>
            <input type="text" name="telefono" id="telefono" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Ej. 1234-5678">
        </div>

        <div>
            <label for="email" class="block text-lg font-medium text-gray-700">Email:</label>
            <input type="email" name="email" id="email" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div>
            <label for="fechaNacimiento" class="block text-lg font-medium text-gray-700">Fecha de Nacimiento:</label>
            <input type="text" name="fechaNacimiento" id="fechaNacimiento" required class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Ej. 01/01/2000">
        </div>

        <div class="flex justify-center">
            <button type="submit" class="px-6 py-3 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500">Enviar</button>
        </div>
    </form>

    <%
        // Recuperamos el mensaje de error o el objeto Estudiante desde el contexto de la solicitud
        String error = (String) request.getAttribute("error");
        Estudiante est = (Estudiante) request.getAttribute("estudiante");

        // Si hay un mensaje de error, se muestra en la página
        if (error != null) {
    %>
    <div class="mt-6 text-red-500 text-center text-lg">
        <%= error %>
    </div>
    <% } else if (est != null) { // Si hay un objeto Estudiante válido %>
    <!-- Se agrega un espacio entre el formulario y la tabla de datos -->
    <div class="mt-12">
        <h3 class="text-2xl font-semibold text-center text-green-600">Datos del Estudiante</h3>
        <table class="min-w-full table-auto mt-6 mx-auto bg-gray-50 border border-gray-200 rounded-lg shadow-md">
            <thead>
            <tr>
                <th class="px-4 py-2 text-left font-medium text-gray-700">Campo</th>
                <th class="px-4 py-2 text-left font-medium text-gray-700">Valor</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Carnet</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getCarnet() %></td>
            </tr>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Nombres</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getNombres() %></td>
            </tr>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Apellidos</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getApellidos() %></td>
            </tr>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Dirección</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getDireccion() %></td>
            </tr>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Teléfono</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getTelefono() %></td>
            </tr>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Email</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getEmail() %></td>
            </tr>
            <tr>
                <td class="px-4 py-2 font-medium text-gray-700">Fecha Nacimiento</td>
                <td class="px-4 py-2 text-gray-600"><%= est.getFechaNacimiento() %></td>
            </tr>
            </tbody>
        </table>
    </div>
    <% } %>
</div>

</body>
</html>
