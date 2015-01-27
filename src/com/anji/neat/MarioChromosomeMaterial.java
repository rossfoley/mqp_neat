package com.anji.neat;

import com.anji.nn.ActivationFunctionType;
import org.jgap.Allele;
import org.jgap.ChromosomeMaterial;

import java.util.ArrayList;

public class MarioChromosomeMaterial{

    public MarioChromosomeMaterial(){}

    public static ChromosomeMaterial getMaterial(){
        ArrayList<NeuronAllele> alleles = new ArrayList<NeuronAllele>();
        NeuronAllele inUp = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inUpRight = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inRight = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inDownRight = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inDown = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inDownLeft = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inLeft = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inUpLeft = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));

        NeuronAllele key_LEFT = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele key_RIGHT = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele key_DOWN = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele key_JUMP = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele key_SPEED = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele key_UP = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        alleles.add(inUp);
        alleles.add(inUpRight);
        alleles.add(inRight);
        alleles.add(inDownRight);
        alleles.add(inDown);
        alleles.add(inDownLeft);
        alleles.add(inLeft);
        alleles.add(inUpLeft);
        alleles.add(key_LEFT);
        alleles.add(key_RIGHT);
        alleles.add(key_DOWN);
        alleles.add(key_JUMP);
        alleles.add(key_SPEED);
        alleles.add(key_UP);

        return new ChromosomeMaterial(alleles);
    }
}
