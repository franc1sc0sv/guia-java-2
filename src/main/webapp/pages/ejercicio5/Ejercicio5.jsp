<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ejercicio5.beans.Cliente" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Car Clean - Registro de Clientes</title>
    <jsp:include page="/components/Libraries.jsp"/>
</head>
<body class="bg-gray-100 min-h-screen flex flex-col items-center p-8">

<jsp:include page="/components/FloatingHomeButton.jsp"/>

<div class="w-full max-w-4xl bg-white rounded-lg shadow-md p-8">
    <h2 class="text-3xl font-bold mb-6 text-center text-blue-600">Registrar nuevo cliente</h2>

    <form action="${pageContext.request.contextPath}/ejercicio5/cliente" method="post" class="space-y-4">

        <div class="grid grid-cols-2 gap-4">
            <div>
                <label for="nombres" class="block font-semibold">Nombres:</label>
                <input id="nombres" type="text" name="nombres" class="w-full border rounded p-2">
            </div>
            <div>
                <label for="apellidos" class="block font-semibold">Apellidos:</label>
                <input id="apellidos" type="text" name="apellidos" class="w-full border rounded p-2">
            </div>
        </div>

        <div>
            <label class="inline-flex items-center">
                <input type="checkbox" name="vip" class="mr-2">
                Cliente VIP
            </label>
        </div>

        <h3 class="text-2xl font-semibold text-gray-700 mt-6">Datos del Vehículo</h3>

        <div class="grid grid-cols-3 gap-4">
            <div>
                <label for="marca" class="block font-semibold">Marca:</label>
                <input id="marca" type="text" name="marca" class="w-full border rounded p-2">
            </div>
            <div>
                <label for="modelo" class="block font-semibold">Modelo:</label>
                <input id="modelo" type="text" name="modelo" class="w-full border rounded p-2">
            </div>
            <div>
                <label for="year" class="block font-semibold">Año:</label>
                <input id="year" type="number" name="year" min="1900" max="2025" class="w-full border rounded p-2">
            </div>
        </div>

        <h3 class="text-2xl font-semibold text-gray-700 mt-6">Tipo de Servicio</h3>

        <div>
            <select name="idServicio" class="w-full border rounded p-2">
                <option value="">Seleccione un servicio</option>
                <option value="1">Motocicleta - $2.75</option>
                <option value="2">Sedan - $3.50</option>
                <option value="3">Camioneta - $4.00</option>
                <option value="4">Microbús - $5.00</option>
                <option value="5">Autobús - $7.00</option>
            </select>
        </div>

        <div class="flex justify-center">
            <button type="submit" class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-6 rounded mt-4">
                Registrar Cliente
            </button>
        </div>
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="w-full max-w-4xl bg-red-100 text-red-700 p-4 rounded-lg mt-4 text-center">
        <strong>Error:</strong> <%= error %>
    </div>
    <%
        }
    %>

</div>

<div class="w-full max-w-6xl mt-10">
    <h2 class="text-3xl font-bold mb-4 text-center text-green-600">Lista de Clientes Registrados</h2>

    <div class="overflow-x-auto bg-white shadow-md rounded-lg">
        <table class="min-w-full table-auto text-center">
            <thead class="bg-gray-200 text-gray-700 uppercase text-sm">
            <tr>
                <th class="py-2 px-4">Nombre Completo</th>
                <th class="py-2 px-4">VIP</th>
                <th class="py-2 px-4">Marca</th>
                <th class="py-2 px-4">Modelo</th>
                <th class="py-2 px-4">Año</th>
                <th class="py-2 px-4">Tipo Servicio</th>
                <th class="py-2 px-4">Precio Base</th>
                <th class="py-2 px-4">Total a Pagar</th>
            </tr>
            </thead>
            <tbody class="text-gray-700">

            <%

                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");

                if (clientes == null) {
                    try {
                        clientes = ejercicio5.services.ClienteService.listarClientes();
                    } catch (Exception e) {
                        clientes = new java.util.ArrayList<>();
                        e.printStackTrace();
                    }
                }

                if (!clientes.isEmpty()) {
                    for (Cliente cliente : clientes) {
            %>
            <tr class="border-b">
                <td class="py-2 px-4"><%= cliente.getNombres() %> <%= cliente.getApellidos() %>
                </td>
                <td class="py-2 px-4"><%= cliente.isVip() ? "Sí" : "No" %>
                </td>
                <td class="py-2 px-4"><%= cliente.getAutomotor().getMarca() %>
                </td>
                <td class="py-2 px-4"><%= cliente.getAutomotor().getModelo() %>
                </td>
                <td class="py-2 px-4"><%= cliente.getAutomotor().getYear() %>
                </td>
                <td class="py-2 px-4"><%= cliente.getServicio().getTipo() %>
                </td>
                <td class="py-2 px-4">$<%= cliente.getServicio().getPrecio() %>
                </td>
                <td class="py-2 px-4 font-bold">$<%= String.format("%.2f", cliente.totalPago()) %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="8" class="py-4">No hay clientes registrados aún.</td>
            </tr>
            <%
                }
            %>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>
