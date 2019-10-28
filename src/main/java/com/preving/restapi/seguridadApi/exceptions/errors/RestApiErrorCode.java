package com.preving.restapi.seguridadApi.exceptions.errors;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeration of Error or Exception codes.
 *
 * @author Javier Montesinos
 * @since 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RestApiErrorCode {
    /**
     * {@code 1001 "Datos del usuario incorrectos. Validacion no aceptada}.
     */
    USER_INCORRECT(1001, "Revise los datos del usuario. Validación de datos incorrecta"),

    USER_NOT_FOUND(1002, "No se encuentra el usuario en el sistema"),



    // c�digos >= 1020 para clientes
    ERROR_VALIDACION_CLIENTE(1020, "Error al validar el cliente"),

    ERROR_CREANDO_CLIENTE(1021, "Error creando cliente"),

    ERROR_GET_CLIENTE(1022, "Error en la búsqueda de clientes"),

    ERROR_MODIFICANDO_CLIENTE(1023, "Error modificando cliente"),


    // c�digos >= 1050 para ofertas
    OFERTA_NO_VALIDA(1050, "Oferta no valida"),

    OFERTA_NOT_FOUND(1051, "No se encuentra la oferta en el sistema"),

    ERROR_BORRAR_OFERTA(1054, "Error al borrar la oferta"),

    ERROR_ENVIAR_OFERTA(1055, "Error al enviar la oferta por email"),

    ERROR_GENERAR_PDF(1056, "Error en la generación del pdf"),

    ERROR_ESTADO_OFERTA_GENERAR_PDF(1057, "Solo se puede generar PDF sobre una oferta emitida que no haya promocionado a contrato"),

    ERROR_CONX_OFFICE(1058, "Error en la conexión con el servidor de office"),

    ERROR_ACCESO_PLANTILLA(1059, "Error al acceder a la plantilla"),

    ERROR_IMPRESION_DOC(1060, "Error al formar los elementos de la plantilla"),

    ERROR_MAP_CONF_DOC(1061, "Error de datos al configurar el mapa"),

    ERROR_NO_ESTADO_OFERTADA_OFERTA(1062, "No se puede eliminar la oferta, no está en estado ofertada"),

    ERROR_CALCULO_PRECIOS_OFERTA(1063, "No se pueden calcular los precios de la oferta"),

    ERROR_EDICION_SERVICIO(1064, "Error en la edición de un servicio asociado a una oferta"),

    ERROR_ELIMINACION_SERVICIO(1164, "No est� permitida la eliminaci�n del servicio seleccionado"),

    ERROR_VALIDAR_CENTRO(1065, "Error al validar el centro"),

    ERROR_BORRAR_CENTRO_OFERTA(1066, "Error al borrar un centro en la oferta"),

    CONTRATO_NO_VALIDO(1067, "Contrato no conformado correctamente"),

    ERROR_CREAR_CENTRO_OFERTA(1068, "Error al crear un centro en la oferta"),

    ERROR_EDITAR_CENTRO(1069, "Error al actualizar un centro"),

    ERROR_CONSULTAR_CENTRO(1070, "Error al consultar un centro"),

    ERROR_VALIDACION_MIGRAR_CONTRATO_NMCTOGC(1071, "Error en la validación al migrar un contrato de NMC a GC"),

    ERROR_MIGRAR_CONTRATO_NMCTOGC(1072, "Error al migrar un contrato de NMC a GC"),

    CONTRATO_NOT_FOUND(1073, "No se encuentra el contrato en el sistema"),

    ERROR_INSERTAR_PDF(1074, "Error al insertar el pdf"),

    ERROR_TAMANIO_PDF(1075, "Error, el archivo no puede ser superior a 10MB"),

    ERROR_OBTENER_PDF(1076, "Error al obtener el documento PDF del servidor"),

    ERROR_BORRAR_PDF(1077, "Error al borrar el documento PDF"),

    ERROR_MODIFICAR_PDF(1078, "Error al modificar el pdf"),

    ERROR_IBAN_NO_ENCONTRADO(1079, "Error, el número iban no se a encontrado"),

    ERROR_ENVIO_MAIL_NO_MAIL(1080, "Error, no ha sido posible enviar comunicación. El cliente no tiene definido email ni fax."),

    ERROR_ENVIO_MAIL(1081, "Error en el envío de mail."),

    ERROR_MAIL_YA_EXISTE(1082, "El mail que intenta registrar ya está en uso."),

    ERROR_ACCION_NO_DISPONIBLE_CONTRATO_ACEPTADO(1083, "Acción no disponible para contrato aceptado."),

    SOLICITUD_ANEXO_NO_VALIDA(1084, "Solicitud de anexo no conformada correctamente"),

    ERROR_SOLICITUD_ANEXO_PRODUCTO(1085, "Error al guardar solicitud de anexo producto"),

    ERROR_SOLICITUD_ANEXO(1086, "Error al guardar solicitud de anexo"),

    ERROR_BORRAR_SERVICIO_ANEXO(1087, "Error al borrar servicio de anexo"),

    ERROR_ACTUALIZAR_RRLL_TEMPORALES(1087, "Error al actualizar RRLL de oferta procedente de importación de cartera"),

    ERROR_GENERAR_CODIGO_BARRAS(1088, "Error al generar el codigo de barras"),


    ERROR_PORCENTAJE_SERVICIO(1089, "Sólo usuarios con un rol especial podr\u00E1n aceptar anexos con servicios que superen dicho porcentaje."),

    ERROR_CONTRATO_PENDIENTE (1090,"Tiene todavía algun contrato pendiente"),

    // C�digos >= 1500 para renovaciones
    CENTRO_RENOVAR_NO_VALIDO(1501, "Error al renovar la Programación Anual del Centro"),


    // c�digos >= 2000 para centros
    CENTRO_CODIGO_POSTAL_FUERA_RANGO(2000, "Error con código postal. Fuera de rango"),

    ERROR_VALIDACION_ASOCIAR_CENTRO_OFERTA(2001, "Error al asociar un centro a una oferta"),

    // c�digos > 2050 para servicios
    FILTRO_SERVICIO_NO_VALIDO(2050, "El filtro elegido no existe"),

    ERROR_SERVICIOS_NO_COMPATIBLES(2051, "Los servicios seleccionados no son compatibles entre si. Sólo será posible agregar servicios compatibles"),



    // c�digos > 3000 para anexos
    ANEXO_NO_VALIDO(3000, "Anexo no válido"),

    ERROR_LISTAR_ANEXOS(3001, "Error al listar los anexos"),

    ERROR_ACTUALIZAR_CLAUSULA_DG(3002, "Error al actualizar la cláusula datos generales"),

    ERROR_OBTENER_CLAUSULA_DF(3003, "Error al obtener la cláusula de datos facturación"),

    ERROR_ACTUALIZAR_CLAUSULA_DF(3004, "Error al actualizar la cláusula de datos facturación"),

    ERROR_ANEXO_NOT_FOUND(3005, "Error no se encuentra el anexo en el sistema"),

    ERROR_CREAR_ANEXO(3006, "Error al crear el anexo"),

    ERROR_DESCARGAR_DOCUMENTO_ESCANEADO(3007, "Error al descargar documento escaneado de anexo"),

    // c�digos >= 4000 para SERVICIOS ADICIONALES

    ERROR_ESCALAR_SERVICIO(4001, "No se ha podido escalar el servicio"),

    ERROR_SA_PARAMETROS_INCOMPATIBLES(4002, "Error en la gestion de Servicios Adicionales. Parámetros incompatibles"),

    ERROR_BORRAR_ACTIVIDAD(4003, "Error al borrar, una de las Actividades seleccionadas tiene tiempo imputado o la fecha de ejecución informada"),

    ERROR_OBTENER_ACTUACION(4004, "Error al obtener la actuación del Servicio Adicional para el nuevo Centro"),

    ERROR_ACTIVIDAD_NO_PERTENECIENTE_AL_CENTRO(4005, "Error, la actividad indicada no pertenece al Centro indicado"),

    ERROR_ASIGNAR_ACTIVIDAD(4006, "Error al asignar la actividad"),

    ERROR_NUMERO_HORAS_INCORRECTO(4007, "Error al Distribuir de forma homogenea, no hay Horas disponibles"),

    ERROR_CATEGORIA_SIN_JEFE_PRODUCTO(4008, "Error, existe ningún Jefe de Producto para la Categoría del Servicio."),

    ERROR_SIN_PERMISOS(4009,"Error, no tiene permisos para acceder a esta página"),

    ERROR_REALIZAR_ROLLBACK_PRODUCTOPRL(4010, "Error al realizar rollback del Producto"),

    ERROR_SIN_PERMISOS_ESCALAR(4011,"No tiene permisos para escalar el Servicio"),

    ERROR_SIN_PERMISOS_SERVICIO(4011,"No tiene permisos para realizar la acción solicitada"),

    ERROR_ACTUALIZAR_SERVICIO_ADICIONAL(4012, "Error al actualizar el Servicio Adicional"),

    ERROR_ACTIVIDADES_ASIGNADAS_MENOR_UNIDADES(4013, "Error al actualizar el Servicio Adicional, las unidades deben de ser mayor o igual que las asignadas"),

    ERROR_DESHACER_SERVICIO_BORRADO(4014, "No se ha podido volver añadir el Servicio Adicional borrado, ya existe    el Servicio para el concierto y año seleccionado"),

    // c�digos >= 5000 para firmas de documentos
    ERROR_DOCUMENTO_RECHAZADO(5000,"Error en los datos al rechazar el documento"),

    ERROR_ROL_ENDPOINT_FIRMAS(5001,"No tiene el rol necesario para acceder al recurso solicitado"),

    ERROR_FIRMAS_DELIVERY_INCORRECTO(5002,"El delivery indicado en la peticion no es correcto"),

    ERROR_DOCUMENTO_FIRMADO(5003,"Error en los datos al firmar el documento"),

    ERROR_NOMBREDOC_ENVIADO(5004,"El nombre del documento enviado no corresponde"),

    ERROR_ACTUALIZAR_DELIVERY(5005,"Error al actualizar el delivery"),

    ERROR_PETICION_FIRMA(5006,"Error al realizar peticion de firma"),

    ERROR_EVIDENCIAS_NODISP (5007,"Intente actualizar mas tarde. Tiempo medio: 2 horas "),



    // c�digos >= 9000 para excepciones no controladas y otros errores
    EXCEPCION_NO_CONTROLADA(9000, "Excepción no controlada por el sistema"),

    ERROR_ACCESO_DATOS(9001, "Error de acceso a datos"),

    ERROR_CODIFICACION_UNICIDAD(9002, "Imposible generar la oferta. Error al crear la codificación de unicidad"),

    ERROR_ACCESO(9003, "Sin acceso para realizar la acción solicitada");

    private final int value;
    private final String message;

    RestApiErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    /**
     * Return the integer value of this rest api code.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Return the message of this rest api code.
     */
    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return Integer.toString(this.value);
    }
    /**
     * Return the enum constant of this type with the specified numeric value.
     * @param codeValue the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
     */
    public static RestApiErrorCode valueOf(int codeValue) {
        for (RestApiErrorCode code : values()) {
            if (code.value == codeValue) {
                return code;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + codeValue + "]");
    }



}
