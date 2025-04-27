<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Agregar Contacto</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-md rounded-lg p-8 w-full max-w-md">
  <h1 class="text-2xl font-bold text-center text-blue-600 mb-6">Agregar Nuevo Contacto</h1>

  <c:if test="${not empty error}">
    <div class="bg-red-100 text-red-700 p-3 rounded mb-4">
        ${error}
    </div>
  </c:if>

  <form action="${pageContext.request.contextPath}/ContactoServlet" method="post" class="space-y-4">
    <input type="hidden" name="action" value="agregar">

    <div>
      <label class="block text-gray-700 font-semibold mb-1">Nombre:</label>
      <input type="text" name="nombre" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" required>
    </div>

    <div>
      <label class="block text-gray-700 font-semibold mb-1">Teléfono:</label>
      <input type="text" name="telefono" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" required>
    </div>

    <div>
      <label class="block text-gray-700 font-semibold mb-1">Correo electrónico:</label>
      <input type="email" name="correo" class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400" required>
    </div>

    <div class="flex justify-between mt-6">
      <a href="${pageContext.request.contextPath}/index.jsp" class="text-blue-600 hover:underline">← Volver</a>
      <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700 transition">Guardar</button>
    </div>
  </form>
</div>
</body>
</html>
