package anatlyzer.experiments.export;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class Styler extends ExcelUtil {

	private CellStyle decimal;
	private CellStyle dateStyle;
	private CellStyle dateWithSecondsStyle;
	private Workbook workbook;
	private CellStyle centeringBold;
	private CellStyle alignRight;
	private CellStyle centeringBoldDate;
	private CellStyle decimalCentering;
	private CellStyle centering;
	private CellStyle centeringBoldDateMonth;
	private CellStyle justDateStyle;
	private CellStyle percentage;
	private CellStyle numeric;


	public CellStyle getPercentage() { return percentage; }
	public CellStyle getNumeric() { return numeric; }
		
	public Styler(Workbook wb) {
		CreationHelper createHelper = wb.getCreationHelper();

		workbook = wb;		

        Font boldFont = wb.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		centeringBold = wb.createCellStyle();
		centeringBold.setFont(boldFont);
		centeringBold.setAlignment(CellStyle.ALIGN_CENTER);

		numeric = wb.createCellStyle();
		
		centering = wb.createCellStyle();
		centering.setAlignment(CellStyle.ALIGN_CENTER);

		alignRight = wb.createCellStyle();		
		alignRight.setAlignment(CellStyle.ALIGN_RIGHT);

		
		decimal = wb.createCellStyle();
		decimal.setDataFormat(createHelper.createDataFormat().getFormat("0.00"));

		decimalCentering = wb.createCellStyle();
		decimalCentering.cloneStyleFrom(decimal);
		decimalCentering.setAlignment(CellStyle.ALIGN_CENTER);

		percentage = wb.createCellStyle();
		percentage.cloneStyleFrom(decimal);
		percentage.setDataFormat(createHelper.createDataFormat().getFormat("0.00%"));
		
		
	    dateStyle = wb.createCellStyle();	    
	    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));		    

	    justDateStyle = wb.createCellStyle();	    
	    justDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));		    

	    centeringBoldDate = wb.createCellStyle();
	    centeringBoldDate.cloneStyleFrom(centeringBold);
	    centeringBoldDate.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));		    

	    centeringBoldDateMonth = wb.createCellStyle();
	    centeringBoldDateMonth.cloneStyleFrom(centeringBold);
	    centeringBoldDateMonth.setDataFormat(createHelper.createDataFormat().getFormat("mm/yyyy"));		    

	    dateWithSecondsStyle = wb.createCellStyle();	    
	    dateWithSecondsStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));		    		
	    
	}
	
	public CellStyle getDecimal() {
		return decimal;
	}
	
	public CellStyler cell(Sheet s, int row, int col, Calendar d) {
		return new CellStyler( createCell(s, row, col, d) );
	}
	
	public CellStyler cell(Sheet s, int row, int col, Date d) {
		return new CellStyler( createCell(s, row, col, d) );
	}
	
	public CellStyler cell(Sheet s, int row, int col, Double v) {
		return new CellStyler( this.createCell(s, row, col, v) ); 
	}	

	public CellStyler cell(Sheet s, int row, int col, Integer v) {
		return new CellStyler( this.createCell(s, row, col, v.longValue()) ); 
	}	

	public CellStyler cell(Sheet s, int row, int col, Float v) {
		return new CellStyler( this.createCell(s, row, col, v) ); 
	}	

	public CellStyler cell(Sheet s, int row, int col, Long v) {
		return new CellStyler( this.createCell(s, row, col, v) ); 
	}	
	
	public CellStyler cell(Sheet s, int row, int col, String str) {
		return new CellStyler( createCell(s, row, col, str) );
	}
	

	
	public CellStyler cellFormula(Sheet s, int row, int col, String str) {
		return new CellStyler( createCellFormula(s, row, col, str) );
	}
	
	public class CellStyler {
		private Cell cell;

		public CellStyler(Cell c) {
			this.cell = c;
		}
		
		public CellStyler withDecimal() {
			cell.setCellStyle(decimal);
			return this;
		}		
		
		public CellStyler percentage() {
			cell.setCellStyle(percentage);
			return this;
		}
		
		public CellStyler isDateWithSeconds() {
			cell.setCellStyle(dateWithSecondsStyle);
			return this;
		}

		public CellStyler charsWidth(int length) {
			cell.getSheet().setColumnWidth(cell.getColumnIndex(), length * 256);
			return this;		
		}
		
		public CellStyler centeringBold() {
			cell.setCellStyle(centeringBold);		
			return this;
		}

		public void centeringBoldDate() {
			cell.setCellStyle(centeringBoldDate);		
		}
		
		public void alignRight() {
			cell.setCellStyle(alignRight);			
		}

		public void decimalCentering() {
			cell.setCellStyle(decimalCentering);
		}
		
		public void centering() {
			cell.setCellStyle(centering);
		}

		public void centeringBoldDateMonth() {
			cell.setCellStyle(centeringBoldDateMonth);			
		}

		public void decimal2() {
			cell.setCellStyle(decimal);
		}

		public void justDate() {
			cell.setCellStyle(justDateStyle);
		}

		public CellStyler wrapText() {
			// Is this fine or I need to clone the style?
			cell.getCellStyle().setWrapText(true);
			return this;
		}
		
		public CellStyler centerVertically() {
			cell.getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			return this;
		}

		public void background(short index) {
			CellStyle style = workbook.createCellStyle();
			style.setFillBackgroundColor(index);			
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(style);
			
//			cell.getCellStyle().setFillBackgroundColor(index);			
//			cell.getCellStyle().setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		}

	}




}
