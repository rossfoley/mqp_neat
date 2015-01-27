/*
 * Copyright (C) 2004 Derek James and Philip Tucker
 * 
 * This file is part of ANJI (Another NEAT Java Implementation).
 * 
 * ANJI is free software; you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA
 * 
 * created by Philip Tucker
 */
package com.anji.neat;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.ForwardAgent;
import ch.idsia.agents.controllers.mqp.NEATAgent;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.*;
import com.anji.util.Properties;
import org.jgap.BulkFitnessFunction;
import org.jgap.Chromosome;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


/**
 * Fitness function where error is subtracted from max fitness, then squared. Fitness is skewed
 * such that max fitness is <code>MAX_FITNESS</code>. See
 * <code>calculateErrorFitness()</code> for details. This mimics the error function used in <a
 * href="http://nn.cs.utexas.edu/downloads/papers/stanley.ec02.pdf"> Evolving Neural Networks
 * through Augmenting Topologies </a>.
 * 
 * @author Philip Tucker
 * @see com.anji.integration.TargetFitnessFunction
 */
public class MarioFitnessFunction implements BulkFitnessFunction {

	private final static boolean SUM_OF_SQUARES = false;

	private final static int MAX_FITNESS = 160000000;

	private ActivatorTranscriber activatorFactory;

	/**
	 * See <a href=" {@docRoot}/params.htm" target="anji_params">Parameter Details </a> for
	 * specific property settings.
	 * 
	 * @param newProps configuration parameters
	 */
	public void init( Properties newProps ) {
		try {
			//super.init( newProps );
			ErrorFunction.getInstance().init( newProps );
			//setMaxFitnessValue( MAX_FITNESS );
			activatorFactory = (ActivatorTranscriber) newProps.singletonObjectProperty(ActivatorTranscriber.class);
		}
		catch ( Exception e ) {
			throw new IllegalArgumentException( "invalid properties: " + e.getClass().toString()
					+ ": " + e.getMessage() );
		}
	}

	public void evaluate(List chromosomes){
		ArrayList<Chromosome> chromos = (ArrayList<Chromosome>)chromosomes;
		for(Chromosome c: chromos){
			try {
				Activator a = activatorFactory.newActivator(c);
				NEATAgent marioAgent = new NEATAgent(a);
				boolean[] actions = marioAgent.getAction();

				final MarioAIOptions marioAIOptions = new MarioAIOptions();
				final BasicTask basicTask = new BasicTask(marioAIOptions);
				int seed = 0;
				do{
						marioAIOptions.setLevelDifficulty(1);
						marioAIOptions.setLevelRandSeed(seed);
						marioAIOptions.setGameViewer(true);
						//marioAIOptions.setGameViewerContinuousUpdates(true);
						marioAIOptions.setAgent(agent);
						marioAIOptions.setFPS(100);
						basicTask.setOptionsAndReset(marioAIOptions);
						basicTask.runSingleEpisode(1);
						System.out.println(basicTask.getEnvironment().getEvaluationInfoAsString());
					} while (basicTask.getEnvironment().getEvaluationInfo().marioStatus != Environment.MARIO_STATUS_WIN);
				Runtime rt = Runtime.getRuntime();
				try
				{
//            Process proc = rt.exec("/usr/local/bin/mate " + marioTraceFileName);
					Process proc = rt.exec("python hello.py");
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				System.exit(0);

				//1. instantiate Mario agent class with Activator
				//2. evaluate the agent
				//3. store the fitness from the agent back into the chromosome (return the data kinda)
				//4. set up allessesssesees (with input and output neurons apporiate for mario)
			}
			catch (Exception e){

			}
		}
	}

	public int getMaxFitnessValue(){
		return 1000;
	}

}
