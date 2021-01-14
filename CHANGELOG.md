# Changelog
Todos los cambios de este proyecto estarán documentados en este archivo.

El formato está basado en [SemVer](https://semver.org/spec/v2.0.0.html).

## [4.4.0](https://github.com/TelefonicaED/lmsmailing-portlet/releases/tag/v4.4.0)

### Fixed

- #191633: Se sustituye la variable por la del curso cuando no es nulo.

- #189936: No debe de haber ningun job sin conditionStatus, para ello se ha establecido una validación para que no se puedan introducir valores nulos. 
		   Además se han corregido otros problemas con el componente y se ha refactorizado el código.
   
### Added

- #194918: Para los mailjobs se da funcionalidad a la fecha de inscripción, se basa en la fecha de inscripción del usuario en el curso y esos mensajes programados se ejecutan todos los dias.
		   Se calcula la fecha fin del curso teniendo en cuenta el allowFinishDate del courseResult.
		   
- #190466: Añadido envio de copia a relaciones sociales en los correos programados.

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
