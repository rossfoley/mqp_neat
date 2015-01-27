package ch.idsia.agents.controllers.mqp;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;
import com.anji.integration.Activator;

/**
 * Created by rossfoley on 1/27/15.
 */
public class NEATAgent extends BasicMarioAIAgent {
    private Activator neat_nn;
    public final int NUM_INPUTS = 21;

    public NEATAgent(Activator nn) {
        super("NEATAgent");
        neat_nn = nn;
    }

    public boolean[] getAction() {
        double[] inputs = new double[NUM_INPUTS];
        int which = 0;

        // Fill in inputs for scene data
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                inputs[which++] = probe(i, j, levelScene);
            }
        }

        // Fill in inputs for enemy data
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                inputs[which++] = probe(i, j, enemies);
            }
        }

        // Fill in jumping information
        inputs[inputs.length - 3] = isMarioOnGround ? 1 : 0;
        inputs[inputs.length - 2] = isMarioAbleToJump ? 1 : 0;
        inputs[inputs.length - 1] = 1;

        // Get the neural network output
        double[] output = neat_nn.next(inputs);

        // Fill in the actions based on the output
        for (int i = 0; i < output.length; i++) {
            action[i] = (output[i] > 0);
        }

        return action;
    }

    public void reset() {
        // NEAT doesn't require resetting between levels
    }

    private double probe(int x, int y, byte[][] scene) {
        int realX = x + 11;
        int realY = y + 11;
        return (scene[realX][realY] != 0) ? 1 : 0;
    }
}
