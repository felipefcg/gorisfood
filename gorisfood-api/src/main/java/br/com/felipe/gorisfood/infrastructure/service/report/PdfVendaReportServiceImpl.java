package br.com.felipe.gorisfood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.service.VendaQueryService;
import br.com.felipe.gorisfood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Override
	public byte[] consultaVendasDiaria(VendaDiariaFilter filtro, String timeOffset) {
		try {
			var jasperInputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			var dataSource = new JRBeanCollectionDataSource(vendaQueryService.consultaVendasDiaria(filtro, timeOffset));
		
			var jasperPrint = JasperFillManager.fillReport(jasperInputStream, parametros, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relatório de vendas diárias");
		}
		
	}

}
