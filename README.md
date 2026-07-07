# Llanquihue Tour — Gestión de Colaboradores

Aplicación de consola en Java para la agencia de turismo **Llanquihue Tour** (Región de Los Lagos). Permite registrar y consultar a las personas vinculadas a la operación de la agencia —guías, operadores y proveedores— cargando sus datos desde un archivo externo y aplicando búsquedas y filtros simples.

## Descripción del sistema

El sistema lee los datos de los colaboradores desde el archivo `files/colaboradores.txt`, los valida, los carga en una colección dinámica (`ArrayList`) y muestra por consola:

1. El listado completo de colaboradores.
2. Un filtro por tipo (guías).
3. Un filtro por comuna.
4. Una búsqueda por nombre.

Si una línea del archivo está mal formada o tiene datos inválidos (RUT, correo, etc.), se descarta de forma controlada mediante `try-catch` y se informa por consola, sin detener la carga del resto.

## Estructura del proyecto

```
LlanquihueTourAppV1.2/
├── src/
│   ├── model/                          # Entidades del dominio
│   │   ├── Colaborador.java            # Clase base abstracta: atributos, getters, setters y toString()
│   │   ├── Guia.java                   # Hereda de Colaborador (idiomas, especialidad)
│   │   ├── Operador.java               # Hereda de Colaborador (empresa, años de experiencia)
│   │   ├── Proveedor.java              # Hereda de Colaborador (rubro, servicio)
│   │   ├── Contacto.java               # Composición: correo y teléfono
│   │   └── Ubicacion.java              # Composición: comuna y región
│   ├── util/                           # Validación y utilidades
│   │   ├── Validador.java              # Valida RUT (módulo 11), correo, texto y números
│   │   └── ValidacionException.java    # Excepción propia de validación
│   ├── data/                           # Lógica de datos y colección
│   │   ├── CargadorColaboradores.java  # Lee files/colaboradores.txt y retorna ArrayList<Colaborador>
│   │   └── GestorColaboradores.java    # Gestiona la colección: recorrido polimórfico, búsquedas y filtros
│   └── ui/
│       └── Main.java                   # Ejecuta lectura, despliegue polimórfico y filtros
├── files/
│   └── colaboradores.txt               # Archivo de datos usado por Main
└── README.md
```

### Paquetes

El código está organizado en cuatro paquetes funcionales:

| Paquete    | Responsabilidad | Clases |
|------------|-----------------|--------|
| `model`    | Entidades del dominio | `Colaborador` (abstracta), `Guia`, `Operador`, `Proveedor`, `Contacto`, `Ubicacion` |
| `util`     | Validación y utilidades | `Validador`, `ValidacionException` |
| `data`     | Lógica de datos y colección | `CargadorColaboradores`, `GestorColaboradores` |
| `ui`       | Punto de entrada | `Main` |

### Clases principales

- **`Colaborador`** (abstracta): clase base con los atributos comunes (`tipo`, `nombre`, `rut`) y dos relaciones de **composición**: cada colaborador posee un `Contacto` y una `Ubicacion`.
- **`Guia`, `Operador`, `Proveedor`**: especializan a `Colaborador` y añaden sus atributos propios.
- **`Contacto`** y **`Ubicacion`**: objetos de valor compuestos dentro de cada colaborador.
- **`Validador`**: valida texto no vacío, RUT (con dígito verificador, módulo 11), correo y números, lanzando `ValidacionException`.
- **`CargadorColaboradores`**: lee el archivo de datos y construye la lista de colaboradores válidos.
- **`GestorColaboradores`**: gestiona la colección `ArrayList<Colaborador>` y ofrece búsquedas y filtros.
- **`Main`**: clase principal con el método `main`.

## Semana 7 — Polimorfismo y colecciones genéricas

En esta etapa se extiende la jerarquía existente para aplicar **polimorfismo** y **colecciones genéricas**:

- **Colección polimórfica**: `GestorColaboradores` mantiene una `List<Colaborador>` que almacena objetos de
  distintas subclases (`Guia`, `Operador`, `Proveedor`) en una misma colección. Al ejecutar se cargan 10
  colaboradores (más de los cinco solicitados).
- **Sobrescritura de métodos**: la superclase `Colaborador` declara el método abstracto
  `mostrarInformacion()`, sobrescrito con `@Override` en cada subclase para desplegar información
  especializada según el tipo de servicio.
- **Recorrido polimórfico**: el método `GestorColaboradores.mostrarTodos()` recorre la colección con un bucle
  `for-each` e invoca `mostrarInformacion()` desde la referencia de tipo `Colaborador`. Cada objeto ejecuta la
  versión sobrescrita según su tipo real, sin necesidad de usar `instanceof`. Este recorrido se muestra en la
  sección *"DESPLIEGUE POLIMÓRFICO CON mostrarInformacion()"* de la salida por consola.
- **Escalabilidad**: para integrar un nuevo tipo de servicio basta con crear otra subclase de `Colaborador` y
  sobrescribir `mostrarInformacion()`, sin modificar el código de recorrido existente.

## Buenas prácticas de POO aplicadas

- Atributos `private` con sus respectivos *getters* y *setters*.
- Constructores en todas las clases.
- Método `toString()` implementado en cada clase (las subclases reutilizan el de la clase base con `super.toString()`).
- **Herencia**: `Guia`, `Operador` y `Proveedor` extienden `Colaborador`.
- **Polimorfismo**: método abstracto `mostrarInformacion()` sobrescrito en cada subclase e invocado desde referencias de la superclase.
- **Composición**: `Colaborador` contiene un `Contacto` y una `Ubicacion`.
- **Validaciones** con bloques `try-catch` y una excepción propia (`ValidacionException`).
- **Colecciones genéricas**: uso de `List<Colaborador>` (`ArrayList`) para almacenar y recorrer los objetos.
- Documentación con **Javadoc** en todas las clases y métodos.

## Formato del archivo de datos (`files/colaboradores.txt`)

Archivo de texto con campos separados por punto y coma (`;`). La primera línea es el encabezado; las líneas que empiezan con `#` se ignoran.

```
tipo;nombre;rut;email;telefono;comuna;campoA;campoB
```

- `tipo`: `GUIA`, `OPERADOR` o `PROVEEDOR`.
- `campoA` / `campoB` dependen del tipo:
  - **GUIA** → `idiomas` ; `especialidad`
  - **OPERADOR** → `empresa` ; `años de experiencia` (número)
  - **PROVEEDOR** → `rubro` ; `servicio`

Ejemplo:

```
GUIA;María González;12345678-5;maria.gonzalez@llanquihuetour.cl;+56912345678;Puerto Varas;Español/Inglés;Rutas gastronómicas
```

## Instrucciones de ejecución

### Desde NetBeans / IntelliJ IDEA

1. Abrir el proyecto `LlanquihueTourAppV1.2`.
2. Verificar que la clase principal sea `ui.Main`.
3. Ejecutar el proyecto (botón **Run** o `F6` en NetBeans).

> La carpeta `files/` (con `colaboradores.txt` dentro) debe estar en la raíz del proyecto (junto a `build.xml`), que es el directorio de trabajo al ejecutar desde el IDE.

### Desde la línea de comandos

```bash
# Compilar (desde la carpeta del proyecto)
javac -encoding UTF-8 -d build/classes $(find src -name "*.java")

# Ejecutar
java -Dfile.encoding=UTF-8 -cp build/classes ui.Main
```

## Requisitos

- JDK 17 o superior (el proyecto está configurado para JDK del IDE).
- NetBeans o IntelliJ IDEA.

## Autor

Nicolás Ahumada C.  
Asignatura: Desarrollo Orientado a Objetos I  
Institución: Duoc UC