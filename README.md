# Lab 9 - APIs REST en Compose: Consumo de Datos

Aplicación Android desarrollada con Jetpack Compose que consume datos de una API REST pública, aplicando arquitectura MVVM, gestión de estados reactiva y navegación entre pantallas.

**Curso:** Desarrollo Avanzado en Nuevas Plataformas
**Docente:** MSc. Norman Patrick Harvey Arce
**Universidad:** Universidad Nacional de San Agustín de Arequipa

## Descripción

La aplicación muestra un catálogo de razas de perros obtenidas desde la API pública [Dog CEO](https://dog.ceo/dog-api/), permitiendo buscar razas, marcarlas como favoritas y ver una galería de imágenes adicionales de cada raza en una pantalla de detalle.

## Funcionalidades

- Listado de razas de perros en grilla de dos columnas con imagen y nombre
- Búsqueda en tiempo real sobre los datos ya cargados
- Sistema de favoritos con filtro dedicado
- Pantalla de detalle con galería de imágenes adicionales por raza
- Gestión explícita de estados de red: Loading, Success y Error
- Botón de reintento en caso de fallo de conexión

## Arquitectura

El proyecto sigue el patrón MVVM con separación de responsabilidades en capas:

```
UI (Compose) → ViewModel → Repository → ApiService (Retrofit) → API REST
```

| Capa | Responsabilidad |
|---|---|
| `ui` | Pantallas Compose, presentación y captura de eventos |
| `viewmodel` | Gestión de estado con StateFlow, lógica de presentación |
| `repository` | Acceso centralizado a datos, intermediario entre ViewModel y API |
| `network` | Definición de endpoints REST con Retrofit |
| `model` | Data classes que representan las respuestas JSON |

## Tecnologías y librerías

- Kotlin
- Jetpack Compose
- Retrofit + Gson Converter
- Kotlin Coroutines
- Coil (carga de imágenes)
- Navigation Compose

## API utilizada

**Dog CEO API** — `https://dog.ceo/api/`

| Endpoint | Descripción |
|---|---|
| `GET /breeds/list/all` | Lista completa de razas disponibles |
| `GET /breed/{breed}/images/random` | Imagen aleatoria de una raza |
| `GET /breed/{breed}/images/random/8` | Ocho imágenes aleatorias de una raza |

> Nota: inicialmente se utilizó REST Countries API, pero fue descontinuada (v1-v4) y migrada a un modelo de autenticación con API key, por lo que se optó por Dog CEO al ser gratuita y sin necesidad de registro.

## Requisitos

- Android Studio (Hedgehog o superior)
- SDK mínimo: API 24
- Conexión a internet

## Cómo clonar y ejecutar el proyecto

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/CarlitoUwU/DANP-LAB09.git
   ```

2. Ingresar a la carpeta del proyecto:

   ```bash
   cd DANP-LAB09
   ```

3. Abrir la carpeta del proyecto en Android Studio (`File → Open`).
4. Esperar a que Gradle sincronice las dependencias automáticamente.
5. Conectar un dispositivo físico o iniciar un emulador con conexión a internet.
6. Ejecutar la aplicación con el botón **Run** (▶) o `Shift + F10`.

## Repositorio

[https://github.com/CarlitoUwU/DANP-LAB09](https://github.com/CarlitoUwU/DANP-LAB09)
