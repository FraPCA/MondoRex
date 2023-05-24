package model.Bean;

import java.sql.SQLException;
import java.sql.Date;
import java.util.Collection;

public interface MetodoPagamentoModel {
	public void doSave(MetodoPagamentoBean metodopagamento) throws SQLException;

	public boolean doDelete(String email_utente_pagamento, Integer CVCCVV, Date scadenza) throws SQLException;

	public MetodoPagamentoBean doRetrieveByKey(String email_utente_pagamento, Integer CVCCVV, Date scadenza) throws SQLException;
	
	public Collection<MetodoPagamentoBean> doRetrieveAll() throws SQLException;
	public Collection<MetodoPagamentoBean> doRetrieveAllForUtente(String email_utente_pagamento) throws SQLException;
	
}