package anatlyzer.experiments.featurecoverage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.core.resources.IFolder;

import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.featurecoverage.raw.CFeature;
import anatlyzer.experiments.featurecoverage.raw.CTransformation;
import anatlyzer.experiments.featurecoverage.raw.CoverageData;

public class ExcelExporter {

	public void export(CoverageData data, String output) {
			
		try {
			Workbook wb = new HSSFWorkbook();
			exportTable(wb, data);
			wb.write(new FileOutputStream(output));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
	}


	private void exportTable(Workbook wb, CoverageData data) throws FileNotFoundException, IOException {
		Sheet sheet = wb.createSheet("Summary");
		Styler   st = new Styler(wb);

		List<String> allFeatures = data.getTransformations().stream().
				flatMap(t -> t.getFeatures().stream()).
				map(f -> f.getFeatureName()).
				collect(Collectors.toSet()).stream().
				sorted().
				collect(Collectors.toList());
		
		
		int startRow = 2;
		int startCol = 1;

		
		for(int i = 0; i < allFeatures.size(); i++) {
			st.cell(sheet, startRow + i + 1, startCol, allFeatures.get(i)).alignRight();;
		}
			
		
		int idx = 1;
		for (CTransformation t : data.getTransformations()) {
			st.cell(sheet, startRow, startCol + idx, t.getName()).bold();
			
			int rowIdx = 1;
			for (String f : allFeatures) {
				Optional<CFeature> r = t.getFeatures().stream().filter(cf -> cf.getFeatureName().equals(f)).findFirst();
				if ( r.isPresent() ) {
					st.cell(sheet, startRow + rowIdx, startCol + idx, r.get().getOccurences());
				}

					
				rowIdx++;
			}
			
			idx++;
		}
		
			
	}
	
}
