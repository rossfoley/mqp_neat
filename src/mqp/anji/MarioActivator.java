package mqp.anji;

import ch.idsia.benchmark.tasks.ProgressTask;
import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.Activator;
import com.anji.integration.ActivatorTranscriber;
import com.anji.persistence.Persistence;
import com.anji.util.DummyConfiguration;
import com.anji.util.Properties;
import mqp.mario.NEATAgent;
import org.jgap.Chromosome;
import org.jgap.Configuration;

/**
 * Created by rossfoley on 1/28/15.
 */
public class MarioActivator {
    public static void main(String[] args) throws Exception {
        // Set the Chromosome ID to run
        final String chromosomeID = "78153";

        // Load in the properties file
        Properties props = new Properties("mario.properties");
        Persistence db = (Persistence) props.singletonObjectProperty(Persistence.PERSISTENCE_CLASS_KEY);
        ActivatorTranscriber activatorFactory = (ActivatorTranscriber) props.singletonObjectProperty(ActivatorTranscriber.class);

        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(true);
        ProgressTask task = new ProgressTask(marioAIOptions);

        // Create a Mario Agent based on the chromosome
        Configuration config = new DummyConfiguration();
        Chromosome c = db.loadChromosome(chromosomeID, config);
        Activator a = activatorFactory.newActivator(c);
        NEATAgent marioAgent = new NEATAgent(a);

        // Run it
        task.evaluate(marioAgent);

        // Exit
        System.exit(0);
    }
}
