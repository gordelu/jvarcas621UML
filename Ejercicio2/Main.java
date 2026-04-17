import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear persona
            Persona p = new Persona();
            Field fNombre = Persona.class.getDeclaredField("nombre");
            Field fAp1 = Persona.class.getDeclaredField("apellido1");
            Field fAp2 = Persona.class.getDeclaredField("apellido2");
            Field fId = Persona.class.getDeclaredField("id");
            Field fSexo = Persona.class.getDeclaredField("sexo");
            Field fFecha = Persona.class.getDeclaredField("fechaNacimiento");
            fNombre.setAccessible(true);
            fAp1.setAccessible(true);
            fAp2.setAccessible(true);
            fId.setAccessible(true);
            fSexo.setAccessible(true);
            fFecha.setAccessible(true);

            fNombre.set(p, "Luis");
            fAp1.set(p, "García");
            fAp2.set(p, "Pérez");
            fId.setInt(p, 123);
            fSexo.set(p, "M");
            fFecha.set(p, new GregorianCalendar(1990, Calendar.JANUARY, 15).getTime());

            // Crear dos puestos de trabajo
            PuestoTrabajo pt1 = new PuestoTrabajo();
            PuestoTrabajo pt2 = new PuestoTrabajo();

            // Establecer campos de puesto mediante reflexión
            Field ptNombre = PuestoTrabajo.class.getDeclaredField("nombre");
            Field ptFechaI = PuestoTrabajo.class.getDeclaredField("fechaInicio");
            Field ptFechaF = PuestoTrabajo.class.getDeclaredField("fechaFin");
            Field ptSal = PuestoTrabajo.class.getDeclaredField("salario");
            Field ptPuestos = PuestoTrabajo.class.getDeclaredField("puestos");
            ptNombre.setAccessible(true);
            ptFechaI.setAccessible(true);
            ptFechaF.setAccessible(true);
            ptSal.setAccessible(true);
            ptPuestos.setAccessible(true);

            ptNombre.set(pt1, "Programador Junior");
            ptFechaI.set(pt1, new GregorianCalendar(2015, Calendar.MARCH, 1).getTime());
            ptFechaF.set(pt1, new GregorianCalendar(2018, Calendar.AUGUST, 31).getTime());
            ptSal.set(pt1, 18000.0);
            Set<Persona> s1 = new HashSet<>();
            s1.add(p);
            ptPuestos.set(pt1, s1);

            ptNombre.set(pt2, "Analista");
            ptFechaI.set(pt2, new GregorianCalendar(2018, Calendar.SEPTEMBER, 1).getTime());
            ptFechaF.set(pt2, new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime());
            ptSal.set(pt2, 24000.0);
            Set<Persona> s2 = new HashSet<>();
            s2.add(p);
            ptPuestos.set(pt2, s2);

            // Mostrar resultados
            System.out.println("Persona creada:");
            System.out.println("  Nombre: " + fNombre.get(p) + " " + fAp1.get(p) + " " + fAp2.get(p));
            System.out.println("  ID: " + fId.getInt(p));
            System.out.println("  Sexo: " + fSexo.get(p));
            System.out.println("  Fecha Nac: " + fFecha.get(p));

            System.out.println("\nPuestos donde ha trabajado esta persona:");
            System.out.println("- " + ptNombre.get(pt1) + " (" + ptFechaI.get(pt1) + " -> " + ptFechaF.get(pt1) + ") salario=" + ptSal.get(pt1));
            System.out.println("- " + ptNombre.get(pt2) + " (" + ptFechaI.get(pt2) + " -> " + ptFechaF.get(pt2) + ") salario=" + ptSal.get(pt2));

            // Verificar pertenencia
            int count = 0;
            if (((Set<?>) ptPuestos.get(pt1)).contains(p)) count++;
            if (((Set<?>) ptPuestos.get(pt2)).contains(p)) count++;
            System.out.println("\nLa persona ha tenido " + count + " puestos de trabajo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
