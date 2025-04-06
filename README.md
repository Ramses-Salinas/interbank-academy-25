# (SOLUCIÓN) Reto Técnico: Procesamiento de Transacciones Bancarias (CLI)

## Introducción


### Objetivo:

Desarrollar una aplicación de línea de comandos (CLI) que procese un archivo CSV con transacciones bancarias y generar un reporte que incluya:

- **Balance Final:**  
  Suma de los montos de las transacciones de tipo "Crédito" menos la suma de los montos de las transacciones de tipo "Débito".

- **Transacción de Mayor Monto:**  
  Identificar el ID y el monto de la transacción con el valor más alto.

- **Conteo de Transacciones:**  
  Número total de transacciones para cada tipo ("Crédito" y "Débito").

### Propósito:

Probar hábilidades tecnicas dentro proceso de admisión para el programa *Interbank COBOL Academy*

## Instrucciones de Ejecución

### Dependencias

La unica dependencia que utiliza el proyecto es OpenCSV, la cual está detallada en el POM del proyecto, por lo que debe ser administrada e instalada con Maven.

### Ejecución de la aplicación

**Ejecutando el proyecto:**
Si se quiere ejecutar o hacer debug al proyecto, se debe cargar el mismo en su IDE de preferencia, asegurarse de que Maven cargue el proyecto directamente y correr la aplicación desde la siguiente clase:
```
pe.interbank.adapters.in.Main
```
**Ejecutando el .jar ya generado:**
Puede ejecutar la aplicación directamente desde consola gracias al .jar ya generado. Para hacerlo, debe abrir su consola de comandos y ubicarse en la ruta
```
...\target
```
Una vez allí, ejecute el siguiente comando
```
java -jar interbank-0.0.1-SNAPSHOT.jar "path\archivo.csv"
```
Donde *path\archivo.csv* es la ruta del archivo CSV que desea procesar.

## Enfoque y Solución

### Decisiones de diseño y arquitectura
Considerando que esta aplicación con el tiempo pueda llegar a requerir aceptar otro tipo de archivos, cambiar la dependencia seleccionada para mapear los CSV o expandir su alcance; se decició implementarla utilizando la arquitectura hexagonal como guía junto con las decisiones de diseño que eso conlleva.

**Arquitectura hexagonal:**
- *Dominio:* La lógica de negocio esencial se separa del resto y se hace independiente de todo lo que no esté dentro de su capa. 
- *Adaptadores:* Se implementa todo lo referente a la recepción de información externa a la apliación dentro de los adaptadores. 
- *Puertos:* En los puertos se establecen interfaces que detallen la lógica básica para que se utilicen los adaptadores sin depender de la implementación especifica para un tipo de archivo. 
- *Apliación:* Finalmente, en la capa de aplicación se hace uso de los adaptadores a través de los puertos, a la vez que utiliza la lógica esencial para completar los casos de uso requeridos.

**Otras decisiones de diseño:**
- Se tuvo en cuenta en todo momento la separación de responsabilidades y demás instrucciones que hacen parte de los principios SOLID.
- Se utilizó OpenCSV para la lectura de CSV debido a que se considera que la estructra de los mismos es sencilla, además que esta dependencia permite utilizar beans y anotaciones.
- Se uilizó el **jdk 1.8** para generar el jar del proyecto y se considero esta versión al momento de códificar y añadir la dependencia.

### Lógica implementada
A continuación se presenta un flujo resumido para explicar la lógica de la aplicación.

- Se recibe la ruta del CSV como argumento al momento de ejecutar la aplicación.
- Se valida la ruta especificada.
- Se procesan las transacciones que se encuentran en el CSV para obtener objetos DTO
- Los objetos DTO son traducidos a los objetos independientes de la fuente de los datos
- Los nuevos objetos son procesados para obtener el balance final, la transacción de mayor monto y el conteo de transacciones
- El proceso se completa siempre que no haya errores el CSV, como errores en el encabezado o cantidad de transacciones igual a 0.
- Se obtiene el mensaje final con los resultados.
- El mensaje final se imprime en consola.

## Estructura del Proyecto
La estructura de carpetas principales es la siguiente:
```
src/main/java/
└── pe/interbank/
    ├── adapters/
    │   ├── in/
    │   │   ├── Main.java
    │   │   └── csv/
    │   │       ├── TransactionCsvDTO.java
    │   │       └── TransactionsImporterCSV.java
    ├── application/
    │   └── TransactionsProcessCSV.java
    ├── domain/
    │   ├── TransactionsProcess.java
    │   ├── models/
    │   │   └── Transaction.java
    │   └── utils/
    │       └── TransactionsProcessUtil.java
    ├── ports/
    │   ├── in/
    │   │   └── TransactionsImporter.java
    │   └── out/
    │       └── TransactionsProcessServices.java
```
