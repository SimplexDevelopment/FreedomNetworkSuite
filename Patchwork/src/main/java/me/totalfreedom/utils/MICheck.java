package me.totalfreedom.utils;

public class MICheck
{
    public double maintainabilityIndex(double fileLength, double vocabulary, double cyclomaticComplexity, double linesOfCode) {
        double halsteadVolume = fileLength * (Math.log(vocabulary) / Math.log(2));
        double maintainabilityIndexUnbounded = 171 - 5.2 * Math.log(halsteadVolume) - 0.23 * cyclomaticComplexity - 16.2 * Math.log(linesOfCode);
        return Math.max(0, maintainabilityIndexUnbounded * 100 / 171);
    }

    public double complexity(double decisionPoints) {
        return decisionPoints + 1;
    }
}
