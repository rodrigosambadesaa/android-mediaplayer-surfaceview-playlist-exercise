# Enunciado original — MediaPlayer, SurfaceView y playlist

Práctica de Programación Multimedia y Dispositivos Móviles centrada en reproducción de vídeo remoto.

El ejercicio histórico solicita:

- declarar permiso `INTERNET`;
- utilizar un `SurfaceView` de 90 dp;
- reproducir secuencialmente dos vídeos remotos;
- asociar el `MediaPlayer` a la superficie mediante `setDisplay()` cuando esté disponible;
- configurar cada URL mediante `setDataSource()` y preparar con `prepareAsync()`;
- iniciar en `onPrepared()`;
- al terminar un vídeo, avanzar al siguiente desde `onCompletion()`, reutilizando el reproductor mediante `reset()`;
- liberar el `MediaPlayer` al finalizar la Activity.

Las URLs históricas se conservan solo como referencia porque pueden dejar de existir.

No existe una base de datos asociada.
