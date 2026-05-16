<img width="1600" height="389" alt="image" src="https://github.com/user-attachments/assets/159d471f-063b-41f6-9f5c-4c65095fd823" />

# 🚗 API de Sistema de Alquiler de Vehículos


### 🏫 Universidad de El Salvador  
**Facultad Multidisciplinaria de Occidente**  
**Asignatura:** Programación Orientada a Objetos - GT03  
**Proyecto de Ciclo - Grupo #8**



## 📌 Descripción

Este proyecto consiste en el desarrollo de una **API REST** para gestionar un **Sistema de Alquiler de Vehículos**, permitiendo administrar **vehículos y reservas**, con operaciones CRUD completas y manejo de relaciones entre entidades.

El sistema está diseñado con **Spring Boot** y sigue la arquitectura en capas **Controller-Service-Repository**, integrando **Lombok**, **JPA (Hibernate)** y **DTOs** para mantener un código limpio, modular y mantenible.



## 🎯 Objetivos

- Poner en práctica conceptos de **Programación Orientada a Objetos (POO)**.  
- Implementar una **API REST** con buenas prácticas de arquitectura.  
- Usar **Spring Boot + Maven** como base del proyecto.  
- Gestionar entidades con **Spring Data JPA** y una base de datos relacional.  
- Documentar y probar endpoints con **Postman** y **JUnit**.



## ✨ Estado Actual - Entrega 2 ✅

### ✅ Requisitos Implementados

#### 1. **Endpoints CRUD Completos para 2 Entidades Relacionadas**
- **Vehículo** y **Reserva** (relación Many-to-One)  
- Todos los métodos HTTP: GET, POST, PUT, DELETE, PATCH  
- Lógica funcional correcta con validaciones de negocio  

#### 2. **Uso de Lombok y DTOs**
- Lombok en todas las clases (reducción de código).  
- DTOs completamente desacoplados de las entidades.  
- Mapeo manual entre DTOs y entidades en servicios.  

#### 3. **Arquitectura Controller-Service-Repository**
- Separación clara de responsabilidades.  
- Inyección de dependencias con Spring.  
- Transacciones manejadas correctamente.  

#### 4. **Validaciones en DTOs**
- Anotaciones de validación (`@NotNull`, `@Size`, `@Pattern`, etc.).  
- Manejo global de errores de validación.  
- Respuestas estructuradas con detalles de error.  

#### 5. **Pruebas en Postman**
- Colección con más de 20 casos de prueba.  
- Tests automáticos por endpoint.  
- Casos de éxito y error cubiertos.  
- Variables de entorno configuradas.  

#### 6. **Base de Datos H2**
- Configuración completa con consola H2 (`/h2-console`).  
- Relaciones entre entidades implementadas.  



## 🏗️ Entidades Principales

- **Vehículo:** placa, marca, modelo, estado, precio por día, tipo, disponibilidad.  
- **Cliente:** datos personales y de contacto.  
- **Agencia:** sucursal que administra vehículos.  
- **Reserva:** registro de alquiler de vehículo.  
- **Pago:** control de transacciones de reservas.  



## 🔗 Relaciones Principales

- Una **Agencia** tiene muchos **Vehículos** (1:N).  
- Un **Cliente** puede tener varias **Reservas** (1:N).  
- Un **Vehículo** puede tener varias **Reservas** (1:N).  
- Una **Reserva** tiene un único **Pago** (1:1).  



## 👥 Integrantes Equipo #8

| Nombre | Carnet | Usuario GitHub | Rol |
|--------|---------|----------------|-----|
| **Franklin Esteban Pérez Fuentes** | PF24001 | [PF24001](https://github.com/PF24001) | Líder 
| **Adriana Valeria Moreno Zetino** | MZ21014 | [Mz21014](https://github.com/Mz21014) 
| **José Israel Lemus Salguero** | LS24009 | [LS24009-LS](https://github.com/LS24009-LS) 
| **Josue Stanley Ruiz Gaitán** | RG24040 | [Stanley-rg24040](https://github.com/Stanley-rg24040) 
| **Rolando Estuardo Salguero Borja** | SB21023 | [sb21023](https://github.com/sb21023) 



## ⚙️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|-------------|----------|------------|
| **Java** | 11 | Lenguaje base |
| **Spring Boot** | 2.7.18 | Framework principal |
| **Spring Data JPA** | 2.7.18 | Persistencia |
| **Lombok** | Latest | Reducción de código repetido |
| **H2 / MySQL** | Runtime | Base de datos |
| **JUnit 5** | Latest | Pruebas unitarias |
| **Maven** | 3.6+ | Gestión de dependencias |



## 📂 Estructura del Proyecto

```
C:.
|   .gitignore
|   DOCUMENTATION.md
|   pom.xml
|   README.md
|   
+---Diagramas
|   |   DiagramaDeClases.wsd
|   |   DiagramaDeUsos.swd
|   |   DiagramaER.dbml
|   |   
|   \---png
|           DiagramaDeClases.png
|           DiagramaDeUsos.png
|           DiagramaER.png
|
+---postman
|       Sistema-Alquiler-Vehiculos.postman_collection.json
|
+---src
|   \---main
|       +---java
|       |   \---com
|       |       \---proyecto
|       |           \---apirenta
|       |               |   ApiRentaApplication.java
|       |               |
|       |               +---controller
|       |               |       ReservaController.java
|       |               |       VehiculoController.java
|       |               |
|       |               +---dto
|       |               |       ReservaDTO.java
|       |               |       VehiculoDTO.java
|       |               |
|       |               +---exception
|       |               |       BadRequestException.java
|       |               |       ErrorResponse.java
|       |               |       GlobalExceptionHandler.java
|       |               |       ResourceNotFoundException.java
|       |               |
|       |               +---model
|       |               |       Reserva.java
|       |               |       Vehiculo.java
|       |               |
|       |               +---repository
|       |               |       ReservaRepository.java
|       |               |       VehiculoRepository.java
|       |               |
|       |               \---service
|       |                       ReservaService.java
|       |                       VehiculoService.java
|       |
|       \---resources
|               application.properties
|
\---target
    |   vehiculos-renta-api-0.0.1-SNAPSHOT.jar
    |   vehiculos-renta-api-0.0.1-SNAPSHOT.jar.original
    |
    +---classes
    |   |   application.properties
    |   |
    |   \---com
    |       \---proyecto
    |           \---apirenta
    |               |   ApiRentaApplication.class
    |               |
    |               +---controller
    |               |       ReservaController.class
    |               |       VehiculoController.class
    |               |
    |               +---dto
    |               |       ReservaDTO.class
    |               |       VehiculoDTO.class
    |               |
    |               +---exception
    |               |       BadRequestException.class
    |               |       ErrorResponse.class
    |               |       GlobalExceptionHandler.class
    |               |       ResourceNotFoundException.class
    |               |
    |               +---model
    |               |       Reserva$EstadoReserva.class
    |               |       Reserva.class
    |               |       Vehiculo$TipoVehiculo.class
    |               |       Vehiculo.class
    |               |
    |               +---repository
    |               |       ReservaRepository.class
    |               |       VehiculoRepository.class
    |               |
    |               \---service
    |                       ReservaService.class
    |                       VehiculoService.class
    |
    +---generated-sources
    |   \---annotations
    +---maven-archiver
    |       pom.properties
    |
    \---maven-status
        \---maven-compiler-plugin
            \---compile
                \---default-compile
                        createdFiles.lst
                        inputFiles.lst
```


## 🌐 Endpoints API

### 🚙 Vehículos (`/api/vehiculos`)
| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| GET | `/api/vehiculos` | Listar todos |
| GET | `/api/vehiculos/{id}` | Obtener por ID |
| GET | `/api/vehiculos/disponibles` | Filtrar por disponibilidad |
| POST | `/api/vehiculos` | Crear nuevo vehículo |
| PUT | `/api/vehiculos/{id}` | Actualizar |
| DELETE | `/api/vehiculos/{id}` | Eliminar (si no tiene reservas activas) |

### 📋 Reservas (`/api/reservas`)
| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| GET | `/api/reservas` | Listar todas |
| GET | `/api/reservas/{id}` | Obtener por ID |
| POST | `/api/reservas` | Crear nueva reserva |
| PUT | `/api/reservas/{id}` | Actualizar (solo si está activa) |
| PATCH | `/api/reservas/{id}/cancelar` | Cancelar reserva |
| PATCH | `/api/reservas/{id}/completar` | Completar reserva |
| DELETE | `/api/reservas/{id}` | Eliminar (si no está activa) |



## 🚀 Instalación y Ejecución

### Prerrequisitos
- JDK 11 o superior  
- Maven 3.6+  
- Postman  

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/PF24001/grupo8-api-sistema-alquiler-vehiculos-poo-gt03.git
cd grupo8-api-sistema-alquiler-vehiculos-poo-gt03

# 2. Compilar el proyecto
mvn clean install

# 3. Ejecutar la aplicación
mvn spring-boot:run
```

Accede a:
- **API:** [http://localhost:8080](http://localhost:8080)  
- **Consola H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)



## 🧪 Pruebas con Postman

1. Abrir Postman  
2. Importar la colección:  
   `postman/Sistema-Alquiler-Vehiculos.postman_collection.json`  
3. Correr los tests automáticos.

**Cobertura:**
- Más de 20 casos de prueba.  
- Casos de éxito (200, 201, 204).  
- Casos de error (400, 404).  
- Validaciones de campos y reglas de negocio.



## 📊 Diagramas Incluidos

- `Diagramas/DiagramaDeClases.wsd`  
- `Diagramas/DiagramaDeUsos.swd`  
- `Diagramas/DiagramaER.dbml`  

Visualízalos en VS Code con las extensiones:
- [PlantUML](https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml)  
- [DBML Previewer](https://marketplace.visualstudio.com/items?itemName=rizkykurniawan.dbml-previewer)



## 📅 Entregas

| Entrega | Descripción | Estado |
|----------|--------------|--------|
| 1 | Diagramas UML + ER, casos de uso, repo base | ✅ |
| 2 | CRUD de 2 entidades + DTOs + Postman + Validaciones | ✅ |
| 3 | Implementación completa + pruebas unitarias + documentación final | ⏳ |



