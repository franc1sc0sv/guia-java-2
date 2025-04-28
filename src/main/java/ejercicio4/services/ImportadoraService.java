import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Importadora extends JFrame {
    // Listas para almacenar los datos
    private List<Cliente> clientes = new ArrayList<>();
    private List<Vehiculo> vehiculos = new ArrayList<>();
    
    // Componentes de la interfaz
    private JTextField txtNombre;
    private JComboBox<String> cmbSexo;
    private JComboBox<String> cmbMarca;
    private JTextField txtAnio;
    private JTextField txtPrecio;
    private JButton btnRegistrar;
    private JTable tblVentas;
    private JTable tblEstadisticas;
    private DefaultTableModel modelVentas;
    private DefaultTableModel modelEstadisticas;
    
    public Importadora() {
        // Configuración de la ventana principal
        setTitle("Sistema de Control de Ventas - Importadora de Vehículos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con layout de tipo BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel para el formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Venta"));
        
        // Componentes del formulario
        panelFormulario.add(new JLabel("Nombre completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);
        
        panelFormulario.add(new JLabel("Sexo:"));
        cmbSexo = new JComboBox<>(new String[]{"Seleccione", "Masculino", "Femenino"});
        panelFormulario.add(cmbSexo);
        
        panelFormulario.add(new JLabel("Marca:"));
        cmbMarca = new JComboBox<>(new String[]{"Seleccione", "Nissan", "Toyota", "Kia"});
        panelFormulario.add(cmbMarca);
        
        panelFormulario.add(new JLabel("Año (2000-2025):"));
        txtAnio = new JTextField();
        panelFormulario.add(txtAnio);
        
        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);
        
        panelFormulario.add(new JLabel(""));  // Espacio en blanco
        btnRegistrar = new JButton("Registrar Venta");
        panelFormulario.add(btnRegistrar);
        
        // Agregar acción al botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });
        
        // Panel para las tablas
        JPanel panelTablas = new JPanel(new GridLayout(2, 1, 0, 10));
        
        // Tabla de ventas
        modelVentas = new DefaultTableModel(
            new Object[]{"Cliente", "Sexo", "Marca", "Año", "Precio"}, 0
        );
        tblVentas = new JTable(modelVentas);
        JScrollPane scrollVentas = new JScrollPane(tblVentas);
        scrollVentas.setBorder(BorderFactory.createTitledBorder("Registro de Ventas"));
        panelTablas.add(scrollVentas);
        
        // Tabla de estadísticas
        modelEstadisticas = new DefaultTableModel(
            new Object[]{"Descripción", "Cantidad", "Valor Total"}, 0
        );
        tblEstadisticas = new JTable(modelEstadisticas);
        JScrollPane scrollEstadisticas = new JScrollPane(tblEstadisticas);
        scrollEstadisticas.setBorder(BorderFactory.createTitledBorder("Estadísticas"));
        panelTablas.add(scrollEstadisticas);
        
        // Agregar paneles al panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelTablas, BorderLayout.CENTER);
        
        // Agregar panel principal a la ventana
        add(panelPrincipal);
        
        // Inicializar tabla de estadísticas
        actualizarEstadisticas();
    }
    
    private void registrarVenta() {
        // Obtener datos del formulario
        String nombre = txtNombre.getText().trim();
        String sexo = cmbSexo.getSelectedItem().toString();
        String marca = cmbMarca.getSelectedItem().toString();
        String anioStr = txtAnio.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        
        // Validar que todos los campos estén completos
        if (nombre.isEmpty() || sexo.equals("Seleccione") || marca.equals("Seleccione") || 
            anioStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int anio = Integer.parseInt(anioStr);
            double precio = Double.parseDouble(precioStr);
            
            // Validar año
            if (anio < 2000 || anio > 2025) {
                JOptionPane.showMessageDialog(this, 
                    "El año debe estar entre 2000 y 2025", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar precio
            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El precio debe ser mayor que cero", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear objetos y agregarlos a las listas
            Cliente cliente = new Cliente(nombre, sexo);
            Vehiculo vehiculo = new Vehiculo(marca, anio, precio);
            
            clientes.add(cliente);
            vehiculos.add(vehiculo);
            
            // Agregar a la tabla de ventas
            modelVentas.addRow(new Object[]{
                cliente.getNombre(),
                cliente.getSexo(),
                vehiculo.getMarca(),
                vehiculo.getAnio(),
                String.format("$%.2f", vehiculo.getPrecio())
            });
            
            // Actualizar estadísticas
            actualizarEstadisticas();
            
            // Limpiar formulario
            limpiarFormulario();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Formato de número inválido", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarEstadisticas() {
        // Limpiar tabla de estadísticas
        modelEstadisticas.setRowCount(0);
        
        // Si no hay vehículos, no hay nada que mostrar
        if (vehiculos.isEmpty()) {
            return;
        }
        
        // Crear objeto de estadísticas
        Estadisticas stats = new Estadisticas(vehiculos);
        
        // Agregar estadísticas a la tabla
        modelEstadisticas.addRow(new Object[]{
            "Total de Nissan vendidos",
            stats.getTotalPorMarca("Nissan"),
            String.format("$%.2f", stats.getSumaTotalPorMarca("Nissan"))
        });
        
        modelEstadisticas.addRow(new Object[]{
            "Total de Toyota vendidos",
            stats.getTotalPorMarca("Toyota"),
            String.format("$%.2f", stats.getSumaTotalPorMarca("Toyota"))
        });
        
        modelEstadisticas.addRow(new Object[]{
            "Total de Kia vendidos",
            stats.getTotalPorMarca("Kia"),
            String.format("$%.2f", stats.getSumaTotalPorMarca("Kia"))
        });
        
        modelEstadisticas.addRow(new Object[]{
            "Vehículos entre 2000 y 2015",
            stats.getVehiculosEnRangoAnio(2000, 2015),
            "-"
        });
        
        modelEstadisticas.addRow(new Object[]{
            "Vehículos entre 2016 y 2025",
            stats.getVehiculosEnRangoAnio(2016, 2025),
            "-"
        });
    }
    
    private void limpiarFormulario() {
        txtNombre.setText("");
        cmbSexo.setSelectedIndex(0);
        cmbMarca.setSelectedIndex(0);
        txtAnio.setText("");
        txtPrecio.setText("");
        txtNombre.requestFocus();
    }
    
    // Clase interna para Cliente
    class Cliente {
        private String nombre;
        private String sexo;
        
        public Cliente(String nombre, String sexo) {
            this.nombre = nombre;
            this.sexo = sexo;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public String getSexo() {
            return sexo;
        }
    }
    
    // Clase interna para Vehiculo
    class Vehiculo {
        private String marca;
        private int anio;
        private double precio;
        
        public Vehiculo(String marca, int anio, double precio) {
            this.marca = marca;
            this.anio = anio;
            this.precio = precio;
        }
        
        public String getMarca() {
            return marca;
        }
        
        public int getAnio() {
            return anio;
        }
        
        public double getPrecio() {
            return precio;
        }
    }
    
    // Clase interna para Estadisticas
    class Estadisticas {
        private List<Vehiculo> vehiculos;
        
        public Estadisticas(List<Vehiculo> vehiculos) {
            this.vehiculos = vehiculos;
        }
        
        public int getTotalPorMarca(String marca) {
            int total = 0;
            for (Vehiculo v : vehiculos) {
                if (v.getMarca().equals(marca)) {
                    total++;
                }
            }
            return total;
        }
        
        public double getSumaTotalPorMarca(String marca) {
            double suma = 0;
            for (Vehiculo v : vehiculos) {
                if (v.getMarca().equals(marca)) {
                    suma += v.getPrecio();
                }
            }
            return suma;
        }
        
        public int getVehiculosEnRangoAnio(int anioInicio, int anioFin) {
            int total = 0;
            for (Vehiculo v : vehiculos) {
                if (v.getAnio() >= anioInicio && v.getAnio() <= anioFin) {
                    total++;
                }
            }
            return total;
        }
    }
    
    // Método principal
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Importadora().setVisible(true);
            }
        });
    }
}
