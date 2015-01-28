package mqp.anji;

import ch.idsia.benchmark.tasks.ProgressTask;
import mqp.mario.NEATAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.*;
import com.anji.util.Properties;
import org.jgap.BulkFitnessFunction;
import org.jgap.Chromosome;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class MarioFitnessFunction implements BulkFitnessFunction {
	private final static int MAX_FITNESS = 160000000;
	private ActivatorTranscriber activatorFactory;
	private Properties props;

	public MarioFitnessFunction(Properties props) {
		init(props);
	}

	public void init(Properties newProps) {
		try {
			props = newProps;
			ErrorFunction.getInstance().init(newProps);
			activatorFactory = (ActivatorTranscriber) newProps.singletonObjectProperty(ActivatorTranscriber.class);
		} catch ( Exception e ) {
			throw new IllegalArgumentException( "invalid properties: " + e.getClass().toString()
					+ ": " + e.getMessage() );
		}
	}

	public void evaluate(List chromosomes) {
		MarioAIOptions marioAIOptions = new MarioAIOptions();
		marioAIOptions.setVisualization(false);
		ProgressTask task = new ProgressTask(marioAIOptions);

		Iterator it = chromosomes.iterator();
		while (it.hasNext()) {
			Chromosome c = (Chromosome) it.next();
			try {
				Activator a = activatorFactory.newActivator(c);
				NEATAgent marioAgent = new NEATAgent(a);
				int fitness = task.evaluate(marioAgent);
				c.setFitnessValue(fitness);
			} catch (Exception e) {
				System.out.println("Error in generating an activator for the current chromosome!");
			}
		}
	}

	public int getMaxFitnessValue(){
		return MAX_FITNESS;
	}

}
