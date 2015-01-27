package mqp.anji;

import mqp.mario.NEATAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.*;
import com.anji.util.Properties;
import org.jgap.BulkFitnessFunction;
import org.jgap.Chromosome;

import java.util.List;
import java.util.ArrayList;

public class MarioFitnessFunction implements BulkFitnessFunction {
	private final static int MAX_FITNESS = 160000000;
	private ActivatorTranscriber activatorFactory;


	public void init( Properties newProps ) {
		try {
			ErrorFunction.getInstance().init(newProps);
			activatorFactory = (ActivatorTranscriber) newProps.singletonObjectProperty(ActivatorTranscriber.class);
		}
		catch ( Exception e ) {
			throw new IllegalArgumentException( "invalid properties: " + e.getClass().toString()
					+ ": " + e.getMessage() );
		}
	}

	public void evaluate(List chromosomes){
		ArrayList<Chromosome> chromos = (ArrayList<Chromosome>)chromosomes;
		for (Chromosome c : chromos) {
			try {
				Activator a = activatorFactory.newActivator(c);
				NEATAgent marioAgent = new NEATAgent(a);
				final MarioAIOptions marioAIOptions = new MarioAIOptions();
				final BasicTask basicTask = new BasicTask(marioAIOptions);
				int seed = 0;

				marioAIOptions.setLevelDifficulty(1);
				marioAIOptions.setLevelRandSeed(seed);
				marioAIOptions.setGameViewer(false);
				marioAIOptions.setAgent(marioAgent);
				basicTask.setOptionsAndReset(marioAIOptions);
				basicTask.runSingleEpisode(1);

				int fitness = basicTask.getEnvironment().getEvaluationInfo().computeBasicFitness();
				c.setFitnessValue(fitness);
			} catch (Exception e) {}
		}
	}

	public int getMaxFitnessValue(){
		return MAX_FITNESS;
	}

}
