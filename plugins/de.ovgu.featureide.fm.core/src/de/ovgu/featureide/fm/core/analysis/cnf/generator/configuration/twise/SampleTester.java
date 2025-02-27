/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2019  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 *
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.core.analysis.cnf.generator.configuration.twise;

import java.util.ArrayList;
import java.util.List;

import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.analysis.cnf.LiteralSet;

/**
 * Tests whether a sample of configurations is valid and achieve a certain coverage.
 *
 * @author Sebastian Krieter
 */
public class SampleTester {

	private List<LiteralSet> sample;
	private CNF cnf;

	public SampleTester(CNF cnf) {
		this.cnf = cnf;
	}

	public void setSample(List<LiteralSet> sample) {
		this.sample = sample;
	}

	public void setCnf(CNF cnf) {
		this.cnf = cnf;
	}

	public List<LiteralSet> getSample() {
		return sample;
	}

	public CNF getCnf() {
		return cnf;
	}

	public int getSize() {
		return sample.size();
	}

	public double getCoverage(CoverageCriterion coverageCriterion) {
		return coverageCriterion.getCoverage(sample);
	}

	public boolean hasInvalidSolutions() {
		final List<LiteralSet> invalidSolutions = getInvalidSolutions(true);
		return !invalidSolutions.isEmpty();
	}

	public LiteralSet getFirstInvalidSolution() {
		final List<LiteralSet> invalidSolutions = getInvalidSolutions(true);
		return invalidSolutions.isEmpty() ? null : invalidSolutions.get(0);
	}

	public List<LiteralSet> getInvalidSolutions() {
		return getInvalidSolutions(false);
	}

	private List<LiteralSet> getInvalidSolutions(boolean cancelAfterFirst) {
		final ArrayList<LiteralSet> invalidSolutions = new ArrayList<>();
		configLoop: for (final LiteralSet solution : sample) {
			for (final LiteralSet clause : cnf.getClauses()) {
				if (!solution.hasDuplicates(clause)) {
					invalidSolutions.add(solution);
					if (cancelAfterFirst) {
						break configLoop;
					}
				}
			}
		}
		return invalidSolutions;
	}

}
