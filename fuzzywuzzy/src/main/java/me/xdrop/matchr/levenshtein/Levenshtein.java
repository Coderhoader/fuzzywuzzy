package me.xdrop.matchr.levenshtein;

import me.xdrop.matchr.FuzzyWuzzy;
import me.xdrop.matchr.algorithms.AlgorithmBase;
import me.xdrop.matchr.algorithms.AlgorithmFactory;
import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.levenshtein.algorithms.RatioAlgorithm;
import me.xdrop.matchr.levenshtein.algorithms.TokenSet;
import me.xdrop.matchr.levenshtein.algorithms.TokenSort;
import me.xdrop.matchr.levenshtein.algorithms.WeightedRatio;
import me.xdrop.matchr.levenshtein.ratios.PartialRatio;
import me.xdrop.matchr.levenshtein.ratios.SimpleRatio;
import me.xdrop.matchr.model.ScoringMethod;

/**
 * Levenshtein algorithm implementation.
 */
public class Levenshtein extends AlgorithmBase {
    /**
     * Factory for the Levenshtein Algorithm
     */
    public final static AlgorithmFactory<Levenshtein> FACTORY = new Factory();

    /**
     * Private constructor; used by {@link Factory}.
     * Defines default scoring method as {@link Method#SIMPLE_RATIO}
     */
    private Levenshtein() {
        super(Method.WEIGHTED_RATIO);
    }

    /**
     * Predefined Levenshtein scoring methods.
     */
    public enum Method implements ScoringMethod {
        SIMPLE_RATIO(new SimpleRatio()),
        PARTIAL_RATIO(new PartialRatio()),
        WEIGHTED_RATIO(new WeightedRatio()),
        TOKEN_SORT_SIMPLE(new TokenSort(), SIMPLE_RATIO),
        TOKEN_SET_SIMPLE(new TokenSet(), SIMPLE_RATIO),
        TOKEN_SORT_PARTIAL(new TokenSort(), PARTIAL_RATIO),
        TOKEN_SET_PARTIAL(new TokenSet(), PARTIAL_RATIO);

        private final ScoringFunction scoringFunction;
        private final Method subMethod;

        <F extends ScoringFunction> Method(F scoringFunction) {
            this.scoringFunction = scoringFunction;
            this.subMethod = null;
        }

        <F extends ScoringFunction> Method(F scoringFunction, Method subMethod) {
            this.scoringFunction = scoringFunction;
            this.subMethod = subMethod;
        }

        @Override
        public Integer apply(String base, String target) {
            if (scoringFunction instanceof RatioAlgorithm && subMethod != null)
                return ((RatioAlgorithm) scoringFunction).apply(base, target, subMethod.scoringFunction);
            else if (scoringFunction instanceof RatioAlgorithm)
                throw new NullPointerException("No SubMethod defined for method "+name());
            else return scoringFunction.apply(base, target);
        }
    }

    /**
     * Engine implementation for Levenshtein algorithm
     */
    private static class Engine extends FuzzyWuzzy<Levenshtein> {
        /**
         * Private contructor, used by {@link Factory}.
         *
         * @param algo The algorithm to be stored.
         */
        private Engine(Levenshtein algo) {
            super(algo);
        }
    }

    /**
     * Factory implementation for Levenshtein algorithm
     */
    private static class Factory implements AlgorithmFactory<Levenshtein> {
        /**
         * Private constructor, used for instantiating the {@code factory} field.
         */
        private Factory() {
            super();
        }

        @Override
        public FuzzyWuzzy<Levenshtein> craft() {
            return new Engine(new Levenshtein());
        }
    }
}
