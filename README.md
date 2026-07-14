# Llanquihue Tour — Sistema de Gestión

Aplicación en Java para la agencia de turismo **Llanquihue Tour** (Región de Los Lagos). El sistema modela
los **servicios turísticos** y las **entidades operativas** de la agencia (guías, vehículos y colaboradores
externos) aplicando herencia, polimorfismo, interfaces, colecciones genéricas y una interfaz gráfica básica.

## Descripción del sistema

El sistema define un contrato común (`Registrable`) que implementan las distintas entidades gestionables,
las organiza en una colección genérica y las recorre diferenciando su tipo en tiempo de ejecución con
`instanceof`. Además, incorpora una interfaz gráfica (Swing) que permite ingresar nuevas entidades y
visualizar el resumen de las existentes, pensada para el personal administrativo.

## Estructura del proyecto

```
LlanquihueTourAppV1.2/
├── src/
│   ├── model/                       # Dominio: jerarquías e interfaz
│   │   ├── Registrable.java         # Interfaz: contrato común mostrarResumen()
│   │   ├── RecursoAgencia.java      # Superclase abstracta de las entidades (implements Registrable)
│   │   ├── GuiaTuristico.java       # Entidad: guía turístico
│   │   ├── Vehiculo.java            # Entidad: vehículo
│   │   ├── ColaboradorExterno.java  # Entidad: colaborador externo
│   │   ├── ServicioTuristico.java   # Superclase abstracta de los servicios (semanas 6-7)
│   │   ├── RutaGastronomica.java    # Servicio turístico (numeroDeParadas)
│   │   ├── PaseoLacustre.java       # Servicio turístico (tipoEmbarcacion)
│   │   └── ExcursionCultural.java   # Servicio turístico (lugarHistorico)
│   ├── data/                        # Colecciones y lógica de datos
│   │   ├── GestorEntidades.java     # ArrayList<Registrable> + diferenciación con instanceof
│   │   └── GestorServicios.java     # List<ServicioTuristico> (semanas 6-7)
│   └── ui/
│       ├── Main.java                # Clase principal: demo por consola y lanzamiento de la GUI
│       └── VentanaPrincipal.java    # Interfaz gráfica (JFrame + JOptionPane)
└── README.md
```

### Paquetes

| Paquete | Responsabilidad | Clases e interfaces |
|---------|-----------------|---------------------|
| `model` | Dominio, jerarquías e interfaz | `Registrable`, `RecursoAgencia`, `GuiaTuristico`, `Vehiculo`, `ColaboradorExterno`, `ServicioTuristico` y sus subclases |
| `data`  | Colecciones y lógica de datos | `GestorEntidades`, `GestorServicios` |
| `ui`    | Interfaz de usuario | `Main`, `VentanaPrincipal` |

## Clases e interfaces principales

- **`Registrable`** (interfaz): declara el método `mostrarResumen()`, contrato común de todas las entidades
  gestionables.
- **`RecursoAgencia`** (superclase abstracta): implementa `Registrable` y concentra el atributo común
  `nombre`. Es la base de la jerarquía de entidades.
- **`GuiaTuristico`, `Vehiculo`, `ColaboradorExterno`**: heredan de `RecursoAgencia` e implementan
  `mostrarResumen()` con un mensaje personalizado según su tipo.
- **`GestorEntidades`**: mantiene un `ArrayList<Registrable>`, recorre la colección con un bucle `for-each` y
  usa `instanceof` para identificar el tipo de cada entidad y aplicar una lógica de detalle diferenciada.
- **`VentanaPrincipal`**: interfaz gráfica con botones para ingresar guías, vehículos y colaboradores
  (mediante `JOptionPane`) y un área de texto que muestra el resumen de todos los registros.
- **`Main`**: crea la colección con datos de ejemplo, imprime una demostración por consola y lanza la GUI.

## Conceptos de POO aplicados

- **Interfaces**: `Registrable` como contrato de comportamiento común (Semana 8).
- **Herencia**: `RecursoAgencia` → `GuiaTuristico`, `Vehiculo`, `ColaboradorExterno`; y
  `ServicioTuristico` → `RutaGastronomica`, `PaseoLacustre`, `ExcursionCultural` (Semanas 6-7).
- **Polimorfismo**: recorrido de la colección desde referencias del tipo `Registrable`, invocando métodos
  sobrescritos/implementados.
- **`instanceof`**: diferenciación del comportamiento en tiempo de ejecución dentro de `GestorEntidades`.
- **Colecciones genéricas**: `ArrayList<Registrable>` y `List<ServicioTuristico>`.
- **Interfaz gráfica**: ingreso y visualización de datos con Swing (`JFrame` + `JOptionPane`).

## Instrucciones de ejecución

### Desde NetBeans / IntelliJ IDEA

1. Abrir el proyecto `LlanquihueTourAppV1.2`.
2. Verificar que la clase principal sea `ui.Main`.
3. Ejecutar el proyecto (botón **Run** o `F6` en NetBeans).
4. Se mostrará una demostración por consola y se abrirá la ventana de gestión de entidades.

### Desde la línea de comandos

```bash
# Compilar (desde la carpeta del proyecto)
javac -encoding UTF-8 -d build/classes src/model/*.java src/data/*.java src/ui/*.java

# Ejecutar
java -Dfile.encoding=UTF-8 -cp build/classes ui.Main
```

## Uso de la interfaz gráfica

- **Agregar Guía / Agregar Vehículo / Agregar Colaborador**: abren cuadros de diálogo para ingresar los
  datos de cada tipo de entidad.
- **Mostrar Resúmenes**: actualiza el área central con el resumen de todas las entidades registradas.

## Requisitos

- JDK 17 o superior.
- NetBeans o IntelliJ IDEA (opcional).

## Autor

Nicolás Ahumada C.
Asignatura: Desarrollo Orientado a Objetos I
Institución: Duoc UC
