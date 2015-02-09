package mqp.mario;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import com.anji.integration.Activator;

/**
 * The logic that powers the Mario AI agents
 * @author Ross Foley and Karl Kuhn
 */
public class NEATCustomAgent extends BasicMarioAIAgent {
    private Activator activator;
    private final double OUTPUT_THRESHOLD = 0.5;

    /**
     * NEATAgent constructor
     * @param nn the neural network activator
     */
    public NEATCustomAgent(Activator nn) {
        super("NEATCustomAgent");
        activator = nn;
    }

    /**
     * Compute the next action that the AI agent will perform
     * @return the next action
     */
    public boolean[] getAction() {
        double[] inputs = new double[46];
        int which = 0;

        // Use 3x3 inputs for scene data
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                inputs[which++] = probe(i, j, levelScene);
            }
        }

        // Use 7x5 inputs for enemy data
        for (int i = -2; i < 5; i++) {
            for (int j = -2; j < 3; j++) {
                inputs[which++] = probe(i, j, enemies);
            }
        }

        // Fill in jumping information
        inputs[inputs.length - 3] = isMarioOnGround ? 1 : 0;
        inputs[inputs.length - 2] = isMarioAbleToJump ? 1 : 0;

        // Get the neural network output
        double[] output = activator.next(inputs);

        // Fill in the actions based on the output
        for (int i = 0; i < output.length; i++) {
            action[i] = (output[i] > OUTPUT_THRESHOLD);
        }

        return action;
    }

    /**
     * Determine if there is an object at a specified position in the scene
     * @param x x position in the scene
     * @param y y position in the scene
     * @param scene scene data
     * @return the data at the position in the scene
     */
    private double probe(int x, int y, byte[][] scene) {
        int realX = x + 11;
        int realY = y + 11;
        return (scene[realX][realY] != 0) ? 1 : 0;
    }
}
