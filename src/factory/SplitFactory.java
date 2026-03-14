package factory;

import constants.SplitType;
import strategy.EqualSplitStrategy;
import strategy.PercentageSplitStrategy;
import strategy.SpecificSplitStrategy;
import strategy.SplitStrategy;

public class SplitFactory {
    public static SplitStrategy getSplitStrategy(SplitType splitType) {
        System.out.println("getSplitStrategy splitType=" + splitType);
        if (splitType == SplitType.EQUAL) {
            return new EqualSplitStrategy();
        } else if (splitType == SplitType.EXACT) {
            return new SpecificSplitStrategy();
        } else {
            return new PercentageSplitStrategy();
        }
    }
}
