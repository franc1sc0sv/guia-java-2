<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Contacto</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-md rounded-lg p-8 w-full max-w-md">
    <h1 class="text-2xl font-bold text-center text-green-600 mb-6">Buscar Contacto</h1>

    <c:if test="${not empty error}">
        <div class="bg-red-100 text-red-700 p-3 rounded mb-4">
                ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/ContactoServlet" method="get" class="space-y-4">
        <input type="hidden" name="action" value="buscar">

        <div>
            <label class="block text-gray-700 font-semibold mb-1">Nombre del contacto:</label>
            <input type="text" name="nombreBusqueda"
                   class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-green-400"
                   required
                   value="${not empty nombreBusqueda ? nombreBusqueda : ''}">
        </div>

        <div class="flex justify-between mt-6">
            <a href="${pageContext.request.contextPath}/index.jsp" class="text-green-600 hover:underline">← Volver</a>
            <button type="submit" class="bg-green-600 text-white px-6 py-2 rounded hover:bg-green-700 transition">Buscar</button>
        </div>
    </form>

    <c:if test="${not empty nombre && not empty telefono && not empty correo}">
        <div class="mt-8 bg-gray-50 p-4 rounded shadow">
            <h2 class="text-lg font-bold text-gray-800 mb-2">Resultado:</h2>
            <p><strong>Nombre:</strong> ${nombre}</p>
            <p><strong>Teléfono:</strong> ${telefono}</p>
            <p><strong>Correo:</strong> ${correo}</p>
        </div>
    </c:if>

    <c:if test="${not empty noEncontrado}">
        <div class="mt-8 bg-yellow-100 text-yellow-800 p-3 rounded">
            No se encontró ningún contacto con ese nombre.
        </div>
    </c:if>
</div>
</body>
</html>
