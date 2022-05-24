package com.ejercicio.implement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.dto.CodigoHum;
import com.ejercicio.services.ServiceCodigoHum;


@RestController
@RequestMapping("/mutant")
public class CodigoHumImp {

	@Autowired
	ServiceCodigoHum serviceCodigoHum;

	@GetMapping
	public ArrayList<CodigoHum> obtenerCodigos(){
		return serviceCodigoHum.obtenerCodigos();
	}

	@Autowired
	@Qualifier("jdbcMaster")
	private JdbcTemplate jdbcTemplate;

	@PostMapping
	public CodigoHum guardarCodigo(@RequestBody CodigoHum codigo) {

		Map<String, Object> resultMap = null;
		ArrayList<SqlParameter> parameters = (ArrayList<SqlParameter>) Arrays.asList(new SqlParameter(Types.BIGINT));
		ArrayList<CodigoHum> lstCodigoHum = new ArrayList<CodigoHum>();

		try {
			resultMap = jdbcTemplate.call(new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					String codigo1="";
					String codigo2="";
					String codigo3="";
					String codigo4="";
					String codigo5="";
					String codigo6="";
					String xmenb="";
					
					Map<String, Object> resultCad = null;
					
					CallableStatement callableStatement = con.prepareCall("{call [dbo].[codigohum](?)}");
					int id = 0;
					callableStatement.setInt(1, id);
					
					resultCad = jdbcTemplate.call(new CallableStatementCreator() {

						@Override
						public CallableStatement createCallableStatement(Connection con) throws SQLException {
							// TODO Auto-generated method stub
							return null;
						}}, parameters);

					@SuppressWarnings("unchecked")
					ArrayList<Object> lstObjetos = (ArrayList<Object>) resultCad.get("#result-set-1");

					for (int i = 0; i < lstObjetos.size(); i++) {
						Map<?, ?> rowMap = (Map<?, ?>) lstObjetos.get(i);
						CodigoHum cod = new CodigoHum(lstCodigoHum, null);
						cod.setId(Integer.parseInt(rowMap.get("id").toString()));
						cod.setCod1(rowMap.get("cod1").toString());
						codigo1 = cod.getCod1().toString();
						cod.setCod2(rowMap.get("cod2").toString());
						codigo2 = cod.getCod2().toString();
						cod.setCod3(rowMap.get("cod3").toString());
						codigo3 = cod.getCod3().toString();
						cod.setCod4(rowMap.get("cod4").toString());
						codigo4 = cod.getCod4().toString();
						cod.setCod5(rowMap.get("cod5").toString());
						codigo5 = cod.getCod5().toString();
						cod.setCod6(rowMap.get("cod6").toString());
						codigo6 = cod.getCod6().toString();

						if (vallet(codigo1, codigo2, codigo3, codigo4, codigo5, codigo6)) {
							if (validxmen(codigo1, codigo2, codigo3, codigo4, codigo5, codigo6)) {
								cod.setXmen("true");
							}else {
								cod.setXmen("false");
							}
						}else {
							System.out.println("Código ingresado contiene caracteres no válidos");
						}

						lstCodigoHum.add(cod);

					}
					return callableStatement;
				}
			}, parameters);
			return this.serviceCodigoHum.GuardarCodigo(lstCodigoHum);
		} catch (Exception e) {
			// TODO: handle exception
			return new CodigoHum(lstCodigoHum, HttpStatus.NOT_FOUND);
		}
	}

	private boolean validxmen(String cadena1, String cadena2, String cadena3, String cadena4, String cadena5, String cadena6) {
		int cont = 0;
		String carac="";

		for (int i=0; i < 6; i++) {
			for (int j=0; j < 6; j++) {
				if (carac==("cadena" + (i+1)).substring(i, i)) {
					cont++;
				}else {
					cont=0;
				}

				if (cont == 4) {
					return true;
				}
				carac=("cadena" + (i+1)).substring(i, i);
			}

		}

		cont=0;

		for (int i=0; i < 6; i++) {
			for (int j=0; j < cadena1.length(); j++) {
				if (("cadena"+(i+1)).substring(j, j)==("cadena"+(i+2)).substring(j, j)) {
					cont++;
				}else {
					cont=0;
				}

				if (cont == 4) {
					return true;
				}
			}
		}

		cont=0;

		for (int i=0; i < 6; i++) {
			for (int j=0; j < cadena1.length(); j++) {
				if (j<3) {
					if (("cadena"+(i+1)).substring(j, j)==("cadena"+(i+2)).substring((j+1), (j+1))) {
						cont++;
					}else {
						cont=0;
					}

					if (cont == 4) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean vallet(String cadena1, String cadena2, String cadena3, String cadena4, String cadena5, String cadena6) {
		String carval[] = {"A","T","C","G"};

		for (int i=0; i < carval.length; i++) {
			for (int j=0; j < 6; j++) {
				for (int k=0; k < 6; k++) {
					if (("cadena"+(j+1)).substring(k, k) != (carval[i])) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
