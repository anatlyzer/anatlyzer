package anatlyzer.experiments.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CountingModel<ART extends IClassifiedArtefact> {
	protected HashMap<Category, List<ART>> results = new HashMap<Category, List<ART>>();
	protected int total = 0;
	protected int failed = 0;
	
	// From path to errors
	protected  HashMap<String, Exception> errors = new HashMap<String, Exception>();
	private boolean withRepetitions          = false;
	private boolean showRepetitionDetails    = false;
	private boolean showCategoryDescriptions = false;
	
	public void addCategory(String name) {
		
	}


	/**
	 * Adds an artefact to the list of artefacts that are processed. 
	 * 
	 * It is important to call this method before adding a classification
	 * for the resource (with {@link #addByCategory(String, IClassifiedArtefact)}).
	 * 
	 * Otherwise, the total count of processed artefacts may be wrong.
	 *  
	 * @param id The id of the artefact, as returned later by {@link IClassifiedArtefact#getId()}.
	 */
	public void processingArtefact(String id) {
		total++;		
	}
	
	public void addByCategory(Category category, ART analysedResource) {
		if ( ! results.containsKey(category) ) {
			results.put(category, new ArrayList<ART>());
		}
		
		List<ART> current = results.get(category);
		current.add(analysedResource);			
	}
	
	public void addByCategory(String categoryName, ART analysedResource) {
		Category category = new Category(categoryName);
		addByCategory(category, analysedResource);
	}

	public void addError(String analysedResource, Exception e) {
		failed++;
		errors.put(analysedResource, e);
	}

	
	public HashMap<String, Exception> getErrors() {
		return errors;
	}
	
	
	/**
	 * Return the categories, indexed by artefact id.
	 * @param all
	 * @return
	 */
	private HashMap<String, List<Category>> computeOverlappingDetectedCategories(boolean all) {
		HashMap<String, List<Category>> overlapping = new HashMap<String, List<Category>>();

		for (Entry<Category, List<ART>> entry : results.entrySet()) {
			for(IClassifiedArtefact p : entry.getValue()) {
				if ( ! overlapping.containsKey(p.getId())) {
					overlapping.put(p.getId(), new ArrayList<Category>());
				}
				overlapping.get(p.getId()).add(entry.getKey());
			}
		}
		
		if ( ! all ) {
			for (Entry<String, List<Category>> entry : new ArrayList<Entry<String, List<Category>>>(overlapping.entrySet())) {
				if ( entry.getValue().size() < 2 ) 
					overlapping.remove(entry.getKey());
			}
		}
		
		return overlapping;
	}

	
	public void setRepetitions(boolean b) {
		this.withRepetitions = b;
	}
	
	public void showRepetitionDetails(boolean b) {
		this.showRepetitionDetails = b;
	}
	
	public void showCategoryDescriptions(boolean b) {
		this.showCategoryDescriptions = b;
	}
	
	public void printResult(PrintStream out) {
		out.println("Summary");
		out.println("===============================");
		for (Entry<Category, List<ART>> entry : results.entrySet()) {
			int category = entry.getValue().size();
			// out.println(" " + entry.getKey() + " : " + category + " (" + (1.0* category / total) * 100 + "%)");
			out.printf(" " + entry.getKey() + " : " + category + " (%.2f%s)\n", (1.0* category / total) * 100, "%");

		}
		out.println(" Total: " + total);
		if ( failed > 0 )
			out.println(" Failed: " + failed);

		HashMap<String, List<Category>> overlapping = computeOverlappingDetectedCategories(false);
		if ( ! overlapping.isEmpty() ) {
			out.println();
			out.println("Overlapping categories");
			out.println("===============================");
			for (Entry<String, List<Category>> entry : overlapping.entrySet()) {
				String s = entry.getValue().get(0).getName();
				for(int i = 1; i < entry.getValue().size(); i++) {
					s += ", " + entry.getValue().get(i).getName();
				}
				
				out.println(" " + entry.getKey() + " : " + s);
			}
			out.println(" Total: " + overlapping.size());
		}
		
		out.println();
		out.println("Detail");
		out.println("=============================");
		for (Entry<Category, List<ART>> entry : results.entrySet()) {
			out.println("- " + entry.getKey() + " : ");

			if ( withRepetitions ) {
				HashMap<String, List<ART>> artefactsById = groupByArtefactId(entry.getValue());
				
				for (Entry<String, List<ART>> idAndArtefacts : artefactsById.entrySet()) {
					// Output the artefact info
					
					int numberOfOccurences = idAndArtefacts.getValue().size();
					out.println("   * " + idAndArtefacts.getKey() + "(" + numberOfOccurences + ")");
					
					if ( showRepetitionDetails ) {
						for (ART artifact : entry.getValue()) {
							// List the hints for the artefact classification 
							String hintStr = "";
							List<IHint> hints = artifact.getHints();
							if ( hints.size() > 0 ) {
								hintStr = " : " + hints.get(0).toString();
								for(int i = 1; i < hints.size(); i++) {
									hintStr += ", " + hints.get(i).getShortDescription();
								}
							}
							
							out.println("      -" + artifact.getName() + hintStr);					
						}					
					}
				}
			} else {
				for (ART artifact : entry.getValue()) {
					// List the hints for the artefact classification 
					String hintStr = "";
					List<IHint> hints = artifact.getHints();
					if ( hints.size() > 0 ) {
						hintStr = " : " + hints.get(0).toString();
						for(int i = 1; i < hints.size(); i++) {
							hintStr += ", " + hints.get(i).getShortDescription();
						}
					}
					
					// Output the artefact info
					out.println("   * " + artifact.getName() + hintStr);		
				}				
			}
		}

		if ( failed > 0 ) {
			out.println();
			out.println("Failed analysis");
			out.println("===============");
			for (Entry<String, Exception> entry : errors.entrySet()) {
				out.println("- " + entry.getKey() + " : " + entry.getValue().getMessage());
			}			
		}
		
	}

	private HashMap<String, List<ART>> groupByArtefactId(List<ART> value) {
		HashMap<String, List<ART>> results = new HashMap<String, List<ART>>();
		for (ART art : value) {
			if ( ! results.containsKey(art.getId())) {
				results.put(art.getId(), new ArrayList<ART>());
			}
			
			results.get(art.getId()).add(art);
		}
		return results;
	}


	public void toExcel(String fileName) throws IOException {
		Workbook wb = new XSSFWorkbook();
		
		createSummary(wb);
		createDetail(wb);
		createOverlapping(wb);
		if ( showCategoryDescriptions ) {
			createCategoryDescriptions(wb);
		}
		
		if ( errors.size() > 0 ) {
			createErrorSheet(wb);
		}

		// Save
		FileOutputStream fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		wb.close();
		fileOut.close();           
	
	}

	private void createErrorSheet(Workbook wb) {
		Sheet s = wb.createSheet("Errors");
		ExcelUtil u = new ExcelUtil();
		Styler    st = new Styler(wb);

		int row = 1;
		int startCol = 1;

		st.cell(s, row, startCol + 0, "Resource").centeringBold();
		st.cell(s, row, startCol + 1, "Error").centeringBold();
		
		row++;
		for (Entry<String, Exception> entry : errors.entrySet()) {
			st.cell(s, row, startCol + 0, entry.getKey());
			st.cell(s, row, startCol + 1, entry.getValue().getMessage());			
			row++;
		}
		
		
	}


	private void createCategoryDescriptions(Workbook wb) {
		Sheet s = wb.createSheet("Categories");
		ExcelUtil u = new ExcelUtil();
		Styler    st = new Styler(wb);

		int row = 1;
		int startCol = 1;

		st.cell(s, row, startCol + 0, "Id");
		st.cell(s, row, startCol + 1, "Description");

		row++;
		
		List<Category> allCategories = getAllCategories();
		for(int i = 0; i < allCategories.size(); i++ ) {
			st.cell(s, row, startCol + 0, allCategories.get(i).getName());
			st.cell(s, row, startCol + 1, allCategories.get(i).getDescription());
			row++;
		}
		

	}


	private void createDetail(Workbook wb) {
		Sheet s = wb.createSheet("Detail");
		
		ExcelUtil u = new ExcelUtil();
		
		int initRow = 2;
		int row = initRow;
		int col = 1;
		
		for (Entry<Category, List<ART>> entry : results.entrySet()) {
			u.createCell(s, row, col, entry.getKey().getName());
			
			row++;
			for (ART art : entry.getValue()) {
				u.createCell(s, row, col + 1, art.getName());				
				
				// Create hints if needed
				List<IHint> hints = art.getHints();
				int j = 1;
				for (IHint hint : hints) {
					j++;
					u.createCell(s, row, col + j, hint.getShortDescription());	
				}
				
				row++;
			}
		}
		
		
	}

	private void createSummary(Workbook wb) {
		Sheet s = wb.createSheet("Summary");
		
		ExcelUtil u = new ExcelUtil();
		Styler    st = new Styler(wb);
		
		int totalArtefacts = total;
		if ( withRepetitions ) {
			totalArtefacts = (int) results.values().stream().flatMap(v -> v.stream()).count();
		}
		
		int initRow = 2;
		int row = initRow;
		int col = 1;
		for (Category category : getAllCategories()) {
			List<ART> values = results.get(category);
			int categorySize = values.size();
			
			// Category
			st.cell(s, row, col + 0, category.getName());
			// Total of each
			st.cell(s, row, col + 1, (long) categorySize);			
			// Percentage
			st.cell(s, row, col + 2, (1.0* categorySize / totalArtefacts)).percentage();
			
			row++;
		}
		/*
		for (Entry<Category, List<ART>> entry : results.entrySet()) {
			int category = entry.getValue().size();
		
			// Category
			st.cell(s, row, col + 0, entry.getKey().getName());
			// Total of each
			st.cell(s, row, col + 1, (int) entry.getValue().size());			
			// Percentage
			st.cell(s, row, col + 2, (1.0* category / totalArtefacts)).percentage();
			
			row++;
		}
		*/

		Cell c1 = u.createCellFormula(s, row, col + 1, "SUM(" + u.columnRange(initRow, row - 1, col + 1) + ")");
		c1.setCellStyle(st.getNumeric());
		
		Cell c2 = u.createCellFormula(s, row, col + 2, "SUM(" + u.columnRange(initRow, row - 1, col + 2) + ")");
		c2.setCellStyle(st.getPercentage());

		row++;
		
		u.createCell(s, row, col + 0, "Total artefacts:");		
		u.createCell(s, row, col + 1, (long) totalArtefacts);
	}

	private void createOverlapping(Workbook wb) {
		HashMap<String, List<Category>> overlapping = computeOverlappingDetectedCategories(true);
		if ( ! overlapping.isEmpty() ) {
			Sheet s = wb.createSheet("Overlapping");
			
			ExcelUtil u = new ExcelUtil();
			Styler    st = new Styler(wb);
			
			int initRow  = 1;
			int startCol = 0;
			int row = initRow;
			// int col = startCol;
			
			
			st.cell(s, row, startCol + 1, "Total").centeringBold();
			List<Category> allCategories = getAllCategories();
			for(int i = 0; i < allCategories.size(); i++ ) {
				st.cell(s, row, startCol + i + 2, allCategories.get(i).getName()).
					centeringBold().
					wrapText().
					centerVertically();
			}
			
			row++;
			
			for (Entry<String, List<Category>> entry : overlapping.entrySet()) {
				int beginPatterns = startCol + 2;
				
				st.cell(s, row, startCol, entry.getKey());	
				st.cellFormula(s, row, startCol + 1, "SUM(" + u.rowRange(row, beginPatterns, beginPatterns + allCategories.size() ) + ")").centering();
				
				int col = beginPatterns;
				
				for(int i = 0; i < entry.getValue().size(); i++) {
					Category c = entry.getValue().get(i);
					int pos = allCategories.indexOf(c);
					if ( pos < 0 )
						throw new IllegalStateException();
					
					long numberOfCategoryOcurrences = 1L; // default;
					numberOfCategoryOcurrences = countOccurences(entry.getKey(), results.get(c));					
					st.cell(s, row, col + pos, numberOfCategoryOcurrences).centering();
				}
				
				row++;
			}
		}
		
	}


	private int countOccurences(String artefactId, List<ART> list) {
		int counter = 0;
		for (ART art : list) {
			if ( art.getId().equals(artefactId)) {
				counter++;
			}
		}
		return counter;
	}


	private List<Category> getAllCategories() {
		List<Category> l = new LinkedList<Category>(results.keySet());
		Collections.sort(l);
		return l;
	}





}
