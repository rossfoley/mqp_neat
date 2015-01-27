package mqp.anji;

import com.anji.neat.NeatChromosomeUtility;
import com.anji.neat.NeatConfiguration;
import com.anji.util.Properties;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;

import java.io.IOException;

/**
 * Created by rossfoley on 1/27/15.
 */
public class MarioRunner {
    private static final int MAX_ALLOWED_EVOLUTIONS = 50;

    public static void main(String[] args) throws InvalidConfigurationException, IOException {
        // Load in the mario properties file
        Properties props = new Properties();
        props.loadFromResource("mario.properties");

        // Set up the proper config and fitness function
        NeatConfiguration config = new NeatConfiguration(props);
        MarioFitnessFunction fitnessFunction = new MarioFitnessFunction();
        ChromosomeMaterial marioMaterial = NeatChromosomeUtility.newSampleChromosomeMaterial((short)101, (short)0, (short)6, config, true);

        // Apply config settings
        config.setBulkFitnessFunction(fitnessFunction);
        config.setSampleChromosomeMaterial(marioMaterial);
        config.setPopulationSize(500);

        // Generate a random initial population
        Genotype population = Genotype.randomInitialGenotype(config);

        // Evolve the population
        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
        }
    }
}
