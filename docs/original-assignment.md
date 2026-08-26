# Resumen del enunciado original

La práctica indica que al iniciar la aplicación deben reproducirse en bucle dos vídeos publicitarios remotos.
La interfaz utiliza un `SurfaceView` de 90 dp. `MainActivity` mantiene un `MediaPlayer`, el array de URLs,
un índice y las referencias a `SurfaceView`/`SurfaceHolder`.

Flujo pedido:

1. `surfaceCreated`: conectar el `SurfaceHolder` con `MediaPlayer`, asignar la fuente y ejecutar `prepareAsync()`.
2. `onPrepared`: ejecutar `start()`.
3. `onCompletion`: incrementar el índice, volver al inicio si se supera el array, llamar `reset()` y preparar el siguiente.
4. `onStop`: liberar recursos con `release()`.

URLs conservadas en el material:

- `https://tesdai.com/publicidad/endesa.mp4`
- `https://tesdai.com/publicidad/renaultcaptur.mp4`
