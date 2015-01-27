package com.anji.neat;

import com.anji.nn.ActivationFunctionType;
import org.jgap.Allele;
import org.jgap.ChromosomeMaterial;

import java.util.ArrayList;

public class MarioChromosomeMaterial{

    public MarioChromosomeMaterial(){}

    public static ChromosomeMaterial getMaterial(){
        ArrayList<NeuronAllele> alleles = new ArrayList<NeuronAllele>();

        // Set up inputs for level data
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                alleles.add(new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR)));
            }
        }

        // Set up inputs for enemy data
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                alleles.add(new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR)));
            }
        }

        // Input for isMarioOnGround
        alleles.add(new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR)));

        // Input for isMarioAbleToJump
        alleles.add(new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR)));

        // Input for bias factor
        alleles.add(new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR)));

        // Output that corresponds to the 6 buttons
        for (int i = 0; i < 6; i++) {
            alleles.add(new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long) 0, ActivationFunctionType.LINEAR)));
        }

        return new ChromosomeMaterial(alleles);
    }
}
