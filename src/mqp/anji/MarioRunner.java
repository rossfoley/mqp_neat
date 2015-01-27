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
        Properties props = new Properties("mario.properties");
        NeatConfiguration config = new NeatConfiguration(props);
        MarioFitnessFunction fitnessFunction = new MarioFitnessFunction(props);
        config.setBulkFitnessFunction(fitnessFunction);

        // Generate a random initial population
        Genotype population = Genotype.randomInitialGenotype(config);

        // Evolve the population
        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
        }
    }
}
