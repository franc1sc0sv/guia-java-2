<h2>Agregar Nuevo Contacto</h2>
<form action="ContactoServlet" method="post">
  <input type="hidden" name="action" value="agregar">

  <label for="nombre">Nombre:</label>
  <input type="text" id="nombre" name="nombre" placeholder="Nombre del contacto" required>

  <label for="telefono">Teléfono:</label>
  <input type="text" id="telefono" name="telefono" placeholder="Teléfono" required>

  <label for="correo">Correo:</label>
  <input type="email" id="correo" name="correo" placeholder="Correo" required>

  <button type="submit">Agregar Contacto</button>
</form>

