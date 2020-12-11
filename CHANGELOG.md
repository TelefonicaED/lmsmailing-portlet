# Changelog
Todos los cambios de este proyecto estarán documentados en este archivo.

El formato está basado en [SemVer](https://semver.org/spec/v2.0.0.html).

## [4.3.1](https://github.com/TelefonicaED/lmsmailing-portlet/releases/tag/v4.3.1)

### Fixed

- #193892: No funcionaba el check de "Enviar correo de prueba" y enviaba el correo a todos los alumnos del curso

## [4.3.0](https://github.com/TelefonicaED/lmsmailing-portlet/releases/tag/v4.3.0)

### Added 

- #151401 Añadir la posibilidad de adjuntos en los correos dentro del curso.

## [4.2.0](https://github.com/TelefonicaED/lmsmailing-portlet/releases/tag/v4.2.0)

### Added 

- #171812 Se añade la opción de usar el nombre del alumno como variable en el envío del email
- #169695 Se añade a las preferencias del mailing la opción de activar los expandos de curso y de usuario como variables en el envío de emails
- #138540 Se pueden configurar desde el panel de control la cabecera y pie de los correos
- #177049 Nueva columna para plantilla en correos programados

### Fixed

 #179432 - Configuración de correos programados no funciona correctamente con la plataforma en catalán. Cambio comilla simple por comilla doble

## [4.1.0](https://github.com/TelefonicaED/lmsmailing-portlet/releases/tag/v4.1.0)

### Added

- #164221 Se añade a las preferencias del mailing la opción de activar el envío de email a supervisor, jefe

### Updated

- #172347 Se cambia el sanitizador del asunto del mail de HTML a TEXT_PLAIN

## [4.0.0](https://github.com/TelefonicaED/lmsmailing-portlet/releases/tag/v4.0.0)

### UPDATED 

- #164514 Sanitizado el asunto y el body del GroupMailing

## ADDED

- #164221 Opción de enviar copia de email a supervisores de alumnos
