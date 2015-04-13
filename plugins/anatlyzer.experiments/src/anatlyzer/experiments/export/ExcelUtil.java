package anatlyzer.experiments.export;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtil {
	
	public Row getRow(Sheet s, int row) {
		Row r = s.getRow(row);
		if ( r == null ) r = s.createRow(row);
		return r;
	}


	public Cell createCell(Sheet s, int row, int col, Long v) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_NUMERIC);
		c.setCellValue(v);
		return c;
	}
	
	public Cell createCell(Sheet s, int row, int col, Double v) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_NUMERIC);
		c.setCellValue(v);
		return c;
	}
	
	public Cell createCell(Sheet s, int row, int col, Float v) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_NUMERIC);
		c.setCellValue(v);
		return c;
	}
	
	public Cell createCell(Sheet s, int row, int col, String text) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_STRING);
		c.setCellValue(text);
		return c;
	}

	public Cell createCell(Sheet s, int row, int col, Calendar d, CellStyle style) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_BLANK);
		c.setCellStyle(style);
		c.setCellValue(d);			
		return c;
	}

	public Cell createCell(Sheet s, int row, int col, Calendar d) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_BLANK);
		c.setCellValue(d);			
		return c;
	}

	public Cell createCell(Sheet s, int row, int col, Date d) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_BLANK);
		c.setCellValue(d);			
		return c;
	}


	public Cell createCellFormula(Sheet s, int row, int col, String textFormula) {
		Cell c = createCell(s, row, col, Cell.CELL_TYPE_NUMERIC);
		c.setCellFormula(textFormula);
		return c;		
	}
	
	public Cell createCell(Sheet s, int row, int col, int cellType) {
		Row r = getRow(s, row);
		Cell c = r.getCell(col);
		if ( c == null ) c = r.createCell(col, cellType);			
		return c;
	}

	/**
	 * Devuelve la fecha como un objeto. Utiliza el formato
	 * yyyy/MM/dd HH:mm.
	 * @return
	 */
	public Calendar getDate(String fecha) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date d;
		try {
			d = sdf.parse(fecha);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
        Calendar c = Calendar.getInstance();
        c.setTime(d);
		
        return c;
	}


	public String columnRange(int initRow, int row, int col) {
		initRow++;
		row++;
		
		String ccol = getCol(col);
		return ccol + "" + initRow + ":" + ccol + row;
	}


	private String getCol(int col) {
		String prefix = "";
		if ( ('A' + col) > 'Z' ) {
			prefix = "A";
			col = col - (char) (('Z' - 'A') - 1);
		}
		return prefix + (char) ('A' + col);
	}


	public String rowRange(int row, int initCol, int endCol) {
		String bcol = getCol(initCol); // (char) ('A' + initCol);
		String ecol = getCol(endCol);  // (char) ('A' + endCol);
		System.out.println(bcol);
		System.out.println(ecol);
		System.out.println("---");
		row = row + 1;
		return bcol + "" + row + ":" + ecol + "" + row;
	}


}
