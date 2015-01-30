package mqp.mario;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.benchmark.tasks.Task;
import ch.idsia.tools.MarioAIOptions;

/**
 * Contains the levels that will be used to evolve Mario AI agents
 * @author Ross Foley and Karl Kuhn
 */
public class MQPMarioTask extends BasicTask implements Task, Cloneable {
    private int uniqueSeed;
    private int difficulties[] = {0, 0, 1, 1, 3};

    /**
     * MQPMarioTask Constructor
     * @param evaluationOptions the Mario options
     */
    public MQPMarioTask(MarioAIOptions evaluationOptions) {
        super(evaluationOptions);
        setOptionsAndReset(evaluationOptions);
        uniqueSeed = 1337;
        System.out.println("Running new generation on MQPMarioTask");
    }

    /**
     * Run the agent through a single specific level
     * @param controller the AI agent
     * @param difficulty the difficulty level
     * @param seed the level seed
     * @return the distance travelled by the agent
     */
    private float evaluateSingleLevel(Agent controller, int difficulty, int seed) {
        options.setAgent(controller);
        options.setLevelDifficulty(difficulty);
        options.setLevelRandSeed(seed);
        setOptionsAndReset(options);
        runSingleEpisode(1);
        return getEnvironment().getEvaluationInfo().computeDistancePassed();
    }

    /**
     * Evaluate an AI agent across multiple levels and difficulties
     * @param controller the AI agent
     * @return the total distance travelled by the agent across all levels
     */
    public int evaluate(Agent controller) {
        float totalFitness = 0;
        int seed = uniqueSeed;
        for (int difficulty : difficulties) {
            totalFitness += evaluateSingleLevel(controller, difficulty, seed);
            seed++;
        }

        return (int) totalFitness;
    }
}
