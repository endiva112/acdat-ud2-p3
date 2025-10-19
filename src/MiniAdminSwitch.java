import java.io.File;
import java.io.IOException;

public class MiniAdminSwitch {

    public static void main(String[] args) {
        if (args.length < 3) {
            imprimirUso();
            return;
        }

        File base = new File(args[0]);
        if (!base.exists() || !base.isDirectory()) {
            System.err.println("La ruta base debe existir y ser un directorio.");
            return;
        }

        String operacion = args[1];

        try {
            switch (operacion) {
                case "borrar":
                    String rutaRel = args[2];
                    borrarSeguro(base, rutaRel);
                    break;

                case "renombrar":
                    if (args.length < 4) {
                        System.err.println("Faltan <origenRel> <destinoRel>.");
                        return;
                    }
                    String origenRel = args[2];
                    String destinoRel = args[3];
                    renombrarSeguro(base, origenRel, destinoRel); // TODO implementar
                    break;

                case "permiso":
                    if (args.length < 5) {
                        System.err.println("Faltan <rutaRel> <permiso> <on|off>.");
                        return;
                    }
                    String ruta = args[2];
                    String permiso = args[3].toLowerCase();
                    String valor = args[4].toLowerCase();
                    boolean activar;

                    switch (valor) {
                        case "on":
                        case "true":
                            activar = true;
                            break;
                        case "off":
                        case "false":
                            activar = false;
                            break;
                        default:
                            System.err.println("El valor debe ser on|off o true|false.");
                            return;
                    }

                    cambiarPermiso(base, ruta, permiso, activar); // TODO implementar
                    break;

                default:
                    System.err.println("Operación no reconocida: " + operacion);
                    imprimirUso();
            }
        } catch (IOException e) {
            System.err.println("[main] IOException: " + e.getMessage());
        }
    }

    private static void imprimirUso() {
        System.err.println("Uso:");
        System.err.println("  java MiniAdminSwitch <rutaBase> borrar <rutaRel>");
        System.err.println("  java MiniAdminSwitch <rutaBase> renombrar <origenRel> <destinoRel>");
        System.err.println("  java MiniAdminSwitch <rutaBase> permiso <rutaRel> <lectura|escritura|ejecucion> <on|off>");
    }

    private static void borrarSeguro(File base, String rutaRel) throws IOException {
        File objetivo = new File(base, rutaRel);
        try {
            if (objetivo.exists()) {
                if (objetivo.isDirectory()) {//DIRECTORIO
                    String[] items = objetivo.list();
                    if (items != null) {
                        if (items.length > 0) {
                            for (String item : items) {
                                System.out.println(" - " + item);
                            }
                            throw new IOException("Directorio no vacío");
                        } else {
                            if (objetivo.delete()) {
                                System.out.println("El directorio " + objetivo.getName() + " ha sido eliminado.");
                            }
                        }
                    } else {
                        throw new IOException("No se pudo listar");
                    }
                } else {//FICHERO
                    if (objetivo.delete()) {
                        System.out.println("El fichero " + objetivo.getName() + " ha sido eliminado.");
                    } else {
                        throw new IOException("No se pudo borrar");
                    }
                }
            } else {
                throw new IOException("No existe");
            }
        } finally {
            System.out.println("[borrar] Fin");
        }
    }

    // TODO: renombrarSeguro
    // 1. Crear File origen y destino
    // 2. Si origen no existe -> mensaje de error
    // 3. Si destino existe -> mensaje de error
    // 4. Si el directorio padre del destino no existe -> mensaje de error
    // 5. Usar renameTo()
    //    - si true -> mensaje de éxito
    //    - si false -> mensaje de fallo
    // 6. Capturar SecurityException
    // 7. finally -> mensaje "[renombrar] Fin"
    private static void renombrarSeguro(File base, String origenRel, String destinoRel) {
        // TODO implementar
        System.err.println("TODO renombrarSeguro");
    }

    // TODO: cambiarPermiso
    // 1. Crear File f = new File(base, rutaRel)
    // 2. Si no existe -> mensaje de error
    // 3. Usar switch(permiso):
    //    - lectura   -> setReadable(activar, false)
    //    - escritura -> setWritable(activar, false)
    //    - ejecucion -> setExecutable(activar, false)
    //    - otro -> mensaje "Permiso no válido"
    // 4. Mostrar resultado según boolean devuelto
    // 5. Capturar SecurityException
    // 6. finally -> mensaje "[permiso] Fin"
    private static void cambiarPermiso(File base, String rutaRel, String permiso, boolean activar) {
        // TODO implementar
        System.err.println("TODO cambiarPermiso (" + permiso + " -> " + (activar ? "on" : "off") + ")");
    }
}