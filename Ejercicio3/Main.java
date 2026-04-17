import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear proyecto
            Proyecto p = new Proyecto();

            // Crear empleados
            Empleado e1 = new Empleado();
            Empleado e2 = new Empleado();
            Empleado e3 = new Empleado(); // no asignado

            // Crear lugar
            Lugar l = new Lugar();

            // Asignar nombres a empleados (opcional para salida)
            Field fn = Empleado.class.getDeclaredField("nombre");
            Field fa = Empleado.class.getDeclaredField("apellidos");
            fn.setAccessible(true);
            fa.setAccessible(true);
            fn.set(e1, "Ana");
            fa.set(e1, "López");
            fn.set(e2, "Carlos");
            fa.set(e2, "Martín");
            fn.set(e3, "María");
            fa.set(e3, "Santos");

            // Enlazar proyecto en los Set de proyectos de e1 y e2
            Field fProyEmp = Empleado.class.getDeclaredField("proyectos");
            fProyEmp.setAccessible(true);
            Set<Proyecto> s1 = new HashSet<>();
            s1.add(p);
            fProyEmp.set(e1, s1);
            Set<Proyecto> s2 = new HashSet<>();
            s2.add(p);
            fProyEmp.set(e2, s2);
            // e3 deja su 'proyectos' en null (no asignado)

            // Enlazar lugar al proyecto
            Field fProjLug = Lugar.class.getDeclaredField("proyectos");
            fProjLug.setAccessible(true);
            fProjLug.set(l, p);

            // Añadir lugar al Set de lugares del proyecto
            Field fLugares = Proyecto.class.getDeclaredField("lugares");
            fLugares.setAccessible(true);
            Set<Lugar> sl = new HashSet<>();
            sl.add(l);
            fLugares.set(p, sl);

            // Mostrar resultados
            List<Empleado> empleados = Arrays.asList(e1, e2, e3);
            System.out.println("Empleados asignados al proyecto:");
            int count = 0;
            for (Empleado em : empleados) {
                Object proySet = fProyEmp.get(em);
                boolean assigned = false;
                if (proySet instanceof Set) {
                    assigned = ((Set<?>) proySet).contains(p);
                }
                if (assigned) {
                    System.out.println(" - " + fn.get(em) + " " + fa.get(em));
                    count++;
                }
            }
            System.out.println("Total empleados asignados: " + count);

            // Empleado no asignado
            System.out.println("Empleado sin proyecto: " + fn.get(e3) + " " + fa.get(e3));

            // Lugar asignado al proyecto
            Object lugarProy = fProjLug.get(l);
            System.out.println("Lugar asociado al proyecto: " + (lugarProy == p ? "sí" : "no"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
