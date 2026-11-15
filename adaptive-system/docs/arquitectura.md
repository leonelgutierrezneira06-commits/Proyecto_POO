# Arquitectura de HelioControl

## Módulos Maven

| Módulo | Responsabilidad |
|---|---|
| `plugin-api` | Define `Strategy<I,O>`, `Context` y `Result<O>`. |
| `domain` | Contiene `EnergyInput`, `EnergyResult`, `DISPOSITIVO` y `REGISTRO`. |
| `adaptive-core` | Valida métricas, selecciona un plugin, ejecuta y comprueba el resultado. |
| `plugins-java` | Aporta ECO, Performance y el adaptador de la rama original. |
| `plugins-scala` | Implementa Adaptive en Scala 3. |
| `persistence` | Configura SQLite y ejecuta el CRUD de dispositivos y registros. |
| `launcher` | Carga la configuración, inicia el servidor local y coordina la consola y la API web. |

![Componentes](diagramas/componentes.svg)

## Selección y ejecución

`PluginLoader` lee los nombres configurados, comprueba la firma genérica y llama por Reflection al constructor público sin argumentos. El selector usa tres identificadores, `ECO_ENERGIA`, `performance` y `adaptive-scala`. El sistema detecta los errores de carga de plugins antes de abrir el panel.

`Monitor` valida la potencia entre 0 y 1 000 000 W, la carga entre 0 y 100 % y la temperatura entre −30 y 120 °C. El selector evalúa las reglas en orden. `EnergyController` comprueba que la potencia final y el ahorro sean coherentes con la entrada. A potencia cero, el ahorro porcentual es cero.

![Orden de decisión](diagramas/seleccion.svg)

ECO reutiliza los tramos de 30 %, 20 % y 10 % de la rama base. Con el umbral de selección incluido, la primera franja es la que usa el controlador. Performance aplica 5 % si la carga es menor de 90 % y la temperatura menor de 75 °C, dentro de su rango admitido. Adaptive calcula `min(35, 8 + (100 − carga) × 0.12 + max(0, temperatura − 55) × 0.45)` como porcentaje estimado.

![Clases del motor y los plugins](diagramas/clases.svg)

## Persistencia

![Modelo de datos](diagramas/datos.svg)

Cada ejecución guarda la entrada, la regla, el identificador del plugin, la acción y el resultado. Los registros conservan una copia del nombre y tipo del dispositivo. Al eliminar el equipo se anula la relación y se preservan sus ejecuciones. La edición de una ejecución implica reevaluar las métricas y registrar un resultado actualizado. La demostración completa se escribe en una sola transacción.

## API local

| Método | Ruta | Operación |
|---|---|---|
| GET | `/api/meta` | Consulta umbrales y escenarios. |
| GET, POST | `/api/dispositivos` | Lista o crea equipos. |
| PUT, DELETE | `/api/dispositivos/{id}` | Actualiza o elimina un equipo. |
| GET, POST | `/api/registros` | Lista o evalúa y crea ejecuciones. |
| PUT, DELETE | `/api/registros/{id}` | Reevalúa o elimina una ejecución. |
| POST | `/api/escenarios` | Ejecuta el escenario indicado por `id` o `todos`. |

## Integración del equipo

La rama de integración aporta el cálculo ECO, el adaptador, el inventario SQLite y los casos de prueba originales. La rama Guillermo aporta el punto de partida del plugin Scala y la implementación Performance compartida. La consolidación distribuye las clases en módulos y deja una aplicación ejecutable con el mismo motor en web y consola.

Para integrar el ZIP en el repositorio del equipo, cada integrante puede crear su rama desde la rama compartida, incorporar sus cambios, ejecutar `mvnw clean verify` y abrir un pull request. Las revisiones y el merge corresponden al repositorio del equipo.
