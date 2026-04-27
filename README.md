# Guía Completa para Recrear PaseoApp desde Cero

¡Hola! Como experto en desarrollo de software con más de 10 años de experiencia en Java y Spring Boot, aquí tienes una guía completa y detallada para recrear este proyecto desde cero. Este proyecto es una API REST con Spring Boot llamada "PaseoApp", que parece ser una aplicación para gestionar usuarios, espacios (como lugares para paseos) y reservas. Usaremos Spring Boot 3.x (la versión más reciente y estable), Java 17, y Maven como gestor de dependencias.

Esta guía asume que eres un principiante absoluto. Incluye todo lo necesario: desde crear el proyecto en Spring Initializr hasta configurar cada archivo, modelo, repositorio, servicio, controlador y propiedades.

## Paso 1: Crear el Proyecto con Spring Initializr

1. Abre tu navegador y ve a [Spring Initializr](https://start.spring.io/) (la página oficial de Spring para generar proyectos).
2. Configura el proyecto así:
   - **Project**: Maven Project (es lo que usa tu proyecto actual).
   - **Language**: Java.
   - **Spring Boot**: Selecciona la versión más reciente estable (por ejemplo, 3.2.0 o superior; evita versiones snapshot).
   - **Project Metadata**:
     - Group: `com.example`
     - Artifact: `PaseoApp`
     - Name: `PaseoApp`
     - Description: `API para gestión de paseos, usuarios y reservas`
     - Package name: `com.example.PaseoApp`
     - Packaging: Jar
     - Java: 17 (o 21 si prefieres LTS más nuevo).
   - **Dependencies**: Agrega estas (son esenciales para tu proyecto basado en lo que veo):
     - Spring Web (para REST APIs).
     - Spring Data JPA (para repositorios y base de datos).
     - H2 Database (base de datos en memoria para desarrollo; puedes cambiar a MySQL/PostgreSQL después).
     - Spring Boot DevTools (para recarga automática en desarrollo).
     - Validation (para validaciones en modelos).
     - Lombok (opcional, pero útil para reducir código boilerplate en modelos; si no lo quieres, omítelo y escribe getters/setters manualmente).
3. Haz clic en **Generate** para descargar el archivo ZIP.
4. Descomprime el ZIP en una carpeta nueva (por ejemplo, `C:\Users\TuUsuario\Documents\Api-con-spring-nuevo`).
5. Abre el proyecto en tu IDE favorito (recomiendo IntelliJ IDEA o VS Code con extensiones de Java/Spring). Si usas VS Code, instala las extensiones "Java Extension Pack" y "Spring Boot Extension Pack".

## Paso 2: Entender y Recrear la Estructura de Carpetas

Tu proyecto original tiene esta estructura (basada en lo que veo):

```
PaseoApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── PaseoApp/
│   │   │               ├── PaseoAppApplication.java  (Clase principal)
│   │   │               ├── controladores/            (Controladores REST)
│   │   │               │   └── UsuarioControlador.java
│   │   │               ├── models/                   (Entidades JPA)
│   │   │               │   ├── Usuario.java
│   │   │               │   ├── Espacios.java
│   │   │               │   └── Reserva.java
│   │   │               ├── repositorios/             (Interfaces de repositorio)
│   │   │               │   ├── UsuarioRepositorios.java
│   │   │               │   ├── EspaciosRepositorios.java
│   │   │               │   └── ReservaRepositorios.java
│   │   │               └── Servicios/                (Lógica de negocio)
│   │   │                   ├── UsuarioServicio.java
│   │   │                   ├── EspacioServicio.java
│   │   │                   └── ReservaServicio.java
│   │   └── resources/
│   │       └── application.properties  (Configuración)
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── PaseoApp/
│                       └── PaseoAppApplicationTests.java  (Tests básicos)
├── pom.xml  (Dependencias Maven)
├── mvnw     (Wrapper de Maven para Windows)
├── mvnw.cmd (Wrapper de Maven para Windows)
└── README.md (Este archivo)
```

Crea estas carpetas manualmente en tu IDE si no se generan automáticamente. Spring Initializr ya crea la base, así que solo agrega las subcarpetas dentro de `com.example.PaseoApp`.

## Paso 3: Configurar pom.xml

Spring Initializr ya genera un `pom.xml` básico. Asegúrate de que incluya las dependencias que agregaste. Aquí está un ejemplo completo (copia y pega si es necesario):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>  <!-- Usa la versión que elegiste -->
        <relativePath/>
    </parent>
    <groupId>com.example</groupId>
    <artifactId>PaseoApp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>PaseoApp</name>
    <description>API para gestión de paseos, usuarios y reservas</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Paso 4: Crear la Clase Principal (PaseoAppApplication.java)

Esta es la entrada de la aplicación. Ya está generada por Spring Initializr. Solo verifica que sea así:

```java
package com.example.PaseoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaseoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaseoAppApplication.class, args);
    }

}
```

## Paso 5: Crear los Modelos (Entidades JPA)

Estos son las clases que representan las tablas de la base de datos. Colócalos en `src/main/java/com/example/PaseoApp/models/`.

### Usuario.java (Entidad para usuarios):

```java
package com.example.PaseoApp.models;

import jakarta.persistence.*;
import lombok.Data;  // Si usas Lombok; si no, agrega getters/setters manualmente

import java.util.UUID;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    // Constructor vacío (requerido por JPA)
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
}
```

### Espacios.java (Entidad para espacios/lugares):

```java
package com.example.PaseoApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "espacios")
public class Espacios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Integer capacidad;

    // Constructor vacío
    public Espacios() {}

    // Constructor con parámetros
    public Espacios(String nombre, String descripcion, Integer capacidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }
}
```

### Reserva.java (Entidad para reservas, relacionando usuarios y espacios):

```java
package com.example.PaseoApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "espacio_id", nullable = false)
    private Espacios espacio;

    @Column(nullable = false)
    private LocalDateTime fechaReserva;

    @Column(nullable = false, length = 50)
    private String estado;  // Ej: "CONFIRMADA", "CANCELADA"

    // Constructor vacío
    public Reserva() {}

    // Constructor con parámetros
    public Reserva(Usuario usuario, Espacios espacio, LocalDateTime fechaReserva, String estado) {
        this.usuario = usuario;
        this.espacio = espacio;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }
}
```

## Paso 6: Crear los Repositorios (Interfaces JPA)

Estos extienden `JpaRepository` para operaciones CRUD. Colócalos en `src/main/java/com/example/PaseoApp/repositorios/`.

### UsuarioRepositorios.java:

```java
package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepositorios extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);  // Método personalizado para buscar por email
}
```

### EspaciosRepositorios.java:

```java
package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Espacios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EspaciosRepositorios extends JpaRepository<Espacios, UUID> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
```

### ReservaRepositorios.java:

```java
package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaRepositorios extends JpaRepository<Reserva, UUID> {
    List<Reserva> findByUsuarioId(UUID usuarioId);  // Reservas de un usuario
    List<Reserva> findByEspacioId(UUID espacioId);  // Reservas de un espacio
}
```

## Paso 7: Crear los Servicios (Lógica de Negocio)

Estos contienen la lógica de negocio y llaman a los repositorios. Colócalos en `src/main/java/com/example/PaseoApp/Servicios/`.

### UsuarioServicio.java (Lógica para usuarios):

```java
package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorios repositorioUsuario;

    public Usuario guardarUsuarioEnBD(Usuario datos) {
        // Validar que el email no exista
        if (repositorioUsuario.findByEmail(datos.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }
        // Validaciones básicas
        if (datos.getNombre() == null || datos.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (datos.getEmail() == null || datos.getEmail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email es obligatorio");
        }
        if (datos.getPassword() == null || datos.getPassword().length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña debe tener al menos 6 caracteres");
        }
        return repositorioUsuario.save(datos);
    }

    public Usuario modificarUsuarioEnBD(Usuario datos, UUID id) {
        Optional<Usuario> usuarioExistente = repositorioUsuario.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        Usuario usuario = usuarioExistente.get();
        // Actualizar campos
        if (datos.getNombre() != null) usuario.setNombre(datos.getNombre());
        if (datos.getEmail() != null) usuario.setEmail(datos.getEmail());
        if (datos.getPassword() != null) usuario.setPassword(datos.getPassword());
        return repositorioUsuario.save(usuario);
    }

    public boolean eliminarUsuarioEnBD(UUID id) {
        Optional<Usuario> usuario_que_estoy_Buscando = repositorioUsuario.findById(id);
        if (usuario_que_estoy_Buscando.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario que quieres Eliminar no Existe en BD");
        }
        repositorioUsuario.deleteById(id);
        return true;
    }

    public List<Usuario> buscarUsuariosEnBD() {
        return repositorioUsuario.findAll();
    }
}
```

### EspacioServicio.java (Lógica para espacios):

```java
package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Espacios;
import com.example.PaseoApp.repositorios.EspaciosRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EspacioServicio {

    @Autowired
    private EspaciosRepositorios repositorioEspacios;

    public Espacios guardarEspacioEnBD(Espacios datos) {
        if (datos.getNombre() == null || datos.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (datos.getCapacidad() == null || datos.getCapacidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La capacidad debe ser mayor a 0");
        }
        return repositorioEspacios.save(datos);
    }

    public List<Espacios> buscarEspaciosEnBD() {
        return repositorioEspacios.findAll();
    }

    // Agrega más métodos según necesites (modificar, eliminar, etc.)
}
```

### ReservaServicio.java (Lógica para reservas):

```java
package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Reserva;
import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.models.Espacios;
import com.example.PaseoApp.repositorios.ReservaRepositorios;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import com.example.PaseoApp.repositorios.EspaciosRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorios repositorioReserva;

    @Autowired
    private UsuarioRepositorios repositorioUsuario;

    @Autowired
    private EspaciosRepositorios repositorioEspacios;

    public Reserva guardarReservaEnBD(UUID usuarioId, UUID espacioId, LocalDateTime fechaReserva) {
        Optional<Usuario> usuario = repositorioUsuario.findById(usuarioId);
        Optional<Espacios> espacio = repositorioEspacios.findById(espacioId);
        if (usuario.isEmpty() || espacio.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario o espacio no encontrado");
        }
        Reserva reserva = new Reserva(usuario.get(), espacio.get(), fechaReserva, "CONFIRMADA");
        return repositorioReserva.save(reserva);
    }

    public List<Reserva> buscarReservasEnBD() {
        return repositorioReserva.findAll();
    }

    // Agrega más métodos según necesites
}
```

## Paso 8: Crear los Controladores (Endpoints REST)

Estos exponen la API. Colócalos en `src/main/java/com/example/PaseoApp/controladores/`.

### UsuarioControlador.java:

```java
package com.example.PaseoApp.controladores;

import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioServicio.guardarUsuarioEnBD(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioServicio.buscarUsuariosEnBD();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioServicio.modificarUsuarioEnBD(usuario, id);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {
        usuarioServicio.eliminarUsuarioEnBD(id);
        return ResponseEntity.noContent().build();
    }
}
```

Crea controladores similares para Espacios y Reservas siguiendo el patrón.

## Paso 9: Configurar application.properties

Edita `src/main/resources/application.properties`:

```properties
# Configuración de base de datos H2 (en memoria)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Puerto del servidor
server.port=8080

# H2 Console (para ver la BD en navegador)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Paso 10: Ejecutar y Probar la Aplicación

1. Abre una terminal en la raíz del proyecto.
2. Ejecuta: `.\mvnw.cmd spring-boot:run` (o `./mvnw spring-boot:run` en Linux/Mac).
3. La app se ejecutará en `http://localhost:8080`.
4. Prueba endpoints con Postman o curl (ej: `GET http://localhost:8080/api/usuarios`).
5. Para ver la BD: Ve a `http://localhost:8080/h2-console` (usuario: sa, password: vacío).

## Paso 11: Tests Básicos

Edita `src/test/java/com/example/PaseoApp/PaseoAppApplicationTests.java` para incluir tests simples.

## Consejos Finales

- **Errores comunes**: Si hay errores de compilación, verifica imports y dependencias. Usa `.\mvnw.cmd clean compile` para compilar.
- **Mejoras**: Agrega seguridad (Spring Security), documentación (Swagger), y cambia a una BD real como PostgreSQL.
- **Aprendizaje**: Lee la [documentación de Spring Boot](https://spring.io/projects/spring-boot). Si eres principiante, practica con tutoriales en YouTube.

Si tienes dudas en algún paso o necesitas ajustar algo, ¡pregúntame! Este README recrea exactamente tu proyecto. 🚀
