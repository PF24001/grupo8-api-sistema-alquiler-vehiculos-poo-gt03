<img width="1600" height="389" alt="image" src="https://github.com/user-attachments/assets/159d471f-063b-41f6-9f5c-4c65095fd823" />

- Universidad de El Salvador
- Facultad Multidiciplinaria de Occidente
- Programación Orientada a Objetos - GT03
- Proyecto de ciclo - Grupo #8

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



## ⚙️ Tecnologías a utilizar en todo el proyecto
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
root:
│   .gitignore
│   pom.xml
│   README.md
│   
├───Diagramas
│   │   DiagramaDeClases.wsd
│   │   DiagramaDeUsos.swd
│   │   DiagramaER.dbml
│   │   
│   └───png
│           DiagramaDeClases.png
│           DiagramaDeUsos.png
│           DiagramaER.png
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───proyecto
│   │   │           └───apirenta
│   │   │               ├───controller
│   │   │               ├───dto
│   │   │               ├───model
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │           application.properties
│   │
│   └───test
│       ├───java
│       │   └───com
│       │       └───proyecto
│       │           └───apirenta
│       └───resources
└───target
    ├───classes
    │   │   application.properties
    │   │
    │   └───com
    │       └───proyecto
    │           └───apirenta
    │               ├───controller
    │               ├───dto
    │               ├───model
    │               ├───repository
    │               └───service
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───proyecto
                └───apirenta
```


## Extensiones necesarias en VS Code

Para poder compilar y trabajar correctamente con el proyecto, asegúrate de instalar las siguientes extensiones:

- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) → Para compilar y ejecutar proyectos Java. 
- [DBML Previewer](https://marketplace.visualstudio.com/items?itemName=rizkykurniawan.dbml-previewer) → Para visualizar diagramas DBML.   
- [PlantUML](https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml) → Para visualizar diagramas UML.  

## Visualización de diagramas de clases y usos (.swd, .wsd)

Dentro del proyecto encontrarás los siguientes diagramas:

- `Diagramas\DiagramaDeClases.wsd`  
- `Diagramas\DiagramaDeUsos.swd`  

Para poder visualizar la **vista previa** de estos diagramas:  

1. Abre el archivo correspondiente (`.wsd` o `.swd`) en VS Code.  
2. Colócate sobre cualquier línea del código del diagrama.  
3. Presiona la combinación de teclas:  "Alt + D"

Esto abrirá la vista previa generada del diagrama.

## Visualización de diagrama ER (.dbml)

El proyecto también incluye el siguiente diagrama ER:  

- `Diagramas\DiagramaER.dbml`  

Para **compilar y visualizar** este diagrama:  

1. Abre el archivo `.dbml` en VS Code.  
2. Presiona la combinación de teclas:  "Ctrl + Shift + D"

Esto abrirá la vista previa del diagrama ER.  


## 📅 Entregas
- **Entrega 1:** Diagramas UML + ER, Casos de uso, Repo base ✅
- **Entrega 2:** Implementación parcial de la API (CRUD de 2 entidades + Postman).  
- **Entrega 3:** Implementación completa con relaciones, pruebas unitarias y documentación final.  
