package com.xxx.prospect.utilitarios.gener;

public class ConstantesClienteService {
	/**
	 * Variables para el status de cliente
	 * @author OLM05871
	 *
	 */
	public static class CLienteStatus {
		
		//public static final String  URLSERVICE = "http://172.23.50.204:24200/customer/v1/customer_information/status?";
		public static final Integer STATUSVALCODE = 0;
		public static final String STATUSVALPARTYSTATUS = "1_2_3";
		

		public static final String STATUSCODEOK = "200_";
		public static final String STATUSOKMENSAJE = "Cliente status aprobado.";
		public static final String STATUSCODEERROR = "422-01_";
	
		public static final String STATUSMENSAJEERRORRECHAZADO = "Cliente rechazado o existente.";
		
		//Errores 500 
		public static final String STATUSCODEERROR500 = "500_";
		public static final String STATUSMESAJEERROR500 = "Error al consumir servicio CustomerInformation Status.";
	}
	
	/**
	 * Variables para data cliente
	 * @author OLM05871
	 *
	 */
	public static class  CLienteData {
		//public static final String  URLSERVICE = "http://172.23.50.204:24200/customer/v1/customer_information/data?";
		public static final Integer ADDITIONALINFOID = 113;
		public static final String ADDITIONALINFOVALUE = "NO";
		public static final String STATUSCODEOK = "200_";
		public static final String STATUSOKMENSAJE = "Cliente data aprobado.";
		
		public static final String STATUSCODEERROR = "422-02_";
		public static final String STATUSMENSAJEERROR = "El cliente PEP.";
		
		//Errores 500 
		public static final String STATUSCODEERROR500 = "500_";
		public static final String STATUSMESAJEERROR500 = "Error al consumir servicio CustomerInformation";

	}

	/**
	 * Variables para la Listas del cliente
	 * @author OLM05871
	 *
	 */
	public static class  CLienteList {
		//public static final String  URLSERVICE = "http://172.23.50.204:24200/customer/v1/restrictive_lists/lists?";
		public static final Integer VALACEPTADO = 1;
		public static final Integer VALAPROBADO = 4;
		
		public static final String STATUSCODEOK = "200_";
		public static final String STATUSOKMENSAJE = "Cliente no se encuentra en listas de riesgo.";
		
		public static final String STATUSCODEERROR = "422-03_";
		public static final String STATUSMENSAJEERROR = "El cliente invalido por listas restrictivas";
		
		// Errores 500 
		public static final String STATUSCODEERROR500 = "500_";
		public static final String STATUSMESAJEERROR500 = "Error al consumir servicio RestrictiveList";
	}
	
	/**
	 * Variables para la consulta del cliente
	 * @author OLM05871
	 *
	 */
	public static class CLienteConsulta {
		//public static final String  URLSERVICE = "http://172.23.50.12:8420/autenticacion/bytte/usuario?";
		public static final String OPERACIONVALEXITOSA = "true";
		public static final String ENROLADOVALEXITOSO = "true";
		
		public static final String STATUSCODEOK = "200_";
		public static final String STATUSOKMENSAJE = "Cliente data aprobado.";
		
		
		public static final String STATUSCODEERROR = "422-04_";
		public static final String STATUSMENSAJEERROR = "El cliente no enrolado.";
		
		//Errores 500 
		public static final String STATUSCODEERROR500 = "500_";
		public static final String STATUSMESAJEERROR500 = "Error el servicio de consultar usuario.";
	}
	
	/**
	 * Variables para la consultaANi del cliente
	 * @author OLM05871
	 *
	 */
	public static class CLienteConsultaAni {
		//public static final String  URLSERVICE = "http://172.23.50.12:8420/autenticacion/bytte/ani?";
		public static final String OPERACIONVALEXITOSA = "true";
		public static final String ESTADOVALDOCUMENTO = "0_1";
		
		public static final String STATUSCODEOK = "200_";
		public static final String STATUSOKMENSAJE = "Cliente data aprobado.";
		
		
		public static final String STATUSCODEERROR = "422-05_";
		public static final String STATUSMENSAJEERROR = "Cliente invalido por proceso Ani en registraduría";
		
		//Errores 500 
		public static final String STATUSCODEERROR500 = "500_";
		public static final String STATUSMESAJEERROR500 = "Error el servicio de consultar usuario Ani.";
	}
	
	
	/**
	 * Variables para la consultaANi del cliente
	 * @author OLM05871
	 *
	 */
	public static class REsponseOk {

		
		public static final String STATUSCODEOK = "200_";
		public static final String STATUSOKMENSAJE = "Cliente aprobado";
	
	}
}
