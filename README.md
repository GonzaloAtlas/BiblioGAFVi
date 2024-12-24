# BiblioGAFVi

## Descripción del Proyecto
BiblioGAFVi es un catálogo de libros interactivo diseñado como parte de la ruta de especialización BackEnd del programa ONE (Oracle Next Education). Este proyecto tiene como objetivo proporcionar una herramienta que permita a los usuarios buscar, filtrar y visualizar información sobre libros y autores utilizando una API de libros, mientras refuerza conceptos clave del desarrollo BackEnd.

## Características Principales
- **Framework:** Spring Framework para gestionar la arquitectura del proyecto.
- **Integración de API:** Conexión con la API de Gutendex para obtener datos de libros.
- **Manipulación de JSON:** Uso de la biblioteca Jackson para procesar y transformar datos JSON.
- **Base de Datos:** Persistencia de datos con PostgreSQL y consultas realizadas con Spring Data JPA.
- **Interfaz:** Interacción desde la consola de Java utilizando IntelliJ IDEA.
- **Filtrado Personalizado:** Capacidad para buscar libros y autores de interés del usuario.

## Tecnologías Utilizadas
- **Lenguaje de programación:** Java
- **Framework:** Spring Framework
- **API:** Gutendex API
- **Bibliotecas:** Jackson para manejo de JSON
- **Base de datos:** PostgreSQL
- **IDE:** IntelliJ IDEA

## Conocimientos Aplicados
Durante el desarrollo del proyecto, se aplicaron y reforzaron conocimientos en:
- **Java:** Uso de lambdas y streams para manipulación de datos.
- **Spring Framework:** Desarrollo de aplicaciones robustas y estructuradas.
- **Persistencia de Datos:** Consultas y almacenamiento con Spring Data JPA.
- **Creación de API:** Implementación de una API y su integración con el Front End.

## Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:
- **Java 17 o superior**
- **PostgreSQL**
- **IntelliJ IDEA**
- **Maven**

## Configuración del Proyecto
1. Clona este repositorio:
   ```bash
   git clone https://github.com/GonzaloAtlas/BiblioGAFVi.git
   ```

2. Configura la base de datos PostgreSQL:
   - Crea una nueva base de datos para el proyecto.
   - Actualiza las credenciales de la base de datos en el archivo `application.properties` ubicado en `src/main/resources`.


## Uso del Proyecto
1. Desde la consola de Java en IntelliJ IDEA, puedes interactuar con el catálogo de libros:
   - Buscar libros por título o autor.
   - Filtrar libros según las preferencias del usuario.
   - Visualizar la información detallada de los libros obtenidos desde la API.

## Contribuciones
Si deseas contribuir a este proyecto, sigue estos pasos:
1. Realiza un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y haz un commit:
   ```bash
   git commit -m "Descripción de los cambios"
   ```
4. Sube tus cambios:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Abre un Pull Request en este repositorio.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

¡Gracias por explorar BiblioGAFVi! Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue en el repositorio.
