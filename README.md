# Hotel Alura - Manual de Usuario

## Configuración Inicial

Antes de ejecutar la aplicación, asegúrate de haber ejecutado el siguiente script en tu MySQL Workbench :

```sql
-- MySQL Workbench Forward Engineering

-- ... (Otras configuraciones)

-- Schema hotel_alura
CREATE SCHEMA IF NOT EXISTS `hotel_alura` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hotel_alura` ;

-- Table reservas
CREATE TABLE IF NOT EXISTS `hotel_alura`.`reservas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fechaEntrada` DATE NULL DEFAULT NULL,
  `fechaSalida` DATE NULL DEFAULT NULL,
  `valor` DECIMAL(10,2) NULL DEFAULT NULL,
  `formaPago` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 56 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- Table huespedes
CREATE TABLE IF NOT EXISTS `hotel_alura`.`huespedes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `apellido` VARCHAR(255) NULL DEFAULT NULL,
  `fechaNacimiento` DATE NULL DEFAULT NULL,
  `nacionalidad` VARCHAR(255) NULL DEFAULT NULL,
  `telefono` VARCHAR(20) NULL DEFAULT NULL,
  `idReserva` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idReserva` (`idReserva` ASC) VISIBLE,
  CONSTRAINT `huespedes_ibfk_1`
    FOREIGN KEY (`idReserva`)
    REFERENCES `hotel_alura`.`reservas` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 21 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ... (Resto del script)
```

## Ejecución de la Aplicación

1. Abre el proyecto en Eclipse.

2. Ejecuta la clase `Main(1)` .

3. Accede a la página principal de la aplicación.

## Inicio de Sesión

Haz clic en "Login" e inicia sesión con las siguientes credenciales:
- Usuario: `admin`
- Contraseña: `admin`

## Menú Principal

Una vez iniciada la sesión, accederás al menú principal con tres opciones: "Registro de Reservas", "Búsqueda" o "Salir".

### Registro de Reservas

1. Selecciona la opción "Registro de Reservas" desde el menú principal.

2. Completa la información solicitada, como la fecha de ingreso, fecha de salida y la forma de pago.

3. Haz clic en el botón correspondiente para continuar.

4. Completa los datos personales del huésped y haz clic en "Guardar".

5. Los datos de la reserva y el huésped se almacenarán en la base de datos, y regresarás al menú principal.

### Búsqueda

1. Selecciona la opción "Búsqueda" desde el menú principal.

2. Gestiona los datos de reservas y clientes seleccionando la fila correspondiente en la tabla.

3. Haz clic en "Editar" o "Eliminar" según lo que desees hacer.

4. Puedes volver al menú de opciones haciendo clic en la "X" en la esquina superior derecha.

### Salir

Haz clic en "Salir" para cerrar definitivamente la aplicación o en "LOGIN" para regresar a la pantalla de inicio de sesión.

¡Listo! Ahora puedes gestionar las reservas y huéspedes de Hotel Alura utilizando esta sencilla aplicación. Ten en cuenta que esta aplicación utiliza MySQL como base de datos y ha sido desarrollada en Java utilizando Eclipse como entorno de desarrollo integrado.




