/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.helpers.cargaMasiva;

import com.sigad.sigad.app.controller.LoginController;
import com.sigad.sigad.business.Insumo;
import com.sigad.sigad.business.Perfil;
import com.sigad.sigad.business.Permiso;
import com.sigad.sigad.business.Producto;
import com.sigad.sigad.business.ProductoCategoria;
import com.sigad.sigad.business.ProductoFragilidad;
import com.sigad.sigad.business.ProductoInsumo;
import com.sigad.sigad.business.Proveedor;
import com.sigad.sigad.business.Tienda;
import com.sigad.sigad.business.TipoMovimiento;
import com.sigad.sigad.business.TipoPago;
import com.sigad.sigad.business.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author paul
 */
public class CargaMasivaHelper {
    
    private final static Logger LOGGER = Logger.getLogger(CargaMasivaHelper.class.getName());
    
    /* Incio : Metodos disponibles */
    
    /* forma de consumo : se pasa como parametros la lista des tablas de las cuales se quieren generar sus plantillas
    y la ruta donde se quiere guardar el archivo, cabe senialar que la ruta debe contener el nombre del archivo con el formato xls (Excel)*/
    public static void generarCargaMasivaTemplate(ArrayList<String> listaTablaCarga, String destinoTemplate) {
        if (listaTablaCarga != null && !listaTablaCarga.isEmpty()) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            int rowIndex;
            for (String tablaCarga : listaTablaCarga) {
                HSSFSheet sheet = workbook.createSheet(tablaCarga);
                HSSFRow rowhead = sheet.createRow(0);
                rowIndex = 0;
                // Definimos las cabeceras
                switch(tablaCarga) {
                    case CargaMasivaConstantes.TABLA_PRODUCTOCATEGORIA:
                        rowhead.createCell(rowIndex).setCellValue("Nombre");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        break;
                    case CargaMasivaConstantes.TABLA_PERFILES:
                        rowhead.createCell(rowIndex).setCellValue("Nombre");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        break;
                    case CargaMasivaConstantes.TABLA_USUARIOS:
                        rowhead.createCell(rowIndex).setCellValue("Nombre(s)");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Apellido Paterno");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Apellido Materno");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Perfil");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Telefono Fijo");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("DNI");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Celular");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Correo Electronico");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Contraseña");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Intereses");
                        break;
                    case CargaMasivaConstantes.TABLA_PROVEEDORES:
                        rowhead.createCell(rowIndex).setCellValue("Nombre");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Ruc");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        break;
                    case CargaMasivaConstantes.TABLA_INSUMOS:
                        rowhead.createCell(rowIndex).setCellValue("Nombre");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Precio");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Tiempo de Vida (dias)");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Volumen por Unidad");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Imagen (Direccion Web)");
                        break;
                    case CargaMasivaConstantes.TABLA_TIENDAS:
                        rowhead.createCell(rowIndex).setCellValue("Direccion");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Ubicacion, Eje X");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Ubicacion, Eje Y");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Capacidad en peso");
                        break;
                    case CargaMasivaConstantes.TABLA_TIPOMOV:
                        rowhead.createCell(rowIndex).setCellValue("Nombre");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        break;
                    case CargaMasivaConstantes.TABLA_PERMISOS:
                        rowhead.createCell(rowIndex).setCellValue("Menu");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Icono");
                        break;
                    case CargaMasivaConstantes.TABLA_PERFILXPERMISO:
                        rowhead.createCell(rowIndex).setCellValue("Nombre de Perfil");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Opcion de Permiso");
                        break;
                    case CargaMasivaConstantes.TABLA_FRAGILIDAD:
                        rowhead.createCell(rowIndex).setCellValue("Valor de Fragilidad");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        break;
                    case CargaMasivaConstantes.TABLA_PRODUCTOS:
                        rowhead.createCell(rowIndex).setCellValue("Nombre");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Precio Unitario");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Peso");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Imagen (Direccion Web)");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Descripcion");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Categoria");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Nivel de Fragilidad");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Volumen");
                        break;
                    case CargaMasivaConstantes.TABLA_PRODXINSUMO:
                        rowhead.createCell(rowIndex).setCellValue("Nombre de Producto");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Nombre de Insumo");
                        rowIndex++;
                        rowhead.createCell(rowIndex).setCellValue("Cantidad");
                        break;
                    case CargaMasivaConstantes.TABLA_TIPOPAGO:
                        rowhead.createCell(rowIndex).setCellValue("Descripcion del Tipo de Pago");
                        break;
                    // agregar aqui el resto de casos
                    default:
                        LOGGER.log(Level.WARNING, "Tabla no reconocida, abortando ....");
                        return;
                }
                // dar formato a celdas
                for (int i=0;i<=rowIndex;i++)
                    sheet.autoSizeColumn(i);
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(destinoTemplate);
                workbook.write(fileOut);
                fileOut.close();
                LOGGER.log(Level.INFO, "Plantilla(s) creada(s) con exito");
                }
            catch(Exception ex) {
                LOGGER.log(Level.SEVERE, "Error al generar la(s) plantilla(s)");
                System.out.print(ex);
            }
        }
    }
    
    /* forma de consumo : se pasa como unico parametro la ruta del archivo, el metodo identificara las hojas del archivo e iniciara la carga masiva */
    public static void CargaMasivaProceso(String archivoRuta) {
        try {
            // Declarando e inicializando variables a utilizar
            DataFormatter dataFormatter = new DataFormatter();
            Workbook workbook = WorkbookFactory.create(new File(archivoRuta));
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            Sheet sheet;
            String sheetName;
            Row row;
            Iterator<Row> rowIterator;
            //Iterator<Cell> cellIterator;
            int casosExitosos, casosFallidos;
            int hojasReconocidas = 0;
            // Abriendo conexion a Base de Datos
            Configuration config;
            SessionFactory sessionFactory;
            Session session;
            config = new Configuration();
            config.configure("hibernate.cfg.xml");
            sessionFactory = config.buildSessionFactory();
            session = sessionFactory.openSession();
            LOGGER.log(Level.INFO, "Se procede a inspeccionar archivo ...");
            // se itera sobre la prioridad establecida en CargaMasivaConstantes
            for (String tablaEnCola : CargaMasivaConstantes.getList()) {
                sheet = workbook.getSheet(tablaEnCola);
                if (sheet!=null) {
                    sheetName = sheet.getSheetName();
                    LOGGER.log(Level.INFO, String.format("Hoja %s reconocida, iniciando proceso ...", sheetName));
                    hojasReconocidas++;
                    rowIterator = sheet.rowIterator();
                    rowIterator.next(); // nos saltamos la cabecera
                    // inicializamos contadores
                    casosExitosos = 0;
                    casosFallidos = 0;
                    // a este punto ya estamos en la fila inicial para comenzar a leer y cargar datos
                    while (rowIterator.hasNext()) {
                        row = rowIterator.next();
                        if (CargaMasivaHelper.SubirRegistroBD(sheetName, row, dataFormatter, session)) casosExitosos++;
                        else casosFallidos++;
                    }
                    LOGGER.log(Level.INFO, String.format("Hoja %s finalizada, reporte :", sheetName));
                    LOGGER.log(Level.INFO, String.format("Casos Exitosos : %d", casosExitosos));
                    LOGGER.log(Level.INFO, String.format("Casos Fallidos : %d", casosFallidos));
                }
            }
            // Cerrando conexion a Base de Datos
            session.close();
            sessionFactory.close();
            workbook.close();
            LOGGER.log(Level.INFO, "Procesamiento Finalizado, reporte final :");
            LOGGER.log(Level.INFO, String.format("Cantidad de Hojas Procesadas : %s", hojasReconocidas));
        }
        catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "Error al cargar masivamente, revisar la ruta del archivo");
            System.out.print(ex);
        }
    }
    
    /* Fin : Metodos disponibles */
    
    /* Inicio : Metodos de Apoyo */
    
    // solo retorna el primero que encuentra
    private static Object busquedaGeneralInt(Session session, String nombreEntidad, String [] condiciones, int [] valoresCondiciones) {
        String hqlQuery = String.format("from %s where %s = %d", nombreEntidad, condiciones[0], valoresCondiciones[0]);
        for (int i=1;i<condiciones.length;i++)
            hqlQuery += String.format(" and %s = %d", condiciones[i], valoresCondiciones[i]);
        try{
            List<Object> resultadoBusqueda = session.createQuery(hqlQuery).list();
            return resultadoBusqueda.get(0);
        }
        catch(Exception e) {
            LOGGER.log(Level.WARNING, String.format("La busqueda de la entidad %s fallo", nombreEntidad));
            System.out.println("====================================================================");
            System.out.println(e);
            System.out.println("====================================================================");
            return null;
        }
    }
    
    // solo retornara el primero que encuentre
    private static Object busquedaGeneralString(Session session, String nombreEntidad, String [] condiciones, String [] valoresCondiciones) {
        String hqlQuery = String.format("from %s where %s='%s'", nombreEntidad, condiciones[0], valoresCondiciones[0]);
        for (int i=1;i<condiciones.length;i++)
            hqlQuery += String.format(" and %s='%s'", condiciones[i], valoresCondiciones[i]);
        try{
            List<Object> resultadoBusqueda = session.createQuery(hqlQuery).list();
            return resultadoBusqueda.get(0);
        }
        catch(Exception e) {
            LOGGER.log(Level.WARNING, String.format("La busqueda de la entidad %s fallo", nombreEntidad));
            System.out.println("====================================================================");
            System.out.println(e);
            System.out.println("====================================================================");
            return null;
        }
    }
    
    // implementacion de logica para cada tipo de tabla a cargar en bd, por cada registro a escanear
    private static boolean SubirRegistroBD(String tablaCarga, Row row, DataFormatter dataFormatter, Session session) {
        int index = 0;
        switch(tablaCarga) {
            case CargaMasivaConstantes.TABLA_PRODUCTOCATEGORIA:
                ProductoCategoria nuevoProdCat = new ProductoCategoria();
                nuevoProdCat.setActivo(true);   // logica de negocio
                String nombreProdCat = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombreProdCat))
                    nuevoProdCat.setNombre(nombreProdCat);
                else {
                    LOGGER.log(Level.SEVERE, "El nombre de una categoria de producto es un campo obligatorio");
                    return false;
                }
                index++;
                nuevoProdCat.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevoProdCat, session);
            case CargaMasivaConstantes.TABLA_PERFILES:
                Perfil nuevoPerfil = new Perfil();
                nuevoPerfil.setActivo(true);    // logica de negocio
                String nombrePerfil = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombrePerfil))
                    nuevoPerfil.setNombre(nombrePerfil);
                else {
                    LOGGER.log(Level.SEVERE, "El nombre de un perfil es un campo obligatorio");
                    return false;
                }
                index++;
                nuevoPerfil.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevoPerfil, session);
            case CargaMasivaConstantes.TABLA_USUARIOS:
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setActivo(true);   // logica de negocio
                String nombreUsuario = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombreUsuario))
                    nuevoUsuario.setNombres(nombreUsuario);
                else {
                    LOGGER.log(Level.SEVERE, "El nombre de usuario es un campo obligatorio");
                    return false;
                }
                index++;
                String apPaternoUsuario = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(apPaternoUsuario))
                    nuevoUsuario.setApellidoPaterno(apPaternoUsuario);
                else {
                    LOGGER.log(Level.SEVERE, "El apellido paterno es un campo obligatorio");
                    return false;
                }
                index++;
                String apMaternoUsuario = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(apMaternoUsuario))
                    nuevoUsuario.setApellidoMaterno(apMaternoUsuario);
                else {
                    LOGGER.log(Level.SEVERE, "El apellido materno es un campo obligatorio");
                    return false;
                }
                index++;
                String perfilNombre = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                // Buscando el perfil elegido
                Perfil perfilBuscado = (Perfil) CargaMasivaHelper.busquedaGeneralString(session, "Perfil", new String [] {"nombre"}, new String [] {perfilNombre});
                if (perfilBuscado!=null)
                    LOGGER.log(Level.INFO, String.format("Perfil %s encontrado con exito", perfilNombre));
                else {
                    LOGGER.log(Level.SEVERE, String.format("Perfil %s no encontrado, cancelando operacion", perfilNombre));
                    return false;
                }
                nuevoUsuario.setPerfil(perfilBuscado);
                index++;
                nuevoUsuario.setTelefono(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                String dniUsuario = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(dniUsuario))
                    nuevoUsuario.setDni(dniUsuario);
                else {
                    LOGGER.log(Level.SEVERE, "El DNI es un campo obligatorio");
                    return false;
                }
                index++;
                nuevoUsuario.setCelular(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                // otener correo
                String correoUsuario = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(correoUsuario))
                    nuevoUsuario.setCorreo(correoUsuario);
                else {
                    LOGGER.log(Level.SEVERE, String.format("No se introdujo un correo valido para el usuario %s", nuevoUsuario.getNombres()));
                    return false;
                }
                index++;
                // obtener contrasenia
                String constraseniaUsuario = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(constraseniaUsuario))
                    nuevoUsuario.setPassword(LoginController.encrypt(constraseniaUsuario));
                else {
                    LOGGER.log(Level.SEVERE, String.format("No se introdujo una contraseña valida para el correo %s", correoUsuario));
                    return false;
                }
                index++;
                nuevoUsuario.setIntereses(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevoUsuario, session);
            case CargaMasivaConstantes.TABLA_PROVEEDORES:
                Proveedor nuevoProv = new Proveedor();
                nuevoProv.setNombre(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                String rucProved = (String) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), true);
                if (rucProved!=null)
                    nuevoProv.setRuc(rucProved);
                else
                    return false;
                index++;
                nuevoProv.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevoProv, session);
            case CargaMasivaConstantes.TABLA_INSUMOS:
                Insumo nuevoInsumo = new Insumo();
                nuevoInsumo.setActivo(true);    // logica de negocio
                nuevoInsumo.setStockTotalFisico(0);        // logica de negocio, se inicializa nuevo insumo
                nuevoInsumo.setStockTotalLogico(0);
                String nombreInsumo = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombreInsumo))
                    nuevoInsumo.setNombre(nombreInsumo);
                else {
                    LOGGER.log(Level.SEVERE, "El nombre de insumo es un campo obligatorio");
                    return false;
                }
                index++;
                nuevoInsumo.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                // se trata de obtener el precio del insumo
                Double precioInsumo = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                if (precioInsumo!=null)
                    nuevoInsumo.setPrecio(precioInsumo);
                else
                    return false;
                index++;
                Integer tiempoVidaInsumo = (Integer) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), true);
                if (tiempoVidaInsumo!=null)
                    nuevoInsumo.setTiempoVida(tiempoVidaInsumo);
                else
                    return false;
                index++;
                Double volumenInsumo = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                nuevoInsumo.setVolumen(volumenInsumo);
                index++;
                nuevoInsumo.setImagen(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevoInsumo, session);
            case CargaMasivaConstantes.TABLA_TIENDAS:
                Tienda nuevaTienda = new Tienda();
                nuevaTienda.setDireccion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                Double codX = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                if (codX!=null)
                    nuevaTienda.setCooXDireccion(codX);
                else
                    return false;
                index++;
                Double codY = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                if (codY!=null)
                    nuevaTienda.setCooYDireccion(codY);
                else
                    return false;
                index++;
                nuevaTienda.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                Double capacidadTienda = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                if (capacidadTienda!=null && capacidadTienda >= 0.0)
                    nuevaTienda.setCapacidad(capacidadTienda);
                else
                    return false;
                return CargaMasivaHelper.guardarObjeto(nuevaTienda, session);
            case CargaMasivaConstantes.TABLA_TIPOMOV:
                TipoMovimiento nuevoTipoMov = new TipoMovimiento();
                String nombreTipoMov = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombreTipoMov))
                    nuevoTipoMov.setNombre(nombreTipoMov);
                else {
                    LOGGER.log(Level.SEVERE, "El nombre del tipo de movimiento es un campo obligatorio");
                    return false;
                }
                index++;
                nuevoTipoMov.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevoTipoMov, session);
            case CargaMasivaConstantes.TABLA_PERMISOS:
                Permiso nuevoPermiso = new Permiso();
                String menuPermiso = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(menuPermiso))
                    nuevoPermiso.setMenu(menuPermiso);
                else {
                    LOGGER.log(Level.SEVERE, "El menu de un permiso es un campo obligatorio");
                    return false;
                }
                index++;
                String iconoPermiso = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(iconoPermiso))
                    nuevoPermiso.setIcono(iconoPermiso);
                else {
                    LOGGER.log(Level.SEVERE, "El icono de un permiso es un campo obligatorio");
                    return false;
                }
                return CargaMasivaHelper.guardarObjeto(nuevoPermiso, session);
            case CargaMasivaConstantes.TABLA_PERFILXPERMISO:
                String perfilNombreAux = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                String permisoOpcionAux = null;
                Perfil perfilAsociado = (Perfil) CargaMasivaHelper.busquedaGeneralString(session, "Perfil", new String [] {"nombre"}, new String [] {perfilNombreAux});
                if (perfilAsociado!=null) { // si el perfil mencionado fue encontrado entonces se continua con el proceso
                    LOGGER.log(Level.INFO, String.format("Perfil %s encontrado con exito", perfilNombreAux));
                    while (true) {
                        index++;
                        if (StringUtils.isBlank(dataFormatter.formatCellValue(row.getCell(index))))
                            break;
                        permisoOpcionAux = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                        Permiso permisoAux = (Permiso) CargaMasivaHelper.busquedaGeneralString(session, "Permiso", new String [] {"opcion"}, new String [] {permisoOpcionAux});
                        if (permisoAux!=null) {
                            LOGGER.log(Level.INFO, String.format("Permiso %s encontrado con exito", permisoOpcionAux));
                            perfilAsociado.getPermisos().add(permisoAux);
                        }
                        else
                            LOGGER.log(Level.WARNING, String.format("Permiso %s no encontrado, este permiso no sera considerado", permisoOpcionAux));
                    }
                    /*
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        permisoOpcionAux = StringUtils.trimToEmpty(dataFormatter.formatCellValue(cell));
                        Permiso permisoAux = (Permiso) CargaMasivaHelper.busquedaGeneralString(session, "Permiso", new String [] {"opcion"}, new String [] {permisoOpcionAux});
                        if (permisoAux!=null) {
                            LOGGER.log(Level.INFO, String.format("Permiso %s encontrado con exito", permisoOpcionAux));
                            perfilAsociado.getPermisos().add(permisoAux);
                        }
                        else
                            LOGGER.log(Level.WARNING, String.format("Permiso %s no encontrado, este permiso no sera considerado", permisoOpcionAux));
                    }
                    */
                }
                else {
                    LOGGER.log(Level.SEVERE, String.format("Perfil %s no encontrado, cancelando operacion", perfilNombreAux));
                    return false;
                }
                return CargaMasivaHelper.actualizarObjeto(perfilAsociado, session);
            case CargaMasivaConstantes.TABLA_FRAGILIDAD:
                ProductoFragilidad nuevaFrag = new ProductoFragilidad();
                String valorCandea = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                Integer valorParseado = (Integer) CargaMasivaHelper.validarParsing(valorCandea, true);
                if (valorParseado!=null && valorParseado>0)
                    nuevaFrag.setValor(valorParseado);
                else {
                    LOGGER.log(Level.SEVERE, "El valor de fragilidad no es valido");
                    return false;
                }
                index++;
                nuevaFrag.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                return CargaMasivaHelper.guardarObjeto(nuevaFrag, session);
            case CargaMasivaConstantes.TABLA_PRODUCTOS:
                Producto nuevoProd = new Producto();
                nuevoProd.setActivo(true);  // logica de negocio
                nuevoProd.setStockFisico(0);  // logica de negocio
                nuevoProd.setStockLogico(0);
                nuevoProd.setNombre(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                nuevoProd.setPrecio((Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false));
                index++;
                Double pesoProd = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                if (pesoProd!=null)
                    nuevoProd.setPeso(pesoProd);
                else
                    return false;
                index++;
                nuevoProd.setImagen(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                nuevoProd.setDescripcion(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))));
                index++;
                String nombreCategoriaAsociada = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombreCategoriaAsociada)) {
                    ProductoCategoria categoriaAsociada = (ProductoCategoria) CargaMasivaHelper.busquedaGeneralString(session, "ProductoCategoria", new String[] {"nombre"}, new String[] {nombreCategoriaAsociada});
                    if (categoriaAsociada!=null){
                        LOGGER.log(Level.INFO, String.format("Categoria %s encontrado con exito", nombreCategoriaAsociada));
                        nuevoProd.setCategoria(categoriaAsociada);
                    }
                    else
                        LOGGER.log(Level.SEVERE, String.format("Categoria %s no encontrada, no se tendra en consideracion", nombreCategoriaAsociada));
                }
                else
                    LOGGER.log(Level.WARNING, String.format("No se especifico categoria para producto %s, se continua el proceso", nuevoProd.getNombre()));
                index++;
                String intensidadAsociada = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(intensidadAsociada)) {
                    Integer valorIntensidadAsociada = (Integer) validarParsing(intensidadAsociada, true);
                    if (valorIntensidadAsociada != null) {
                        ProductoFragilidad fragilidadAsociada = (ProductoFragilidad) CargaMasivaHelper.busquedaGeneralInt(session, "ProductoFragilidad", new String [] {"valor"}, new int [] {valorIntensidadAsociada});
                        if (fragilidadAsociada!=null) {
                            LOGGER.log(Level.INFO, String.format("Fragilidad %s encontrada con exito", intensidadAsociada));
                            nuevoProd.setFragilidad(fragilidadAsociada);
                        }
                        else
                            LOGGER.log(Level.SEVERE, String.format("Fragilidad %s no encontrada, no se tendra en consideracion", intensidadAsociada));
                    }
                }
                else
                    LOGGER.log(Level.WARNING, String.format("No se especifico fragilidad para producto %s, se continua el proceso", nuevoProd.getNombre()));
                index++;
                String volumenCadena = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                Double volumenValor = (Double) CargaMasivaHelper.validarParsing(volumenCadena, false);
                if (volumenValor!=null)
                    nuevoProd.setVolumen(volumenValor);
                else
                    return false;
                return CargaMasivaHelper.guardarObjeto(nuevoProd, session);
            case CargaMasivaConstantes.TABLA_PRODXINSUMO:
                String nombreProductoAsociado = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(nombreProductoAsociado)) {
                    Producto prodAsociado = (Producto) CargaMasivaHelper.busquedaGeneralString(session, "Producto", new String[] {"nombre"}, new String[] {nombreProductoAsociado});
                    if (prodAsociado!=null) {
                        LOGGER.log(Level.INFO, String.format("Producto %s encontrado con exito", nombreProductoAsociado));
                        index++;
                        String nombreInsumoAsociado = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                        if (StringUtils.isNotBlank(nombreInsumoAsociado)) {
                            Insumo insumoAsociado = (Insumo) CargaMasivaHelper.busquedaGeneralString(session, "Insumo", new String[] {"nombre"}, new String[] {nombreInsumoAsociado});
                            if (insumoAsociado!=null) {
                                LOGGER.log(Level.INFO, String.format("Insumo %s encontrado con exito", nombreInsumoAsociado));
                                index++;
                                Double cantidadAsociada = (Double) CargaMasivaHelper.validarParsing(StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index))), false);
                                if (cantidadAsociada!=null) {
                                    ProductoInsumo nuevoProdxInsumo = new ProductoInsumo();
                                    nuevoProdxInsumo.setProducto(prodAsociado);
                                    nuevoProdxInsumo.setInsumo(insumoAsociado);
                                    nuevoProdxInsumo.setCantidad(cantidadAsociada);
                                    return CargaMasivaHelper.guardarObjeto(nuevoProdxInsumo, session);
                                }
                                else 
                                    return false;
                            }
                            else {
                                LOGGER.log(Level.SEVERE, String.format("Insumo %s no encontrado, cancelando operacion", nombreInsumoAsociado));
                                return false;
                            }
                        }
                        else {
                            LOGGER.log(Level.SEVERE, String.format("Insumo %s no detectado", nombreInsumoAsociado));
                            return false;
                        }
                    }
                    else {
                        LOGGER.log(Level.SEVERE, String.format("Producto %s no encontrado, cancelando operacion", nombreProductoAsociado));
                        return false;
                    }
                }
                else {
                    LOGGER.log(Level.SEVERE, String.format("Producto %s no detectado", nombreProductoAsociado));
                    return false;
                }
            case CargaMasivaConstantes.TABLA_TIPOPAGO:
                String descripTipoPago = StringUtils.trimToEmpty(dataFormatter.formatCellValue(row.getCell(index)));
                if (StringUtils.isNotBlank(descripTipoPago)) {
                    TipoPago nuevoTipoPago = new TipoPago();
                    nuevoTipoPago.setDescripcion(descripTipoPago);
                    return CargaMasivaHelper.guardarObjeto(nuevoTipoPago, session);
                }
                else {
                    LOGGER.log(Level.SEVERE, "No se identifica una descripcion valida de tipo de pago");
                    return false;
                }
            // colocar aqui los demas casos para el resto de tablas de carga masiva
            default:
                return false;
        }
    }
    
    public static Object validarParsing(String numero, boolean esInteger) {
        try {
            System.out.println(String.format("Valor a ser parseado %s", numero));
            if (esInteger)
                return Integer.valueOf(numero);
            else
                return Double.valueOf(numero);
        }
        catch(Exception e) {
            LOGGER.log(Level.SEVERE, String.format("Error en el parseo, revisar el valor : %s", numero));
            return null;
        }
    }
    
    public static boolean actualizarObjeto(Object object, Session session) {
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
            LOGGER.log(Level.FINE, "Objeto actualizado con exito");
            return true;
        }
        catch(Exception he) {
            if (tx!=null)   tx.rollback();
            LOGGER.log(Level.SEVERE, "Error al intentar actualizar objeto");
            System.out.println("====================================================================");
            System.out.println(he);
            System.out.println("====================================================================");
            return false;
        }
    }
    
    public static boolean guardarObjeto(Object object, Session session) {
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(object);
            tx.commit();
            LOGGER.log(Level.FINE, "Objeto guardado con exito");
            return true;
        }
        catch(Exception he) {
            if (tx!=null)   tx.rollback();
            LOGGER.log(Level.SEVERE, "Error al intentar guardar objeto");
            System.out.println("====================================================================");
            System.out.println(he);
            System.out.println("====================================================================");
            return false;
        }
    }
    
    /* Fin : Metodos de Apoyo */
    
}