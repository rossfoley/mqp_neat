package ch.idsia.agents.controllers.mqp;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import com.anji.integration.Activator;

/**
 * Created by rossfoley on 1/27/15.
 */
public class NEATAgent extends BasicMarioAIAgent implements Agent {
    private Activator neat_nn;

    public NEATAgent(Activator nn) {
        super("NEATAgent");
        neat_nn = nn;
    }

    public boolean[] getAction() {
        // Set values in action based on the result of the neural network
        double[] stimuli = new double[100]; // Change size to actual input numbers
        // Fill in stimuli with the neural network inputs
        double[] output = neat_nn.next(stimuli);
        for (int i = 0; i < output.size; i++) {
            action[i] = (output[i] >= 1); // 1 is a placeholder for the output threshold
        }
        return action;
    }

    public void reset() {
        // NEAT doesn't require resetting between levels
    }
}
