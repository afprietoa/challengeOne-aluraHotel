# Alura-Hotel : Sistema de reserva del Hotel 

## Bases de datos

```java {.highlight .highlight-source-java .bg-black}
DROP DATABASE IF EXISTS alura_hotel;
CREATE DATABASE alura_hotel;

USE alura_hotel;

CREATE TABLE reserva(
id INT NOT NULL AUTO_INCREMENT,
fecha_entrada DATE NOT NULL,
fecha_salida DATE NOT NULL,
valor VARCHAR(50),
forma_de_pago VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE huesped(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(50) NOT NULL,
fecha_nacimiento DATE NOT NULL,
nacionalidad VARCHAR(50) NOT NULL,
telefono VARCHAR(50) NOT NULL,
id_reserva INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(id_reserva) REFERENCES reserva(id)
);

CREATE TABLE usuario(
nombre VARCHAR(50),
contrasena VARCHAR(50)
);
```
##  Interfaz Grafica de Usuario (GUI)

### Clases de la interfaz Grafica

- Registro Huesped
- Reservas views
- Menu Principal
- Menu Usuario
- Busqueda
- Exito
- Login


### Descripción

En el menú principal, encontrarás un botón de inicio de sesión que te permitirá iniciar sesión. Una vez que te hayas autenticado, se desplegará un menú de usuario con varias opciones, incluyendo la posibilidad de hacer una reserva. En el proceso de reserva, deberás proporcionar las fechas de Check In y Check Out. El sistema calculará automáticamente el costo en función de la tarifa diaria y te pedirá que elijas el método de pago (tarjeta de crédito, débito o efectivo). 

A continuación, deberás registrar tus datos como huésped, incluyendo tu nombre, apellido, fecha de nacimiento, nacionalidad y número de contacto. Además, se te asignará un número de reserva que estará vinculado a tu perfil. El sistema te notificará una vez que hayas completado la operación, ya sea con un mensaje de éxito o de error.

Después de realizar esta operación, regresarás al menú principal, donde podrás realizar otra reserva o utilizar la función de búsqueda para localizar reservas previas. La función de búsqueda te permitirá filtrar las reservas según diversos parámetros, editarlas o cancelarlas desde una lista. También tendrás la opción de cerrar sesión para autenticarte con otro usuario si lo deseas.

## Flujo de navegación

En general, en aplicaciones Java Swing, cambiar de una vista a otra puede hacerse de varias maneras, como usando CardLayout, mostrando y ocultando paneles, o utilizando ventanas (frames) separadas y controlando su visibilidad.

## Clase Reservas views

### ReservasView -> MenuPrincipal:
Se da cuando el usuario hace clic en el botón "btnexit". La acción lleva al usuario a la vista del Menú Principal.

```java {.highlight .highlight-source-java .bg-black}
btnexit.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        MenuPrincipal principal = new MenuPrincipal();
        principal.setVisible(true);
        dispose();
    }
});
```

### ReservasView -> MenuUsuario:
Se da cuando el usuario hace clic en el botón "btnAtras". Esta acción lleva al usuario a la vista del Menú Usuario.

```java {.highlight .highlight-source-java .bg-black}
btnAtras.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        MenuUsuario usuario = new MenuUsuario();
        usuario.setVisible(true);
        dispose();                
    }
});
```

### ReservasView -> RegistroHuesped:
Esto ocurre cuando el usuario hace clic en "btnsiguiente" (asumiendo que todas las condiciones requeridas se cumplen). Esta acción lleva al usuario a la vista RegistroHuesped.

```java {.highlight .highlight-source-java .bg-black}
btnsiguiente.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {		
            guardarReserva();
        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
        }
    }						
});

private void guardarReserva(){
    // ...
    RegistroHuesped registro = new RegistroHuesped(reserva.getId());
    registro.setVisible(true);
    dispose();
    // ...
}
```

## Clase Registro Huesped

### RegistroHuesped -> ReservasView:
Esto ocurre cuando el usuario hace clic en "btnsiguiente" (asumiendo que todas las condiciones requeridas se cumplen). Esta acción lleva al usuario a la vista RegistroHuesped.

```java {.highlight .highlight-source-java .bg-black}
JPanel btnAtras = new JPanel();
btnAtras.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        ReservasView reservas = new ReservasView();
        reservas.setVisible(true);
        dispose();				
    }
});
```

### Cerrar la ventana RegistroHuesped

```java {.highlight .highlight-source-java .bg-black}
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
```

## Clase Menu Usuario

### MenuUsuario -> Reservas:
Esto ocurre cuando el usuario hace clic en "btnRegistro" (asumiendo que todas las condiciones requeridas se cumplen). Esta acción lleva al usuario a la vista Reservas.

```java {.highlight .highlight-source-java .bg-black}
JPanel btnRegistro = new JPanel();
btnRegistro.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        ReservasView reservas = new ReservasView();
        reservas.setVisible(true);
        dispose();
    }
});
```

### MenuUsuario -> Búsqueda:
Similar al anterior, el botón btnBusqueda es el desencadenante. Al hacer clic en este botón, se crea e inicializa un nuevo objeto Busqueda, que representa la vista de Búsqueda. Luego, se hace visible esta nueva vista y se cierra la vista actual (MenuUsuario). Esto indica que el flujo es:

```java {.highlight .highlight-source-java .bg-black}
JPanel btnBusqueda = new JPanel();
btnBusqueda.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Busqueda busqueda = new Busqueda();
        busqueda.setVisible(true);
        dispose();
    }
});
```

### Cerrar la ventana MenuUsuario

```java {.highlight .highlight-source-java .bg-black}
JPanel btnexit = new JPanel();
btnexit.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(0);
    }
});
```

## Clase MenuPrincipal

### MenuPrincipal -> Login:
 al hacer clic en el panel btnLogin, se crea una nueva instancia de la clase Login, se hace visible y se cierra la ventana del menú principal con dispose().

```java {.highlight .highlight-source-java .bg-black}
JPanel btnLogin = new JPanel(); 
btnLogin.setBounds(754, 300, 83, 70);
btnLogin.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }
});
```

### Cerrar la ventana MenuPrincipal

```java {.highlight .highlight-source-java .bg-black}
JPanel btnexit = new JPanel();
btnexit.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(0);
    }
    //... otros métodos ...
});
```

## Clase Exito

### Exito -> MenuUsuario
Cuando el usuario presiona el botón "OK" en el diálogo de éxito, la aplicación cierra el diálogo y abre la vista de MenuUsuario.

```java {.highlight .highlight-source-java .bg-black}
JButton okButton = new JButton("OK");
okButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        dispose();//sirve para cerrar la ventana actual
        MenuUsuario usuario = new MenuUsuario(); 
        usuario.setVisible(true);
    }
});
```

## Clase Busqueda

### Busqueda -> MenuUsuario
Aquí, cuando se hace clic en el botón btnAtras, se cierra la vista actual (dispose()) y se muestra la vista MenuUsuario.

```java {.highlight .highlight-source-java .bg-black}
JPanel btnAtras = new JPanel();
btnAtras.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        MenuUsuario usuario = new MenuUsuario();
        usuario.setVisible(true);
        dispose();				
    }
    ...
});
```

### Cerrar la ventana Busqueda

```java {.highlight .highlight-source-java .bg-black}
JPanel btnexit = new JPanel();
btnexit.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        MenuUsuario usuario = new MenuUsuario();
        usuario.setVisible(true);
        dispose();
    }
    ...
});
```

### Boton Buscar
al hacer clic en el botón btnbuscar, se realiza una búsqueda y se muestran los resultados, ya sea en la tabla de reservas o en la tabla de huéspedes.

```java {.highlight .highlight-source-java .bg-black}
JPanel btnbuscar = new JPanel();
btnbuscar.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        limpiarTabla();
        if(txtBuscar.getText().equals("")){
            visualizarTablaReservas();
            visualizarTablaHuespedes();
        }else{
            visualizarTablaReserva();
            visualizarTablaHuesped();
        }
    }
});
```

### Boton Editar
al hacer clic en el botón btnEditar, verifica qué fila de qué tabla está seleccionada. Si se selecciona una fila de la tabla de reservas (tbReservas), actualizará esa reserva. Si se selecciona una fila de la tabla de huéspedes (tbHuespedes), actualizará ese huésped.

```java {.highlight .highlight-source-java .bg-black}
JPanel btnEditar = new JPanel();
btnEditar.addMouseListener(new MouseAdapter(){
    public void mouseClicked(MouseEvent e){
        int filaReservas = tbReservas.getSelectedRow();
        int filaHuespedes = tbHuespedes.getSelectedRow();
        if(filaReservas >= 0){
            actualizarReserva();
            limpiarTabla();
            visualizarTablaReservas();
            visualizarTablaHuespedes();
        }else if(filaHuespedes >= 0){
            actualizarHuesped();
            limpiarTabla();
            visualizarTablaHuespedes();
            visualizarTablaReservas();
        }
    }
});
```
