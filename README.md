# Proyecto: Gestión de Clientes y Mercados

Este proyecto es una aplicación para la gestión de **clientes** y **mercados**, permitiendo la creación, modificación y vinculación de ambos. Está desarrollada en **Java** utilizando **Spring Boot** y utiliza **PostgreSQL** como base de datos.

## Ambiente de prueba en la nube
Para la api es necesario agregar en el header x-client con el token especificado mas abajo y para swagger authorization también
- Dominio: https://decrypto-challenge.onrender.com
- Health check: https://decrypto-challenge.onrender.com/health
- Swagger: https://decrypto-challenge.onrender.com/swagger-ui/index.html#/
- Api-docs: https://decrypto-challenge.onrender.com/v3/api-docs
- x-client: 560a09e5-1e51-4267-bb7a-4e6de7a93804

## Funcionalidades principales
1. **CRUD de Clientes**: Crear, leer, actualizar y eliminar clientes.
2. **CRUD de Mercados**: Crear, leer, actualizar y eliminar mercados.
3. **Relación muchos a muchos**: Vinculación de clientes a mercados y viceversa.
4. **Autorización mediante API Key**: Autorización para acceder a los endpoints protegidos mediante una clave API.

## Tecnologías utilizadas
- Java 17
- Spring Boot
- PostgreSQL
- Docker
- Swagger con OpenApi para la documentación de la API
- Autenticación mediante API Key

## Requisitos previos
- **Java 17** instalado.
- **PostgreSQL** instalado y corriendo localmente o en un contenedor.
- **Base de datos** creada.
- **Maven** para gestionar las dependencias y compilar el proyecto.
- **Docker** (opcional) si se desea ejecutar el proyecto mediante contenedores.

## Variables de entorno necesarias
A continuación se detallan las variables de entorno necesarias para levantar el proyecto de forma local (cambiar los valores por los que correspondan en su ambiente local):

```bash
# Base de datos
DB_HOST=localhost:5432
DB_NAME=postgres
DB_USER=postgres
DB_PASS=password

# API Key para la autorización
X_CLIENT_DEFAULT=your-api-key-here
