# 🚗 API de Sistema de Alquiler de Vehículos

## 📌 Descripción
Este proyecto consiste en el desarrollo de una **API REST** para gestionar un **Sistema de Alquiler de Vehículos**.  
Permite administrar **vehículos, clientes, agencias, reservas y pagos**, brindando operaciones CRUD completas y manejo de relaciones entre entidades.

El sistema está diseñado con **Spring Boot** y sigue la arquitectura en capas, integrando **Lombok, JPA (Hibernate) y DTOs** para mantener un código limpio, modular y mantenible.



## 🎯 Objetivos
- Poner en práctica conceptos de **Programación Orientada a Objetos (POO)**.
- Implementar una **API REST** con buenas prácticas de arquitectura.
- Usar **Spring Boot + Maven** como base del proyecto.
- Gestionar entidades con **Spring Data JPA** y una base de datos relacional.
- Documentar y probar endpoints con **Postman** y **JUnit**.


## 👥 Integrantes Equipo #8 
- **(Lider) Franklin Esteban Perez Fuentes - Carnet: PF24001 - Usuario de GitHub: PF24001**
- **Adriana Valeria Moreno Zetino - Carnet: MZ21014 - Usuario de GitHub: Mz21014**
- **José Israel Lemus Salguero - Carnet: LS24009 - Usuario de GitHub: LS24009-LS**
- **Josue Stanley Ruiz Gaitan - Carnet: RG24040 - Usuario de GitHub: Stanley-rg24040**
- **Rolando Estuardo Salguero Borja - Carnet: SB21023- Usuario de GitHub: sb21023**



## 🏗️ Entidades principales
- **Vehículo**: información de cada auto (placa, marca, modelo, estado, precio por día).
- **Cliente**: datos personales y de contacto.
- **Agencia**: sucursal que administra vehículos.
- **Reserva**: registro del alquiler de un vehículo por parte de un cliente.
- **Pago**: control de transacciones asociadas a reservas.



## 🔗 Relaciones principales
- Una **Agencia** tiene muchos **Vehículos** (1:N).  
- Un **Cliente** puede tener varias **Reservas** (1:N).  
- Un **Vehículo** puede estar en varias **Reservas** a lo largo del tiempo (1:N).  
- Una **Reserva** tiene un único **Pago** (1:1).  



## 📖 Casos de Uso (principales endpoints)
- **Vehículos**
  - Crear, actualizar, eliminar y listar vehículos.
  - Filtrar por disponibilidad y agencia.
- **Clientes**
  - Registrar clientes.
  - Consultar información.
- **Reservas**
  - Crear, cancelar y finalizar reservas.
- **Pagos**
  - Registrar pagos.
  - Consultar historial.
- **Agencias**
  - Administrar agencias y listar sus vehículos.



## ⚙️ Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3**
- **Maven** (gestión de dependencias)
- **Spring Data JPA** (persistencia)
- **Lombok** (reducción de boilerplate)
- **H2 / MySQL** (base de datos)
- **JUnit 5** (pruebas unitarias)
- **Postman** (pruebas de endpoints)



## 📂 Estructura del Proyecto

```

│   .gitignore             - Archivos/Carpetas que no se deben subir a Git
│   pom.xml                - Configuración de Maven y dependencias
│   README.md              - Documentación del proyecto
│
├───src/                                - Código fuente y recursos
│   ├───main/                           - Código principal de la aplicación
│   │   ├───java/com/proyecto/apirenta/
│   │   │   ├───controller              - Controladores REST (manejan peticiones HTTP)
│   │   │   ├───dto                     - Objetos de transferencia de datos (request/response)
│   │   │   ├───model                   - Entidades JPA (tablas de la BD)
│   │   │   ├───repository              - Interfaces JPA (acceso a datos)
│   │   │   └───service                 - Lógica de negocio (reglas del sistema)
│   │   └───resources/       
│   │           application.properties  - Configuración de la app (BD, puerto, etc.)
│   │
│   └───test/                           - Pruebas unitarias y de integración
│       ├───java/com/proyecto/apirenta/ - Clases de prueba con JUnit
│       └───resources/                  - Configuración y datos para pruebas
│
└───target/                             - Archivos generados automáticamente (build)
    ├───generated-sources/  
    │   └───annotations                 - Código generado por anotaciones (ej. Lombok)
    └───generated-test-sources/
        └───test-annotations            - Código generado para pruebas

```



## 🚀 Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/<tu-usuario>/vehiculos-rental-api.git
   ```
2. Entrar al directorio:
   ```bash
   cd vehiculos-renta-api
   ```
3. Compilar y ejecutar con Maven:
   ```bash
   mvn spring-boot:run
   ```
4. La API estará disponible en:
   ```
   http://localhost:8080
   ```


## 📅 Entregas
- **Entrega 1:** Diagramas UML + ER, Casos de uso, Repo base.  
- **Entrega 2:** Implementación parcial de la API (CRUD de 2 entidades + Postman).  
- **Entrega 3:** Implementación completa con relaciones, pruebas unitarias y documentación final.  

