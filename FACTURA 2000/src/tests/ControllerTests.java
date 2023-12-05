package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Controller;
import dtos.ItemProductoDTO;
import dtos.OrdenCompraDTO;
import dtos.ProductoDTO;
import dtos.ProveedorDTO;
import mapper.MapperDTO;
import model.CondicionIVA;
import model.Proveedor;
import model.TipoIVA;
import model.TipoUnidad;

public class ControllerTests {

    private Controller controller;

    private MapperDTO mapper;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        mapper = new MapperDTO();
    }

    @Test
    public void testCreateProveedor() {
        ProveedorDTO proveedorDTO = controller.createProveedor("123456789", CondicionIVA.MONOTRIBUTO, "Empresa XYZ",
                "XYZ Corp", "Calle Principal 123", "123-456-7890", "info@xyz.com", 1000.0, LocalDate.now(), "Tecnología");

        assertNotNull(proveedorDTO);
        assertEquals("123456789", proveedorDTO.getCuit());
    }

    @Test
    public void testGetProveedor() {

        controller.createProveedor("123456789", CondicionIVA.MONOTRIBUTO, "Empresa XYZ",
                "XYZ Corp", "Calle Principal 123", "123-456-7890", "info@xyz.com", 1000.0, LocalDate.now(), "Tecnología");

        Proveedor proveedor = controller.getProveedor("123456789");

        assertNotNull(proveedor);
        assertEquals("XYZ Corp", proveedor.getNombreFantasia());
    }

    @Test
    public void testCreateProducto() {
        ProveedorDTO proveedor = controller.createProveedor("123456789", CondicionIVA.MONOTRIBUTO, "Empresa XYZ",
                "XYZ Corp", "Calle Principal 123", "123-456-7890", "info@xyz.com", 1000.0, LocalDate.now(), "Tecnología");

        assertNotNull(proveedor);

        controller.createProducto(mapper.toProveedor(proveedor), "Producto1", TipoUnidad.UNIDAD, 20.0, TipoIVA.IVA_21);

        List<ProductoDTO> productos = controller.getAllProductos();

        assertNotNull(productos);
        assertEquals(1, productos.size());
    }


    @Test
    public void testGenerarOrdenCompra() {
        ProveedorDTO proveedor = controller.createProveedor("123456789", CondicionIVA.MONOTRIBUTO, "Empresa XYZ",
                "XYZ Corp", "Calle Principal 123", "123-456-7890", "info@xyz.com", 1000.0, LocalDate.now(), "Tecnología");

        assertNotNull(proveedor);

        ProductoDTO producto1 = controller.createProducto(mapper.toProveedor(proveedor), "Producto1", TipoUnidad.UNIDAD, 20.0, TipoIVA.IVA_21);
        ProductoDTO producto2 = controller.createProducto(mapper.toProveedor(proveedor), "Producto2", TipoUnidad.UNIDAD, 15.0, TipoIVA.IVA_21);

        ItemProductoDTO item1 = new ItemProductoDTO();
        item1.setProducto(producto1);
        item1.setCantidad(2);
        item1.setPrecio(producto1.getPrecioUnitario());
        ItemProductoDTO item2 = new ItemProductoDTO();
        item2.setProducto(producto2);
        item2.setCantidad(1);
        item2.setPrecio(producto2.getPrecioUnitario());

        List<ItemProductoDTO> productosSeleccionados = List.of(item1, item2);

        OrdenCompraDTO ordenCompraDTO = controller.generarOrdenCompra(proveedor.getCuit(), productosSeleccionados);

        assertNotNull(ordenCompraDTO);
        assertEquals(proveedor.getCuit(), ordenCompraDTO.getProveedor().getCuit());
        assertEquals(proveedor.getNombreFantasia(), ordenCompraDTO.getProveedor().getNombreFantasia());
        assertEquals(55.0, ordenCompraDTO.getImporte(), 0);
        
    }

}
