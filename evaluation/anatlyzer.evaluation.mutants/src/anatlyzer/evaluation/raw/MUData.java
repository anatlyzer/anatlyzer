package anatlyzer.evaluation.raw;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

@Root(name="muexperiment")
public class MUData {
	@ElementList(name="mutants")
	protected List<MUTransformation> mutants;
	
	@Attribute
	protected Date date;

	@Attribute
	private long elapsedTime = -1;
	
	public MUData() {
		this.mutants = new ArrayList<MUTransformation>();
		this.date     = Calendar.getInstance().getTime();
	}
	
	public void addMutant(MUTransformation m) {
		this.mutants.add(m);
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public void save(String fname) {
		Serializer serializer = new Persister();
        File result = new File(fname);
        try {
			serializer.write(this, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
