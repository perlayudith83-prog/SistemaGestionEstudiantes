import java.util.*;

/**
 * PROYECTO: Sistema de Gestión Académica Integrado
 */

// --- SECCIÓN DE MODELOS DE DATOS ---

/**
 * Clase que representa la información personal y de contacto del alumno.
 */
class Student {
    private String firstName;
    private String middleName; 
    private String lastName;
    private String motherLastName;
    private int age;
    private String gender;
    private String nationality;
    private String address;
    private String homePhone;
    private String mobilePhone;

    public Student(String firstName, String middleName, String lastName, String motherLastName,
                   int age, String gender, String nationality, String address,
                   String homePhone, String mobilePhone) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
    }

    public String getFullName() {
        return firstName + " " + (middleName != null && !middleName.isEmpty() ? middleName + " " : "") +
               lastName + " " + motherLastName;
    }

    // Getters para reportes humanizados
    public String getAddress() { return address; }
    public String getMobilePhone() { return mobilePhone; }
}

/**
 * Representa una asignatura con su peso académico.
 */
class Subject {
    private String name;
    private int semester;
    private int credits;

    public Subject(String name, int semester, int credits) {
        this.name = name;
        this.semester = semester;
        this.credits = credits;
    }

    public String getName() { return name; }
    public int getSemester() { return semester; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return name + " (Tetramestre: " + semester + ", Créditos: " + credits + ")";
    }
}

/**
 * Gestiona el cálculo de promedios según los porcentajes institucionales.
 */
class Evaluation {
    private double projects;
    private double homework;
    private double activities;
    private double partialExam1;
    private double partialExam2;
    private double finalExam;

    public Evaluation(double projects, double homework, double activities,
                      double partialExam1, double partialExam2, double finalExam) {
        this.projects = validateGrade(projects);
        this.homework = validateGrade(homework);
        this.activities = validateGrade(activities);
        this.partialExam1 = validateGrade(partialExam1);
        this.partialExam2 = validateGrade(partialExam2);
        this.finalExam = validateGrade(finalExam);
    }

    private double validateGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("La calificación " + grade + " está fuera del rango 0-100.");
        }
        return grade;
    }

    public double calculateFinalGrade() {
        // Ponderación: 20% Proy, 20% Tareas, 10% Act, 10% P1, 10% P2, 30% Final
        return (projects * 0.2) + (homework * 0.2) + (activities * 0.1) +
               (partialExam1 * 0.1) + (partialExam2 * 0.1) + (finalExam * 0.3);
    }
}

/**
 * Estructura que organiza las materias por periodo.
 */
class Semester {
    private int number;
    private List<Subject> subjects;
    private List<Student> students;

    public Semester(int number) {
        this.number = number;
        this.subjects = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public int getNumber() { return number; }
    public void addSubject(Subject subject) { subjects.add(subject); }
    public void enrollStudent(Student student) { students.add(student); }
    public List<Subject> getSubjects() { return subjects; }
}

/**
 * Representa la asignación docente con límites de carga.
 */
class Teacher {
    private String name;
    private List<Subject> assignedSubjects;

    public Teacher(String name) {
        this.name = name;
        this.assignedSubjects = new ArrayList<>();
    }

    public void assignSubject(Subject subject) {
        if (assignedSubjects.size() < 3) {
            assignedSubjects.add(subject);
        } else {
            System.out.println("Límite excedido: El docente " + name + " ya tiene 3 materias asignadas.");
        }
    }

    public String getName() { return name; }
    public List<Subject> getAssignedSubjects() { return assignedSubjects; }
}

/**
 * Clase que vincula todos los elementos para un registro histórico.
 */
class AcademicRecord {
    private Student student;
    private Subject subject;
    private Semester semester;
    private Evaluation evaluation;

    public AcademicRecord(Student student, Subject subject, Semester semester, Evaluation evaluation) {
        this.student = student;
        this.subject = subject;
        this.semester = semester;
        this.evaluation = evaluation;
    }

    public double getFinalGrade() { return evaluation.calculateFinalGrade(); }
    public Student getStudent() { return student; }
    public Subject getSubject() { return subject; }
}


// --- SECCIÓN DE SERVICIOS (LÓGICA DE NEGOCIO) ---

/**
 * Gestiona el catálogo de materias y la administración de semestres.
 */
class AcademicService {
    private List<Semester> semesters;
    private List<AcademicRecord> academicRecords;
    private List<Subject> subjectCatalog;

    public AcademicService() {
        this.semesters = new ArrayList<>();
        this.academicRecords = new ArrayList<>();
        this.subjectCatalog = new ArrayList<>();
        initializeSubjects();
    }

    /**
     * Catálogo oficial de la carrera (9 Tetramestres).
     */
    private void initializeSubjects() {
        // Tetramestre 1
        subjectCatalog.add(new Subject("Matemáticas Básicas", 1, 8));
        subjectCatalog.add(new Subject("Introducción a la Programación", 1, 8));
        subjectCatalog.add(new Subject("Lógica Matemática", 1, 7));
        subjectCatalog.add(new Subject("Administración Contemporánea", 1, 6));
        subjectCatalog.add(new Subject("Comunicación Oral y Escrita", 1, 6));
        subjectCatalog.add(new Subject("Inglés Inicial", 1, 4));

        // Tetramestre 2
        subjectCatalog.add(new Subject("Cálculo Diferencial", 2, 8));
        subjectCatalog.add(new Subject("Programación Orientada a Objetos", 2, 8));
        subjectCatalog.add(new Subject("Sistemas Operativos", 2, 7));
        subjectCatalog.add(new Subject("Contabilidad Financiera", 2, 6));
        subjectCatalog.add(new Subject("Metodología de la Investigación", 2, 6));
        subjectCatalog.add(new Subject("Inglés Técnico I", 2, 4));

        // Tetramestre 3
        subjectCatalog.add(new Subject("Cálculo Integral", 3, 8));
        subjectCatalog.add(new Subject("Estructuras de Datos", 3, 8));
        subjectCatalog.add(new Subject("Arquitectura de Computadoras", 3, 7));
        subjectCatalog.add(new Subject("Derecho Informático", 3, 6));
        subjectCatalog.add(new Subject("Estadística Descriptiva", 3, 6));
        subjectCatalog.add(new Subject("Inglés Técnico II", 3, 4));

        // Tetramestre 4
        subjectCatalog.add(new Subject("Álgebra Lineal", 4, 8));
        subjectCatalog.add(new Subject("Bases de Datos I", 4, 8));
        subjectCatalog.add(new Subject("Redes de Computadoras I", 4, 7));
        subjectCatalog.add(new Subject("Ingeniería de Software I", 4, 7));
        subjectCatalog.add(new Subject("Estadística Inferencial", 4, 6));
        subjectCatalog.add(new Subject("Ética Profesional", 4, 5));

        // Tetramestre 5
        subjectCatalog.add(new Subject("Programación Web", 5, 8));
        subjectCatalog.add(new Subject("Bases de Datos II", 5, 8));
        subjectCatalog.add(new Subject("Redes de Computadoras II", 5, 7));
        subjectCatalog.add(new Subject("Ingeniería de Software II", 5, 7));
        subjectCatalog.add(new Subject("Sistemas Embebidos", 5, 6));
        subjectCatalog.add(new Subject("Gestión de Proyectos", 5, 6));

        // Tetramestre 6
        subjectCatalog.add(new Subject("Desarrollo de Apps Móviles", 6, 8));
        subjectCatalog.add(new Subject("Seguridad Informática", 6, 8));
        subjectCatalog.add(new Subject("Inteligencia Artificial", 6, 7));
        subjectCatalog.add(new Subject("Minería de Datos", 6, 7));
        subjectCatalog.add(new Subject("Sistemas Distribuidos", 6, 6));
        subjectCatalog.add(new Subject("Liderazgo y Emprendimiento", 6, 5));

        // Tetramestre 7
        subjectCatalog.add(new Subject("Cloud Computing", 7, 8));
        subjectCatalog.add(new Subject("Auditoría de Sistemas", 7, 7));
        subjectCatalog.add(new Subject("Machine Learning", 7, 8));
        subjectCatalog.add(new Subject("Internet de las Cosas (IoT)", 7, 7));
        subjectCatalog.add(new Subject("Proyecto Integrador I", 7, 10));
        subjectCatalog.add(new Subject("Seminario de Titulación I", 7, 5));

        // Tetramestre 8
        subjectCatalog.add(new Subject("Big Data", 8, 8));
        subjectCatalog.add(new Subject("Ciberseguridad Avanzada", 8, 7));
        subjectCatalog.add(new Subject("Ciencia de Datos", 8, 8));
        subjectCatalog.add(new Subject("Simulación de Sistemas", 8, 7));
        subjectCatalog.add(new Subject("Proyecto Integrador II", 8, 10));
        subjectCatalog.add(new Subject("Seminario de Titulación II", 8, 5));

        // Tetramestre 9
        subjectCatalog.add(new Subject("Prácticas Profesionales", 9, 15));
        subjectCatalog.add(new Subject("Servicio Social", 9, 10));
        subjectCatalog.add(new Subject("Calidad de Software", 9, 7));
        subjectCatalog.add(new Subject("Tendencias Tecnológicas", 9, 6));
        subjectCatalog.add(new Subject("Mantenimiento de Sistemas", 9, 6));
        subjectCatalog.add(new Subject("Evaluación de Proyectos", 9, 6));
    }

    public List<Subject> getSubjectsBySemester(int semesterNumber) {
        List<Subject> filtered = new ArrayList<>();
        for (Subject s : subjectCatalog) {
            if (s.getSemester() == semesterNumber) filtered.add(s);
        }
        return filtered;
    }

    public void addSemester(Semester semester) {
        if (semester.getNumber() < 1 || semester.getNumber() > 9) {
            throw new IllegalArgumentException("Número de tetramestre inválido.");
        }
        semesters.add(semester);
        // Autocarga de materias del catálogo al semestre
        for (Subject s : getSubjectsBySemester(semester.getNumber())) {
            semester.addSubject(s);
        }
    }

    public void enrollStudentInSemester(Semester semester, Student student) {
        semester.enrollStudent(student);
    }

    public void registerEvaluation(Student s, Subject sub, Semester sem, Evaluation ev) {
        academicRecords.add(new AcademicRecord(s, sub, sem, ev));
    }

    public List<AcademicRecord> getAcademicRecords() {
        return Collections.unmodifiableList(academicRecords);
    }
}

/**
 * Gestiona la persistencia de estudiantes y cálculos globales de desempeño.
 */
class StudentService {
    private List<Student> students;
    private List<AcademicRecord> records;

    public StudentService() {
        this.students = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public Student findStudentByName(String fullName) {
        for (Student s : students) {
            if (s.getFullName().equalsIgnoreCase(fullName)) return s;
        }
        return null;
    }

    public void addAcademicRecord(AcademicRecord record) {
        records.add(record);
    }

    public double calculateStudentAverage(Student student) {
        double total = 0;
        int count = 0;
        for (AcademicRecord record : records) {
            if (record.getStudent().equals(student)) {
                total += record.getFinalGrade();
                count++;
            }
        }
        return (count == 0) ? 0 : total / count;
    }
}


// --- SECCIÓN DE EJECUCIÓN (MAIN) ---

public class Main {

    public static void main(String[] args) {
        // Inicialización de herramientas y servicios
        Scanner lector = new Scanner(System.in);
        StudentService servicioEstudiante = new StudentService();
        AcademicService servicioAcademico = new AcademicService();

        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA UNIFICADO     ");
        System.out.println("==============================================");

        // Registro de estudiante inicial (Datos basados en el perfil solicitado)
        Student alumnoPrincipal = new Student(
            "Perla",
            "Yudith",
            "Delgadillo",
            "Navarro",
            38,
            "Femenino",
            "Mexicana",
            "Monterrey, N.L.",
            "528128459785",
            "528131658748"
        );

        servicioEstudiante.registerStudent(alumnoPrincipal);
        System.out.println("Sesión iniciada como: " + alumnoPrincipal.getFullName());

        // 1. Selección de Tetramestre
        int numTetra = 0;
        while (numTetra < 1 || numTetra > 9) {
            System.out.print("\nIngrese el número de tetramestre a consultar (1-9): ");
            if (lector.hasNextInt()) {
                numTetra = lector.nextInt();
                if (numTetra < 1 || numTetra > 9) System.out.println("Error: El rango debe ser de 1 a 9.");
            } else {
                System.out.println("Error: Ingrese un número válido.");
                lector.next();
            }
        }

        // Configuración del semestre actual
        Semester semestreActual = new Semester(numTetra);
        servicioAcademico.addSemester(semestreActual);
        servicioAcademico.enrollStudentInSemester(semestreActual, alumnoPrincipal);

        // 2. Selección de Materia del Catálogo
        List<Subject> materiasDisponibles = servicioAcademico.getSubjectsBySemester(numTetra);
        
        System.out.println("\n--- MATERIAS DISPONIBLES EN TETRAMESTRE " + numTetra + " ---");
        for (int i = 0; i < materiasDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + materiasDisponibles.get(i).getName() + 
                               " [" + materiasDisponibles.get(i).getCredits() + " Créditos]");
        }

        int seleccionMateria = 0;
        while (seleccionMateria < 1 || seleccionMateria > materiasDisponibles.size()) {
            System.out.print("Seleccione el número de materia para evaluar: ");
            if (lector.hasNextInt()) {
                seleccionMateria = lector.nextInt();
            } else {
                lector.next();
            }
        }

        Subject materiaElegida = materiasDisponibles.get(seleccionMateria - 1);

        // 3. Captura de Calificaciones con validación de rango
        System.out.println("\n--- REGISTRO DE CALIFICACIONES (Escala 0-100) ---");
        double proy = capturarNota(lector, "Puntaje de Proyectos (20%): ");
        double tare = capturarNota(lector, "Puntaje de Tareas (20%): ");
        double acti = capturarNota(lector, "Puntaje de Actividades (10%): ");
        double par1 = capturarNota(lector, "Examen Parcial 1 (10%): ");
        double par2 = capturarNota(lector, "Examen Parcial 2 (10%): ");
        double exFi = capturarNota(lector, "Examen Final (30%): ");

        // Procesamiento de la evaluación
        try {
            Evaluation evaluacionFinal = new Evaluation(proy, tare, acti, par1, par2, exFi);
            
            // Registro en ambos servicios para mantener integridad
            servicioAcademico.registerEvaluation(alumnoPrincipal, materiaElegida, semestreActual, evaluacionFinal);
            
            AcademicRecord nuevoRegistro = new AcademicRecord(alumnoPrincipal, materiaElegida, semestreActual, evaluacionFinal);
            servicioEstudiante.addAcademicRecord(nuevoRegistro);

            // 4. Despliegue de Resultados y Reporte
            System.out.println("\n==============================================");
            System.out.println("           REPORTE ACADÉMICO FINAL            ");
            System.out.println("==============================================");
            System.out.println("Alumno:     " + alumnoPrincipal.getFullName());
            System.out.println("Ubicación:  " + alumnoPrincipal.getAddress());
            System.out.println("Materia:    " + materiaElegida.getName());
            System.out.println("Tetra:      " + numTetra);
            System.out.println("----------------------------------------------");
            
            double califFinal = nuevoRegistro.getFinalGrade();
            System.out.printf("CALIFICACIÓN FINAL EN MATERIA: %.2f%n", califFinal);
            
            String estatus = (califFinal >= 70) ? "APROBADO" : "REPROBADO";
            System.out.println("ESTATUS:    " + estatus);
            
            double promedioGral = servicioEstudiante.calculateStudentAverage(alumnoPrincipal);
            System.out.printf("PROMEDIO GENERAL ACUMULADO:    %.2f%n", promedioGral);
            System.out.println("==============================================");

        } catch (IllegalArgumentException e) {
            System.out.println("Error en el procesamiento: " + e.getMessage());
        }

        System.out.println("\nProceso finalizado. El sistema se cerrará.");
        lector.close();
    }

    /**
     * Método auxiliar para garantizar la captura de datos numéricos válidos.
     */
    private static double capturarNota(Scanner sc, String mensaje) {
        double nota = -1;
        while (nota < 0 || nota > 100) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                nota = sc.nextDouble();
                if (nota < 0 || nota > 100) {
                    System.out.println("Alerta: La nota debe estar entre 0 y 100.");
                }
            } else {
                System.out.println("Alerta: Ingrese un valor numérico válido.");
                sc.next(); // Limpiar buffer
            }
        }
        return nota;
    }
}