# Llanquihue Tour — Sistema de Gestión (Evaluación Final Transversal)

Prototipo de software en Java para la agencia de turismo **Llanquihue Tour** (Región de Los Lagos). El
sistema digitaliza la gestión de las personas vinculadas a la agencia (clientes, empleados y proveedores) y
del catálogo de servicios turísticos, aplicando los principios de la Programación Orientada a Objetos con una
estructura modular organizada en paquetes.

## Descripción general del sistema

El sistema carga los datos de las personas desde un archivo de texto externo (`files/personas.txt`), los
valida, los almacena en colecciones (`ArrayList` y `HashMap`) y los gestiona desde una **interfaz gráfica**
(Swing) que permite:

- Ingresar clientes, empleados y proveedores mediante formularios.
- Listar todas las personas registradas de forma polimórfica.
- Buscar personas por nombre.
- Filtrar personas por tipo (usando `instanceof`).
- Buscar una persona por su RUT (usando el índice `HashMap`).
- Eliminar una persona por su RUT.
- Consultar el catálogo de servicios turísticos.

Si una línea del archivo tiene datos inválidos (por ejemplo, un RUT con dígito verificador incorrecto), se
descarta de forma controlada mediante excepciones personalizadas, sin detener la carga del resto. Al ingresar
datos desde la interfaz, el sistema valida en el momento el RUT (formato y dígito verificador) y el correo, y
no permite registrar dos personas con el mismo RUT (integridad de datos).

## Estructura del proyecto (paquetes)

```
LlanquihueTourAppV1.2/
├── src/
│   ├── model/                       # Entidades del dominio, jerarquías e interfaz
│   │   ├── Registrable.java         # Interfaz: contrato registrar() y mostrarDatos()
│   │   ├── Persona.java             # Superclase abstracta (implements Registrable)
│   │   ├── Cliente.java             # Persona -> Cliente
│   │   ├── Empleado.java            # Persona -> Empleado
│   │   ├── Proveedor.java           # Persona -> Proveedor
│   │   ├── Rut.java                 # Composición: RUT validado
│   │   ├── Direccion.java           # Composición: dirección
│   │   ├── Contacto.java            # Composición: correo y teléfono
│   │   ├── ServicioTuristico.java   # Superclase de los servicios (catálogo)
│   │   ├── RutaGastronomica.java    # Servicio turístico
│   │   ├── PaseoLacustre.java       # Servicio turístico
│   │   └── ExcursionCultural.java   # Servicio turístico
│   ├── utils/                       # Utilidades y validación
│   │   ├── Validador.java           # Valida RUT (módulo 11), correo, texto y números
│   │   └── ValidacionException.java # Excepción personalizada de validación
│   ├── data/                        # Colecciones y acceso a datos
│   │   ├── CargadorPersonas.java    # Lee files/personas.txt y crea objetos Persona
│   │   ├── GestorPersonas.java      # ArrayList + HashMap: alta, búsqueda y filtro
│   │   └── GestorServicios.java     # Colección del catálogo de servicios
│   └── app/                         # Aplicación e interfaz de usuario
│       ├── Main.java                # Clase principal: carga datos y lanza la GUI
│       └── VentanaPrincipal.java    # Interfaz gráfica (JFrame + JOptionPane)
├── files/
│   └── personas.txt                 # Archivo de datos (fuente externa)
└── README.md
```

## Resumen de las clases principales

| Clase / Interfaz | Función |
|------------------|---------|
| `Registrable` | Interfaz con los métodos `registrar()` y `mostrarDatos()`; contrato común de las personas. |
| `Persona` (abstracta) | Superclase con los atributos comunes y composición (`Rut`, `Direccion`, `Contacto`). |
| `Cliente`, `Empleado`, `Proveedor` | Subclases que heredan de `Persona` e implementan `mostrarDatos()` según su tipo. |
| `Rut` | Valida el formato y el dígito verificador (módulo 11) al construirse. |
| `Direccion`, `Contacto` | Objetos de valor usados por composición dentro de `Persona`. |
| `Validador` | Valida RUT, correo, texto y números, lanzando `ValidacionException`. |
| `CargadorPersonas` | Lee el archivo `.txt` y construye la lista de personas válidas. |
| `GestorPersonas` | Gestiona un `ArrayList` y un `HashMap`; alta, listado, búsqueda, filtro con `instanceof` y eliminación. |
| `GestorServicios` | Mantiene el catálogo de servicios turísticos. |
| `VentanaPrincipal` | Interfaz gráfica de ingreso y consulta. |
| `Main` | Punto de entrada: carga los datos y lanza la GUI. |

## Principios de POO aplicados

- **Encapsulamiento**: atributos `private` con getters y setters.
- **Constructores** y método **`toString()`** en todas las clases.
- **Composición**: `Persona` contiene un `Rut`, una `Direccion` y un `Contacto`.
- **Validación con excepciones personalizadas**: `ValidacionException` para el RUT (módulo 11) y el correo,
  tanto al cargar el archivo como al ingresar datos en la interfaz.
- **Integridad de datos**: no se permiten RUT duplicados, verificados con el índice `HashMap`.
- **Colecciones**: `ArrayList<Persona>` y `HashMap<String, Persona>` para agregar, buscar, filtrar y eliminar.
- **Archivos externos**: los datos se cargan desde `files/personas.txt`.
- **Herencia**: `Persona → Cliente, Empleado, Proveedor`.
- **Interfaces y polimorfismo**: `Registrable`, recorrido polimórfico y uso de `instanceof` para
  diferenciar comportamientos según el tipo.

## Instrucciones para clonar y ejecutar

### Clonar el repositorio

```bash
git clone https://github.com/nikoduoc/LlanquihueTourApp.git
cd LlanquihueTourApp
```

### Ejecutar desde NetBeans / IntelliJ IDEA

1. Abrir el proyecto `LlanquihueTourAppV1.2`.
2. Verificar que la clase principal sea `app.Main`.
3. Ejecutar el proyecto (botón **Run** o `F6` en NetBeans). Se abrirá la interfaz gráfica.

> La carpeta `files/` (con `personas.txt`) debe estar en la raíz del proyecto, que es el directorio de
> trabajo al ejecutar desde el IDE.

### Ejecutar desde la línea de comandos

```bash
# Compilar
javac -encoding UTF-8 -d build/classes src/model/*.java src/utils/*.java src/data/*.java src/app/*.java

# Ejecutar
java -Dfile.encoding=UTF-8 -cp build/classes app.Main
```

## Formato del archivo de datos (`files/personas.txt`)

Campos separados por punto y coma (`;`). La primera línea es el encabezado; las líneas que comienzan con `#`
se ignoran.

```
tipo;nombre;rut;email;telefono;calle;comuna;region;extra1;extra2
```

- `tipo`: `CLIENTE`, `EMPLEADO` o `PROVEEDOR`.
- `extra1` / `extra2` dependen del tipo:
  - **CLIENTE** → preferencia ; número de reservas (número)
  - **EMPLEADO** → cargo ; idiomas
  - **PROVEEDOR** → empresa ; rubro

## Requisitos

- JDK 17 o superior.
- NetBeans o IntelliJ IDEA.

## Autor

Nicolás Ahumada C.
Asignatura: Desarrollo Orientado a Objetos I
Institución: Duoc UC
