<p align="center">
  <img src="https://images.unsplash.com/photo-1450101499163-c8848c66ca85?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80" alt="Quotes Backend" width="100%" style="border-radius: 10px;"/>
</p>

<h1 align="center">
  📝 Quotes Backend API
</h1>

<p align="center">
  <em>Backend REST API para gestión de citas y frases motivacionales</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" alt="Java 17"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot" alt="Spring Boot 3"/>
  <img src="https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql" alt="PostgreSQL 15"/>
  <img src="https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker" alt="Docker"/>
</p>

## 🚀 Tecnologías

- **Java 17** (Eclipse Temurin)
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL 15**
- **Docker & Docker Compose**
- **Maven 3.9.2**
- **Swagger/OpenAPI 3** (Documentación interactiva)
- **Bean Validation** (jakarta.validation)

## 📋 Características

- ✅ CRUD completo de citas motivacionales
- ✅ Validación de datos con Bean Validation
- ✅ Manejo global de excepciones
- ✅ Respuestas estandarizadas con `ApiResponse<T>`
- ✅ IDs únicos con UUID
- ✅ Documentación interactiva con Swagger UI
- ✅ Contenerización con Docker multi-stage
- ✅ DTOs para transferencia de datos
- ✅ Arquitectura en capas (Controller → Service → Repository)

## 🏗️ Estructura del Proyecto

```
quotes-backend/
├── src/main/java/com/victor/quotes/quotes_backend/
│   ├── controller/       # Endpoints REST (@RestController)
│   │   └── QuoteController.java
│   ├── service/          # Lógica de negocio (@Service)
│   │   └── QuoteService.java
│   ├── repository/       # Acceso a datos (JPA)
│   │   └── QuoteRepository.java
│   ├── entity/           # Entidades JPA (@Entity)
│   │   └── Quote.java
│   ├── dto/              # Data Transfer Objects
│   │   ├── QuoteDTO.java
│   │   └── ApiResponse.java
│   ├── exception/        # Manejo global de excepciones
│   │   └── GlobalExceptionHandler.java
│   └── config/           # Configuraciones
│       └── SwaggerConfig.java
├── docker-compose.yml    # Orquestación de servicios
├── Dockerfile            # Imagen multi-stage
├── .env                  # Variables de entorno
└── pom.xml               # Dependencias Maven
```

## 🗄️ Modelo de Datos

### Quote Entity
```java
{
  "id": "uuid",           // Generado automáticamente
  "q": "frase/cita",      // Máx 255 caracteres, no vacío
  "a": "autor",           // Máx 255 caracteres, no vacío
  "h": "html"             // Formato enriquecido, no vacío
}
```

### QuoteDTO (Data Transfer Object)
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "frase": "La vida es lo que pasa mientras estás ocupado haciendo otros planes.",
  "autor": "John Lennon",
  "html": "<blockquote>La vida es lo que pasa mientras estás ocupado...</blockquote>"
}
```

### ApiResponse<T> (Respuesta Estandarizada)
```json
{
  "message": "✅ Mensaje descriptivo de la operación",
  "data": { /* QuoteDTO o List<QuoteDTO> */ }
}
```

## 🔌 API Endpoints

| Método | Endpoint | Descripción | Status Code |
|--------|----------|-------------|-------------|
| `GET` | `/api/quotes` | Obtener todas las citas | 200 |
| `GET` | `/api/quotes/{id}` | Obtener cita por ID | 200, 404 |
| `POST` | `/api/quotes` | Crear nueva cita | 201, 400 |
| `PUT` | `/api/quotes/{id}` | Actualizar cita existente | 200, 400, 404 |
| `DELETE` | `/api/quotes/{id}` | Eliminar cita | 200, 404 |

### Ejemplo de Respuestas

**Éxito (GET todas las citas):**
```json
{
  "message": "✅ Citas obtenidas exitosamente",
  "data": [
    {
      "id": "123e4567-e89b-12d3-a456-426614174000",
      "frase": "La vida es lo que pasa mientras estás ocupado haciendo otros planes.",
      "autor": "John Lennon",
      "html": "<blockquote>La vida es lo que pasa...</blockquote>"
    }
  ]
}
```

**Éxito (POST crear cita):**
```json
{
  "message": "✅ Cita creada exitosamente",
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "frase": "El éxito es la suma de pequeños esfuerzos repetidos día tras día",
    "autor": "Robert Collier",
    "html": "<blockquote>El éxito es la suma...</blockquote>"
  }
}
```

**Error (Validación):**
```json
{
  "message": "❌ Error de validación",
  "data": {
    "frase": "La frase no puede estar vacía",
    "autor": "El autor no puede tener más de 255 caracteres"
  }
}
```

**Error (No encontrado):**
```json
{
  "message": "❌ Cita no encontrada",
  "data": null
}
```

## 🐳 Instalación y Ejecución

### Prerequisitos
- Docker 20+ y Docker Compose 2+
- Java 17 (para desarrollo local)
- Maven 3.9+ (para desarrollo local)
- Git

### Opción 1: Con Docker (Recomendado)

```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/quotes-backend.git
cd quotes-backend

# Crear archivo .env con las variables de entorno
cat > .env << EOF
# PostgreSQL Configuration
POSTGRES_DB=quotes_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=admin123

# Spring Boot Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/quotes_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=admin123
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
EOF

# Levantar los servicios
docker-compose up -d

# Verificar logs
docker-compose logs -f app
```

La API estará disponible en: `http://localhost:8080`  
Swagger UI: `http://localhost:8080/swagger-ui.html`

### Opción 2: Ejecución Local

```bash
# Asegúrate de tener PostgreSQL corriendo localmente
# Crea la base de datos
createdb quotes_db

# Configurar application.properties
# Ver sección de configuración más abajo

# Compilar el proyecto
./mvnw clean install

# Ejecutar
./mvnw spring-boot:run
```

## 📝 Configuración

### Archivo .env (Docker)

```env
# PostgreSQL Configuration
POSTGRES_DB=quotes_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=admin123

# Spring Boot Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/quotes_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=admin123
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
```

### docker-compose.yml

```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    env_file:
      - .env

  db:
    image: postgres:15
    ports:
      - "5432:5432"
    env_file:
      - .env
    volumes:
      - postgres-data:/var/lib/postgresql/data

volumes:
  postgres-data:
```

### Dockerfile (Multi-stage build)

```dockerfile
# Etapa de build con Maven + JDK
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de runtime solo con JDK
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Ventajas del multi-stage build:**
- ✅ Imagen final más pequeña (~200MB vs ~700MB)
- ✅ No incluye Maven ni código fuente en producción
- ✅ Mayor seguridad
- ✅ Builds más rápidos con caché de dependencias

### application.properties (desarrollo local)

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/quotes_db
spring.datasource.username=postgres
spring.datasource.password=admin123

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server
server.port=8080

# Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📚 Documentación API

### Swagger UI (Interactivo)
Accede a la documentación interactiva para probar los endpoints:

```
http://localhost:8080/swagger-ui.html
```

### OpenAPI JSON
```
http://localhost:8080/api-docs
```

**Características de la documentación:**
- 📖 Descripción detallada de cada endpoint
- 🎯 Ejemplos de request/response
- 🧪 Interfaz de prueba interactiva
- 📋 Schemas de todos los DTOs
- ✅ Códigos de estado HTTP documentados

**Configuración de Swagger:**
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quotes Backend API")
                        .description("API para gestionar citas y frases inspiradoras")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Victor")
                                .email("victor.trimpa1987@gmail.com")));
    }
}
```

## 🧪 Ejemplos de Uso

### Crear una nueva cita
```bash
curl -X POST http://localhost:8080/api/quotes \
  -H "Content-Type: application/json" \
  -d '{
    "frase": "El éxito es la suma de pequeños esfuerzos repetidos día tras día",
    "autor": "Robert Collier",
    "html": "<blockquote class=\"quote\"><p>El éxito es la suma de pequeños esfuerzos repetidos día tras día</p><footer>— Robert Collier</footer></blockquote>"
  }'
```

**Respuesta:**
```json
{
  "message": "✅ Cita creada exitosamente",
  "data": {
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "frase": "El éxito es la suma de pequeños esfuerzos repetidos día tras día",
    "autor": "Robert Collier",
    "html": "<blockquote class=\"quote\">...</blockquote>"
  }
}
```

### Obtener todas las citas
```bash
curl http://localhost:8080/api/quotes
```

### Obtener una cita por ID
```bash
curl http://localhost:8080/api/quotes/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

### Actualizar una cita
```bash
curl -X PUT http://localhost:8080/api/quotes/a1b2c3d4-e5f6-7890-abcd-ef1234567890 \
  -H "Content-Type: application/json" \
  -d '{
    "frase": "Frase actualizada",
    "autor": "Autor actualizado",
    "html": "<p>HTML actualizado</p>"
  }'
```

### Eliminar una cita
```bash
curl -X DELETE http://localhost:8080/api/quotes/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

## 🔍 Validaciones

El API incluye validaciones automáticas a nivel de entidad y servicio:

### Validaciones de Entity (Bean Validation)
```java
@Entity
public class Quote {
    @NotBlank(message = "La frase no puede estar vacía")
    @Size(max = 255, message = "La frase no puede tener más de 255 caracteres")
    private String q;
    
    @NotBlank(message = "El autor no puede estar vacío")
    @Size(max = 255, message = "El autor no puede tener más de 255 caracteres")
    private String a;
    
    @NotBlank(message = "El HTML no puede estar vacío")
    private String h;
}
```

### Validaciones de Servicio (Lógica de negocio)
- ✅ Campos no pueden estar vacíos después de `trim()`
- ✅ Validación de existencia en actualizaciones/eliminaciones
- ✅ Respuestas descriptivas con emojis (✅/❌)

### Manejo de Errores Global
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        // Manejo de errores de validación
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        // Manejo de errores generales
    }
}
```

### Ejemplo de respuesta de error
```json
{
  "message": "❌ Error de validación",
  "data": {
    "frase": "La frase no puede estar vacía",
    "autor": "El autor no puede tener más de 255 caracteres"
  }
}
```

**HTTP Status Codes:**
- `200 OK` - Operación exitosa (GET, PUT, DELETE)
- `201 Created` - Recurso creado exitosamente
- `400 Bad Request` - Error de validación
- `404 Not Found` - Recurso no encontrado
- `500 Internal Server Error` - Error del servidor

## 🛠️ Comandos Docker Útiles

```bash
# Detener servicios
docker-compose down

# Reconstruir imágenes
docker-compose up --build

# Ver logs en tiempo real
docker-compose logs -f

# Ver logs solo de la app
docker-compose logs -f app

# Ver logs solo de la base de datos
docker-compose logs -f db

# Reiniciar solo la app
docker-compose restart app

# Entrar al contenedor de la app
docker-compose exec app sh

# Entrar a PostgreSQL
docker-compose exec db psql -U postgres -d quotes_db

# Limpiar todo (incluyendo volúmenes)
docker-compose down -v

# Ver estado de los contenedores
docker-compose ps

# Ver uso de recursos
docker stats
```

## 🏛️ Arquitectura

### Patrón de Capas

```
┌─────────────────────────────────────┐
│         QuoteController             │  ← @RestController
│    (Capa de Presentación/API)      │     Maneja HTTP requests
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│          QuoteService               │  ← @Service
│      (Capa de Negocio)              │     Lógica de negocio
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│       QuoteRepository               │  ← @Repository
│    (Capa de Persistencia)           │     Acceso a datos
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│         PostgreSQL DB               │
│          (quotes_db)                │
└─────────────────────────────────────┘
```

### DTOs y Entities

- **Quote (Entity)**: Representa la tabla en la base de datos con nombres cortos (q, a, h)
- **QuoteDTO**: Interfaz de comunicación con nombres descriptivos (frase, autor, html)
- **ApiResponse<T>**: Wrapper genérico para todas las respuestas del API

## 📊 Base de Datos

### Esquema de tabla `quote`

```sql
CREATE TABLE quote (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    q VARCHAR(255) NOT NULL,  -- frase
    a VARCHAR(255) NOT NULL,  -- autor
    h TEXT NOT NULL           -- html
);
```

### Comandos SQL útiles

```sql
-- Ver todas las citas
SELECT * FROM quote;

-- Contar citas
SELECT COUNT(*) FROM quote;

-- Buscar por autor
SELECT * FROM quote WHERE a ILIKE '%Lennon%';

-- Limpiar tabla
TRUNCATE TABLE quote;
```

## 🎯 Próximas Características

- [ ] Sistema de categorías para las citas
- [ ] Endpoint para obtener cita aleatoria del día
- [ ] Filtros de búsqueda por autor/palabra clave
- [ ] Sistema de favoritos
- [ ] Autenticación y autorización (JWT)
- [ ] Rate limiting
- [ ] Paginación de resultados
- [ ] Cache con Redis
- [ ] Tests unitarios e integración
- [ ] CI/CD con GitHub Actions

## 🧪 Testing

### Pruebas con Swagger UI
1. Navegar a `http://localhost:8080/swagger-ui.html`
2. Expandir el endpoint deseado
3. Clic en "Try it out"
4. Completar los parámetros
5. Clic en "Execute"

### Pruebas con cURL
Ver sección "Ejemplos de Uso" más arriba

### Pruebas con Postman
Importar la colección desde: `http://localhost:8080/api-docs`

## 🐛 Troubleshooting

### El contenedor no inicia
```bash
# Ver logs detallados
docker-compose logs app

# Verificar que el puerto 8080 no esté ocupado
lsof -i :8080

# Verificar variables de entorno
docker-compose config
```

### Error de conexión a la base de datos
```bash
# Verificar que PostgreSQL esté corriendo
docker-compose ps

# Reiniciar servicios
docker-compose restart

# Verificar conectividad
docker-compose exec app ping db
```

### La base de datos está vacía después de reiniciar
```bash
# Verificar que el volumen existe
docker volume ls

# Para persistir datos, asegúrate de no usar
docker-compose down -v  # Este comando elimina volúmenes
```

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor

**Victor** - [victor.trimpa1987@gmail.com](mailto:victor.trimpa1987@gmail.com)

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📞 Soporte

Si tienes problemas o preguntas:
- 📧 Email: victor.trimpa1987@gmail.com
- 🐛 Issues: [GitHub Issues](https://github.com/tuusuario/quotes-backend/issues)

---

⭐ Si este proyecto te fue útil, dale una estrella en GitHub!

**Hecho con ❤️ por Victor**