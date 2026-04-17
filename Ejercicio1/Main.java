public class Main {
    public static void main(String[] args) {
        Circulo c = new Circulo();

        System.out.println("Es instancia de Circulo? " + (c instanceof Circulo));
        System.out.println("Es instancia de Conica? " + (c instanceof Conica));
        System.out.println("Es instancia de Forma? " + (c instanceof Forma));

        // Comprobar atributos heredados mediante reflexión
        try {
            java.lang.reflect.Field diam = Conica.class.getDeclaredField("Diametro");
            diam.setAccessible(true);
            try {
                Object val = diam.get(c);
                System.out.println("Diametro (de Conica) en la instancia: " + val);
            } catch (IllegalArgumentException ia) {
                System.out.println("La instancia no contiene el campo 'Diametro' de Conica.");
            }
        } catch (NoSuchFieldException nsf) {
            System.out.println("Conica no declara 'Diametro'.");
        }

        try {
            java.lang.reflect.Field color = Forma.class.getDeclaredField("color");
            color.setAccessible(true);
            try {
                Object val = color.get(c);
                System.out.println("Color (de Forma) en la instancia: " + val);
            } catch (IllegalArgumentException ia) {
                System.out.println("La instancia no contiene el campo 'color' de Forma.");
            }
        } catch (NoSuchFieldException nsf) {
            System.out.println("Forma no declara 'color'.");
        }
    }
}
