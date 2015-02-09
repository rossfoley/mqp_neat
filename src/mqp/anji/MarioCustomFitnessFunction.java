package mqp.anji;

import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.Activator;
import com.anji.integration.ActivatorTranscriber;
import com.anji.integration.ErrorFunction;
import com.anji.util.Properties;
import mqp.mario.MQPMarioTask;
import mqp.mario.NEATAgent;
import mqp.mario.NEATCustomAgent;
import org.jgap.BulkFitnessFunction;
import org.jgap.Chromosome;

import java.util.Iterator;
import java.util.List;

/**
 * Evaluate a list of chromosomes by having them complete the MQPMarioTask
 * @author Ross Foley and Karl Kuhn
 */
public class MarioCustomFitnessFunction implements BulkFitnessFunction {
    private final static int MAX_FITNESS = 160000000;
    private ActivatorTranscriber activatorFactory;
    private Properties props;
    private int radius;

    public MarioCustomFitnessFunction(Properties props) {
        init(props);
    }

    public void init(Properties newProps) {
        try {
            props = newProps;
            ErrorFunction.getInstance().init(newProps);
            activatorFactory = (ActivatorTranscriber) newProps.singletonObjectProperty(ActivatorTranscriber.class);
            radius = props.getIntProperty("mario.agent.input.radius");
        } catch ( Exception e ) {
            throw new IllegalArgumentException( "invalid properties: " + e.getClass().toString()
                    + ": " + e.getMessage() );
        }
    }

    public void evaluate(List chromosomes) {
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(false);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        Iterator it = chromosomes.iterator();
        while (it.hasNext()) {
            Chromosome c = (Chromosome) it.next();
            try {
                Activator a = activatorFactory.newActivator(c);
                NEATCustomAgent marioAgent = new NEATCustomAgent(a);
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
