package com.anji.neat;

import com.anji.nn.ActivationFunctionType;
import org.jgap.Allele;
import org.jgap.ChromosomeMaterial;

import java.util.ArrayList;

public class MarioChromosomeMaterial{

    public MarioChromosomeMaterial(){}

    public ChromosomeMaterial getMaterial(){
        ArrayList<NeuronAllele> alleles = new ArrayList<NeuronAllele>();
        NeuronAllele inUp = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inUpRight = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inRight = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inDownRight = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inDown = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inDownLeft = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inLeft = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele inUpLeft = new NeuronAllele(new NeuronGene(NeuronType.INPUT, (long)0, ActivationFunctionType.LINEAR));

        NeuronAllele outA = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele outS = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele outRight = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele outLeft = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele outUp = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        NeuronAllele outDown = new NeuronAllele(new NeuronGene(NeuronType.OUTPUT, (long)0, ActivationFunctionType.LINEAR));
        alleles.add(inUp);
        alleles.add(inUpRight);
        alleles.add(inRight);
        alleles.add(inDownRight);
        alleles.add(inDown);
        alleles.add(inDownLeft);
        alleles.add(inLeft);
        alleles.add(inUpLeft);
        alleles.add(outA);
        alleles.add(outS);
        alleles.add(outRight);
        alleles.add(outLeft);
        alleles.add(outUp);
        alleles.add(outDown);

        return new ChromosomeMaterial(alleles);
    }
}
