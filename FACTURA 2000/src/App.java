import java.time.LocalDate;
import javax.swing.SwingUtilities;

import controller.Controller;
import gui.MainWindow;
import model.CondicionIVA;
import model.TipoIVA;
import model.TipoUnidad;

public class App {

    static Controller controller = Controller.getInstance();

    public static void main(String[] args) {    
        SwingUtilities.invokeLater(() -> {
            addDummyData(controller);
            new MainWindow(controller);
        });
    }

    public static void addDummyData(Controller controller) {

        controller.createProveedor("20123456781",
                CondicionIVA.MONOTRIBUTO,
                "Comestibles S.A.",
                "Comestibles Express",
                "Av. Juan Manuel de Rosas 851, Lomas del Mirador",
                "(011) 555-1234",
                "info@deliciasexpress.com",
                12345.67,
                LocalDate.of(2010, 1, 1),
                "Industria Alimentaria");


        controller.createProveedor(
                "27987654321",
                CondicionIVA.RESPONSABLE_INSCRIPTO,
                "ElectroTec S.R.L.",
                "TecnoGadgets",
                "Av. Directorio 3451, C.A.B.A - Flores",
                "(011) 555-5678",
                "ventas@tecnogadgets.com",
                98765.43,
                LocalDate.of(2012, 3, 5),
                "Industria Tecnologica"
        );


        controller.createProveedor(
                "30876543219",
                CondicionIVA.MONOTRIBUTO,
                "Ropa Elegante S.H.",
                "Moda Chic",
                "Asuncion 977, Ituzaingo",
                "(011) 555-9876",
                "info@modachic.com",
                54321.0,
                LocalDate.of(2015, 9, 10),
                "Industria Textil"
        );


        controller.createProveedor(
                "23234567891",
                CondicionIVA.RESPONSABLE_INSCRIPTO,
                "Materiales de Construcción S.A.",
                "ConstruMat",
                "Av. Gral. Eustaquio Frías, Lomas de Zamora",
                "(011) 555-3456",
                "ventas@construmat.com",
                87654.32,
                LocalDate.of(2008, 7, 2),
                "Construccion"
        );


        controller.createProveedor(
                "24765432101",
                CondicionIVA.MONOTRIBUTO,
                "Librería Creativa S.R.L.",
                "ArteLibro",
                "Av. Juan Bautista Justo, C.A.B.A - Palermo",
                "(011) 555-6543",
                "info@artelibro.com",
                23456.78,
                LocalDate.of(2013, 11, 15),
                "Librería y otros insumos"
        );


        controller.createProveedor(
                "123",
                CondicionIVA.MONOTRIBUTO,
                "Proveedor Debug",
                "Debug",
                "Calle Falsa 123",
                "XXXXXXXXX",
                "prov@debug.com",
                1.1,
                LocalDate.of(1000, 11, 1),
                "TESTING"
        );



        controller.createProducto(controller.getProveedor("20123456781"), "Pastas", TipoUnidad.PESO, 1900.0, TipoIVA.IVA_21);
        controller.createProducto(controller.getProveedor("20123456781"), "Carne", TipoUnidad.PESO, 3250.0, TipoIVA.IVA_10_5);
        controller.createProducto(controller.getProveedor("20123456781"), "Minutas", TipoUnidad.UNIDAD, 2980.0, TipoIVA.IVA_5);



        controller.createProducto(controller.getProveedor("27987654321"), "Smartphone X1000", TipoUnidad.UNIDAD, 799000.0, TipoIVA.IVA_21);
        controller.createProducto(controller.getProveedor("27987654321"), "Laptop UltraSlim", TipoUnidad.UNIDAD, 1299000.0, TipoIVA.IVA_10_5);
        controller.createProducto(controller.getProveedor("27987654321"), "Cámara de Seguridad HD", TipoUnidad.UNIDAD, 1499000.0, TipoIVA.IVA_21);

        controller.createProducto(controller.getProveedor("30876543219"), "Vestido Elegante", TipoUnidad.UNIDAD, 89000.0, TipoIVA.IVA_21);
        controller.createProducto(controller.getProveedor("30876543219"), "Zapatos de Tacón", TipoUnidad.UNIDAD, 59000.0, TipoIVA.IVA_10_5);
        controller.createProducto(controller.getProveedor("30876543219"), "Traje de Hombre", TipoUnidad.UNIDAD, 129000.0, TipoIVA.IVA_27);

        controller.createProducto(controller.getProveedor("23234567891"), "Bolsa de Cemento", TipoUnidad.UNIDAD, 25000.0, TipoIVA.IVA_21);
        controller.createProducto(controller.getProveedor("23234567891"), "Pintura Interior", TipoUnidad.PESO, 18000.0, TipoIVA.IVA_10_5);
        controller.createProducto(controller.getProveedor("23234567891"), "Ladrillo Refractario", TipoUnidad.UNIDAD, 21000.0, TipoIVA.IVA_5);

        controller.createProducto(controller.getProveedor("24765432101"), "Set de Pinturas Acrílicas", TipoUnidad.UNIDAD, 4900.0, TipoIVA.IVA_21);
        controller.createProducto(controller.getProveedor("24765432101"), "Cuadernos de Diseño Gráfico", TipoUnidad.UNIDAD, 9000.0, TipoIVA.IVA_10_5);
        controller.createProducto(controller.getProveedor("24765432101"), "Libro de Arte Moderno", TipoUnidad.UNIDAD, 120000.0, TipoIVA.IVA_10_5);

        controller.createProducto(controller.getProveedor("123"), "ProductoTestA.Unidad.IVA%10,5", TipoUnidad.UNIDAD, 5.0, TipoIVA.IVA_10_5);
        controller.createProducto(controller.getProveedor("123"), "ProductoTestB.Peso.IVA%2,5", TipoUnidad.PESO, 10.0, TipoIVA.IVA_2_5);
        controller.createProducto(controller.getProveedor("123"), "ProductoTestC.Horas.IVA%27", TipoUnidad.HORAS, 2.0, TipoIVA.IVA_27);

    }

}
