# Android MediaPlayer + SurfaceView playlist exercise

Modernización de una práctica de **Programación Multimedia y Dispositivos Móviles (DAM)** centrada en
`MediaPlayer`, `SurfaceView`, preparación asíncrona y reproducción secuencial.

## Origen de la práctica

El enunciado histórico pedía:

- permiso `INTERNET`;
- un `SurfaceView` de 90 dp;
- dos vídeos remotos del servidor de Tesdai;
- `setDisplay()` cuando la superficie estuviera disponible;
- `setDataSource()` + `prepareAsync()`;
- `start()` desde `onPrepared()`;
- avanzar al siguiente vídeo desde `onCompletion()`, reutilizando el `MediaPlayer` con `reset()`;
- `release()` al finalizar la Activity.

La disponibilidad actual de las URLs históricas no está garantizada. Se conservan como referencia didáctica,
pero la aplicación permite editar ambas URLs en tiempo de ejecución.

## Qué se ha modernizado

- Android 17 / API 37 como `compileSdk` y `targetSdk`.
- Java 17 y AndroidX.
- Gestión explícita del estado `SurfaceHolder`.
- `OnErrorListener` y reintento controlado.
- Botones de pausa/reanudación, siguiente vídeo y reintento.
- Lógica de índice aislada y testeable (`PlaylistCursor`).
- Liberación idempotente del reproductor.
- CI con compilación y tests unitarios.

## Uso

1. Abre el proyecto en Android Studio.
2. Ejecuta en un emulador o dispositivo con Internet.
3. Si las URLs históricas ya no sirven, sustituye los campos por MP4/H.264 accesibles mediante HTTPS.
4. Pulsa **Cargar playlist**.

## Nota histórica

Esta versión no pretende fingir que el proyecto Android original fue recuperado: se trata de una
**reconstrucción moderna y fiel del enunciado conservado**, con mejoras de robustez claramente separadas.
