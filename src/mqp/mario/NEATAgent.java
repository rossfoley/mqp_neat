package mqp.mario;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import com.anji.integration.Activator;

/**
 * The logic that powers the Mario AI agents
 * @author Ross Foley and Karl Kuhn
 */
public class NEATAgent extends BasicMarioAIAgent {
    private Activator activator;
    private int radius;
    private final double OUTPUT_THRESHOLD = 0.5;

    /**
     * NEATAgent constructor
     * @param nn the neural network activator
     * @param radius the radius of input for Mario
     */
    public NEATAgent(Activator nn, int radius) {
        super("NEATAgent");
        activator = nn;
        this.radius = radius;
        if (activator.getInputDimension() != getNumInputs()) {
            throw new RuntimeException("Radius provided does not match the provided neural network!");
        }
    }

    /**
     * Compute the next action that the AI agent will perform
     * @return the next action
     */
    public boolean[] getAction() {
        double[] inputs = new double[getNumInputs()];
        int which = 0;

        // Fill in inputs for scene data
        for (int i = -1*radius; i <= radius; i++) {
            for (int j = -1*radius; j <= radius; j++) {
                inputs[which++] = probe(i, j, levelScene);
            }
        }

        // Fill in inputs for enemy data
        for (int i = -1*radius; i <= radius; i++) {
            for (int j = -1*radius; j <= radius; j++) {
                inputs[which++] = probe(i, j, enemies);
            }
        }

        // Fill in jumping information
        // It should be -2 and -1 instead of -3 and -2, but we can't change it now
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

    /**
     * Get the total number of inputs for the neural network
     * @return the number of inputs
     */
    public int getNumInputs() {
        int width = 2*radius + 1;
        return 2*width*width + 2;
    }
}
