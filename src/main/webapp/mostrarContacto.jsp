<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ejercicio3.beans.Contacto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Contactos</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-xl rounded-lg p-8 w-full max-w-3xl">
    <h1 class="text-3xl font-bold text-center text-blue-600 mb-6">Lista de Contactos</h1>

     <c:if test="${not empty contactos}">
        <table class="w-full table-auto border-collapse border border-gray-300">
            <thead>
            <tr class="bg-blue-100">
                <th class="border px-4 py-2 text-left">Nombre</th>
                <th class="border px-4 py-2 text-left">Teléfono</th>
                <th class="border px-4 py-2 text-left">Correo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="contacto" items="${contactos}">
                <tr class="hover:bg-gray-50">
                    <td class="border px-4 py-2">${contacto.nombre}</td>
                    <td class="border px-4 py-2">${contacto.telefono}</td>
                    <td class="border px-4 py-2">${contacto.correo}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty contactos}">
        <p class="text-gray-600 text-center">No hay contactos disponibles.</p>
    </c:if>

    <div class="mt-6 text-center">
        <a href="${pageContext.request.contextPath}/index.jsp" class="text-blue-600 hover:underline">← Volver a la página principal</a>
    </div>
</div>
</body>
</html>

