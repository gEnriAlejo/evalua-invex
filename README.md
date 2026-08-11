# evalua-invex
Repositorio de prueba de evaluación para invex

# 🚀 API de Empleados (Nombre de tu Proyecto)

Esta es una API REST desarrollada con Java y Spring Boot para el menejo de empleados

## 🛠️ Tecnologías Utilizadas

* **Java:** 17
* **Framework:** Spring Boot 4.x
* **Gestor de Dependencias:** Maven
* **Base de Datos:** MariaDB
* **Documentación:** Springdoc-openapi (Swagger)

## 📋 Requisitos Previos

Antes de empezar, asegúrate de tener instalado:
* **Java Development Kit (JDK):** Versión 17.
* **Maven:** Versión 3.9+ (opcional si usas el Maven Wrapper `./mvnw`).

## ⚙️ Configuración e Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/gEnriAlejo/evalua-invex.git
   cd evalua-invex
   ```

2. **Variables de Entorno:**
   Duplica el archivo de configuración o revisa el archivo `src/main/resources/application.properties` y configura tus credenciales de base de datos:
   ```properties
   
3. **Compilar el proyecto:**
   Descarga las dependencias y compila el código sin ejecutar los tests:
   ```bash
   ./mvnw clean package -DskipTests
   ```

## 🏃 Ejecución

Para iniciar la aplicación en tu entorno local, ejecuta:

```bash
./mvnw spring-boot:run
```

La API estará disponible en: `http://localhost:8000`

## 🧪 Pruebas (Testing)

Para ejecutar la suite de pruebas unitarias y de integración:

```bash
./mvnw test
```

## 📌 Endpoints Principales (Ejemplo)

Una vez encendido el servidor, puedes interactuar con los siguientes endpoints base:

* **Empleados:** `GET /employee`
* **Crear Empleado:** `POST /employee`
* **Actualizar Empleado:** `PUT /employee/{id}`
* **Eliminar Empleado:** `DELETE /employee/{id}`
* **Documentación Swagger:** `http://localhost:8000/swagger-ui/index.html`

## 🌳 Estructura del Proyecto

```text
src/main/java/com/example/test/
│
├── common/               # Recursos comunes
├── common/exception      # Excepciones comunes
├── controller/           # Controlador REST (Endpoints)
├── dtos/                 # Clases dto
├── records/              # Clases record
├── repository/           # Interfaces de acceso a datos (Spring Data JPA)
├── repository/model      # Entidades JPA (Base de datos)
└── services/             # Lógica de negocio

