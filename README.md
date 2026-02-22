🎓 Sistema de Gestión Académica Unificado (SGAU)
Este proyecto es una solución integral para la administración escolar, desarrollada en Java. Consolida múltiples módulos de gestión académica en un único motor funcional, permitiendo la administración de estudiantes, un catálogo completo de 54 materias (9 tetramestres), y un sistema de evaluación ponderada.

🚀 Características Principales
Gestión de Estudiantes: Registro detallado de información personal, demográfica y de contacto.

Catálogo Académico Completo: Incluye el plan de estudios total de 9 tetramestres con créditos y nombres oficiales.

Cálculo de Evaluación Institucional: Motor de cálculo automático basado en la ponderación oficial:

Proyectos (20%)

Tareas (20%)

Actividades (10%)

Examen Parcial 1 (10%)

Examen Parcial 2 (10%)

Examen Final (30%)

Reportes en Tiempo Real: Generación de estatus (Aprobado/Reprobado) y promedio general acumulado.

Validación de Datos: Sistema de seguridad que impide el ingreso de calificaciones fuera del rango 0-100 y maneja excepciones de entrada de usuario.

🛠️ Estructura del Proyecto
El código está diseñado bajo un enfoque de Consolidación Estructural para facilitar su portabilidad, integrando los siguientes modelos en un único archivo Main.java:

Student Model: Entidad de datos del alumno.

Subject Model: Definición de materias y créditos.

Evaluation Engine: Lógica matemática de promedios.

Academic & Student Services: Capas de lógica de negocio para inscripciones y registros históricos.

💻 Instalación y Ejecución
Para correr este proyecto en tu máquina local, asegúrate de tener instalado el JDK (Java Development Kit).

Clonar el repositorio:

Bash
git clone https://github.com/tu-usuario/nombre-del-repo.git
Navegar a la carpeta del proyecto:

Bash
cd nombre-del-repo
Compilar el archivo único:

Bash
javac Main.java
Ejecutar la aplicación:

Bash
java Main
📊 Ejemplo de Uso
Al ejecutar el programa, el sistema te guiará a través de los siguientes pasos:

Identificación: El sistema reconoce al alumno en sesión.

Selección: Eliges un tetramestre (1-9) para ver las materias disponibles.

Evaluación: Ingresas las notas de los diferentes criterios.

Resultado: El sistema imprime un reporte detallado con el estatus académico.

Desarrollado para la Universidad Ciudadana de Nuevo León.
