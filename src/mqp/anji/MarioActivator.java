package mqp.anji;

import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.Activator;
import com.anji.integration.ActivatorTranscriber;
import com.anji.persistence.Persistence;
import com.anji.util.DummyConfiguration;
import com.anji.util.Properties;
import mqp.mario.MQPMarioTask;
import mqp.mario.NEATAgent;
import mqp.mario.NEATCustomAgent;
import org.jgap.Chromosome;
import org.jgap.Configuration;

/**
 * Run a specific Mario AI agent through the MQPMarioTask
 * @author Ross Foley and Karl Kuhn
 */
public class MarioActivator {
    public static void main(String[] args) throws Exception {
        // Load in the properties file
        Properties props = new Properties("mario.properties");
        Persistence db = (Persistence) props.singletonObjectProperty(Persistence.PERSISTENCE_CLASS_KEY);
        ActivatorTranscriber activatorFactory = (ActivatorTranscriber) props.singletonObjectProperty(ActivatorTranscriber.class);
        int radius = props.getIntProperty("mario.agent.input.radius");
        int fps = props.getIntProperty("mario.activator.fps");
        String chromosomeID = props.getProperty("mario.agent.chromosome.id");

        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(false);
        marioAIOptions.setFPS(fps);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        // Create a Mario Agent based on the chromosome
        Configuration config = new DummyConfiguration();
        Chromosome c = db.loadChromosome(chromosomeID, config);
        Activator a = activatorFactory.newActivator(c);
        NEATAgent marioAgent = new NEATAgent(a, radius);

        // Run it
        System.out.println("Evaluating champion of radius " + radius);

        int[] difficulties = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};

        // Evaluate the agent
//        task.evaluate(agent);

        int seed = 0;
        for (int difficulty : difficulties) {
            float fitness = task.evaluateSingleLevel(marioAgent, difficulty, seed);
            System.out.println(fitness);
            seed++;
        }


        // Exit
        System.exit(0);
    }
}
