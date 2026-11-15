# HelioControl

Sistema autoadaptativo de control energético. La interfaz web local permite simular lecturas de un equipo, cambiar de estrategia según la carga y la temperatura, y guardar el resultado en SQLite. El proyecto usa Java 21, Scala 3.3, Reflection y Maven. Las reducciones son estimaciones de una simulación académica.

## Inicio rápido en Windows

1. Extrae el ZIP completo y entra a la carpeta `adaptive-system`.
2. Comprueba con `java -version` que tienes Java 21 o posterior. Para compilar usa el JDK 21.
3. Abre `Iniciar_HelioControl.cmd`. Si el navegador sigue cerrado, abre [http://127.0.0.1:8080](http://127.0.0.1:8080).
4. En **Resumen**, pulsa **Ejecutar 3 escenarios**. Cada ejecución se guarda en el historial.

El JAR incluido trae los controladores de SQLite y las dependencias de Java y Scala. El archivo `control_energetico.db` se crea en la raíz cuando arranca el programa. Puedes detener el servidor con Ctrl+C en su consola.

## Abrir y ejecutar en NetBeans

1. Ve a **File → Open Project** y abre la carpeta `adaptive-system`, que contiene el `pom.xml` padre.
2. Selecciona **HelioControl** como proyecto principal. Usa el JDK 21 para ejecutar Maven.
3. Haz clic derecho en **HelioControl → Run** o pulsa **F6**. La acción del proyecto compila los módulos, construye el JAR y arranca el panel. El puerto aparece en **Output**.
4. La primera compilación requiere acceso a Maven Central para descargar las dependencias. Si deseas compilar o arrancar desde una terminal, usa los comandos de abajo.

## Compilación, pruebas y consola

En PowerShell, desde `adaptive-system`:

```powershell
.\mvnw.cmd clean verify
java -jar launcher/target/launcher-1.0.0-app.jar --abrir
java -jar launcher/target/launcher-1.0.0-app.jar --demo
```

En Linux o macOS, ejecuta `./mvnw clean verify`. El informe de cobertura agregado queda en `launcher/target/site/jacoco-aggregate/index.html`. Cada módulo con pruebas genera sus resultados en `target/surefire-reports`. Las pruebas usan bases temporales.

La opción `--demo` guarda tres ejecuciones y las muestra en la consola. Cada vez que ejecutas la demostración, se añaden tres registros nuevos.

## Escenarios con la configuración incluida

| Escenario | Entrada | Plugin elegido | Potencia final | Ahorro estimado |
|---|---|---|---:|---:|
| Operación ligera | 500 W, 30 %, 40 °C | ECO (Java) | 350 W | 30 % |
| Demanda elevada | 750 W, 80 %, 68 °C | Performance (Java) | 712,50 W | 5 % |
| Presión térmica | 900 W, 82 %, 91 °C | Adaptive (Scala) | 662,76 W | 26,36 % |

La temperatura de 85 °C o superior tiene prioridad. Por debajo de ese umbral, Performance se aplica desde 70 % de carga cuando el plugin admite la temperatura. ECO se aplica con carga menor de 40 %. Adaptive cubre el ajuste intermedio y los casos térmicos.

## Configuración local

| Variable | Valor predeterminado | Uso |
|---|---|---|
| `HELIO_PORT` | `8080` | Puerto del servidor local |
| `HELIO_DB` | `control_energetico.db` | Ruta al archivo SQLite |
| `HELIO_CONFIG` | `config/config.json` | Reglas y clases de plugin |

Para cambiar el puerto en PowerShell, ejecuta `$env:HELIO_PORT = "8090"` antes de iniciar el JAR desde esa consola. Si el JSON predeterminado no está junto al proyecto, el JAR usa la copia integrada. Si quieres usar los equipos de una base anterior, cierra la aplicación y apunta `HELIO_DB` a una copia del archivo original. El inicializador conserva la tabla de dispositivos y agrega el esquema de historial.

## Archivos de entrega

- `db/schema.sql` y `config/config.json` contienen el esquema y las reglas.
- `docs/arquitectura.md` describe los módulos, los flujos y la API.
- `docs/diagramas` incluye diagramas de componentes, selección y datos.
- `docs/cobertura/index.html` abre el informe JaCoCo de esta entrega.
- `Doxyfile` permite regenerar la documentación con `doxygen Doxyfile`. La salida HTML se abre en `docs/referencia/html/index.html`.
- `docs/Informe_HelioControl.docx` y `docs/Presentacion_HelioControl.pptx` acompañan la sustentación.
