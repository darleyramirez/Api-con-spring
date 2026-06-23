# Evolución de los servicios

Este documento resume los cambios hechos en los servicios del proyecto, explicando qué se modificó en cada paso y cómo evolucionaron los servicios de `Espacio`, `Usuario` y `Reserva`.

## 1) EspacioServicio

### Antes
- Estaba definido como `public class EspacioServicio`.
- Usaba paquetes inconsistentes (`com.example.PaseoAPP` en lugar de `com.example.PaseoApp`).
- Referenciaba un repositorio `IRepositorioEspacio` que no existía en el proyecto.
- La lógica estaba todo en una clase sin separación de contrato e implementación.

### Cambios realizados
- Creé `src/main/java/com/example/PaseoApp/Servicios/EspacioServicio.java` como una **interfaz**.
- Creé `src/main/java/com/example/PaseoApp/Servicios/EspacioServicioImpl.java` como la **implementación**.
- Reemplacé el repositorio inexistente por `EspaciosRepositorios`.
- Añadí validaciones claras:
  - `datos != null`
  - `nombre` obligatorio
  - `nombre` único
  - `descripción` obligatoria
  - `aforo > 0`
- Mantengo los métodos:
  - `guardarEspacioEnBD`
  - `modificarEspacioEnBD`
  - `eliminarEspacioEnBD`
  - `buscarEspaciosEnBD`

### Resultado
- Servicio organizado en contrato + implementación.
- Lógica más fácil de mantener.
- Valores inválidos devuelven excepciones HTTP correctas.

## 2) UsuarioServicio

### Antes
- Era una clase con errores de sintaxis y mezclaba validación con código roto.
- Tenía código inválido, como `Usuario usuarioguardUsuario.this.repositorioUsuario.save(datos);`.
- No había separación entre interfaz y la implementación.
- No se aplicaba el patrón de servicio consistente con otros servicios.

### Cambios realizados
- Reescribí `src/main/java/com/example/PaseoApp/Servicios/UsuarioServicio.java` como **interfaz**.
- Mantuve e integré `src/main/java/com/example/PaseoApp/Servicios/UsuarioServicioImpl.java` como la **implementación**.
- Añadí validaciones sólidas:
  - `datos != null`
  - `correo` obligatorio
  - `correo` único
  - `nombres` obligatorios
  - `contraseña` mínimo 6 caracteres
- En `modificarUsuarioEnBD`, validé también cuando el correo cambia para evitar duplicados.
- Implementé los métodos:
  - `guardarUsuarioEnBD`
  - `modificarUsuarioEnBD`
  - `eliminarUsuarioEnBD`
  - `buscarUsuariosEnBD`

### Resultado
- Ahora existe un contrato claro para el servicio de usuario.
- La lógica de negocio está dentro de la implementación.
- Se unifica el patrón con `EspacioServicio`.

## 3) ReservaServicio

### Antes
- Solo existía la clase `ReservaServicio`.
- No había interfaz ni separación de responsabilidades.
- El nombre del método de eliminación era inconsistente (`eliminarenBD`).

### Cambios realizados
- Creé `src/main/java/com/example/PaseoApp/Servicios/ReservaServicio.java` como **interfaz**.
- Creé `src/main/java/com/example/PaseoApp/Servicios/ReservaServicioImpl.java` como la **implementación**.
- Añadí validaciones de negocio:
  - `datos != null`
  - `fecha` obligatorio
  - `fecha` no puede ser en el pasado
  - `tiempo` obligatorio
- Implementé los métodos:
  - `guardarReservaEnBD`
  - `modificarReservaEnBD`
  - `eliminarReservaEnBD`
  - `buscarReservaEnBD`

### Resultado
- `ReservaServicio` sigue el mismo esquema modelo/servicio que `Espacio`.
- Las excepciones ahora son consistentes con el resto del proyecto.

## Diferencias principales entre los servicios

- Ahora todos los servicios usan el patrón `interface` + `Impl`.
- Se normalizó la validación de entrada en los métodos `guardar` y `modificar`.
- Se corrigieron nombres y paquetes erróneos.
- Los controladores pueden inyectar el servicio por interfaz, lo que facilita pruebas y mantenimiento.

## Archivos clave creados o modificados

- `src/main/java/com/example/PaseoApp/Servicios/EspacioServicio.java`
- `src/main/java/com/example/PaseoApp/Servicios/EspacioServicioImpl.java`
- `src/main/java/com/example/PaseoApp/Servicios/UsuarioServicio.java`
- `src/main/java/com/example/PaseoApp/Servicios/UsuarioServicioImpl.java`
- `src/main/java/com/example/PaseoApp/Servicios/ReservaServicio.java`
- `src/main/java/com/example/PaseoApp/Servicios/ReservaServicioImpl.java`
