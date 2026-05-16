# 🚗 API Sistema de Alquiler de Vehículos

API RESTful desarrollada con Spring Boot para la gestión de un sistema de alquiler de vehículos.

## 📋 Características Implementadas

### ✅ Arquitectura y Patrones
- **Patrón MVC**: Controller - Service - Repository
- **DTOs**: Desacoplamiento total entre API y entidades de base de datos
- **Lombok**: Reducción de código boilerplate
- **Gestión de Dependencias**: Maven con Spring Boot

### ✅ Entidades y Relaciones
- **Vehículo**: Entidad principal con información del vehículo
- **Reserva**: Entidad relacionada con Vehículo (Many-to-One)
- **Relación bidireccional** entre las entidades

### ✅ Endpoints CRUD Completos

#### Vehículos (`/api/vehiculos`)
- `GET /api/vehiculos` - Obtener todos los vehículos
- `GET /api/vehiculos/{id}` - Obtener vehículo por ID
- `GET /api/vehiculos/disponibles` - Obtener vehículos disponibles
- `GET /api/vehiculos/tipo/{tipo}` - Obtener vehículos por tipo
- `POST /api/vehiculos` - Crear nuevo vehículo
- `PUT /api/vehiculos/{id}` - Actualizar vehículo
- `DELETE /api/vehiculos/{id}` - Eliminar vehículo

#### Reservas (`/api/reservas`)
- `GET /api/reservas` - Obtener todas las reservas
- `GET /api/reservas/{id}` - Obtener reserva por ID
- `GET /api/reservas/vehiculo/{vehiculoId}` - Obtener reservas por vehículo
- `GET /api/reservas/estado/{estado}` - Obtener reservas por estado
- `POST /api/reservas` - Crear nueva reserva
- `PUT /api/reservas/{id}` - Actualizar reserva
- `PATCH /api/reservas/{id}/cancelar` - Cancelar reserva
- `PATCH /api/reservas/{id}/completar` - Completar reserva
- `DELETE /api/reservas/{id}` - Eliminar reserva

### ✅ Validaciones
Todos los DTOs incluyen validaciones completas:
- `@NotBlank`, `@NotNull` - Campos obligatorios
- `@Size` - Longitud de cadenas
- `@Pattern` - Formato de datos (placa, DPI, teléfono)
- `@Email` - Validación de correos electrónicos
- `@Min`, `@Max` - Rangos numéricos
- `@DecimalMin`, `@Digits` - Validación de decimales
- `@FutureOrPresent`, `@Future` - Validación de fechas

### ✅ Manejo de Errores
- Excepciones personalizadas (`ResourceNotFoundException`, `BadRequestException`)
- `GlobalExceptionHandler` con `@RestControllerAdvice`
- Respuestas de error estructuradas con detalles de validación
- Códigos HTTP apropiados (200, 201, 204, 400, 404, 500)

### ✅ Base de Datos H2
- Base de datos en memoria configurada
- Consola H2 habilitada en `/h2-console`
- DDL automático con `create-drop`
- SQL logging habilitado para debugging

## 🛠️ Tecnologías Utilizadas

- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Lombok**
- **Maven**

## 🚀 Instalación y Ejecución

### Prerrequisitos
- JDK 11 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar el repositorio**
```bash
git clone <url-del-repositorio>
cd grupo8-api-sistema-alquiler-vehiculos-poo-gt03
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

### Acceso a la Consola H2
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:vehiculosdb`
- Username: `sa`
- Password: *(dejar en blanco)*

## 📝 Modelo de Datos

### Vehículo
```json
{
  "id": 1,
  "placa": "ABC-123",
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2023,
  "color": "Blanco",
  "precioDiario": 250.00,
  "disponible": true,
  "tipo": "SEDAN"
}
```

**Tipos de Vehículo**: `SEDAN`, `SUV`, `CAMIONETA`, `DEPORTIVO`, `ECONOMICO`

### Reserva
```json
{
  "id": 1,
  "nombreCliente": "Juan Pérez",
  "dpiCliente": "1234567890123",
  "telefonoCliente": "12345678",
  "emailCliente": "juan.perez@email.com",
  "fechaInicio": "2025-11-10",
  "fechaFin": "2025-11-15",
  "costoTotal": 1250.00,
  "estado": "ACTIVA",
  "vehiculoId": 1,
  "vehiculoPlaca": "ABC-123",
  "vehiculoMarca": "Toyota",
  "vehiculoModelo": "Corolla"
}
```

**Estados de Reserva**: `ACTIVA`, `COMPLETADA`, `CANCELADA`

## 🧪 Pruebas con Postman

### Importar la Colección
1. Abrir Postman
2. Click en "Import"
3. Seleccionar el archivo: `postman/Sistema-Alquiler-Vehiculos.postman_collection.json`
4. La colección incluye:
   - ✅ 20+ casos de prueba
   - ✅ Tests automáticos para cada endpoint
   - ✅ Casos de éxito y error
   - ✅ Validación de respuestas
   - ✅ Variables de entorno configuradas

### Ejecutar las Pruebas
1. Asegurarse que la aplicación esté corriendo
2. En Postman, hacer click en la colección
3. Click en "Run" para ejecutar todos los tests
4. Ver los resultados de las pruebas automáticas

### Orden Recomendado de Ejecución
1. **Vehículos** → Crear, Obtener, Actualizar
2. **Reservas** → Crear, Obtener, Actualizar, Completar, Eliminar
3. **Vehículos** → Eliminar

## 📊 Lógica de Negocio Implementada

### Gestión de Vehículos
- ✅ Validación de placa única
- ✅ Control de disponibilidad automático
- ✅ Prevención de eliminación si tiene reservas activas
- ✅ Filtrado por tipo y disponibilidad

### Gestión de Reservas
- ✅ Cálculo automático del costo total
- ✅ Validación de fechas (fin > inicio)
- ✅ Validación de disponibilidad del vehículo
- ✅ Actualización automática de disponibilidad del vehículo
- ✅ Gestión de estados (Activa, Completada, Cancelada)
- ✅ Liberación de vehículo al completar o cancelar
- ✅ Prevención de eliminación de reservas activas

## 🔍 Ejemplos de Uso

### Crear un Vehículo
```bash
POST http://localhost:8080/api/vehiculos
Content-Type: application/json

{
  "placa": "ABC-123",
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2023,
  "color": "Blanco",
  "precioDiario": 250.00,
  "tipo": "SEDAN"
}
```

### Crear una Reserva
```bash
POST http://localhost:8080/api/reservas
Content-Type: application/json

{
  "nombreCliente": "Juan Pérez",
  "dpiCliente": "1234567890123",
  "telefonoCliente": "12345678",
  "emailCliente": "juan.perez@email.com",
  "fechaInicio": "2025-11-10",
  "fechaFin": "2025-11-15",
  "vehiculoId": 1
}
```

## 📁 Estructura del Proyecto

```
src/main/java/com/proyecto/apirenta/
├── ApiRentaApplication.java          # Clase principal
├── controller/                        # Controladores REST
│   ├── VehiculoController.java
│   └── ReservaController.java
├── service/                          # Lógica de negocio
│   ├── VehiculoService.java
│   └── ReservaService.java
├── repository/                       # Acceso a datos
│   ├── VehiculoRepository.java
│   └── ReservaRepository.java
├── model/                           # Entidades JPA
│   ├── Vehiculo.java
│   └── Reserva.java
├── dto/                            # Data Transfer Objects
│   ├── VehiculoDTO.java
│   └── ReservaDTO.java
└── exception/                      # Manejo de excepciones
    ├── ResourceNotFoundException.java
    ├── BadRequestException.java
    ├── ErrorResponse.java
    └── GlobalExceptionHandler.java
```

## ✨ Características Destacadas

1. **Desacoplamiento Total**: Los DTOs exponen/reciben datos sin exponer las entidades de BD
2. **Validaciones Completas**: Validaciones a nivel de campo y lógica de negocio
3. **Manejo de Errores Robusto**: Respuestas de error consistentes y descriptivas
4. **Tests Automatizados**: Colección de Postman con tests para todos los endpoints
5. **Documentación Clara**: Código bien documentado con JavaDoc
6. **Patrón Repository**: Consultas personalizadas y reutilizables
7. **Transacciones**: Operaciones atómicas con `@Transactional`

## 👥 Autores
