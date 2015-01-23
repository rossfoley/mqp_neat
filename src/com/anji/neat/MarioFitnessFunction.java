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

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import com.anji.integration.ErrorFunction;
import com.anji.integration.TargetFitnessFunction;
import com.anji.util.Properties;

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
public class MarioFitnessFunction extends TargetFitnessFunction {

	private final static boolean SUM_OF_SQUARES = false;

	private final static int MAX_FITNESS = 160000000;

	/**
	 * See <a href=" {@docRoot}/params.htm" target="anji_params">Parameter Details </a> for
	 * specific property settings.
	 * 
	 * @param newProps configuration parameters
	 */
	public void init( Properties newProps ) {
		try {
			super.init( newProps );
			ErrorFunction.getInstance().init( newProps );
			setMaxFitnessValue( MAX_FITNESS );
		}
		catch ( Exception e ) {
			throw new IllegalArgumentException( "invalid properties: " + e.getClass().toString()
					+ ": " + e.getMessage() );
		}
	}

	/**
	 * Subtract <code>responses</code> from targets, sum all differences, subtract from max
	 * fitness, and square result.
	 * j
	 * @param responses output top be compared to targets
	 * @param minResponse
	 * @param maxResponse
	 * @return result of calculation
	 */
	protected int calculateErrorFitness( double[][] responses, double minResponse, double maxResponse ) {
		final MarioAIOptions marioAIOptions = new MarioAIOptions("");
//        final Environment environment = new MarioEnvironment();
//        final Agent agent = new ForwardAgent();
//        final Agent agent = marioAIOptions.getAgent();
//        final Agent a = AgentsPool.loadAgent("ch.idsia.controllers.agents.controllers.ForwardJumpingAgent");
		final BasicTask basicTask = new BasicTask(marioAIOptions);
//        for (int i = 0; i < 10; ++i)
//        {
//            int seed = 0;
//            do
//            {
//                marioAIOptions.setLevelDifficulty(i);
//                marioAIOptions.setLevelRandSeed(seed++);
		basicTask.setOptionsAndReset(marioAIOptions);
//    basicTask.runSingleEpisode(1);
		basicTask.doEpisodes(1,true,1);
		return 10;
	}

}
